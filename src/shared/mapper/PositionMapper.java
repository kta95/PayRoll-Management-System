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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return position;
    }
}
