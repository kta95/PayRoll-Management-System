package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class EmployeeFormshit {

	private JFrame frmEmployeeForm;
	private JTextField idField;
	private JTextField nameField;
	private JTextField genderField;
	private JTextField dobField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextField addressField;
	private JTextField basicSalaryField;
	private JTextField roleField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeFormshit window = new EmployeeFormshit();
					window.frmEmployeeForm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmployeeFormshit() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEmployeeForm = new JFrame();
		frmEmployeeForm.getContentPane().setBackground(Color.WHITE);
		frmEmployeeForm.setTitle("Employee Form");
		frmEmployeeForm.setBounds(100, 100, 1130, 597);
		frmEmployeeForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEmployeeForm.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Employee Registration", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panel.setToolTipText("Employee Registration");
		panel.setBounds(278, 99, 326, 448);
		panel.setName("Employee Registration");
		frmEmployeeForm.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel idLbl = new JLabel("ID");
		idLbl.setForeground(Color.WHITE);
		idLbl.setBounds(31, 33, 51, 21);
		panel.add(idLbl);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(31, 65, 51, 21);
		panel.add(lblName);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setForeground(Color.WHITE);
		lblGender.setBounds(31, 97, 51, 21);
		panel.add(lblGender);
		
		JLabel lblDob = new JLabel("DOB");
		lblDob.setHorizontalAlignment(SwingConstants.LEFT);
		lblDob.setForeground(Color.WHITE);
		lblDob.setBounds(31, 129, 51, 21);
		panel.add(lblDob);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setForeground(Color.WHITE);
		lblPhone.setBounds(31, 161, 51, 21);
		panel.add(lblPhone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setBounds(31, 193, 51, 21);
		panel.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(Color.WHITE);
		lblAddress.setBounds(31, 225, 51, 21);
		panel.add(lblAddress);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setForeground(Color.WHITE);
		lblRole.setBounds(31, 289, 51, 21);
		panel.add(lblRole);
		
		idField = new JTextField();
		idField.setBounds(111, 33, 184, 21);
		panel.add(idField);
		idField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(111, 65, 184, 21);
		panel.add(nameField);
		
		genderField = new JTextField();
		genderField.setColumns(10);
		genderField.setBounds(111, 97, 184, 21);
		panel.add(genderField);
		
		dobField = new JTextField();
		dobField.setColumns(10);
		dobField.setBounds(111, 129, 184, 21);
		panel.add(dobField);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(111, 161, 184, 21);
		panel.add(phoneField);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(111, 193, 184, 21);
		panel.add(emailField);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(111, 225, 184, 21);
		panel.add(addressField);
		
		JLabel lblBasicSalary = new JLabel("Basic Salary");
		lblBasicSalary.setForeground(Color.WHITE);
		lblBasicSalary.setBounds(31, 257, 75, 21);
		panel.add(lblBasicSalary);
		
		basicSalaryField = new JTextField();
		basicSalaryField.setEditable(false);
		basicSalaryField.setColumns(10);
		basicSalaryField.setBounds(111, 257, 184, 21);
		panel.add(basicSalaryField);
		
		roleField = new JTextField();
		roleField.setColumns(10);
		roleField.setBounds(111, 289, 184, 21);
		panel.add(roleField);
	}
}
