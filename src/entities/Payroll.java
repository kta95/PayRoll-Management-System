package entities;

import java.time.LocalDate;
import java.util.Date;

public class Payroll {

	private int id;
	private Employee employee;
	private Attendance attendance;
	private AllowanceDetails allowanceDetails;
	private DeductionDetails deductionDetails;
	private String grossSalary;
	private String netSalary;
	private LocalDate date;
	public Payroll() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Payroll(int id, Employee employee, Attendance attendance, AllowanceDetails allowanceDetails,
			DeductionDetails deductionDetails, String grossSalary, String netSalary, LocalDate date) {
		super();
		this.id = id;
		this.employee = employee;
		this.attendance = attendance;
		this.allowanceDetails = allowanceDetails;
		this.deductionDetails = deductionDetails;
		this.grossSalary = grossSalary;
		this.netSalary = netSalary;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Attendance getAttendance() {
		return attendance;
	}
	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}
	public AllowanceDetails getAllowanceDetails() {
		return allowanceDetails;
	}
	public void setAllowanceDetails(AllowanceDetails allowanceDetails) {
		this.allowanceDetails = allowanceDetails;
	}
	public DeductionDetails getDeductionDetails() {
		return deductionDetails;
	}
	public void setDeductionDetails(DeductionDetails deductionDetails) {
		this.deductionDetails = deductionDetails;
	}
	public String getGrossSalary() {
		return grossSalary;
	}
	public void setGrossSalary(String grossSalary) {
		this.grossSalary = grossSalary;
	}
	public String getNetSalary() {
		return netSalary;
	}
	public void setNetSalary(String netSalary) {
		this.netSalary = netSalary;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date2) {
		this.date = date2;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allowanceDetails == null) ? 0 : allowanceDetails.hashCode());
		result = prime * result + ((attendance == null) ? 0 : attendance.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((deductionDetails == null) ? 0 : deductionDetails.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((grossSalary == null) ? 0 : grossSalary.hashCode());
		result = prime * result + id;
		result = prime * result + ((netSalary == null) ? 0 : netSalary.hashCode());
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
		Payroll other = (Payroll) obj;
		if (allowanceDetails == null) {
			if (other.allowanceDetails != null)
				return false;
		} else if (!allowanceDetails.equals(other.allowanceDetails))
			return false;
		if (attendance == null) {
			if (other.attendance != null)
				return false;
		} else if (!attendance.equals(other.attendance))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (deductionDetails == null) {
			if (other.deductionDetails != null)
				return false;
		} else if (!deductionDetails.equals(other.deductionDetails))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (grossSalary == null) {
			if (other.grossSalary != null)
				return false;
		} else if (!grossSalary.equals(other.grossSalary))
			return false;
		if (id != other.id)
			return false;
		if (netSalary == null) {
			if (other.netSalary != null)
				return false;
		} else if (!netSalary.equals(other.netSalary))
			return false;
		return true;
	}

	
}
