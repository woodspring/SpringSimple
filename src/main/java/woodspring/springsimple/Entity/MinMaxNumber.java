package woodspring.springsimple.Entity;

import java.math.BigDecimal;

public class MinMaxNumber {
	BigDecimal max = null;
	BigDecimal  min = null;
	public BigDecimal getMax() {
		return max;
	}
	public void setMax(BigDecimal max) {
		this.max = max;
	}
	public BigDecimal getMin() {
		return min;
	}
	public void setMin(BigDecimal min) {
		this.min = min;
	}
	
	@Override
	public String toString() {
		return "MinMaxNumber [min=" + min + ", max=" + max + "]";
	}

}
