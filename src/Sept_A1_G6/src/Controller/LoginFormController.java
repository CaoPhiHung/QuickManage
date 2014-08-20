package Controller;

import Custom.*;
import Model.*;
import View.*;
import View.ControlPanelFormUtilities.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.*;

public class LoginFormController implements MouseListener, ActionListener, KeyListener, WindowListener {

    private static int remember = 0;

    @Override
    public void mouseClicked(MouseEvent e) {
        SuperButton but = (SuperButton) e.getSource();
        //If user click "Log in" it will go to ControlPanelForm
        if (but.getFrontText().getText().equalsIgnoreCase("Log in") || but.getFrontText().getText().equalsIgnoreCase("Đăng nhập")) {
            getLogin();

            //If user click exit it will exit the program
        } else if (but.getFrontText().getText().equalsIgnoreCase("Exit") || but.getFrontText().getText().equalsIgnoreCase("Thoát")) {
            System.exit(0);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem = (JMenuItem) e.getSource();
        if (menuItem.getText().equalsIgnoreCase("Close") || menuItem.getText().equalsIgnoreCase("Đóng")) {
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            getLogin();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    //The function to look at user in Data arraylist of Manager and Staff and log the user in
    private void getLogin() {
        String user = LoginForm.getInstance().getUsernameF().getText();
        char[] passInChar = LoginForm.getInstance().getPasswordF().getPassword();
        boolean connected = false;
        boolean checkManager = false;
        boolean checkStaff = false;

        //check login with activate account
        for (int i = 0; i < Data.managerList.size(); i++) {
            if (Data.managerList.get(i).getUsername().equals(user) && Data.managerList.get(i).getStatus().equalsIgnoreCase("Activate")
                    && Arrays.equals(Data.managerList.get(i).getTypingPassword(), passInChar)) {
                Data.setCurrentUser(Data.managerList.get(i));
                connected = true;
                break;
            }
        }

        for (int i = 0; i < Data.staffList.size(); i++) {
            if (Data.staffList.get(i).getUsername().equals(user) && Data.staffList.get(i).getStatus().equalsIgnoreCase("Activate")
                    && Arrays.equals(Data.staffList.get(i).getTypingPassword(), passInChar)) {
                Data.setCurrentUser(Data.staffList.get(i));
                connected = true;
                break;
            }
        }

        //check login with deactivate account
        for (int i = 0; i < Data.managerList.size(); i++) {
            if (Data.managerList.get(i).getUsername().equals(user) && Data.managerList.get(i).getStatus().equalsIgnoreCase("Deactivate")
                    && Arrays.equals(Data.managerList.get(i).getTypingPassword(), passInChar)) {
                Data.setCurrentUser(Data.managerList.get(i));
                checkManager = true;
                break;
            }
        }

        for (int i = 0; i < Data.staffList.size(); i++) {
            if (Data.staffList.get(i).getUsername().equals(user) && Data.staffList.get(i).getStatus().equalsIgnoreCase("Deactivate")
                    && Arrays.equals(Data.staffList.get(i).getTypingPassword(), passInChar)) {
                Data.setCurrentUser(Data.staffList.get(i));
                checkStaff = true;
                break;
            }
        }

        if (connected) {

            System.out.println("Logged in");
            if (remember == 0) {
                LoginForm.getInstance().dispose();
                ControlPanelForm.getInstance().initialize();
            } else {
                LoginForm.getInstance().dispose();
                ControlPanelForm.getInstance().removeAll();
                ControlPanelForm.setUnique(null);
                ScrollPanelInfo.setUnique(null);
                PersonalInfo.setUnique(null);
                ControlPanelForm.getInstance().initialize();
                ScrollPanelInfo.getInstance().removeScrollPane();
            }
            remember = 1;
            PersonalInfo.getInstance().setAllLabelsEmpty();
            
            //Calculate ratio
            double totalOccupiedMinute = 0;
            for (Room ro : Data.roomList) {
                totalOccupiedMinute = totalOccupiedMinute + ro.getOccupiedMinute();
            }
            Data.totalOccupiedMinutes = totalOccupiedMinute;
            Data.totalAvailableMinutes = Data.defaultAvailableHours - Data.totalOccupiedMinutes;
            
            Data.ratio = Data.totalOccupiedMinutes / Data.totalAvailableMinutes;
            
            
            
            DashBoardView.setUnique(null);
            DashBoardView.getInstance().initialize();
        } else if (checkManager) {
            JOptionPane.showMessageDialog(null, "This Manager is already deactivated");
        } else if (checkStaff) {
            JOptionPane.showMessageDialog(null, "This Staff is already deactivated");
        } else {
            JOptionPane.showMessageDialog(null, "Wrong username or password");
        }
    }

    @Override
    public void windowOpened(WindowEvent we) {
    }

    @Override
    public void windowClosing(WindowEvent we) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent we) {
    }

    @Override
    public void windowIconified(WindowEvent we) {
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
    }

    @Override
    public void windowActivated(WindowEvent we) {
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }
}
