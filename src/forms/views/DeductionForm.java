package forms.views;
import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

import entities.Employee;
import services.EmployeeService;
import entities.AllowanceDetails;
import entities.Attendance;
import entities.DeductionDetails;
import services.AttendanceService;
import services.DeductionService;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class DeductionForm extends JInternalFrame {
	
	private JTextField txtTax;
	private JTextField txtSSC;
	private JTextField txtdAmount;
	private DeductionService deductionService;
	private EmployeeService employeeService;
	private JTable tblDeduction;
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<DeductionDetails> originalDeductionList = new ArrayList<>();
	private List<DeductionDetails> dDetailslist=new ArrayList();
	private JTextField txtSearchName;
	private DeductionDetails deductionDetails;
    private List<Employee> employeeList = new ArrayList<>();
    private JTextField txtDescription;
    private Employee employee;
    private JTextField txtEmpName;
    private JTextField txtLate;
    private Attendance attendance;
    private AttendanceService attendanceService;
    private JTextField txtAbsent;
	List<String> attendanceIdRecords = new ArrayList<>();
	JComboBox<String> comboBoAttendance = new JComboBox<String>();
	List<Attendance> attdList = new ArrayList<>();
	JComboBox<String> EmpIdCombo = new JComboBox<String>();
    private Optional<Employee> selectedEmployee;
    private Optional<Attendance> selectedAttendance;
    private JTextField txtMonth;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeductionForm frame = new DeductionForm();
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
	
	private void loadAttendanceForComboBox(String id) {
		this.comboBoAttendance.removeAllItems();
        this.comboBoAttendance.addItem("- Select -");
        this.attdList = this.attendanceService.findAllAttendances();
        List<Attendance> newAttdList = new ArrayList<>();
        newAttdList = this.attdList.stream().filter(a -> String.valueOf(a.getEmployee().getId()).equals(id)).collect(Collectors.toList());

        
        newAttdList.forEach(e -> this.comboBoAttendance.addItem(String.valueOf(e.getId())));
    }

	private void loadEmployeeForComboBox() {
        this.EmpIdCombo.addItem("- Select -");
        this.employeeList = this.employeeService.findAllEmployees();
        this.employeeList.forEach(e -> this.EmpIdCombo.addItem(String.valueOf(e.getId())));
    }
	
	public DeductionForm() {
		initialize();
		initializeDependency();
		loadEmployeeForComboBox();
		this.setTableDesign();
		this.loadAllDeductionDetails(Optional.empty());
    	this.deductionDetails = new DeductionDetails();

	}
	
	public DeductionForm(DeductionDetails deductionDetails) {
    	this.deductionDetails = deductionDetails;
    	initialize();
        this.initializeDependency();
        this.setTableDesign();
        this.loadAllDeductionDetails(Optional.empty());     
    }
	
	private void initializeDependency() {
        this.deductionService = new DeductionService();
        this.employeeService = new EmployeeService();
        this.attendanceService = new AttendanceService();

    }
	
	 private void setTableDesign() {
	       	dtm.addColumn("ID");
	        dtm.addColumn("Tax");
	        dtm.addColumn("SSC");
	        dtm.addColumn("Amount");
	        this.tblDeduction.setModel(dtm);
	  }
	 
	 private void resetFormData() {	
	        txtTax.setText("");
	        txtSSC.setText("");
	        txtdAmount.setText("");
	        txtDescription.setText("");
	        txtAbsent.setText("");
	        txtEmpName.setText("");
	        txtLate.setText("");
	        EmpIdCombo.setSelectedIndex(0);
	        comboBoAttendance.setSelectedIndex(0);
	        txtMonth.setText("");
	  }
	 
	 private void setDeductionDetails(DeductionDetails deductionDetails) {
		 String id = EmpIdCombo.getSelectedItem() + "";
		 Employee newEmployee = new Employee();
		 newEmployee = employeeService.findEmployeeById(id);
		 
		 Attendance newAttendance = new Attendance();
		 
		 String attd_id = (String) comboBoAttendance.getSelectedItem();		
		 newAttendance = attendanceService.findAttendanceById(attd_id);
		 
		 deductionDetails.setTax(txtTax.getText());
		 deductionDetails.setSSC(txtSSC.getText());
		 deductionDetails.setDeduction_amount(Double.valueOf(txtdAmount.getText()));
		 deductionDetails.setDescription(txtDescription.getText());
		 deductionDetails.setEmployee(newEmployee);
		 deductionDetails.setAttendance(newAttendance);
	 }
	 

	 
	 private void loadAllDeductionDetails(Optional<List<DeductionDetails>> optionalDeductionDetails) {
	    	this.dtm = (DefaultTableModel) this.tblDeduction.getModel();
	    	this.dtm.getDataVector().removeAllElements();
	    	this.dtm.fireTableDataChanged();
	    	this.originalDeductionList = this.deductionService.findAllDDetails();
	    		List<DeductionDetails> dDetailsList = optionalDeductionDetails.orElseGet(() ->  originalDeductionList);
	    		dDetailsList.forEach(d -> {
	    			Object[] row = new Object[7];
	    				row[0] = d.getDeduction_details_id();
	    				row[1] = d.getTax();
	    				row[2] = d.getSSC();
	    				row[3] = d.getDeduction_amount();
	    				row[4] = d.getDescription();
	    				dtm.addRow(row);
	    			});
	    		this.tblDeduction.setModel(dtm);
	    }
	 
	public void initialize() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Deduction", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(48, 11, 428, 487);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tax");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(45, 224, 46, 14);
		panel.add(lblNewLabel);
		
		txtTax = new JTextField();
		txtTax.setEditable(false);
		txtTax.setBounds(169, 217, 193, 28);
		panel.add(txtTax);
		txtTax.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Emp ID");
		lblNewLabel_1.setBounds(45, 27, 75, 17);
		panel.add(lblNewLabel_1);
		
		txtDescription = new JTextField();
		txtDescription.setBounds(169, 334, 193, 28);
		panel.add(txtDescription);
		txtDescription.setColumns(10);
		
		txtEmpName = new JTextField();
		txtEmpName.setEditable(false);
		txtEmpName.setColumns(10);
		txtEmpName.setBounds(169, 139, 193, 28);
		panel.add(txtEmpName);
		
		JLabel lblNewLabel_4_1 = new JLabel("late (hour)");
		lblNewLabel_4_1.setBounds(45, 185, 59, 14);
		panel.add(lblNewLabel_4_1);
		
		txtLate = new JTextField();
		txtLate.setEditable(false);
		txtLate.setColumns(10);
		txtLate.setBounds(169, 178, 193, 28);
		panel.add(txtLate);
		
	
		
		this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		BasicInternalFrameUI ui= (BasicInternalFrameUI)this.getUI();
		ui.setNorthPane(null);
		
		
		setBounds(0, 0, 1065, 588);
		
		JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(516, 51, 497, 413);
        this.getContentPane().add(scrollPane);

        tblDeduction = new JTable();
        tblDeduction.setFont(new Font("Tahoma", Font.PLAIN, 15));
        scrollPane.setViewportView(tblDeduction);
        
        
        
        txtSearchName = new JTextField();
        txtSearchName.setBounds(742, 11, 172, 29);
        getContentPane().add(txtSearchName);
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
		
        txtSearchName.setColumns(10);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String keyword=txtSearchName.getText();
				loadAllDeductionDetails(
						Optional.of(originalDeductionList.stream()
								.filter(deduk -> deduk.getEmployee().getName().toLowerCase()
								.startsWith(keyword.toLowerCase()))
								.collect(Collectors.toList()))
								);
				if(txtSearchName.getText().equals("Search By Employee Name")) {
					loadAllDeductionDetails(Optional.empty());
				}
        	}
        });
        btnSearch.setBounds(924, 11, 89, 32);
        getContentPane().add(btnSearch);
		
		JLabel lblNewLabel_2 = new JLabel("SSC");
		lblNewLabel_2.setBounds(45, 263, 58, 14);
		panel.add(lblNewLabel_2);
		
		txtSSC = new JTextField();
		txtSSC.setEditable(false);
		txtSSC.setColumns(10);
		txtSSC.setBounds(169, 256, 193, 28);
		panel.add(txtSSC);
		
		JLabel lblNewLabel_3 = new JLabel("Total Amount");
		lblNewLabel_3.setBounds(45, 375, 100, 25);
		panel.add(lblNewLabel_3);
		
		txtdAmount = new JTextField();
		txtdAmount.setEditable(false);
		txtdAmount.setColumns(10);
		txtdAmount.setBounds(169, 373, 193, 28);
		panel.add(txtdAmount);
		
		JButton btnSave = new JButton("Register");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeductionDetails deductionDetails = new DeductionDetails();
				setDeductionDetails(deductionDetails);
				
				
				
				List<DeductionDetails> ddList = new ArrayList<>();
				ddList = deductionService.findAllDDetails();
				List<String> ddIdList = new ArrayList<>();

				ddList = ddList.stream().filter( a -> String.valueOf(a.getEmployee().getId()).equals(deductionDetails.getEmployee().getId() + "")).collect(Collectors.toList());
				ddIdList = ddList.stream().map(a -> a.getAttendance().getId() + "").collect(Collectors.toList());
				
				
				
				if(!txtTax.getText().isEmpty() && !txtSSC.getText().isEmpty() && !txtdAmount.getText().isEmpty()) {


					if (ddIdList.contains(String.valueOf(deductionDetails.getAttendance().getId()))) {
			    		JOptionPane.showMessageDialog(null, "Selected employee's Deduction has already registered for the month!", "Invalid", 0);
						resetFormData();
			    		return;	
					} else {
						deductionService.createDeductionDetails(deductionDetails);
						loadAllDeductionDetails(Optional.empty());
						resetFormData();
						JOptionPane.showMessageDialog(null, "Successfully registered!", "Success", 1);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Enter required fields!", "Invalid fields", 0);
				}
			}
		});
		btnSave.setBounds(45, 421, 74, 42);
		panel.add(btnSave);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtTax.getText().isEmpty() && !txtSSC.getText().isEmpty()) {
					double total;
					double lateHour;
					double basic;
					double absentDays;
					double ssc;
					double lateDeduction;
					double hourlyPay;
					double absentDeduction;
					double tax;
					basic = employee.getPosition().getBasicSalary();
					ssc = Double.valueOf(txtSSC.getText());
					tax = Double.valueOf(txtTax.getText());
					
					absentDays = Double.valueOf(txtAbsent.getText());
					lateHour = Double.valueOf(txtLate.getText());
					hourlyPay = basic / 160;
					lateDeduction = (hourlyPay * lateHour);
					absentDeduction = hourlyPay * 8 * absentDays;
					
					total = lateDeduction + absentDeduction + ssc + tax;
					txtdAmount.setText(String.valueOf(total));
					
				} else {
					JOptionPane.showMessageDialog(null, "Enter required fields to calculate deduction!", "Invalid fields", 0);
				}
			}
		});
		btnCalculate.setBounds(127, 421, 85, 42);
		panel.add(btnCalculate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFormData();
			}
		});
		btnClear.setBounds(222, 421, 74, 42);
		panel.add(btnClear);
		
		JLabel lblNewLabel_4 = new JLabel("Emp Name");
		lblNewLabel_4.setBounds(45, 146, 59, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Description");
		lblNewLabel_5.setBounds(45, 341, 75, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_4_2 = new JLabel("Absent (Day)");
		lblNewLabel_4_2.setBounds(45, 296, 100, 26);
		panel.add(lblNewLabel_4_2);
		
		txtAbsent = new JTextField();
		txtAbsent.setEditable(false);
		txtAbsent.setColumns(10);
		txtAbsent.setBounds(169, 295, 193, 28);
		panel.add(txtAbsent);
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtTax.getText().isEmpty() && !txtSSC.getText().isEmpty() && !txtdAmount.getText().isEmpty()) {
					deductionService.deleteDeduction(String.valueOf(deductionDetails.getDeduction_details_id()));
			        loadAllDeductionDetails(Optional.empty());     
			        resetFormData();
				}
			}
		});
		btnDelete.setBounds(306, 421, 74, 42);
		panel.add(btnDelete);
		
		JLabel lblMonth = new JLabel("Month");
		lblMonth.setBounds(45, 106, 75, 17);
		panel.add(lblMonth);
		
		EmpIdCombo.setBounds(169, 21, 193, 28);
		panel.add(EmpIdCombo);
		EmpIdCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String emp_id = (String) EmpIdCombo.getSelectedItem();
                selectedEmployee = employeeList.stream()
                        .filter(emp -> String.valueOf(emp.getId()).equals(EmpIdCombo.getSelectedItem())).findFirst();
                if (selectedEmployee.isPresent()) {
                	
                	Employee employee = new Employee();
                	employee = employeeService.findEmployeeById(emp_id);
                	
                	Attendance attendance = new Attendance();
                	attendance = attendanceService.findAttendanceByEmpId(emp_id);
                	
                	try {
                		if (attendance.getId() == 0) {
                    		JOptionPane.showMessageDialog(null, "Selected Employee doesn't have attendance records!", "Invalid", 0);
                    		resetFormData();
                    		return;
                    	}	
                	} catch (Exception e2) {
                		
                	}
                	
                	txtEmpName.setText(employee.getName());            	
                	loadAttendanceForComboBox(emp_id);           	              	
                }
            }
        });
		
		
		JLabel lblAttd = new JLabel("Attendance ID");
		lblAttd.setBounds(45, 66, 75, 17);
		panel.add(lblAttd);
		
		comboBoAttendance.setBounds(169, 60, 193, 28);
		panel.add(comboBoAttendance);
		
		txtMonth = new JTextField();
		txtMonth.setEditable(false);
		txtMonth.setColumns(10);
		txtMonth.setBounds(169, 100, 193, 28);
		panel.add(txtMonth);
		comboBoAttendance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String attd_id = (String) comboBoAttendance.getSelectedItem();
				selectedAttendance = attdList.stream().filter(a -> String.valueOf(a.getId()).equals(comboBoAttendance.getSelectedItem())).findFirst();
				if (selectedAttendance.isPresent()) {            	             	                   				 
                	Attendance attendance = new Attendance();
                	attendance = attendanceService.findAttendanceById(attd_id);
					String id = EmpIdCombo.getSelectedItem() + "";
					employee = new Employee();
					employee = employeeService.findEmployeeById(id);
					txtEmpName.setText(employee.getName());
					
					txtLate.setText(attendance.getHourLate());
					
					double tax = 0;
					double basic;
					double absentDays;
					double ssc;
					
					basic = employee.getPosition().getBasicSalary();
					
					// income tax
					if (basic < (1960000 / 12)) {
						tax = 0.05 * basic;
					} else if (basic > (1960000 / 12)) {
						tax = 0.1 * basic;
					} else if (basic > (3300000 / 12)) {
						tax = 0.2 * basic;
					} else if (basic > (9000000 / 12)) {
						tax = 0.33 * basic;					
					} else if (basic > (18000000 / 12)) {
						tax = 0.4 * basic;
					} else if (basic > (40000000 / 12)) {
						tax = 0.45 * basic;
					}
					
					absentDays = Integer.valueOf(attendance.getAbsent());
					ssc = basic * 0.05;
					txtTax.setText(String.valueOf(tax));
					txtSSC.setText(String.valueOf(ssc));
					txtAbsent.setText(String.valueOf(absentDays));
   				 	txtMonth.setText(attendance.getMonth());

				}
			}
		});
		
		
		
		this.tblDeduction.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!tblDeduction.getSelectionModel().isSelectionEmpty()) {

					String id = tblDeduction.getValueAt(tblDeduction.getSelectedRow(), 0).toString();

					deductionDetails = deductionService.findDeductionDetailsById(id);

					EmpIdCombo.setSelectedIndex(deductionDetails.getEmployee().getId());
					
					Attendance attendance = new Attendance();
                	attendance = attendanceService.findAttendanceById(deductionDetails.getAttendance().getId() + "");
                	int index = 0;
                	for (int i = 0; i < comboBoAttendance.getItemCount(); i++) {
                		if (comboBoAttendance.getItemAt(i).equals(attendance.getId() + "")) {
                			index = i;
                		}
                	}
                	
                	comboBoAttendance.setSelectedIndex(index);
					
					
					txtEmpName.setText(deductionDetails.getEmployee().getName());
					txtLate.setText(deductionDetails.getAttendance().getHourLate());
					txtAbsent.setText(deductionDetails.getAttendance().getAbsent());
					txtTax.setText(deductionDetails.getTax());
					txtSSC.setText(deductionDetails.getSSC());
					txtDescription.setText(deductionDetails.getDescription());
					txtdAmount.setText(String.valueOf(deductionDetails.getDeduction_amount()));
					txtMonth.setText(deductionDetails.getAttendance().getMonth());

				}
			}
		});
	}
}