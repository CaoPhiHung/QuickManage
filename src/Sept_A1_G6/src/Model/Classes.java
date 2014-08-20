package Model;

import View.ClassForm;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Classes implements Serializable {

    private String Id;
    private String classCode;
    private ClassType classType;
    private String className;
    private Date startDate;
    private Date endDate;
    private String currentDate;
    private String classLabel = "Class";
    private String tuitionFee;
    private String[] days = new String[7];
    private String[] froms = new String[7];
    private String[] tos = new String[7];
    private String[] rooms = new String[7];
    private ArrayList<Student> students = new ArrayList<>();
    private Teacher teacher;
//    private static Classes unique;

    public Classes() {
    }

    public Classes(String Id, String classCode, ClassType classType,
            String className, String tuitionFee, Date startDate, Date endDate,
            String days0, String days1, String days2, String days3, String days4,
            String days5, String days6, String froms0, String froms1,
            String froms2, String froms3, String froms4, String froms5,
            String froms6, String to0, String to1, String to2, String to3,
            String to4, String to5, String to6, String room0, String room1,
            String room2, String room3, String room4, String room5, String room6,
            String currentDate) {

        this.Id = Id;
        this.classCode = classCode;
        this.classType = classType;
        this.className = className;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currentDate = currentDate;
        this.tuitionFee = tuitionFee;
        // assign endtime into days array "days"
        days[0] = days0;
        days[1] = days1;
        days[2] = days2;
        days[3] = days3;
        days[4] = days4;
        days[5] = days5;
        days[6] = days6;

        // assign endtime into start time array "froms"
        froms[0] = froms0;
        froms[1] = froms1;
        froms[2] = froms2;
        froms[3] = froms3;
        froms[4] = froms4;
        froms[5] = froms5;
        froms[6] = froms6;

        // assign endtime into endtime array "tos"
        tos[0] = to0;
        tos[1] = to1;
        tos[2] = to2;
        tos[3] = to3;
        tos[4] = to4;
        tos[5] = to5;
        tos[6] = to6;

        // assign rooms into room array
        rooms[0] = room0;
        rooms[1] = room1;
        rooms[2] = room2;
        rooms[3] = room3;
        rooms[4] = room4;
        rooms[5] = room5;
        rooms[6] = room6;
//        this.tuitionFee = tuitionFee;
//        this.teacher = teacher;
    }

//    /* this method return a singleton of classes */
//    public static Classes getInstance(){
//        if(unique == null){
//            unique = new Classes();
//        }
//        return unique;
//    }

    /* this method initialize all the variables 
     * parameter is the ClassForm 
     * method will get inputs from the form and set to variable
     */
    public void initializeClass(ClassForm cf) {
        this.Id = cf.getIdR().getText();
        this.className = cf.getNameText().getText();
        this.classCode = cf.getCodeText().getText();
        for(ClassType ct : Data.classTypeList){
            if(ct.getName().equals(cf.getTypeCB().getSelectedItem().toString())){
                this.classType = ct;
            }
        }
        this.startDate = cf.getDateChooser1().getDate();
        this.endDate = cf.getDateChooser2().getDate();
        this.currentDate = Data.getCurrentDate();
        this.tuitionFee = cf.getTuitionFeeR().getText();

        // assign selected day(s) into array
        if (cf.getMon().isSelected()) {
            days[0] = cf.getMon().getText();
        } else {
        }
        if (cf.getMon().isSelected()) {
            days[1] = cf.getTue().getText();
        }
        if (cf.getMon().isSelected()) {
            days[2] = cf.getWed().getText();
        }
        if (cf.getMon().isSelected()) {
            days[3] = cf.getThu().getText();
        }
        if (cf.getMon().isSelected()) {
            days[4] = cf.getFri().getText();
        }
        if (cf.getMon().isSelected()) {
            days[5] = cf.getSat().getText();
        }
        if (cf.getMon().isSelected()) {
            days[6] = cf.getSun().getText();
        }

        // assign start time to array
        froms[0] = cf.getMonStartTime().getSelectedItem().toString();
        froms[1] = cf.getTueStartTime().getSelectedItem().toString();
        froms[2] = cf.getWedStartTime().getSelectedItem().toString();
        froms[3] = cf.getThuStartTime().getSelectedItem().toString();
        froms[4] = cf.getFriStartTime().getSelectedItem().toString();
        froms[5] = cf.getSatStartTime().getSelectedItem().toString();
        froms[6] = cf.getSunStartTime().getSelectedItem().toString();

        // assign end time to array
        tos[0] = cf.getMonEndTime().getSelectedItem().toString();
        tos[1] = cf.getTueEndTime().getSelectedItem().toString();
        tos[2] = cf.getWedEndTime().getSelectedItem().toString();
        tos[3] = cf.getThuEndTime().getSelectedItem().toString();
        tos[4] = cf.getFriEndTime().getSelectedItem().toString();
        tos[5] = cf.getSatEndTime().getSelectedItem().toString();
        tos[6] = cf.getSunEndTime().getSelectedItem().toString();

        // assign room
        rooms[0] = cf.getMonRoom().getSelectedItem().toString();
        rooms[1] = cf.getTueRoom().getSelectedItem().toString();
        rooms[2] = cf.getWedRoom().getSelectedItem().toString();
        rooms[3] = cf.getThuRoom().getSelectedItem().toString();
        rooms[4] = cf.getFriRoom().getSelectedItem().toString();
        rooms[5] = cf.getSatRoom().getSelectedItem().toString();
        rooms[6] = cf.getSunRoom().getSelectedItem().toString();


    }

    /* this method enrolls student(s) into this class 
     * parameter is an object Student
     * the mothod simply adds the student object into students arraylist
     */
    public void enrollStudent(ArrayList<Student> students) {
        for (int i = 0; i < students.size(); i++) {
            this.students.add(students.get(i));
        }
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public String[] getFroms() {
        return froms;
    }

    public String[] getTos() {
        return tos;
    }

    public String[] getRooms() {
        return rooms;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    //
    //    public String getTeacher() {
    //        return teacher;
    //    }
    //
    //    public void setTeacher(String teacher) {
    //        this.teacher = teacher;
    //    }
    //    public String getStartDate() {
    //        return startDate;
    //    }
    //
    //    public void setStartDate(String startDate) {
    //        this.startDate = startDate;
    //    }
    //
    //    public String getEndDate() {
    //        return endDate;
    //    }
    //
    //    public void setEndDate(String endDate) {
    //        this.endDate = endDate;
    //    }
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getId() {
        return Id;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getClassLabel() {
        return classLabel;
    }

    public void setClassLabel(String classLabel) {
        this.classLabel = classLabel;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String[] getDays() {
        return days;
    }

    public void setDays(String[] days) {
        this.days = days;
    }

    public String getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(String tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public void setFroms(String[] froms) {
        this.froms = froms;
    }

    public void setTos(String[] tos) {
        this.tos = tos;
    }

    public void setRooms(String[] rooms) {
        this.rooms = rooms;
    }
}
