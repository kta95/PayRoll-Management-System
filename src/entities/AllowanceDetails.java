package entities;

public class AllowanceDetails {

	private int adId;
	private Allowance allowance;
	private Employee employee;
	private String allowanceType;
	private double allowanceAmount;
	public AllowanceDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AllowanceDetails(int adId, Allowance allowance, Employee employee, String allowanceType,
			double allowanceAmount) {
		super();
		this.adId = adId;
		this.allowance = allowance;
		this.employee = employee;
		this.allowanceType = allowanceType;
		this.allowanceAmount = allowanceAmount;
	}
	public int getAdId() {
		return adId;
	}
	public void setAdId(int adId) {
		this.adId = adId;
	}
	public Allowance getAllowance() {
		return allowance;
	}
	public void setAllowance(Allowance allowance) {
		this.allowance = allowance;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getAllowanceType() {
		return allowanceType;
	}
	public void setAllowanceType(String allowanceType) {
		this.allowanceType = allowanceType;
	}
	public double getAllowanceAmount() {
		return allowanceAmount;
	}
	public void setAllowanceAmount(double allowanceAmount) {
		this.allowanceAmount = allowanceAmount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + adId;
		result = prime * result + ((allowance == null) ? 0 : allowance.hashCode());
		long temp;
		temp = Double.doubleToLongBits(allowanceAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((allowanceType == null) ? 0 : allowanceType.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AllowanceDetails other = (AllowanceDetails) obj;
		if (adId != other.adId)
			return false;
		if (allowance == null) {
			if (other.allowance != null)
				return false;
		} else if (!allowance.equals(other.allowance))
			return false;
		if (Double.doubleToLongBits(allowanceAmount) != Double.doubleToLongBits(other.allowanceAmount))
			return false;
		if (allowanceType == null) {
			if (other.allowanceType != null)
				return false;
		} else if (!allowanceType.equals(other.allowanceType))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		return true;
	}
	
	
	
}
