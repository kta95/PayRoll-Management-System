package shared.utils;

import entities.Employee;

public class EmployeeHolder {
private static Employee employee;
	
	private EmployeeHolder() {}
	
	public static Employee getSelectedEmployee() {
		return employee;
	}
	
	public static void setSelectedEmployee(Employee employee) {
		EmployeeHolder.employee = employee;
	}
}
