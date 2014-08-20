package Controller;

import View.*;
import java.awt.event.*;
import javax.swing.*;

public class MobilePhoneController implements KeyListener {

    private int[] validKey = {KeyEvent.VK_0, KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4, KeyEvent.VK_5, KeyEvent.VK_6, KeyEvent.VK_7, KeyEvent.VK_8, KeyEvent.VK_9, KeyEvent.VK_ENTER};
    private ChooseOptions options = ChooseOptions.getInstance();

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() != validKey[0] && e.getKeyCode() != validKey[1] && e.getKeyCode() != validKey[2]
                && e.getKeyCode() != validKey[3] && e.getKeyCode() != validKey[4] && e.getKeyCode() != validKey[5]
                && e.getKeyCode() != validKey[6] && e.getKeyCode() != validKey[7] && e.getKeyCode() != validKey[8]
                && e.getKeyCode() != validKey[9] && e.getKeyCode() != KeyEvent.VK_BACK_SPACE && e.getKeyCode() != KeyEvent.VK_ENTER) {
            JOptionPane.showMessageDialog(null, "Please enter from 0 - 9");
            JTextField jtf = (JTextField) e.getSource();
            if (jtf.getName().equalsIgnoreCase("contactPhone")) {
                //
                if (options.getTypes().getSelectedItem().equals("Student")) {
                    StudentForm.getInstance().getContactPhoneR().setText("");
                }
                //
            } else if (jtf.getName().equalsIgnoreCase("phoneNumR")) {
                //
                if (options.getTypes().getSelectedItem().equals("Manager")) {
                    ManagerForm.getInstance().getPhoneNumR().setText("");
                } else if (options.getTypes().getSelectedItem().equals("Staff")) {
                    StaffForm.getInstance().getPhoneNumR().setText("");
                } else if (options.getTypes().getSelectedItem().equals("Teacher")) {
                    TeacherForm.getInstance().getPhoneNumR().setText("");
                } else if (options.getTypes().getSelectedItem().equals("Student")) {
                    StudentForm.getInstance().getPhoneNumR().setText("");
                }
                //
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String tempMobile = "";
        String tempContactMobile = "";
        JTextField jtf = (JTextField) e.getSource();
        if (jtf.getName().equalsIgnoreCase("contactPhone")) {
            tempContactMobile = StudentForm.getInstance().getContactPhoneR().getText();
        } else if (jtf.getName().equalsIgnoreCase("phoneNumR")) {
            if (options.getTypes().getSelectedItem().equals("Manager")) {
                tempMobile = ManagerForm.getInstance().getPhoneNumR().getText();
            } else if (options.getTypes().getSelectedItem().equals("Staff")) {
                tempMobile = StaffForm.getInstance().getPhoneNumR().getText();
            } else if (options.getTypes().getSelectedItem().equals("Teacher")) {
                tempMobile = TeacherForm.getInstance().getPhoneNumR().getText();
            } else if (options.getTypes().getSelectedItem().equals("Student")) {
                tempMobile = StudentForm.getInstance().getPhoneNumR().getText();
            }
        }

        String[] mobileSplit = tempMobile.split("-");
        String[] contactMobileSplit = tempContactMobile.split("-");
        String afterMobile = "";
        String afterContactMobile = "";
        for (String st : mobileSplit) {
            afterMobile = afterMobile + st;
        }
        for (String st : contactMobileSplit) {
            afterContactMobile = afterContactMobile + st;
        }

        int sizeMobile = afterMobile.length();
        int sizeContactMobile = afterContactMobile.length();

        if (sizeMobile == 3 || sizeContactMobile == 3) {
            if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                if (jtf.getName().equalsIgnoreCase("phoneNumR")) {
                    //
                    if (options.getTypes().getSelectedItem().equals("Manager")) {
                        ManagerForm.getInstance().getPhoneNumR().setText(tempMobile + "-");
                    } else if (options.getTypes().getSelectedItem().equals("Staff")) {
                        StaffForm.getInstance().getPhoneNumR().setText(tempMobile + "-");
                    } else if (options.getTypes().getSelectedItem().equals("Teacher")) {
                        TeacherForm.getInstance().getPhoneNumR().setText(tempMobile + "-");
                    } else if (options.getTypes().getSelectedItem().equals("Student")) {
                        StudentForm.getInstance().getPhoneNumR().setText(tempMobile + "-");
                    }
                    //
                } else if (jtf.getName().equalsIgnoreCase("contactPhone")) {
                    StudentForm.getInstance().getContactPhoneR().setText(tempContactMobile + "-");
                }
            }
        }
        if (sizeMobile == 7 || sizeContactMobile == 7) {
            JOptionPane.showMessageDialog(null, "Enough for Mobile Phone");
        }

        if (sizeMobile >= 8 || sizeContactMobile >= 8) {
            if (jtf.getName().equalsIgnoreCase("phoneNumR")) {
                //
                if (options.getTypes().getSelectedItem().equals("Manager")) {
                    ManagerForm.getInstance().getPhoneNumR().setText("");
                } else if (options.getTypes().getSelectedItem().equals("Staff")) {
                    StaffForm.getInstance().getPhoneNumR().setText("");
                } else if (options.getTypes().getSelectedItem().equals("Teacher")) {
                    TeacherForm.getInstance().getPhoneNumR().setText("");
                } else if (options.getTypes().getSelectedItem().equals("Student")) {
                    StudentForm.getInstance().getPhoneNumR().setText("");
                }
                //
            } else if (jtf.getName().equalsIgnoreCase("contactPhone")) {
                StudentForm.getInstance().getContactPhoneR().setText("");
            }
            JOptionPane.showMessageDialog(null, "The Mobile Phone should be xxx-xxxx");
        }
    }
}
