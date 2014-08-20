package Controller;

import Model.*;
import View.*;
import java.awt.event.*;
import javax.swing.*;

public class ClassFormController implements ActionListener, WindowListener, KeyListener {

    private boolean checkTimeResult = true;
    private int lengthTemp = 0;
    int[] validKey = {KeyEvent.VK_0, KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4, KeyEvent.VK_5, KeyEvent.VK_6, KeyEvent.VK_7, KeyEvent.VK_8, KeyEvent.VK_9};

    @Override
    public void actionPerformed(ActionEvent ae) {

        ClassForm cf = ClassForm.getInstance();
        DataValidation dv = new DataValidation();
        if (ae.getSource() instanceof JButton) {

            JButton but = (JButton) ae.getSource();

            if (but.getText().equalsIgnoreCase("Add") || but.getText().equalsIgnoreCase("Thêm")) {
                Data data = new Data();
                if (ClassForm.getInstance().getOption().equalsIgnoreCase("add") || ClassForm.getInstance().getOption().equalsIgnoreCase("Thêm")) {
                    if (ClassForm.numberChosenLesson != ClassForm.numberOfLesson) {
                        JOptionPane.showMessageDialog(null, "Not equal number of lessons");
                        return;
                    }
                    dv.validateClassForm(ClassForm.getInstance());
                } else if (ClassForm.getInstance().getOption().equalsIgnoreCase("edit")) {
                    if (ClassForm.numberChosenLesson != ClassForm.numberOfLesson) {
                        JOptionPane.showMessageDialog(null, "Not equal number of lessons");
                        return;
                    }
                    String[] Days = new String[7];
                    String[] Froms = new String[7];
                    String[] Tos = new String[7];
                    String[] Roms = new String[7];
                    if (ClassForm.getInstance().getMon().isSelected()) {
                        Days[0] = "Mon";
                        Froms[0] = ClassForm.getInstance().getMonStartTime().getSelectedItem().toString();
                        Tos[0] = ClassForm.getInstance().getMonEndTime().getSelectedItem().toString();
                        Roms[0] = ClassForm.getInstance().getMonRoom().getSelectedItem().toString();
                    } else {
                        Days[0] = "";
                        Froms[0] = "09:00";
                        Tos[0] = "09:00";
                        Roms[0] = "";
                    }

                    if (ClassForm.getInstance().getTue().isSelected()) {
                        Days[1] = "Tue";
                        Froms[1] = ClassForm.getInstance().getTueStartTime().getSelectedItem().toString();
                        Tos[1] = ClassForm.getInstance().getTueEndTime().getSelectedItem().toString();
                        Roms[1] = ClassForm.getInstance().getTueRoom().getSelectedItem().toString();
                    } else {
                        Days[1] = "";
                        Froms[1] = "09:00";
                        Tos[1] = "09:00";
                        Roms[1] = "";
                    }

                    if (ClassForm.getInstance().getWed().isSelected()) {
                        Days[2] = "Wed";
                        Froms[2] = ClassForm.getInstance().getWedStartTime().getSelectedItem().toString();
                        Tos[2] = ClassForm.getInstance().getWedEndTime().getSelectedItem().toString();
                        Roms[2] = ClassForm.getInstance().getWedRoom().getSelectedItem().toString();
                    } else {
                        Days[2] = "";
                        Froms[2] = "09:00";
                        Tos[2] = "09:00";
                        Roms[2] = "";
                    }


                    if (ClassForm.getInstance().getThu().isSelected()) {
                        Days[3] = "Thu";
                        Froms[3] = ClassForm.getInstance().getThuStartTime().getSelectedItem().toString();
                        Tos[3] = ClassForm.getInstance().getThuEndTime().getSelectedItem().toString();
                        Roms[3] = ClassForm.getInstance().getThuRoom().getSelectedItem().toString();
                    } else {
                        Days[3] = "";
                        Froms[3] = "09:00";
                        Tos[3] = "09:00";
                        Roms[3] = "";
                    }

                    if (ClassForm.getInstance().getFri().isSelected()) {
                        Days[4] = "Fri";
                        Froms[4] = ClassForm.getInstance().getFriStartTime().getSelectedItem().toString();
                        Tos[4] = ClassForm.getInstance().getFriEndTime().getSelectedItem().toString();
                        Roms[4] = ClassForm.getInstance().getFriRoom().getSelectedItem().toString();
                    } else {
                        Days[4] = "";
                        Froms[4] = "09:00";
                        Tos[4] = "09:00";
                        Roms[4] = "";
                    }

                    if (ClassForm.getInstance().getSat().isSelected()) {
                        Days[5] = "Sat";
                        Froms[5] = ClassForm.getInstance().getSatStartTime().getSelectedItem().toString();
                        Tos[5] = ClassForm.getInstance().getSatEndTime().getSelectedItem().toString();
                        Roms[5] = ClassForm.getInstance().getSatRoom().getSelectedItem().toString();
                    } else {
                        Days[5] = "";
                        Froms[5] = "09:00";
                        Tos[5] = "09:00";
                        Roms[5] = "";
                    }

                    if (ClassForm.getInstance().getSun().isSelected()) {
                        Days[6] = "Sun";
                        Froms[6] = ClassForm.getInstance().getSunStartTime().getSelectedItem().toString();
                        Tos[6] = ClassForm.getInstance().getSunEndTime().getSelectedItem().toString();
                        Roms[6] = ClassForm.getInstance().getSunRoom().getSelectedItem().toString();
                    } else {
                        Days[6] = "";
                        Froms[6] = "09:00";
                        Tos[6] = "09:00";
                        Roms[6] = "";
                    }

                    ((Classes) Data.getCurrentEditClass()).setClassName(cf.getNameText().getText());
                    ((Classes) Data.getCurrentEditClass()).setClassCode(cf.getCodeText().getText());
                    ((Classes) Data.getCurrentEditClass()).setStartDate(cf.getDateChooser1().getDate());
                    ((Classes) Data.getCurrentEditClass()).setEndDate(cf.getDateChooser2().getDate());
                    for (ClassType ct : Data.classTypeList) {
                        if (ct.getName().equals(cf.getTypeCB().getSelectedItem().toString())) {
                            ((Classes) Data.getCurrentEditClass()).setClassType(ct);
                        }
                    }

                    ((Classes) Data.getCurrentEditClass()).setTuitionFee(cf.getTuitionFeeR().getText());
                    ((Classes) Data.getCurrentEditClass()).setDays(Days);
                    ((Classes) Data.getCurrentEditClass()).setFroms(Froms);
                    ((Classes) Data.getCurrentEditClass()).setTos(Tos);
                    ((Classes) Data.getCurrentEditClass()).setRooms(Roms);

                    //Loop through student
                    for (int i = 0; i < Data.studentList.size(); i++) {
                        for (int j = 0; j < Data.studentList.get(i).getClasses().size(); j++) {
                            if (Data.studentList.get(i).getClasses().get(j).getId().equals(Data.getCurrentEditClass().getId())) {
                                Data.studentList.get(i).getClasses().remove(j);
                                Data.studentList.get(i).getClasses().add(Data.getCurrentEditClass());
                            }
                        }
                    }

                    //Loop through teacher
                    for (int i = 0; i < Data.teacherList.size(); i++) {
                        for (int j = 0; j < Data.teacherList.get(i).getClasses().size(); j++) {
                            if (Data.teacherList.get(i).getClasses().get(j).getId().equals(Data.getCurrentEditClass().getId())) {
                                Data.teacherList.get(i).getClasses().remove(j);
                                Data.teacherList.get(i).getClasses().add(Data.getCurrentEditClass());
                            }
                        }
                    }
                    dv.validateClassForm(ClassForm.getInstance());
                }
//
//
//                    ClassForm.getInstance().dispose();
//                    ControlPanelForm.getInstance().setEnabled(true);
//                    ControlPanelForm.getInstance().setVisible(true);
//                } else {
//                    JOptionPane.showMessageDialog(null, "Invalid inputs");
//                    checkTimeResult = true; // return finalResult to initial state
//                }

            } else if (but.getText().equalsIgnoreCase("Cancel") || but.getText().equalsIgnoreCase("Hủy")) {
                ClassForm classForm = ClassForm.getInstance();
                classForm.dispose();
                ControlPanelForm.getInstance().setEnabled(true);
                ControlPanelForm.getInstance().setVisible(true);
            } else if (but.getText().equalsIgnoreCase("Add Student") || but.getText().equalsIgnoreCase("Thêm Học Sinh")) {
                StudentListView.setUnique(null);
                StudentListView.getInstance().initialize();
            } else if (but.getText().equalsIgnoreCase("Assign Teacher") || but.getText().equalsIgnoreCase("Bổ Dụng Giáo Viên")) {
                TeacherListView.setUnique(null);
                TeacherListView.getInstance().initialize();
            }
        } else if (ae.getSource() instanceof JCheckBox) {
            JCheckBox check = (JCheckBox) ae.getSource();
            if (check.isSelected()) {
                ClassForm.numberChosenLesson++;
            } else {
                ClassForm.numberChosenLesson--;
            }
            System.out.println("Class Form Number chosen " + ClassForm.numberChosenLesson);
            System.out.println("Class Form current number lesson " + ClassForm.numberOfLesson);
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

            ClassForm.getInstance().getTuitionFeeR().setText("");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String tempFee = ClassForm.getInstance().getTuitionFeeR().getText();

        String[] feeSplit = tempFee.split(",");
        String afterFee = "";
        for (String st : feeSplit) {
            afterFee = afterFee + st;
        }

        int sizeFee = afterFee.length();

        if (sizeFee % 3 == 0) {
            if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                ClassForm.getInstance().getTuitionFeeR().setText(tempFee + ",");
            }

        }
    }
}
