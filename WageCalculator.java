import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class WageCalculator extends JFrame {
    private JComboBox<String> employeeTypeCombo;
    private JTextField nameField;
    private JTextField wageField;
    private JTextField hoursField;
    private JTextField salaryField;
    private JButton submitButton;
    private JButton showReportButton;
    private JTextArea txtOutput;
    private ArrayList<Employee> employeeList;

    public WageCalculator() {
        setTitle("Wage Calculator");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));

        JLabel employeeTypeLabel = new JLabel("Employee Type:");
        employeeTypeCombo = new JComboBox<>(new String[]{"Select", "Hourly", "Salary"});
        inputPanel.add(employeeTypeLabel);
        inputPanel.add(employeeTypeCombo);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JLabel wageLabel = new JLabel("Hourly Wage:");
        wageField = new JTextField();
        inputPanel.add(wageLabel);
        inputPanel.add(wageField);

        JLabel hoursLabel = new JLabel("Hours Worked:");
        hoursField = new JTextField();
        inputPanel.add(hoursLabel);
        inputPanel.add(hoursField);

        JLabel salaryLabel = new JLabel("Yearly Salary:");
        salaryField = new JTextField();
        inputPanel.add(salaryLabel);
        inputPanel.add(salaryField);

        add(inputPanel, BorderLayout.NORTH);

        txtOutput = new JTextArea(10, 20);
        JScrollPane scrollPane = new JScrollPane(txtOutput);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());
        submitButton.setEnabled(false);
        buttonPanel.add(submitButton);

        showReportButton = new JButton("Show Report");
        showReportButton.addActionListener(new ShowReportButtonListener());
        showReportButton.setEnabled(false);
        buttonPanel.add(showReportButton);

        add(buttonPanel, BorderLayout.SOUTH);

        nameField.setEnabled(false);
        wageField.setEnabled(false);
        hoursField.setEnabled(false);
        salaryField.setEnabled(false);

        employeeList = new ArrayList<>();

        employeeTypeCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedType = (String) employeeTypeCombo.getSelectedItem();
                    if (selectedType.equals("Select")) {
                        nameField.setEnabled(false);
                        wageField.setEnabled(false);
                        hoursField.setEnabled(false);
                        salaryField.setEnabled(false);
                        submitButton.setEnabled(false);
                        showReportButton.setEnabled(false);
                    } else {
                        nameField.setEnabled(true);
                        submitButton.setEnabled(true);
                        showReportButton.setEnabled(true);
                        if (selectedType.equals("Hourly")) {
                            wageField.setEnabled(true);
                            hoursField.setEnabled(true);
                            salaryField.setEnabled(false);
                        } else if (selectedType.equals("Salary")) {
                            wageField.setEnabled(false);
                            hoursField.setEnabled(false);
                            salaryField.setEnabled(true);
                        }
                    }
                }
            }
        });
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Validate inputs
            String selectedType = (String) employeeTypeCombo.getSelectedItem();

            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter the employee name", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double wage = 0;
            double hours = 0;
            double salary = 0;

            if (selectedType.equals("Hourly")) {
                String wageStr = wageField.getText().trim();
                String hoursStr = hoursField.getText().trim();

                if (wageStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the hourly wage", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (hoursStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the hours worked", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    wage = Double.parseDouble(wageStr);
                    hours = Double.parseDouble(hoursStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numeric values for wage and hours", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if (selectedType.equals("Salary")) {
                String salaryStr = salaryField.getText().trim();

                if (salaryStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the yearly salary", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    salary = Double.parseDouble(salaryStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid numeric value for salary", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Create and submit the employee object
            Employee employee;
            if (selectedType.equals("Hourly")) {
                employee = new HourlyEmployee(name, wage, hours);
            } else {
                employee = new SalaryEmployee(name, salary);
            }

            employee.generatePayroll();
            employeeList.add(employee);
            txtOutput.setText(""); 
            txtOutput.append(employee.toString() + "\n");

            // Reset fields
            nameField.setText("");
            wageField.setText("");
            hoursField.setText("");
            salaryField.setText("");
            employeeTypeCombo.setSelectedIndex(0);
            
            showReportButton.setEnabled(true);
        }
    }

    private class ShowReportButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Clear the text area
            txtOutput.setText("");
            
            // Display the report
            for (Employee employee : employeeList) {
                txtOutput.append(employee.toString() +  "\n\n");
            }
        }
    }

    public static void main(String[] args) {
            WageCalculator wageCalculator = new WageCalculator();
            wageCalculator.setVisible(true);
       
    }
}
