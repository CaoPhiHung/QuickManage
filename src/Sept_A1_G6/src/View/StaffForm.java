package View;

import Controller.LanguageController;
import Controller.StaffFormController;
import Model.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class StaffForm extends UserForm implements Observer {

    private static StaffForm unique;
    private StaffFormController staffCon = new StaffFormController();
    private JLabel userL = new JLabel("Username: ");
    private JLabel passL = new JLabel("Password: ");
    private JLabel retypeL = new JLabel("Retype password: ");
    private JTextField userR = new JTextField(30);
    private JPasswordField passR = new JPasswordField(30);
    private JPasswordField retypeR = new JPasswordField(30);
    private int numberOfFields = 9;
    private ResourceBundle resources;

    public int getNumberOfFields() {
        return numberOfFields;
    }

    public static StaffForm getInstance() {
        if (unique == null) {
            unique = new StaffForm();
        }
        return unique;
    }

    public void staffInitialize() {

        staffCon.addObserver(this);
        //Call super
        super.initialize();
        if (Data.staffList.isEmpty()) {
            this.idR.setText("T1");
        } else {
            String temp = Data.staffList.get(Data.staffList.size() - 1).getID();
            int num = Integer.parseInt(temp.substring(1));
            num++;
            this.idR.setText("T" + num);
        }

        //Settings
        this.setUserType("Staff");
        setTitle("Add Staff Form");
        if (LanguageController.getInstance().isClicked()) {
            try {
                resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
                //Add Staff Form
                StaffForm.getInstance().getId().setText(resources.getString("ID"));
                StaffForm.getInstance().getFirstname().setText(resources.getString("FirstName"));
                StaffForm.getInstance().getLastname().setText(resources.getString("LastName"));
                StaffForm.getInstance().getDob().setText(resources.getString("DOB"));
                StaffForm.getInstance().getGender().setText(resources.getString("Gender"));
                StaffForm.getInstance().getHomeNum().setText(resources.getString("HomeNum"));
                StaffForm.getInstance().getPhoneNum().setText(resources.getString("PhoneNum"));
                StaffForm.getInstance().getEmail().setText(resources.getString("Email"));
                StaffForm.getInstance().getAddress().setText(resources.getString("Address"));
                StaffForm.getInstance().getStatus().setText(resources.getString("Status"));
                StaffForm.getInstance().getUserL().setText(resources.getString("Username"));
                StaffForm.getInstance().getPassL().setText(resources.getString("Password"));
                StaffForm.getInstance().getRetypeL().setText(resources.getString("RetypePassword"));
                this.setTitle(resources.getString("addStaffForm"));
            } catch (MissingResourceException mre) {
                System.err.println("MyData.properties not found");
            }
        }

        //Adding
        userForm.add(userL);
        userForm.add(passL);
        userForm.add(retypeL);
        userForm.add(userR);
        userForm.add(passR);
        userForm.add(retypeR);

        userL.setBounds(10, 450, 70, 25);
        passL.setBounds(10, 490, 70, 25);
        retypeL.setBounds(10, 530, 120, 25);
        userR.setBounds(110, 450, 170, 25);
        passR.setBounds(110, 490, 170, 25);
        retypeR.setBounds(110, 530, 170, 25);
        add.setBounds(110, 570, 80, 25);
        cancel.setBounds(200, 570, 80, 25);

        //Set actions
        add.addActionListener(staffCon);
        browse.addActionListener(staffCon);
        cancel.addActionListener(staffCon);
        this.addWindowListener(staffCon);

        //JFrame's settings 
        setSize(440, 630);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public JLabel getUserL() {
        return userL;
    }

    public JLabel getPassL() {
        return passL;
    }

    public JLabel getRetypeL() {
        return retypeL;
    }

    public JTextField getUserName() {
        return userR;
    }

    public void setUserName(JTextField userR) {
        this.userR = userR;
    }

    public JPasswordField getTypingPassword() {
        return passR;
    }

    public void setTypingPassword(JPasswordField passR) {
        this.passR = passR;
    }

    public JPasswordField getRetypePassword() {
        return retypeR;
    }

    public void setRetypePassword(JPasswordField retypeR) {
        this.retypeR = retypeR;
    }

    public static void setUnique(StaffForm unique) {
        StaffForm.unique = unique;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof boolean[]) {
            System.out.println("updating!!!!!!!!!!!!!!!!!!!!!!!!!");
            boolean[] results = (boolean[]) arg;

            // show message log if there's any invalid input
            for (int i = 0; i < results.length; i++) {
                if (results[i] == false) {
                    JOptionPane.showMessageDialog(null, "Invalid inputs ! Please check"
                            + " red input fields");
                    break;
                }
            }

            // check and highligh invalid input fields
            if (!results[DataValidation.FIRST_NAME]) {
                this.getFirstnameR().setBorder(BorderFactory.createLineBorder(Color.red));
                JOptionPane.showMessageDialog(null, "First name cannot be empty");
            } else {
                this.getFirstnameR().setBorder(BorderFactory.createLineBorder(Color.green));
            }

            if (!results[DataValidation.LAST_NAME]) {
                this.getLastnameR().setBorder(BorderFactory.createLineBorder(Color.red));
                JOptionPane.showMessageDialog(null, "Last name cannot be empty");
            } else {
                this.getLastnameR().setBorder(BorderFactory.createLineBorder(Color.green));
            }

            if (!results[DataValidation.HOME_PHONE]) {
                this.getHomeNumR().setBorder(BorderFactory.createLineBorder(Color.red));
                JOptionPane.showMessageDialog(null, "Home phone must be 8 numbers");
            } else {
                this.getHomeNumR().setBorder(BorderFactory.createLineBorder(Color.green));
            }

            if (!results[DataValidation.CELL_PHONE]) {
                this.getPhoneNumR().setBorder(BorderFactory.createLineBorder(Color.red));
                JOptionPane.showMessageDialog(null, "Mobile phone must be 7 numbers");
            } else {
                this.getPhoneNumR().setBorder(BorderFactory.createLineBorder(Color.green));
            }

            if (!results[DataValidation.EMAIL]) {
                this.getEmailR().setBorder(BorderFactory.createLineBorder(Color.red));
                JOptionPane.showMessageDialog(null, "Email must follow common conventions. Please check user guide for more info");
            } else {
                this.getEmailR().setBorder(BorderFactory.createLineBorder(Color.green));
            }

            if (!results[DataValidation.ADDRESS]) {
                this.getAddressR().setBorder(BorderFactory.createLineBorder(Color.red));
                JOptionPane.showMessageDialog(null, "Address can not be empty");
            } else {
                this.getAddressR().setBorder(BorderFactory.createLineBorder(Color.green));
            }

            if (!results[DataValidation.USERNAME]) {
                this.getUserName().setBorder(BorderFactory.createLineBorder(Color.red));
                JOptionPane.showMessageDialog(null, "Username cannot be empty, must only contain alphanumeric characters, and cannot be taken before");
            } else {
                this.getUserName().setBorder(BorderFactory.createLineBorder(Color.green));
            }

            if (!results[DataValidation.PASSWORD]) {
                this.getTypingPassword().setBorder(BorderFactory.createLineBorder(Color.red));
                JOptionPane.showMessageDialog(null, "Password must contain at least 1 lower case character, 1 upper case character, 1 special character, and 1 number ");
            } else {
                this.getTypingPassword().setBorder(BorderFactory.createLineBorder(Color.green));
                if (!results[DataValidation.RETYPE_PASSWORD]) {
                    this.getTypingPassword().setBorder(BorderFactory.createLineBorder(Color.red));
                    this.getTypingPassword().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(null, "Passwords must match");
                } else {
                    this.getTypingPassword().setBorder(BorderFactory.createLineBorder(Color.green));
                    this.getTypingPassword().setBorder(BorderFactory.createLineBorder(Color.green));
                }
            }
        } else {
            this.createImage();
            this.validate();
            this.repaint();
            System.out.println("Added image");
        }
    }
}