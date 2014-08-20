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

public class ManagerFormController extends Observable implements ActionListener, WindowListener {

    private JFileChooser fc;

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton but = (JButton) ae.getSource();
        ManagerForm mf = ManagerForm.getInstance();
        DataValidation dv = new DataValidation();
        if (but.getText().equalsIgnoreCase("Add") || but.getText().equalsIgnoreCase("Thêm")) {
            Data data = new Data();
            // add manager into system
            if (ManagerForm.getInstance().getOption().equalsIgnoreCase("add") || ManagerForm.getInstance().getOption().equalsIgnoreCase("Thêm")) {
                System.out.println("adding new manager");
                dv.validateManagerForm(ManagerForm.getInstance());
            } else if (ManagerForm.getInstance().getOption().equalsIgnoreCase("edit")) {
                dv.validateManagerForm(ManagerForm.getInstance());
            }

        } else if (but.getText().equalsIgnoreCase("Cancel") || but.getText().equalsIgnoreCase("Hủy")) {
            ManagerForm manager = ManagerForm.getInstance();
            manager.dispose();
            ControlPanelForm.getInstance().setEnabled(true);
            ControlPanelForm.getInstance().setVisible(true);
        } else if (but.getText().equals("Browse") || but.getText().equals("Đính Kèm")) {
            // Set up the file chooser

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
                ManagerForm manager = ManagerForm.getInstance();

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
                manager.setImagePath(newFile);
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
