package woodspring.springsimple.Entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity(name="STATISTICS_DATA")
@Data
public class Statistics {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name="statistics_id")
	Long id;

	@Column(name="mean", nullable=false)
	private Double mean;
	
	@Column(name="STDDEV", nullable=false)
	private double stdDev;
	
	@Column(name="CONFIDENT", nullable=false)
	private double confident;
	
	@Column(name="LOWBOND", nullable=false)
	private Double lowBound;
	
	@Column(name="UPBOUND", nullable=false)
	private Double upBound;
	
	private String taskName;
	private long startTime = System.nanoTime();
	private long processedTime;
	public Statistics(Double mean, double stdDev, double confident, Double lowBound, Double upBound) {
		super();
		this.mean = mean;
		this.stdDev = stdDev;
		this.confident = confident;
		this.lowBound = lowBound;
		this.upBound = upBound;
	}
	public void setTask(String taskId) {
		this.taskName = taskId;
		processedTime = (System.nanoTime() - startTime) / 1000L;
		
	}
	

}
