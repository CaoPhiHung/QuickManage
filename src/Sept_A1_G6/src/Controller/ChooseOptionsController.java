package Controller;

import View.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class ChooseOptionsController implements ActionListener, WindowListener {

    ResourceBundle resources;

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton but = (JButton) ae.getSource();
        resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
        if (but.getText().equalsIgnoreCase(resources.getString("Add1"))) {

            ChooseOptions options = ChooseOptions.getInstance();
            if (options.getTypes().getSelectedItem().equals("Class") || options.getTypes().getSelectedItem().equals("Lớp")) {
                ClassForm.setUnique(null);
                ClassForm classForm = ClassForm.getInstance();
                classForm.initialize();
                classForm.setOption("add");
            } else if (options.getTypes().getSelectedItem().equals("Manager") || options.getTypes().getSelectedItem().equals("Quản Lí")) {
                ManagerForm.setUnique(null);
                ManagerForm manager = ManagerForm.getInstance();
                manager.managerInitialize();
                manager.setOption("add");
            } else if (options.getTypes().getSelectedItem().equals("Staff") || options.getTypes().getSelectedItem().equals("Nhân Viên")) {
                StaffForm.setUnique(null);
                StaffForm staff = StaffForm.getInstance();
                staff.staffInitialize();
                staff.setOption("add");
            } else if (options.getTypes().getSelectedItem().equals("Teacher") || options.getTypes().getSelectedItem().equals("Giáo Viên")) {
                TeacherForm.setUnique(null);
                TeacherForm teacher = TeacherForm.getInstance();
                teacher.teacherInitialize();
                teacher.setOption("add");
                teacher.getCancel().setBounds(270, 620, 80, 25);
                teacher.getAssignButton().setVisible(true);
            } else if (options.getTypes().getSelectedItem().equals("Student") || options.getTypes().getSelectedItem().equals("Học Sinh")) {
                StudentForm.setUnique(null);
                StudentForm student = StudentForm.getInstance();
                student.studentInitialize();
                student.setOption("add");
                student.getCancel().setBounds(400, 450, 100, 25);
                student.getEnrollButton().setVisible(true);
            } else if (options.getTypes().getSelectedItem().equals("Class Type")) {
                ClassTypeForm.setUnique(null);
                ClassTypeForm classType = ClassTypeForm.getInstance();
                classType.initialize();
                classType.setOption("add");
            } else if (options.getTypes().getSelectedItem().equals("Room")) {
                RoomForm.setUnique(null);
                RoomForm roomForm = RoomForm.getInstance();
                roomForm.initialize();
                roomForm.setOption("add");
            }
            options.dispose();

        } else if (but.getText().equalsIgnoreCase("Cancel") || but.getText().equalsIgnoreCase("Hủy")) {
            ChooseOptions.getInstance().setVisible(false);
            ChooseOptions.getInstance().dispose();
            ControlPanelForm.getInstance().setEnabled(true);
            ControlPanelForm.getInstance().setVisible(true);

        }
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
