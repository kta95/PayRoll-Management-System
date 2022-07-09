package forms.views;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;


import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JFormattedTextField.AbstractFormatter;
import java.util.Properties;

public class EmployeeForm extends JInternalFrame {

	private JTextField idField;
	private JTextField nameField;
	private JTextField genderField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextField addressField;
	private JTextField roleField;
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

		
		
		this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		BasicInternalFrameUI ui= (BasicInternalFrameUI)this.getUI();
		ui.setNorthPane(null);
	}
	private void initialize() {
		this.getContentPane().setBackground(Color.WHITE);
		this.setTitle("Employee Form");
		this.setBounds(0, 0, 976, 591);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Employee Registration", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setToolTipText("Employee Registration");
		panel.setBounds(26, 38, 924, 298);
		panel.setName("Employee Registration");
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel idLbl = new JLabel("ID");
		idLbl.setForeground(new Color(0, 0, 0));
		idLbl.setBounds(31, 33, 51, 21);
		panel.add(idLbl);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.black);
		lblName.setBounds(31, 65, 51, 21);
		panel.add(lblName);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setForeground(Color.black);
		lblGender.setBounds(31, 97, 51, 21);
		panel.add(lblGender);
		
		JLabel lblDob = new JLabel("DOB");
		lblDob.setHorizontalAlignment(SwingConstants.LEFT);
		lblDob.setForeground(Color.black);
		lblDob.setBounds(31, 129, 51, 21);
		panel.add(lblDob);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setForeground(Color.black);
		lblPhone.setBounds(31, 161, 51, 21);
		panel.add(lblPhone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.black);
		lblEmail.setBounds(31, 193, 51, 21);
		panel.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(Color.black);
		lblAddress.setBounds(31, 225, 51, 21);
		panel.add(lblAddress);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setForeground(Color.black);
		lblRole.setBounds(31, 257, 51, 21);
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
		
		roleField = new JTextField();
		roleField.setColumns(10);
		roleField.setBounds(111, 257, 184, 21);
		panel.add(roleField);
		

	}
}
