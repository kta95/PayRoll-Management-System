package shared.mapper;

import java.sql.ResultSet;

import entities.Department;
import entities.Position;

public class PositionMapper {
	public Position mapToPosition(Position position, ResultSet rs) {
        try {
        	position.setpId(rs.getInt("position_id"));
        	position.setTitle(rs.getString("position_title"));
        	position.setBasicSalary(rs.getDouble("basic_salary"));
        	Department department = new Department();
        	department.setDepartmentId(rs.getInt("department_id"));
        	department.setDepartmentName(rs.getString("dept_name"));
        	position.setDepartment(department);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return position;
    }
}
