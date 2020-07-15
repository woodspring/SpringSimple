package woodspring.springsimple.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import woodspring.springsimple.Entity.Statistics;



public class RunConfIntWorker implements Runnable {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	//@Autowired
	//StatisticsDAO statDao;

	private String workerName;
	private long samplesSize;
	private Statistics statistics;
	private ConfidenceInterval confidentInterval;
	private long loopTimes = 10000;
	private double confd = 90.0;
	private boolean loop = true;
	
	public RunConfIntWorker( String... params) {
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
	public void run() {
		int loopInd=0;
		long startTime = 0L, endTime = 0L;
		while ( loop && ( loopTimes > loopInd)) {
			startTime = System.nanoTime();
			logger.info("ind:"+ loopInd+++" worker:"+ workerName+" size:"+samplesSize+" confd:"+ confd);
			statistics =  confidentInterval.getConfidenceInterval();
			endTime = System.nanoTime();
			logger.info("ind:"+ loopInd +" worker:"+ workerName+" statistics:"+statistics.toString()+" runningTime:["+ (endTime - startTime)+"]" );
			//if (statDao == null) 
			//statDao.save( statistics);
			logger.info("ind:"+ loopInd +" worker:"+ workerName+" after save  statistics:"+statistics.toString()+" runningTime:["+ (System.nanoTime() - endTime)+"]" );
		}
		
	}
	
	public void breakLoop(boolean breakLoop) {
		this.loop = breakLoop;
	}

}
