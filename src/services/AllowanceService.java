package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import config.DBConfig;
import entities.AllowanceDetails;

import shared.mapper.AllowanceMapper;



public class AllowanceService {
	private DBConfig dbConfig;
	private AllowanceMapper allowanceMapper;
	
	public AllowanceService() {
		  this.dbConfig = new DBConfig();
		  this.allowanceMapper=new AllowanceMapper();
	}
	

	public void createAllowanceDetails(AllowanceDetails allowanceDetails) {
		
        try {
            PreparedStatement ps = this.dbConfig.getConnection().prepareStatement("INSERT INTO allowance_details(skills, longevity, allowance_amount, description, hra, ta, ad_employee_id, ad_attendance_id) VALUES (?,?,?,?,?,?,?,?);");

            ps.setString(1, allowanceDetails.getSkills());
            ps.setString(2, allowanceDetails.getLongevity());
            ps.setString(3, allowanceDetails.getAllowance_Amount());
            ps.setString(4, allowanceDetails.getDescription());
            ps.setString(5, allowanceDetails.getHouseRentAllowance());
            ps.setString(6, allowanceDetails.getTransportAllowance());
            ps.setInt(7, allowanceDetails.getEmployee().getId());
            ps.setInt(8, allowanceDetails.getAttendance().getId());

            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
		
    }
	

	
	public List<AllowanceDetails> findAllADetails() {

        List<AllowanceDetails> aDetailsList = new ArrayList<>();

        try (Statement st = this.dbConfig.getConnection().createStatement()) {

            String query = "SELECT * FROM allowance_details\n" +
                    "INNER JOIN employee\n" +
                    "ON employee.emp_id = allowance_details.ad_employee_id\n" +
                    "INNER JOIN attendance\n" +
                    "ON attendance.attendance_id = allowance_details.ad_attendance_id;";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                AllowanceDetails allowanceDetails = new AllowanceDetails();
                aDetailsList.add(this.allowanceMapper.mapToAllowanceDetails(allowanceDetails, rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return aDetailsList;
    }
	
	public AllowanceDetails findAllowanceDetailsById(String id) {

		AllowanceDetails allowanceDetails = new AllowanceDetails();
        try (Statement st = this.dbConfig.getConnection().createStatement()) {

            String query = "SELECT * FROM allowance_details\n" +
                    "INNER JOIN employee\n" +
                    "ON employee.emp_id = allowance_details.ad_employee_id\n" +
                    "INNER JOIN attendance\n" +
                    "ON attendance.attendance_id=allowance_details.ad_attendance_id \n" +
                    "WHERE ad_id=" + id +";";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                this.allowanceMapper.mapToAllowanceDetails(allowanceDetails, rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return allowanceDetails;
    }
	
	public AllowanceDetails findAllowanceDetailsByEmpId(String id) {

		AllowanceDetails allowanceDetails = new AllowanceDetails();
        try (Statement st = this.dbConfig.getConnection().createStatement()) {

            String query = "SELECT * FROM allowance_details\n" +
                    "INNER JOIN employee\n" +
                    "ON employee.emp_id = allowance_details.ad_employee_id\n" +
                    "INNER JOIN attendance\n" +
                    "ON attendance.attendance_id=allowance_details.ad_attendance_id \n" +
                    "WHERE ad_employee_id=" + id +";";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                this.allowanceMapper.mapToAllowanceDetails(allowanceDetails, rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return allowanceDetails;
    }
	
	public void deleteAllowance(String id) {
	        try {
	            PreparedStatement ps = this.dbConfig.getConnection()
	                    .prepareStatement("DELETE FROM allowance_details WHERE ad_id=?;");
	            ps.setString(1, id);
	            ps.executeUpdate();
	            ps.close();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	    }
	
}
