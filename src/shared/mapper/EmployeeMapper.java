package shared.mapper;

import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import entities.Department;
import entities.Position;
import entities.Employee;

public class EmployeeMapper {
	
	public Employee mapToEmployee(Employee employee, ResultSet rs) {
        try {
            employee.setId(rs.getInt("emp_id"));
            employee.setName(rs.getString("emp_name"));
            employee.setGender(rs.getString("emp_gender"));
            employee.setDateOfBirth(rs.getDate("emp_dob"));
            employee.setPhone(rs.getString("emp_phone"));
            employee.setEmail(rs.getString("emp_email"));
            employee.setAddress(rs.getString("emp_address"));
            Department department = new Department();
        	department.setDepartmentId(rs.getInt("department_id"));
        	department.setDepartmentName(rs.getString("dept_name"));
        	Position position = new Position();
        	position.setpId(rs.getInt("position_id"));
        	position.setTitle(rs.getString("position_title"));
        	employee.setDepartment(department);
        	employee.setPosition(position);
        	
        	employee.setHiredDate((rs.getDate("hired_date")));
            employee.setUsername(rs.getString("username"));
            employee.setActive(rs.getBoolean("active"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }
}
