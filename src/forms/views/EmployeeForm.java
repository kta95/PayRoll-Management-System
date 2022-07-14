package forms.views;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.ui.FlatTextFieldUI;

import javax.swing.JFormattedTextField.AbstractFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import com.toedter.calendar.JDateChooser;

import entities.Department;
import entities.Employee;
import entities.Position;
import services.DepartmentService;
import services.EmployeeService;
import services.PositionService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class EmployeeForm extends JInternalFrame {
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextField addressField;
    private JTable tblEmployee;
    private JComboBox<String> deptCombo = new JComboBox<>();
    private JComboBox<String> genderCombo = new JComboBox<>();
	List<String> genders = new ArrayList<>();
    private List<Department> deptList;
    private JComboBox<String> positionCombo = new JComboBox<>();
    private List<Position> positionList;
	JDateChooser dobChooser = new JDateChooser();
	JDateChooser hiredDateChooser = new JDateChooser();
	private DefaultTableModel dtm=new DefaultTableModel();
	private List<Employee> employeeList = new ArrayList<>();
	private DepartmentService departmentService;
	private PositionService positionService;
	private EmployeeService employeeService;
	private List<Employee> filteredEmployeeList = new ArrayList();
	private Employee employee;
	SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	private JTextField searchField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeForm frame = new EmployeeForm();

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
	public EmployeeForm() {
		initialize();
		setTableDesign();
		initializeDependancy();
		this.loadAllEmployee(Optional.empty());
		this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		BasicInternalFrameUI ui= (BasicInternalFrameUI)this.getUI();
		ui.setNorthPane(null);
		this.loadDepartmentComboBox();
		this.loadPositionComboBox();
		this.loadGenderComboBox();
	}
	
	private void initializeDependancy() {

		this.departmentService = new DepartmentService();
		this.positionService = new PositionService();
		this.employeeService = new EmployeeService();
	}
	
	private void setTableDesign() {
		dtm.addColumn("Employee ID");
		dtm.addColumn("Name");
		dtm.addColumn("Gender");
		dtm.addColumn("Date Of Birth");
		dtm.addColumn("Phone");
		dtm.addColumn("Email");
		dtm.addColumn("Address");
		dtm.addColumn("Hired Date");
		dtm.addColumn("Position");
		dtm.addColumn("Department");
		
		this.tblEmployee.setModel(dtm);
	}
	
	private void setEmployeeDataFromForm(Employee employee) throws ParseException {

		employee.setName(nameField.getText());
		Optional<String> selectedGender = genders.stream().filter(g -> g.equals(genderCombo.getSelectedItem())).findFirst();
		employee.setGender(selectedGender.orElse(null));
		String dob = Date_Format.format(dobChooser.getDate());
		employee.setDateOfBirth(dob);
//		System.out.println(dob);

		employee.setPhone(phoneField.getText());
		employee.setEmail(emailField.getText());
		employee.setAddress(addressField.getText());
		String hd = "" + Date_Format.format(hiredDateChooser.getDate());
//		System.out.println(hd);
		employee.setHiredDate(hd);
		Optional<Position> selectedPosition = positionList.stream().filter(p -> p.getTitle().equals(positionCombo.getSelectedItem())).findFirst();
		employee.setPosition(selectedPosition.orElse(null));
		Optional<Department> selectedDepartment = deptList.stream().filter(d -> d.getDepartmentName().equals(deptCombo.getSelectedItem())).findFirst();
		employee.setDepartment(selectedDepartment.orElse(null));
//		employee.setBasicSalary(Double.valueOf(basicSalaryField.getText().isBlank()? "0" : basicSalaryField.getText()));
//		Optional<Department> selectedDepartment = deptList.stream().filter(d -> d.getDepartmentName().equals(deptCombo.getSelectedItem())).findFirst();
//		position.setDepartment(selectedDepartment.orElse(null));
	}
	
	private void loadDepartmentComboBox() {
		deptCombo.addItem("- Select -");
		this.deptList = this.departmentService.findAllDepartments();
		this.deptList.forEach(d -> deptCombo.addItem(d.getDepartmentName()));
	}
	
	private void loadGenderComboBox() {
		genderCombo.addItem("- Select -");
		this.genders.add("Male");
		this.genders.add("Female");
		this.genders.add("Other");
		this.genders.forEach(g -> genderCombo.addItem(g));
	}
	
	private void loadPositionComboBox() {
		positionCombo.addItem("- Select -");
		this.positionList = this.positionService.findAllPositions();
		this.positionList.forEach(d -> positionCombo.addItem(d.getTitle()));
	}
	
	private void loadAllEmployee(Optional<List<Employee>> optionalEmployees) {
		this.dtm = (DefaultTableModel) this.tblEmployee.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();
		
		this.employeeList=this.employeeService.findAllEmployees();
		this.filteredEmployeeList=optionalEmployees.orElseGet(()->this.employeeList)
				.stream().collect(Collectors.toList());
	
            
		filteredEmployeeList.forEach(e-> {
            	Object[] row =new Object [12]; 
            			row[0] = e.getId();
            			row[1] = e.getName();
            			row[2] = e.getGender();
            			row[3] = e.getDateOfBirth();
            			row[4] = e.getPhone();
            			row[5] = e.getEmail();
            			row[6] = e.getAddress();
            			row[7] = e.getHiredDate();
            			row[8] = e.getPosition().getTitle();
            			row[9] = e.getDepartment().getDepartmentName();
            	dtm.addRow(row);
            });
			this.tblEmployee.setModel(dtm);
       
	}
	
	private void resetFormData() {
		nameField.setText("");
		genderCombo.setSelectedIndex(0);
		dobChooser.setDate(null);
		phoneField.setText("");
		emailField.setText("");
		addressField.setText("");
		hiredDateChooser.setDate(null);
		positionCombo.setSelectedIndex(0);
		deptCombo.setSelectedIndex(0);
	}
	
	private void initialize() {
        

		this.getContentPane().setBackground(Color.WHITE);
		this.setTitle("Employee Form");
		this.setBounds(0, 0, 976, 538);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Employee Management", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setToolTipText("Employee Registration");
		panel.setBounds(28, 11, 924, 187);
		panel.setName("Employee Registration");
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.black);
		lblName.setBounds(31, 33, 51, 21);
		panel.add(lblName);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setForeground(Color.black);
		lblGender.setBounds(31, 65, 51, 21);
		panel.add(lblGender);
		
		JLabel lblDob = new JLabel("DOB");
		lblDob.setHorizontalAlignment(SwingConstants.LEFT);
		lblDob.setForeground(Color.black);
		lblDob.setBounds(31, 97, 51, 21);
		panel.add(lblDob);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setForeground(Color.black);
		lblPhone.setBounds(31, 129, 51, 21);
		panel.add(lblPhone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.black);
		lblEmail.setBounds(321, 33, 51, 21);
		panel.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(Color.black);
		lblAddress.setBounds(321, 65, 51, 21);
		panel.add(lblAddress);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(111, 33, 200, 21);
		panel.add(nameField);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(111, 129, 200, 21);
		panel.add(phoneField);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(382, 33, 200, 21);
		panel.add(emailField);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(382, 65, 200, 21);
		panel.add(addressField);
		
		dobChooser.setBounds(111, 97, 200, 20);
		panel.add(dobChooser);
		
		JLabel lblhiredDate = new JLabel("HiredDate");
		lblhiredDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblhiredDate.setForeground(Color.BLACK);
		lblhiredDate.setBounds(321, 97, 67, 21);
		panel.add(lblhiredDate);
		
		hiredDateChooser.setBounds(382, 98, 200, 20);
		panel.add(hiredDateChooser);
		
		JButton btnSave = new JButton("Register");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Employee employee = new Employee();
				if(!nameField.getText().isEmpty() && !(genderCombo.getSelectedIndex() == 0) && !dobChooser.getDate().equals(null) &&
						!phoneField.getText().isEmpty() && !emailField.getText().isEmpty() && !addressField.getText().isEmpty() &&
						!hiredDateChooser.getDate().equals(null)) {
					try {
						setEmployeeDataFromForm(employee);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					employeeService.createEmployee(employee);
					Date date = dobChooser.getDate();

					System.out.println(date);

					loadAllEmployee(Optional.empty());
					resetFormData();
				}else {
					JOptionPane.showMessageDialog(null,"Enter required field", "error", 0);
				}
				
			}
		});
		btnSave.setBounds(382, 133, 163, 38);
		panel.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setEmployeeDataFromForm(employee);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				employeeService.updateEmployee(String.valueOf(employee.getId()), employee);
				resetFormData();
				loadAllEmployee(Optional.empty());
			}
		});
		btnUpdate.setBounds(565, 133, 163, 38);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!nameField.getText().isEmpty() && !(genderCombo.getSelectedIndex() == 0) && !dobChooser.getDate().equals(null) &&
						!phoneField.getText().isEmpty() && !emailField.getText().isEmpty() && !addressField.getText().isEmpty() &&
						!hiredDateChooser.getDate().equals(null)) {
					employeeService.deleteEmployee(String.valueOf(employee.getId()));
					resetFormData();
					loadAllEmployee(Optional.empty());
				}
			}
		});
		btnDelete.setBounds(746, 133, 163, 38);
		panel.add(btnDelete);
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setForeground(Color.BLACK);
		lblPosition.setBounds(592, 33, 67, 21);
		panel.add(lblPosition);
		
		positionCombo.setBounds(684, 32, 200, 22);
		panel.add(positionCombo);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setForeground(Color.BLACK);
		lblDepartment.setBounds(592, 65, 84, 21);
		panel.add(lblDepartment);
		
		deptCombo.setBounds(684, 64, 200, 22);
		panel.add(deptCombo);
		
		genderCombo.setBounds(111, 64, 200, 22);
		panel.add(genderCombo);
		
		

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(28, 267, 924, 217);
        this.getContentPane().add(scrollPane);

        tblEmployee = new JTable();
        tblEmployee.setFont(new Font("Tahoma", Font.PLAIN, 15));
        scrollPane.setViewportView(tblEmployee);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String keyword=searchField.getText();
				loadAllEmployee(
						Optional.of(employeeList.stream()
								.filter(employee -> employee.getName().toLowerCase()
								.startsWith(keyword.toLowerCase()))
								.collect(Collectors.toList()))
								);
				if(searchField.getText().equals("Search By Employee Name")) {
					loadAllEmployee(Optional.empty());
				}
        	}
        });
        btnSearch.setBounds(237, 226, 89, 30);
        getContentPane().add(btnSearch);
        
        searchField = new JTextField("Search By Employee Name");
        searchField.setColumns(10);
        searchField.setBounds(28, 226, 200, 30);
    	searchField.setForeground(Color.GRAY);
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search By Employee Name")) {
                	searchField.setText("");
                	searchField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                	searchField.setForeground(Color.GRAY);
                	searchField.setText("Search By Employee Name");
                }
            }
            });
        getContentPane().add(searchField);
        
        
        this.tblEmployee.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!tblEmployee.getSelectionModel().isSelectionEmpty()) {

                String id = tblEmployee.getValueAt(tblEmployee.getSelectedRow(), 0).toString();

                employee = employeeService.findEmployeeById(id);
                
                nameField.setText(employee.getName());
                int index = 0;
                for (String gender : genders) {
                	if (gender.equals(employee.getGender())) {
                		index = genders.indexOf(gender) + 1;
                	}
                }
                System.out.println(index);
                genderCombo.setSelectedIndex(index); 
                
        		Date dateOfBirth = null;
        		Date hiredDate = null;
				try {
					dateOfBirth = formatter.parse(employee.getDateOfBirth());
	        		hiredDate = formatter.parse(employee.getHiredDate());

				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
             
                dobChooser.setDate(dateOfBirth);

                phoneField.setText(employee.getPhone());
                emailField.setText(employee.getEmail());
                addressField.setText(employee.getAddress());        
                hiredDateChooser.setDate(hiredDate);
                System.out.println("This is position index: " + employee.getPosition().getpId());
                positionCombo.setSelectedIndex(employee.getPosition().getpId());
                System.out.println("this is department index: " + employee.getDepartment().getDepartmentId());
				deptCombo.setSelectedIndex(employee.getDepartment().getDepartmentId());

            }
        });
	}
}
