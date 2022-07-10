package shared.mapper;

import java.sql.ResultSet;

import entities.Department;

public class DepartmentMapper {
	public Department mapToDepartment(Department department, ResultSet rs) {
        try {
            department.setDepartmentId(rs.getInt("dept_id"));
            department.setDepartmentName(rs.getString("dept_name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return department;
    }
}