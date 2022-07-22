package shared.mapper;

import java.sql.ResultSet;
import entities.Attendance;
import entities.Employee;
import entities.UserRole;

public class AttendanceMapper {
	
	public Attendance mapToAttendance(Attendance attendance, ResultSet rs) {
        try {

        	attendance.setId(rs.getInt("attendance_id"));

        	attendance.setPresent(rs.getString("present"));
        	attendance.setAbsent(rs.getString("absent"));
        	attendance.setMonth(rs.getString("month"));
        	attendance.setLeave(rs.getString("leaves"));
        	attendance.setHourLate(rs.getString("hour_late"));
        	attendance.setHourOT(rs.getString("hour_overtime"));
        	
        	Employee theEmployee = new Employee();
        	theEmployee.setId(rs.getInt("emp_id"));
        	theEmployee.setName(rs.getString("emp_name"));
        	theEmployee.setGender(rs.getString("emp_gender"));                
        	theEmployee.setDateOfBirth(String.valueOf(rs.getDate("emp_dob")));
        	theEmployee.setPhone(rs.getString("emp_phone"));
        	theEmployee.setEmail(rs.getString("emp_email"));
        	theEmployee.setAddress(rs.getString("emp_address"));     	
        	theEmployee.setHiredDate(String.valueOf(rs.getDate("hired_date")));
          	UserRole role = UserRole.valueOf(rs.getString("role"));
          	theEmployee.setRole((role));
          	theEmployee.setUsername(rs.getString("username"));
          	theEmployee.setActive(rs.getBoolean("active"));

        	attendance.setEmployee(theEmployee);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return attendance;
    }
}
