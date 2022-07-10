package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;
import entities.Department;
import shared.mapper.DepartmentMapper;



public class DepartmentService {
	private DBConfig dbConfig;
	private DepartmentMapper departmentMapper;

	public DepartmentService() {
		this.departmentMapper = new DepartmentMapper();
		this.dbConfig=new DBConfig();
	}
	public void createDepartment(Department dept) {
		try {
			PreparedStatement ps=this.dbConfig.getConnection().prepareStatement("INSERT INTO department (dept_name) VALUES (?)");
			ps.setString(1,dept.getDepartmentName());
			ps.executeUpdate();
			ps.close();
		}catch(Exception e) {
			if(e instanceof SQLException) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void updateDepartment(String id, Department dept) {
        try {
            PreparedStatement ps = this.dbConfig.getConnection()
                    .prepareStatement("UPDATE department SET dept_name=? WHERE dept_id=?");
            ps.setString(1, dept.getDepartmentName());
            ps.setString(2, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {

        	e.printStackTrace();
        }
    }
	 public void deleteDepartment(String id) {
	        try {
	            PreparedStatement ps = this.dbConfig.getConnection()
	                    .prepareStatement("DELETE FROM department WHERE dept_id=?");
	            ps.setString(1, id);
	            ps.executeUpdate();
	            ps.close();
	        } catch (Exception e) {

	        	e.printStackTrace();
	        }
	    }
	public List<Department> findAllDepartments() {
		List<Department> deptList=new ArrayList<>();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM department";

			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				Department dept=new Department();
				deptList.add(this.departmentMapper.mapToDepartment(dept, rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return deptList;
	}
	
	public Department finddepartmentById(String id) {
		Department dept= new Department();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM department WHERE dept_id = " + id + ";";

			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				this.departmentMapper.mapToDepartment(dept,rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dept;
	}
}