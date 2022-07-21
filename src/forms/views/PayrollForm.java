package forms.views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.border.EtchedBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import com.toedter.calendar.JMonthChooser;

import entities.AllowanceDetails;
import entities.Attendance;
import entities.DeductionDetails;
import entities.Employee;
import entities.Payroll;
import services.AllowanceService;
import services.AttendanceService;
import services.DeductionService;
import services.DepartmentService;
import services.EmployeeService;
import services.PayrollService;
import services.PositionService;
import shared.utils.EmployeeHolder;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class PayrollForm extends JInternalFrame {
	private JTextField txtEmpName;
	private JTextField txtPosition;
	private JTextField txtDept;
	JComboBox<String> EmpIdCombo = new JComboBox<String>();
	private JTextField txtAttdId;
	private JTextField txtPresent;
	private JTextField txtAbsent;
	private JTextField txtLate;
	private JTextField txtOT;
	private JTextField txtAllowance;
	private JTextField txtDeduk;
	private JTextField txtAllowanceAmount;
	private JTextField txtBasicS;
	private JTextField txtGrossS;
	private JTextField txtDedukAmount;
	private JTextField txtNetS;
	private JTextField textField;
    private DefaultTableModel dtm = new DefaultTableModel();
    private List<Employee> employeeList = new ArrayList<>();
    private Optional<Employee> selectedEmployee;
    private Employee employee;
    private Attendance attendance;
    private AllowanceDetails allowanceDetails;
    private DeductionDetails deductionDetails;
    private EmployeeService employeeService;
    private AttendanceService attendanceService;
    private AllowanceService allowanceService;
    private DeductionService deductionService;
    private PayrollService payrollService;
    JTable tblPayroll = new JTable();
	JButton btnRegister = new JButton("Register");
	private List<Payroll> payrollList = new ArrayList<>();
	private List<Payroll> filteredPayrollList = new ArrayList<>();
	JMonthChooser monthChooser = new JMonthChooser();
	String[] months = new String[12];
	List<String> attendanceIdRecords = new ArrayList<>();
	private Payroll payroll;
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
		
		this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		BasicInternalFrameUI ui= (BasicInternalFrameUI)this.getUI();
		ui.setNorthPane(null);
		this.months = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

		initialize();
		initializeDependancy();
		loadEmployeeForComboBox();
		setTableDesign();
		loadAllPayroll(Optional.empty());
	}
	
	private void initializeDependancy() {

		this.employeeService = new EmployeeService();
		this.attendanceService = new AttendanceService();
		this.allowanceService = new AllowanceService();
		this.deductionService = new DeductionService();
		this.payrollService = new PayrollService();

	}
	
	private void setTableDesign() {
		dtm.addColumn("Payroll ID");
		dtm.addColumn("Employee ID");
		dtm.addColumn("Name");
//		dtm.addColumn("Position");
//		dtm.addColumn("Department");
		dtm.addColumn("Month");
		dtm.addColumn("Present");
		dtm.addColumn("Absent");
		dtm.addColumn("Late");
		dtm.addColumn("Overtime");
//		dtm.addColumn("Basic Salary");
		dtm.addColumn("Total Allowance");
		dtm.addColumn("Gross Salary");
		dtm.addColumn("Total Deduction");
		dtm.addColumn("Net Salary");
		dtm.addColumn("Date");
		
		this.tblPayroll.setModel(dtm);
	}
	
	private void loadAllPayroll(Optional<List<Payroll>> optionalPayrolls) {
		this.dtm = (DefaultTableModel) this.tblPayroll.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();
		
		this.payrollList=this.payrollService.findAllPayrolls();
		this.filteredPayrollList=optionalPayrolls.orElseGet(()->this.payrollList)
				.stream().collect(Collectors.toList());
	
            
		filteredPayrollList.forEach(p-> {
            	Object[] row =new Object [20]; 
            			row[0] = p.getId();
            			row[1] = p.getEmployee().getId();
            			row[2] = p.getEmployee().getName();

            			row[3] = p.getAttendance().getMonth();
            			row[4] = p.getAttendance().getPresent();
            			row[5] = p.getAttendance().getAbsent();
            			row[6] = p.getAttendance().getHourLate();
            			row[7] = p.getAttendance().getHourOT();
            			row[8] = p.getAllowanceDetails().getAllowance_Amount();
            			row[9] = p.getGrossSalary();
            			row[10] = p.getDeductionDetails().getDeduction_amount();
            			row[11] = p.getNetSalary();
            			row[12] = p.getDate() + "";
            	dtm.addRow(row);
            });
			this.tblPayroll.setModel(dtm);
       
	}
	
	
	private void setPayrollDataFromForm(Payroll payroll) {
		String id = (String) EmpIdCombo.getSelectedItem();
		Employee employee = new Employee();
		employee = employeeService.findEmployeeById(id);

		
		
		Attendance attendance = new Attendance();
    	attendance = attendanceService.findAttendanceByEmpId(id);
    	
    	AllowanceDetails allowanceDetails = new AllowanceDetails();
    	allowanceDetails = allowanceService.findAllowanceDetailsByEmpId(id);
    	
    	DeductionDetails deductionDetails = new DeductionDetails();
    	deductionDetails = deductionService.findDeductionDetailsByEmpId(id);
    	
    	
    	
		payroll.setEmployee(employee);
		payroll.setAllowanceDetails(allowanceDetails);
		payroll.setAttendance(attendance);
		payroll.setDeductionDetails(deductionDetails);
		LocalDate date = LocalDate.now();
		payroll.setDate(date);
		payroll.setGrossSalary(txtGrossS.getText());
		payroll.setNetSalary(txtNetS.getText());
		
	}
	
	private void resetFormDate() {
		EmpIdCombo.setSelectedIndex(0);
		txtEmpName.setText("");
    	txtAttdId.setText("");
    	txtPosition.setText("");
    	txtDept.setText("");
    	txtPresent.setText("");
    	txtAbsent.setText("");
    	txtLate.setText("");
    	txtOT.setText("");
    	txtAllowance.setText("");
    	txtDeduk.setText("");
    	txtAllowanceAmount.setText("");
    	txtDedukAmount.setText("");
    	txtBasicS.setText("");		
	}
	
	
	
	private void loadEmployeeForComboBox() {
	        this.EmpIdCombo.addItem("- Select -");
	        this.employeeList = this.employeeService.findAllEmployees();
	        this.employeeList.forEach(e -> this.EmpIdCombo.addItem(String.valueOf(e.getId())));
	    }
	
	private void initialize() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setBounds(0, 0, 1065, 588);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(0, 0, 1065, 588);
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
		panel.setBounds(119, 11, 822, 490);
		payrollManagement.add(panel);
		
		EmpIdCombo.setBounds(188, 81, 200, 30);
		
		EmpIdCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = (String) EmpIdCombo.getSelectedItem();
                selectedEmployee = employeeList.stream()
                        .filter(emp -> String.valueOf(emp.getId()).equals(EmpIdCombo.getSelectedItem())).findFirst();
                if (selectedEmployee.isPresent()) {
                	Attendance attendance = new Attendance();
                	attendance = attendanceService.findAttendanceByEmpId(id);
                	
                	AllowanceDetails allowanceDetails = new AllowanceDetails();
                	allowanceDetails = allowanceService.findAllowanceDetailsByEmpId(id);
                	
                	DeductionDetails deductionDetails = new DeductionDetails();
                	deductionDetails = deductionService.findDeductionDetailsByEmpId(id);
                	
                	if (attendance.getId() == 0) {
                		JOptionPane.showMessageDialog(null, "Selected Employee doesn't have attendance records!", "Invalid", 0);
                		resetFormDate();
                		return;
                	}
                	if (allowanceDetails.getAdId() == 0) {
                		JOptionPane.showMessageDialog(null, "Selected Employee doesn't have Allowance records!", "Invalid", 0);  
                		resetFormDate();
                		return;
                	}
                	if (deductionDetails.getDeduction_details_id() == 0) {
                		JOptionPane.showMessageDialog(null, "Selected Employee doesn't have Deduction records!", "Invalid", 0); 
                		resetFormDate();
                		return;
                	}
                	
                	txtEmpName.setText(selectedEmployee.map(emp -> emp.getName()).orElse(""));
                	txtAttdId.setText(attendance.getId() + "");
                	txtPosition.setText(selectedEmployee.map(emp -> emp.getPosition().getTitle()).orElse(""));
                	txtDept.setText(selectedEmployee.map(emp -> emp.getDepartment().getDepartmentName()).orElse(""));
                	txtPresent.setText(attendance.getPresent());
                	txtAbsent.setText(attendance.getAbsent());
                	txtLate.setText(attendance.getHourLate());
                	txtOT.setText(attendance.getHourOT());
                	txtAllowance.setText(allowanceDetails.getAdId() + "");
                	txtDeduk.setText(deductionDetails.getDeduction_details_id() + "");
                	txtAllowanceAmount.setText(allowanceDetails.getAllowance_Amount());
                	txtDedukAmount.setText(deductionDetails.getDeduction_amount() + "");
                	txtBasicS.setText(selectedEmployee.map(emp -> String.valueOf(emp.getPosition().getBasicSalary())).orElse(""));
                	int index = 0;
                	String selectedMonth = attendance.getMonth();
                	for (int i = 0; i < months.length; i++) {
                		if (months[i].equals(selectedMonth)) {
                			index = i;
                		}
                	}               	
                	monthChooser.setMonth(index);
                	
                }
            }
        });
		
		
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
		
		monthChooser.setMonth(LocalDate.now().getMonthValue());
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
		
		txtAbsent = new JTextField();
		txtAbsent.setEditable(false);
		txtAbsent.setColumns(10);
		txtAbsent.setBounds(188, 327, 200, 30);
		panel.add(txtAbsent);
		
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
		
		txtDeduk = new JTextField();
		txtDeduk.setEditable(false);
		txtDeduk.setColumns(10);
		txtDeduk.setBounds(569, 81, 200, 30);
		panel.add(txtDeduk);
		
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
		lblGrossSalary.setBounds(435, 243, 124, 30);
		panel.add(lblGrossSalary);
		
		txtGrossS = new JTextField();
		txtGrossS.setEditable(false);
		txtGrossS.setColumns(10);
		txtGrossS.setBounds(569, 243, 200, 30);
		panel.add(txtGrossS);
		
		JLabel lblTotalDeduction = new JLabel("Total Deduction");
		lblTotalDeduction.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotalDeduction.setBounds(435, 202, 124, 30);
		panel.add(lblTotalDeduction);
		
		txtDedukAmount = new JTextField();
		txtDedukAmount.setEditable(false);
		txtDedukAmount.setColumns(10);
		txtDedukAmount.setBounds(569, 204, 200, 30);
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
		
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				List<Payroll> thePayroll = new ArrayList<>();
				thePayroll = payrollService.findAllPayrolls();
				attendanceIdRecords = thePayroll.stream().map(p -> String.valueOf(p.getAttendance().getId())).collect(Collectors.toList());
				
				Payroll payroll = new Payroll();
				setPayrollDataFromForm(payroll);
				Attendance attendance = new Attendance();
		    	attendance = attendanceService.findAttendanceByEmpId(payroll.getEmployee().getId() + "");
				String selectedMonth =months[monthChooser.getMonth()];
				if (!attendance.getMonth().equals(selectedMonth)) {
					JOptionPane.showMessageDialog(null, "Selected employee doesn't have attendance record for the selected month!", "Invalid", 0);
		    		resetFormDate();
		    		return;	
				}
				if (attendanceIdRecords.contains(String.valueOf(payroll.getAttendance().getId()))) {
		    		JOptionPane.showMessageDialog(null, "Selected employee has already registered for the month!", "Invalid", 0);
		    		resetFormDate();
		    		return;	
				}
		    	if (attendance.getMonth().equals(selectedMonth)) {
					payrollService.createPayroll(payroll);
					loadAllPayroll(Optional.empty());
					resetFormDate();
		    	} else {
		    		JOptionPane.showMessageDialog(null, "invalid!", "Invalid", 0);
		    		resetFormDate();
		    		return;
		    	}
				JOptionPane.showMessageDialog(null, "Payroll registered successfully!", "Success", 1);

			}
		});
		btnRegister.setEnabled(false);
		btnRegister.setBounds(435, 407, 103, 65);
		panel.add(btnRegister);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				double basic = Double.valueOf(selectedEmployee.map(emp -> String.valueOf(emp.getPosition().getBasicSalary())).orElse(""));
				double allowanceAmount = Double.valueOf(txtAllowanceAmount.getText());
				double deductionAmount = Double.valueOf(txtDedukAmount.getText());
				double grossSalary = basic + allowanceAmount;
				txtGrossS.setText(String.valueOf(grossSalary));
				double netSalary = grossSalary - deductionAmount;
				txtNetS.setText(String.valueOf(netSalary));
            	btnRegister.setEnabled(true);

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
		scrollPane.setBounds(51, 67, 963, 235);
		payrollList.add(scrollPane);

        tblPayroll.setFont(new Font("Tahoma", Font.PLAIN, 15));
        scrollPane.setViewportView(tblPayroll);
		
		
		
		textField = new JTextField("Search By Employee Name");
		textField.setForeground(Color.GRAY);
		textField.setColumns(10);
		textField.setBounds(51, 26, 200, 30);
		payrollList.add(textField);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(261, 26, 89, 30);
		payrollList.add(btnSearch);
		
		JButton btnslip = new JButton("Generate Pay Slip");
		btnslip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String empId = (String) EmpIdCombo.getSelectedItem();
				String empName = txtEmpName.getText();
				String empPosition = txtPosition.getText();
				String empDept = txtDept.getText();
				String month = months[monthChooser.getMonth()];
				String absent = txtAbsent.getText();
				String late = txtLate.getText();
				String overtime = txtOT.getText();
				String totalAllowance = txtAllowanceAmount.getText();
				String totalDeduction = txtDedukAmount.getText();
				String grossSalary = txtGrossS.getText();
				String basicSalary = txtBasicS.getText();
				String netSalary = txtNetS.getText();
				
				JFileChooser dialog = new JFileChooser();
				
				dialog.setSelectedFile(new File(empName + "-Salary Slip" +".pdf" ));
				int dialogResult = dialog.showSaveDialog(null);
				if (dialogResult == JFileChooser.APPROVE_OPTION) {
					String filePath = dialog.getSelectedFile().getPath();
					
					try {
						
	                	AllowanceDetails allowanceDetails = new AllowanceDetails();
	                	allowanceDetails = allowanceService.findAllowanceDetailsByEmpId(empId);
	                	
	                	DeductionDetails deductionDetails = new DeductionDetails();
	                	deductionDetails = deductionService.findDeductionDetailsByEmpId(empId);
	                	
	                	double basic = Double.valueOf(basicSalary);
	                	double otAllowance ;
	                	double overtimeHours = Double.valueOf(overtime);
	                	String skillFee = allowanceDetails.getSkills();
	                	String houseRantFee = allowanceDetails.getHouseRentAllowance();
	                	String transport = allowanceDetails.getTransportAllowance();
						double longevityAllowance;

	                	int longevityh = Integer.valueOf(allowanceDetails.getLongevity());
						
						if (longevityh < 2) {
							longevityAllowance = 0;
						} else {
							longevityAllowance = 0.1 * basic;
						}
	                	String tax = deductionDetails.getTax();
	                	String ssc = deductionDetails.getSSC();
	                	
	                	double skillsAllowance = Double.valueOf(skillFee);

						otAllowance = ((basic + skillsAllowance) / 160) * overtimeHours * 2;
						String overtimeAllowance = String.valueOf(otAllowance);
	                	
						
						double absentDays = Double.valueOf(absent);
						double lateHour = Double.valueOf(late);
						double hourlyPay = basic / 160;
						double lateDeduction = (hourlyPay * lateHour);
						double absentDeduction = hourlyPay * 8 * absentDays;
	                	
	                	Document myDoc = new Document();
						PdfWriter myWriter = PdfWriter.getInstance(myDoc, new FileOutputStream(filePath));
						
						myDoc.open();
						
						myDoc.add(new Paragraph("IMC PAY SLIP", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
						myDoc.add(new Paragraph("Salary for the month of " + month, FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD)));
						myDoc.add(new Paragraph(new Date().toString()));
						myDoc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------"));
						myDoc.add(new Paragraph("EMPLOYEE DETAILS", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
						myDoc.add(new Paragraph("Employee ID	: " + empId, FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
						myDoc.add(new Paragraph("Employee Name	: " + empName, FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
						myDoc.add(new Paragraph("Position	: " + empPosition, FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
						myDoc.add(new Paragraph("Department	: " + empDept, FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.PLAIN)));
						myDoc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------"));
						myDoc.add(new Paragraph("SALARY", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
						myDoc.add(new Paragraph("Basic Salary	: " + basic + " ¥", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
						myDoc.add(new Paragraph("Overtime pay	: " + overtimeAllowance + " ¥", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
						myDoc.add(new Paragraph("HRA	: " + houseRantFee + " ¥", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
						myDoc.add(new Paragraph("TA	: " + transport + " ¥", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));						
						myDoc.add(new Paragraph("Longevity allowance	: " + longevityAllowance + " ¥", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));						
						myDoc.add(new Paragraph("Total Allowance	: " + totalAllowance + " ¥", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.BOLD)));
						myDoc.add(new Paragraph("GROSS SALARY	: " + grossSalary + " ¥", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.BOLD)));
						myDoc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------"));
						myDoc.add(new Paragraph("DEDUCTION", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
						myDoc.add(new Paragraph("Income tax	: " + tax + " ¥", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
						myDoc.add(new Paragraph("SSC	: " + ssc + " ¥", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
						myDoc.add(new Paragraph("Late Deduction	: " + lateDeduction + " ¥", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
						myDoc.add(new Paragraph("Absent Deduction	: " + absentDeduction + " ¥", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));						
						myDoc.add(new Paragraph("Total Deduction	: " + totalDeduction + " ¥", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.BOLD)));
						myDoc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------"));
						myDoc.add(new Paragraph("TOTAL PAYMENT", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));							
						myDoc.add(new Paragraph("NET SALARY	: " + netSalary + " ¥", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.BOLD)));

						
						myDoc.close();
						JOptionPane.showMessageDialog(null, "Report was successfully generated");
						
					} catch(Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
				}

			}
		});
		btnslip.setEnabled(false);
		btnslip.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnslip.setBounds(458, 320, 150, 81);
		payrollList.add(btnslip);
		
        
        this.tblPayroll.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!tblPayroll.getSelectionModel().isSelectionEmpty()) {

                String payrollId = tblPayroll.getValueAt(tblPayroll.getSelectedRow(), 0).toString();

                payroll = payrollService.findPayrollById(payrollId);
                btnslip.setEnabled(true);
                EmpIdCombo.setSelectedIndex(payroll.getEmployee().getId());
                
                txtEmpName.setText(payroll.getEmployee().getName());
                
            	            	
                String id = String.valueOf(payroll.getEmployee().getId());                
                
                selectedEmployee = employeeList.stream()
                        .filter(emp -> String.valueOf(emp.getId()).equals(id)).findFirst();
                if (selectedEmployee.isPresent()) {
                	Attendance attendance = new Attendance();
                	attendance = attendanceService.findAttendanceByEmpId(id);
                	
                	AllowanceDetails allowanceDetails = new AllowanceDetails();
                	allowanceDetails = allowanceService.findAllowanceDetailsByEmpId(id);
                	
                	DeductionDetails deductionDetails = new DeductionDetails();
                	deductionDetails = deductionService.findDeductionDetailsByEmpId(id);
                	txtEmpName.setText(selectedEmployee.map(emp -> emp.getName()).orElse(""));
                	txtAttdId.setText(attendance.getId() + "");
                	txtPosition.setText(selectedEmployee.map(emp -> emp.getPosition().getTitle()).orElse(""));
                	txtDept.setText(selectedEmployee.map(emp -> emp.getDepartment().getDepartmentName()).orElse(""));
                	txtPresent.setText(attendance.getPresent());
                	txtAbsent.setText(attendance.getAbsent());
                	txtLate.setText(attendance.getHourLate());
                	txtOT.setText(attendance.getHourOT());
                	txtAllowance.setText(allowanceDetails.getAdId() + "");
                	txtDeduk.setText(deductionDetails.getDeduction_details_id() + "");
                	txtAllowanceAmount.setText(allowanceDetails.getAllowance_Amount());
                	txtDedukAmount.setText(deductionDetails.getDeduction_amount() + "");
                	txtBasicS.setText(selectedEmployee.map(emp -> String.valueOf(emp.getPosition().getBasicSalary())).orElse(""));
                	txtGrossS.setText(payroll.getGrossSalary());
                	txtNetS.setText(payroll.getNetSalary());
                	int index = 0;
                	String selectedMonth = attendance.getMonth();
                	for (int i = 0; i < months.length; i++) {
                		if (months[i].equals(selectedMonth)) {
                			index = i;
                		}
                	}               	
                	monthChooser.setMonth(index);
                }
                             
            }
        });
		
	}
}
