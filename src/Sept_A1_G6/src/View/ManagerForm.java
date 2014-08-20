package View;

import Controller.*;
import Model.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class ManagerForm extends UserForm implements Observer {

    private static ManagerForm unique;
    private ManagerFormController managerCon = new ManagerFormController();
    private JLabel userL = new JLabel("Username: ");
    private JLabel passL = new JLabel("Password: ");
    private JLabel retypeL = new JLabel("Retype password: ");
    private JTextField userR = new JTextField(30);
    private JPasswordField passR = new JPasswordField(30);
    private JPasswordField retypeR = new JPasswordField(30);
    private int numberOfFields = 9;
    private ResourceBundle resources;

    public static ManagerForm getInstance() {
        if (unique == null) {
            unique = new ManagerForm();
        }
        return unique;
    }

    public int getNumberOfFields() {
        return numberOfFields;
    }

    public void managerInitialize() {

        managerCon.addObserver(this);

        //Call super
        super.initialize();
        if (Data.managerList.isEmpty()) {
            this.idR.setText("M1");
        } else {
            String temp = Data.managerList.get(Data.managerList.size() - 1).getID();
            int num = Integer.parseInt(temp.substring(1));
            num++;
            this.idR.setText("M" + num);
        }

        //Settings
        this.setUserType("Manager");
        setTitle("Add Manager Form");

        if (LanguageController.getInstance().isClicked()) {
            try {
                resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
                //Add Manager Form
                this.id.setText(resources.getString("ID"));
                this.firstname.setText(resources.getString("FirstName"));
                this.lastname.setText(resources.getString("LastName"));
                this.dob.setText(resources.getString("DOB"));
                this.gender.setText(resources.getString("Gender"));
                this.homeNum.setText(resources.getString("HomeNum"));
                this.phoneNum.setText(resources.getString("PhoneNum"));
                this.email.setText(resources.getString("Email"));
                this.address.setText(resources.getString("Address"));
                this.status.setText(resources.getString("Status"));
                this.userL.setText(resources.getString("Username"));
                this.passL.setText(resources.getString("Password"));
                this.retypeL.setText(resources.getString("RetypePassword"));
                this.setTitle(resources.getString("addManagerForm"));
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

        userL.setBounds(10, 450, 100, 25);
        passL.setBounds(10, 490, 100, 25);
        retypeL.setBounds(10, 530, 120, 25);
        userR.setBounds(110, 450, 170, 25);
        passR.setBounds(110, 490, 170, 25);
        retypeR.setBounds(110, 530, 170, 25);
        add.setBounds(110, 570, 80, 25);
        cancel.setBounds(200, 570, 80, 25);

        //Set listener
        cancel.addActionListener(managerCon);
        add.addActionListener(managerCon);
        browse.addActionListener(managerCon);
        this.addWindowListener(managerCon);

        //JFrame's settings 
        setSize(435, 630);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public JPasswordField getPassR() {
        return passR;
    }

    public JPasswordField getRetypeR() {
        return retypeR;
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

    public static void setUnique(ManagerForm unique) {
        ManagerForm.unique = unique;
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
                JOptionPane.showMessageDialog(null, "Address cannot be empty");
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
                this.getPassR().setBorder(BorderFactory.createLineBorder(Color.red));
                JOptionPane.showMessageDialog(null, "Password must contain at least 1 lower case character, 1 upper case character, 1 special character, and 1 number ");
            } else {
                this.getPassR().setBorder(BorderFactory.createLineBorder(Color.green));
                if (!results[DataValidation.RETYPE_PASSWORD]) {
                    this.getPassR().setBorder(BorderFactory.createLineBorder(Color.red));
                    this.getRetypeR().setBorder(BorderFactory.createLineBorder(Color.red));
                    JOptionPane.showMessageDialog(null, "Password must match");
                } else {
                    this.getPassR().setBorder(BorderFactory.createLineBorder(Color.green));
                    this.getRetypeR().setBorder(BorderFactory.createLineBorder(Color.green));
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
