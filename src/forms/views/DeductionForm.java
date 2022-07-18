package forms.views;
import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

import entities.Employee;
import services.EmployeeService;
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
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class DeductionForm extends JInternalFrame {
	
	private JTextField txtTax;
	private JTextField txtSearch;
	private JTextField txtSSC;
	private JTextField txtdAmount;
	private DeductionService deductionService;
	private EmployeeService employeeService;
	private JTable tblDeduction;
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<DeductionDetails> originalDeductionList = new ArrayList<>();
	private List<DeductionDetails> dDetailslist=new ArrayList();
	private JTextField txtdSearch;
	private DeductionDetails deductionDetails;
    private List<Employee> employeeList;
    private JTextField txtDescription;
    private Employee employee;
    private JTextField txtEmpName;
    private JTextField txtLate;
    private Attendance attendance;
    private AttendanceService attendanceService;
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
	public DeductionForm() {
		initialize();
		initializeDependency();
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
	  }
	 
	 private void setDeductionDetails(DeductionDetails deductionDetails) {
		
		 deductionDetails.setTax(txtTax.getText());
		 deductionDetails.setSSC(txtSSC.getText());
		 deductionDetails.setDeduction_amount(Double.parseDouble(txtdAmount.getText()));
		 deductionDetails.setDescription(txtDescription.getText());
		 deductionDetails.setEmployee(employee);
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
		panel.setBounds(59, 11, 421, 467);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tax");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(45, 199, 46, 14);
		panel.add(lblNewLabel);
		
		txtTax = new JTextField();
		txtTax.setEditable(false);
		txtTax.setBounds(170, 196, 193, 20);
		panel.add(txtTax);
		txtTax.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Emp ID");
		lblNewLabel_1.setBounds(45, 54, 75, 17);
		panel.add(lblNewLabel_1);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(169, 54, 109, 20);
		panel.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearchEmpID = new JButton("Search");
		btnSearchEmpID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<DeductionDetails> dList = new ArrayList<>();
				dList= deductionService.findAllDDetails();
				
				String id = txtSearch.getText();
				employee = new Employee();
				employee = employeeService.findEmployeeById(id);
				txtEmpName.setText(employee.getName());
				
				attendance = new Attendance();
				attendance = attendanceService.findAttendanceByEmpId(id);
				txtLate.setText(attendance.getHourLate());
			}
		});
		btnSearchEmpID.setBounds(288, 53, 75, 23);
		panel.add(btnSearchEmpID);
		
		JLabel lblNewLabel_2 = new JLabel("SSC");
		lblNewLabel_2.setBounds(45, 246, 58, 14);
		panel.add(lblNewLabel_2);
		
		txtSSC = new JTextField();
		txtSSC.setEditable(false);
		txtSSC.setColumns(10);
		txtSSC.setBounds(169, 243, 193, 20);
		panel.add(txtSSC);
		
		JLabel lblNewLabel_3 = new JLabel("Amount");
		lblNewLabel_3.setBounds(45, 345, 100, 14);
		panel.add(lblNewLabel_3);
		
		txtdAmount = new JTextField();
		txtdAmount.setEditable(false);
		txtdAmount.setColumns(10);
		txtdAmount.setBounds(169, 342, 193, 20);
		panel.add(txtdAmount);
		
		JButton btnSave = new JButton("Register");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeductionDetails deductionDetails = new DeductionDetails();
				setDeductionDetails(deductionDetails);
				
				if(!txtTax.getText().isEmpty() && !txtSSC.getText().isEmpty() && !txtdAmount.getText().isEmpty()) {
					deductionService.createDeductionDetails(deductionDetails);
					loadAllDeductionDetails(Optional.empty());
					resetFormData();
				}
			}
		});
		btnSave.setBounds(45, 399, 74, 42);
		panel.add(btnSave);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCalculate.setBounds(129, 399, 74, 42);
		panel.add(btnCalculate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClear.setBounds(213, 399, 74, 42);
		panel.add(btnClear);
		
		JLabel lblNewLabel_4 = new JLabel("Emp Name");
		lblNewLabel_4.setBounds(44, 110, 59, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Description");
		lblNewLabel_5.setBounds(45, 295, 75, 14);
		panel.add(lblNewLabel_5);
		
		txtDescription = new JTextField();
		txtDescription.setBounds(169, 292, 193, 20);
		panel.add(txtDescription);
		txtDescription.setColumns(10);
		
		txtEmpName = new JTextField();
		txtEmpName.setEditable(false);
		txtEmpName.setColumns(10);
		txtEmpName.setBounds(169, 107, 193, 20);
		panel.add(txtEmpName);
		
		JLabel lblNewLabel_4_1 = new JLabel("late (hour)");
		lblNewLabel_4_1.setBounds(45, 153, 59, 14);
		panel.add(lblNewLabel_4_1);
		
		txtLate = new JTextField();
		txtLate.setEditable(false);
		txtLate.setColumns(10);
		txtLate.setBounds(170, 150, 193, 20);
		panel.add(txtLate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(297, 399, 74, 42);
		panel.add(btnDelete);
		
		this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		BasicInternalFrameUI ui= (BasicInternalFrameUI)this.getUI();
		ui.setNorthPane(null);
		
		
		setBounds(0, 0, 976, 591);
		
		JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(502, 51, 412, 413);
        this.getContentPane().add(scrollPane);

        tblDeduction = new JTable();
        tblDeduction.setFont(new Font("Tahoma", Font.PLAIN, 15));
        scrollPane.setViewportView(tblDeduction);
        
        
        
        txtdSearch = new JTextField();
        txtdSearch.setBounds(643, 11, 172, 29);
        getContentPane().add(txtdSearch);
        txtdSearch.setColumns(10);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(825, 11, 89, 32);
        getContentPane().add(btnSearch);

	}
}