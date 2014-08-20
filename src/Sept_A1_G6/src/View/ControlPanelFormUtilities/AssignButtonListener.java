package View.ControlPanelFormUtilities;

import Custom.SuperButton;
import Model.Classes;
import Model.Data;
import View.AssignmentForm;
import View.EnrollmentForm;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class AssignButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            SuperButton but = (SuperButton) e.getSource();

            if (Data.getCurrentStudentEnroll() == null && but.getFrontText().getText().equals("Enroll")) {
                JOptionPane.showMessageDialog(null, "Please choose a student to edit");
            } else if (Data.getCurrentTeacherAssign() == null && but.getFrontText().getText().equals("Assign")) {
                JOptionPane.showMessageDialog(null, "Please choose a teacher to edit");
            } else {
                if (but.getFrontText().getText().equals("Enroll") || but.getFrontText().getText().equals("Ghi Danh")) {
                    EnrollmentForm.setUnique(null);
                    EnrollmentForm enroll = EnrollmentForm.getInstance();
                    enroll.getTimeTable().getTimeController().setEnOrAs("enroll");
                    enroll.getEnCon().setDeter("outside");
                    String name = Data.getCurrentStudentEnroll().getFirstName() + " " + Data.getCurrentStudentEnroll().getLastName();
                    enroll.getNames().setText(name);
                    enroll.getIdR().setText(Data.getCurrentStudentEnroll().getID());
                    enroll.initialize("Set");

                    for (Classes cla : Data.getCurrentStudentEnroll().getClasses()) {
                        String total = cla.getId() + " | " + cla.getClassName() + " | " + cla.getClassType();
                        enroll.getEnrolled().add(total);
                    }

                    enroll.getEnCon().setModify();

                } else if (but.getFrontText().getText().equals("Assign") || but.getFrontText().getText().equals("Bổ Dụng")) {
                    AssignmentForm.setUniqueAss(null);
                    AssignmentForm assignn = AssignmentForm.getInstanceAss();
                    assignn.getTimeTable().getTimeController().setEnOrAs("assign");
                    assignn.getAsCon().setDeter("outside");
                    String name = Data.getCurrentTeacherAssign().getFirstName() + " " + Data.getCurrentTeacherAssign().getLastName();
                    assignn.getNames().setText(name);
                    assignn.getIdR().setText(Data.getCurrentTeacherAssign().getID());
                    assignn.initialize("Set");

                    for (Classes cla : Data.getCurrentTeacherAssign().getClasses()) {
                        String total = cla.getId() + " | " + cla.getClassName() + " | " + cla.getClassType();
                        assignn.getEnrolled().add(total);
                    }

                    assignn.getAsCon().setModify();
                }
            }

        }

    }
}
