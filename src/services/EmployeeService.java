package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import config.DBConfig;
import entities.Attendance;
import entities.Employee;
import entities.UserRole;
import shared.mapper.EmployeeMapper;



public class EmployeeService {
	private DBConfig dbConfig;
	private EmployeeMapper employeeMapper;
	private AttendanceService attendanceService;
	private AllowanceService allowanceService;
	private DeductionService deductionService;
	
	public EmployeeService() {
		this.employeeMapper = new EmployeeMapper();
		this.dbConfig=new DBConfig();
		this.attendanceService = new AttendanceService();
		this.allowanceService = new AllowanceService();
		this.deductionService = new DeductionService();
	}
	public void createEmployee(Employee employee) {
		try {
			  PreparedStatement ps = this.dbConfig.getConnection()
	                    .prepareStatement("INSERT INTO employee (emp_name, emp_gender, emp_dob, emp_phone, emp_email, emp_address, hired_date, role, active, emp_position_id, emp_department_id, leave_days) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

	            ps.setString(1, employee.getName());
	            ps.setString(2, employee.getGender());
	            ps.setString(3, String.valueOf(employee.getDateOfBirth()));
	            ps.setString(4, employee.getPhone());
	            ps.setString(5, employee.getEmail());
	            ps.setString(6, employee.getAddress());
	            ps.setString(7, String.valueOf(employee.getHiredDate()));
	            ps.setString(8, (employee.getDepartment().getDepartmentName().equals("HR")) ? UserRole.ADMIN.toString() : UserRole.USER.toString());
	            ps.setBoolean(9, true);
	            ps.setLong(10, employee.getPosition().getpId());
	            ps.setInt(11, employee.getDepartment().getDepartmentId());
	            ps.setString(12, employee.getLeaveDays());
	            ps.executeUpdate();
	            ps.close();
		}catch(Exception e) {
			if(e instanceof SQLException) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void updateEmployee(String id, Employee employee) {
		 try {
	            PreparedStatement ps = this.dbConfig.getConnection()
	                    .prepareStatement("UPDATE employee SET emp_name=?, emp_gender=?, emp_dob=?, emp_phone=?, emp_email=?, emp_address=?, hired_date=?, username=?, password=?, role=?, active=?, emp_position_id=?, emp_department_id=?, leave_days=? WHERE emp_id=?");
	            ps.setString(1, employee.getName());
	            ps.setString(2, employee.getGender());
	            ps.setString(3, String.valueOf(employee.getDateOfBirth()));
	            ps.setString(4, employee.getPhone());
	            ps.setString(5, employee.getEmail());
	            ps.setString(6, employee.getAddress());
	            ps.setString(7, String.valueOf(employee.getHiredDate()));
	            ps.setString(8, employee.getUsername());
	            ps.setString(9, employee.getPassword());
	            ps.setString(10, (employee.getDepartment().getDepartmentName().equals("HR"))? UserRole.ADMIN.toString() : UserRole.USER.toString());
	            ps.setBoolean(11, true);
	            ps.setInt(12, employee.getPosition().getpId());
	            ps.setInt(13, employee.getDepartment().getDepartmentId());
	            ps.setString(14, employee.getLeaveDays());
	            ps.setString(15, id);
	            ps.executeUpdate();
	            ps.close();
        } catch (Exception e) {

        	e.printStackTrace();
        }
    }
	 public void deleteEmployee(String id) {
	        try {
	            PreparedStatement ps = this.dbConfig.getConnection()
	                    .prepareStatement("DELETE FROM employee WHERE emp_id=?;");
	            
	            ps.setString(1, id);
	            ps.executeUpdate();
	            ps.close();
	        } catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.toString(), "unsuccessful", 0);
	        	e.printStackTrace();
	        	return;
	        }
//	        attendanceService.deleteAttendance(id);
//	        allowanceService.deleteAllowance(id);
//	        deductionService.deleteDeduction(id);
	    }
	public List<Employee> findAllEmployees() {
		List<Employee> employeeList=new ArrayList<>();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM employee "
					+ "INNER JOIN job_position "
					+ "ON job_position.position_id = employee.emp_position_id "
					+ "INNER JOIN department "
					+ "ON department.dept_id = employee.emp_department_id ORDER BY emp_id ASC;";

			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				Employee Employee=new Employee();
				employeeList.add(this.employeeMapper.mapToEmployee(Employee, rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employeeList;
	}
	
	public Employee findEmployeeById(String id) {
		Employee employee= new Employee();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM employee "
					+ "INNER JOIN job_position "
					+ "ON job_position.position_id = employee.emp_position_id "
					+ "INNER JOIN department "
					+ "ON department.dept_id = employee.emp_department_id "
					+ "WHERE emp_id = " + id + ";";

			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				this.employeeMapper.mapToEmployee(employee,rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employee;
	}
	

}