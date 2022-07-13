package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;
import entities.Position;
import shared.mapper.PositionMapper;



public class PositionService {
	private DBConfig dbConfig;
	private PositionMapper positionMapper;

	public PositionService() {
		this.positionMapper = new PositionMapper();
		this.dbConfig=new DBConfig();
	}
	public void createPosition(Position position) {
		try {
			PreparedStatement ps=this.dbConfig.getConnection().prepareStatement("INSERT INTO job_position (position_title, basic_salary) VALUES (?,?)");
			ps.setString(1, position.getTitle());
			ps.setDouble(2, position.getBasicSalary());
			ps.executeUpdate();
			ps.close();
		}catch(Exception e) {
			if(e instanceof SQLException) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void updatePosition(String id, Position position) {
        try {
            PreparedStatement ps = this.dbConfig.getConnection()
                    .prepareStatement("UPDATE job_position SET position_title=?, basic_salary=? WHERE position_id=?");
            ps.setString(1, position.getTitle());
            ps.setDouble(2, position.getBasicSalary());
			ps.setString(3, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {

        	e.printStackTrace();
        }
    }
	 public void deletePosition(String id) {
	        try {
	            PreparedStatement ps = this.dbConfig.getConnection()
	                    .prepareStatement("DELETE FROM job_position WHERE position_id=?");
	            ps.setString(1, id);
	            ps.executeUpdate();
	            ps.close();
	        } catch (Exception e) {

	        	e.printStackTrace();
	        }
	    }
	public List<Position> findAllPositions() {
		List<Position> positionList=new ArrayList<>();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM job_position;";

			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				Position position=new Position();
				positionList.add(this.positionMapper.mapToPosition(position, rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return positionList;
	}
	
	public Position findPositionById(String id) {
		Position position= new Position();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM job_position "
					+ "WHERE position_id = " + id + ";";

			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				this.positionMapper.mapToPosition(position,rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return position;
	}
}