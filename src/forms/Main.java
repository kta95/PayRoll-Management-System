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

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.formdev.flatlaf.icons.FlatCheckBoxIcon;
import com.formdev.flatlaf.ui.FlatBorder;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(40, 10, 1291, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel topBar = new JPanel();
		topBar.setBackground(Color.WHITE);
		topBar.setBounds(299, 0, 976, 72);
		frame.getContentPane().add(topBar);
		topBar.setLayout(null);
		
		JLabel lblLogo = new JLabel();
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(448, 0, 80, 70);
		topBar.add(lblLogo);
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("src\\images\\logo.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));

		lblLogo.setIcon(imageIcon);

		
		JPanel sideBar = new JPanel();
		sideBar.setBackground(Color.BLACK);
		sideBar.setBounds(0, 0, 299, 661);
		frame.getContentPane().add(sideBar);
		sideBar.setLayout(null);
		
		
		JLabel lblNewLabel_1 = new JLabel("");
	
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(0, 52, 157, 123);
		sideBar.add(lblNewLabel_1);
		
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
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
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
		lblEmployee.setHorizontalAlignment(SwingConstants.CENTER);
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
		lblPosition.setHorizontalAlignment(SwingConstants.CENTER);
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
		lblDepart.setHorizontalAlignment(SwingConstants.CENTER);
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
		lblPayroll.setHorizontalAlignment(SwingConstants.CENTER);
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
		lblAttendance.setHorizontalAlignment(SwingConstants.CENTER);
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
		lblDeduction.setHorizontalAlignment(SwingConstants.CENTER);
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
		lblAllowance.setHorizontalAlignment(SwingConstants.CENTER);
		lblAllowance.setForeground(Color.WHITE);
		lblAllowance.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAllowance.setBounds(80, 0, 135, 49);
		allowanceMenu.add(lblAllowance);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(299, 123, 976, 538);
		frame.getContentPane().add(desktopPane);
		HomeForm home = new HomeForm();
		desktopPane.add(home).setVisible(true);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(299, 71, 976, 51);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCurrentMenu = new JLabel(lblHome.getText());
		lblCurrentMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentMenu.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblCurrentMenu.setBounds(0, 0, 328, 51);
		panel.add(lblCurrentMenu);
		
		Color defaultColor = Color.BLACK;
		Color onClickColor = new Color(0, 0, 128);
		
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
