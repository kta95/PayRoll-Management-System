package forms.views;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entities.AllowanceDetails;
import entities.DeductionDetails;
import entities.Department;
import entities.Employee;
import entities.Payroll;
import entities.Position;
import services.AllowanceService;
import services.AttendanceService;
import services.DeductionService;
import services.DepartmentService;
import services.EmployeeService;
import services.PayrollService;
import services.PositionService;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;

public class HomeForm extends JInternalFrame {

	private EmployeeService employeeService;
	private AttendanceService attendanceService;
	private AllowanceService allowanceService;
	private DeductionService deductionService;
	private PayrollService payrollService;
	private DepartmentService departmentService;
	private PositionService positionService;
	List<Employee> employeeList = new ArrayList<>();
	List<Department> deptList = new ArrayList<>();
	List<Position> positionList = new ArrayList<>();
	List<Payroll> payrollList = new ArrayList<>();
	List<AllowanceDetails> allowanceList = new ArrayList<>();
	List<DeductionDetails> dedukList = new ArrayList<>();
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
		initializeDependancy();

		initialize();

	}
	
	private void initializeDependancy() {

		this.employeeService = new EmployeeService();
		this.attendanceService = new AttendanceService();
		this.allowanceService = new AllowanceService();
		this.deductionService = new DeductionService();
		this.payrollService = new PayrollService();
		this.departmentService = new DepartmentService();
		this.positionService = new PositionService();
	}
	private void initialize() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		employeeList = employeeService.findAllEmployees();
		deptList = departmentService.findAllDepartments();
		positionList = positionService.findAllPositions();
		payrollList = payrollService.findAllPayrolls();
		allowanceList = allowanceService.findAllADetails();
		dedukList = deductionService.findAllDDetails();
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(74, 11, 911, 506);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser dialog = new JFileChooser();
				dialog.setSelectedFile(new File("Employees Report.pdf"));
				int dialogResult = dialog.showSaveDialog(null);
				if (dialogResult == JFileChooser.APPROVE_OPTION) {
					String filePath = dialog.getSelectedFile().getPath();
					try {
						List<Employee> empList = new ArrayList<>();
						empList = employeeService.findAllEmployees();
						
						Document myDocument = new Document();
						PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
						PdfPTable table = new PdfPTable(11);
						myDocument.open();
						
						float[] columnWidths = new float[] {5, 12, 8, 8, 12, 15, 8, 8, 7, 10, 10};
						table.setWidths(columnWidths);
						
						table.setWidthPercentage(100);
						
					    
				          myDocument.add(new Paragraph("Employee Report",FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD )));
				          myDocument.add(new Paragraph(new Date().toString()));
				          myDocument.add(new Paragraph("---------------------------------------------------------------------------------------------------------------"));
				          table.addCell(new PdfPCell(new Paragraph("Employee ID",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Name",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Gender",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Date of Birth",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Telephone",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Email",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Address",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Date Hired",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Role",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Position",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Department",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          
				          for(Employee emp : empList) {
				        	  table.addCell(new PdfPCell(new Paragraph(emp.getId() + "",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(emp.getName(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(emp.getGender(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(emp.getDateOfBirth(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(emp.getPhone(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(emp.getEmail(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(emp.getAddress(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(emp.getHiredDate(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(emp.getRole() + "",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(emp.getPosition().getTitle(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(emp.getDepartment().getDepartmentName(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				          }
				          
				          myDocument.add(table);
				           myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------"));
				           myDocument.close();  
				           JOptionPane.showMessageDialog(null,"Report was successfully generated");
				          
					}  catch(Exception e2){
			            JOptionPane.showMessageDialog(null,e2);			            			            
				     }
				}
			}
		});
		panel_1.setBackground(UIManager.getColor("CheckBox.background"));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(72, 45, 348, 183);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Report");
		lblNewLabel.setBackground(UIManager.getColor("Button.highlight"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 328, 67);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Total Employee :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(37, 89, 179, 67);
		panel_1.add(lblNewLabel_1);
		
		
		
		JLabel empCount = new JLabel("");
		empCount.setHorizontalAlignment(SwingConstants.CENTER);
		empCount.setText(employeeList.size() + "");
		empCount.setFont(new Font("Tahoma", Font.BOLD, 18));
		empCount.setBounds(214, 89, 69, 67);
		panel_1.add(empCount);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser dialog = new JFileChooser();
				dialog.setSelectedFile(new File("Payroll Report.pdf"));
				int dialogResult = dialog.showSaveDialog(null);
				if (dialogResult == JFileChooser.APPROVE_OPTION) {
					String filePath = dialog.getSelectedFile().getPath();
					try {
						List<Payroll> pList = new ArrayList<>();
						pList = payrollService.findAllPayrolls();
						
						pList.forEach(a -> System.out.println(a.getNetSalary()));
						
						
						Document myDocument = new Document();
						PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
						PdfPTable table = new PdfPTable(11);
						myDocument.open();
						
						float[] columnWidths = new float[] {5, 12, 8, 8, 12, 15, 8, 8, 7, 10, 10};
						table.setWidths(columnWidths);
						
						table.setWidthPercentage(100);
						
					    
				          myDocument.add(new Paragraph("Payroll Report",FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD )));
				          myDocument.add(new Paragraph(new Date().toString()));
				          myDocument.add(new Paragraph("---------------------------------------------------------------------------------------------------------------"));
				          table.addCell(new PdfPCell(new Paragraph("Payroll ID",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Employee Name",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Month",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Present",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Absent",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Late Hour",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Overtime Hour",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Total Allowance",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Total Deduction",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Gross Salary",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Net Salary",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          
				          for(Payroll p : pList) {
				        	  table.addCell(new PdfPCell(new Paragraph(p.getId() + "",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(p.getEmployee().getName(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(p.getAttendance().getMonth(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(p.getAttendance().getPresent() + " day(s)",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(p.getAttendance().getAbsent() + " day(s)",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(p.getAttendance().getHourLate() + " hrs",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(p.getAttendance().getHourOT() + " hrs",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(p.getAllowanceDetails().getAllowance_Amount() + " ¥",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(p.getDeductionDetails().getDeduction_amount() + " ¥",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(p.getGrossSalary() + " ¥",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(p.getNetSalary() + " ¥",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				          }
				          
				          myDocument.add(table);
				          myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------"));
				          myDocument.close();  
				          JOptionPane.showMessageDialog(null,"Report was successfully generated");
				          
					}  catch(Exception e2){
			            JOptionPane.showMessageDialog(null,e2);			            			            
				     }
				}	
				
			}
		});
		panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_1.setBounds(498, 45, 348, 183);
		panel.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JLabel lblDepartment = new JLabel("Payroll Report");
		lblDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepartment.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDepartment.setBounds(10, 11, 328, 67);
		panel_1_1.add(lblDepartment);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Total Payroll :");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(55, 89, 179, 67);
		panel_1_1.add(lblNewLabel_1_1_1);
		
		JLabel payrollCount = new JLabel("");
		payrollCount.setHorizontalAlignment(SwingConstants.CENTER);
		payrollCount.setFont(new Font("Tahoma", Font.BOLD, 18));
		payrollCount.setBounds(220, 89, 69, 67);
		panel_1_1.add(payrollCount);
		
		payrollCount.setText(payrollList.size() + "");
		JPanel panel_1_2 = new JPanel();
		panel_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser dialog = new JFileChooser();
				dialog.setSelectedFile(new File("Deduction Report.pdf"));
				int dialogResult = dialog.showSaveDialog(null);
				if (dialogResult == JFileChooser.APPROVE_OPTION) {
					String filePath = dialog.getSelectedFile().getPath();
					try {
						List<DeductionDetails> ddList = new ArrayList<>();
						ddList = deductionService.findAllDDetails();
												
						
						Document myDocument = new Document();
						PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
						PdfPTable table = new PdfPTable(5);
						myDocument.open();
						
						float[] columnWidths = new float[] {5, 12, 8, 12, 15};
						table.setWidths(columnWidths);
						
						table.setWidthPercentage(100);
						
					    
				          myDocument.add(new Paragraph("Deduction Report",FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD )));
				          myDocument.add(new Paragraph(new Date().toString()));
				          myDocument.add(new Paragraph("---------------------------------------------------------------------------------------------------------------"));
				          table.addCell(new PdfPCell(new Paragraph("Deduction ID",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Employee Name",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Tax",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("SSC",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Deduction Amount",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
		
				          
				          for(DeductionDetails d : ddList) {
				        	  table.addCell(new PdfPCell(new Paragraph(d.getDeduction_details_id() + "",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(d.getEmployee().getName(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(d.getTax() + " ¥",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(d.getSSC() + " ¥",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(d.getDeduction_amount() + " ¥",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				          }
				          
				          myDocument.add(table);
				          myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------"));
				          myDocument.close();  
				          JOptionPane.showMessageDialog(null,"Report was successfully generated");
				          
					}  catch(Exception e2){
			            JOptionPane.showMessageDialog(null,e2);			            			            
				    }
				}			
				
			}
		});
		panel_1_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_2.setBounds(498, 281, 348, 183);
		panel.add(panel_1_2);
		panel_1_2.setLayout(null);
		
		JLabel lblDeduction = new JLabel("Deduction Report");
		lblDeduction.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeduction.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDeduction.setBounds(10, 11, 328, 67);
		panel_1_2.add(lblDeduction);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Total Deduction :");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_1.setBounds(59, 89, 179, 67);
		panel_1_2.add(lblNewLabel_1_1_1_1);
		
		JLabel dedukCount = new JLabel("");
		dedukCount.setHorizontalAlignment(SwingConstants.CENTER);
		dedukCount.setFont(new Font("Tahoma", Font.BOLD, 18));
		dedukCount.setBounds(227, 89, 69, 67);
		panel_1_2.add(dedukCount);
		dedukCount.setText(dedukList.size() + "");
		
		JPanel allowancepnl = new JPanel();
		allowancepnl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser dialog = new JFileChooser();
				dialog.setSelectedFile(new File("Allowance Report.pdf"));
				int dialogResult = dialog.showSaveDialog(null);
				if (dialogResult == JFileChooser.APPROVE_OPTION) {
					String filePath = dialog.getSelectedFile().getPath();
					try {
						List<AllowanceDetails> alwList = new ArrayList<>();
						alwList = allowanceService.findAllADetails();
												
						
						Document myDocument = new Document();
						PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
						PdfPTable table = new PdfPTable(8);
						myDocument.open();
						
						float[] columnWidths = new float[] {5, 12, 8, 12, 15, 8, 8, 9};
						table.setWidths(columnWidths);
						
						table.setWidthPercentage(100);
						
					    
				          myDocument.add(new Paragraph("Allowance Report",FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD )));
				          myDocument.add(new Paragraph(new Date().toString()));
				          myDocument.add(new Paragraph("---------------------------------------------------------------------------------------------------------------"));
				          table.addCell(new PdfPCell(new Paragraph("Allowance ID",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Employee Name",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Longevity",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Acedamic Certification Allowance",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("HRA",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("TA",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Month",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          table.addCell(new PdfPCell(new Paragraph("Allowance Amount",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD))));
				          
				          for(AllowanceDetails a : alwList) {
				        	  table.addCell(new PdfPCell(new Paragraph(a.getAdId() + "",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(a.getEmployee().getName(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(a.getLongevity() + " year(s)",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(a.getSkills() + " ¥",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(a.getHouseRentAllowance() + " ¥",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(a.getTransportAllowance() + " ¥",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(a.getAttendance().getMonth(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				              table.addCell(new PdfPCell(new Paragraph(a.getAllowance_Amount() + " ¥",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
				          }
				          
				          myDocument.add(table);
				          myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------"));
				          myDocument.close();  
				          JOptionPane.showMessageDialog(null,"Report was successfully generated");
				          
					}  catch(Exception e2){
			            JOptionPane.showMessageDialog(null,e2);			            			            
				    }
				}		
				
			}
		});
		allowancepnl.setBorder(new LineBorder(new Color(0, 0, 0)));
		allowancepnl.setBounds(72, 281, 348, 183);
		panel.add(allowancepnl);
		allowancepnl.setLayout(null);
		
		JLabel lblAllowance = new JLabel("Allowance Report");
		lblAllowance.setHorizontalAlignment(SwingConstants.CENTER);
		lblAllowance.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAllowance.setBounds(10, 11, 328, 67);
		allowancepnl.add(lblAllowance);
		
		JLabel lblNewLabel_1_1 = new JLabel("Total Allowance :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(33, 89, 188, 67);
		allowancepnl.add(lblNewLabel_1_1);
		
		JLabel allowancelbl = new JLabel("");
		allowancelbl.setHorizontalAlignment(SwingConstants.CENTER);
		allowancelbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		allowancelbl.setBounds(218, 89, 69, 67);
		allowancepnl.add(allowancelbl);
		
		allowancelbl.setText(allowanceList.size() + "");
		this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		BasicInternalFrameUI ui= (BasicInternalFrameUI)this.getUI();
		ui.setNorthPane(null);
		
		
		setBounds(0, 0, 1065, 588);
	}
}
