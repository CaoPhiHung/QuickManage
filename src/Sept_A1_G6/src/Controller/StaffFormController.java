package Controller;

import Model.*;
import View.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import org.apache.commons.io.FileUtils;

public class StaffFormController extends Observable implements ActionListener, WindowListener {

    private JFileChooser fc;

    @Override
    public void actionPerformed(ActionEvent ae) {
        StaffForm sf = StaffForm.getInstance();
        JButton but = (JButton) ae.getSource();
        DataValidation dv = new DataValidation();

        if (but.getText().equalsIgnoreCase("Add") || but.getText().equalsIgnoreCase("Thêm")) {
            Data data = new Data();
            // validate user inputs
            if (StaffForm.getInstance().getOption().equalsIgnoreCase("add") || StaffForm.getInstance().getOption().equalsIgnoreCase("Thêm")) {
                dv.validateStaffForm(StaffForm.getInstance());
            } else if (StaffForm.getInstance().getOption().equalsIgnoreCase("edit")) {
                ((Staff) Data.getCurrentEditUser()).setFirstName(sf.getFirstnameR().getText());
                ((Staff) Data.getCurrentEditUser()).setLastName(sf.getLastnameR().getText());
                ((Staff) Data.getCurrentEditUser()).setDobDate(sf.getDobDay().getSelectedItem().toString());
                ((Staff) Data.getCurrentEditUser()).setDobMonth(sf.getDobMonth().getSelectedItem().toString());
                ((Staff) Data.getCurrentEditUser()).setDobYear(sf.getDobYear().getSelectedItem().toString());
                ((Staff) Data.getCurrentEditUser()).setGender(sf.getGenderR().getSelectedItem().toString());
                ((Staff) Data.getCurrentEditUser()).setHomeNoCode(sf.getHomeNumCodeR().toString());
                ((Staff) Data.getCurrentEditUser()).setHomeNo(sf.getHomeNumR().getText());
                ((Staff) Data.getCurrentEditUser()).setPhoneNoCode(sf.getPhoneNumCodeR().getSelectedItem().toString());
                ((Staff) Data.getCurrentEditUser()).setPhoneNo(sf.getPhoneNumR().getText());
                ((Staff) Data.getCurrentEditUser()).setEmail(sf.getEmailR().getText());
                ((Staff) Data.getCurrentEditUser()).setAddress(sf.getAddressR().getText());
                ((Staff) Data.getCurrentEditUser()).setStatus(sf.getStatusR().getText());
                ((Staff) Data.getCurrentEditUser()).setUsername(sf.getUserName().getText());
                ((Staff) Data.getCurrentEditUser()).setTypingPassword(sf.getTypingPassword().getPassword());
                ((Staff) Data.getCurrentEditUser()).setDescription(sf.getInfoArea().getText());
                ((Staff) Data.getCurrentEditUser()).setImageLink(sf.getImagePath());
                dv.validateStaffForm(StaffForm.getInstance());

            }

        } else if (but.getText().equalsIgnoreCase("Cancel") || but.getText().equalsIgnoreCase("Hủy")) {
            StaffForm staff = StaffForm.getInstance();
            staff.dispose();
            ControlPanelForm.getInstance().setEnabled(true);
            ControlPanelForm.getInstance().setVisible(true);
        } else if (but.getText().equalsIgnoreCase("Browse") || but.getText().equalsIgnoreCase("Đính Kèm")) {
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
                StaffForm staff = StaffForm.getInstance();

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
                staff.setImagePath(newFile);
                //manager.setImagePath(fc.getSelectedFile().getPath());
                setModify();
            }
        }
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(120, 100, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 120, 100, null);
        g.dispose();

        return resizedImage;
    }

    public void setModify() {
        setChanged();
        notifyObservers();
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
}
