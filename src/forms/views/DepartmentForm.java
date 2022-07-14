package forms.views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

import entities.Department;
import services.DepartmentService;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

public class DepartmentForm extends JInternalFrame {
	private JTextField deptField;
	private DepartmentService departmentService;
	private Department dept;
	private JTable tbldept;
	private DefaultTableModel dtm=new DefaultTableModel();
	private List<Department> deptList=new ArrayList();
	private List<Department> filtereddeptList=new ArrayList();
	private JTextField txtSearch;
	private JTextField txtdeptSearch;
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
	
	private void resetFormData() {
		deptField.setText("");
		
	}
	
	private void setTableDesign() {
		dtm.addColumn("DepartmentID");
		dtm.addColumn("DepartmentName");
		this.tbldept.setModel(dtm);
	}
	
	
	
	
	public DepartmentForm() {
		initialize();
		initializeDependency();
		this.setTableDesign();
		this.loadAllDepartment(Optional.empty());
		
		
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		
		
		this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		BasicInternalFrameUI ui= (BasicInternalFrameUI)this.getUI();
		ui.setNorthPane(null);
		
		
		setBounds(0, 0, 976, 591);

	}
	
	private void initializeDependency() {
		// TODO Auto-generated method stub
		this.departmentService=new DepartmentService();
	}
	
	private void loadAllDepartment(Optional<List<Department>> optionalDepartments) {
		this.dtm = (DefaultTableModel) this.tbldept.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();
		
		this.deptList=this.departmentService.findAllDepartments();
		this.filtereddeptList=optionalDepartments.orElseGet(()->this.deptList)
				.stream().collect(Collectors.toList());
	
            
			filtereddeptList.forEach(e-> {
            	Object[] row =new Object [2]; 
            			row[0]=e.getDepartmentId();
            			row[1]=e.getDepartmentName();
            	
            	dtm.addRow(row);
            });
			this.tbldept.setModel(dtm);
       
	}
	
	public void initialize() {
			
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 23, 540, 459);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Department Management", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dept.setDepartmentName(deptField.getText());
				departmentService.updateDepartment(String.valueOf(dept.getDepartmentId()), dept);
				resetFormData();
				loadAllDepartment(Optional.empty());
			}
		});
		btnEdit.setForeground(Color.BLACK);
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEdit.setBounds(338, 264, 89, 47);
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!deptField.getText().isEmpty()) {
					departmentService.deleteDepartment(String.valueOf(dept.getDepartmentId()));
					resetFormData();
					loadAllDepartment(Optional.empty());
				}
			}
		});
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDelete.setBounds(226, 264, 89, 47);
		panel.add(btnDelete);
		
		deptField = new JTextField();
		deptField.setBounds(226, 124, 264, 47);
		panel.add(deptField);
		deptField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(112, 265, 89, 47);
		panel.add(btnSave);
		JLabel lblDept = new JLabel("Department");
		lblDept.setBounds(57, 122, 143, 47);
		panel.add(lblDept);
		lblDept.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDept.setHorizontalAlignment(SwingConstants.LEFT);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Department dept=new Department();
				if(!deptField.getText().isEmpty()) {
					dept.setDepartmentName(deptField.getText());
					departmentService.createDepartment(dept);
					loadAllDepartment(Optional.empty());
					resetFormData();
				}else {
					JOptionPane.showMessageDialog(null,"Enter required field", "error", 0);
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(582, 72, 384, 410);
		this.getContentPane().add(scrollPane);
		
		tbldept = new JTable();
		tbldept.setFont(new Font("Tahoma",Font.PLAIN,15));
		scrollPane.setViewportView(tbldept);
		
		txtdeptSearch = new JTextField();
		txtdeptSearch.setBounds(582, 23, 285, 25);
		this.getContentPane().add(txtdeptSearch);
		txtdeptSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String keyword=txtdeptSearch.getText();
				loadAllDepartment(
						Optional.of(deptList.stream()
								.filter(dept->dept.getDepartmentName().toLowerCase()
								.startsWith(keyword.toLowerCase()))
								.collect(Collectors.toList()))
								);
			}
		});
		btnSearch.setForeground(Color.BLACK);
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearch.setBounds(877, 22, 89, 25);
		this.getContentPane().add(btnSearch);
		
		this.tbldept.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!tbldept.getSelectionModel().isSelectionEmpty()) {

					String id = tbldept.getValueAt(tbldept.getSelectedRow(), 0).toString();

					dept = departmentService.finddepartmentById(id);

					deptField.setText(dept.getDepartmentName());
					

				}
			}
		});
	}
	
}
