package shared.mapper;

import java.sql.ResultSet;

import entities.AllowanceDetails;
import entities.Attendance;
import entities.DeductionDetails;
import entities.Department;
import entities.Employee;
import entities.Payroll;
import entities.Position;
import entities.UserRole;

public class PayrollMapper {
	public Payroll mapToPayroll(Payroll payroll, ResultSet rs) {
        try {
        	
        	payroll.setId(rs.getInt("payroll_id"));
        	payroll.setDate(rs.getDate("date").toLocalDate());
        	payroll.setGrossSalary(rs.getString("gross_salary"));
        	payroll.setNetSalary(rs.getString("net_salary"));      
        	
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
            payroll.setEmployee(employee);
            
            Attendance attendance = new Attendance();
            attendance.setId(rs.getInt("attendance_id"));
        	attendance.setPresent(rs.getString("present"));
        	attendance.setAbsent(rs.getString("absent"));
        	attendance.setMonth(rs.getString("month"));
        	attendance.setLeave(rs.getString("leaves"));
        	attendance.setHourLate(rs.getString("hour_late"));
        	attendance.setHourOT(rs.getString("hour_overtime"));
        	
        	payroll.setAttendance(attendance);
        	
        	AllowanceDetails allowanceDetails = new AllowanceDetails();
        	allowanceDetails.setAdId(rs.getInt("ad_id"));
        	allowanceDetails.setSkills(rs.getString("skills"));
        	allowanceDetails.setLongevity(rs.getString("Longevity"));
        	allowanceDetails.setAllowance_Amount(rs.getString("allowance_amount"));
        	allowanceDetails.setDescription(rs.getString("description"));
        	allowanceDetails.setHouseRentAllowance(rs.getString("hra"));
        	allowanceDetails.setTransportAllowance(rs.getString("ta"));
        	
            payroll.setAllowanceDetails(allowanceDetails);
            
            DeductionDetails deductionDetails = new DeductionDetails();
            deductionDetails.setDeduction_details_id(rs.getInt("deduction_id"));
            deductionDetails.setTax(rs.getString("tax"));
            deductionDetails.setSSC(rs.getString("SSC"));
            deductionDetails.setDeduction_amount(rs.getDouble("deduction_amount"));
            deductionDetails.setDescription("description");
            
            payroll.setDeductionDetails(deductionDetails);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payroll;
    }
}
