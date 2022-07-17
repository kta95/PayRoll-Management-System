package shared.mapper;



import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Allowance;
import entities.AllowanceDetails;
import entities.Attendance;
import entities.Employee;

public class AllowanceMapper {
    public Allowance mapToAllowance(Allowance allowance, ResultSet rs) {
        try {
             allowance.setAllowanceID(rs.getInt("allowance_id"));
             allowance.setDescription(rs.getString("description"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allowance;

    }
    public AllowanceDetails mapToAllowanceDetails(AllowanceDetails allowanceDetails, ResultSet rs) {
        try {
             allowanceDetails.setAdId(rs.getInt("ad_id"));
             allowanceDetails.setSkills(rs.getString("skills"));
             allowanceDetails.setLongevity(rs.getString("Longevity"));
             allowanceDetails.setAllowance_Amount(rs.getString("allowance_amount"));
             allowanceDetails.setDescription(rs.getString("description"));
             Employee employee = new Employee();
             employee.setId(rs.getInt("emp_id"));
             employee.setName(rs.getString("emp_name"));
             allowanceDetails.setEmployee(employee);
             Attendance attendance = new Attendance();
             attendance.setId(rs.getInt("attendance_id"));
             attendance.setHourOT(rs.getString("hour_overtime"));
             allowanceDetails.setAttendance(attendance);
             allowanceDetails.setHouseRentAllowance(rs.getString("hra"));
             allowanceDetails.setTransportAllowance(rs.getString("ta"));
             
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allowanceDetails;

    }
}