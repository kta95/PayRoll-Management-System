package forms.views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

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
import entities.Employee;
import services.AttendanceService;
import services.EmployeeService;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
	private DefaultTableModel dtm=new DefaultTableModel();
	private List<Attendance> attendanceList = new ArrayList<>();
	private final String[] months;
	private Employee employee;
	private EmployeeService employeeService;
	JMonthChooser monthChooser = new JMonthChooser();
	private AttendanceService attendanceService;
	private List<Attendance> filteredAttendanceList = new ArrayList();
	private JTextField searchField;

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
		this.initialize();
		this.initializeDependancy();
		this.setTableDesign();
		resetFormData();
		this.months = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		BasicInternalFrameUI ui= (BasicInternalFrameUI)this.getUI();
		ui.setNorthPane(null);
		
		this.loadAllAttendance(Optional.empty());

	}
	
	private void initializeDependancy() {
		this.employeeService = new EmployeeService();
		this.attendanceService = new AttendanceService();
	}
	
	
	private void setTableDesign() {
		dtm.addColumn("Attendance ID");
		dtm.addColumn("Employee ID");
		dtm.addColumn("Employee Name");
		dtm.addColumn("Present");
		dtm.addColumn("Absent");
		dtm.addColumn("Month");
		dtm.addColumn("Leaves");
		dtm.addColumn("Lates");
		dtm.addColumn("Overtime");
		
		this.attdtbl.setModel(dtm);
	}
	
	private void resetFormData() {
		empIDField.setText("");
		empNameField.setText("");
		presentField.setText("0");
		absentField.setText("0");
		monthChooser.setMonth(0);
		leavesField.setText("0");
		lateField.setText("0");
		otField.setText("0");

	}
	
	private void setAttendanceDataFrom(Attendance attendance) {
		String mymonth = months[monthChooser.getMonth()];

		attendance.setPresent(presentField.getText().isBlank() ? "0" : presentField.getText());
		attendance.setAbsent(absentField.getText().isBlank() ? "0" : absentField.getText());
		attendance.setLeave(leavesField.getText().isBlank()? "0" : leavesField.getText());
		attendance.setMonth(mymonth);
		attendance.setHourLate(lateField.getText().isBlank() ? "0" : lateField.getText());
		attendance.setHourOT(otField.getText().isBlank() ? "0" : otField.getText());
		attendance.setEmployee(employee);
	}
	
	private void loadAllAttendance(Optional<List<Attendance>> optionalAttendances) {
		this.dtm = (DefaultTableModel) this.attdtbl.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();
		
		System.out.println("from");
		
		this.attendanceList=this.attendanceService.findAllAttendances();
		for (Attendance attd : attendanceList) {
			System.out.println(attd.getId() + " " + attd.getEmployee().getName());
		}
		this.filteredAttendanceList=optionalAttendances.orElseGet(()->this.attendanceList)
				.stream().collect(Collectors.toList());
	
            
		filteredAttendanceList.forEach(e-> {
            	Object[] row =new Object [9]; 
            			row[0] = e.getId();
            			row[1] = e.getEmployee().getId();
            			row[2] = e.getEmployee().getName();
            			row[3] = e.getPresent();
            			row[4] = e.getAbsent();
            			row[5] = e.getMonth();
            			row[6] = e.getLeave();
            			row[7] = e.getHourLate();
            			row[8] = e.getHourOT();

            	dtm.addRow(row);
            });
			this.attdtbl.setModel(dtm);
       
	}
	
	private void initialize() {
		this.setBounds(0, 0, 976, 591);
		this.getContentPane().setBackground(Color.WHITE);
		this.getContentPane().setLayout(null);
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
		
		empIDField = new JTextField("Search By Employee ID");
		empIDField.setColumns(10);
		empIDField.setBounds(128, 33, 136, 21);
		empIDField.setForeground(Color.GRAY);
		empIDField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (empIDField.getText().equals("Search By Employee ID")) {
                	empIDField.setText("");
                	empIDField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (empIDField.getText().isEmpty()) {
                	empIDField.setForeground(Color.GRAY);
                	empIDField.setText("Search By Employee ID");
                }
            }
            });
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
		btnSearchEmpID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Attendance> attendanceList = new ArrayList<>();
				attendanceList= attendanceService.findAllAttendances();
				for (Attendance attd : attendanceList) {
					System.out.println(attd.getId() + " " + attd.getEmployee().getName());
				}
				String id = empIDField.getText();
				employee = new Employee();
				employee = employeeService.findEmployeeById(id);
				empNameField.setText(employee.getName());
				
				
			}
		});
		btnSearchEmpID.setBounds(274, 32, 78, 23);
		panel.add(btnSearchEmpID);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 249, 924, 250);
		getContentPane().add(scrollPane);
		
		attdtbl = new JTable();
		attdtbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
        scrollPane.setViewportView(attdtbl);
        
		monthChooser.setBounds(475, 31, 224, 26);
		panel.add(monthChooser);
		
        JButton btnSave = new JButton("Register");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Attendance attendance = new Attendance();

				if (!presentField.getText().trim().isBlank() && !absentField.getText().trim().isBlank() && !leavesField.getText().trim().isBlank() &&
						!(monthChooser.getMonth() == 0) && !lateField.getText().trim().isBlank() && !otField.getText().trim().isBlank() && !empIDField.getText().trim().isBlank()) {
					
					setAttendanceDataFrom(attendance);
					
					attendanceService.createAttendance(attendance);
					loadAllAttendance(Optional.empty());
					
					resetFormData();
				}
				
			}
		});
		btnSave.setBounds(770, 88, 128, 38);
		panel.add(btnSave);
		
		searchField = new JTextField("Search By Employee Name");
		searchField.setColumns(10);
		searchField.setBounds(10, 217, 162, 21);
		searchField.setForeground(Color.GRAY);
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search By Employee Name")) {
                	searchField.setText("");
                	searchField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                	searchField.setForeground(Color.GRAY);
                	searchField.setText("Search By Employee Name");
                }
            }
            });
		getContentPane().add(searchField);
		
		JButton btnEmpAttendanceSearch = new JButton("Search");
		btnEmpAttendanceSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword=searchField.getText();
				loadAllAttendance(
						Optional.of(attendanceList.stream()
								.filter(a -> a.getEmployee().getName().toLowerCase()
								.startsWith(keyword.toLowerCase()))
								.collect(Collectors.toList()))
								);
				if(searchField.getText().equals("Search By Employee Name")) {
					loadAllAttendance(Optional.empty());
				}
			}
		});
		btnEmpAttendanceSearch.setBounds(182, 215, 89, 23);
		getContentPane().add(btnEmpAttendanceSearch);
		
	}
}
