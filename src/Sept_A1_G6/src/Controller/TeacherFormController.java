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

public class TeacherFormController extends Observable implements ActionListener, WindowListener {

    private JFileChooser fc;
    private boolean[] checkResults = new boolean[6];
    private boolean finalResult = true;

    @Override
    public void actionPerformed(ActionEvent ae) {
        TeacherForm tf = TeacherForm.getInstance();
        JButton but = (JButton) ae.getSource();
        DataValidation dv = new DataValidation();

        if (but.getText().equalsIgnoreCase("Add") || but.getText().equalsIgnoreCase("Thêm")) {
            Data data = new Data();
            // checkResult data from staff form

            if (TeacherForm.getInstance().getOption().equalsIgnoreCase("add") || TeacherForm.getInstance().getOption().equalsIgnoreCase("Thêm")) {
                dv.validateTeacherForm(TeacherForm.getInstance());
            } else if (TeacherForm.getInstance().getOption().equalsIgnoreCase("edit")) {

                ArrayList<String> tempSkill = new ArrayList<>();
                ArrayList<String> tempHourlyRate = new ArrayList<>();
                for (int i = 0; i < TeacherForm.getInstance().getNumberOfSkill(); i++) {
                    if (TeacherForm.getInstance().getAllSkill()[i].isSelected()) {
                        tempSkill.add(TeacherForm.getInstance().getAllSkill()[i].getText());
                        tempHourlyRate.add(TeacherForm.getInstance().getHourlyPaid()[i].getText());
                    }
                }
                ((Teacher) Data.getCurrentEditUser()).setFirstName(tf.getFirstnameR().getText());
                ((Teacher) Data.getCurrentEditUser()).setLastName(tf.getLastnameR().getText());
                ((Teacher) Data.getCurrentEditUser()).setDobDate(tf.getDobDay().getSelectedItem().toString());
                ((Teacher) Data.getCurrentEditUser()).setDobMonth(tf.getDobMonth().getSelectedItem().toString());
                ((Teacher) Data.getCurrentEditUser()).setDobYear(tf.getDobYear().getSelectedItem().toString());
                ((Teacher) Data.getCurrentEditUser()).setGender(tf.getGenderR().getSelectedItem().toString());
                ((Teacher) Data.getCurrentEditUser()).setHomeNoCode(tf.getHomeNumCodeR().toString());
                ((Teacher) Data.getCurrentEditUser()).setHomeNo(tf.getHomeNumR().getText());
                ((Teacher) Data.getCurrentEditUser()).setPhoneNoCode(tf.getPhoneNumCodeR().getSelectedItem().toString());
                ((Teacher) Data.getCurrentEditUser()).setPhoneNo(tf.getPhoneNumR().getText());
                ((Teacher) Data.getCurrentEditUser()).setEmail(tf.getEmailR().getText());
                ((Teacher) Data.getCurrentEditUser()).setAddress(tf.getAddressR().getText());
                ((Teacher) Data.getCurrentEditUser()).setStatus(tf.getStatusR().getText());
                ((Teacher) Data.getCurrentEditUser()).setDescription(tf.getInfoArea().getText());
                ((Teacher) Data.getCurrentEditUser()).setSkillList(tempSkill);
                ((Teacher) Data.getCurrentEditUser()).setHourlyRate(tempHourlyRate);
                ((Teacher) Data.getCurrentEditUser()).setImageLink(tf.getImagePath());
                dv.validateTeacherForm(TeacherForm.getInstance());
            }

            Data.setCurrentTeacherAssign(Data.getCurrentTeacherAssignTemp());
        } else if (but.getText().equalsIgnoreCase("Cancel") || but.getText().equalsIgnoreCase("Hủy")) {
            TeacherForm teacher = TeacherForm.getInstance();
            teacher.dispose();
            ControlPanelForm.getInstance().setEnabled(true);
            ControlPanelForm.getInstance().setVisible(true);

            Data.setCurrentTeacherAssign(Data.getCurrentTeacherAssignTemp());

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
            TeacherForm teacher = TeacherForm.getInstance();
            int returnVal = fc.showDialog(fc, "Attach");

            //Process the results.
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println(fc.getSelectedFile().getPath());
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
                teacher.setImagePath(newFile);
                //manager.setImagePath(fc.getSelectedFile().getPath());
                setModify();

            }


        } else if (but.getText().equalsIgnoreCase("Assign") || but.getText().equalsIgnoreCase("Bổ Dụng")) {

            Data.setCurrentTeacherAssignTemp(Data.getCurrentTeacherAssign());
            Data.setCurrentTeacherAssign(null);
            AssignmentForm.setUniqueAss(null);
            AssignmentForm assignn = AssignmentForm.getInstanceAss();
            assignn.getTimeTable().getTimeController().setEnOrAs("assign");
            assignn.getAsCon().setDeter("inside");
            String name = TeacherForm.getInstance().getFirstnameR().getText() + " " + TeacherForm.getInstance().getLastnameR().getText();
            assignn.getNames().setText(name);
            assignn.getIdR().setText(TeacherForm.getInstance().getIdR().getText());
            assignn.initialize("Set");
            TeacherForm teaForm = TeacherForm.getInstance();
            for (Classes cla : teaForm.getClasses()) {
                String total = cla.getId() + " " + cla.getClassName() + " " + cla.getClassType();
                assignn.getEnrolled().add(total);
            }
            assignn.getAsCon().setModify();

//            Data.setCurrentStudentEnroll(new Student(stuForm.getIdR().getText(), stuForm.getFirstnameR().getText(), stuForm.getLastnameR().getText(),
//                    stuForm.getDobDay().getSelectedItem().toString(), stuForm.getDobMonth().getSelectedItem().toString(),
//                    stuForm.getDobYear().getSelectedItem().toString(), stuForm.getGenderR().getSelectedItem().toString(),
//                    stuForm.getPhoneNumCodeR().getSelectedItem().toString(),
//                    stuForm.getPhoneNumR().getText(), stuForm.getHomeNumCodeR().getSelectedItem().toString(),
//                    stuForm.getHomeNumR().getText(), stuForm.getEmailR().getText(), stuForm.getAddressR().getText(),
//                    stuForm.getStatusR().getText(), stuForm.getInfoArea().getText(), stuForm.getUserType(), Data.getCurrentDate(),
//                    stuForm.getClasses(), stuForm.getImagePath()));

            teaForm.setEnabled(false);
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
