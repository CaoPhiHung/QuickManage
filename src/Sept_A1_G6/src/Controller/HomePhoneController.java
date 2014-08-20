package Controller;

import View.*;
import java.awt.event.*;
import javax.swing.*;

public class HomePhoneController implements KeyListener {

    private int[] validKey = {KeyEvent.VK_0, KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4, KeyEvent.VK_5, KeyEvent.VK_6, KeyEvent.VK_7, KeyEvent.VK_8, KeyEvent.VK_9};
    private ChooseOptions options = ChooseOptions.getInstance();

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() != validKey[0] && e.getKeyCode() != validKey[1] && e.getKeyCode() != validKey[2]
                && e.getKeyCode() != validKey[3] && e.getKeyCode() != validKey[4] && e.getKeyCode() != validKey[5]
                && e.getKeyCode() != validKey[6] && e.getKeyCode() != validKey[7] && e.getKeyCode() != validKey[8]
                && e.getKeyCode() != validKey[9] && e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
            JOptionPane.showMessageDialog(null, "Please enter from 0 - 9");

            //
            if (options.getTypes().getSelectedItem().equals("Manager")) {
                ManagerForm.getInstance().getHomeNumR().setText("");
            } else if (options.getTypes().getSelectedItem().equals("Staff")) {
                StaffForm.getInstance().getHomeNumR().setText("");
            } else if (options.getTypes().getSelectedItem().equals("Teacher")) {
                TeacherForm.getInstance().getHomeNumR().setText("");
            } else if (options.getTypes().getSelectedItem().equals("Student")) {
                StudentForm.getInstance().getHomeNumR().setText("");
            }
            //
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String tempHome = "";

        //
        if (options.getTypes().getSelectedItem().equals("Manager")) {
            tempHome = ManagerForm.getInstance().getHomeNumR().getText();
        } else if (options.getTypes().getSelectedItem().equals("Staff")) {
            tempHome = StaffForm.getInstance().getHomeNumR().getText();
        } else if (options.getTypes().getSelectedItem().equals("Teacher")) {
            tempHome = TeacherForm.getInstance().getHomeNumR().getText();
        } else if (options.getTypes().getSelectedItem().equals("Student")) {
            tempHome = StudentForm.getInstance().getHomeNumR().getText();
        }
        //

        String[] homeSplit = tempHome.split("-");
        String afterHome = "";
        for (String st : homeSplit) {
            afterHome = afterHome + st;
        }

        int sizeHome = afterHome.length();

        if (sizeHome % 4 == 0 && sizeHome <= 7) {
            if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                //
                if (options.getTypes().getSelectedItem().equals("Manager")) {
                    ManagerForm.getInstance().getHomeNumR().setText(tempHome + "-");
                } else if (options.getTypes().getSelectedItem().equals("Staff")) {
                    StaffForm.getInstance().getHomeNumR().setText(tempHome + "-");
                } else if (options.getTypes().getSelectedItem().equals("Teacher")) {
                    TeacherForm.getInstance().getHomeNumR().setText(tempHome + "-");
                } else if (options.getTypes().getSelectedItem().equals("Student")) {
                    StudentForm.getInstance().getHomeNumR().setText(tempHome + "-");
                }
                //
            }
        }

        if (sizeHome == 8) {
            JOptionPane.showMessageDialog(null, "Enough for Home Phone");
        }

        if (sizeHome >= 9) {
            //
            if (options.getTypes().getSelectedItem().equals("Manager")) {
                ManagerForm.getInstance().getHomeNumR().setText("");
            } else if (options.getTypes().getSelectedItem().equals("Staff")) {
                StaffForm.getInstance().getHomeNumR().setText("");
            } else if (options.getTypes().getSelectedItem().equals("Teacher")) {
                TeacherForm.getInstance().getHomeNumR().setText("");
            } else if (options.getTypes().getSelectedItem().equals("Student")) {
                StudentForm.getInstance().getHomeNumR().setText("");
            }
            //
            JOptionPane.showMessageDialog(null, "The Home Phone should be xxxx-xxxx");
        }
    }
}
