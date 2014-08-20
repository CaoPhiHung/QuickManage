package TimetableUtilities;

import Model.*;
import View.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TimetableController implements MouseListener {

    //Properties
    Timetable view;
    private Color currentColor;
    private String enOrAs = "";

    //Constructor
    public TimetableController(Timetable view) {
        this.view = view;
    }
    
    public TimetableController(){};

    //This method will be used to create class
    public void onCreateClass(String Cid, String day, String startTime, String endTime, String className, String id, String ClassCode, String room, String text) {
//        boolean check = onCheckClass(day, startTime, endTime, room);
//        if (!check) {
//            System.out.println("Already exists");
//            return;
//        }
        JPanel temp = new JPanel();
        String newname = day + startTime + " ~ " + endTime;
        double timeStart = getTime(startTime);
        double timeEnd = getTime(endTime);
//        double tempRatio = (timeEnd - timeStart) / 50;
        int ratio = (int)((timeEnd - timeStart) / 50);
        if(ratio == 2){
            ratio = 4;
        }else{
            ratio = 3;
        }
//        if(tempRatio > ratio){
//            ratio++;
//        }

        if (ratio == 0) {
            ratio = 1;
        }
        if (timeStart == timeEnd) {
            ratio = 0;
        }
       
        String totext = "<html>" + ClassCode + "<br/>" + className + "<br/>" + room + "<br/>" + text + "</html>";

        for (int i = 0; i < view.getTimeModel().getTableCell().length; i++) {
            if (view.getTimeModel().getTableCell()[i].getName().substring(3, 8).equals(startTime)
                    && view.getTimeModel().getTableCell()[i].getName().substring(0, 3).equals(day)) {
                temp = view.getTimeModel().getTableCell()[i];
                break;
            }
        }

        CoverPanel cover = new CoverPanel();
        cover.setName(newname);
        cover.setCorX(temp.getX());
        cover.setCorY(temp.getY());
        cover.setRatio(ratio);
        cover.setStringText(totext);
        cover.setClassName(className);
        cover.setClassid(id);
        cover.setClassCode(ClassCode);
        cover.setRoom(room);
        cover.setDay(day);
        cover.setStartTime(startTime);
        cover.setEndTime(endTime);

        view.getTimeModel().getChosenClass().add(cover);
        view.getTimeModel().change();
    }

    //This method will destroy all the class with the same day and time
    public void onDestroyClass(String day, String startTime, String endTime) {

        for (int i = 0; i < view.getTimeModel().getChosenClass().size(); i++) {
            if (view.getTimeModel().getChosenClass().get(i).getStartTime().equals(startTime)
                    && view.getTimeModel().getChosenClass().get(i).getDay().equals(day)
                    && view.getTimeModel().getChosenClass().get(i).getEndTime().equals(endTime)) {
                view.getTimeModel().getChosenClass().remove(i);
            }
        }
        view.getTimeModel().change();
    }

    //This method will destroy all class with the same room
    public void onDestroyClass(String room, String id) {

        if (room != null) {
            for (int i = 0; i < view.getTimeModel().getChosenClass().size(); i++) {
                if (view.getTimeModel().getChosenClass().get(i).getRoom().equals(room)) {
                    view.getTimeModel().getChosenClass().remove(i);
                }
            }
        }


        if (id != null) {
            for (int i = 0; i < view.getTimeModel().getChosenClass().size(); i++) {
                if (view.getTimeModel().getChosenClass().get(i).getClassid().equals(id)) {
                    view.getTimeModel().getChosenClass().remove(i);
                }
            }
        }

    }

//    //Check Class collision
//    public boolean onCheckClass(String day, String startTime, String endTime, String room) {
//        int oldTimeStart;
//        int oldTimeEnd;
//        int newTimeStart = getTime(startTime);
//        int newTimeEnd = getTime(endTime);
//
//        for (int i = 0; i < view.getTimeModel().getChosenClass().size(); i++) {
//            if (view.getTimeModel().getChosenClass().get(i).getDay().equals(day)
//                    && view.getTimeModel().getChosenClass().get(i).getRoom().equals(room)) {
//                oldTimeStart = getTime(view.getTimeModel().getChosenClass().get(i).getStartTime());
//                oldTimeEnd = getTime(view.getTimeModel().getChosenClass().get(i).getEndTime());
//
//                if (//Situation1: same time
//                        (newTimeStart == oldTimeStart && newTimeEnd == oldTimeEnd)
//                        //Situation2: Partly    
//                        || ((newTimeStart > oldTimeStart && newTimeStart < oldTimeEnd)
//                        || (newTimeEnd > oldTimeStart && newTimeEnd < oldTimeEnd))
//                        //Situation3: Extra (the new one cover the old and extend
//                        || ((oldTimeStart > newTimeStart && oldTimeStart < newTimeEnd)
//                        || (oldTimeEnd > newTimeStart && oldTimeEnd < newTimeEnd))
//                        //Situation4: The new one is inside the old one
//                        || ((newTimeStart > oldTimeStart && newTimeStart < oldTimeEnd)
//                        || (newTimeEnd > oldTimeStart && newTimeEnd < oldTimeEnd))) {
//                    return false;
//                }
//            }
//        }
//
//        return true;
//    }

    public double getTime(String time) {
        //11:30
        double toReturn;
        String temp = time.substring(0, 2) + time.substring(3);
        toReturn = Double.parseDouble(temp);
        return toReturn;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof CoverPanel) {
            CoverPanel cov = (CoverPanel) e.getSource();

            if (enOrAs.equals("enroll")) {
                EnrollmentForm enroll = EnrollmentForm.getInstance();
                Classes temp = new Classes();
                StudentForm stufo = StudentForm.getInstance();
                Data da = new Data();

                for (int i = 0; i < Data.classList.size(); i++) {
                    if (Data.classList.get(i).getId().equals(cov.getClassid())) {
                        if (Data.getCurrentStudentEnroll() == null) {
                            stufo.getClasses().add(Data.classList.get(i));
                        } else {
                            Data.getCurrentStudentEnroll().getClasses().add(Data.classList.get(i));
                            da.saveStudentData(0);
                        }
                        temp = Data.classList.get(i);
                        break;
                    }
                }
                String total = temp.getId() + " | " + temp.getClassName() + " | " + temp.getClassType().getName();
                enroll.getEnrolled().add(total);

                enroll.getTimeTable().getTimeModel().resetChosenClass();
                enroll.getTimeTable().getTimeModel().change();
                enroll.getEnCon().setModify();
            } else if (enOrAs.equals("assign")) {
                AssignmentForm assignn = AssignmentForm.getInstanceAss();
                Classes temp = new Classes();
                TeacherForm teafo = TeacherForm.getInstance();
                Data da = new Data();

                for (int i = 0; i < Data.classList.size(); i++) {
                    if (Data.classList.get(i).getId().equals(cov.getClassid())) {
                        if (Data.getCurrentTeacherAssign() == null) {
                            teafo.getClasses().add(Data.classList.get(i));
                        } else {
                            Data.getCurrentTeacherAssign().getClasses().add(Data.classList.get(i));
                            da.saveTeacherData(0);
                        }
                        temp = Data.classList.get(i);
                        break;
                    }
                }
                String total = temp.getId() + " | " + temp.getClassName() + " | " + temp.getClassType().getName();
                assignn.getEnrolled().add(total);

                assignn.getTimeTable().getTimeModel().resetChosenClass();
                assignn.getTimeTable().getTimeModel().change();
                assignn.getAsCon().setModify();
            }

        }

        System.out.println("Class added");


    }

    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
        if (e.getSource() instanceof CoverPanel) {
            CoverPanel cover = (CoverPanel) e.getSource();
            this.currentColor = cover.getBackground();
            cover.setBackground(Color.ORANGE);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
        if (e.getSource() instanceof CoverPanel) {
            CoverPanel cover = (CoverPanel) e.getSource();
            cover.setBackground(currentColor);
        }
    }

    public String getEnOrAs() {
        return enOrAs;
    }

    public void setEnOrAs(String enOrAs) {
        this.enOrAs = enOrAs;
    }
}
