package View;

import Controller.*;
import Model.*;
import java.util.*;
import javax.swing.*;

public class ExportOptions extends JFrame {

    private static ExportOptions unique;
    private JComboBox<String> types;
    protected JButton addButton = new JButton("Choose");
    protected JButton cancelButton = new JButton("Cancel");
    private boolean added = false;
    protected ExportController exCon = new ExportController();
    private JLabel instruction = new JLabel("Choose a type to Export");
    private String[] comboBoxContentsForManager = new String[]{"Manager", "Staff", "Class", "Teacher", "Student"};
    private String[] comboBoxContentsForStaff = new String[]{"Staff", "Class", "Teacher", "Student"};
    private ResourceBundle resources;

    public ExportOptions() {
        this.types = new JComboBox<>();
    }

    public static ExportOptions getInstance() {
        if (unique == null) {
            unique = new ExportOptions();
        }
        return unique;
    }

    public void initialize() {

//        if (Data.getCurrentUser() instanceof Manager) {
//            types = new JComboBox(comboBoxContentsForManager);
//        } else {
//            types = new JComboBox(comboBoxContentsForStaff);
//        }

        // label settings
        instruction.setBounds(85, 10, 300, 20);

        // button settings
        addButton.setBounds(50, 80, 80, 30);
        cancelButton.setBounds(160, 80, 80, 30);

        // combo box settings
        types.setBounds(95, 40, 100, 30);
        setTitle("Export CSV File");

        if (LanguageController.getInstance().isClicked()) {

            try {
                resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
                if (Data.getCurrentUser() instanceof Manager) {
                    types.addItem(resources.getString("Manager"));
                    types.addItem(resources.getString("Staff"));
                    types.addItem(resources.getString("Teacher"));
                    types.addItem(resources.getString("Student"));
                    types.addItem(resources.getString("Class"));
                    types.addItem(resources.getString("Invoice"));
                } else if (Data.getCurrentUser() instanceof Staff) {
                    types.addItem(resources.getString("Staff"));
                    types.addItem(resources.getString("Teacher"));
                    types.addItem(resources.getString("Student"));
                    types.addItem(resources.getString("Class"));
                    types.addItem(resources.getString("Invoice"));
                }
                this.addButton.setText(resources.getString("Add2"));
                this.cancelButton.setText(resources.getString("Cancel"));
                this.instruction.setText(resources.getString("chooseExportFile"));
                this.setTitle(resources.getString("exportCSV"));
            } catch (MissingResourceException mre) {
                System.err.println("MyData.properties not found");
            }
        } else {
            if (!added) {
                if (Data.getCurrentUser() instanceof Manager) {
                    types.addItem("Manager");
                }
                types.addItem("Staff");
                types.addItem("Teacher");
                types.addItem("Student");
                types.addItem("Class");
                types.addItem("Invoice");
                added = true;
            }
        }


        // add components
        add(instruction);
        add(types);
        add(addButton);
        addButton.addActionListener(exCon);

        add(cancelButton);
        cancelButton.addActionListener(exCon);
        this.addWindowListener(exCon);

        // frame settings

        setLayout(null);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        //Set look and feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Can't change look and feel", "Invalid PLAF",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static ExportOptions getUnique() {
        return unique;
    }

    public static void setUnique(ExportOptions unique) {
        ExportOptions.unique = unique;
    }

    public JComboBox<String> getTypes() {
        return types;
    }

    public void setTypes(JComboBox<String> types) {
        this.types = types;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

    public ExportController getExCon() {
        return exCon;
    }

    public void setExCon(ExportController exCon) {
        this.exCon = exCon;
    }

    public JLabel getInstruction() {
        return instruction;
    }

    public void setInstruction(JLabel instruction) {
        this.instruction = instruction;
    }

    public String[] getComboBoxContentsForManager() {
        return comboBoxContentsForManager;
    }

    public void setComboBoxContentsForManager(String[] comboBoxContentsForManager) {
        this.comboBoxContentsForManager = comboBoxContentsForManager;
    }

    public String[] getComboBoxContentsForStaff() {
        return comboBoxContentsForStaff;
    }

    public void setComboBoxContentsForStaff(String[] comboBoxContentsForStaff) {
        this.comboBoxContentsForStaff = comboBoxContentsForStaff;
    }

    public ResourceBundle getResources() {
        return resources;
    }

    public void setResources(ResourceBundle resources) {
        this.resources = resources;
    }
}
