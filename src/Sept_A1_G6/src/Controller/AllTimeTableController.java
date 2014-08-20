package Controller;

import Model.*;
import View.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class AllTimeTableController extends Observable implements ActionListener, ListSelectionListener, WindowListener {

    private Data dat = new Data();

    @Override
    public void actionPerformed(ActionEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
        if (e.getSource() instanceof JButton) {
            JButton but = (JButton) e.getSource();
            if (but.getText().equals("Close") || but.getText().equals("Đóng")) {
                AllTimeTable.getInstance().dispose();
                ControlPanelForm.getInstance().setEnabled(true);
                ControlPanelForm.getInstance().setVisible(true);
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList li = (JList) e.getSource();
        String item = (String) li.getSelectedValue();

        if (e.getValueIsAdjusting() == true && item != null) {
            String[] itemSplit = item.split(" | ");

            AllTimeTable allTime = AllTimeTable.getInstance();
            allTime.getTimetable().getTimeModel().resetChosenClass();
            allTime.getTimetable().getTimeModel().change();

            //Display classes in timetable
            for (int i = 0; i < Data.classList.size(); i++) {
                if (Data.classList.get(i).getId().equals(itemSplit[0])) {
                    for (int j = 0; j < Data.classList.get(i).getDays().length; j++) {
                        String from = Data.classList.get(i).getFroms()[j];
                        String tos = Data.classList.get(i).getTos()[j];

                        if (from.length() == 4) {
                            from = "0" + from;
                        }

                        if (tos.length() == 4) {
                            tos = "0" + tos;
                        }

                        allTime.getTimetable().getTimeController().onCreateClass(Data.classList.get(i).getId(),
                                Data.classList.get(i).getDays()[j],
                                from, tos,
                                Data.classList.get(i).getClassName(), Data.classList.get(i).getId(), Data.classList.get(i).getClassCode(),
                                Data.classList.get(i).getRooms()[j], "");
                    }
                }
            }

            //Display Teacher
            allTime.getAllTeacher().clear();
            for (int i = 0; i < Data.teacherList.size(); i++) {
                for (int j = 0; j < Data.teacherList.get(i).getClasses().size(); j++) {
                    if (Data.teacherList.get(i).getClasses().get(j).getId().equals(itemSplit[0])) {
                        String total = Data.teacherList.get(i).getID() + " | " + Data.teacherList.get(i).getFirstName() + Data.teacherList.get(i).getLastName();
                        allTime.getAllTeacher().add(total);
                        break;
                    }
                }
            }
            //Display Student
            allTime.getAllStudent().clear();
            for (int i = 0; i < Data.studentList.size(); i++) {
                for (int j = 0; j < Data.studentList.get(i).getClasses().size(); j++) {
                    if (Data.studentList.get(i).getClasses().get(j).getId().equals(itemSplit[0])) {
                        String total = Data.studentList.get(i).getID() + " | " + Data.studentList.get(i).getFirstName() + Data.studentList.get(i).getLastName();
                        allTime.getAllStudent().add(total);
                        break;
                    }
                }
            }
        }

        setModify();
    }

    public Data getDat() {
        return dat;
    }

    public void setModify() {
        setChanged();
        notifyObservers();
    }

    @Override
    public void windowOpened(WindowEvent we) {
    }

    @Override
    public void windowClosing(WindowEvent we) {
        ControlPanelForm.getInstance().setEnabled(true);
        ControlPanelForm.getInstance().setVisible(true);
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
