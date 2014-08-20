package Controller;

import Model.*;
import View.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class EnrollmentFormController extends Observable implements ActionListener, ListSelectionListener, WindowListener {

    private String deter;

    public EnrollmentFormController() {
    }

    ;
    public EnrollmentFormController(String deter) {
        this.deter = deter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton cloBut = (JButton) e.getSource();
            if (cloBut.getText().equals("Close") && this.deter.equals("inside") || cloBut.getText().equals("Đóng") && this.deter.equals("inside")) {
                EnrollmentForm en = EnrollmentForm.getInstance();
                en.dispose();
                ControlPanelForm control = ControlPanelForm.getInstance();
                control.setVisible(true);
                StudentForm stu = StudentForm.getInstance();
                stu.setEnabled(true);
                stu.setVisible(true);
            } else if (cloBut.getText().equals("Close") && this.deter.equals("outside") || cloBut.getText().equals("Đóng") && this.deter.equals("outside")) {
                EnrollmentForm en = EnrollmentForm.getInstance();
                en.dispose();
                ControlPanelForm control = ControlPanelForm.getInstance();
                control.setVisible(true);
                control.setEnabled(true);
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        JList li = (JList) e.getSource();
        EnrollmentForm en = EnrollmentForm.getInstance();
        StudentForm stuForm = StudentForm.getInstance();

        String item = (String) li.getSelectedValue();

        if (Data.getCurrentStudentEnroll() == null) {
            if (li.getToolTipText().equals("classesList") && e.getValueIsAdjusting() == true && item != null) {
                displayClassInTimetable(en, stuForm, item);
            } else if (li.getToolTipText().equals("enrolledList") && e.getValueIsAdjusting() == true && item != null) {
                removeClassInEnrolled(en, stuForm, item);
            }
        } else {
            if (li.getToolTipText().equals("classesList") && e.getValueIsAdjusting() == true && item != null) {
                displayClassInTimetable(en, null, item);
            } else if (li.getToolTipText().equals("enrolledList") && e.getValueIsAdjusting() == true && item != null) {
                removeClassInEnrolled(en, null, item);
            }
        }

    }

    private void displayClassInTimetable(EnrollmentForm en, StudentForm stuForm, String item) {

        if (stuForm != null) {
            en.getTimeTable().getTimeModel().resetChosenClass();
            en.getTimeTable().getTimeModel().change();

            //Loop through all Data classes
            for (int i = 0; i < Data.classList.size(); i++) {

                //Check each Data classes to find the right type
                if (Data.classList.get(i).getClassType().getName().equals(item)) {

                    boolean exist = false;
                    //Check for each class in Student Form
                    for (int k = 0; k < stuForm.getClasses().size(); k++) {

                        if (stuForm.getClasses().get(k).getId().equals(Data.classList.get(i).getId())) {
                            exist = true;
                        }
                    }

                    //If doesnt exist then it appears on the timetable
                    if (!exist) {
                        for (int j = 0; j < Data.classList.get(i).getDays().length; j++) {
                            String from = Data.classList.get(i).getFroms()[j];
                            String tos = Data.classList.get(i).getTos()[j];

                            if (from.length() == 4) {
                                from = "0" + from;
                            }

                            if (tos.length() == 4) {
                                tos = "0" + tos;
                            }

                            en.getTimeTable().getTimeController().onCreateClass(Data.classList.get(i).getId(),
                                    Data.classList.get(i).getDays()[j],
                                    from, tos,
                                    Data.classList.get(i).getClassName(), Data.classList.get(i).getId(), Data.classList.get(i).getClassCode(),
                                    Data.classList.get(i).getRooms()[j], "");
                        }
                    }

                }
            }
            en.getTimeTable().setPanelListener();
            en.getEnCon().setModify();
        } else {
            en.getTimeTable().getTimeModel().resetChosenClass();
            en.getTimeTable().getTimeModel().change();

            //Loop through all Data classes
            for (int i = 0; i < Data.classList.size(); i++) {

                //Check each Data classes to find the right type
                if (Data.classList.get(i).getClassType().getName().equals(item)) {

                    boolean exist = false;
                    //Check for each class in Student Form
                    for (int k = 0; k < Data.getCurrentStudentEnroll().getClasses().size(); k++) {

                        if (Data.getCurrentStudentEnroll().getClasses().get(k).getId().equals(Data.classList.get(i).getId())) {
                            exist = true;
                        }
                    }

                    //If doesnt exist then it appears on the timetable
                    if (!exist) {
                        for (int j = 0; j < Data.classList.get(i).getDays().length; j++) {
                            String from = Data.classList.get(i).getFroms()[j];
                            String tos = Data.classList.get(i).getTos()[j];

                            if (from.length() == 4) {
                                from = "0" + from;
                            }

                            if (tos.length() == 4) {
                                tos = "0" + tos;
                            }

                            en.getTimeTable().getTimeController().onCreateClass(Data.classList.get(i).getId(),
                                    Data.classList.get(i).getDays()[j],
                                    from, tos,
                                    Data.classList.get(i).getClassName(), Data.classList.get(i).getId(), Data.classList.get(i).getClassCode(),
                                    Data.classList.get(i).getRooms()[j], "");
                        }
                    }

                }
            }
            en.getTimeTable().setPanelListener();
            en.getEnCon().setModify();
        }


    }

    private void removeClassInEnrolled(EnrollmentForm en, StudentForm stuForm, String item) {

        Data da = new Data();

        if (stuForm != null) {
            String[] splitText = item.split(" | ");

            for (Classes cla : stuForm.getClasses()) {
                if (cla.getId().equals(splitText[0])) {
                    stuForm.getClasses().remove(cla);
                    for (int i = 0; i < en.getEnrolled().size(); i++) {
                        String[] splitFound = en.getEnrolled().get(i).split(" | ");
                        if (splitFound[0].equals(splitText[0])) {
                            en.getEnrolled().remove(i);
                            break;
                        }
                    }
                    break;
                }
            }
            en.getEnCon().setModify();
        } else {
            String[] splitText = item.split(" | ");

            for (Classes cla : Data.getCurrentStudentEnroll().getClasses()) {
                if (cla.getId().equals(splitText[0])) {
                    Data.getCurrentStudentEnroll().getClasses().remove(cla);
                    for (int i = 0; i < en.getEnrolled().size(); i++) {
                        String[] splitFound = en.getEnrolled().get(i).split(" | ");
                        if (splitFound[0].equals(splitText[0])) {
                            en.getEnrolled().remove(i);
                            break;
                        }
                    }
                    break;
                }
            }
            da.saveStudentData(0);
            en.getEnCon().setModify();
        }

    }

    public void setModify() {
        setChanged();
        notifyObservers();
    }

    public String getDeter() {
        return deter;
    }

    public void setDeter(String deter) {
        this.deter = deter;
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