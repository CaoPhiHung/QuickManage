package Controller;

import Model.*;
import View.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.apache.commons.io.FileUtils;

public class StudentFormController extends Observable implements ActionListener, WindowListener {

    private JFileChooser fc;

    @Override
    public void actionPerformed(ActionEvent ae) {
        StudentForm sf = StudentForm.getInstance();
        DataValidation dv = new DataValidation();
        //Cast source
        JButton but = (JButton) ae.getSource();
        if (but.getText().equalsIgnoreCase("Add") || but.getText().equalsIgnoreCase("Thêm")) {
            Data data = new Data();
            // checkResult data from manaager form 
            if (StudentForm.getInstance().getOption().equalsIgnoreCase("add") || StudentForm.getInstance().getOption().equalsIgnoreCase("Thêm")) {
                dv.validateStudentForm(StudentForm.getInstance());
            } else if (StudentForm.getInstance().getOption().equalsIgnoreCase("edit")) {
                ((Student) Data.getCurrentEditUser()).setFirstName(sf.getFirstnameR().getText());
                ((Student) Data.getCurrentEditUser()).setLastName(sf.getLastnameR().getText());
                ((Student) Data.getCurrentEditUser()).setDobDate(sf.getDobDay().getSelectedItem().toString());
                ((Student) Data.getCurrentEditUser()).setDobMonth(sf.getDobMonth().getSelectedItem().toString());
                ((Student) Data.getCurrentEditUser()).setDobYear(sf.getDobYear().getSelectedItem().toString());
                ((Student) Data.getCurrentEditUser()).setGender(sf.getGenderR().getSelectedItem().toString());
                ((Student) Data.getCurrentEditUser()).setHomeNoCode(sf.getHomeNumCodeR().toString());
                ((Student) Data.getCurrentEditUser()).setHomeNo(sf.getHomeNumR().getText());
                ((Student) Data.getCurrentEditUser()).setPhoneNoCode(sf.getPhoneNumCodeR().getSelectedItem().toString());
                ((Student) Data.getCurrentEditUser()).setPhoneNo(sf.getPhoneNumR().getText());
                ((Student) Data.getCurrentEditUser()).setEmail(sf.getEmailR().getText());
                ((Student) Data.getCurrentEditUser()).setAddress(sf.getAddressR().getText());
                ((Student) Data.getCurrentEditUser()).setStatus(sf.getStatusR().getText());
                ((Student) Data.getCurrentEditUser()).setDescription(sf.getInfoArea().getText());

                ((Student) Data.getCurrentEditUser()).setContactName(sf.getContactNameR().getText());
                ((Student) Data.getCurrentEditUser()).setContactPhoneCode(sf.getContactPhoneCodeR().getSelectedItem().toString());
                ((Student) Data.getCurrentEditUser()).setContactPhone(sf.getContactPhoneR().getText());
                ((Student) Data.getCurrentEditUser()).setContactEmail(sf.getContactEmailR().getText());
                ((Student) Data.getCurrentEditUser()).setContactAddress(sf.getContactAddressR().getText());
                ((Student) Data.getCurrentEditUser()).setContactRelationShip(sf.getContactRelationshipR().getSelectedItem().toString());
                ((Student) Data.getCurrentEditUser()).setImageLink(sf.getImagePath());
                dv.validateStudentForm(StudentForm.getInstance());
            }
//            if (Data.getCurrentStudentEnroll() != null) {
//                ScrollPanelInfo.getInstance().getListener().getPressOrNot()[ScrollPanelInfo.getInstance().getListener().getIndex()] = false;
//                ScrollPanelInfo.getInstance().getListener().getCheckBoxPanel()[ScrollPanelInfo.getInstance().getListener().getIndex()].setBackground(Color.WHITE);
//                ScrollPanelInfo.getInstance().getListener().getTypePanel()[ScrollPanelInfo.getInstance().getListener().getIndex()].setBackground(Color.WHITE);
//                ScrollPanelInfo.getInstance().getListener().getInfoPanel()[ScrollPanelInfo.getInstance().getListener().getIndex()].setBackground(Color.WHITE);
//                ScrollPanelInfo.getInstance().getListener().getDatePanel()[ScrollPanelInfo.getInstance().getListener().getIndex()].setBackground(Color.WHITE);
//                ScrollPanelInfo.getInstance().getListener().getStatusPanel()[ScrollPanelInfo.getInstance().getListener().getIndex()].setBackground(Color.WHITE);
//                PersonalInfo.getInstance().setAllLabelsEmpty();//remove old info of personalPanel
//            }
            Data.setCurrentStudentEnroll(Data.getCurrentStudentEnrollTemp());

        } else if (but.getText().equalsIgnoreCase("Cancel") || but.getText().equalsIgnoreCase("Hủy")) {
            StudentForm student = StudentForm.getInstance();
            student.dispose();
            ControlPanelForm.getInstance().setEnabled(true);
            ControlPanelForm.getInstance().setVisible(true);

//            if (Data.getCurrentStudentEnroll() != null) {
//                ScrollPanelInfo.getInstance().getListener().getPressOrNot()[ScrollPanelInfo.getInstance().getListener().getIndex()] = false;
//                ScrollPanelInfo.getInstance().getListener().getCheckBoxPanel()[ScrollPanelInfo.getInstance().getListener().getIndex()].setBackground(Color.WHITE);
//                ScrollPanelInfo.getInstance().getListener().getTypePanel()[ScrollPanelInfo.getInstance().getListener().getIndex()].setBackground(Color.WHITE);
//                ScrollPanelInfo.getInstance().getListener().getInfoPanel()[ScrollPanelInfo.getInstance().getListener().getIndex()].setBackground(Color.WHITE);
//                ScrollPanelInfo.getInstance().getListener().getDatePanel()[ScrollPanelInfo.getInstance().getListener().getIndex()].setBackground(Color.WHITE);
//                ScrollPanelInfo.getInstance().getListener().getStatusPanel()[ScrollPanelInfo.getInstance().getListener().getIndex()].setBackground(Color.WHITE);
//                PersonalInfo.getInstance().setAllLabelsEmpty();//remove old info of personalPanel
//            }
            Data.setCurrentStudentEnroll(Data.getCurrentStudentEnrollTemp());

        } else if (but.getText().equals("Browse") || but.getText().equals("Đính Kèm")) {
            //Set up the file chooser.
            if (fc == null) {
                fc = new JFileChooser();

                //Add a custom file filter and disable the default
                //(Accept All) file filter.
                fc.addChoosableFileFilter(new ImageFilter());
                fc.setAcceptAllFileFilterUsed(false);

                //Add custom icons for file types.
                fc.setFileView(new ImageFileView());

                //Add the preview pane.
                fc.setAccessory(new ImagePreview(fc));
            }

            //Show it.
            int returnVal = fc.showDialog(fc, "Attach");

            //Process the results.
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println(fc.getSelectedFile().getPath());
                StudentForm student = StudentForm.getInstance();
                try {

                    BufferedImage originalImage = ImageIO.read(new File(fc.getSelectedFile().getPath()));
                    int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
                    BufferedImage resizeImageJpg = resizeImage(originalImage, type);
                    ImageIO.write(resizeImageJpg, "jpg", new File(fc.getSelectedFile().getPath() + "_new.jpg"));



                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                String source = fc.getSelectedFile().getPath();
                //directory where file will be copied
                String workingDir = System.getProperty("user.dir");
                System.out.println("Working Dir: " + workingDir);
                String target = workingDir + "\\src\\Images\\";
                System.out.println("Target: " + target);

                //name of source file
                File sourceFile = new File(source);
                String name = fc.getSelectedFile().getName();
                File targetFile = new File(target + name);

                try {
                    //copy file from one location to other
                    FileUtils.copyFile(sourceFile, targetFile);
                    System.out.println("Copy Working");
                } catch (IOException ex) {
                }

                String newFile = target + name;
                System.out.println("New File: " + newFile);
                student.setImagePath(newFile);
                //manager.setImagePath(fc.getSelectedFile().getPath());
                setModify();
            }



        } else if (but.getText().equals("Enroll") || but.getText().equals("Ghi Danh")) {
            Data.setCurrentStudentEnrollTemp(Data.getCurrentStudentEnroll());
            Data.setCurrentStudentEnroll(null);
            EnrollmentForm.setUnique(null);
            EnrollmentForm enroll = EnrollmentForm.getInstance();
            enroll.getTimeTable().getTimeController().setEnOrAs("enroll");
            enroll.getEnCon().setDeter("inside");
            String name = StudentForm.getInstance().getFirstnameR().getText() + " " + StudentForm.getInstance().getLastnameR().getText();
            enroll.getNames().setText(name);
            enroll.getIdR().setText(StudentForm.getInstance().getIdR().getText());
            enroll.initialize("Set");
            StudentForm stuForm = StudentForm.getInstance();
            for (Classes cla : stuForm.getClasses()) {
                String total = cla.getId() + " " + cla.getClassName() + " " + cla.getClassType();
                enroll.getEnrolled().add(total);
            }
            enroll.getEnCon().setModify();

//            Data.setCurrentStudentEnroll(new Student(stuForm.getIdR().getText(), stuForm.getFirstnameR().getText(), stuForm.getLastnameR().getText(),
//                    stuForm.getDobDay().getSelectedItem().toString(), stuForm.getDobMonth().getSelectedItem().toString(),
//                    stuForm.getDobYear().getSelectedItem().toString(), stuForm.getGenderR().getSelectedItem().toString(),
//                    stuForm.getPhoneNumCodeR().getSelectedItem().toString(),
//                    stuForm.getPhoneNumR().getText(), stuForm.getHomeNumCodeR().getSelectedItem().toString(),
//                    stuForm.getHomeNumR().getText(), stuForm.getEmailR().getText(), stuForm.getAddressR().getText(),
//                    stuForm.getStatusR().getText(), stuForm.getInfoArea().getText(), stuForm.getUserType(), Data.getCurrentDate(),
//                    stuForm.getClasses(), stuForm.getImagePath()));

            stuForm.setEnabled(false);
        }
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(120, 100, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 120, 100, null);
        g.dispose();

        return resizedImage;
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        ControlPanelForm.getInstance().setEnabled(true);
        ControlPanelForm.getInstance().setVisible(true);
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    public void setModify() {
        setChanged();
        notifyObservers();
    }
}
