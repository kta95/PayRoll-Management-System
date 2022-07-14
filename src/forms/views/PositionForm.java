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
import entities.Position;
import services.DepartmentService;
import services.PositionService;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class PositionForm extends JInternalFrame {
	private JTextField positionField;
	private PositionService PositionService;
	private Position position;
	private JTable tblposition;
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<Position> positionList = new ArrayList<>();
	private List<Position> filteredpositionList;
	private JTextField txtSearch;
	private JTextField txtpositionSearch;
	private JTextField basicSalaryField;
    private List<Department> deptList;
    private DepartmentService departmentService;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PositionForm frame = new PositionForm();
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
		positionField.setText("");
		basicSalaryField.setText("");

	}
	
	private void setTableDesign() {
		dtm.addColumn("PositionID");
		dtm.addColumn("Position");
		dtm.addColumn("Basic Salary");
//		dtm.addColumn("Department");
		this.tblposition.setModel(dtm);
	}
	

	
	public PositionForm() {
		initialize();
		initializeDependency();
		this.setTableDesign();
		this.loadAllPosition(Optional.empty());	
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
			
		this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		BasicInternalFrameUI ui= (BasicInternalFrameUI)this.getUI();
		ui.setNorthPane(null);	
		setBounds(0, 0, 976, 591);

	}
	
	private void initializeDependency() {
		// TODO Auto-generated method stub
		this.PositionService = new PositionService();
		this.departmentService = new DepartmentService();
		this.filteredpositionList = new ArrayList<>();
	}
	
	private void loadAllPosition(Optional<List<Position>> optionalPositions) {
		this.dtm = (DefaultTableModel) this.tblposition.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();
		
		this.positionList=this.PositionService.findAllPositions();
		this.filteredpositionList=optionalPositions.orElseGet(()->this.positionList)
				.stream().collect(Collectors.toList());
	
            
			filteredpositionList.forEach(e-> {
            	Object[] row =new Object [5]; 
            			row[0] = e.getpId();
            			row[1] = e.getTitle();
            			row[2] = e.getBasicSalary();
  ///          			row[3] = e.getDepartment().getDepartmentName();
            	dtm.addRow(row);
            });
			this.tblposition.setModel(dtm);
       
	}
	
	private void setPositionDataFromForm(Position position) {
		position.setTitle(positionField.getText());
		position.setBasicSalary(Double.valueOf(basicSalaryField.getText().isBlank()? "0" : basicSalaryField.getText()));

	}
	
	public void initialize() {
			
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 23, 540, 459);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Position Management", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setPositionDataFromForm(position);
				PositionService.updatePosition(String.valueOf(position.getpId()), position);
				resetFormData();
				loadAllPosition(Optional.empty());
			}
		});
		btnEdit.setForeground(Color.BLACK);
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEdit.setBounds(367, 305, 123, 47);
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!positionField.getText().isEmpty()) {
					PositionService.deletePosition(String.valueOf(position.getpId()));
					resetFormData();
					loadAllPosition(Optional.empty());
				}
			}
		});
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDelete.setBounds(210, 305, 123, 47);
		panel.add(btnDelete);
		
		positionField = new JTextField();
		positionField.setBounds(226, 109, 264, 36);
		panel.add(positionField);
		positionField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(51, 306, 123, 47);
		panel.add(btnSave);
		JLabel lblposition = new JLabel("Position");
		lblposition.setBounds(51, 102, 143, 47);
		panel.add(lblposition);
		lblposition.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblposition.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblBasicSalary = new JLabel("Basic Salary");
		lblBasicSalary.setHorizontalAlignment(SwingConstants.LEFT);
		lblBasicSalary.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBasicSalary.setBounds(51, 160, 143, 47);
		panel.add(lblBasicSalary);
		
		basicSalaryField = new JTextField();
		basicSalaryField.setColumns(10);
		basicSalaryField.setBounds(226, 167, 264, 36);
		panel.add(basicSalaryField);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Position position=new Position();
				if(!positionField.getText().isEmpty()) {
					setPositionDataFromForm(position);
					PositionService.createPosition(position);
					loadAllPosition(Optional.empty());
					resetFormData();
				}else {
					JOptionPane.showMessageDialog(null,"Enter required field", "error", 0);
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(582, 72, 384, 410);
		this.getContentPane().add(scrollPane);
		
		tblposition = new JTable();
		tblposition.setFont(new Font("Tahoma",Font.PLAIN,15));
		scrollPane.setViewportView(tblposition);
		
		txtpositionSearch = new JTextField();
		txtpositionSearch.setBounds(582, 23, 285, 25);
		this.getContentPane().add(txtpositionSearch);
		txtpositionSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String keyword=txtpositionSearch.getText();
				loadAllPosition(
						Optional.of(positionList.stream()
								.filter(position->position.getTitle().toLowerCase()
								.startsWith(keyword.toLowerCase()))
								.collect(Collectors.toList()))
								);
			}
		});
		btnSearch.setForeground(Color.BLACK);
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearch.setBounds(877, 22, 89, 25);
		this.getContentPane().add(btnSearch);
		
		// put data into fields when selected
		this.tblposition.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!tblposition.getSelectionModel().isSelectionEmpty()) {

					String id = tblposition.getValueAt(tblposition.getSelectedRow(), 0).toString();

					position = PositionService.findPositionById(id);

					positionField.setText(position.getTitle());
					basicSalaryField.setText(String.valueOf(position.getBasicSalary()));
//					Optional<Department> selectedDepartment = deptList.stream().filter(d -> d.getDepartmentName().equals(deptCombo.getSelectedItem())).findFirst();
				//	deptCombo.setSelectedIndex(position.getDepartment().getDepartmentId());
				}
			}
		});
	}
}
