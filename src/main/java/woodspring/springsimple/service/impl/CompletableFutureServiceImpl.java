package woodspring.springsimple.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import woodspring.springsimple.Entity.StatisticsParam;
import woodspring.springsimple.service.ConcurrencyService;
import woodspring.springsimple.tasks.CallConfIntWorker;
import woodspring.springsimple.utilities.GenerateStatisticsParams;


@Service("CompletedFutureService")
public class CompletableFutureServiceImpl implements ConcurrencyService {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public String runConfdInterval(Long taskNum) {
		StringBuffer strBuf = new StringBuffer();
		List<StatisticsParam> paramsList = getParamsList( taskNum);
		//CompletableFuture<CallConfIntWorker>  futureTask = 
		var retList = paramsList.stream()
							.map(item -> CompletableFuture.completedFuture( new CallConfIntWorker( item)))
							.collect(Collectors.toList());
		
		//CompletableFuture<String> stringFuture = ComletableFuture.allOf( retList.toArray(a))
		//														.thenApplyAsync( item -> item.)
		
		
		return strBuf.toString();
	}
	
	private List<StatisticsParam> getParamsList( Long taskNum) {
		GenerateStatisticsParams statParams = new GenerateStatisticsParams();
		List<StatisticsParam> retList = statParams.getParamsList(taskNum.intValue());
		
		return retList;
		
	}

}
