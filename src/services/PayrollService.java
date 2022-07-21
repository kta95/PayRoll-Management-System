package services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import config.DBConfig;
import entities.Payroll;
import shared.mapper.PayrollMapper;



public class PayrollService {
	private DBConfig dbConfig;
	private PayrollMapper payrollMapper;
//	private EmployeeService employeeService;
//	private AttendanceService attendanceService;
//	private AllowanceService allowanceService;
//	private DeductionService deductionService;
	
	public PayrollService() {
		this.payrollMapper = new PayrollMapper();
		this.dbConfig=new DBConfig();
//		this.employeeService = new EmployeeService();
//		this.attendanceService = new AttendanceService();
//		this.allowanceService = new AllowanceService();
//		this.deductionService = new DeductionService();
	}
	public void createPayroll(Payroll payroll) {
		try {
			  PreparedStatement ps = this.dbConfig.getConnection()
	                    .prepareStatement("INSERT INTO payroll (emp_id, attendance_id, allowance_id, deduction_id, date, gross_salary, net_salary) VALUES (?, ?, ?, ?, ?, ?, ?)");
			  	System.out.println("HRERE");
	            ps.setLong(1, payroll.getEmployee().getId());
	            ps.setLong(2, payroll.getAttendance().getId());
	            ps.setLong(3, payroll.getAllowanceDetails().getAdId());
	            ps.setLong(4, payroll.getDeductionDetails().getDeduction_details_id());
	            payroll.getDate();
				ps.setString(5, LocalDate.now() + "");
	            ps.setString(6, payroll.getGrossSalary());
	            ps.setString(7, payroll.getNetSalary());
			  	System.out.println("HRERE2");

	            ps.executeUpdate();
	            ps.close();
		}catch(Exception e) {
//			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString(), "error", 0);
			
		}
	}
	
	
	public void updatePayroll(String id, Payroll payroll) {
		 try {
	            PreparedStatement ps = this.dbConfig.getConnection()
	                    .prepareStatement("UPDATE payroll SET emp_id=?, attendance_id=?, allowance_id=?, deduction_id=?, date=?, gross_salary=?, net_salary=? WHERE payroll_id=?");
	            ps.setLong(1, payroll.getEmployee().getId());
	            ps.setLong(2, payroll.getAttendance().getId());
	            ps.setLong(3, payroll.getAllowanceDetails().getAdId());
	            ps.setLong(4, payroll.getDeductionDetails().getDeduction_details_id());
	            payroll.getDate();
				ps.setString(5, String.valueOf(LocalDate.now()));
	            ps.setString(6, payroll.getGrossSalary());
	            ps.setString(7, payroll.getNetSalary());
	            ps.setString(8, id);
	            ps.executeUpdate();
	            ps.close();
        } catch (Exception e) {

        	e.printStackTrace();
        }
    }
	 public void deletePayroll(String id) {
	        try {
	            PreparedStatement ps = this.dbConfig.getConnection()
	                    .prepareStatement("DELETE FROM payroll WHERE payroll_id=?;");
	            
	            ps.setString(1, id);
	            ps.executeUpdate();
	            ps.close();
	        } catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.toString(), "unsuccessful", 0);
	        	e.printStackTrace();
	        	return;
	        }
	    }
	public List<Payroll> findAllPayrolls() {
		List<Payroll> payrollList=new ArrayList<>();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM payroll "
					+ "INNER JOIN employee "
					+ "ON employee.emp_id = payroll.emp_id "
					+ "INNER JOIN attendance "
					+ "ON attendance.attendance_id = payroll.attendance_id "
					+ "INNER JOIN allowance_details "
					+ "ON allowance_details.ad_id = payroll.allowance_id "
					+ "INNER JOIN deduction_details "
					+ "ON deduction_details.deduction_id = payroll.deduction_id ;";

			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				Payroll payroll=new Payroll();
				payrollList.add(this.payrollMapper.mapToPayroll(payroll, rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return payrollList;
	}
	
	public Payroll findPayrollById(String id) {
		Payroll payroll= new Payroll();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM payroll "
					+ "INNER JOIN employee "
					+ "ON employee.emp_id = payroll.emp_id "
					+ "INNER JOIN attendance "
					+ "ON attendance.attendance_id = payroll.attendance_id "
					+ "INNER JOIN allowance_details "
					+ "ON allowance_details.ad_id = payroll.allowance_id "
					+ "INNER JOIN deduction_details "
					+ "ON deduction_details.deduction_id = payroll.deduction_id "
					+ "WHERE payroll_id = " + id + ";";
					
			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				this.payrollMapper.mapToPayroll(payroll,rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return payroll;
	}
	

}