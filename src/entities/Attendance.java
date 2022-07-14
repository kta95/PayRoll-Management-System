package entities;

import java.util.Date;
import java.util.Objects;

public class Attendance {

	private int id;
	private String present;
	private String absent;
	private String month;
	private String leaveDays;
	private String hourLate;
	private String hourOT;
	private String leave;
	private Employee employee;
	public Attendance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Attendance(int id, String present, String absent, String month, String leaveDays, String hourLate,
			String hourOT, String leave, Employee employee) {
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
	public String getLeaveDays() {
		return leaveDays;
	}
	public void setLeaveDays(String leaveDays) {
		this.leaveDays = leaveDays;
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
		return Objects.hash(absent, employee, hourLate, hourOT, id, leave, leaveDays, month, present);
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
		return Objects.equals(absent, other.absent) && Objects.equals(employee, other.employee)
				&& Objects.equals(hourLate, other.hourLate) && Objects.equals(hourOT, other.hourOT) && id == other.id
				&& Objects.equals(leave, other.leave) && Objects.equals(leaveDays, other.leaveDays)
				&& Objects.equals(month, other.month) && Objects.equals(present, other.present);
	}
	
	
	
}
