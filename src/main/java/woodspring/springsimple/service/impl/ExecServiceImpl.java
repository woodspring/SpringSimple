package woodspring.springsimple.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import woodspring.springsimple.service.ConcurrencyService;
import woodspring.springsimple.tasks.RunConfIntWorker;

@Service("ExecService")
public class ExecServiceImpl implements ConcurrencyService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	final ExecutorService pool = Executors.newFixedThreadPool( 5);
	
	@Override
	public String runConfdInterval(Long taskNo) {
		List< RunConfIntWorker> workerList = new ArrayList<>();
		String name="CondInterval";
		for (int ind=0; ind < taskNo; ind++) {
			workerList.add( new RunConfIntWorker(name+ind, "1000", "100", "99.0" ));
		}
		logger.info("START runConfdInterv-------------------------" );
		for ( RunConfIntWorker worker: workerList ) {
			pool.execute( worker);
		}
		
		Thread.holdsLock(1000000);
		return "Complete";
		
	}

}
