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

import forms.views.EmployeeForm;
import forms.views.HomeForm;

import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.formdev.flatlaf.icons.FlatCheckBoxIcon;

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
		frame.setBounds(10, 10, 1291, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel topBar = new JPanel();
		topBar.setBackground(Color.WHITE);
		topBar.setBounds(299, 0, 976, 92);
		frame.getContentPane().add(topBar);
		topBar.setLayout(null);
		
		JLabel lblLogo = new JLabel();
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(438, 0, 103, 92);
		topBar.add(lblLogo);
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("C:\\Users\\dell\\Downloads\\IMC.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));

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
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(299, 92, 976, 569);
		frame.getContentPane().add(desktopPane);
		
		homeMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				homeMenu.setBackground(new Color(0, 0, 128));
				empMenu.setBackground(Color.BLACK);;
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				HomeForm home = new HomeForm();
				desktopPane.removeAll();
				desktopPane.add(home).setVisible(true);
			}
			
		});

		empMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				empMenu.setBackground(new Color(0, 0, 128));
				homeMenu.setBackground(Color.BLACK);;
			}
			public void mouseClicked(MouseEvent e) {
				EmployeeForm empForm = new EmployeeForm();
				desktopPane.removeAll();
				desktopPane.add(empForm).setVisible(true);
			}
		});
	}
}
