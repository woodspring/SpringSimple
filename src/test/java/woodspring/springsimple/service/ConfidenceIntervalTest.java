package woodspring.springsimple.service;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import woodspring.springsimple.Entity.Statistics;
import woodspring.springsimple.tasks.ConfidenceInterval;

public class ConfidenceIntervalTest {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	//@Autowired
	//ConfidenceInterval confInter;

	
	@Test
	public void testInit() {
		long num = 1000L;
		ConfidenceInterval confInter = new ConfidenceInterval(num, 90.0);
		//logger.info("confInter:"+confInter.getSize());
		//Assertions.assertNotNull( Long.valueOf(confInter.getSize()).toString());
	}
//
//	@Test
//	public void testnextRandom() {
//		long num = 1000L;
//		ConfidenceInterval confInter = new ConfidenceInterval(num, 90.0);
//		logger.info("confInter:"+confInter.getRandom().nextGaussian());
//		Assertions.assertNotNull( Long.valueOf(confInter.getSize()).toString());
//	}
//
//	@Test
//	public void testgetConfidenceInterval() {
//		long num = 100000L;
//		ConfidenceInterval confInter = new ConfidenceInterval(num, 99.0);
//		Statistics stat = confInter.getConfidenceInterval();
//		logger.info("confInter:"+stat.toString());
//		Assertions.assertNotNull( Long.valueOf(confInter.getSize()).toString());
//	}
}
