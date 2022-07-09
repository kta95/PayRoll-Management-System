package entities;

import java.util.List;

public class Department {

	private int departmentId;
	private String departmentName;

	private List<Position> positon;

	public Department(int departmentId, String departmentName, List<Position> positon) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.positon = positon;
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
	
}
