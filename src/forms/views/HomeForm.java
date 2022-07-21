package forms.views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;

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
		panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_1.setBounds(498, 45, 348, 183);
		panel.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JLabel lblDepartment = new JLabel("Payroll");
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
		panel_1_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_2.setBounds(498, 281, 348, 183);
		panel.add(panel_1_2);
		panel_1_2.setLayout(null);
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setHorizontalAlignment(SwingConstants.CENTER);
		lblPosition.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPosition.setBounds(10, 11, 328, 67);
		panel_1_2.add(lblPosition);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Total Position :");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1_1.setBounds(59, 89, 179, 67);
		panel_1_2.add(lblNewLabel_1_1_1_1);
		
		JLabel positionCount = new JLabel("");
		positionCount.setHorizontalAlignment(SwingConstants.CENTER);
		positionCount.setFont(new Font("Tahoma", Font.BOLD, 18));
		positionCount.setBounds(227, 89, 69, 67);
		panel_1_2.add(positionCount);
		positionCount.setText(positionList.size() + "");
		
		JPanel panel_1_3 = new JPanel();
		panel_1_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_3.setBounds(72, 281, 348, 183);
		panel.add(panel_1_3);
		panel_1_3.setLayout(null);
		
		JLabel lblDepartment_1 = new JLabel("Department");
		lblDepartment_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepartment_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDepartment_1.setBounds(10, 11, 328, 67);
		panel_1_3.add(lblDepartment_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Total Department :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(33, 89, 188, 67);
		panel_1_3.add(lblNewLabel_1_1);
		
		JLabel deptCount = new JLabel("");
		deptCount.setHorizontalAlignment(SwingConstants.CENTER);
		deptCount.setFont(new Font("Tahoma", Font.BOLD, 18));
		deptCount.setBounds(218, 89, 69, 67);
		panel_1_3.add(deptCount);
		
		deptCount.setText(deptList.size() + "");
		this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		BasicInternalFrameUI ui= (BasicInternalFrameUI)this.getUI();
		ui.setNorthPane(null);
		
		
		setBounds(0, 0, 976, 591);
	}
}
