package woodspring.springsimple.Entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;
import woodspring.springsimple.tasks.ConfidenceInterval;


@Data
public class StatisticsParam {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private String workerName;
	private long samplesSize;
	private long loopTimes = 500;
	private double confd = 90.0;
	
	
	public StatisticsParam(String... params) {
		this.workerName = params[0];
		this.samplesSize = Long.valueOf( (params[1] == null) ? "1500" : params[1]).longValue() ;
		this.loopTimes = Long.valueOf( (params[2] == null) ? "100" : params[2]).longValue() ;;
		this.confd = Double.valueOf( (params[3] == null) ? "90.0" : params[3]).doubleValue() ;		
	}
	
	public StatisticsParam(String worker, long sampleSize, long loop, double confd) {
		this.workerName = worker;
		this.samplesSize = sampleSize;
		this.loopTimes = loop;
		this.confd = confd;
	}


}
