package services;

import java.sql.PreparedStatement;

import config.DBConfig;
import entities.Attendance;
import repositories.AttendanceRepo;

public class AttendanceService implements AttendanceRepo{
	private final DBConfig DBConfig;
	
	public AttendanceService() {
		DBConfig = new DBConfig();
	}
	@Override
	public void createAttendance(Attendance attendance) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = this.DBConfig.getConnection()
					.prepareStatement("INSERT INTO attendance (present, absent, month, leave, hour_late, hour_overtime, attd_emp_id)" +
							"VALUES (?,?,?,?,?,?,?);");
			ps.setInt(1, attendance.getPresent());
			ps.setInt(2, attendance.getAbsent());
			ps.setString(3, attendance.getMonth());
			ps.setInt(4, attendance.getAbsent());
			ps.setInt(5, attendance.getPresent());
			ps.setInt(6, attendance.getAbsent());
			ps.setInt(7, attendance.getPresent());
			
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
