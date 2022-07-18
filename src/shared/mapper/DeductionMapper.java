package shared.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Attendance;
import entities.DeductionDetails;
import entities.Employee;

public class DeductionMapper {
  
    public DeductionDetails mapToDeductionDetails(DeductionDetails deductionDetails, ResultSet rs) {
        try {
             deductionDetails.setDeduction_details_id(rs.getInt("deduction_details_id"));
             deductionDetails.setTax(rs.getString("tax"));
             deductionDetails.setSSC(rs.getString("SSC"));
             deductionDetails.setDeduction_amount(rs.getDouble("deduction_amount"));
             
             Employee employee = new Employee();
             employee.setId(rs.getInt("emp_id"));
             employee.setName(rs.getString("emp_name"));
             deductionDetails.setEmployee(employee);
             
             Attendance attendance = new Attendance();
             attendance.setId(rs.getInt("attendance_id"));
             attendance.setHourLate(rs.getString("hour_late"));
             deductionDetails.setAttendance(attendance);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deductionDetails;

    }
}