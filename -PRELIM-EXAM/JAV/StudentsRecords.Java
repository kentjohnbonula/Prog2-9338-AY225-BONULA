/* Programmer Identifier: [YOUR FULL NAME] [YOUR STUDENT ID] */
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentsRecords extends JFrame {

    private DefaultTableModel model;
    private JTable table;
    private JTextField txtId, txtName, txtGrade;

    public StudentsRecords() {
        this.setTitle("Records - [YOUR NAME] [YOUR STUDENT ID]");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Table Setup
        String[] columns = {"ID", "Name", "Grade"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        this.add(new JScrollPane(table), BorderLayout.CENTER);

        // Input Panel
        JPanel inputPanel = new JPanel();
        txtId = new JTextField(5);
        txtName = new JTextField(10);
        txtGrade = new JTextField(5);
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");

        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(txtId);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("Grade:"));
        inputPanel.add(txtGrade);
        inputPanel.add(btnAdd);
        inputPanel.add(btnDelete);
        
        this.add(inputPanel, BorderLayout.SOUTH);

        // ADD Action
        btnAdd.addActionListener(e -> {
            String id = txtId.getText().trim();
            String name = txtName.getText().trim();
            String grade = txtGrade.getText().trim();

            if(!id.isEmpty() && !name.isEmpty() && !grade.isEmpty()) {
                model.addRow(new Object[]{id, name, grade});
                txtId.setText(""); txtName.setText(""); txtGrade.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            }
        });

        // DELETE Action
        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Select a row to delete.");
            }
        });
    }

    // THE MAIN METHOD (This makes it run!)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentsRecords().setVisible(true);
        });
    }
}