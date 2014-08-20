package View;

import Controller.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.imageio.*;
import javax.swing.*;

public abstract class UserForm extends JFrame {

    //Properties
    protected JPanel userForm;
    JButton add;
    JButton cancel;
    JButton browse;
    protected JLabel image;
    private JLabel description;
    private JTextArea infoArea;
    //declare label
    public JLabel id = new JLabel("ID: ");
    JLabel firstname = new JLabel("First Name: ");
    JLabel lastname = new JLabel("Last Name: ");
    JLabel dob = new JLabel("DOB: ");
    JLabel gender = new JLabel("Gender: ");
    JLabel homeNum = new JLabel("Home Phone: ");
    JLabel phoneNum = new JLabel("Mobile Phone: ");
    JLabel email = new JLabel("Email: ");
    JLabel address = new JLabel("Address: ");
    JLabel status = new JLabel("Status: ");
    public JLabel idR;
    private JTextField firstnameR;
    private JTextField lastnameR;
    private JComboBox<String> dobDay;
    private JComboBox<String> dobMonth;
    private JComboBox<String> dobYear;
    private JComboBox<String> genderR;
    private JComboBox<String> homeNumCodeR;
    private JComboBox<String> phoneNumCodeR;
    private JTextField homeNumR;
    protected JTextField phoneNumR;
    private JTextField emailR;
    private JTextArea addressR;
    JLabel skill = new JLabel("Skill: ");
    JLabel statusR;
    private JCheckBox piano, organ, violin, guitar, painting, singing, ballet, hiphop, photography;
    private String userType = null;
    protected BufferedImage imageBuffer, originalImage;
    protected String imagePath = "C:\\Users\\Phi Hung\\Desktop\\images.jpg";
    private String option = "";
    protected HomePhoneController homeCon = new HomePhoneController();
    protected MobilePhoneController mobileCon = new MobilePhoneController();
    private ResourceBundle resources;

    //Initialize
    public void initialize() {

        userForm = new JPanel();
        userForm.setLayout(null);

        //Create instance and settings     

        idR = new JLabel("1");
        firstnameR = new JTextField(30);
        lastnameR = new JTextField(30);
        dobDay = new JComboBox<>();



        for (int i = 1; i <= 30; i++) {
            dobDay.addItem("" + i);
        }
        dobMonth = new JComboBox<>();
        String[] allMonth = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "July",
            "Aug", "Sep", "Oct", "Nov", "Dec"};
        for (int i = 0; i < allMonth.length; i++) {
            dobMonth.addItem(allMonth[i]);
        }
        dobYear = new JComboBox<>();
        for (int i = 1953; i < 2006; i++) {
            dobYear.addItem("" + i);
        }
        homeNumCodeR = new JComboBox<>();
        //HCM city
        homeNumCodeR.addItem("008");
        //HN city
        homeNumCodeR.addItem("004");
        //Binh Duong
        homeNumCodeR.addItem("065");
        //Da Nang
        homeNumCodeR.addItem("051");
        //Dong Thap
        homeNumCodeR.addItem("067");
        //Can Tho
        homeNumCodeR.addItem("071");
        //Ca Mau
        homeNumCodeR.addItem("780");

        phoneNumCodeR = new JComboBox<>();
        //HCM city
        phoneNumCodeR.addItem("012");
        //HN city
        phoneNumCodeR.addItem("097");
        //Binh Duong
        phoneNumCodeR.addItem("065");
        //Da Nang
        phoneNumCodeR.addItem("051");
        //Dong Thap
        phoneNumCodeR.addItem("067");
        //Can Tho
        phoneNumCodeR.addItem("071");
        //Ca Mau
        phoneNumCodeR.addItem("780");

        genderR = new JComboBox<>();
        genderR.addItem("Male");
        genderR.addItem("Female");
        homeNumR = new JTextField(15);
        phoneNumR = new JTextField(15);
        emailR = new JTextField(60);
        addressR = new JTextArea();
        statusR = new JLabel("Activate");
        add = new JButton("Add");
        cancel = new JButton("Cancel");
        browse = new JButton("Browse");

        createImage();
        description = new JLabel("Description");
        infoArea = new JTextArea();
        piano = new JCheckBox("Piano");
        organ = new JCheckBox("Organ");
        violin = new JCheckBox("Violin");
        guitar = new JCheckBox("Guitar");
        painting = new JCheckBox("Painting");
        singing = new JCheckBox("Singing");
        ballet = new JCheckBox("Ballet");
        hiphop = new JCheckBox("Hiphop");
        photography = new JCheckBox("Photography");

        //SetBounds
        id.setBounds(10, 20, 50, 25);
        firstname.setBounds(10, 60, 70, 25);
        lastname.setBounds(10, 100, 70, 25);
        dob.setBounds(10, 140, 100, 25);
        gender.setBounds(10, 180, 70, 25);
        homeNum.setBounds(10, 220, 90, 25);
        phoneNum.setBounds(10, 260, 90, 25);
        email.setBounds(10, 300, 100, 25);
        address.setBounds(10, 340, 60, 25);
        status.setBounds(10, 410, 100, 25);

        //Left Panels add info
        userForm.add(id);
        userForm.add(firstname);
        userForm.add(lastname);
        userForm.add(dob);
        userForm.add(gender);
        userForm.add(homeNum);
        userForm.add(phoneNum);
        userForm.add(email);
        userForm.add(address);
        userForm.add(status);

        //SetBounds
        idR.setBounds(110, 20, 70, 25);
        firstnameR.setBounds(110, 60, 170, 25);
        lastnameR.setBounds(110, 100, 170, 25);
        dobDay.setBounds(110, 140, 50, 25);
        dobMonth.setBounds(160, 140, 55, 25);
        dobYear.setBounds(215, 140, 65, 25);
        genderR.setBounds(110, 180, 75, 25);
        homeNumCodeR.setBounds(110, 220, 55, 25);
        homeNumR.setBounds(165, 220, 115, 25);
        phoneNumCodeR.setBounds(110, 260, 55, 25);
        phoneNumR.setBounds(165, 260, 115, 25);
        emailR.setBounds(110, 300, 170, 25);
        addressR.setBounds(110, 340, 170, 60);
        addressR.setLineWrap(true);
        addressR.setWrapStyleWord(true);
        statusR.setBounds(115, 410, 100, 25);

        add.setBounds(120, 420, 80, 25);
        cancel.setBounds(210, 420, 80, 25);


        description.setBounds(327, 140, 130, 130);
        browse.setBounds(310, 165, 100, 25);
        infoArea.setBounds(300, 220, 120, 180);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);

        //Right Panels add info
        userForm.add(idR);
        userForm.add(firstnameR);
        userForm.add(lastnameR);
        userForm.add(firstnameR);
        userForm.add(dobDay);
        userForm.add(dobMonth);
        userForm.add(dobYear);
        userForm.add(genderR);
        userForm.add(homeNumCodeR);
        userForm.add(homeNumR);
        userForm.add(phoneNumCodeR);
        userForm.add(phoneNumR);
        userForm.add(emailR);
        userForm.add(addressR);
        userForm.add(statusR);

        //
        userForm.add(add);
        userForm.add(cancel);
        //cancel.addActionListener(userCo);
        userForm.add(browse);

        userForm.add(image);
        userForm.add(description);
        userForm.add(infoArea);
        add(userForm);

        phoneNumR.setName("phoneNumR");

        //add key listener to add dash - to home num & phone num
        homeNumR.addKeyListener(homeCon);
        phoneNumR.addKeyListener(mobileCon);
        if (LanguageController.getInstance().isClicked()) {
            try {
                resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
                this.add.setText(resources.getString("Add"));
                this.cancel.setText(resources.getString("Cancel"));
                this.browse.setText(resources.getString("Browse"));
                this.description.setText(resources.getString("description"));
            } catch (MissingResourceException mre) {
                System.err.println("MyData.properties not found");
            }
        }
//        //JFrame's settings 
        setSize(440, 480);
//        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    protected void createImage() {
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

            BufferedImage resizeImageJpg = resizeImage(originalImage, type);
            ImageIO.write(resizeImageJpg, "jpg", new File(imagePath + "_new.jpg"));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            imageBuffer = ImageIO.read(new File(imagePath + "_new.jpg"));
        } catch (IOException ex) {
        }

        image = new JLabel("         Image here") {
            @Override
            protected void paintComponent(Graphics grphcs) {
                super.paintComponent(grphcs);
                grphcs.drawImage(imageBuffer, 0, 0, null);
            }
        };
        image.paintComponents(null);
        image.setBorder(BorderFactory.createLineBorder(Color.blue));
        image.setBounds(300, 60, 120, 100);
    }

    public JButton getCancel() {
        return cancel;
    }

    public JButton getAdd() {
        return add;
    }

    public JPanel getUserForm() {
        return userForm;
    }

    public JButton getBrowse() {
        return browse;
    }

    public JLabel getImage() {
        return image;
    }

    public JLabel getHomeNum() {
        return homeNum;
    }

    public JLabel getSkill() {
        return skill;
    }

    public JLabel getDescription() {
        return description;
    }

    public JTextArea getInfoArea() {
        return infoArea;
    }

    public JLabel getId() {
        return id;
    }

    public JLabel getFirstname() {
        return firstname;
    }

    public JLabel getLastname() {
        return lastname;
    }

    public JLabel getDob() {
        return dob;
    }

    public JLabel getGender() {
        return gender;
    }

    public JLabel getPhoneNum() {
        return phoneNum;
    }

    public JLabel getEmail() {
        return email;
    }

    public JLabel getAddress() {
        return address;
    }

    public JLabel getStatus() {
        return status;
    }

    public JLabel getIdR() {
        return idR;
    }

    public void setIdR(JLabel idR) {
        this.idR = idR;
    }

    public JTextField getFirstnameR() {
        return firstnameR;
    }

    public JTextField getLastnameR() {
        return lastnameR;
    }

    public void setInfoArea(JTextArea infoArea) {
        this.infoArea = infoArea;
    }

    public JComboBox<String> getDobDay() {
        return dobDay;
    }

    public JComboBox<String> getDobMonth() {
        return dobMonth;
    }

    public JComboBox<String> getDobYear() {
        return dobYear;
    }

    public JComboBox<String> getGenderR() {
        return genderR;
    }

    public JTextField getPhoneNumR() {
        return phoneNumR;
    }

    public JTextField getEmailR() {
        return emailR;
    }

    public JTextArea getAddressR() {
        return addressR;
    }

    public JLabel getStatusR() {
        return statusR;
    }

    public JCheckBox getPiano() {
        return piano;
    }

    public JCheckBox getOrgan() {
        return organ;
    }

    public JCheckBox getViolin() {
        return violin;
    }

    public JCheckBox getGuitar() {
        return guitar;
    }

    public JCheckBox getPainting() {
        return painting;
    }

    public JCheckBox getSinging() {
        return singing;
    }

    public JCheckBox getBallet() {
        return ballet;
    }

    public JCheckBox getHiphop() {
        return hiphop;
    }

    public JCheckBox getPhotography() {
        return photography;
    }

    public JComboBox<String> getHomeNumCodeR() {
        return homeNumCodeR;
    }

    public void setHomeNumCodeR(JComboBox<String> homeNumCodeR) {
        this.homeNumCodeR = homeNumCodeR;
    }

    public JComboBox<String> getPhoneNumCodeR() {
        return phoneNumCodeR;
    }

    public void setPhoneNumCodeR(JComboBox<String> phoneNumCodeR) {
        this.phoneNumCodeR = phoneNumCodeR;
    }

    public JTextField getHomeNumR() {
        return homeNumR;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(120, 100, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 700, 690, null);
        g.dispose();

        return resizedImage;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
