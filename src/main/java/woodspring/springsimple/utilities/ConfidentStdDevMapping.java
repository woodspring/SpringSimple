package woodspring.springsimple.utilities;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfidentStdDevMapping {
	private final static Logger logger = LoggerFactory.getLogger("ConfidentStdDevMapping");
	static ConcurrentHashMap<String, Double> confStdDevMap = new ConcurrentHashMap<>();
	private static void init() {
		confStdDevMap.put(Double.valueOf(90.0).toString(),  1.645);
		confStdDevMap.put(Double.valueOf(95.0).toString(),  1.96);
		confStdDevMap.put(Double.valueOf(99.0).toString(),  2.576);
	}
	public static Double getStdDev( String  confident ) {
		if (confStdDevMap.isEmpty()) {
			init();
		}
		logger.info("CONTENT:"+confStdDevMap.mappingCount() +" configent:"+confident);
		
		return confStdDevMap.get(confident);
	}
	
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		
		for ( Entry<String, Double> item :confStdDevMap.entrySet()) {
			strBuf.append(" KEY:"+item.getKey()+" value:"+ item.getValue());
		}
		
		
		return strBuf.toString();
	}

}
