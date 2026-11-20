/**
 * Author: Lon Smith, Ph.D.
 * Description: This is the framework for the database program. Additional requirements and functionality
 *    are to be built by you and your group.
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

// Prabin's part: imports for the database
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class EmployeeSearchFrame extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDatabase;
	private JCheckBox chckbxNotDept;
	private JCheckBox chckbxNotProject;
	private JList<String> lstDepartment;
	private DefaultListModel<String> department = new DefaultListModel<String>();
	private JList<String> lstProject;
	private DefaultListModel<String> project = new DefaultListModel<String>();
	private JTextArea textAreaEmployee;

        // Prabin's part: database connection and the real ID numbers behind the lists.
        // deptIds.get(i) is the Dnumber of the department shown at index i in lstDepartment.
        // projIds.get(i) is the Pnumber of the project shown at index i in lstProject.
        private Connection conn;
        private final List<Integer> deptIds = new ArrayList<Integer>();
        private final List<Integer> projIds = new ArrayList<Integer>();
        private static final String URL_PREFIX = "jdbc:mysql://localhost:3306/";
        private static final String USER = "projuser";
        // The password is read from an environment variable so it is never committed to GitHub.
        private static final String PASS =
                System.getenv("DB_PASS") == null ? "" : System.getenv("DB_PASS");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeSearchFrame frame = new EmployeeSearchFrame();
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
	public EmployeeSearchFrame() {
		setTitle("Employee Search");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 347);
		setMinimumSize(new java.awt.Dimension(450, 347));
		setResizable(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Database:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel.setBounds(21, 23, 59, 14);
		contentPane.add(lblNewLabel);
		
		txtDatabase = new JTextField();
		txtDatabase.setBounds(90, 20, 193, 20);
		contentPane.add(txtDatabase);
		txtDatabase.setColumns(10);
		
		JButton btnDBFill = new JButton("Fill");
		/**
		 * The btnDBFill should fill the department and project JList with the 
		 * departments and projects from your entered database name.
		 */

		btnDBFill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fill();
			}
		});

		btnDBFill.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnDBFill.setBounds(307, 19, 68, 23);
		contentPane.add(btnDBFill);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblDepartment.setBounds(52, 63, 89, 14);
		contentPane.add(lblDepartment);
		
		JLabel lblProject = new JLabel("Project");
		lblProject.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblProject.setBounds(255, 63, 47, 14);
		contentPane.add(lblProject);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(225, 84, 150, 42);
		contentPane.add(scrollPane);
		
		lstProject = new JList<String>(new DefaultListModel<String>());
		scrollPane.setViewportView(lstProject);
		lstProject.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lstProject.setModel(project);
		
		chckbxNotDept = new JCheckBox("Not");
		chckbxNotDept.setBounds(71, 133, 59, 23);
		contentPane.add(chckbxNotDept);
		
		chckbxNotProject = new JCheckBox("Not");
		chckbxNotProject.setBounds(270, 133, 59, 23);
		contentPane.add(chckbxNotProject);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(36, 84, 172, 40);
		contentPane.add(scrollPane_1);
		
		lstDepartment = new JList<String>(new DefaultListModel<String>());
		scrollPane_1.setViewportView(lstDepartment);
		lstDepartment.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lstDepartment.setModel(department);
		
		JLabel lblEmployee = new JLabel("Employee");
		lblEmployee.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblEmployee.setBounds(52, 179, 89, 14);
		contentPane.add(lblEmployee);
		

JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});

		btnSearch.setBounds(80, 276, 89, 23);
		contentPane.add(btnSearch);
		


		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});


		btnClear.setBounds(236, 276, 89, 23);
		contentPane.add(btnClear);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(36, 197, 339, 68);
		contentPane.add(scrollPane_2);
		
		textAreaEmployee = new JTextArea();
		scrollPane_2.setViewportView(textAreaEmployee);


		textAreaEmployee.setEditable(false);
		textAreaEmployee.setText("Click Fill to load departments and projects.");
	}

	// TODO Member 3: replace this sample output with database-backed search.
	private void search() {
		textAreaEmployee.setText("John Smith\nFranklin Wong");
	}

        // Prabin's part: connect to the database named in txtDatabase and fill both lists.
        private void fill() {
                String dbName = txtDatabase.getText().trim();
                try {
                        if (dbName.isEmpty()) {
                                throw new SQLException("Database name is empty");
                        }

                        // Close any previous connection, then open the new one
                        if (conn != null) {
                                conn.close();
                                conn = null;
                        }
                        conn = DriverManager.getConnection(URL_PREFIX + dbName, USER, PASS);

                        // Read everything into temporary lists first
                        List<Integer> newDeptIds = new ArrayList<Integer>();
                        List<String> newDeptNames = new ArrayList<String>();
                        List<Integer> newProjIds = new ArrayList<Integer>();
                        List<String> newProjNames = new ArrayList<String>();

                        try (Statement s = conn.createStatement();
                             ResultSet rs = s.executeQuery(
                                     "SELECT Dnumber, Dname FROM DEPARTMENT ORDER BY Dname")) {
                                while (rs.next()) {
                                        newDeptIds.add(rs.getInt(1));
                                        newDeptNames.add(rs.getString(2));
                                }
                        }
                        try (Statement s = conn.createStatement();
                             ResultSet rs = s.executeQuery(
                                     "SELECT Pnumber, Pname FROM PROJECT ORDER BY Pname")) {
                                while (rs.next()) {
                                        newProjIds.add(rs.getInt(1));
                                        newProjNames.add(rs.getString(2));
                                }
                        }

                        // Everything loaded, so now reset the window (Fill = full reset)
                        department.clear();
                        project.clear();
                        deptIds.clear();
                        projIds.clear();
                        for (int i = 0; i < newDeptIds.size(); i++) {
                                deptIds.add(newDeptIds.get(i));
                                department.addElement(newDeptNames.get(i));
                        }
                        for (int i = 0; i < newProjIds.size(); i++) {
                                projIds.add(newProjIds.get(i));
                                project.addElement(newProjNames.get(i));
                        }
                        lstDepartment.clearSelection();
                        lstProject.clearSelection();
                        textAreaEmployee.setText("");
                        chckbxNotDept.setSelected(false);
                        chckbxNotProject.setSelected(false);

                } catch (SQLException ex) {
                        if (conn != null) {
                                try { conn.close(); } catch (SQLException ignored) { }
                        }
                        conn = null;
                        department.clear();
                        project.clear();
                        deptIds.clear();
                        projIds.clear();
                        textAreaEmployee.setText("");
                        JOptionPane.showMessageDialog(this,
                                "The database could not be opened.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                }
        }


	private void clear() {
		textAreaEmployee.setText("");
		lstDepartment.clearSelection();
		lstProject.clearSelection();
		chckbxNotDept.setSelected(false);
		chckbxNotProject.setSelected(false);
	}


}

