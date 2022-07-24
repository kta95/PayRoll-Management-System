package forms.views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import entities.Employee;
import entities.UserRole;
import services.EmployeeService;
import shared.utils.EmployeeHolder;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeeDetailsForm extends JInternalFrame {
	private JTextField txtUsername;
	private JTextField txtPassword;
	private Employee employee;
	private EmployeeService employeeService;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeDetailsForm frame = new EmployeeDetailsForm();
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
	public EmployeeDetailsForm() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		this.initialize();
		this.employeeService = new EmployeeService();
	}
	
	
	private void initialize() {
		this.setBounds(0, 0, 1065, 588);

		JLabel lbltitle = new JLabel("Employee Details");
		lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbltitle.setBounds(0, 0, 976, 76);
		getContentPane().add(lbltitle);
		
		JLabel lblName = new JLabel("Name -");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(129, 175, 143, 38);
		getContentPane().add(lblName);
		
		JLabel lblId = new JLabel("ID -");
		lblId.setHorizontalAlignment(SwingConstants.LEFT);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblId.setBounds(129, 126, 143, 38);
		getContentPane().add(lblId);
		
		JLabel lblGender = new JLabel("Gender -");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGender.setBounds(129, 224, 143, 38);
		getContentPane().add(lblGender);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth -");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDateOfBirth.setBounds(129, 273, 143, 38);
		getContentPane().add(lblDateOfBirth);
		
		JLabel lblPhone = new JLabel("Phone -");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPhone.setBounds(129, 322, 143, 38);
		getContentPane().add(lblPhone);
		
		JLabel lblEmail = new JLabel("Email -");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail.setBounds(129, 371, 143, 38);
		getContentPane().add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAddress.setBounds(593, 126, 143, 38);
		getContentPane().add(lblAddress);
		
		JLabel lblHiredDate = new JLabel("Hired Date -");
		lblHiredDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHiredDate.setBounds(593, 175, 143, 38);
		getContentPane().add(lblHiredDate);
		
		JLabel lblPosition = new JLabel("Position -");
		lblPosition.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPosition.setBounds(593, 224, 143, 38);
		getContentPane().add(lblPosition);
		
		JLabel lblDepartment = new JLabel("Department -");
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDepartment.setBounds(593, 273, 143, 38);
		getContentPane().add(lblDepartment);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setBounds(593, 322, 143, 38);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(593, 371, 143, 38);
		getContentPane().add(lblPassword);
		
		JLabel lblid = new JLabel("");
		lblid.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblid.setBounds(282, 126, 194, 38);
		getContentPane().add(lblid);
		
		JLabel lblname = new JLabel("");
		lblname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblname.setBounds(282, 175, 194, 38);
		getContentPane().add(lblname);
		
		JLabel lblGender_1 = new JLabel("");
		lblGender_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGender_1.setBounds(282, 224, 194, 38);
		getContentPane().add(lblGender_1);
		
		JLabel lblDob = new JLabel("");
		lblDob.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDob.setBounds(282, 273, 194, 38);
		getContentPane().add(lblDob);
		
		JLabel lblPhone_1 = new JLabel("");
		lblPhone_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPhone_1.setBounds(282, 322, 194, 38);
		getContentPane().add(lblPhone_1);
		
		JLabel lblMail = new JLabel("");
		lblMail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMail.setBounds(282, 371, 194, 38);
		getContentPane().add(lblMail);
		
		JLabel lblAddress_1 = new JLabel("");
		lblAddress_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAddress_1.setBounds(746, 126, 194, 38);
		getContentPane().add(lblAddress_1);
		
		JLabel lblHd = new JLabel("");
		lblHd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHd.setBounds(746, 175, 194, 38);
		getContentPane().add(lblHd);
		
		JLabel lblPosition_1 = new JLabel("");
		lblPosition_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPosition_1.setBounds(746, 224, 194, 38);
		getContentPane().add(lblPosition_1);
		
		JLabel lblDepartment_1 = new JLabel("");
		lblDepartment_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDepartment_1.setBounds(746, 273, 194, 38);
		getContentPane().add(lblDepartment_1);
		
		txtUsername = new JTextField();
		txtUsername.setEditable(false);
		txtUsername.setBounds(746, 325, 194, 38);
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setEditable(false);
		txtPassword.setColumns(10);
		txtPassword.setBounds(746, 374, 194, 38);
		getContentPane().add(txtPassword);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeForm newframe = new EmployeeForm();
				EmployeeDetailsForm.this.setVisible(false);
				newframe.setVisible(true);
				getParent().add(newframe);
			}
		});
		btnBack.setBounds(593, 443, 143, 46);
		getContentPane().add(btnBack);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(797, 443, 143, 46);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Employee theEmployee = EmployeeHolder.getSelectedEmployee();
				theEmployee.setUsername(txtUsername.getText());
				theEmployee.setPassword(txtPassword.getText());
				if (!txtUsername.getText().trim().isBlank() && !txtPassword.getText().trim().isBlank()) {
					employeeService.updateEmployee(String.valueOf(theEmployee.getId()), theEmployee);
					JOptionPane.showMessageDialog(null, "Saved Successfully!", "Admin Registration", 1 );
					txtUsername.setText("");
					txtPassword.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Username and Password shouldn't be empty!", "Admin Registration", 0 );
				}
				
			}
		});
		getContentPane().add(btnSave);

		this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		BasicInternalFrameUI ui= (BasicInternalFrameUI)this.getUI();
		ui.setNorthPane(null);
		
		JLabel lblaccess = new JLabel("");
		lblaccess.setForeground(Color.RED);
		lblaccess.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblaccess.setBounds(86, 87, 497, 28);
		getContentPane().add(lblaccess);
		
		
		JButton btnShow = new JButton("Show details");
		btnShow.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnShow.setForeground(Color.WHITE);
		btnShow.setBackground(Color.RED);
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employee = EmployeeHolder.getSelectedEmployee();

				lblid.setText(String.valueOf(employee.getId()));
				lblname.setText(employee.getName());
				lblGender_1.setText(employee.getGender());
				lblDob.setText(employee.getDateOfBirth());
				lblPhone_1.setText(employee.getPhone());
				lblMail.setText(employee.getEmail());
				lblAddress_1.setText(employee.getAddress());
				lblHd.setText(employee.getHiredDate());
				lblPosition_1.setText(employee.getPosition().getTitle());
				lblDepartment_1.setText(employee.getDepartment().getDepartmentName());


				if (employee.getRole().equals(UserRole.ADMIN) || employee.getName().equals("Michiko")) {
					lblaccess.setForeground(Color.green);
					lblaccess.setText("This employee has permission to become administrator!");
					txtUsername.setEditable(true);
					txtPassword.setEditable(true);
					txtUsername.setText(employee.getUsername());
				} else {
					lblaccess.setForeground(Color.red);
					lblaccess.setText("This employee does not have access to Admin!");
				}
			}
		});
		btnShow.setBounds(129, 438, 143, 52);
		getContentPane().add(btnShow);
	

		
	}
}
