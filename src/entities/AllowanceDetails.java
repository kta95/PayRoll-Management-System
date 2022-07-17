package entities;

import java.util.Objects;

public class AllowanceDetails {

	private int adId;
	
	private Employee employee;
	
	private String skills;
	
	private String allowance_Amount;
	
	private String longevity;
	
	private String description;
	
	private String houseRentAllowance;
	
	private String transportAllowance;
	
	private Attendance attendance;
	
	public AllowanceDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public AllowanceDetails(int adId, Employee employee, String skills, String allowance_Amount, String longevity,
			String description, String houseRentAllowance, String transportAllowance, Attendance attendance) {
		super();
		this.adId = adId;
		this.employee = employee;
		this.skills = skills;
		this.allowance_Amount = allowance_Amount;
		this.longevity = longevity;
		this.description = description;
		this.houseRentAllowance = houseRentAllowance;
		this.transportAllowance = transportAllowance;
		this.attendance = attendance;
	}



	public int getAdId() {
		return adId;
	}

	public void setAdId(int adId) {
		this.adId = adId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getAllowance_Amount() {
		return allowance_Amount;
	}

	public void setAllowance_Amount(String allowance_Amount) {
		this.allowance_Amount = allowance_Amount;
	}

	public String getLongevity() {
		return longevity;
	}

	public void setLongevity(String longevity) {
		this.longevity = longevity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHouseRentAllowance() {
		return houseRentAllowance;
	}

	public void setHouseRentAllowance(String houseRentAllowance) {
		this.houseRentAllowance = houseRentAllowance;
	}

	public String getTransportAllowance() {
		return transportAllowance;
	}

	public void setTransportAllowance(String transportAllowance) {
		this.transportAllowance = transportAllowance;
	}

	public Attendance getAttendance() {
		return attendance;
	}

	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + adId;
		result = prime * result + ((allowance_Amount == null) ? 0 : allowance_Amount.hashCode());
		result = prime * result + ((attendance == null) ? 0 : attendance.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((houseRentAllowance == null) ? 0 : houseRentAllowance.hashCode());
		result = prime * result + ((longevity == null) ? 0 : longevity.hashCode());
		result = prime * result + ((skills == null) ? 0 : skills.hashCode());
		result = prime * result + ((transportAllowance == null) ? 0 : transportAllowance.hashCode());
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
		if (allowance_Amount == null) {
			if (other.allowance_Amount != null)
				return false;
		} else if (!allowance_Amount.equals(other.allowance_Amount))
			return false;
		if (attendance == null) {
			if (other.attendance != null)
				return false;
		} else if (!attendance.equals(other.attendance))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (houseRentAllowance == null) {
			if (other.houseRentAllowance != null)
				return false;
		} else if (!houseRentAllowance.equals(other.houseRentAllowance))
			return false;
		if (longevity == null) {
			if (other.longevity != null)
				return false;
		} else if (!longevity.equals(other.longevity))
			return false;
		if (skills == null) {
			if (other.skills != null)
				return false;
		} else if (!skills.equals(other.skills))
			return false;
		if (transportAllowance == null) {
			if (other.transportAllowance != null)
				return false;
		} else if (!transportAllowance.equals(other.transportAllowance))
			return false;
		return true;
	}

	

	
	
}
