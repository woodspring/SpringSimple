package woodspring.springsimple.service.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import woodspring.springsimple.Entity.StatisticsParam;
import woodspring.springsimple.service.ConcurrencyService;
import woodspring.springsimple.utilities.GenerateStatisticsParams;


@Component("ExecCompletionService")
public class ExecCompletionServiceImpl implements ConcurrencyService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	final ExecutorService pool = Executors.newFixedThreadPool( 10);

	@Override
	public String runConfdInterval(Long taskNum) {
		StringBuffer strBuf = new StringBuffer();
		GenerateStatisticsParams  params = new GenerateStatisticsParams();
		List<StatisticsParam> paramsList = params.getParamsList( taskNum.intValue());
		
		
		
		return null;
	}

}
