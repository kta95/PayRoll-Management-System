package entities;

import java.util.List;

public class Department {

	private int departmentId;
	private String departmentName;

	private List<Position> positon;
	private List<Employee> employee;
	public Department(int departmentId, String departmentName, List<Position> positon, List<Employee> employee) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.positon = positon;
		this.employee = employee;
	}

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<Position> getPositon() {
		return positon;
	}

	public void setPositon(List<Position> positon) {
		this.positon = positon;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
	
}
