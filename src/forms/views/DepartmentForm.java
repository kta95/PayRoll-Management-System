package forms.views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

public class DepartmentForm extends JInternalFrame {
	private JTextField deptField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepartmentForm frame = new DepartmentForm();
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
	public DepartmentForm() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblDept = new JLabel("Department");
		lblDept.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDept.setHorizontalAlignment(SwingConstants.LEFT);
		lblDept.setBounds(75, 69, 128, 47);
		getContentPane().add(lblDept);
		
		deptField = new JTextField();
		deptField.setBounds(202, 71, 205, 47);
		getContentPane().add(deptField);
		deptField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(417, 71, 89, 47);
		getContentPane().add(btnSave);
		
		this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		BasicInternalFrameUI ui= (BasicInternalFrameUI)this.getUI();
		ui.setNorthPane(null);
		
		
		setBounds(0, 0, 976, 591);

	}
}
