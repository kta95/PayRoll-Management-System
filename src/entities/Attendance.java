package entities;

import java.util.Date;
import java.util.Objects;

public class Attendance {

	private int id;
	private String present;
	private String absent;
	private String month;
	private String hourLate;
	private String hourOT;
	private String leave;
	private Employee employee;
	public Attendance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Attendance(int id, String present, String absent, String month, String hourLate, String hourOT, String leave,
			Employee employee) {
		super();
		this.id = id;
		this.present = present;
		this.absent = absent;
		this.month = month;
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
	public String getPresent() {
		return present;
	}
	public void setPresent(String present) {
		this.present = present;
	}
	public String getAbsent() {
		return absent;
	}
	public void setAbsent(String absent) {
		this.absent = absent;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getHourLate() {
		return hourLate;
	}
	public void setHourLate(String hourLate) {
		this.hourLate = hourLate;
	}
	public String getHourOT() {
		return hourOT;
	}
	public void setHourOT(String hourOT) {
		this.hourOT = hourOT;
	}
	public String getLeave() {
		return leave;
	}
	public void setLeave(String leave) {
		this.leave = leave;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((absent == null) ? 0 : absent.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((hourLate == null) ? 0 : hourLate.hashCode());
		result = prime * result + ((hourOT == null) ? 0 : hourOT.hashCode());
		result = prime * result + id;
		result = prime * result + ((leave == null) ? 0 : leave.hashCode());
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + ((present == null) ? 0 : present.hashCode());
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
		if (absent == null) {
			if (other.absent != null)
				return false;
		} else if (!absent.equals(other.absent))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (hourLate == null) {
			if (other.hourLate != null)
				return false;
		} else if (!hourLate.equals(other.hourLate))
			return false;
		if (hourOT == null) {
			if (other.hourOT != null)
				return false;
		} else if (!hourOT.equals(other.hourOT))
			return false;
		if (id != other.id)
			return false;
		if (leave == null) {
			if (other.leave != null)
				return false;
		} else if (!leave.equals(other.leave))
			return false;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (present == null) {
			if (other.present != null)
				return false;
		} else if (!present.equals(other.present))
			return false;
		return true;
	}

	
	
	
}
