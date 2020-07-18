package woodspring.springsimple.utilities;

import java.util.ArrayList;
import java.util.List;

import woodspring.springsimple.Entity.StatisticsParam;

public class GenerateStatisticsParams {
	
	private String worker="Worker";
	private int index =0;
	
	public StatisticsParam getParams(String sampleSize, String loop, String confd ) {
		String workerName = worker+index++;
		
		return new StatisticsParam(workerName, sampleSize, loop, confd);
	}
	
	public StatisticsParam getParams() {
		return getParams("1500", "500", "99.0");
	}

	
	public List<StatisticsParam> getParamsList(int number) {
		List<StatisticsParam> retList =  new ArrayList<>();
		index += number;
		String workerName = worker ;
		long ssize = 1500L;
		long loop = 500L;
		String confd = "99.0";
		for (int ind=0; ind < number; ind++) {
			workerName = worker+index++;
			retList.add( new StatisticsParam( workerName, Long.valueOf( ssize + ind* 10).toString(), Long.valueOf( loop).toString(), confd ));		
		}
		return retList;
	}
}
