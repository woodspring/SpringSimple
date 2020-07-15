package woodspring.springsimple.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.Data;
import woodspring.springsimple.Entity.Statistics;
import woodspring.springsimple.utilities.ConfidentStdDevMapping;


@Component
@Data
public class ConfidenceInterval {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Random random = null;
	private long size = 30000;
	private List<Double> samples;
	private List<BigDecimal> bdSamples;
	
	private Double  mean =0.0;
	private double  stdDev=0.0;
	private long count =0;
	private double confident = 90.0;
	public ConfidenceInterval() {
		random =  ThreadLocalRandom.current(); 
		bdSamples  = new ArrayList<>();
	}

	public ConfidenceInterval(long size) {
		random =  ThreadLocalRandom.current();
		this.size = size;
		bdSamples  = new ArrayList<>();
	}

	public ConfidenceInterval(long size, double confident ) {
		random =  ThreadLocalRandom.current();
		this.size = size;
		this.confident =confident;
		bdSamples  = new ArrayList<>();
	}
	public BigDecimal nextRandom() {
		BigDecimal retBD = new BigDecimal(random.nextGaussian());
		return retBD;
	}
	
	private boolean getSamples() {
		//logger.info("getSamples size:"+size);
		//DoubleStream $ = random.doubles();
		//samples = $.limit(size).boxed().collect(Collectors.toList());
		//mean = samples.stream().reduce(Double::sum).get();
		// count = samples.size();
		mean = bdSamples.stream().reduce(BigDecimal.ZERO, (p, q) -> p.add(q)).doubleValue();
		count = bdSamples.size();
		mean = mean / count;
		//logger.info("getSamples<--------------------------------- count:"+ count+"  mean:"+ mean);
		return true;
	}
	
	public Statistics getConfidenceInterval() {
		for (int ind=0; ind < size; ind++) {
			BigDecimal bd = nextRandom();
			bdSamples.add( bd);
			//logger.info("samples BigDecimal:"+ bd);
		}
		//logger.info("samples size:"+ size);
		getSamples();
		//logger.info("samples mean:"+ mean);
		//int ind=0;
		//stdDev = samples.stream().map( value -> {
		//	double theCont = (value - mean);
		//	theCont = theCont *theCont;
		//	theCont = theCont / (count-1);
		//	logger.info("map    value:"+value+"   theCont:"+ theCont);
		//	return theCont;
		//})
		//.reduce(Double::sum).get();

		stdDev = bdSamples.stream().map( value -> {
			double theCont = (value.doubleValue() - mean);
			theCont = theCont *theCont;
			theCont = theCont / (count-1);
			//logger.info("map    value:"+value+"   theCont:"+ theCont);
			theCont = Math.sqrt( theCont);
			return theCont;
		})
		.reduce(Double::sum).get();
		//logger.info("samples stdDev:"+ stdDev);
		Double conDev = ConfidentStdDevMapping.getStdDev(Double.valueOf(confident).toString() );
		//logger.info("samples confident:"+ confident+"     conDev:"+conDev);
		Double low = mean - conDev * stdDev;
		Double up = mean + conDev * stdDev;
		Statistics stat = new Statistics( mean, stdDev,  confident, low, up);
		logger.info("samples statisticse:"+ stat.toString());
		return stat;
		
	}

}
