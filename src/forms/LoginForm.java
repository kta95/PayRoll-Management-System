package forms;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import entities.Employee;
import services.AuthService;
import services.EmployeeService;
import shared.utils.CurrentUserHolder;

import java.awt.Font;
import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginForm {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private AuthService authService;
	private Employee employee;
	private EmployeeService employeeService;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
					UIManager.setLookAndFeel( new FlatLightLaf() );
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginForm() {
		initialize();
		this.authService = new AuthService();
		this.employeeService = new EmployeeService();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(350, 100, 660, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 320, 500);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(103, 36, 115, 52);
		panel.add(lblLogin);
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(53, 164, 211, 41);
		panel.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(53, 239, 211, 41);
		panel.add(txtPassword);

		
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(53, 122, 74, 31);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setBounds(53, 206, 74, 31);
		panel.add(lblPassword);
		ImageIcon loginIcon = new ImageIcon(new ImageIcon("src\\icons\\login.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));

		JButton btnLogin = new JButton("Login");
		btnLogin.setIcon(loginIcon);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String username = txtUsername.getText();
                 String password = String.valueOf(txtPassword.getPassword());

                 if (!username.isEmpty() && !password.isEmpty()) {
                     String loggedInUserId = authService.login(username, password);
                     System.out.println("hello");
                     if(!loggedInUserId.isEmpty()) {
                         CurrentUserHolder.setLoggedInUser(employeeService.findEmployeeById(loggedInUserId));
                         System.out.println("==========");
                         JOptionPane.showMessageDialog(null, "Successfully Login", "Success", 1);
                         frame.setVisible(false);
                         Main main = new Main();
                         main.mainframe.setVisible(true);                
                     }
                 } else {
                     JOptionPane.showMessageDialog(null, "Enter required Fields", "Oops", 0);
                 }
                 txtUsername.setText("");
                 txtPassword.setText("");
             }
			
		});
		btnLogin.setBounds(53, 323, 211, 41);
		panel.add(btnLogin);

		JLabel logoImg = new JLabel("");
		logoImg.setHorizontalAlignment(SwingConstants.CENTER);
//		ImageIcon imageIcon = new ImageIcon(new ImageIcon("src\\icons\\logo.png").getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH));

		logoImg.setBounds(322, 87, 320, 249);
		logoImg.setIcon(new ImageIcon(LoginForm.class.getResource("/icons/logo.png")));
		frame.getContentPane().add(logoImg);
		
		JLabel lblNewLabel = new JLabel("Payroll Management System version(beta)");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(322, 436, 320, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(330, 11, 304, 68);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
