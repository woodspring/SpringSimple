package woodspring.springsimple.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import woodspring.springsimple.Entity.StatisticsParam;
import woodspring.springsimple.service.ConcurrencyService;
import woodspring.springsimple.tasks.CallConfIntWorker;

import woodspring.springsimple.tasks.RunConfIntWorker;
import woodspring.springsimple.utilities.GenerateStatisticsParams;
import woodspring.springsimple.Entity.Statistics;


@Service("ExecutorService")
public class ExecutorServiceImpl implements ConcurrencyService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	final ExecutorService pool = Executors.newFixedThreadPool( 10);

	@Override
	public String runConfdInterval(Long taskNum) {
		StringBuffer strBuf = new StringBuffer();
		GenerateStatisticsParams  params = new GenerateStatisticsParams();
		List<StatisticsParam> paramsList = params.getParamsList( taskNum.intValue());
		
		
		List<Future< Statistics >> statList = paramsList.stream()
			.map(item -> { 
				return pool.submit( new CallConfIntWorker( item)); 
			})
			.collect( Collectors.toList());
		Gson gson = new Gson();
		List<String> stringList = statList.stream()
			.map( item -> {
				String retStr = null;
				try {
					retStr =  gson.toJson( item.get(5, TimeUnit.MINUTES) );
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TimeoutException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return retStr;
			})
			.collect( Collectors.toList());
		
		stringList.stream().forEach( item -> strBuf.append(item));
		
		//Thread.holdsLock(1000000);
		return  strBuf.toString();
	}

}
