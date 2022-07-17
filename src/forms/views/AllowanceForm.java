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
import java.awt.event.ActionEvent;

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
 
 private void setAllowanceDetails(AllowanceDetails allowanceDetails) {
	 String id = txtEmpID.getText();
	 Employee newEmployee = new Employee();
	 newEmployee = employeeService.findEmployeeById(id);
	 
	 attendance = new Attendance();
	 attendance = attendanceService.findAttendanceByEmpId(id);
		
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
		
		
		setBounds(0, 0, 976, 591);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "Allowance", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(62, 27, 405, 460);
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
		lblNewLabel_5.setBounds(37, 371, 89, 14);
		panel.add(lblNewLabel_5);
		
		txtAmount = new JTextField();
		txtAmount.setEditable(false);
		txtAmount.setBounds(173, 368, 185, 20);
		panel.add(txtAmount);
		txtAmount.setColumns(10);
		
		txtEmp = new JTextField();
		txtEmp.setEditable(false);
		txtEmp.setBounds(173, 76, 185, 20);
		panel.add(txtEmp);
		txtEmp.setColumns(10);
		
		JLabel lblHRA = new JLabel("HRA");
		lblHRA.setBounds(37, 273, 60, 20);
		panel.add(lblHRA);
		
		txtHRA = new JTextField();
		txtHRA.setColumns(10);
		txtHRA.setBounds(173, 273, 185, 20);
		panel.add(txtHRA);
		
		JLabel lblTA = new JLabel("TA");
		lblTA.setBounds(37, 316, 60, 20);
		panel.add(lblTA);
		
		txtTA = new JTextField();
		txtTA.setColumns(10);
		txtTA.setBounds(173, 316, 185, 20);
		panel.add(txtTA);
		
		JLabel lblNewLabel_3_1 = new JLabel("OverTime (hour)");
		lblNewLabel_3_1.setBounds(37, 116, 115, 14);
		panel.add(lblNewLabel_3_1);
		
		txtOT = new JTextField();
		txtOT.setEditable(false);
		txtOT.setColumns(10);
		txtOT.setBounds(173, 113, 185, 20);
		panel.add(txtOT);
		
		JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(510, 64, 412, 413);
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
				attendance = new Attendance();
				attendance = attendanceService.findAttendanceByEmpId(id);
				txtEmp.setText(employee.getName());
				txtOT.setText(attendance.getHourOT());
			}
		});
		btnSearch.setBounds(269, 33, 89, 23);
		panel.add(btnSearch);
		
		JLabel lblNewLabel_1 = new JLabel("academic certification");
		lblNewLabel_1.setBounds(37, 154, 126, 14);
		panel.add(lblNewLabel_1);
		
		txtSkills = new JTextField();
		txtSkills.setBounds(173, 151, 185, 20);
		panel.add(txtSkills);
		txtSkills.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Longetivity (Year)");
		lblNewLabel_2.setBounds(37, 195, 115, 14);
		panel.add(lblNewLabel_2);
		
		txtLong = new JTextField();
		txtLong.setBounds(173, 192, 185, 20);
		panel.add(txtLong);
		txtLong.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Emp name");
		lblNewLabel_3.setBounds(37, 79, 60, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Description");
		lblNewLabel_4.setBounds(37, 232, 60, 20);
		panel.add(lblNewLabel_4);
		
		txtDescription = new JTextField();
		txtDescription.setBounds(173, 232, 185, 20);
		panel.add(txtDescription);
		txtDescription.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
//		btnRegister.setEnabled(false);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AllowanceDetails allowanceDetails = new AllowanceDetails();
				setAllowanceDetails(allowanceDetails);
				
				if(!txtSkills.getText().isEmpty()&&!txtLong.getText().isEmpty()&&!txtAmount.getText().isEmpty()) {
					allowanceService.createAllowanceDetails(allowanceDetails);
					loadAllowanceDetails(Optional.empty());
					resetFormData();
					txtEmpID.requestFocus();
				} else {
					JOptionPane.showMessageDialog(null, "Enter required Fields", "Invalid field values", 0);
				}
			}
		});
		btnRegister.setBounds(20, 410, 80, 39);
		panel.add(btnRegister);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFormData();
				txtEmpID.requestFocus();

			}
		});
		btnClear.setBounds(200, 410, 80, 39);
		panel.add(btnClear);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtEmpID.getText();
				Employee theEmployee = new Employee();
				theEmployee = employeeService.findEmployeeById(id);
				if(!txtSkills.getText().isEmpty()&&!txtLong.getText().isEmpty()) {
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
					System.out.println(longevityAllowance + " this is longevity allowance!");
					otAllowance = ((basic + skillsAllowance) / 160) * overtimeHours * 2;
					System.out.println(otAllowance + " is the ot allowance!");
					otherAllowance = houseRent + transportFee + longevityAllowance +skillsAllowance;
					
					total = otherAllowance + otAllowance;
					txtAmount.setText(String.valueOf(total));
					btnRegister.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Enter required Fields to Calculate the Amount!", "Invalid field values", 0);
				}
				
			}
		});
		btnCalculate.setBounds(110, 410, 80, 39);
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
		btnDelete.setBounds(290, 410, 80, 39);
		panel.add(btnDelete);
		
		txtSearchName = new JTextField("Search By Employee Name");
		txtSearchName.setColumns(10);
		txtSearchName.setBounds(666, 27, 165, 26);
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
		btnSearchName.setBounds(841, 27, 75, 26);
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