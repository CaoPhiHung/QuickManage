package View.ControlPanelFormUtilities;

import Controller.*;
import Custom.SuperButton;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

public class PersonalInfo extends JPanel implements Observer {

    private static PersonalInfo unique;
    //This font will be used for all components in this class
    private Font font = new Font("Serif", Font.ROMAN_BASELINE, 13);
    private Font fontBold = new Font("Serif", Font.BOLD, 13);
    //Left panel's components
    private JPanel photoLine = new JPanel();
    private JLabel photo = new JLabel();
    private BufferedImage myPhoto;
    private JLabel id = new JLabel("ID: ");
    private JLabel dob = new JLabel("Date of Birth: ");
    private JLabel firstName = new JLabel("First name: ");
    private JLabel lastName = new JLabel("Last Name: ");
    private JLabel gender = new JLabel("Gender: ");
    private JLabel phoneNumber = new JLabel("Mobile Phone: ");
    private JLabel email = new JLabel("Email: ");
    private JLabel address = new JLabel("Address: ");
    private JLabel status = new JLabel("Status: ");
    private JLabel room = new JLabel("");
    private SuperButton editButton = new SuperButton(SuperButton.EDIT_BUTTON, "Edit", SuperButton.WHITE_TO_BLUE, SuperButton.BLACK_COLOR);
    private SuperButton invoiceButton = new SuperButton(SuperButton.INVOICE_BUTTON, "Invoice", SuperButton.WHITE_TO_BLUE, SuperButton.BLACK_COLOR);
    private SuperButton assignEnrolButton = new SuperButton(SuperButton.ASSIGNENROLL_BUTTON, "Assign/Enroll", SuperButton.WHITE_TO_BLUE, SuperButton.BLACK_COLOR);
    private SuperButton viewSmallTimeTableButton = new SuperButton(SuperButton.TIMETABLE_BUTTON, "TimeTable", SuperButton.WHITE_TO_BLUE, SuperButton.BLACK_COLOR);
    private SuperButton generate = new SuperButton(SuperButton.ASSIGNENROLL_BUTTON, "Generate", SuperButton.WHITE_TO_BLUE, SuperButton.BLACK_COLOR);
    private SuperButton viewPayslip = new SuperButton(SuperButton.TIMETABLE_BUTTON, "View payslip", SuperButton.WHITE_TO_BLUE, SuperButton.BLACK_COLOR);
    //Listeners for time tables and other buttons
    private EditButtonListener editListener = new EditButtonListener();
    private AssignButtonListener assignListener = new AssignButtonListener();
    private StudentTableListerner studentListener = new StudentTableListerner();
    private TeacherTableListerner teacherListener = new TeacherTableListerner();
    private InvoiceFormController invoiceCon = new InvoiceFormController();
    private GenerateListener generateListener = new GenerateListener();
    private ViewPayslipListener viewPayslipListener = new ViewPayslipListener();
    
    //Left Panel Output Components
    private JLabel idO = new JLabel();
    private JLabel dobO = new JLabel();
    private JLabel firstNameO = new JLabel();
    private JLabel lastNameO = new JLabel();
    private JLabel genderO = new JLabel();
    private JLabel phoneNumberO = new JLabel();
    private JLabel emailO = new JLabel();
    private JLabel addressO = new JLabel();
    private JLabel statusO = new JLabel();
    private JLabel roomO = new JLabel();
    ResourceBundle resources;

    public static PersonalInfo getInstance() {
        if (unique == null) {
            unique = new PersonalInfo();
        }
        return unique;
    }

    public static void setUnique(PersonalInfo unique) {
        PersonalInfo.unique = unique;
    }

    public void initialize() {
        //Set font for all components
        id.setFont(fontBold);
        dob.setFont(fontBold);
        firstName.setFont(fontBold);
        lastName.setFont(fontBold);
        gender.setFont(fontBold);
        phoneNumber.setFont(fontBold);
        email.setFont(fontBold);
        address.setFont(fontBold);
        status.setFont(fontBold);
        room.setFont(fontBold);
        editButton.setFont(font);
        invoiceButton.setFont(font);
        assignEnrolButton.setFont(font);
        viewSmallTimeTableButton.setFont(font);
        idO.setFont(font);
        dobO.setFont(font);
        firstNameO.setFont(font);
        lastNameO.setFont(font);
        genderO.setFont(font);
        phoneNumberO.setFont(font);
        emailO.setFont(font);
        addressO.setFont(font);
        statusO.setFont(font);
        roomO.setFont(font);
        //Make Left Panel style
        setLayout(null);
        setBackground(new Color(204, 255, 230));
        setBounds(1, 77, 325, 590);
        //Make photo
        photoLine.removeAll();
        photoLine.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        photoLine.setLayout(new GridLayout(1, 1));
        photoLine.add(photo);
        photoLine.setBackground(Color.LIGHT_GRAY);
        //Set bound for photo
        photoLine.setBounds(72, 10, 180, 160);
        generate.setBounds(135, 510, 80, 70);
        viewPayslip.setBounds(215, 410, 80, 70);
        //Add to left panel
        add(id);
        add(dob);
        add(firstName);
        add(lastName);
        add(gender);
        add(phoneNumber);
        add(email);
        add(address);
        add(status);
        add(room);
        add(photoLine);
        add(editButton);
        add(assignEnrolButton);
        add(idO);
        add(dobO);
        add(firstNameO);
        add(lastNameO);
        add(genderO);
        add(phoneNumberO);
        add(emailO);
        add(addressO);
        add(statusO);
        add(roomO);
        // set listeners for 'Edit' and 'Assign'
        editButton.addActionListener(editListener);
        assignEnrolButton.addActionListener(assignListener);
        invoiceButton.addActionListener(invoiceCon);
        generate.addActionListener(generateListener);
        viewPayslip.addActionListener(viewPayslipListener);
    }

    //Set new values for labels  and assign ActionListener for buttons
    public void setAllPeopleLabels(String id, String dob, String firstName, String lastName, String gender,
            String phoneNumber, String email, String address, String status, String type, String url) {
        //Load Image
        photoLine.removeAll();
        photo = new JLabel();
        System.out.println(url);
        if (url.equals("C:\\Users\\Phi Hung\\Desktop\\images.jpg") || url.equals("No image")) {
            URL link = PersonalInfo.class.getResource("/Images/user.png");
            try {
                myPhoto = ImageIO.read(link);//Load image from a file located in Desktop
                photo = new JLabel(new ImageIcon(myPhoto));//If there is no new image, this line will not be reached
                //because of exception which means photo remains the same
            } catch (IOException e) {
                System.out.println("Image not loading");
            }
        } else {
            try {
                myPhoto = ImageIO.read(new File(url));//Load image from a file located in Desktop
                photo = new JLabel(new ImageIcon(myPhoto));//If there is no new image, this line will not be reached
                //because of exception which means photo remains the same
            } catch (IOException e) {
                System.out.println("Image not loading");
            }
        }
        photoLine.add(photo);
        resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
        //set labels
        this.id.setText(resources.getString("ID"));
        this.dob.setText(resources.getString("DOB"));
        this.firstName.setText(resources.getString("FirstName"));
        this.lastName.setText(resources.getString("LastName"));
        this.gender.setText(resources.getString("Gender"));
        this.phoneNumber.setText(resources.getString("PhoneNum"));
        this.email.setText(resources.getString("Email"));
        this.address.setText(resources.getString("Address"));
        this.status.setText(resources.getString("Status"));
        this.room.setText("");

        //Set new texts info
        idO.setText(id);
        dobO.setText(dob);
        firstNameO.setText(firstName);
        lastNameO.setText(lastName);
        genderO.setText(gender);
        phoneNumberO.setText(phoneNumber);
        emailO.setText(email);
        addressO.setText(address);
        statusO.setText(status);
        roomO.setText("");
        //set bounds
        this.id.setBounds(10, 180, 45, 20);
        this.dob.setBounds(10, 215, 100, 20);
        this.firstName.setBounds(10, 250, 100, 20);
        this.lastName.setBounds(10, 285, 100, 20);
        this.gender.setBounds(10, 320, 100, 20);
        this.phoneNumber.setBounds(10, 355, 120, 20);
        this.email.setBounds(10, 390, 100, 20);
        this.address.setBounds(10, 425, 100, 20);
        this.status.setBounds(10, 460, 420, 20);
        idO.setBounds(55, 180, 200, 20);
        dobO.setBounds(75, 215, 200, 20);
        firstNameO.setBounds(80, 250, 200, 20);
        lastNameO.setBounds(80, 285, 200, 20);
        genderO.setBounds(75, 320, 200, 20);
        phoneNumberO.setBounds(90, 355, 200, 20);
        emailO.setBounds(95, 390, 390, 20);
        addressO.setBounds(65, 425, 200, 20);
        statusO.setBounds(85, 460, 200, 20);
        //Update edit listener
        editListener.setId(id);
        //Listeners' conditions
        switch (type) {
            case "Student":
                //remover old listeners from timeTable button only
                viewSmallTimeTableButton.removeActionListener(studentListener);
                viewSmallTimeTableButton.removeActionListener(teacherListener);
                //Update listeners with new ID
                studentListener.setId(id);
                this.assignEnrolButton.setText("Enroll");
                viewSmallTimeTableButton.addActionListener(studentListener);
                //set bounds
                assignEnrolButton.setBounds(80, 510, 80, 70);
                editButton.setBounds(5, 510, 80, 70);
                invoiceButton.setBounds(155, 510, 80, 70);
                viewSmallTimeTableButton.setBounds(230, 510, 80, 70);
                add(editButton);
                add(invoiceButton);
                add(viewSmallTimeTableButton);
                add(assignEnrolButton);
                remove(generate);
                remove(viewPayslip);
                if (status.equalsIgnoreCase("Deactivate")) {
                    assignEnrolButton.setEnabled(false);
                    invoiceButton.setEnabled(false);
                } else {
                    assignEnrolButton.setEnabled(true);
                    invoiceButton.setEnabled(true);
                }
                if (LanguageController.getInstance().isClicked()) {
                    try {
                        resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
                        this.assignEnrolButton.setText(resources.getString("enrollButton"));
                    } catch (MissingResourceException mre) {
                        System.err.println("MyData.properties not found");
                    }
                }
                break;
            case "Teacher":
                //remover old listeners from timeTable button only
                viewSmallTimeTableButton.removeActionListener(studentListener);
                viewSmallTimeTableButton.removeActionListener(teacherListener);
                //Update listeners with new ID
                teacherListener.setId(id);
                this.assignEnrolButton.setText("Assign");
                viewSmallTimeTableButton.addActionListener(teacherListener);
                //set bounds
                editButton.setBounds(0, 510, 80, 70);
                assignEnrolButton.setBounds(60, 510, 80, 70);
                viewSmallTimeTableButton.setBounds(200, 510, 110, 70);
                add(editButton);
                add(assignEnrolButton);
                add(viewSmallTimeTableButton);
                add(generate);
                add(viewPayslip);
                remove(invoiceButton);
                if (status.equalsIgnoreCase("Deactivate")) {
                    assignEnrolButton.setEnabled(false);
                } else {
                    assignEnrolButton.setEnabled(true);
                }
                if (LanguageController.getInstance().isClicked()) {
                    try {
                        resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
                        this.assignEnrolButton.setText(resources.getString("assignButton"));
                    } catch (MissingResourceException mre) {
                        System.err.println("MyData.properties not found");
                    }
                }
                break;
            case "Staff":
                //Set bounds for buttons
                editButton.setBounds(125, 510, 80, 70);
                add(editButton);
                remove(assignEnrolButton);
                remove(viewSmallTimeTableButton);
                remove(invoiceButton);
                remove(generate);
                remove(viewPayslip);
                break;
            case "Manager":
                //Set bounds for buttons
                editButton.setBounds(125, 510, 80, 70);
                add(editButton);
                remove(assignEnrolButton);
                remove(viewSmallTimeTableButton);
                remove(invoiceButton);
                remove(generate);
                remove(viewPayslip);
                break;
        }

        if (LanguageController.getInstance().isClicked()) {
            try {
                resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
                this.editButton.setText(resources.getString("editButton"));
                this.invoiceButton.setText(resources.getString("invoiceButton"));
                this.viewSmallTimeTableButton.setText(resources.getString("viewSmallTimeTableButton"));
            } catch (MissingResourceException mre) {
                System.err.println("MyData.properties not found");
            }
        }
        //Update Components and UI
        revalidate();
        repaint();
    }

    public void setAllClassLabels(String id, String dob, String firstName, String lastName, String gender,
            Date phoneNumber, Date email, String address, String status, String type, String room, String url) {
        //Load Image
        photoLine.removeAll();
        photo = new JLabel();
        URL link = PersonalInfo.class.getResource("/Images/classicon.png");
        try {
            myPhoto = ImageIO.read(link);
            photo = new JLabel(new ImageIcon(myPhoto));
        } catch (IOException e) {
            System.out.println("Image not loading");
        }
        photoLine.setBackground(Color.WHITE);
        photoLine.add(photo);
        resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
        //Set labels
        this.id.setText(resources.getString("classID"));
        this.dob.setText(resources.getString("classCode"));
        this.firstName.setText(resources.getString("classType"));
        this.lastName.setText(resources.getString("className"));
        this.gender.setText(resources.getString("tuitionFee"));
        this.phoneNumber.setText(resources.getString("startDate"));
        this.email.setText(resources.getString("endDate"));
        this.address.setText(resources.getString("StartTime"));
        this.status.setText(resources.getString("EndTime"));
        this.room.setText(resources.getString("Room"));
        idO.setText(id);
        dobO.setText(dob);
        firstNameO.setText(firstName);
        lastNameO.setText(lastName);
        genderO.setText(gender);
        phoneNumberO.setText(String.format("%1$td-%1$tm-%1$tY", phoneNumber));
        emailO.setText(String.format("%1$td-%1$tm-%1$tY", email));
        addressO.setText(address);
        statusO.setText(status);
        roomO.setText(room);
        //Set bounds for left panel's components
        this.id.setBounds(10, 180, 80, 20);
        this.dob.setBounds(10, 215, 100, 20);
        this.firstName.setBounds(10, 250, 100, 20);
        this.lastName.setBounds(10, 285, 100, 20);
        this.gender.setBounds(10, 320, 100, 20);
        this.phoneNumber.setBounds(10, 355, 120, 20);
        this.email.setBounds(10, 390, 100, 20);
        this.address.setBounds(10, 425, 100, 20);
        this.status.setBounds(10, 460, 420, 20);
        this.room.setBounds(10, 495, 420, 20);
        idO.setBounds(80, 180, 200, 20);
        dobO.setBounds(90, 215, 200, 20);
        firstNameO.setBounds(90, 250, 200, 20);
        lastNameO.setBounds(100, 285, 200, 20);
        genderO.setBounds(100, 320, 200, 20);
        phoneNumberO.setBounds(95, 355, 200, 20);
        emailO.setBounds(85, 390, 390, 20);
        addressO.setBounds(95, 425, 200, 20);
        statusO.setBounds(90, 460, 200, 20);
        roomO.setBounds(60, 495, 200, 20);
        //Set bounds for buttons
        editButton.setBounds(125, 510, 80, 70);
        //Update edit listener
        editListener.setId(id);//Class only has one button
        add(editButton);
        remove(assignEnrolButton);
        remove(viewSmallTimeTableButton);
        remove(invoiceButton);
        remove(generate);
        remove(viewPayslip);
        //Update Components and UI
        revalidate();
        repaint();
    }

    public void setAllLabelsEmpty() {
        //Make photo
        photoLine.removeAll();
        photo = new JLabel();
        URL link = PersonalInfo.class.getResource("/Images/welcome.png");
        try {
            myPhoto = ImageIO.read(link);//Load image from a file located in Desktop
            photo = new JLabel(new ImageIcon(myPhoto));//If there is no new image, this line will not be reached
            //because of exception which means photo remains the same
        } catch (IOException e) {
            System.out.println("Image not loading");
        }
        photoLine.setBackground(Color.WHITE);
        photoLine.add(photo);
        //Hide all components
        this.id.setText("");
        this.dob.setText("");
        this.firstName.setText("");
        this.lastName.setText("");
        this.gender.setText("");
        this.phoneNumber.setText("");
        this.email.setText("");
        this.address.setText("");
        this.status.setText("");
        this.room.setText("");
        idO.setText("");
        dobO.setText("");
        firstNameO.setText("");
        lastNameO.setText("");
        genderO.setText("");
        phoneNumberO.setText("");
        emailO.setText("");
        addressO.setText("");
        statusO.setText("");
        roomO.setText("");
        remove(editButton);
        remove(assignEnrolButton);
        remove(viewSmallTimeTableButton);
        remove(invoiceButton);
        remove(generate);
        remove(viewPayslip);
        //Update Components and UI
        revalidate();
        repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
