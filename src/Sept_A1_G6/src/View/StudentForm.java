package View;

import Controller.*;
import Model.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

public class StudentForm extends UserForm implements Observer {

    private static StudentForm unique;
    private StudentFormController studentCon = new StudentFormController();
//    private MobilePhoneController mobileCon = new MobilePhoneController();
    private JButton enrollButton = new JButton("Enroll");
    private ArrayList<Classes> classes = new ArrayList<>();
    private JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
    //Label for contact person
    private JLabel contactHeading = new JLabel("Contact Person");
    private JLabel contactName = new JLabel("Name:");
    private JLabel contactPhone = new JLabel("Mobile Phone:");
    private JLabel contactEmail = new JLabel("Email:");
    private JLabel contactAddress = new JLabel("Address:");
    private JLabel contactRelationship = new JLabel("Relationship:");
    //Text Field for contact person
    private JTextField contactNameR = new JTextField();
    private JTextField contactPhoneR = new JTextField();
    private JTextField contactEmailR = new JTextField();
    private JTextArea contactAddressR = new JTextArea();
    private JComboBox contactRelationshipR;
    private JComboBox contactPhoneCodeR;
    private int numberOfFields = 10;
    //contact image
    private JLabel contactImage;
    private BufferedImage imageBuffer, originalImage;
    private String imagePath = "C:\\Users\\Phi Hung\\Desktop\\images.jpg";
    private JButton contactBrowse;
    private ResourceBundle resources;

    public int getNumberOfFields() {
        return numberOfFields;
    }

    //Singleton
    public static StudentForm getInstance() {
        if (unique == null) {
            unique = new StudentForm();
        }
        return unique;
    }

    public void studentInitialize() {
        studentCon.addObserver(this);
        super.initialize();
        Data data = new Data();
        data.autoGenerateID(this);
        this.setUserType("Student");
        contactBrowse = new JButton("Browse");

        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

            BufferedImage resizeImageJpg = resizeImage(originalImage, type);
            ImageIO.write(resizeImageJpg, "jpg", new File(imagePath + "_new.jpg"));

//		BufferedImage resizeImagePng = resizeImage(originalImage, type);
//		ImageIO.write(resizeImagePng, "png", new File(imagePath+"_new.png")); 

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            imageBuffer = ImageIO.read(new File(imagePath + "_new.jpg"));
        } catch (IOException ex) {
        }

        contactImage = new JLabel("         Image here") {
            @Override
            protected void paintComponent(Graphics grphcs) {
                super.paintComponent(grphcs);
                grphcs.drawImage(imageBuffer, 0, 0, null);
            }
        };
        contactImage.paintComponents(null);
        contactImage.setBorder(BorderFactory.createLineBorder(Color.blue));


        //add phone code
        contactPhoneCodeR = new JComboBox<>();
        contactPhoneCodeR.addItem("012");
        //HN city
        contactPhoneCodeR.addItem("097");
        //Binh Duong
        contactPhoneCodeR.addItem("065");
        //Da Nang
        contactPhoneCodeR.addItem("051");
        //Dong Thap
        contactPhoneCodeR.addItem("067");
        //Can Tho
        contactPhoneCodeR.addItem("071");
        //Ca Mau
        contactPhoneCodeR.addItem("780");

        //add contact relationship
        contactRelationshipR = new JComboBox<>();
        contactRelationshipR.addItem("Father");
        contactRelationshipR.addItem("Mother");
        contactRelationshipR.addItem("Sister");
        contactRelationshipR.addItem("Brother");
        contactRelationshipR.addItem("Other");




        //Adding 
        userForm.add(enrollButton);
        userForm.add(separator);
        userForm.add(contactHeading);
        userForm.add(contactName);
        userForm.add(contactPhoneCodeR);
        userForm.add(contactPhone);
        userForm.add(contactEmail);
        userForm.add(contactAddress);
        userForm.add(contactRelationship);
        userForm.add(contactNameR);
        userForm.add(contactPhoneR);
        userForm.add(contactEmailR);
        userForm.add(contactAddressR);
        userForm.add(contactRelationshipR);
        userForm.add(contactImage);
        userForm.add(contactBrowse);

        //set bound
        separator.setBounds(430, 10, 10, 420);
        contactHeading.setBounds(515, 20, 100, 25);
        contactImage.setBounds(500, 60, 120, 100);
        contactBrowse.setBounds(520, 165, 80, 25);
        contactName.setBounds(440, 220, 50, 25);
        contactPhone.setBounds(440, 260, 80, 25);
        contactEmail.setBounds(440, 300, 100, 25);
        contactAddress.setBounds(440, 340, 50, 25);
        contactRelationship.setBounds(440, 410, 80, 25);

        //set bound for text field
        contactNameR.setBounds(520, 220, 150, 25);
        contactPhoneCodeR.setBounds(520, 260, 55, 25);
        contactPhoneR.setBounds(575, 260, 95, 25);
        contactEmailR.setBounds(520, 300, 150, 25);
        contactAddressR.setBounds(520, 340, 150, 60);
        contactAddressR.setLineWrap(true);
        contactAddressR.setWrapStyleWord(true);
        contactRelationshipR.setBounds(520, 410, 80, 25);

        //set bound for button
        add.setBounds(180, 450, 100, 25);
        cancel.setBounds(400, 450, 100, 25);
        enrollButton.setBounds(290, 450, 100, 25);

        //
        contactPhoneR.setName("contactPhone");

        //Set Listener
        add.addActionListener(studentCon);
        browse.addActionListener(studentCon);
        cancel.addActionListener(studentCon);
        enrollButton.addActionListener(studentCon);
        contactPhoneR.addKeyListener(mobileCon);
        this.addWindowListener(studentCon);

        //Settings
        this.setUserType("Student");
        setTitle("Add Student Form");
        if (LanguageController.getInstance().isClicked()) {
            try {
                resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
                //Add Student Form
                StudentForm.getInstance().getId().setText(resources.getString("ID"));
                StudentForm.getInstance().getFirstname().setText(resources.getString("FirstName"));
                StudentForm.getInstance().getLastname().setText(resources.getString("LastName"));
                StudentForm.getInstance().getDob().setText(resources.getString("DOB"));
                StudentForm.getInstance().getGender().setText(resources.getString("Gender"));
                StudentForm.getInstance().getHomeNum().setText(resources.getString("HomeNum"));
                StudentForm.getInstance().getPhoneNum().setText(resources.getString("PhoneNum"));
                StudentForm.getInstance().getEmail().setText(resources.getString("Email"));
                StudentForm.getInstance().getAddress().setText(resources.getString("Address"));
                StudentForm.getInstance().getStatus().setText(resources.getString("Status"));
                //Contact Person
                StudentForm.getInstance().getContactName().setText(resources.getString("FirstName"));
                StudentForm.getInstance().getContactPhone().setText(resources.getString("PhoneNum"));
                StudentForm.getInstance().getContactEmail().setText(resources.getString("Email"));
                StudentForm.getInstance().getContactAddress().setText(resources.getString("Address"));
                StudentForm.getInstance().getContactRelationship().setText(resources.getString("Relationship"));
                this.enrollButton.setText(resources.getString("enrollButton"));
                this.setTitle(resources.getString("addStudentForm"));
            } catch (MissingResourceException mre) {
                System.err.println("MyData.properties not found");
            }
        }
        //JFrame's settings 
        setSize(685, 510);
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
                JOptionPane.showMessageDialog(null, "Mobile phone must be 7 numbers");
                this.getPhoneNumR().setBorder(BorderFactory.createLineBorder(Color.red));
            } else {
                this.getPhoneNumR().setBorder(BorderFactory.createLineBorder(Color.green));
            }

            if (!results[DataValidation.EMAIL]) {
                JOptionPane.showMessageDialog(null, "Email must follow common email conventions. Check user guide for more info");
                this.getEmailR().setBorder(BorderFactory.createLineBorder(Color.red));
            } else {
                this.getEmailR().setBorder(BorderFactory.createLineBorder(Color.green));
            }

            if (!results[DataValidation.ADDRESS]) {
                JOptionPane.showMessageDialog(null, "Address cannot be empty");
                this.getAddressR().setBorder(BorderFactory.createLineBorder(Color.red));
            } else {
                this.getAddressR().setBorder(BorderFactory.createLineBorder(Color.green));
            }

            if (!results[DataValidation.CONTACT_PERSON_NAME]) {
                JOptionPane.showMessageDialog(null, "Name cannot be empty");
                this.getContactNameR().setBorder(BorderFactory.createLineBorder(Color.red));
            } else {
                this.getContactNameR().setBorder(BorderFactory.createLineBorder(Color.green));
            }

            if (!results[DataValidation.CONTACT_PERSON_PHONE]) {
                JOptionPane.showMessageDialog(null, "Must be 7 numbers");
                this.getContactPhoneR().setBorder(BorderFactory.createLineBorder(Color.red));
            } else {
                this.getContactPhoneR().setBorder(BorderFactory.createLineBorder(Color.green));
            }

            if (!results[DataValidation.CONTACT_PERSON_EMAIL]) {
                JOptionPane.showMessageDialog(null, "Email must follow common email conventions. Check user guide for more info");
                this.getContactEmailR().setBorder(BorderFactory.createLineBorder(Color.red));

            } else {
                this.getContactEmailR().setBorder(BorderFactory.createLineBorder(Color.green));
            }

            if (!results[DataValidation.CONTACT_PERSON_ADDRESS]) {
                JOptionPane.showMessageDialog(null, "Address cannot be empty");
                this.getContactAddressR().setBorder(BorderFactory.createLineBorder(Color.red));
            } else {
                this.getContactAddressR().setBorder(BorderFactory.createLineBorder(Color.green));
            }
        } else {
            this.createImage();
            this.validate();
            this.repaint();
            System.out.println("Added image");
        }


    }

    public static void setUnique(StudentForm unique) {
        StudentForm.unique = unique;
    }

    public ArrayList<Classes> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Classes> classes) {
        this.classes = classes;
    }

    public JTextField getContactNameR() {
        return contactNameR;
    }

    public void setContactNameR(JTextField contactNameR) {
        this.contactNameR = contactNameR;
    }

    public JTextField getContactPhoneR() {
        return contactPhoneR;
    }

    public void setContactPhoneR(JTextField contactPhoneR) {
        this.contactPhoneR = contactPhoneR;
    }

    public JTextField getContactEmailR() {
        return contactEmailR;
    }

    public void setContactEmailR(JTextField contactEmailR) {
        this.contactEmailR = contactEmailR;
    }

    public JTextArea getContactAddressR() {
        return contactAddressR;
    }

    public void setContactAddressR(JTextArea contactAddressR) {
        this.contactAddressR = contactAddressR;
    }

    public JComboBox getContactRelationshipR() {
        return contactRelationshipR;
    }

    public void setContactRelationshipR(JComboBox contactRelationshipR) {
        this.contactRelationshipR = contactRelationshipR;
    }

    public JComboBox getContactPhoneCodeR() {
        return contactPhoneCodeR;
    }

    public void setContactPhoneCodeR(JComboBox contactPhoneCodeR) {
        this.contactPhoneCodeR = contactPhoneCodeR;
    }

    public JButton getEnrollButton() {
        return enrollButton;
    }

    public void setEnrollButton(JButton enrollButton) {
        this.enrollButton = enrollButton;
    }

    public JLabel getContactHeading() {
        return contactHeading;
    }

    public JLabel getContactName() {
        return contactName;
    }

    public JLabel getContactPhone() {
        return contactPhone;
    }

    public JLabel getContactEmail() {
        return contactEmail;
    }

    public JLabel getContactAddress() {
        return contactAddress;
    }

    public JLabel getContactRelationship() {
        return contactRelationship;
    }

    public JLabel getContactImage() {
        return contactImage;
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(120, 100, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 700, 690, null);
        g.dispose();

        return resizedImage;
    }
}
