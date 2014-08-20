package View;

import Controller.LanguageController;
import Controller.TeacherFormController;
import Model.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class TeacherForm extends UserForm implements Observer {

    //Properties
    private static TeacherForm unique;
    private int numberOfSkill = Data.classTypeList.size();
    private JCheckBox[] allSkill = new JCheckBox[numberOfSkill];
    private JTextField[] hourlyPaid = new JTextField[numberOfSkill];
    private JLabel[] labelPaid = new JLabel[numberOfSkill];
    private JLabel[] labelVND = new JLabel[numberOfSkill];
    private JButton assignButton = new JButton("Assign");
    private TeacherFormController teacherCon = new TeacherFormController();
    private int numberOfFields = 6;
    private ArrayList<Classes> classes = new ArrayList<>();
    private JScrollPane scrollSkill = new JScrollPane();
    private JPanel wrapSkill = new JPanel();
    private ResourceBundle resources;

    //Singleton
    public static TeacherForm getInstance() {
        if (unique == null) {
            unique = new TeacherForm();
        }
        return unique;
    }

    //Initialize
    public void teacherInitialize() {
        super.initialize();
        teacherCon.addObserver(this);
        Data da = new Data();
        da.autoGenerateID(this);
        this.setUserType("Teacher");

        //Set skills, hourly paid
        wrapSkill.setLayout(null);
        int heightSkill = 5;
        int totalLength = 0;
        int lengthbox = 0;
        for (int i = 0; i < allSkill.length; i++) {
            lengthbox = Data.classTypeList.get(i).getName().length();
            allSkill[i] = new JCheckBox(Data.classTypeList.get(i).getName());
            hourlyPaid[i] = new JTextField("0", 30);
            labelPaid[i] = new JLabel("Hourly paid: ", JLabel.CENTER);
            labelVND[i] = new JLabel("VND", JLabel.CENTER);
            wrapSkill.add(allSkill[i]);
            wrapSkill.add(labelPaid[i]);
            wrapSkill.add(hourlyPaid[i]);
            wrapSkill.add(labelVND[i]);
            allSkill[i].setBounds(5, heightSkill, lengthbox * 12, 30);
            labelPaid[i].setBounds(lengthbox * 12 + 5, heightSkill, 100, 30);
            hourlyPaid[i].setBounds(lengthbox * 12 + 5 + 100, heightSkill, 100, 30);
            labelVND[i].setBounds(lengthbox * 12 + 5 + 100 + 100, heightSkill, 50, 30);
            heightSkill += 35;
            totalLength = totalLength + 35;
        }


        status.setBounds(10, 480, 50, 25);
        skill.setBounds(10, 420, 100, 25);
        status.setBounds(10, 590, 100, 25);
        statusR.setBounds(110, 590, 100, 25);
        add.setBounds(90, 620, 80, 25);
        cancel.setBounds(270, 620, 80, 25);
        assignButton.setBounds(180, 620, 80, 25);
        scrollSkill.setBounds(110, 420, 300, 165);
        wrapSkill.setBounds(0, 0, 300, 150);

//        for (int i = 0; i < allSkill.length; i++) {
//            userForm.add(allSkill[i]);
//        }

        scrollSkill.setViewportView(wrapSkill);
        wrapSkill.setPreferredSize(new Dimension(lengthbox * 12 + 5 + 100 + 100 + 50, totalLength));
//        scrollSkill.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        userForm.add(scrollSkill);
        userForm.add(skill);
        userForm.add(assignButton);
        setTitle("Add Teacher Form");
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
                this.skill.setText(resources.getString("Skill"));
                this.status.setText(resources.getString("Status"));
                this.assignButton.setText(resources.getString("assignButton"));
                this.setTitle(resources.getString("addTeacherForm"));
            } catch (MissingResourceException mre) {
                System.err.println("MyData.properties not found");
            }
        }

        //Set Listener
        add.addActionListener(teacherCon);
        browse.addActionListener(teacherCon);
        cancel.addActionListener(teacherCon);
        assignButton.addActionListener(teacherCon);
        this.addWindowListener(teacherCon);

        //JFrame's settings 
        setSize(435, 690);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
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
                JOptionPane.showMessageDialog(null, "Emaill must follow common conventions. Please check user guild for more info");
            } else {
                this.getEmailR().setBorder(BorderFactory.createLineBorder(Color.green));
            }

            if (!results[DataValidation.ADDRESS]) {
                this.getAddressR().setBorder(BorderFactory.createLineBorder(Color.red));
                JOptionPane.showMessageDialog(null, "Address cannot be empty");
            } else {
                this.getAddressR().setBorder(BorderFactory.createLineBorder(Color.green));
            }
        } else {
            this.createImage();
            this.validate();
            this.repaint();
            System.out.println("Added image");
        }
    }

    public int getNumberOfSkill() {
        return numberOfSkill;
    }

    public JCheckBox[] getAllSkill() {
        return allSkill;
    }

    public static void setUnique(TeacherForm unique) {
        TeacherForm.unique = unique;
    }

    public ArrayList<Classes> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Classes> classes) {
        this.classes = classes;
    }

    public int getNumberOfFields() {
        return numberOfFields;
    }

    public JButton getAssignButton() {
        return assignButton;
    }

    public void setAssignButton(JButton assignButton) {
        this.assignButton = assignButton;
    }

    public TeacherFormController getTeacherCon() {
        return teacherCon;
    }

    public void setTeacherCon(TeacherFormController teacherCon) {
        this.teacherCon = teacherCon;
    }

    public JTextField[] getHourlyPaid() {
        return hourlyPaid;
    }

    public void setHourlyPaid(JTextField[] hourlyPaid) {
        this.hourlyPaid = hourlyPaid;
    }
}
