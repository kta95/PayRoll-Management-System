package forms.views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import com.toedter.calendar.JMonthChooser;

import entities.Attendance;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class AttendanceForm extends JInternalFrame {
	private JTextField presentField;
	private JTextField absentField;
	private JTextField leavesField;
	private JTextField lateField;
	private JTextField otField;
	private JTextField empIDField;
	private JTextField empNameField;
	private JTable attdtbl;
	private final String[] months;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AttendanceForm frame = new AttendanceForm();
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
	public AttendanceForm() {
		this.months = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		BasicInternalFrameUI ui= (BasicInternalFrameUI)this.getUI();
		ui.setNorthPane(null);
		
		
		setBounds(0, 0, 976, 591);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setToolTipText("Employee Registration");
		panel.setName("Employee Registration");
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Attendance Management", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 924, 182);
		getContentPane().add(panel);
		
		JLabel lblPresent = new JLabel("Present");
		lblPresent.setForeground(Color.BLACK);
		lblPresent.setBounds(31, 97, 51, 21);
		panel.add(lblPresent);
		
		JLabel lblAbsent = new JLabel("Absent");
		lblAbsent.setForeground(Color.BLACK);
		lblAbsent.setBounds(31, 129, 51, 21);
		panel.add(lblAbsent);
		
		JLabel lblMonth = new JLabel("Month");
		lblMonth.setHorizontalAlignment(SwingConstants.LEFT);
		lblMonth.setForeground(Color.BLACK);
		lblMonth.setBounds(385, 33, 51, 21);
		panel.add(lblMonth);
		
		JLabel lblleaves = new JLabel("leaves");
		lblleaves.setForeground(Color.BLACK);
		lblleaves.setBounds(385, 68, 51, 21);
		panel.add(lblleaves);
		
		JLabel lblLate = new JLabel("Late");
		lblLate.setForeground(Color.BLACK);
		lblLate.setBounds(385, 97, 51, 21);
		panel.add(lblLate);
		
		JLabel lblOT = new JLabel("OverTime");
		lblOT.setForeground(Color.BLACK);
		lblOT.setBounds(385, 129, 69, 21);
		panel.add(lblOT);
		
		presentField = new JTextField();
		presentField.setColumns(10);
		presentField.setBounds(128, 97, 224, 21);
		panel.add(presentField);
		
		absentField = new JTextField();
		absentField.setColumns(10);
		absentField.setBounds(128, 129, 224, 21);
		panel.add(absentField);
		
		leavesField = new JTextField();
		leavesField.setColumns(10);
		leavesField.setBounds(475, 65, 224, 21);
		panel.add(leavesField);
		
		lateField = new JTextField();
		lateField.setColumns(10);
		lateField.setBounds(475, 97, 224, 21);
		panel.add(lateField);
		
		otField = new JTextField();
		otField.setColumns(10);
		otField.setBounds(475, 129, 224, 21);
		panel.add(otField);
		
		
		
		JLabel lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setForeground(Color.BLACK);
		lblEmployeeId.setBounds(31, 36, 78, 21);
		panel.add(lblEmployeeId);
		
		empIDField = new JTextField();
		empIDField.setColumns(10);
		empIDField.setBounds(128, 33, 115, 21);
		panel.add(empIDField);
		
		JLabel lblEmployeeName = new JLabel("Employee Name");
		lblEmployeeName.setForeground(Color.BLACK);
		lblEmployeeName.setBounds(31, 68, 88, 21);
		panel.add(lblEmployeeName);
		
		empNameField = new JTextField();
		empNameField.setEditable(false);
		empNameField.setColumns(10);
		empNameField.setBounds(128, 65, 224, 21);
		panel.add(empNameField);
		
		JButton btnSearchEmpID = new JButton("Search");
		btnSearchEmpID.setBounds(263, 32, 89, 23);
		panel.add(btnSearchEmpID);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 204, 924, 349);
		getContentPane().add(scrollPane);
		
		attdtbl = new JTable();
		attdtbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
        scrollPane.setViewportView(attdtbl);
        
		JMonthChooser monthChooser = new JMonthChooser();
		monthChooser.setBounds(475, 31, 224, 26);
		panel.add(monthChooser);
		
        JButton btnSave = new JButton("Register");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!presentField.getText().trim().isBlank() && !absentField.getText().trim().isBlank() && !leavesField.getText().trim().isBlank() &&
						!(monthChooser.getMonth() == 0) && !lateField.getText().trim().isBlank() && !otField.getText().trim().isBlank() && !empIDField.getText().trim().isBlank()) {
					Attendance attendance = new Attendance();
					String mymonth = months[monthChooser.getMonth()];
					
					attendance.setPresent(Integer.valueOf(presentField.getText()));
					attendance.setAbsent(Integer.valueOf(absentField.getText()));
					attendance.setPresent(Integer.valueOf(leavesField.getText()));
					attendance.setMonth(mymonth);
					attendance.setPresent(Integer.valueOf(lateField.getText()));
					attendance.setPresent(Integer.valueOf(otField.getText()));
					attendance.setPresent(Integer.valueOf(empIDField.getText()));
					
					System.out.println(mymonth);
				}
				
			}
		});
		btnSave.setBounds(749, 24, 128, 38);
		panel.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(749, 73, 128, 38);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(749, 122, 128, 38);
		panel.add(btnDelete);
		


	}
}
