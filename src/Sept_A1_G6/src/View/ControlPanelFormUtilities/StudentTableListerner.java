package View.ControlPanelFormUtilities;

import Model.Data;
import Model.Student;
import View.StudentTimeTable;
import java.awt.event.*;
import java.util.*;

public class StudentTableListerner extends Observable implements ActionListener {

    private String id;

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("THE ID enroll: " + this.id);
        Student tempstu = null;
        boolean active = false;
        StudentTimeTable.setUnique(null);
        StudentTimeTable studentTime = StudentTimeTable.getInstance();

        for (Student stu : Data.studentList) {
            if (stu.getID().equals(id)) {
                tempstu = stu;
                active = true;
                break;
            }
        }

        if (active) {

            studentTime.getTimeTable().getTimeModel().resetChosenClass();
            studentTime.initialize();


            for (int i = 0; i < tempstu.getClasses().size(); i++) {
                for (int j = 0; j < tempstu.getClasses().get(i).getDays().length; j++) {

                    String from = tempstu.getClasses().get(i).getFroms()[j];
                    String tos = tempstu.getClasses().get(i).getTos()[j];

                    if (tos.length() == 4) {
                        tos = "0" + tos;
                    }

                    studentTime.getTimeTable().getTimeController().onCreateClass(tempstu.getClasses().get(i).getId(),
                            tempstu.getClasses().get(i).getDays()[j],
                            from,
                            tos,
                            tempstu.getClasses().get(i).getClassName(),
                            tempstu.getClasses().get(i).getId(),
                            tempstu.getClasses().get(i).getClassCode(),
                            tempstu.getClasses().get(i).getRooms()[j],
                            "");
                }

            }
        }
    }
}