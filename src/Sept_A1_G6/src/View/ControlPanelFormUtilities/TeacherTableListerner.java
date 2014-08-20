package View.ControlPanelFormUtilities;

import Model.Data;
import Model.Teacher;
import View.*;
import java.awt.event.*;

public class TeacherTableListerner implements ActionListener {

    private String id;

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("THE ID assign: " + this.id);
        Teacher temptea = null;
        boolean active = false;
        TeacherTimeTable.setUnique(null);
        TeacherTimeTable teacherTime = TeacherTimeTable.getInstance();

        for (Teacher tea : Data.teacherList) {
            if (tea.getID().equals(id)) {
                temptea = tea;
                active = true;
                break;
            }
        }

        if (active) {

            teacherTime.getTimeTable().getTimeModel().resetChosenClass();
            teacherTime.initialize();


            for (int i = 0; i < temptea.getClasses().size(); i++) {
                for (int j = 0; j < temptea.getClasses().get(i).getDays().length; j++) {

                    String from = temptea.getClasses().get(i).getFroms()[j];
                    String tos = temptea.getClasses().get(i).getTos()[j];

                    if (tos.length() == 4) {
                        tos = "0" + tos;
                    }

                    teacherTime.getTimeTable().getTimeController().onCreateClass(temptea.getClasses().get(i).getId(),
                            temptea.getClasses().get(i).getDays()[j],
                            from,
                            tos,
                            temptea.getClasses().get(i).getClassName(),
                            temptea.getClasses().get(i).getId(),
                            temptea.getClasses().get(i).getClassCode(),
                            temptea.getClasses().get(i).getRooms()[j],
                            "");
                }

            }
        }
    }
}
