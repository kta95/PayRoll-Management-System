package shared.mapper;

import java.sql.ResultSet;
import entities.Attendance;
import entities.Employee;

public class AttendanceMapper {
	
	public Attendance mapToAttendance(Attendance attendance, ResultSet rs) {
        try {

        	System.out.println("this");
        	attendance.setId(rs.getInt("attendance_id"));

        	attendance.setPresent(rs.getString("present"));
        	attendance.setAbsent(rs.getString("absent"));
        	attendance.setMonth(rs.getString("month"));
        	attendance.setLeaveDays(rs.getString("leave_day"));
        	attendance.setLeave(rs.getString("leaves"));
        	attendance.setHourLate(rs.getString("hour_late"));
        	attendance.setHourOT(rs.getString("hour_overtime"));
        	
        	Employee theEmployee = new Employee();
        	theEmployee.setId(rs.getInt("emp_id"));
        	theEmployee.setName(rs.getString("emp_name"));

        	attendance.setEmployee(theEmployee);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return attendance;
    }
}
