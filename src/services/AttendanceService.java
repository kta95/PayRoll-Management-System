package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;
import entities.Attendance;
import entities.Employee;
import repositories.AttendanceRepo;
import shared.mapper.AttendanceMapper;

public class AttendanceService implements AttendanceRepo{
	private final DBConfig dbConfig;
	private AttendanceMapper attendanceMapper;
	
	public AttendanceService() {
		dbConfig = new DBConfig();
		attendanceMapper = new AttendanceMapper();
	}
	@Override
	public void createAttendance(Attendance attendance) {
		// TODO Auto-generated method stub
		
		
		try {
			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("INSERT INTO attendance(present, absent, month, leaves, hour_late, hour_overtime, attd_emp_id) VALUES (?,?,?,?,?,?,?);");
			ps.setString(1, attendance.getPresent());
			ps.setString(2, attendance.getAbsent());
			ps.setString(3, attendance.getMonth());
			ps.setString(4, attendance.getLeave());
			ps.setString(5, attendance.getHourLate());
			ps.setString(6, attendance.getHourOT());
			ps.setInt(7, attendance.getEmployee().getId());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Attendance> findAllAttendances() {
		List<Attendance> attendanceList=new ArrayList<>();
		try (Statement st = this.dbConfig.getConnection().createStatement())  {
			
			String query = "SELECT * FROM attendance INNER JOIN employee ON employee.emp_id = attendance.attd_emp_id ORDER BY attendance_id ASC;";

			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				Attendance attendance = new Attendance();

				attendanceList.add(this.attendanceMapper.mapToAttendance(attendance, rs));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return attendanceList;
	}
	
	public Attendance findAttendanceByEmpId(String id) {
		Attendance attendance = new Attendance();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM attendance "
					+ "INNER JOIN employee "
					+ "ON employee.emp_id = attendance.attd_emp_id "
					+ "WHERE attd_emp_id = " + id + " ORDER BY attendance_id ASC;";

			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				this.attendanceMapper.mapToAttendance(attendance,rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return attendance;
	}
	
	public Attendance findAttendanceById(String id) {
		Attendance attendance = new Attendance();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM attendance "
					+ "INNER JOIN employee "
					+ "ON employee.emp_id = attendance.attd_emp_id "
					+ "WHERE attendance_id = " + id + " ORDER BY attendance_id ASC;";

			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				this.attendanceMapper.mapToAttendance(attendance,rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return attendance;
	}
	 public void deleteAttendance(String id) {
	        try {
	            PreparedStatement ps = this.dbConfig.getConnection()
	                    .prepareStatement("DELETE FROM attendance WHERE attendance_id=?;");
	            ps.setString(1, id);
	            ps.executeUpdate();
	            ps.close();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	    }
}
