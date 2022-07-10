package entities;

import java.util.Date;

public class Attendance {

	private int id;
	private int present;
	private int absent;
	private String month;
	private int leaveDays;
	private int hourLate;
	private int hourOT;
	private int leave;
	private Employee employee;
	public Attendance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Attendance(int id, int present, int absent, String month, int leaveDays, int hourLate, int hourOT, int leave,
			Employee employee) {
		super();
		this.id = id;
		this.present = present;
		this.absent = absent;
		this.month = month;
		this.leaveDays = leaveDays;
		this.hourLate = hourLate;
		this.hourOT = hourOT;
		this.leave = leave;
		this.employee = employee;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPresent() {
		return present;
	}
	public void setPresent(int present) {
		this.present = present;
	}
	public int getAbsent() {
		return absent;
	}
	public void setAbsent(int absent) {
		this.absent = absent;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getLeaveDays() {
		return leaveDays;
	}
	public void setLeaveDays(int leaveDays) {
		this.leaveDays = leaveDays;
	}
	public int getHourLate() {
		return hourLate;
	}
	public void setHourLate(int hourLate) {
		this.hourLate = hourLate;
	}
	public int getHourOT() {
		return hourOT;
	}
	public void setHourOT(int hourOT) {
		this.hourOT = hourOT;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	public int getLeave() {
		return leave;
	}
	public void setLeave(int leave) {
		this.leave = leave;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + absent;
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + hourLate;
		result = prime * result + hourOT;
		result = prime * result + id;
		result = prime * result + leave;
		result = prime * result + leaveDays;
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + present;
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
		Attendance other = (Attendance) obj;
		if (absent != other.absent)
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (hourLate != other.hourLate)
			return false;
		if (hourOT != other.hourOT)
			return false;
		if (id != other.id)
			return false;
		if (leave != other.leave)
			return false;
		if (leaveDays != other.leaveDays)
			return false;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (present != other.present)
			return false;
		return true;
	}

	
}
