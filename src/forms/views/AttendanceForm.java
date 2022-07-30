package forms.views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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
		absentField.setText("0");
		monthChooser.setMonth(0);
		leavesField.setText("0");
		lateField.setText("0");
		otField.setText("0");

	}
	
	private void setAttendanceDataFrom(Attendance attendance) {
		int currentYear = LocalDate.now().getYear(); 
		String mymonth = months[monthChooser.getMonth()] + ", " + currentYear;
		
		
		attendance.setPresent(presentField.getText().isBlank() ? "0" : presentField.getText());
		attendance.setAbsent(absentField.getText().isBlank() ? "0" : absentField.getText());
		attendance.setLeave(leavesField.getText().isBlank()? "0" : leavesField.getText());

		attendance.setMonth(mymonth);
		attendance.setHourLate(lateField.getText().isBlank() ? "0" : lateField.getText());
		attendance.setHourOT(otField.getText().isBlank() ? "0" : otField.getText());
		attendance.setEmployee(employee);
		
		int leave = Integer.valueOf(leavesField.getText());
		int leaveLeft = Integer.valueOf(attendance.getEmployee().getLeaveDays()) - leave;
		if (leaveLeft < 0) {
			JOptionPane.showMessageDialog(null, "The employee doesn't have enough leave days, so the extra leave days will be added to absent days!");
			int extraLeaves = leave - Integer.valueOf(attendance.getEmployee().getLeaveDays());
			
			int addedAbsent = Integer.valueOf(absentField.getText().isBlank() ? "0" : absentField.getText()) + extraLeaves;
			attendance.setAbsent(addedAbsent + "");
			employee.setLeaveDays(0 + "");
			employeeService.updateEmployee(String.valueOf(employee.getId()), employee);
		} else {
			employee.setLeaveDays(leaveLeft + "");
			employeeService.updateEmployee(String.valueOf(employee.getId()), employee);
		}

		
//		System.out.println(attendance.getLeaveDays());
//		int leaveLeft = Integer.valueOf(attendance.getLeaveDays()) - Integer.valueOf(leavesField.getText().isBlank()? "0" : leavesField.getText());
//		attendance.setLeaveDays(leaveLeft + "");
//		if (leaveLeft < 0) {
//			JOptionPane.showMessageDialog(null, "The employee doesn't have enough leave days, so the extra leave days will be added to absent days!");
//			int extraLeaves = Integer.valueOf(leavesField.getText().isBlank()? "0" : leavesField.getText()) - Integer.valueOf(attendance.getLeaveDays());
//			attendance.setLeave(attendance.getLeaveDays());
//			
//			int addedAbsent = Integer.valueOf(absentField.getText().isBlank() ? "0" : absentField.getText()) + extraLeaves;
//			attendance.setAbsent(addedAbsent + "");
//		} else {
//			attendance.setLeaveDays(leaveLeft + "");
//		}

	}
	
	private void loadAllAttendance(Optional<List<Attendance>> optionalAttendances) {
		this.dtm = (DefaultTableModel) this.attdtbl.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();
		
		
		this.attendanceList=this.attendanceService.findAllAttendances();

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
		this.setBounds(0, 0, 1065, 588);
		this.getContentPane().setBackground(Color.WHITE);
		this.getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setToolTipText("Employee Registration");
		panel.setName("Employee Registration");
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Attendance Management", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBackground(Color.WHITE);
		panel.setBounds(72, 22, 924, 182);
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
		lblMonth.setBounds(370, 33, 66, 21);
		panel.add(lblMonth);
		
		JLabel lblleaves = new JLabel("leaves");
		lblleaves.setForeground(Color.BLACK);
		lblleaves.setBounds(370, 68, 66, 21);
		panel.add(lblleaves);
		
		JLabel lblLate = new JLabel("Late (hr)");
		lblLate.setForeground(Color.BLACK);
		lblLate.setBounds(370, 97, 66, 21);
		panel.add(lblLate);
		
		JLabel lblOT = new JLabel("OverTime(hr)");
		lblOT.setForeground(Color.BLACK);
		lblOT.setBounds(370, 129, 82, 21);
		panel.add(lblOT);
		
		presentField = new JTextField("20");
		presentField.setColumns(10);
		presentField.setBounds(128, 97, 224, 21);
		panel.add(presentField);
		//presentField.setText("20");
		
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

				String id = empIDField.getText();
				employee = new Employee();
				employee = employeeService.findEmployeeById(id);
				EmployeeForm emp=new EmployeeForm();
				List<Employee> empList= employeeService.findAllEmployees();
				if(emp.duplicate(Integer.parseInt(id), empList)) {
					empNameField.setText(employee.getName());
				}
				else {
					JOptionPane.showMessageDialog(null, "ID doesn't exist !","Error",0);
					empIDField.setText("");
					empIDField.requestFocus();
					return;
				}
				
				
			}
		});
		btnSearchEmpID.setBounds(274, 32, 78, 23);
		panel.add(btnSearchEmpID);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(72, 249, 924, 250);
		getContentPane().add(scrollPane);
		
		attdtbl = new JTable();
		attdtbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
        scrollPane.setViewportView(attdtbl);
       
		monthChooser.setMonth(LocalDate.now().getMonthValue());
		monthChooser.setBounds(475, 31, 128, 26);
		panel.add(monthChooser);
		
		
        JButton btnSave = new JButton("Register");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Attendance attendance = new Attendance();
				Attendance attdRecord = new Attendance();
				attdRecord = attendanceService.findAttendanceByEmpId(empIDField.getText());
				

				if (!presentField.getText().trim().isBlank() && !absentField.getText().trim().isBlank() && !leavesField.getText().trim().isBlank() &&
					 !lateField.getText().trim().isBlank() && !otField.getText().trim().isBlank() && !empIDField.getText().trim().isBlank()) {
					
					if (!presentField.getText().trim().isBlank() && !absentField.getText().trim().isBlank() && !leavesField.getText().trim().isBlank() && !lateField.getText().trim().isBlank() && !otField.getText().trim().isBlank() && !empIDField.getText().trim().isBlank()) {
						if(presentField.getText().trim().matches("[0-9]+") &&
							absentField.getText().trim().matches("[0-9]+") &&
							leavesField.getText().trim().matches("[0-9]+") &&
							lateField.getText().trim().matches("[0-9]+") &&
							otField.getText().trim().matches("[0-9]+")) 
						{
							
							if (attdRecord.getMonth() != null) {
								int currentYear = LocalDate.now().getYear();
								if (attdRecord.getMonth().equals(months[monthChooser.getMonth()] + ", " + currentYear)) {
									JOptionPane.showMessageDialog(null, "Selected Employee's attendance is already registered for the selected month !", "Invalid Month", 0);
									return;	
								}								
							}
							Employee employee = new Employee();
							employee = employeeService.findEmployeeById(empIDField.getText());
							
							
							Calendar c = Calendar.getInstance();
							c.add(Calendar.MONTH, - 1);
							Date date = c.getTime();
							System.out.println(date + "");
							
							SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
							String lastMonth = format1.format(date);
							String selectedM = LocalDate.now().getYear()+ "-" + monthChooser.getMonth() +"-" + LocalDate.now().getDayOfMonth();
							try {
								Date empHd = format1.parse(employee.getHiredDate());
								Date lastD = format1.parse(lastMonth);
								Date seleD = format1.parse(selectedM);
								
								
								System.out.println(empHd.compareTo(lastD) <= 0);
								if (seleD.compareTo(empHd) < 0) {
									JOptionPane.showMessageDialog(null, "Selected Employee isn't hired yet!", "Invalid Month", 0);
									return;
								} else if (seleD.compareTo(lastD) > 0) {
									JOptionPane.showMessageDialog(null, "Invalid month", "Invalid Month", 0);
									return;	
								}
								setAttendanceDataFrom(attendance);	
								attendanceService.createAttendance(attendance);
								loadAllAttendance(Optional.empty());								
								JOptionPane.showMessageDialog(null, "Attendance successfully registered!", "Success", 1);
								resetFormData();	
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
													
							}
	
						}
						else if(!presentField.getText().trim().matches("[0-9]+")) {
							JOptionPane.showMessageDialog(null, "Enter digits only !", "Error in present", 0);
							return;
						}
						else if(!absentField.getText().trim().matches("[0-9]+")) {
							JOptionPane.showMessageDialog(null, "Enter digits only !", "Error in absent", 0);
							return;
						}
						else if(!leavesField.getText().trim().matches("[0-9]+")) {
							JOptionPane.showMessageDialog(null, "Enter digits only !", "Error in leaves", 0);
							return;
						}
						else if(!lateField.getText().trim().matches("[0-9]+")) {
							JOptionPane.showMessageDialog(null, "Enter digits only !", "Error in late", 0);
							return;
						}
						else {
							JOptionPane.showMessageDialog(null, "Enter digits only !", "Error in overtime", 0);
							return;
						}
					}
				
				}
				
			}
		);
		btnSave.setBounds(770, 88, 128, 38);
		panel.add(btnSave);
		
		JLabel currentYear = new JLabel("");
		currentYear.setHorizontalAlignment(SwingConstants.CENTER);
		currentYear.setBounds(598, 30, 51, 26);
		panel.add(currentYear);
		currentYear.setText(LocalDate.now().getYear() + "");
		
		
		searchField = new JTextField("Search By Employee Name");
		searchField.setColumns(10);
		searchField.setBounds(72, 215, 162, 21);
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
		btnEmpAttendanceSearch.setBounds(292, 215, 89, 23);
		getContentPane().add(btnEmpAttendanceSearch);
		
	}
}
