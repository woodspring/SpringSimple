package woodspring.springsimple.tasks;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import woodspring.springsimple.Entity.Statistics;
import woodspring.springsimple.Entity.StatisticsParam;

public class CallConfIntWorker implements Callable<Statistics>{
	private final Logger logger = LoggerFactory.getLogger(getClass());

	
	private String workerName;
	private long samplesSize;
	private ConfidenceInterval confidentInterval;
	private long loopTimes = 10000;
	private double confd = 90.0;
	private boolean loop = true;
	
	public CallConfIntWorker( StatisticsParam params) {
		workerName = params.getWorkerName();
		samplesSize = Long.valueOf( params.getSamplesSize()).longValue();
		loopTimes = Long.valueOf( params.getLoopTimes()).longValue();
		 confd = Double.valueOf( params.getConfd()).doubleValue();
		 confidentInterval = new ConfidenceInterval(samplesSize, confd);
	}
	
	public CallConfIntWorker( String... params) {
		workerName = params[0];
		if (params.length > 1) {
			samplesSize = Long.valueOf( params[1]).longValue();
			if ( params.length > 2) {
				loopTimes = Long.valueOf( params[2]).longValue();
				if ( params.length > 3) confd = Double.valueOf( params[3]).doubleValue();
			}
			
		}
		confidentInterval = new ConfidenceInterval(samplesSize, confd);
	}
	@Override
	public Statistics call() throws Exception {
		// TODO Auto-generated method stub
		long startTime = System.nanoTime();
		Statistics retStat =confidentInterval.getConfidenceInterval();
		logger.info("worker:"+workerName+" time:"+( (System.nanoTime() - startTime) / 1000)+" statistics:"+ retStat.toString());
		retStat.setTask(this.workerName);
		return retStat;
	}

}
