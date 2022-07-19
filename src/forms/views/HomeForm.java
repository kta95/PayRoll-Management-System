package forms.views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class HomeForm extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeForm frame = new HomeForm();
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
	public HomeForm() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(28, 11, 911, 506);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(72, 45, 348, 183);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employees");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 328, 67);
		panel_1.add(lblNewLabel);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_1.setBounds(498, 45, 348, 183);
		panel.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JLabel lblDepartment = new JLabel("Payroll");
		lblDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepartment.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDepartment.setBounds(10, 11, 328, 67);
		panel_1_1.add(lblDepartment);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_2.setBounds(498, 281, 348, 183);
		panel.add(panel_1_2);
		panel_1_2.setLayout(null);
		
		JPanel panel_1_3 = new JPanel();
		panel_1_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_3.setBounds(72, 281, 348, 183);
		panel.add(panel_1_3);
		panel_1_3.setLayout(null);
		
		this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		BasicInternalFrameUI ui= (BasicInternalFrameUI)this.getUI();
		ui.setNorthPane(null);
		
		
		setBounds(0, 0, 976, 591);

	}
}
