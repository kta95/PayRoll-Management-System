package forms.views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

import entities.AllowanceDetails;
import entities.Attendance;
import entities.Employee;
import services.AllowanceService;
import services.AttendanceService;
import services.EmployeeService;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JMonthChooser;

public class AllowanceForm extends JInternalFrame {
	private JTextField txtEmpID;
	private JTextField txtSkills;
	private JTextField txtLong;
	private JTextField txtDescription;
	private JTextField txtAmount;
	private AllowanceDetails allowanceDetails;
	private EmployeeService employeeService;
	private AttendanceService attendanceService;
	private AllowanceService allowanceService;
	private Employee employee;
	private Attendance attendance;
	private JTable tblAllowance;
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<AllowanceDetails> originalAllowanceList = new ArrayList<>();
	private List<AllowanceDetails> aDetailslist=new ArrayList();
	private JTextField txtEmp;
	private JTextField txtHRA;
	private JTextField txtTA;
	private JTextField txtOT;
	private JTextField txtSearchName;
	List<String> attendanceIdRecords = new ArrayList<>();
	String[] months = new String[12];
	JComboBox<String> comboBoAttendance = new JComboBox<String>();
	List<Attendance> attdList = new ArrayList<>();
	private JTextField txtMonth;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllowanceForm frame = new AllowanceForm();
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
	public AllowanceForm() {
		initialize();
		initializeDependency();
		this.setTableDesign();
		this.loadAllowanceDetails(Optional.empty());
    	this.allowanceDetails = new AllowanceDetails();
		this.months = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

	}
	
	public AllowanceForm(AllowanceDetails allowanceDetails) {
		this.allowanceDetails = allowanceDetails;
    	initialize();
        this.initializeDependency();
        this.setTableDesign();
        this.loadAllowanceDetails(Optional.empty());     
		
    }
	
	private void initializeDependency() {
        this.allowanceService = new AllowanceService();
        this.employeeService = new EmployeeService();
        this.attendanceService = new AttendanceService();
    }
	
	private void setTableDesign() {
       	dtm.addColumn("ID");
       	dtm.addColumn("Name");
        dtm.addColumn("Skills allowance");
        dtm.addColumn("Longevity (years)");
       	dtm.addColumn("HRA");
       	dtm.addColumn("TA");

        dtm.addColumn("Total");
        this.tblAllowance.setModel(dtm);
  }
	
	private void resetFormData() {	
        txtSkills.setText("");
        txtLong.setText("");
        txtDescription.setText("");
        txtAmount.setText("");
        txtEmpID.setText("");
        txtHRA.setText("");
        txtOT.setText("");
        txtTA.setText("");
        txtEmp.setText("");
  }
	
	private void loadAttendanceForComboBox() {
        this.comboBoAttendance.addItem("- Select -");
        this.attdList = this.attendanceService.findAllAttendances();
        this.attdList.forEach(e -> this.comboBoAttendance.addItem(String.valueOf(e.getId())));
    }

	
	private void setAllowanceDetails(AllowanceDetails allowanceDetails) {
		 String id = txtEmpID.getText();
		 Employee newEmployee = new Employee();
		 newEmployee = employeeService.findEmployeeById(id);
		 
		 attendance = new Attendance();
		//	 attendance = attendanceService.findAttendanceByEmpId(id);
		 
		 List<Attendance> attdList = new ArrayList<>(); 
		 attdList = attendanceService.findAllAttendances();
		
		 
		 
		 allowanceDetails.setSkills(txtSkills.getText());;
		 allowanceDetails.setLongevity(txtLong.getText());
		 allowanceDetails.setAllowance_Amount(txtAmount.getText());
		 allowanceDetails.setDescription(txtDescription.getText());
		 allowanceDetails.setEmployee(newEmployee);
		 allowanceDetails.setAttendance(attendance);
		 allowanceDetails.setHouseRentAllowance(txtHRA.getText());
		 allowanceDetails.setTransportAllowance(txtTA.getText());
	}
	 

 
 private void loadAllowanceDetails(Optional<List<AllowanceDetails>> optionalAllowanceDetails) {
    	this.dtm = (DefaultTableModel) this.tblAllowance.getModel();
    	this.dtm.getDataVector().removeAllElements();
    	this.dtm.fireTableDataChanged();
    	this.originalAllowanceList = this.allowanceService.findAllADetails();
    		List<AllowanceDetails> aDetailsList = optionalAllowanceDetails.orElseGet(() ->  originalAllowanceList);
    		aDetailsList.forEach(a -> {
    			Object[] row = new Object[8];
    				row[0] = a.getAdId();
    				row[1] = a.getEmployee().getName();
    				row[2] = a.getSkills();
    				row[3] = a.getLongevity();
    				row[4] = a.getHouseRentAllowance();
    				row[5] = a.getTransportAllowance();
    				row[6] = a.getAllowance_Amount();
    				dtm.addRow(row);
    			});
    		this.tblAllowance.setModel(dtm);
    }
 
	
	public void initialize() {
		getContentPane().setBackground(Color.WHITE);
		
		this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		BasicInternalFrameUI ui= (BasicInternalFrameUI)this.getUI();
		ui.setNorthPane(null);
		
		
		setBounds(0, 0, 1065, 588);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "Allowance", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(50, 53, 405, 497);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Emp ID");
		lblNewLabel.setBounds(37, 37, 46, 14);
		panel.add(lblNewLabel);
		
		txtEmpID = new JTextField();
		txtEmpID.setText("");
		txtEmpID.setBounds(173, 34, 86, 20);
		panel.add(txtEmpID);
		txtEmpID.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Total Amount");
		lblNewLabel_5.setBounds(37, 387, 89, 14);
		panel.add(lblNewLabel_5);
		
		txtAmount = new JTextField();
		txtAmount.setEditable(false);
		txtAmount.setBounds(173, 384, 185, 20);
		panel.add(txtAmount);
		txtAmount.setColumns(10);
		
		txtEmp = new JTextField();
		txtEmp.setEditable(false);
		txtEmp.setBounds(173, 75, 185, 20);
		panel.add(txtEmp);
		txtEmp.setColumns(10);
		
		JLabel lblHRA = new JLabel("HRA");
		lblHRA.setBounds(37, 295, 60, 20);
		panel.add(lblHRA);
		
		txtHRA = new JTextField();
		txtHRA.setColumns(10);
		txtHRA.setBounds(173, 295, 185, 20);
		panel.add(txtHRA);
		
		JLabel lblTA = new JLabel("TA");
		lblTA.setBounds(37, 326, 60, 20);
		panel.add(lblTA);
		
		txtTA = new JTextField();
		txtTA.setColumns(10);
		txtTA.setBounds(173, 326, 185, 20);
		panel.add(txtTA);
		
		JLabel lblNewLabel_3_1 = new JLabel("OverTime (hour)");
		lblNewLabel_3_1.setBounds(37, 174, 115, 14);
		panel.add(lblNewLabel_3_1);
		
		txtOT = new JTextField();
		txtOT.setEditable(false);
		txtOT.setColumns(10);
		txtOT.setBounds(173, 171, 185, 20);
		panel.add(txtOT);
		
		JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(477, 64, 534, 413);
        this.getContentPane().add(scrollPane);

        tblAllowance = new JTable();
        tblAllowance.setFont(new Font("Tahoma", Font.PLAIN, 15));
        scrollPane.setViewportView(tblAllowance);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<AllowanceDetails> aList = new ArrayList<>();
				aList= allowanceService.findAllADetails();
				String id = txtEmpID.getText();
				employee = new Employee();
				employee = employeeService.findEmployeeById(id);
				EmployeeForm emp=new EmployeeForm();
				List<Employee> empList= employeeService.findAllEmployees();
				
				 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				 LocalDateTime now = LocalDateTime.now();
				 
				 
				 LocalDate dateHired = LocalDate.parse(employee.getHiredDate());
				 LocalDate dateNow = LocalDate.parse(dtf.format(now) + "");
				 Period period = dateHired.until(dateNow);
				 int yearsBetween = period.getYears();
				 
				 txtLong.setText(yearsBetween + "");
				
				
				if(emp.duplicate(Integer.parseInt(id), empList)) {
					txtEmp.setText(employee.getName());
				}
				else {
					JOptionPane.showMessageDialog(null, "ID doesn't exist !","Error",0);
					txtEmpID.setText("");
					txtEmpID.requestFocus();
					return;
				}
				attendance = new Attendance();
				attendance = attendanceService.findAttendanceByEmpId(id);
				txtOT.setText(attendance.getHourOT());
			}
		});
		btnSearch.setBounds(269, 33, 89, 23);
		panel.add(btnSearch);
		
		JLabel lblNewLabel_1 = new JLabel("academic certification");
		lblNewLabel_1.setBounds(37, 236, 126, 14);
		panel.add(lblNewLabel_1);
		
		txtSkills = new JTextField();
		txtSkills.setBounds(173, 233, 185, 20);
		panel.add(txtSkills);
		txtSkills.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Longevity (Year)");
		lblNewLabel_2.setBounds(37, 205, 115, 14);
		panel.add(lblNewLabel_2);
		
		txtLong = new JTextField();
		txtLong.setEditable(false);
		txtLong.setBounds(173, 202, 185, 20);
		panel.add(txtLong);
		txtLong.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Emp name");
		lblNewLabel_3.setBounds(37, 78, 60, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Description");
		lblNewLabel_4.setBounds(37, 261, 60, 20);
		panel.add(lblNewLabel_4);
		
		txtDescription = new JTextField();
		txtDescription.setBounds(173, 264, 185, 20);
		panel.add(txtDescription);
		txtDescription.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AllowanceDetails allowanceDetails = new AllowanceDetails();
				setAllowanceDetails(allowanceDetails);
				
				AllowanceDetails newAllowanceDetails = new AllowanceDetails();
				newAllowanceDetails = allowanceService.findAllowanceDetailsByEmpId(txtEmpID.getText());
				
				

				if(!txtSkills.getText().isEmpty()&&!txtLong.getText().isEmpty()&&!txtAmount.getText().isEmpty()) {
					if(txtSkills.getText().trim().matches("[0-9]+")
							&& txtLong.getText().trim().matches("[0-9]+") 
							&& txtHRA.getText().trim().matches("[0-9]+")
							&& txtTA.getText().trim().matches("[0-9]+")) {
						
//						if (newAllowanceDetails.getAttendance().getMonth().equals(months[monthChooser.getMonth()])) {
//				    		JOptionPane.showMessageDialog(null, "Selected employee's allowance has already registered for the month!", "Invalid", 0);
//							resetFormData();
//				    		return;	
//						}
						allowanceService.createAllowanceDetails(allowanceDetails);
						loadAllowanceDetails(Optional.empty());
						resetFormData();
						JOptionPane.showMessageDialog(null, "Successfully registered!", "Success", 1);
						txtEmpID.requestFocus();
						
						}
						else if(!txtSkills.getText().trim().matches("[0-9]+")) {
							JOptionPane.showMessageDialog(null, "Enter digits only !", "Error in academic", 0);
							return;
						}
						else if(! txtLong.getText().trim().matches("[0-9]+")) {
							JOptionPane.showMessageDialog(null, "Enter digits only !", "Error in Longevity", 0);
							return;
						}
						else if(!txtHRA.getText().trim().matches("[0-9]+")) {
							JOptionPane.showMessageDialog(null, "Enter digits only !", "Error in HRA", 0);
							return;
						}
						else {
							JOptionPane.showMessageDialog(null, "Enter digits only !", "Error in TA", 0);
							return;
						}
				} else {
					JOptionPane.showMessageDialog(null, "Enter required Fields", "Invalid field values", 0);
				}
			}
		});
		btnRegister.setBounds(10, 427, 80, 39);
		panel.add(btnRegister);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFormData();
				txtEmpID.requestFocus();

			}
		});
		btnClear.setBounds(200, 427, 80, 39);
		panel.add(btnClear);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtEmpID.getText();
				Employee theEmployee = new Employee();
				theEmployee = employeeService.findEmployeeById(id);
				if(!txtSkills.getText().isEmpty()&&!txtLong.getText().isEmpty()) {
					if(txtSkills.getText().trim().matches("[0-9]+")
							&& txtLong.getText().trim().matches("[0-9]+") 
							&& txtHRA.getText().trim().matches("[0-9]+")
							&& txtTA.getText().trim().matches("[0-9]+") ) {
							double total;
							double skillsAllowance;
							double otAllowance;
							double overtimeHours;
							double houseRent;
							double transportFee;
							int longevity;
							double longevityAllowance;
							double basic;
							double otherAllowance;
							
							skillsAllowance = Double.valueOf(txtSkills.getText().trim());
							overtimeHours = Double.valueOf(txtOT.getText().trim());
							houseRent = Double.valueOf(txtHRA.getText().trim());
							transportFee = Double.valueOf(txtTA.getText().trim());
							longevity = Integer.valueOf(txtLong.getText().trim());
							basic = theEmployee.getPosition().getBasicSalary();
							
							if (longevity < 2) {
								longevityAllowance = 0;
							} else {
								longevityAllowance = 0.1 * basic;
							}
							otAllowance = ((basic + skillsAllowance) / 160) * overtimeHours * 2;
							otherAllowance = houseRent + transportFee + longevityAllowance +skillsAllowance;
							
							total = otherAllowance + otAllowance;
							txtAmount.setText(String.valueOf(total));
							btnRegister.setEnabled(true);
						}
						else if(!txtSkills.getText().trim().matches("[0-9]+")) {
							JOptionPane.showMessageDialog(null, "Enter digits only !", "Error in academic", 0);
							return;
						}
						else if(! txtLong.getText().trim().matches("[0-9]+")) {
							JOptionPane.showMessageDialog(null, "Enter digits only !", "Error in Longevity", 0);
							return;
						}
						else if(!txtHRA.getText().trim().matches("[0-9]+")) {
							JOptionPane.showMessageDialog(null, "Enter digits only !", "Error in HRA", 0);
							return;
						}
						else {
							JOptionPane.showMessageDialog(null, "Enter digits only !", "Error in TA", 0);
							return;
						}
				} else {
					JOptionPane.showMessageDialog(null, "Enter required Fields to Calculate the Amount!", "Invalid field values", 0);
				}
				
			}
		});
		btnCalculate.setBounds(100, 427, 80, 39);
		panel.add(btnCalculate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtSkills.getText().isEmpty()&&!txtLong.getText().isEmpty()&&!txtAmount.getText().isEmpty()) {
					allowanceService.deleteAllowance(String.valueOf(allowanceDetails.getAdId()));
					loadAllowanceDetails(Optional.empty());
					resetFormData();
					txtEmpID.requestFocus();
				}
			}
		});
		btnDelete.setBounds(290, 427, 80, 39);
		panel.add(btnDelete);
		
		JLabel lblMonth = new JLabel("Attendance ID");
		lblMonth.setBounds(37, 110, 89, 14);
		panel.add(lblMonth);
		
		comboBoAttendance.setBounds(173, 106, 185, 23);
		panel.add(comboBoAttendance);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Month");
		lblNewLabel_3_1_1.setBounds(37, 148, 115, 14);
		panel.add(lblNewLabel_3_1_1);
		
		txtMonth = new JTextField();
		txtMonth.setEditable(false);
		txtMonth.setColumns(10);
		txtMonth.setBounds(173, 140, 185, 20);
		panel.add(txtMonth);
		
		txtSearchName = new JTextField("Search By Employee Name");
		txtSearchName.setColumns(10);
		txtSearchName.setBounds(761, 18, 165, 32);
		txtSearchName.setForeground(Color.GRAY);
		txtSearchName.addFocusListener(new FocusListener() {
	            @Override
	            public void focusGained(FocusEvent e) {
	                if (txtSearchName.getText().equals("Search By Employee Name")) {
	                	txtSearchName.setText("");
	                	txtSearchName.setForeground(Color.BLACK);
	                }
	            }
	            @Override
	            public void focusLost(FocusEvent e) {
	                if (txtSearchName.getText().isEmpty()) {
	                	txtSearchName.setForeground(Color.GRAY);
	                	txtSearchName.setText("Search By Employee Name");
	                }
	            }
	     });
		
		
		getContentPane().add(txtSearchName);
		
		JButton btnSearchName = new JButton("Search");
		btnSearchName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		String keyword=txtSearchName.getText();
				loadAllowanceDetails(
						Optional.of(originalAllowanceList.stream()
								.filter(allowance -> allowance.getEmployee().getName().toLowerCase()
								.startsWith(keyword.toLowerCase()))
								.collect(Collectors.toList()))
								);
				if(txtSearchName.getText().equals("Search By Employee Name")) {
					loadAllowanceDetails(Optional.empty());
				}
			}
		});
		btnSearchName.setBounds(936, 18, 75, 32);
		getContentPane().add(btnSearchName);
		
		this.tblAllowance.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!tblAllowance.getSelectionModel().isSelectionEmpty()) {

					String id = tblAllowance.getValueAt(tblAllowance.getSelectedRow(), 0).toString();

					allowanceDetails = allowanceService.findAllowanceDetailsById(id);

					txtEmpID.setText("" + allowanceDetails.getEmployee().getId());
					txtEmp.setText("" + allowanceDetails.getEmployee().getName());
					txtSkills.setText(allowanceDetails.getSkills());
					txtLong.setText(allowanceDetails.getLongevity());
					txtOT.setText(allowanceDetails.getAttendance().getHourOT());
					txtDescription.setText(allowanceDetails.getDescription());
					txtHRA.setText(allowanceDetails.getHouseRentAllowance());
					txtTA.setText(allowanceDetails.getTransportAllowance());
					txtAmount.setText(allowanceDetails.getAllowance_Amount());
				}
			}
		});
	}
}