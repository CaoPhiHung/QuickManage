package View;

import Controller.*;
import Model.*;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.*;

public class ReportChooseForm extends JFrame {

    private static ReportChooseForm unique;
    JLabel instruction;
    private JComboBox<String> monthCombo, yearCombo;
    private JButton chooseButton, cancelButton;
    private ReportChooseController reportChooseCon = new ReportChooseController();
    private boolean added = false;
    private ResourceBundle resources;
    //Singleton

    public static ReportChooseForm getInstance() {
        if (unique == null) {
            unique = new ReportChooseForm();
        }
        return unique;
    }

    public void initialize() {
        instruction = new JLabel("Choose month and year");
        chooseButton = new JButton("Choose");
        cancelButton = new JButton("Cancel");
        // label settings
        instruction.setBounds(60, 10, 300, 20);

        // button settings
        chooseButton.setBounds(40, 80, 80, 30);
        cancelButton.setBounds(140, 80, 80, 30);

        // combo box settings
        monthCombo = new JComboBox<>();
        monthCombo.setBounds(40, 40, 100, 30);
        yearCombo = new JComboBox<>();
        yearCombo.setBounds(150, 40, 70, 30);

        if (!added) {
            if (Data.getCurrentUser() instanceof Manager) {
                monthCombo.addItem("January");
                monthCombo.addItem("February");
                monthCombo.addItem("March");
                monthCombo.addItem("April");
                monthCombo.addItem("May");
                monthCombo.addItem("June");
                monthCombo.addItem("July");
                monthCombo.addItem("August");
                monthCombo.addItem("September");
                monthCombo.addItem("October");
                monthCombo.addItem("November");
                monthCombo.addItem("December");

                yearCombo.addItem("2013");
                yearCombo.addItem("2014");
                yearCombo.addItem("2015");
                yearCombo.addItem("2016");
                yearCombo.addItem("2017");
                yearCombo.addItem("2018");
            }

            added = true;
        }
        setTitle("Choose Date");
        if (LanguageController.getInstance().isClicked()) {
            try {
                resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
                //Add Manager Form
                this.instruction.setText(resources.getString("monthYear"));
                this.chooseButton.setText(resources.getString("choose"));
                this.cancelButton.setText(resources.getString("Cancel"));
                setTitle(resources.getString("ChooseDate"));

            } catch (MissingResourceException mre) {
                System.err.println("MyData.properties not found");
            }
        }
        // add components
        add(instruction);
        add(monthCombo);
        add(yearCombo);
        add(chooseButton);
        add(cancelButton);
        chooseButton.addActionListener(reportChooseCon);
        cancelButton.addActionListener(reportChooseCon);
        this.addWindowListener(reportChooseCon);

        // frame settings

        setLayout(null);
        setSize(270, 150);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public JComboBox<String> getMonthCombo() {
        return monthCombo;
    }

    public void setMonthCombo(JComboBox<String> types) {
        this.monthCombo = types;
    }

    public JComboBox<String> getYearCombo() {
        return yearCombo;
    }

    public void setYearCombo(JComboBox<String> yearCombo) {
        this.yearCombo = yearCombo;
    }

    public static void setUnique(ReportChooseForm unique) {
        ReportChooseForm.unique = unique;
    }
}
