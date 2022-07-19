package forms.views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JComboBox;
import com.toedter.calendar.JMonthChooser;

import entities.Employee;

import java.awt.event.ActionListener;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class PayrollForm extends JInternalFrame {
	private JTextField txtEmpName;
	private JTextField txtPosition;
	private JTextField txtDept;
	JComboBox<String> EmpIdCombo = new JComboBox<String>();
	private JTextField txtAttdId;
	private JTextField txtPresent;
	private JTextField textField_1;
	private JTextField txtLate;
	private JTextField txtOT;
	private JTextField txtAllowance;
	private JTextField textField_2;
	private JTextField txtAllowanceAmount;
	private JTextField txtBasicS;
	private JTextField txtGrossS;
	private JTextField txtDedukAmount;
	private JTextField txtNetS;
	private JTextField textField;
    private Optional<Employee> selectedEmployee;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayrollForm frame = new PayrollForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PayrollForm() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(0, 0, 976, 564);
		getContentPane().add(tabbedPane);
		
		JPanel payrollManagement = new JPanel();
		payrollManagement.setBorder(new LineBorder(new Color(0, 0, 0)));
		payrollManagement.setBackground(Color.WHITE);
		tabbedPane.addTab("Payroll Management", null, payrollManagement, null);
		payrollManagement.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setToolTipText("Payroll Registration");
		panel.setName("Payroll Registration");
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Payroll Management", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBackground(Color.WHITE);
		panel.setBounds(66, 11, 822, 490);
		payrollManagement.add(panel);
		
		EmpIdCombo.setBounds(188, 81, 200, 30);
		panel.add(EmpIdCombo);
		
		JLabel lblEmpId = new JLabel("Employee ID");
		lblEmpId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmpId.setBounds(54, 79, 124, 30);
		panel.add(lblEmpId);
		
		txtEmpName = new JTextField();
		txtEmpName.setEditable(false);
		txtEmpName.setBounds(188, 122, 200, 30);
		panel.add(txtEmpName);
		txtEmpName.setColumns(10);
		
		JLabel lblEmployeeName = new JLabel("Employee Name");
		lblEmployeeName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmployeeName.setBounds(54, 120, 124, 30);
		panel.add(lblEmployeeName);
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPosition.setBounds(54, 202, 124, 30);
		panel.add(lblPosition);
		
		txtPosition = new JTextField();
		txtPosition.setEditable(false);
		txtPosition.setColumns(10);
		txtPosition.setBounds(188, 204, 200, 30);
		panel.add(txtPosition);
		
		txtDept = new JTextField();
		txtDept.setEditable(false);
		txtDept.setColumns(10);
		txtDept.setBounds(188, 245, 200, 30);
		panel.add(txtDept);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDepartment.setBounds(54, 243, 124, 30);
		panel.add(lblDepartment);
		
		txtAttdId = new JTextField();
		txtAttdId.setEditable(false);
		txtAttdId.setColumns(10);
		txtAttdId.setBounds(188, 163, 200, 30);
		panel.add(txtAttdId);
		
		JLabel lblAttendanceId = new JLabel("Attendance ID");
		lblAttendanceId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAttendanceId.setBounds(54, 161, 124, 30);
		panel.add(lblAttendanceId);
		
		JLabel lblAttendanceId_1 = new JLabel("Month");
		lblAttendanceId_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAttendanceId_1.setBounds(54, 38, 124, 30);
		panel.add(lblAttendanceId_1);
		
		JMonthChooser monthChooser = new JMonthChooser();
		monthChooser.setMonth(0);
		monthChooser.setBounds(188, 38, 200, 30);
		panel.add(monthChooser);
		
		JLabel lblPresent = new JLabel("Present (Day)");
		lblPresent.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPresent.setBounds(54, 284, 124, 30);
		panel.add(lblPresent);
		
		txtPresent = new JTextField();
		txtPresent.setEditable(false);
		txtPresent.setColumns(10);
		txtPresent.setBounds(188, 286, 200, 30);
		panel.add(txtPresent);
		
		JLabel lblAbsentday = new JLabel("Absent (Day)");
		lblAbsentday.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAbsentday.setBounds(54, 325, 124, 30);
		panel.add(lblAbsentday);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(188, 327, 200, 30);
		panel.add(textField_1);
		
		JLabel lblLatehour = new JLabel("Late (Hour)");
		lblLatehour.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLatehour.setBounds(54, 366, 124, 30);
		panel.add(lblLatehour);
		
		txtLate = new JTextField();
		txtLate.setEditable(false);
		txtLate.setColumns(10);
		txtLate.setBounds(188, 368, 200, 30);
		panel.add(txtLate);
		
		JLabel lblOvertimehour = new JLabel("Overtime (Hour)");
		lblOvertimehour.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOvertimehour.setBounds(54, 407, 124, 30);
		panel.add(lblOvertimehour);
		
		txtOT = new JTextField();
		txtOT.setEditable(false);
		txtOT.setColumns(10);
		txtOT.setBounds(188, 409, 200, 30);
		panel.add(txtOT);
		
		JLabel lblAllowanceId = new JLabel("Allowance ID");
		lblAllowanceId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAllowanceId.setBounds(435, 38, 124, 30);
		panel.add(lblAllowanceId);
		
		txtAllowance = new JTextField();
		txtAllowance.setEditable(false);
		txtAllowance.setColumns(10);
		txtAllowance.setBounds(569, 38, 200, 30);
		panel.add(txtAllowance);
		
		JLabel lblDeductionId = new JLabel("Deduction ID");
		lblDeductionId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDeductionId.setBounds(435, 79, 124, 30);
		panel.add(lblDeductionId);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(569, 81, 200, 30);
		panel.add(textField_2);
		
		JLabel lblTotalAllowance = new JLabel("Total Allowance");
		lblTotalAllowance.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotalAllowance.setBounds(435, 163, 124, 30);
		panel.add(lblTotalAllowance);
		
		txtAllowanceAmount = new JTextField();
		txtAllowanceAmount.setEditable(false);
		txtAllowanceAmount.setColumns(10);
		txtAllowanceAmount.setBounds(569, 163, 200, 30);
		panel.add(txtAllowanceAmount);
		
		JLabel lblBasicSalary = new JLabel("Basic Salary");
		lblBasicSalary.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBasicSalary.setBounds(435, 122, 124, 30);
		panel.add(lblBasicSalary);
		
		txtBasicS = new JTextField();
		txtBasicS.setEditable(false);
		txtBasicS.setColumns(10);
		txtBasicS.setBounds(569, 122, 200, 30);
		panel.add(txtBasicS);
		
		JLabel lblGrossSalary = new JLabel("Gross Salary");
		lblGrossSalary.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGrossSalary.setBounds(435, 202, 124, 30);
		panel.add(lblGrossSalary);
		
		txtGrossS = new JTextField();
		txtGrossS.setEditable(false);
		txtGrossS.setColumns(10);
		txtGrossS.setBounds(569, 204, 200, 30);
		panel.add(txtGrossS);
		
		JLabel lblTotalDeduction = new JLabel("Total Deduction");
		lblTotalDeduction.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotalDeduction.setBounds(435, 245, 124, 30);
		panel.add(lblTotalDeduction);
		
		txtDedukAmount = new JTextField();
		txtDedukAmount.setEditable(false);
		txtDedukAmount.setColumns(10);
		txtDedukAmount.setBounds(569, 245, 200, 30);
		panel.add(txtDedukAmount);
		
		JLabel lblNetSalary = new JLabel("Net Salary");
		lblNetSalary.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNetSalary.setBounds(435, 325, 124, 30);
		panel.add(lblNetSalary);
		
		txtNetS = new JTextField();
		txtNetS.setEditable(false);
		txtNetS.setColumns(10);
		txtNetS.setBounds(569, 325, 200, 30);
		panel.add(txtNetS);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setEnabled(false);
		btnRegister.setBounds(435, 407, 103, 65);
		panel.add(btnRegister);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCalculate.setBounds(550, 407, 103, 65);
		panel.add(btnCalculate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(666, 407, 103, 65);
		panel.add(btnClear);
		
		JPanel payrollList = new JPanel();
		payrollList.setBorder(new LineBorder(new Color(0, 0, 0)));
		payrollList.setBackground(Color.WHITE);
		tabbedPane.addTab("Payroll List", null, payrollList, null);
		payrollList.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 67, 951, 298);
		payrollList.add(scrollPane);
		
		textField = new JTextField("Search By Employee Name");
		textField.setForeground(Color.GRAY);
		textField.setColumns(10);
		textField.setBounds(10, 26, 200, 30);
		payrollList.add(textField);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(220, 26, 89, 30);
		payrollList.add(btnSearch);
		
		this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		BasicInternalFrameUI ui= (BasicInternalFrameUI)this.getUI();
		ui.setNorthPane(null);
		
		
		setBounds(0, 0, 976, 591);

	}
}
