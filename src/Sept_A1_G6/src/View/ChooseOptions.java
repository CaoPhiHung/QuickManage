package View;

import Controller.*;
import Model.*;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.*;

public class ChooseOptions extends JFrame {

    private static ChooseOptions unique;
    private JComboBox<String> types;
    private JButton addButton = new JButton("Choose");
    private JButton cancelButton = new JButton("Cancel");
    private boolean added = false;
    private ChooseOptionsController opCon = new ChooseOptionsController();
    private JLabel instruction = new JLabel("Choose a type to add");
    private String[] comboBoxContentsForManager = new String[]{"Manager", "Staff", "Class", "Teacher", "Student", "Class Type"};
    private String[] comboBoxContentsForStaff = new String[]{"Staff", "Class", "Teacher", "Student"};
    ResourceBundle resources;

    public ChooseOptions() {
        this.types = new JComboBox<>();
    }

    public static ChooseOptions getInstance() {
        if (unique == null) {
            unique = new ChooseOptions();
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
        setTitle("Choose Type");


        if (LanguageController.getInstance().isClicked()) {

           // try {
                resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
                if (Data.getCurrentUser() instanceof Manager) {
                    types.addItem(resources.getString("Manager"));
                    types.addItem(resources.getString("Staff"));
                    types.addItem(resources.getString("Teacher"));
                    types.addItem(resources.getString("Student"));
                    types.addItem(resources.getString("Class"));
                    types.addItem(resources.getString("ClassType"));
                    types.addItem("Room");

                } else if (Data.getCurrentUser() instanceof Staff) {
                    types.addItem(resources.getString("Staff"));
                    types.addItem(resources.getString("Teacher"));
                    types.addItem(resources.getString("Student"));
                    types.addItem(resources.getString("Class"));
                    types.addItem("Room");
                }
                addButton.setText(resources.getString("Add1"));
                cancelButton.setText(resources.getString("Cancel"));
                instruction.setText(resources.getString("Instruction"));
                setTitle(resources.getString("ChooseType"));
//            } catch (MissingResourceException mre) {
//                System.err.println("MyData.properties not found");
//            }
        } else {
            if (!added) {
                if (Data.getCurrentUser() instanceof Manager) {
                    types.addItem("Manager");
                    types.addItem("Class Type");
                }
                types.addItem("Staff");
                types.addItem("Teacher");
                types.addItem("Student");
                types.addItem("Class");
                types.addItem("Room");
                added = true;
            }
        }


        // add components
        add(instruction);
        add(types);
        add(addButton);
        addButton.addActionListener(opCon);

        add(cancelButton);
        cancelButton.addActionListener(opCon);
        this.addWindowListener(opCon);

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

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JComboBox<String> getTypes() {
        return types;
    }

    public void setTypes(JComboBox<String> types) {
        this.types = types;
    }

    public JLabel getInstruction() {
        return instruction;
    }

    public void setInstruction(JLabel instruction) {
        this.instruction = instruction;
    }

    public static ChooseOptions getUnique() {
        return unique;
    }

    public static void setUnique(ChooseOptions unique) {
        ChooseOptions.unique = unique;
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
}
