import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class StudentGUI extends JFrame {
    private JPanel mainPanel;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtAge;
    private JTextField txtEmail;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JTable studentTable;
    private JButton btnAdd;

    private StudentDAO dao = new StudentDAO();
    private DefaultTableModel tableModel;

    public StudentGUI() {
        setContentPane(mainPanel);
        setTitle("Student System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400); // Auto-sizes the window to fit your design

        // Initialize Table
        String[] columns = {"ID", "First Name", "Last Name", "Age", "Email"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Returning 'false' makes every cell in the table un-editable
                return false;
            }
        };

        studentTable.setModel(tableModel);

        // --- Button Logic ---

        btnAdd.addActionListener(e -> {
            // 1. Check if the Age box is empty or not a number
            String ageText = txtAge.getText().trim();

            if (ageText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Age field cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int age = Integer.parseInt(ageText);

                // 2. Now proceed with the database call
                Student s = new Student();
                s.setFirstName(txtFirstName.getText());
                s.setLastName(txtLastName.getText());
                s.setAge(age);
                s.setEmail(txtEmail.getText());

                dao.addStudent(s);
                refreshTable();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for Age.", "Format Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnUpdate.addActionListener(e -> {
            int row = studentTable.getSelectedRow();
            if (row != -1) {
                int id = (int) tableModel.getValueAt(row, 0); // Get ID from hidden table column
                Student s = new Student();
                s.setStudentId(id);
                s.setFirstName(txtFirstName.getText());
                s.setLastName(txtLastName.getText());
                s.setAge(Integer.parseInt(txtAge.getText()));
                s.setEmail(txtEmail.getText());
                dao.updateStudent(s);
                refreshTable();
            }
        });

        btnDelete.addActionListener(e -> {
            int row = studentTable.getSelectedRow();
            if (row != -1) {
                int id = (int) tableModel.getValueAt(row, 0);
                dao.deleteStudent(id);
                refreshTable();
            }
        });

        // Load initial data
        refreshTable();
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        List<Student> list = dao.getAllStudents();
        for (Student s : list) {
            tableModel.addRow(new Object[]{s.getStudentId(), s.getFirstName(), s.getLastName(), s.getAge(), s.getEmail()});
        }
    }

    public static void main(String[] args) {
        StudentGUI gui = new StudentGUI();
        gui.setVisible(true);
    }
}