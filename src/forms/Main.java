package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;


import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;

import forms.views.AllowanceForm;
import forms.views.AttendanceForm;
import forms.views.DeductionForm;
import forms.views.DepartmentForm;
import forms.views.EmployeeForm;
import forms.views.HomeForm;
import forms.views.PayrollForm;
import forms.views.PositionForm;
import shared.utils.CurrentUserHolder;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.formdev.flatlaf.icons.FlatCheckBoxIcon;
import com.formdev.flatlaf.ui.FlatBorder;

import entities.Employee;
import java.awt.Toolkit;

public class Main {

	public JFrame mainframe;
    private Employee employee;
    public JDesktopPane desktopPane = new JDesktopPane();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					Main window = new Main();
					UIManager.setLookAndFeel( new FlatLightLaf() );
					window.mainframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		employee = CurrentUserHolder.getCurrentUser();
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainframe = new JFrame();
		mainframe.setTitle("Payroll Management System");
		mainframe.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/icons/favicon.png")));
		mainframe.getContentPane().setBackground(Color.WHITE);
		mainframe.setBounds(0, 0, 1380, 750);
		mainframe.setExtendedState( mainframe.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.getContentPane().setLayout(null);
//		mainframe.setUndecorated(true);
		JPanel sideBar = new JPanel();
		
		
		
		JPanel topBar = new JPanel();
		topBar.setBackground(Color.WHITE);
		topBar.setBounds(299, 0, 1065, 72);
		mainframe.getContentPane().add(topBar);
		topBar.setLayout(null);
		
		JLabel lblLogo = new JLabel();
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(448, 0, 80, 70);
		topBar.add(lblLogo);
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("src\\icons\\logo.png").getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));

		lblLogo.setIcon(imageIcon);
		
		JLabel lblGreeting = new JLabel("Welcome,");
		lblGreeting.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGreeting.setBounds(10, 16, 94, 25);
		topBar.add(lblGreeting);
		
		JLabel lblName = new JLabel();
		lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblName.setText(employee.getName() + "!");
		lblName.setBounds(91, 16, 169, 25);
		topBar.add(lblName);
		
		
		JPanel panelexit = new JPanel();
		panelexit.setBackground(Color.WHITE);
		panelexit.addMouseListener(new MouseAdapter() {	// to show confirm box
			@Override
			public void mouseReleased(MouseEvent e) {
				int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);				
				if (response == JOptionPane.YES_OPTION) {
					mainframe.dispose();
					LoginForm login = new LoginForm();
					login.frame.setVisible(true);
				}
			}
		});
		panelexit.setBounds(1025, 0, 40, 35);
		topBar.add(panelexit);
		panelexit.setLayout(null);
		
		JLabel lblExit = new JLabel("");
		lblExit.setBounds(0, 0, 40, 35);
		panelexit.add(lblExit);
		lblExit.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon logoutIcon = new ImageIcon(new ImageIcon("src\\icons\\logout.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		lblExit.setIcon(logoutIcon);
	
		JLabel lblNewLabel = new JLabel("Logout");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(968, 0, 59, 35);
		topBar.add(lblNewLabel);

		sideBar.setBackground(Color.BLACK);
		sideBar.setBounds(0, 0, 299, 710);
		mainframe.getContentPane().add(sideBar);
		sideBar.setLayout(null);
		
		JLabel lblTitle = new JLabel("PayRoll Management");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(10, 11, 279, 40);
		sideBar.add(lblTitle);
		
		JPanel homeMenu = new JPanel();
		
		homeMenu.setBackground(Color.BLACK);;
		homeMenu.setBounds(0, 105, 299, 49);
		sideBar.add(homeMenu);
		homeMenu.setLayout(null);
		
		JLabel lblHome = new JLabel("Home");
		ImageIcon homeIcon = new ImageIcon(new ImageIcon("src\\icons\\home.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		lblHome.setIcon(homeIcon);
		lblHome.setHorizontalAlignment(SwingConstants.LEFT);
		lblHome.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblHome.setForeground(new Color(255, 255, 255));
		lblHome.setBounds(80, 0, 135, 49);
		homeMenu.add(lblHome);
		
		JPanel empMenu = new JPanel();
		empMenu.setLayout(null);
		empMenu.setBackground(Color.BLACK);;
		empMenu.setBounds(0, 165, 299, 49);
		sideBar.add(empMenu);
		
		JLabel lblEmployee = new JLabel("Employee");
		ImageIcon empIcon = new ImageIcon(new ImageIcon("src\\icons\\employee.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		lblEmployee.setIcon(empIcon);
		lblEmployee.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmployee.setForeground(Color.WHITE);
		lblEmployee.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmployee.setBounds(80, 0, 135, 49);
		empMenu.add(lblEmployee);
		
		JPanel positionMenu = new JPanel();

		positionMenu.setLayout(null);
		positionMenu.setBackground(Color.BLACK);
		positionMenu.setBounds(0, 285, 299, 49);
		sideBar.add(positionMenu);
		
		JLabel lblPosition = new JLabel("Position");
		ImageIcon positionIcon = new ImageIcon(new ImageIcon("src\\icons\\hierarchy.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		lblPosition.setIcon(positionIcon);
		lblPosition.setHorizontalAlignment(SwingConstants.LEFT);
		lblPosition.setForeground(Color.WHITE);
		lblPosition.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPosition.setBounds(80, 0, 135, 49);
		positionMenu.add(lblPosition);
		
		JPanel deptMenu = new JPanel();
		
		deptMenu.setLayout(null);
		deptMenu.setBackground(Color.BLACK);
		deptMenu.setBounds(0, 225, 299, 49);
		sideBar.add(deptMenu);
		
		JLabel lblDepart = new JLabel("Department");
		ImageIcon deptIcon = new ImageIcon(new ImageIcon("src\\icons\\department.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		lblDepart.setIcon(deptIcon);
		lblDepart.setHorizontalAlignment(SwingConstants.LEFT);
		lblDepart.setForeground(Color.WHITE);
		lblDepart.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDepart.setBounds(80, 0, 135, 49);
		deptMenu.add(lblDepart);
		
		JPanel payrollMenu = new JPanel();
		payrollMenu.setLayout(null);
		payrollMenu.setBackground(Color.BLACK);
		payrollMenu.setBounds(0, 525, 299, 49);
		sideBar.add(payrollMenu);
		
		JLabel lblPayroll = new JLabel("Payroll");
		ImageIcon payrollIcon = new ImageIcon(new ImageIcon("src\\icons\\salary.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		lblPayroll.setIcon(payrollIcon);
		
		lblPayroll.setHorizontalAlignment(SwingConstants.LEFT);
		lblPayroll.setForeground(Color.WHITE);
		lblPayroll.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPayroll.setBounds(80, 0, 135, 49);
		payrollMenu.add(lblPayroll);
		
		JPanel attdMenu = new JPanel();
		attdMenu.setLayout(null);
		attdMenu.setBackground(Color.BLACK);
		attdMenu.setBounds(0, 345, 299, 49);
		sideBar.add(attdMenu);
		
		JLabel lblAttendance = new JLabel("Attendance");
		ImageIcon attdIcon = new ImageIcon(new ImageIcon("src\\icons\\attendance.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		lblAttendance.setIcon(attdIcon);
		lblAttendance.setHorizontalAlignment(SwingConstants.LEFT);
		lblAttendance.setForeground(Color.WHITE);
		lblAttendance.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAttendance.setBounds(80, 0, 135, 49);
		attdMenu.add(lblAttendance);
		
		JPanel deducMenu = new JPanel();
		deducMenu.setLayout(null);
		deducMenu.setBackground(Color.BLACK);
		deducMenu.setBounds(0, 465, 299, 49);
		sideBar.add(deducMenu);
		
		JLabel lblDeduction = new JLabel("Deduction");
		ImageIcon dedukIcon = new ImageIcon(new ImageIcon("src\\icons\\deduction.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		lblDeduction.setIcon(dedukIcon);
		lblDeduction.setHorizontalAlignment(SwingConstants.LEFT);
		lblDeduction.setForeground(Color.WHITE);
		lblDeduction.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDeduction.setBounds(80, 0, 135, 49);
		deducMenu.add(lblDeduction);
		
		JPanel allowanceMenu = new JPanel();

		allowanceMenu.setLayout(null);
		allowanceMenu.setBackground(Color.BLACK);
		allowanceMenu.setBounds(0, 405, 299, 49);
		sideBar.add(allowanceMenu);
		
		JLabel lblAllowance = new JLabel("allowance");
		ImageIcon allowanceIcon = new ImageIcon(new ImageIcon("src\\icons\\dollar.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		lblAllowance.setIcon(allowanceIcon);
		lblAllowance.setHorizontalAlignment(SwingConstants.LEFT);
		lblAllowance.setForeground(Color.WHITE);
		lblAllowance.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAllowance.setBounds(80, 0, 135, 49);
		allowanceMenu.add(lblAllowance);
		
		JLabel lblNewLabel_2 = new JLabel("Logged in As:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(10, 674, 89, 26);
		sideBar.add(lblNewLabel_2);
		
		JLabel lblloggedInUser = new JLabel();
		lblloggedInUser.setText(employee.getUsername());
		lblloggedInUser.setForeground(Color.WHITE);
		lblloggedInUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblloggedInUser.setBounds(98, 674, 89, 26);
		sideBar.add(lblloggedInUser);
		
		desktopPane.setBounds(299, 123, 1065, 588);
		mainframe.getContentPane().add(desktopPane);
		HomeForm home = new HomeForm();
		desktopPane.add(home).setVisible(true);


		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(299, 71, 1065, 51);
		mainframe.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCurrentMenu = new JLabel(lblHome.getText());
		lblCurrentMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentMenu.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblCurrentMenu.setBounds(0, 0, 328, 51);
		panel.add(lblCurrentMenu);
		
		Color defaultColor = Color.BLACK;
		Color onClickColor = new Color(0, 0, 128);
		homeMenu.setBackground(onClickColor);
		
		homeMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				homeMenu.setBackground(onClickColor);
				empMenu.setBackground(defaultColor);
				deptMenu.setBackground(defaultColor);
				positionMenu.setBackground(defaultColor);
				attdMenu.setBackground(defaultColor);
				allowanceMenu.setBackground(defaultColor);
				deducMenu.setBackground(defaultColor);
				payrollMenu.setBackground(defaultColor);
				
				lblCurrentMenu.setText(lblHome.getText());

			}
			@Override
			public void mouseClicked(MouseEvent e) {
				desktopPane.removeAll();
				desktopPane.add(home).setVisible(true);
			}
			
		});

		empMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				empMenu.setBackground(new Color(0, 0, 128));
				homeMenu.setBackground(Color.BLACK);
				deptMenu.setBackground(defaultColor);
				positionMenu.setBackground(defaultColor);
				attdMenu.setBackground(defaultColor);
				allowanceMenu.setBackground(defaultColor);
				deducMenu.setBackground(defaultColor);
				payrollMenu.setBackground(defaultColor);
				
				lblCurrentMenu.setText(lblEmployee.getText());
			}
			public void mouseClicked(MouseEvent e) {
				EmployeeForm empForm = new EmployeeForm();
				desktopPane.removeAll();
				desktopPane.add(empForm).setVisible(true);
			}
		});
		
		deptMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				homeMenu.setBackground(defaultColor);
				empMenu.setBackground(defaultColor);
				deptMenu.setBackground(onClickColor);
				positionMenu.setBackground(defaultColor);
				attdMenu.setBackground(defaultColor);
				allowanceMenu.setBackground(defaultColor);
				deducMenu.setBackground(defaultColor);
				payrollMenu.setBackground(defaultColor);
				
				
				lblCurrentMenu.setText(lblDepart.getText());

				DepartmentForm dept = new DepartmentForm();
				desktopPane.removeAll();
				desktopPane.add(dept).setVisible(true);
			}
		});
		
		positionMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				homeMenu.setBackground(defaultColor);
				empMenu.setBackground(defaultColor);
				deptMenu.setBackground(defaultColor);
				positionMenu.setBackground(onClickColor);
				attdMenu.setBackground(defaultColor);
				allowanceMenu.setBackground(defaultColor);
				deducMenu.setBackground(defaultColor);
				payrollMenu.setBackground(defaultColor);
				lblCurrentMenu.setText(lblPosition.getText());

				
				
				PositionForm pForm = new PositionForm();
				desktopPane.removeAll();
				desktopPane.add(pForm).setVisible(true);
			}
		});
		
		allowanceMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				homeMenu.setBackground(defaultColor);
				empMenu.setBackground(defaultColor);
				deptMenu.setBackground(defaultColor);
				positionMenu.setBackground(defaultColor);
				attdMenu.setBackground(defaultColor);
				allowanceMenu.setBackground(onClickColor);
				deducMenu.setBackground(defaultColor);
				payrollMenu.setBackground(defaultColor);
				
				
				lblCurrentMenu.setText(lblAllowance.getText());

				AllowanceForm allwn = new AllowanceForm();
				desktopPane.removeAll();
				desktopPane.add(allwn).setVisible(true);
			}
		});
		
		deducMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				homeMenu.setBackground(defaultColor);
				empMenu.setBackground(defaultColor);
				deptMenu.setBackground(defaultColor);
				positionMenu.setBackground(defaultColor);
				attdMenu.setBackground(defaultColor);
				allowanceMenu.setBackground(defaultColor);
				deducMenu.setBackground(onClickColor);
				payrollMenu.setBackground(defaultColor);
				
				lblCurrentMenu.setText(lblDeduction.getText());

				
				DeductionForm deduk = new DeductionForm();
				desktopPane.removeAll();
				desktopPane.add(deduk).setVisible(true);
			}
		});
		
		attdMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				homeMenu.setBackground(defaultColor);
				empMenu.setBackground(defaultColor);
				deptMenu.setBackground(defaultColor);
				positionMenu.setBackground(defaultColor);
				attdMenu.setBackground(onClickColor);
				allowanceMenu.setBackground(defaultColor);
				deducMenu.setBackground(defaultColor);
				payrollMenu.setBackground(defaultColor);
				
				lblCurrentMenu.setText(lblAttendance.getText());

				
				AttendanceForm attd = new AttendanceForm();
				desktopPane.removeAll();
				desktopPane.add(attd).setVisible(true);
			}
		});
		
		payrollMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				homeMenu.setBackground(defaultColor);
				empMenu.setBackground(defaultColor);
				deptMenu.setBackground(defaultColor);
				positionMenu.setBackground(defaultColor);
				attdMenu.setBackground(defaultColor);
				allowanceMenu.setBackground(defaultColor);
				deducMenu.setBackground(defaultColor);
				payrollMenu.setBackground(onClickColor);
				
				lblCurrentMenu.setText(lblPayroll.getText());

				
				PayrollForm payrollForm = new PayrollForm();
				desktopPane.removeAll();
				desktopPane.add(payrollForm).setVisible(true);
			}
		});
	}
}
