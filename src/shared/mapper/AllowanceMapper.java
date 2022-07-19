package shared.mapper;



import java.sql.ResultSet;
import java.sql.SQLException;

import entities.AllowanceDetails;
import entities.Attendance;
import entities.Employee;
import entities.UserRole;

public class AllowanceMapper {

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
             employee.setGender(rs.getString("emp_gender"));                
             employee.setDateOfBirth(String.valueOf(rs.getDate("emp_dob")));
             employee.setPhone(rs.getString("emp_phone"));
             employee.setEmail(rs.getString("emp_email"));
             employee.setAddress(rs.getString("emp_address"));     	
         	 employee.setHiredDate(String.valueOf(rs.getDate("hired_date")));
         	 UserRole role = UserRole.valueOf(rs.getString("role"));
         	 employee.setRole((role));
             employee.setUsername(rs.getString("username"));
             employee.setActive(rs.getBoolean("active"));
             allowanceDetails.setEmployee(employee);
             
             Attendance attendance = new Attendance();
             attendance.setId(rs.getInt("attendance_id"));
             attendance.setPresent(rs.getString("present"));
             attendance.setAbsent(rs.getString("absent"));
         	 attendance.setMonth(rs.getString("month"));
         	 attendance.setLeaveDays(rs.getString("leave_day"));
         	 attendance.setLeave(rs.getString("leaves"));
         	 attendance.setHourLate(rs.getString("hour_late"));
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