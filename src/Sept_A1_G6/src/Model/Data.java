package Model;

import Controller.ControlPanelFormController;
import Controller.LanguageController;
import View.*;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class Data extends Observable {

    /*
     * ---Properties---
     */
    //Static bin data files
    private static String managerFile = "Manager.bin";
    private static String staffFile = "Staff.bin";
    private static String teacherFile = "Teacher.bin";
    private static String studentFile = "Student.bin";
    private static String classFile = "Class.bin";
    private static String invoiceFile = "Invoice.bin";
    private static String roomFile = "Room.bin";
    private static String classTypeFile = "ClassType.bin";
    //List of Users and Classes are stored in ArrayList for easy access
    public static ArrayList<Teacher> teacherList = new ArrayList<>();
    public static ArrayList<Classes> classList = new ArrayList<>();
    public static ArrayList<Manager> managerList = new ArrayList<>();
    public static ArrayList<Staff> staffList = new ArrayList<>();
    public static ArrayList<Student> studentList = new ArrayList<>();
    public static ArrayList<Invoice> invoiceList = new ArrayList<>();
    public static ArrayList<Room> roomList = new ArrayList<>();
    public static ArrayList<ClassType> classTypeList = new ArrayList<>();
    //Useful variables
    private static User currentUser;
    private static User currentEditUser;
    private static Student currentStudentEnroll;
    private static Student currentStudentEnrollTemp;
    private static Teacher currentTeacherAssign;
    private static Teacher currentTeacherAssignTemp;
    private static Classes currentEditClass;
    private static ClassType currentEditClassType;
    private static Room currentEditRoom;
    ResourceBundle resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
    //Room data
    public static final double defaultAvailableHours = 63 * 60;
    public static double totalAvailableMinutes;
    public static double totalOccupiedMinutes;
    public static double ratio;

    //This function will save the ManagerData into Manager.bin file with option
    //saving with new manager or without (for delete part)
    public void saveManagerData(int option) {
        if (option == 1) {
            createNewManager(ManagerForm.getInstance());
        }

        ControlPanelForm.getInstance().getUserType().setSelectedItem(resources.getString("Manager"));
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(managerFile))) {
            {
                os.writeObject(managerList);
                System.out.println("after write" + managerList);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Save to Manager File", "Notice", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Create a new manager and add to arraylist
    private void createNewManager(ManagerForm managerForm) {;
        Manager man = new Manager(managerForm.getIdR().getText(), managerForm.getFirstnameR().getText(),
                managerForm.getLastnameR().getText(), managerForm.getDobDay().getSelectedItem().toString(),
                managerForm.getDobMonth().getSelectedItem().toString(), managerForm.getDobYear().getSelectedItem().toString(),
                managerForm.getGenderR().getSelectedItem().toString(),
                managerForm.getPhoneNumCodeR().getSelectedItem().toString(),
                managerForm.getPhoneNumR().getText(),
                managerForm.getHomeNumCodeR().getSelectedItem().toString(),
                managerForm.getHomeNumR().getText(), managerForm.getEmailR().getText(),
                managerForm.getAddressR().getText(),
                managerForm.getStatusR().getText(), managerForm.getInfoArea().getText(), managerForm.getUserType(), getCurrentDate(),
                managerForm.getUserName().getText(), managerForm.getTypingPassword().getPassword(), managerForm.getRetypePassword().getPassword(), managerForm.getImagePath());
        System.out.println("Phone Code: " + managerForm.getPhoneNumCodeR().getSelectedItem().toString());
        System.out.println("Phone Num: " + managerForm.getPhoneNumR().getText());
        System.out.println("Home Code: " + managerForm.getHomeNumCodeR().getSelectedItem().toString());
        System.out.println("Home Num:" + managerForm.getHomeNumR().getText());
        managerList.add(man);
    }

    //This function will save the StaffrData into Staff.bin file with option
    //saving with new manager or without (for delete part)
    public void saveStaffData(int option) {

        if (option == 1) {
            createNewStaff(StaffForm.getInstance());
        }

        ControlPanelForm.getInstance().getUserType().setSelectedItem(resources.getString("Staff"));
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(staffFile))) {
            {
                os.writeObject(staffList);
                System.out.println("after write" + staffList);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Save to Student File", "Notice", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void saveClassTypeData(int option) {

        if (option == 1) {
            createNewClassType(ClassTypeForm.getInstance());
        }

        ControlPanelForm.getInstance().getUserType().setSelectedItem("Class Type");
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(classTypeFile))) {
            os.writeObject(classTypeList);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Save to Student File", "Notice", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private ArrayList<ClassType> loadClassTypeData() {
        try {
            FileInputStream istream = new FileInputStream(classTypeFile);
            ObjectInputStream is = new ObjectInputStream(istream);
            try {
                while (istream.available() > 0) // check if the file stream is at the end
                {
                    classTypeList = (ArrayList<ClassType>) is.readObject();
                    System.out.println(classTypeList);
                }

                is.close();
            } catch (ClassNotFoundException ex) {
            }
        } catch (IOException ex) {
        }
        return classTypeList;
    }

    //Create a new staff and add to arraylist
    private void createNewClassType(ClassTypeForm classTypeForm) {
        ClassType classType = new ClassType(ClassTypeForm.getInstance().getNameText(), ClassTypeForm.getInstance().getFeeText(),
                ClassTypeForm.getInstance().getTypeText(), ClassTypeForm.getInstance().getRemarksText(),
                ClassTypeForm.getInstance().getLessonPerWeekText());
        classTypeList.add(classType);
    }

    //Create a new staff and add to arraylist
    private void createNewStaff(StaffForm staffForm) {
        Staff sta = new Staff(staffForm.getIdR().getText(), staffForm.getFirstnameR().getText(),
                staffForm.getLastnameR().getText(), staffForm.getDobDay().getSelectedItem().toString(),
                staffForm.getDobMonth().getSelectedItem().toString(), staffForm.getDobYear().getSelectedItem().toString(),
                staffForm.getGenderR().getSelectedItem().toString(), staffForm.getPhoneNumCodeR().getSelectedItem().toString(),
                staffForm.getPhoneNumR().getText(), staffForm.getHomeNumCodeR().getSelectedItem().toString(),
                staffForm.getHomeNumR().getText(), staffForm.getEmailR().getText(), staffForm.getAddressR().getText(),
                staffForm.getStatusR().getText(), staffForm.getInfoArea().getText(), staffForm.getUserType(), getCurrentDate(),
                staffForm.getUserName().getText(), staffForm.getTypingPassword().getPassword(), staffForm.getRetypePassword().getPassword(), staffForm.getImagePath());
        staffList.add(sta);
    }

    //This function will save the TeacherData into Teacher.bin file with option
    //saving with new manager or without (for delete part)
    public void saveTeacherData(int option) {

        if (option == 1) {
            createNewTeacher(TeacherForm.getInstance());
        }
        ControlPanelForm.getInstance().getUserType().setSelectedItem(resources.getString("Teacher"));
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(teacherFile))) {
            {
                os.writeObject(teacherList);
                System.out.println("after write" + teacherList);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Save to Teacher File", "Notice", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Create a new teacher and add to arraylist
    private void createNewTeacher(TeacherForm teacherForm) {
        ArrayList<String> tempSkill = new ArrayList<>();
        ArrayList<String> tempHourlyRate = new ArrayList<>();

        for (int i = 0; i < teacherForm.getNumberOfSkill(); i++) {
            if (teacherForm.getAllSkill()[i].isSelected()) {
                tempSkill.add(teacherForm.getAllSkill()[i].getText());
                tempHourlyRate.add(teacherForm.getHourlyPaid()[i].getText());
            }
        }

        Teacher tea = new Teacher(teacherForm.getIdR().getText(), teacherForm.getFirstnameR().getText(),
                teacherForm.getLastnameR().getText(), teacherForm.getDobDay().getSelectedItem().toString(),
                teacherForm.getDobMonth().getSelectedItem().toString(), teacherForm.getDobYear().getSelectedItem().toString(),
                teacherForm.getGenderR().getSelectedItem().toString(), teacherForm.getPhoneNumCodeR().getSelectedItem().toString(),
                teacherForm.getPhoneNumR().getText(), teacherForm.getHomeNumCodeR().getSelectedItem().toString(),
                teacherForm.getHomeNumR().getText(), teacherForm.getEmailR().getText(), teacherForm.getAddressR().getText(),
                teacherForm.getStatusR().getText(), teacherForm.getInfoArea().getText(), teacherForm.getUserType(), getCurrentDate(), tempSkill, teacherForm.getClasses(), tempHourlyRate, teacherForm.getImagePath());
        teacherList.add(tea);
    }

    //This function will save the StudentData and add to Student.bin file with option
    //saving with new manager or without (for delete part)
    public void saveStudentData(int option) {

        if (option == 1) {
            createNewStudent(StudentForm.getInstance());
        }

        ControlPanelForm.getInstance().getUserType().setSelectedItem(resources.getString("Student"));

        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(studentFile))) {
            {
                os.writeObject(studentList);
                //os.writeObject(contactList);
                System.out.println("after write" + studentList);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Cant save to Student File", "Notice", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Create a new student and add to arraylist
    private void createNewStudent(StudentForm studentForm) {
        Student stu = new Student(studentForm.getIdR().getText(), studentForm.getFirstnameR().getText(),
                studentForm.getLastnameR().getText(), studentForm.getDobDay().getSelectedItem().toString(),
                studentForm.getDobMonth().getSelectedItem().toString(), studentForm.getDobYear().getSelectedItem().toString(),
                studentForm.getGenderR().getSelectedItem().toString(),
                studentForm.getPhoneNumCodeR().getSelectedItem().toString(),
                studentForm.getPhoneNumR().getText(),
                studentForm.getHomeNumCodeR().getSelectedItem().toString(),
                studentForm.getHomeNumR().getText(),
                studentForm.getEmailR().getText(), studentForm.getAddressR().getText(),
                studentForm.getStatusR().getText(), studentForm.getInfoArea().getText(), studentForm.getUserType(), getCurrentDate(),
                studentForm.getClasses(), studentForm.getImagePath(), studentForm.getContactNameR().getText(), studentForm.getContactPhoneCodeR().getSelectedItem().toString(), studentForm.getContactPhoneR().getText(),
                studentForm.getContactEmailR().getText(), studentForm.getContactAddressR().getText(), studentForm.getContactRelationshipR().getSelectedItem().toString());
        studentList.add(stu);
    }

    public void saveInvoiceData(int option, Invoice invoice) {

        if (option == 1) {
            invoiceList.add(invoice);
        }

        // ControlPanelFormController.rightPanelLoadStudent();

        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(invoiceFile))) {
            {
                os.writeObject(invoiceList);
                System.out.println("after write" + invoiceList);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Cant save to Invoice File", "Notice", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //save Room file
    public void saveRoomData(int option, Room room) {

        if (option == 1) {
            roomList.add(room);
        }

        ControlPanelForm.getInstance().getUserType().setSelectedItem("Room");

        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(roomFile))) {
            {
                os.writeObject(roomList);
                System.out.println("after write" + roomList);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Cant save to Room File", "Notice", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //load RoomFile
    public ArrayList<Room> loadRoomData() {
        try {
            FileInputStream istream = new FileInputStream(roomFile);
            ObjectInputStream is = new ObjectInputStream(istream);
            try {
                while (istream.available() > 0) // check if the file stream is at the end
                {
                    roomList = (ArrayList<Room>) is.readObject();
                    System.out.println(roomList);
                }

                is.close();
            } catch (ClassNotFoundException ex) {
            }
        } catch (IOException ex) {
        }
        return roomList;
    }

//This function will save the ClassData and add Class.bin file with option
//saving with new manager or without (for delete part)
    public void saveClassData(int option) {

        if (option == 1) {
            createNewClass(ClassForm.getInstance());
        }
        ControlPanelForm.getInstance().getUserType().setSelectedItem("Class");
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(classFile))) {
            {
                os.writeObject(classList);
                System.out.println("after write" + classList);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Save to Student File", "Notice", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Create a new class and add to arraylist
    private void createNewClass(ClassForm classForm) {
        //        String Id, String classCode, String classType, String className, String startDate, String endDate,
//            String startTime, String endTime, String room
        String[] Days = new String[7];

        if (classForm.getMon().isSelected()) {
            Days[0] = "Mon";
        } else {
            Days[0] = "";
        }

        if (classForm.getTue().isSelected()) {
            Days[1] = "Tue";
        } else {
            Days[1] = "";
        }
        if (classForm.getWed().isSelected()) {
            Days[2] = "Wed";
        } else {
            Days[2] = "";
        }
        if (classForm.getThu().isSelected()) {
            Days[3] = "Thu";
        } else {
            Days[3] = "";
        }
        if (classForm.getFri().isSelected()) {
            Days[4] = "Fri";
        } else {
            Days[4] = "";
        }

        if (classForm.getSat().isSelected()) {
            Days[5] = "Sat";
        } else {
            Days[5] = "";
        }

        if (classForm.getSun().isSelected()) {
            Days[6] = "Sun";
        } else {
            Days[6] = "";
        }
        ClassType ctype = null;
        for (ClassType ct : Data.classTypeList) {
            if (ct.getName().equals(classForm.getTypeCB().getSelectedItem().toString())) {
                ctype = ct;
            }
        }
        Classes cla = new Classes(classForm.getIdText().getText(), classForm.getCodeText().getText(),
                ctype, classForm.getNameText().getText(),
                classForm.getTuitionFeeR().getText(), classForm.getDateChooser1().getDate(), classForm.getDateChooser2().getDate(),
                Days[0],
                Days[1],
                Days[2],
                Days[3],
                Days[4],
                Days[5],
                Days[6],
                classForm.getMonStartTime().getSelectedItem().toString(),
                classForm.getTueStartTime().getSelectedItem().toString(),
                classForm.getWedStartTime().getSelectedItem().toString(),
                classForm.getThuStartTime().getSelectedItem().toString(),
                classForm.getFriStartTime().getSelectedItem().toString(),
                classForm.getSatStartTime().getSelectedItem().toString(),
                classForm.getSunStartTime().getSelectedItem().toString(),
                classForm.getMonEndTime().getSelectedItem().toString(),
                classForm.getTueEndTime().getSelectedItem().toString(),
                classForm.getWedEndTime().getSelectedItem().toString(),
                classForm.getThuEndTime().getSelectedItem().toString(),
                classForm.getFriEndTime().getSelectedItem().toString(),
                classForm.getSatEndTime().getSelectedItem().toString(),
                classForm.getSunEndTime().getSelectedItem().toString(),
                classForm.getMonRoom().getSelectedItem().toString(),
                classForm.getTueRoom().getSelectedItem().toString(),
                classForm.getWedRoom().getSelectedItem().toString(),
                classForm.getThuRoom().getSelectedItem().toString(),
                classForm.getFriRoom().getSelectedItem().toString(),
                classForm.getSatRoom().getSelectedItem().toString(),
                classForm.getSunRoom().getSelectedItem().toString(),
                getCurrentDate());

        classList.add(cla);

    }

    //load Manager
    private ArrayList<Manager> loadManagerData() {
        try {
            FileInputStream istream = new FileInputStream(managerFile);
            ObjectInputStream is = new ObjectInputStream(istream);
            try {
                while (istream.available() > 0) // check if the file stream is at the end
                {
                    managerList = (ArrayList<Manager>) is.readObject();
                    System.out.println(managerList);
                }

                is.close();
            } catch (ClassNotFoundException ex) {
            }
        } catch (IOException ex) {
        }
        return managerList;
    }

    //load Staff
    private ArrayList<Staff> loadStaffData() {
        try {
            FileInputStream istream = new FileInputStream(staffFile);
            ObjectInputStream is = new ObjectInputStream(istream);
            try {
                while (istream.available() > 0) // check if the file stream is at the end
                {
                    staffList = (ArrayList<Staff>) is.readObject();
                    System.out.println(staffList);
                }

                is.close();
            } catch (ClassNotFoundException ex) {
            }
        } catch (IOException ex) {
        }
        return staffList;
    }

    //load Student
    private ArrayList<Student> loadStudentData() {
        try {
            FileInputStream istream = new FileInputStream(studentFile);
            ObjectInputStream is = new ObjectInputStream(istream);
            try {
                while (istream.available() > 0) // check if the file stream is at the end
                {
                    studentList = (ArrayList<Student>) is.readObject();
                    System.out.println(studentList);
                }

                is.close();
            } catch (ClassNotFoundException ex) {
            }
        } catch (IOException ex) {
        }
        return studentList;
    }

    //load Teacher
    private ArrayList<Teacher> loadTeacherData() {
        try {
            FileInputStream istream = new FileInputStream(teacherFile);
            ObjectInputStream is = new ObjectInputStream(istream);
            try {
                while (istream.available() > 0) // check if the file stream is at the end
                {
                    teacherList = (ArrayList<Teacher>) is.readObject();
                    System.out.println(teacherList);
                }

                is.close();
            } catch (ClassNotFoundException ex) {
            }
        } catch (IOException ex) {
        }
        return teacherList;
    }

    //load Class
    private ArrayList<Classes> loadClassData() {
        try {
            FileInputStream istream = new FileInputStream(classFile);
            ObjectInputStream is = new ObjectInputStream(istream);
            try {
                while (istream.available() > 0) // check if the file stream is at the end
                {
                    classList = (ArrayList<Classes>) is.readObject();
                    System.out.println(classList);
                }

                is.close();
            } catch (ClassNotFoundException ex) {
            }
        } catch (IOException ex) {
        }
        return classList;
    }

    //load Invoice
    private ArrayList<Invoice> loadInvoiceData() {
        try {
            FileInputStream istream = new FileInputStream(invoiceFile);
            ObjectInputStream is = new ObjectInputStream(istream);
            try {
                while (istream.available() > 0) // check if the file stream is at the end
                {
                    invoiceList = (ArrayList<Invoice>) is.readObject();
                    System.out.println(invoiceList);
                }
                is.close();
            } catch (ClassNotFoundException ex) {
            }
        } catch (IOException ex) {
        }
        return invoiceList;
    }

    //Load all data
    public static void loadAllData() {
        Data da = new Data();
        Data.managerList = da.loadManagerData();
        Data.staffList = da.loadStaffData();
        Data.teacherList = da.loadTeacherData();
        Data.studentList = da.loadStudentData();
        Data.classList = da.loadClassData();
        Data.invoiceList = da.loadInvoiceData();
        Data.classTypeList = da.loadClassTypeData();
        Data.roomList = da.loadRoomData();
    }

    public void deleteClassType(ArrayList<String> names) {
        int optionSelected = JOptionPane.showOptionDialog(null, "Delete one or many class types will automatically remove the related data? Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE, null, null, null);
        if (optionSelected == 0) {
            ArrayList<String> tempClasses = new ArrayList<>();
            for (int j = 0; j < names.size(); j++) {
                for (int i = 0; i < Data.classTypeList.size(); i++) {
                    if (names.get(j).equals(Data.classTypeList.get(i).getName().toString())) {
                        Data.classTypeList.remove(i);
                        break;
                    }
                }
            }

            for (int i = 0; i < classList.size(); i++) {
                for (int j = 0; j < names.size(); j++) {
                    if (names.get(j).equals(classList.get(i).getClassType().getName())) {
                        tempClasses.add(classList.get(i).getId());
                        classList.remove(i);
                        break;
                    }
                }
                i--;
            }

            for (int i = 0; i < tempClasses.size(); i++) {

                for (Student stu : Data.studentList) {
                    for (int j = 0; j < stu.getClasses().size(); j++) {
                        if (stu.getClasses().get(j).getId().equals(tempClasses.get(i))) {
                            stu.getClasses().remove(j);
                        }
                    }
                }

                for (Teacher tea : Data.teacherList) {
                    for (int j = 0; j < tea.getClasses().size(); j++) {
                        if (tea.getClasses().get(j).getId().equals(tempClasses.get(i))) {
                            tea.getClasses().remove(j);
                        }
                    }
                }
            }

            System.out.println("Delete Class Working");
            saveStudentData(0);
            saveTeacherData(0);
            saveClassData(0);
            saveClassTypeData(0);
        }
    }

    public void deleteRoom(ArrayList<String> numbers) {
        for (int j = 0; j < Data.roomList.size(); j++) {
            for (int i = 0; i < numbers.size(); i++) {
                if (numbers.get(i).equals(Data.roomList.get(j).getNumber())) {
                    Data.roomList.remove(j);
                    break;
                }
            }
            j--;
        }
        saveRoomData(0, null);
    }

    public void delete(ArrayList<String> ids) {

        char cha = ids.get(0).charAt(0);
        for (int i = 0; i < ids.size(); i++) {
            System.out.println("Id: " + ids.get(i));
            switch (cha) {
                case 'M':
                    for (int j = 0; j < Data.managerList.size(); j++) {

                        if (ids.get(i).equals(Data.managerList.get(j).getID())) {
                            Data.managerList.remove(j);
                            break;
                        }
                    }
                    System.out.println("Delete Manager Working");
                    saveManagerData(0);
                    break;
                case 'T':
                    for (int j = 0; j < Data.staffList.size(); j++) {

                        if (ids.get(i).equals(Data.staffList.get(j).getID())) {
                            Data.staffList.remove(j);
                            break;
                        }
                    }
                    System.out.println("Delete Teacher Working");
                    saveStaffData(0);
                    break;
                case 'C':
                    int optionSelected = JOptionPane.showOptionDialog(null, "Delete one or many classes will automatically remove these classes for all students who have enrolled? Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE, null, null, null);

                    if (optionSelected == 0) {
                        Classes tempCla = null;
                        for (int j = 0; j < Data.classList.size(); j++) {

                            if (ids.get(i).equals(Data.classList.get(j).getId())) {
                                tempCla = Data.classList.remove(j);
                                break;
                            }
                        }

                        for (Student stu : Data.studentList) {
                            for (int j = 0; j < stu.getClasses().size(); j++) {
                                if (stu.getClasses().get(j).getId().equals(tempCla.getId())) {
                                    stu.getClasses().remove(j);
                                }
                            }
                        }

                        for (Teacher tea : Data.teacherList) {
                            for (int j = 0; j < tea.getClasses().size(); j++) {
                                if (tea.getClasses().get(j).getId().equals(tempCla.getId())) {
                                    tea.getClasses().remove(j);
                                }
                            }
                        }
                        System.out.println("Delete Class Working");
                        saveStudentData(0);
                        saveTeacherData(0);
                        saveClassData(0);

                    }

                    break;
                default:
                    boolean isStudent = false;
                    for (int j = 0; j < Data.studentList.size(); j++) {
                        if (ids.get(i).equalsIgnoreCase(Data.studentList.get(j).getID())) {
                            isStudent = true;
                            break;
                        }
                    }
                    if (isStudent) {
                        for (int j = 0; j < Data.studentList.size(); j++) {

                            if (ids.get(i).equals(Data.studentList.get(j).getID())) {
                                Data.studentList.remove(j);
                                break;
                            }
                        }
                        saveStudentData(0);
                    } else {
                        for (int j = 0; j < Data.teacherList.size(); j++) {

                            if (ids.get(i).equals(Data.teacherList.get(j).getID())) {
                                Data.teacherList.remove(j);
                                break;
                            }
                        }
                        saveTeacherData(0);
                    }
                    break;
            }
        }

    }

    public void editClassType(String name) {
        for (int i = 0; i < classTypeList.size(); i++) {
            if (classTypeList.get(i).getName().equals(name)) {
                ClassTypeForm.setUnique(null);
                ClassTypeForm classType = ClassTypeForm.getInstance();
                classType.initialize();
                classType.getNameField().setText(classTypeList.get(i).getName());
                classType.getFeeField().setText(classTypeList.get(i).getFeePerLesson());
                classType.getTypeBox().setSelectedItem(classTypeList.get(i).getType());
                classType.getRemarks().setText(classTypeList.get(i).getRemarks());
                classType.getLessonPerWeek().setSelectedItem(classTypeList.get(i).getLessonPerWeek());
                classType.setOption("edit");
                currentEditClassType = classTypeList.get(i);
            }
        }
    }

    public void editRoom(String number) {
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getNumber().equals(number)) {
                RoomForm.setUnique(null);
                RoomForm roomForm = RoomForm.getInstance();
                roomForm.initialize();
                roomForm.getNumber().setText(roomList.get(i).getNumber());
                roomForm.getTypeBox().setSelectedItem(roomList.get(i).getType());
                roomForm.setOption("edit");
                currentEditRoom = roomList.get(i);
            }
        }
    }

    public void Edit(String id) throws ParseException {

        // loop through all ids to get the id

        char cha = id.charAt(0);
        System.out.println("Id: " + id.charAt(0));
        switch (cha) {
            case 'M':
                for (int j = 0; j < Data.managerList.size(); j++) {

                    if (id.equals(Data.managerList.get(j).getID())) {

                        ManagerForm.setUnique(null);
                        ManagerForm manager = ManagerForm.getInstance();
                        manager.getUserName().setFocusable(false);
                        manager.setImagePath(Data.managerList.get(j).getImageLink());
                        manager.managerInitialize();
                        System.out.println("MANAGER CLICKEDDDDDDDDDDDDDDD");
                        manager.setOption("edit");
                        manager.setTitle("Edit Manager");

                        System.out.println(Data.managerList.get(j).getID());
                        manager.idR.setText(Data.managerList.get(j).getID());
                        manager.getFirstnameR().setText(Data.managerList.get(j).getFirstName());
                        manager.getLastnameR().setText(Data.managerList.get(j).getLastName());
                        manager.getDobDay().setSelectedItem(Data.managerList.get(j).getDobDate());
                        manager.getDobMonth().setSelectedItem(Data.managerList.get(j).getDobMonth());
                        manager.getDobYear().setSelectedItem(Data.managerList.get(j).getDobYear());
                        manager.getEmailR().setText(Data.managerList.get(j).getEmail());
                        manager.getAddressR().setText(Data.managerList.get(j).getAddress());
                        manager.getGenderR().setSelectedItem(Data.managerList.get(j).getGender());

                        manager.getHomeNumCodeR().setSelectedItem(Data.managerList.get(j).getHomeNo());

                        manager.getHomeNumR().setText(Data.managerList.get(j).getHomeNoCode().toString());


                        manager.getPhoneNumCodeR().setSelectedItem(Data.managerList.get(j).getPhoneNo());
                        manager.getPhoneNumR().setText(Data.managerList.get(j).getPhoneNoCode().toString());


                        manager.getInfoArea().setText(Data.managerList.get(j).getDescription());
                        manager.getUserName().setText(Data.managerList.get(j).getUsername());
                        manager.getTypingPassword().setText(new String(Data.managerList.get(j).getTypingPassword()));
                        manager.getRetypePassword().setText(new String(Data.managerList.get(j).getRetype()));
                        System.out.println("Edit image: " + Data.managerList.get(j).getImageLink());

                        setModify();
                        currentEditUser = managerList.get(j);
                        break;
                    }
                }
                break;
            case 'T':
                for (int j = 0; j < Data.staffList.size(); j++) {

                    if (id.equals(Data.staffList.get(j).getID())) {
                        StaffForm.setUnique(null);
                        StaffForm staff = StaffForm.getInstance();
                        staff.getUserName().setFocusable(false);
                        staff.setImagePath(Data.staffList.get(j).getImageLink());
                        staff.staffInitialize();
                        staff.setOption("edit");
                        staff.setTitle("Edit Staff");

                        System.out.println(Data.staffList.get(j).getID());
                        staff.idR.setText(Data.staffList.get(j).getID());
                        staff.getFirstnameR().setText(Data.staffList.get(j).getFirstName());
                        staff.getLastnameR().setText(Data.staffList.get(j).getLastName());
                        staff.getDobDay().setSelectedItem(Data.staffList.get(j).getDobDate());
                        staff.getDobMonth().setSelectedItem(Data.staffList.get(j).getDobMonth());
                        staff.getDobYear().setSelectedItem(Data.staffList.get(j).getDobYear());
                        staff.getEmailR().setText(Data.staffList.get(j).getEmail());
                        staff.getAddressR().setText(Data.staffList.get(j).getAddress());
                        staff.getGenderR().setSelectedItem(Data.staffList.get(j).getGender());
                        staff.getHomeNumCodeR().setSelectedItem(Data.staffList.get(j).getHomeNoCode());
                        staff.getHomeNumR().setText(Data.staffList.get(j).getHomeNo());
                        staff.getPhoneNumCodeR().setSelectedItem(Data.staffList.get(j).getPhoneNoCode());
                        staff.getPhoneNumR().setText(Data.staffList.get(j).getPhoneNo());
                        staff.getInfoArea().setText(Data.staffList.get(j).getDescription());
                        staff.getUserName().setText(Data.staffList.get(j).getUsername());
                        staff.getTypingPassword().setText(new String(Data.staffList.get(j).getTypingPassword()));
                        staff.getRetypePassword().setText(new String(Data.staffList.get(j).getRetype()));
                        currentEditUser = staffList.get(j);
                        break;
                    }
                }
                break;
            case 'C':
                for (int j = 0; j < Data.classList.size(); j++) {

                    if (id.equals(Data.classList.get(j).getId())) {
                        ClassForm.setUnique(null);
                        ClassForm classForm = ClassForm.getInstance();
                        classForm.initialize();
                        classForm.setOption("edit");
                        classForm.setTitle("Edit Class");

                        System.out.println(Data.classList.get(j).getId());
                        classForm.getIdText().setText(Data.classList.get(j).getId());
                        classForm.getNameText().setText(Data.classList.get(j).getClassName());
                        classForm.getCodeText().setText(Data.classList.get(j).getClassCode());
                        classForm.getTypeCB().setSelectedItem(Data.classList.get(j).getClassType().getName());
                        classForm.getDateChooser1().setDate(Data.classList.get(j).getStartDate());
                        classForm.getDateChooser2().setDate(Data.classList.get(j).getEndDate());
                        classForm.getTuitionFeeR().setText(Data.classList.get(j).getTuitionFee());

//                        System.out.println("Current lession " + ClassForm.numberOfLesson);
                        ClassForm.numberChosenLesson = ClassForm.numberOfLesson;
                        //from, to , at
                        for (int i = 0; i < Data.classList.get(j).getDays().length; i++) {
                            System.out.println("The text of days: " + Data.classList.get(j).getDays()[i]);
                            if (Data.classList.get(j).getDays()[i].equalsIgnoreCase("Mon")) {
                                classForm.getMon().setSelected(true);
                                classForm.getMonStartTime().setSelectedItem(Data.classList.get(j).getFroms()[i]);
                                classForm.getMonEndTime().setSelectedItem(Data.classList.get(j).getTos()[i]);
                                classForm.getMonRoom().setSelectedItem(Data.classList.get(j).getRooms()[i]);
                                System.out.println("Working");
                            }
                            if (Data.classList.get(j).getDays()[i].equalsIgnoreCase("Tue")) {
                                classForm.getTue().setSelected(true);
                                classForm.getTueStartTime().setSelectedItem(Data.classList.get(j).getFroms()[i]);
                                classForm.getTueEndTime().setSelectedItem(Data.classList.get(j).getTos()[i]);
                                classForm.getTueRoom().setSelectedItem(Data.classList.get(j).getRooms()[i]);
                                System.out.println("Working");
                            }
                            if (Data.classList.get(j).getDays()[i].equalsIgnoreCase("Wed")) {
                                classForm.getWed().setSelected(true);
                                classForm.getWedStartTime().setSelectedItem(Data.classList.get(j).getFroms()[i]);
                                classForm.getWedEndTime().setSelectedItem(Data.classList.get(j).getTos()[i]);
                                classForm.getWedRoom().setSelectedItem(Data.classList.get(j).getRooms()[i]);
                                System.out.println("Working");
                            }
                            if (Data.classList.get(j).getDays()[i].equalsIgnoreCase("Thu")) {
                                classForm.getThu().setSelected(true);
                                classForm.getThuStartTime().setSelectedItem(Data.classList.get(j).getFroms()[i]);
                                classForm.getThuEndTime().setSelectedItem(Data.classList.get(j).getTos()[i]);
                                classForm.getThuRoom().setSelectedItem(Data.classList.get(j).getRooms()[i]);
                                System.out.println("Working");
                            }
                            if (Data.classList.get(j).getDays()[i].equalsIgnoreCase("Fri")) {
                                classForm.getFri().setSelected(true);
                                classForm.getFriStartTime().setSelectedItem(Data.classList.get(j).getFroms()[i]);
                                classForm.getFriEndTime().setSelectedItem(Data.classList.get(j).getTos()[i]);
                                classForm.getFriRoom().setSelectedItem(Data.classList.get(j).getRooms()[i]);
                                System.out.println("Working");
                            }
                            if (Data.classList.get(j).getDays()[i].equalsIgnoreCase("Sat")) {
                                classForm.getSat().setSelected(true);
                                classForm.getSatStartTime().setSelectedItem(Data.classList.get(j).getFroms()[i]);
                                classForm.getSatEndTime().setSelectedItem(Data.classList.get(j).getTos()[i]);
                                classForm.getSatRoom().setSelectedItem(Data.classList.get(j).getRooms()[i]);
                                System.out.println("Working");
                            }
                            if (Data.classList.get(j).getDays()[i].equalsIgnoreCase("Sun")) {
                                classForm.getSun().setSelected(true);
                                classForm.getSunStartTime().setSelectedItem(Data.classList.get(j).getFroms()[i]);
                                classForm.getSunEndTime().setSelectedItem(Data.classList.get(j).getTos()[i]);
                                classForm.getSunRoom().setSelectedItem(Data.classList.get(j).getRooms()[i]);
                                System.out.println("Working");
                            }
                        }

                        // get all currently enrolled student and put on student list
                        String[] studentNames = new String[classList.get(j).getStudents().size()];
                        System.out.println("Size of the array: " + classList.get(j).getStudents().size());
                        for (int i = 0; i < studentNames.length; i++) {
                            studentNames[i] = classList.get(j).getStudents().get(i).getLastName() + " " + classList.get(j).getStudents().get(i).getFirstName();
                            System.out.println("Student enroll in classess : " + studentNames[i]);
                        }
                        classForm.getStudentList().setListData(studentNames);
                        if (classList.get(j).getTeacher() == null) {
                            classForm.getTeacherTextField().setText("No teacher yet");
                        } else {
                            classForm.getTeacherTextField().setText(classList.get(j).getTeacher().getLastName() + " " + classList.get(j).getTeacher().getFirstName());
                        }

                        currentEditClass = classList.get(j);
                        break;
                    }
                }
                break;
            default:
                boolean isStudent = false;
                for (int i = 0; i < Data.studentList.size(); i++) {
                    if (id.equals(Data.studentList.get(i).getID())) {
                        isStudent = true;
                        break;
                    }
                }
                if (isStudent) {
                    //student
                    for (int j = 0; j < Data.studentList.size(); j++) {

                        if (id.equals(Data.studentList.get(j).getID())) {
                            StudentForm.setUnique(null);
                            StudentForm student = StudentForm.getInstance();
                            student.setImagePath(Data.studentList.get(j).getImageLink());
                            student.studentInitialize();
                            student.getEnrollButton().setVisible(false);
                            student.getCancel().setBounds(290, 450, 100, 25);
                            student.setOption("edit");
                            student.setTitle("Edit Student");

                            System.out.println(Data.studentList.get(j).getID());
                            student.idR.setText(Data.studentList.get(j).getID());
                            student.getFirstnameR().setText(Data.studentList.get(j).getFirstName());
                            student.getLastnameR().setText(Data.studentList.get(j).getLastName());
                            student.getDobDay().setSelectedItem(Data.studentList.get(j).getDobDate());
                            student.getDobMonth().setSelectedItem(Data.studentList.get(j).getDobMonth());
                            student.getDobYear().setSelectedItem(Data.studentList.get(j).getDobYear());
                            student.getEmailR().setText(Data.studentList.get(j).getEmail());
                            student.getAddressR().setText(Data.studentList.get(j).getAddress());
                            student.getGenderR().setSelectedItem(Data.studentList.get(j).getGender());

                            student.getHomeNumCodeR().setSelectedItem(Data.studentList.get(j).getHomeNoCode());
                            student.getHomeNumR().setText(Data.studentList.get(j).getHomeNo());
                            student.getPhoneNumCodeR().setSelectedItem(Data.studentList.get(j).getPhoneNoCode());
                            student.getPhoneNumR().setText(Data.studentList.get(j).getPhoneNo());

                            student.getInfoArea().setText(Data.studentList.get(j).getDescription());
                            student.setClasses(Data.studentList.get(j).getClasses());

                            //contact person
                            student.getContactNameR().setText(Data.studentList.get(j).getContactName());
                            student.getContactPhoneCodeR().setSelectedItem(Data.studentList.get(j).getContactPhoneCode());
                            student.getContactPhoneR().setText(Data.studentList.get(j).getContactPhone());
                            student.getContactEmailR().setText(Data.studentList.get(j).getContactEmail());
                            student.getContactAddressR().setText(Data.studentList.get(j).getContactAddress());
                            student.getContactRelationshipR().setSelectedItem(Data.studentList.get(j).getContactRelationShip());
                            currentEditUser = studentList.get(j);
                            break;
                        }
                    }
                    Data.setCurrentStudentEnrollTemp(Data.getCurrentStudentEnroll());
                    Data.setCurrentStudentEnroll(null);
                } else {
                    //teacher  
                    for (int j = 0; j < Data.teacherList.size(); j++) {

                        if (id.equals(Data.teacherList.get(j).getID())) {
                            TeacherForm.setUnique(null);
                            TeacherForm teacher = TeacherForm.getInstance();
                            teacher.setImagePath(Data.teacherList.get(j).getImageLink());
                            teacher.teacherInitialize();
                            teacher.getAssignButton().setVisible(false);
                            teacher.getCancel().setBounds(180, 620, 80, 25);
                            teacher.setOption("edit");
                            teacher.setTitle("Edit Teacher");

                            System.out.println(Data.teacherList.get(j).getID());
                            teacher.idR.setText(Data.teacherList.get(j).getID());
                            teacher.getFirstnameR().setText(Data.teacherList.get(j).getFirstName());
                            teacher.getLastnameR().setText(Data.teacherList.get(j).getLastName());
                            teacher.getDobDay().setSelectedItem(Data.teacherList.get(j).getDobDate());
                            teacher.getDobMonth().setSelectedItem(Data.teacherList.get(j).getDobMonth());
                            teacher.getDobYear().setSelectedItem(Data.teacherList.get(j).getDobYear());
                            teacher.getEmailR().setText(Data.teacherList.get(j).getEmail());
                            teacher.getAddressR().setText(Data.teacherList.get(j).getAddress());
                            teacher.getGenderR().setSelectedItem(Data.teacherList.get(j).getGender());
                            teacher.getHomeNumCodeR().setSelectedItem(Data.teacherList.get(j).getHomeNoCode());
                            teacher.getHomeNumR().setText(Data.teacherList.get(j).getHomeNo());
                            teacher.getPhoneNumCodeR().setSelectedItem(Data.teacherList.get(j).getPhoneNoCode());
                            teacher.getPhoneNumR().setText(Data.teacherList.get(j).getPhoneNo());
                            teacher.getInfoArea().setText(Data.teacherList.get(j).getDescription());
                            System.out.println(Data.teacherList.get(j).getSkillList());

                            for (int i = 0; i < Data.teacherList.get(j).getSkillList().size(); i++) {
                                for (int k = 0; k < teacher.getAllSkill().length; k++) {
                                    if (Data.teacherList.get(j).getSkillList().get(i).equalsIgnoreCase(teacher.getAllSkill()[k].getText())) {
                                        teacher.getAllSkill()[k].setSelected(true);
                                        teacher.getHourlyPaid()[k].setText(Data.teacherList.get(j).getHourlyRate().get(k));
                                    }
                                }
                            }
                            currentEditUser = teacherList.get(j);
                            break;
                        }
                    }
                    Data.setCurrentTeacherAssignTemp(Data.getCurrentTeacherAssign());
                    Data.setCurrentTeacherAssign(null);
                }
                break;
        }
    }

    public void activateButton(ArrayList<String> ids) {

        char cha = ids.get(0).charAt(0);
        for (int i = 0; i < ids.size(); i++) {
            System.out.println("Id: " + ids.get(i));
            switch (cha) {
                case 'M':
                    for (int j = 0; j < Data.managerList.size(); j++) {

                        if (ids.get(i).equals(Data.managerList.get(j).getID())) {

                            System.out.println("Status before:");
                            System.out.println(Data.managerList.get(j).getStatus());
                            if (Data.managerList.get(j).getStatus().equalsIgnoreCase("Activate")) {
                                Data.managerList.get(j).setStatus("Deactivate");
                                System.out.println("Status after:");
                                System.out.println(Data.managerList.get(j).getStatus());
                            } else {
                                Data.managerList.get(j).setStatus("Activate");
                                System.out.println("Status after:");
                                System.out.println(Data.managerList.get(j).getStatus());
                            }
                            System.out.println("Inside change Activate/Deactivate");
                            break;
                        }
                    }
                    System.out.println("Working");
                    saveManagerData(0);
                    break;
                case 'T':
                    for (int j = 0; j < Data.staffList.size(); j++) {

                        if (ids.get(i).equals(Data.staffList.get(j).getID())) {
                            if (Data.staffList.get(j).getStatus().equalsIgnoreCase("Activate")) {
                                Data.staffList.get(j).setStatus("Deactivate");
                            } else {
                                Data.staffList.get(j).setStatus("Activate");
                            }
                            System.out.println("Inside change Activate/Deactivate");
                            break;
                        }
                    }
                    saveStaffData(0);
                    break;
                default:
                    boolean isStudent = false;
                    for (int j = 0; j < Data.studentList.size(); j++) {
                        if (ids.get(i).equals(Data.studentList.get(j).getID())) {
                            isStudent = true;
                            break;
                        }
                    }
                    if (isStudent) {
                        for (int j = 0; j < Data.studentList.size(); j++) {

                            if (ids.get(i).equals(Data.studentList.get(j).getID())) {
                                if (Data.studentList.get(j).getStatus().equalsIgnoreCase("Activate")) {
                                    Data.studentList.get(j).setStatus("Deactivate");
                                } else {
                                    Data.studentList.get(j).setStatus("Activate");
                                }
                                System.out.println("Inside change Activate/Deactivate");
                                break;
                            }
                        }
                        saveStudentData(0);
                    } else {
                        for (int j = 0; j < Data.teacherList.size(); j++) {

                            if (ids.get(i).equals(Data.teacherList.get(j).getID())) {
                                if (Data.teacherList.get(j).getStatus().equalsIgnoreCase("Activate")) {
                                    Data.teacherList.get(j).setStatus("Deactivate");
                                } else {
                                    Data.teacherList.get(j).setStatus("Activate");
                                }
                                System.out.println("Inside change Activate/Deactivate");
                                break;
                            }
                        }
                        saveTeacherData(0);
                    }
                    break;
            }
        }
    }

    public void copyClasses(String id) {
        for (int j = 0; j < Data.classList.size(); j++) {

            if (Data.classList.get(j).getId().equalsIgnoreCase(id)) {

                System.out.println("Old Id: " + Data.classList.get(j).getId());
                String newId = Data.classList.get(Data.classList.size() - 1).getId();
                int num = Integer.parseInt(newId.substring(1));
                num++;
                String newClassesId = "C" + num;
                System.out.println("New Copy ID: " + newClassesId);

                Classes temp = new Classes(newClassesId, Data.classList.get(j).getClassCode(), Data.classList.get(j).getClassType(), Data.classList.get(j).getClassName(), Data.classList.get(j).getTuitionFee(), Data.classList.get(j).getStartDate(),
                        Data.classList.get(j).getEndDate(), Data.classList.get(j).getDays()[0], Data.classList.get(j).getDays()[1], Data.classList.get(j).getDays()[2], Data.classList.get(j).getDays()[3], Data.classList.get(j).getDays()[4], Data.classList.get(j).getDays()[5], Data.classList.get(j).getDays()[6],
                        Data.classList.get(j).getFroms()[0], Data.classList.get(j).getFroms()[1], Data.classList.get(j).getFroms()[2], Data.classList.get(j).getFroms()[3], Data.classList.get(j).getFroms()[4], Data.classList.get(j).getFroms()[5], Data.classList.get(j).getFroms()[6], Data.classList.get(j).getTos()[0], Data.classList.get(j).getTos()[1], Data.classList.get(j).getTos()[2], Data.classList.get(j).getTos()[3], Data.classList.get(j).getTos()[4], Data.classList.get(j).getTos()[5], Data.classList.get(j).getTos()[6], Data.classList.get(j).getRooms()[0], Data.classList.get(j).getRooms()[1], Data.classList.get(j).getRooms()[2], Data.classList.get(j).getRooms()[3], Data.classList.get(j).getRooms()[4], Data.classList.get(j).getRooms()[5], Data.classList.get(j).getRooms()[6], Data.classList.get(j).getCurrentDate());


                Data.classList.add(temp);
                saveClassData(0);
                break;

            }

        }
    }

    public void autoGenerateID(UserForm uf) {
        DateFormat dateFormat = new SimpleDateFormat("yyMM");
        Date date = new Date();
        int id1 = 0;
        int id2 = 0;
        int count = 0;
        for (int i = 0; i < Data.teacherList.size(); i++) {
            //count++;
            String temp = Data.teacherList.get(Data.teacherList.size() - 1).getID();
            id1 = Integer.parseInt(temp.substring(4, 7));
        }
        for (int i = 0; i < Data.studentList.size(); i++) {
            //count++;
            String temp = Data.studentList.get(Data.studentList.size() - 1).getID();
            id2 = Integer.parseInt(temp.substring(4, 7));
        }
        if (id1 > id2) {
            count = id1;
        } else {
            count = id2;
        }

        count++;
        String digits = String.format("%03d", count);
        if (uf instanceof TeacherForm) {
            TeacherForm.getInstance().getIdR().setText(dateFormat.format(date).toString() + digits);
        }
        if (uf instanceof StudentForm) {
            StudentForm.getInstance().getIdR().setText(dateFormat.format(date).toString() + digits);
        }
    }

    public static String autoInvoiceGenerateId() {
        String invoiceID = "";
        DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        Date date = new Date();
        int newTop = -1;

        invoiceID = getCurrentStudentEnroll().getID() + "-" + dateFormat.format(date).toString();
        if (Data.getCurrentStudentEnroll().getInvoices().isEmpty()) {
            invoiceID = invoiceID + "-1";
        } else {
            for (Invoice inv : Data.getCurrentStudentEnroll().getInvoices()) {
                if (inv.getIdNoNumber().equals(invoiceID) && inv.isIsTop()) {
                    inv.setIsTop(false);
                    newTop = Integer.parseInt(inv.getIdNumber()) + 1;
                    break;
                }
            }
            if (newTop == -1) {
                invoiceID = invoiceID + "-1";
            } else {
                invoiceID = invoiceID + "-" + newTop;
            }
        }


        System.out.println("Invoice: " + invoiceID);

        return invoiceID;
    }

    public static String getCurrentDate() {
        String current = "";
        Calendar currentDate = Calendar.getInstance(); //Get the current date
        SimpleDateFormat formatter = new SimpleDateFormat("dd,MMM yyyy"); //format it as per your requirement
        current = formatter.format(currentDate.getTime());
        return current;
    }

    public static String getCurrentDate(String format) {
        String current = "";
        Calendar currentDate = Calendar.getInstance(); //Get the current date
        SimpleDateFormat formatter = new SimpleDateFormat(format); //format it as per your requirement
        current = formatter.format(currentDate.getTime());
        return current;
    }

    public String convertFee(String fee) {
        String[] feeSplit = fee.split(",");
        String feeRe = "";

        for (String sti : feeSplit) {
            feeRe = feeRe + sti;
        }

        return feeRe;
    }

    public String commaFee(String fee) {
        String feeRe = "";


        int count = 1;

        for (int i = fee.length() - 1; i >= 0; i--) {
            if (count == 3) {
                if (i == 0) {
                    feeRe = feeRe + fee.charAt(i);
                } else {
                    feeRe = feeRe + fee.charAt(i) + ",";
                }
                count = 0;
            } else {
                feeRe = feeRe + fee.charAt(i);
            }
            count++;
        }

        return revertStrings(feeRe);
    }

    public String revertStrings(String ori) {
        String aft = "";

        for (int i = ori.length() - 1; i >= 0; i--) {
            aft = aft.concat(ori.charAt(i) + "");
        }

        return aft;
    }

    public static int getWeekDay(Date datebefore, Date dateafter, String whatmonth) {

//        Date d1 = Calendar.getInstance().getTime();
//        Calendar temp = Calendar.getInstance();
//        temp.add(Calendar.MONTH, -1);
//        Date d2 = temp.getTime();
//
//        System.out.println("Current " + d1);
//        System.out.println("Last Month " + d2);

        Calendar c1 = Calendar.getInstance();
        c1.setTime(dateafter);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(datebefore);

        int currentWeekDay = 0;
        int calendarCurrent = 0;
        if(whatmonth.equals("mon") || whatmonth.equals("Mon")){
            calendarCurrent = Calendar.MONDAY;
        }else if (whatmonth.equals("tue") || whatmonth.equals("Tue")){
            calendarCurrent = Calendar.THURSDAY;
        }else if (whatmonth.equals("wed") || whatmonth.equals("Wed")){
            calendarCurrent = Calendar.WEDNESDAY;
        }else if (whatmonth.equals("thu") || whatmonth.equals("Thu")){
            calendarCurrent = Calendar.THURSDAY;
        }else if (whatmonth.equals("fri") || whatmonth.equals("Fri")){
            calendarCurrent = Calendar.FRIDAY;
        }else if (whatmonth.equals("sat") || whatmonth.equals("Sat")){
            calendarCurrent = Calendar.SATURDAY;
        }else if (whatmonth.equals("sun") || whatmonth.equals("Sun")){
            calendarCurrent = Calendar.SUNDAY;
        }else{
            return 0;
        }

        while (c1.after(c2)) {
            if (c2.get(Calendar.DAY_OF_WEEK) == calendarCurrent) {
                currentWeekDay++;
            }
            c2.add(Calendar.DATE, 1);
        }

        return currentWeekDay;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Data.currentUser = currentUser;
    }

    public static Student getCurrentStudentEnroll() {
        return currentStudentEnroll;
    }

    public static void setCurrentStudentEnroll(Student currentStudentEnroll) {
        Data.currentStudentEnroll = currentStudentEnroll;
    }

    public static User getCurrentEditUser() {
        return currentEditUser;
    }

    public static void setCurrentEditUser(User currentEditUser) {
        Data.currentEditUser = currentEditUser;
    }

    public static Classes getCurrentEditClass() {
        return currentEditClass;
    }

    public static void setCurrentEditClass(Classes currentEditClass) {
        Data.currentEditClass = currentEditClass;
    }

    public static Student getCurrentStudentEnrollTemp() {
        return currentStudentEnrollTemp;
    }

    public static void setCurrentStudentEnrollTemp(Student currentStudentEnrollTemp) {
        Data.currentStudentEnrollTemp = currentStudentEnrollTemp;
    }

    public static Teacher getCurrentTeacherAssign() {
        return currentTeacherAssign;
    }

    public static void setCurrentTeacherAssign(Teacher currentTeacherAssign) {
        Data.currentTeacherAssign = currentTeacherAssign;
    }

    public static Teacher getCurrentTeacherAssignTemp() {
        return currentTeacherAssignTemp;
    }

    public static void setCurrentTeacherAssignTemp(Teacher currentTeacherAssignTemp) {
        Data.currentTeacherAssignTemp = currentTeacherAssignTemp;
    }

    public static ClassType getCurrentEditClassType() {
        return currentEditClassType;
    }

    public static Room getCurrentEditRoom() {
        return currentEditRoom;
    }

    public void setModify() {
        setChanged();
        notifyObservers();
    }
}
