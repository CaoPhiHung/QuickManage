package Controller;

import Model.*;
import View.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class AssignmentFormController extends Observable implements ActionListener, ListSelectionListener, WindowListener {

    private String deter;

    public AssignmentFormController() {
    }

    ;
    
    public AssignmentFormController(String deter) {
        this.deter = deter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton cloBut = (JButton) e.getSource();
            if (cloBut.getText().equals("Close") && this.deter.equals("inside") || cloBut.getText().equals("Đóng") && this.deter.equals("inside")) {
                AssignmentForm as = AssignmentForm.getInstanceAss();
                as.dispose();
                ControlPanelForm control = ControlPanelForm.getInstance();
                control.setVisible(true);
                TeacherForm tea = TeacherForm.getInstance();
                tea.setEnabled(true);
                tea.setVisible(true);
            } else if (cloBut.getText().equals("Close") && this.deter.equals("outside") || cloBut.getText().equals("Đóng") && this.deter.equals("outside")) {
                AssignmentForm as = AssignmentForm.getInstanceAss();
                as.dispose();
                ControlPanelForm control = ControlPanelForm.getInstance();
                control.setVisible(true);
                control.setEnabled(true);
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList li = (JList) e.getSource();
        AssignmentForm as = AssignmentForm.getInstanceAss();
        TeacherForm teaForm = TeacherForm.getInstance();

        String item = (String) li.getSelectedValue();

        if (Data.getCurrentTeacherAssign() == null) {
            if (li.getToolTipText().equals("classesList") && e.getValueIsAdjusting() == true && item != null) {
                displayClassInTimetable(as, teaForm, item);
            } else if (li.getToolTipText().equals("assignedList") && e.getValueIsAdjusting() == true && item != null) {
                removeClassInEnrolled(as, teaForm, item);
            }
        } else {
            if (li.getToolTipText().equals("classesList") && e.getValueIsAdjusting() == true && item != null) {
                displayClassInTimetable(as, null, item);
            } else if (li.getToolTipText().equals("assignedList") && e.getValueIsAdjusting() == true && item != null) {
                removeClassInEnrolled(as, null, item);
            }
        }
    }

    private void displayClassInTimetable(AssignmentForm as, TeacherForm teaForm, String item) {

        if (teaForm != null) {
            as.getTimeTable().getTimeModel().resetChosenClass();
            as.getTimeTable().getTimeModel().change();

            //Loop through all Data classes
            for (int i = 0; i < Data.classList.size(); i++) {

                //Check each Data classes to find the right type
                if (Data.classList.get(i).getClassType().getName().equals(item)) {

                    boolean exist = false;
                    //Check for each class in Student Form
                    for (int k = 0; k < teaForm.getClasses().size(); k++) {

                        if (teaForm.getClasses().get(k).getId().equals(Data.classList.get(i).getId())) {
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

                            as.getTimeTable().getTimeController().onCreateClass(Data.classList.get(i).getId(),
                                    Data.classList.get(i).getDays()[j],
                                    from, tos,
                                    Data.classList.get(i).getClassName(), Data.classList.get(i).getId(), Data.classList.get(i).getClassCode(),
                                    Data.classList.get(i).getRooms()[j], "");
                        }
                    }

                }
            }
            as.getTimeTable().setPanelListener();
            as.getEnCon().setModify();
        } else {
            as.getTimeTable().getTimeModel().resetChosenClass();
            as.getTimeTable().getTimeModel().change();

            //Loop through all Data classes
            for (int i = 0; i < Data.classList.size(); i++) {

                //Check each Data classes to find the right type
                if (Data.classList.get(i).getClassType().getName().equals(item)) {

                    boolean exist = false;
                    //Check for each class in Student Form
                    for (int k = 0; k < Data.getCurrentTeacherAssign().getClasses().size(); k++) {

                        if (Data.getCurrentTeacherAssign().getClasses().get(k).getId().equals(Data.classList.get(i).getId())) {
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

                            as.getTimeTable().getTimeController().onCreateClass(Data.classList.get(i).getId(),
                                    Data.classList.get(i).getDays()[j],
                                    from, tos,
                                    Data.classList.get(i).getClassName(), Data.classList.get(i).getId(), Data.classList.get(i).getClassCode(),
                                    Data.classList.get(i).getRooms()[j], "");
                        }
                    }

                }
            }
            as.getTimeTable().setPanelListener();
            as.getEnCon().setModify();
        }


    }

    private void removeClassInEnrolled(AssignmentForm as, TeacherForm teaForm, String item) {

        Data da = new Data();

        if (teaForm != null) {
            String[] splitText = item.split(" | ");

            for (Classes cla : teaForm.getClasses()) {
                if (cla.getId().equals(splitText[0])) {
                    teaForm.getClasses().remove(cla);
                    for (int i = 0; i < as.getEnrolled().size(); i++) {
                        String[] splitFound = as.getEnrolled().get(i).split(" | ");
                        if (splitFound[0].equals(splitText[0])) {
                            as.getEnrolled().remove(i);
                            break;
                        }
                    }
                    break;
                }
            }
            as.getEnCon().setModify();
        } else {
            String[] splitText = item.split(" | ");

            for (Classes cla : Data.getCurrentTeacherAssign().getClasses()) {
                if (cla.getId().equals(splitText[0])) {
                    Data.getCurrentTeacherAssign().getClasses().remove(cla);
                    for (int i = 0; i < as.getEnrolled().size(); i++) {
                        String[] splitFound = as.getEnrolled().get(i).split(" | ");
                        if (splitFound[0].equals(splitText[0])) {
                            as.getEnrolled().remove(i);
                            break;
                        }
                    }
                    break;
                }
            }
            da.saveTeacherData(0);
            as.getEnCon().setModify();
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
