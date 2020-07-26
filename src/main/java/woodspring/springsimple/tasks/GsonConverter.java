package woodspring.springsimple.tasks;

import java.util.concurrent.Callable;
/*
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import woodspring.springsimple.Entity.Statistics;


public class GsonConverter implements Callable<String>{
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public Gson gson = new Gson();
	private  Statistics statistics;
	
	public GsonConverter() {		
	}
	
	GsonConverter(Statistics stat) {
		this.statistics = stat;
	}
	
	public Statistics getStatistics() {
		return statistics;
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

	@Override
	public String call() throws Exception {
		String retStr = gson.toJson(statistics);
		return retStr;
	}

}
*/