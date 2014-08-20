package Controller;

import Model.*;
import View.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class LanguageController implements ActionListener {

    private static LanguageController unique;
    private DefaultComboBoxModel model;
    private static String databaseName = "MyData_en";
    private static boolean clicked;
    ResourceBundle resources;

    public static LanguageController getInstance() {
        if (unique == null) {
            unique = new LanguageController();
        }
        return unique;
    }

    public LanguageController() {
        this.databaseName = getDatabaseName();
        this.clicked = isClicked();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem but = (JMenuItem) e.getSource();
        model = new DefaultComboBoxModel();
        if (but.getText().equalsIgnoreCase("English") || but.getText().equalsIgnoreCase("Tiếng Anh")) {
            databaseName = "MyData_en";
        } else if (but.getText().equalsIgnoreCase("Vietnamese") || but.getText().equalsIgnoreCase("Tiếng Việt")) {
            databaseName = "MyData_vi";
        }
        try {
            resources = ResourceBundle.getBundle(databaseName);
            //Login Form
            LoginForm.getInstance().getUsernameL().setText(resources.getString("Username"));
            LoginForm.getInstance().getPasswordL().setText(resources.getString("Password"));
            LoginForm.getInstance().getLogin().setText(resources.getString("Login"));
            LoginForm.getInstance().getExit().setText(resources.getString("Exit"));
            LoginForm.getInstance().getManage().setText(resources.getString("Manage"));
            LoginForm.getInstance().getLanguage().setText(resources.getString("Language"));
            LoginForm.getInstance().getClose().setText(resources.getString("Close"));
            LoginForm.getInstance().getEnglish().setText(resources.getString("English"));
            LoginForm.getInstance().getVietnamese().setText(resources.getString("Vietnamese"));

            //comboBox user Type
            String[] manager = new String[]{"Quản Lí", "Nhân Viên", "Giáo Viên", "Học Sinh", "Lớp"};
            String[] staff = new String[]{"Nhân Viên", "Giáo Viên", "Học Sinh", "Lớp"};
            ControlPanelForm.getInstance().setComboBoxContentsForManager(manager);
            ControlPanelForm.getInstance().setComboBoxContentsForStaff(staff);
            if (Data.getCurrentUser() instanceof Manager) {
                model.addElement(resources.getString("Manager"));
                model.addElement(resources.getString("Staff"));
                model.addElement(resources.getString("Teacher"));
                model.addElement(resources.getString("Student"));
                model.addElement(resources.getString("Class"));
                model.addElement(resources.getString("ClassType"));
                ControlPanelForm.getInstance().getUserType().setModel(model);
            } else if (Data.getCurrentUser() instanceof Staff) {
                model.addElement(resources.getString("Staff"));
                model.addElement(resources.getString("Teacher"));
                model.addElement(resources.getString("Student"));
                model.addElement(resources.getString("Class"));
                ControlPanelForm.getInstance().getUserType().setModel(model);
            }

            //Control Panel Form
            ControlPanelForm.getInstance().getAddButton().setText(resources.getString("Add"));
            ControlPanelForm.getInstance().getDeleteButton().setText(resources.getString("Delete"));
            ControlPanelForm.getInstance().getActivateButton().setText(resources.getString("Activate"));
            ControlPanelForm.getInstance().getTimeTableButton().setText(resources.getString("TimeTable"));
            ControlPanelForm.getInstance().getCopyClassButton().setText(resources.getString("CopyClass"));
            ControlPanelForm.getInstance().getReportButton().setText(resources.getString("Report"));

            //menu bar of control panel form
            ControlPanelForm.getInstance().getFile().setText(resources.getString("File"));
            ControlPanelForm.getInstance().getLogout().setText(resources.getString("Logout"));
            ControlPanelForm.getInstance().getExit().setText(resources.getString("Exit"));
            ControlPanelForm.getInstance().getLanguage().setText(resources.getString("Language"));
            ControlPanelForm.getInstance().getEnglish().setText(resources.getString("English"));
            ControlPanelForm.getInstance().getVietnamese().setText(resources.getString("Vietnamese"));
            ControlPanelForm.getInstance().getHelp().setText(resources.getString("Help"));
            ControlPanelForm.getInstance().getUserGuide().setText(resources.getString("UserGuide"));
            ControlPanelForm.getInstance().getSearchButton().setText(resources.getString("Search"));
            //
            ControlPanelForm.getInstance().getHotKeys().setText(resources.getString("hotKeys"));
            ControlPanelForm.getInstance().getAddHot().setText(resources.getString("addHot"));
            ControlPanelForm.getInstance().getDeleteHot().setText(resources.getString("deleteHot"));
            ControlPanelForm.getInstance().getActiveHot().setText(resources.getString("activeHot"));
            ControlPanelForm.getInstance().getCopyClassHot().setText(resources.getString("copyClassHot"));
            ControlPanelForm.getInstance().getAllTimeTableHot().setText(resources.getString("allTimeTableHot"));
            ControlPanelForm.getInstance().getReportHot().setText(resources.getString("reportHot"));
            ControlPanelForm.getInstance().getImportCSV().setText(resources.getString("importCSV"));
            ControlPanelForm.getInstance().getExportCSV().setText(resources.getString("exportCSV"));

        } catch (MissingResourceException mre) {
            System.err.println("MyData.properties not found");
        }
        clicked = true;
    }

    public static boolean isClicked() {
        return clicked;
    }

    public static String getDatabaseName() {
        return databaseName;
    }

    public ResourceBundle getResources() {
        return resources;
    }
}
