package View;

import Controller.*;
import Model.*;
import com.toedter.calendar.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class ClassForm extends UserForm implements Observer {

    // Delare properties
    private JPanel labelPane = new JPanel();
    private JLayeredPane valuePane1 = new JLayeredPane();
    private JPanel picPane = new JPanel();
    private JPanel valuePane2 = new JPanel();
    private JPanel buttonPane = new JPanel();
    private JPanel datePickerPane = new JPanel();
    private JCalendar datePicker = new JCalendar();
    private JLabel id = new JLabel("Class ID");
    private JLabel name = new JLabel("Class name");
    private JLabel teacherLabel = new JLabel("Teacher");
    private JLabel code = new JLabel("Class code");
    private JLabel start = new JLabel("Start date");
    private JLabel end = new JLabel("End date");
    private JLabel type = new JLabel("Class Type");
    private JLabel numberOfLessonAvailable = new JLabel("Number of lessons:");
    public static int numberOfLesson;
    public static int numberChosenLesson = 0;
    private JLabel details = new JLabel("Date/Time");
    private JLabel tuitionFee = new JLabel("Tuition Fee");
    private JLabel from;
    private JLabel to;
    private JLabel at;
    private JLabel studentEnrolled = new JLabel("Student Enrolled");
    private JLabel idText = new JLabel();
    private JTextField nameText = new JTextField();
    private JTextField codeText = new JTextField();
    private JTextField teacherTextField = new JTextField();
    private JComboBox typeCB = new JComboBox();
    private JComboBox fromCB = new JComboBox();
    private JComboBox toCB = new JComboBox();
    private JComboBox atCB = new JComboBox();
    private JComboBox monStartTime = new JComboBox();
    private JComboBox tueStartTime = new JComboBox();
    private JComboBox wedStartTime = new JComboBox();
    private JComboBox thuStartTime = new JComboBox();
    private JComboBox friStartTime = new JComboBox();
    private JComboBox satStartTime = new JComboBox();
    private JComboBox sunStartTime = new JComboBox();
    private JComboBox monEndTime = new JComboBox();
    private JComboBox tueEndTime = new JComboBox();
    private JComboBox wedEndTime = new JComboBox();
    private JComboBox thuEndTime = new JComboBox();
    private JComboBox friEndTime = new JComboBox();
    private JComboBox satEndTime = new JComboBox();
    private JComboBox sunEndTime = new JComboBox();
    private JComboBox monRoom = new JComboBox();
    private JComboBox tueRoom = new JComboBox();
    private JComboBox wedRoom = new JComboBox();
    private JComboBox thuRoom = new JComboBox();
    private JComboBox friRoom = new JComboBox();
    private JComboBox satRoom = new JComboBox();
    private JComboBox sunRoom = new JComboBox();
    private JCheckBox mon = new JCheckBox("Mon");
    private JCheckBox tue = new JCheckBox("Tue");
    private JCheckBox wed = new JCheckBox("Wed");
    private JCheckBox thu = new JCheckBox("Thu");
    private JCheckBox fri = new JCheckBox("Fri");
    private JCheckBox sat = new JCheckBox("Sat");
    private JCheckBox sun = new JCheckBox("Sun");
    private JFormattedTextField tuitionFeeR = new JFormattedTextField(NumberFormat.getNumberInstance());
    private JButton addStudent = new JButton("Add Student");
    private JButton assignTeacher = new JButton("Assign Teacher");
    private JButton add = new JButton("Add");
    private JButton cancel = new JButton("Cancel");
    private JScrollPane scroll = new JScrollPane();
    private ArrayList<String> students;
    private JList studentList = new JList();
    private String userType = "Class";
    private static ClassForm unique;
    private ClassFormController classCon = new ClassFormController();
    private DisplayCurrentLesson displayCurrentLesson = new DisplayCurrentLesson();
    JDateChooser dateChooser1 = new JDateChooser();
    JDateChooser dateChooser2 = new JDateChooser();
    private String startDate;
    private String endDate;
    private int numberOfdays = 7;
    private JCheckBox[] days = new JCheckBox[numberOfdays];
    private int numberOfFields = 19;
    private ResourceBundle resources;
    private String[] weektimes = {
        "09:00", "09:15", "09:30", "09:45",
        "10:00", "10:15", "10:30", "10:45",
        "11:00", "11:15", "11:30", "11:45",
        "12:00", "12:15", "12:30", "12:45",
        "13:00", "13:15", "13:30", "13:45",
        "14:00", "14:15", "14:30", "14:45",
        "15:00", "15:15", "15:30", "15:45",
        "16:00", "16:15", "16:30", "16:45",
        "17:00", "17:15", "17:30", "17:45",
        "18:00", "18:15", "18:30", "18:45",
        "19:00", "19:15", "19:30", "19:45",
        "20:00"};

    public int getNumberOfFields() {
        return numberOfFields;
    }

    public static ClassForm getInstance() {
        if (unique == null) {
            unique = new ClassForm();
        }
        return unique;
    }

    public void initialize() {

        numberChosenLesson = 0;
        
        //Label settings
        id.setBounds(10, 12, 50, 20);
        name.setBounds(10, 52, 100, 20);
        teacherLabel.setBounds(195, 12, 100, 20);
        code.setBounds(10, 92, 100, 20);
        start.setBounds(10, 132, 100, 20);
        end.setBounds(10, 172, 100, 20);
        type.setBounds(10, 252, 100, 20);
        numberOfLessonAvailable.setBounds(195, 252, 150, 20);
        details.setBounds(10, 290, 100, 20);
        tuitionFee.setBounds(10, 212, 100, 20);
        studentEnrolled.setBounds(320, 10, 130, 20);
        // create from labels, set positions and sizes
        from = new JLabel("from");
        from.setBounds(90, 10, 25, 20);
        valuePane2.add(from);
        from = new JLabel("from");
        from.setBounds(90, 50, 25, 20);
        valuePane2.add(from);
        from = new JLabel("from");
        from.setBounds(90, 90, 25, 20);
        valuePane2.add(from);
        from = new JLabel("from");
        from.setBounds(90, 130, 25, 20);
        valuePane2.add(from);
        from = new JLabel("from");
        from.setBounds(90, 170, 25, 20);
        valuePane2.add(from);
        from = new JLabel("from");
        from.setBounds(90, 210, 25, 20);
        valuePane2.add(from);
        from = new JLabel("from");
        from.setBounds(90, 250, 25, 20);
        valuePane2.add(from);
        // create to labels, set positions and sizes
        to = new JLabel("to");
        to.setBounds(230, 10, 15, 20);
        valuePane2.add(to);
        to = new JLabel("to");
        to.setBounds(230, 50, 15, 20);
        valuePane2.add(to);
        to = new JLabel("to");
        to.setBounds(230, 90, 15, 20);
        valuePane2.add(to);
        to = new JLabel("to");
        to.setBounds(230, 130, 15, 20);
        valuePane2.add(to);
        to = new JLabel("to");
        to.setBounds(230, 170, 15, 20);
        valuePane2.add(to);
        to = new JLabel("to");
        to.setBounds(230, 210, 15, 20);
        valuePane2.add(to);
        to = new JLabel("to");
        to.setBounds(230, 250, 15, 20);
        valuePane2.add(to);
        // create at labels, set positions and sizes
        at = new JLabel("at");
        at.setBounds(340, 10, 15, 20);
        valuePane2.add(at);
        at = new JLabel("at");
        at.setBounds(340, 50, 15, 20);
        valuePane2.add(at);
        at = new JLabel("at");
        at.setBounds(340, 90, 15, 20);
        valuePane2.add(at);
        at = new JLabel("at");
        at.setBounds(340, 130, 15, 20);
        valuePane2.add(at);
        at = new JLabel("at");
        at.setBounds(340, 170, 15, 20);
        valuePane2.add(at);
        at = new JLabel("at");
        at.setBounds(340, 210, 15, 20);
        valuePane2.add(at);
        at = new JLabel("at");
        at.setBounds(340, 250, 15, 20);
        valuePane2.add(at);

        //Checkbox settings
        mon.setBounds(10, 10, 50, 20);
        tue.setBounds(10, 50, 50, 20);
        wed.setBounds(10, 90, 60, 20);
        thu.setBounds(10, 130, 50, 20);
        fri.setBounds(10, 170, 50, 20);
        sat.setBounds(10, 210, 50, 20);
        sun.setBounds(10, 250, 50, 20);

        //Textfield settings
        idText.setBounds(10, 10, 150, 25);
        teacherTextField.setBounds(175, 50, 100, 25);
        teacherTextField.setFocusable(false);
        nameText.setBounds(10, 50, 150, 25);
        codeText.setBounds(10, 90, 150, 25);

        //dateChooser settings
        dateChooser1.setBounds(10, 130, 150, 25);
        dateChooser2.setBounds(10, 170, 150, 25);
        dateChooser1.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    System.out.println(evt.getPropertyName()
                            + ": " + (Date) evt.getNewValue());
//                    date = evt.getNewValue().toString();
                    startDate = String.format("%1$td-%1$tm-%1$tY", evt.getNewValue());
                    System.out.println(startDate);
                }
            }
        });

        dateChooser2.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    System.out.println(evt.getPropertyName()
                            + ": " + (Date) evt.getNewValue());
//                    date = evt.getNewValue().toString();
                    endDate = String.format("%1$td-%1$tm-%1$tY", evt.getNewValue());
                    System.out.println(endDate);
                }
            }
        });

        //Combobox settings
        typeCB.setBounds(10, 250, 110, 25);
        typeCB.setMaximumRowCount(4);
//        typeCB.addItem("Ballet");
//        typeCB.addItem("Hiphop");
//        typeCB.addItem("Guitar");
//        typeCB.addItem("Organ");
//        typeCB.addItem("Painting");
//        typeCB.addItem("Photography");
//        typeCB.addItem("Singing");
//        typeCB.addItem("Piano");
        for (ClassType ct : Data.classTypeList) {
            typeCB.addItem(ct.getName());
        }
        if (!Data.classTypeList.isEmpty()) {
            for (ClassType ct : Data.classTypeList) {
                if (ct.getName().equals((String) typeCB.getSelectedItem())) {
                    numberOfLessonAvailable.setText("Number of Lesson: " + " " + ct.getLessonPerWeek());
                    numberOfLesson = Integer.parseInt(ct.getLessonPerWeek());
                    break;
                }
            }

        }

        // method to set time for start time and end time combo boxes
        for (int i = 0; i < weektimes.length; i++) {
            monStartTime.addItem(weektimes[i]);
            tueStartTime.addItem(weektimes[i]);
            wedStartTime.addItem(weektimes[i]);
            thuStartTime.addItem(weektimes[i]);
            friStartTime.addItem(weektimes[i]);
            monEndTime.addItem(weektimes[i]);
            tueEndTime.addItem(weektimes[i]);
            wedEndTime.addItem(weektimes[i]);
            thuEndTime.addItem(weektimes[i]);
            friEndTime.addItem(weektimes[i]);

            if (i >= 28) {
                satStartTime.addItem(weektimes[i]);
                sunStartTime.addItem(weektimes[i]);
                satEndTime.addItem(weektimes[i]);
                sunEndTime.addItem(weektimes[i]);
            }
        }
//        for (int i = 8; i <= 20; i++) { // add items for startTime and endTime
//            int j = 1;
//            String num = "";
//            if (i == 8 || i == 9) {
//                num = "0" + i;
//            } else {
//                num = i + "";
//            }
//            if (j % 2 != 0) {
//
//                monStartTime.addItem(num + ":00");
//                tueStartTime.addItem(num + ":00");
//                wedStartTime.addItem(num + ":00");
//                thuStartTime.addItem(num + ":00");
//                friStartTime.addItem(num + ":00");
//                satStartTime.addItem(num + ":00");
//                sunStartTime.addItem(num + ":00");
//                monEndTime.addItem(num + ":00");
//                tueEndTime.addItem(num + ":00");
//                wedEndTime.addItem(num + ":00");
//                thuEndTime.addItem(num + ":00");
//                friEndTime.addItem(num + ":00");
//                satEndTime.addItem(num + ":00");
//                sunEndTime.addItem(num + ":00");
//                j++;
//            }
//
//            if (j % 2 == 0 && !num.equals("20")) {
//                monStartTime.addItem(num + ":30");
//                tueStartTime.addItem("" + num + ":30");
//                wedStartTime.addItem("" + num + ":30");
//                thuStartTime.addItem("" + num + ":30");
//                friStartTime.addItem("" + num + ":30");
//                satStartTime.addItem("" + num + ":30");
//                sunStartTime.addItem("" + num + ":30");
//                //
//                monEndTime.addItem("" + num + ":30");
//                tueEndTime.addItem("" + num + ":30");
//                wedEndTime.addItem("" + num + ":30");
//                thuEndTime.addItem("" + num + ":30");
//                friEndTime.addItem("" + num + ":30");
//                satEndTime.addItem("" + num + ":30");
//                sunEndTime.addItem("" + num + ":30");
//                j++;
//            }
//        }

        //Add room on Room array

        for(int i = 0; i < Data.roomList.size(); i++){
            monRoom.addItem(Data.roomList.get(i).getNumber());
            tueRoom.addItem(Data.roomList.get(i).getNumber());
            wedRoom.addItem(Data.roomList.get(i).getNumber());
            thuRoom.addItem(Data.roomList.get(i).getNumber());
            friRoom.addItem(Data.roomList.get(i).getNumber());
            satRoom.addItem(Data.roomList.get(i).getNumber());
            sunRoom.addItem(Data.roomList.get(i).getNumber());
        }

        if (Data.classList.isEmpty()) {
            this.idText.setText("C1");
        } else {
            String temp = Data.classList.get(Data.classList.size() - 1).getId();
            int num = Integer.parseInt(temp.substring(1));
            num++;
            this.idText.setText("C" + num);
        }

        // method to set appropriate room for certain class type
                /*
         * room also has to have a type: 1to1, 1tomany
         */
        // set bounds for start and end time combo boxes
        monStartTime.setBounds(145, 8, 65, 25);
        tueStartTime.setBounds(145, 48, 65, 25);
        wedStartTime.setBounds(145, 88, 65, 25);
        thuStartTime.setBounds(145, 128, 65, 25);
        friStartTime.setBounds(145, 168, 65, 25);
        satStartTime.setBounds(145, 208, 65, 25);
        sunStartTime.setBounds(145, 248, 65, 25);
        monEndTime.setBounds(260, 8, 65, 25);
        tueEndTime.setBounds(260, 48, 65, 25);
        wedEndTime.setBounds(260, 88, 65, 25);
        thuEndTime.setBounds(260, 128, 65, 25);
        friEndTime.setBounds(260, 168, 65, 25);
        satEndTime.setBounds(260, 208, 65, 25);
        sunEndTime.setBounds(260, 248, 65, 25);
        // set bounds for room combo box
        monRoom.setBounds(370, 8, 80, 25);
        tueRoom.setBounds(370, 48, 80, 25);
        wedRoom.setBounds(370, 88, 80, 25);
        thuRoom.setBounds(370, 128, 80, 25);
        friRoom.setBounds(370, 168, 80, 25);
        satRoom.setBounds(370, 208, 80, 25);
        sunRoom.setBounds(370, 248, 80, 25);

        // Create an inner Listener class to display the date picker
        // when user clicks on sdField and edField
        class CalListener implements ItemListener {

            @Override
            public void itemStateChanged(ItemEvent e) {
                JToggleButton b = (JToggleButton) e.getSource();
                if (b.isSelected()) {
                    datePickerPane.setLayout(null);
                    datePickerPane.setBorder(BorderFactory.createLineBorder(Color.green));
                    datePicker.setBounds(0, 0, 290, 200);
                    datePicker.setMaxDayCharacters(1);
                    datePicker.setFont(new Font(Font.SERIF, Font.PLAIN, 8));
                    datePickerPane.add(datePicker);
                    datePickerPane.setBounds(160, 130, 500, 150);
                    ClassForm.getInstance().getScroll().setFocusable(false);
                    ClassForm.getInstance().getScroll().setEnabled(false);
//                    ClassForm.getInstance().getScroll().disable();
                    valuePane1.add(datePickerPane, 0);
//                    ClassForm.getInstance().setEnabled(false);

                } else {
                    valuePane1.remove(datePickerPane);
                    ClassForm.getInstance().getScroll().setFocusable(true);
                    ClassForm.getInstance().getScroll().setFocusable(true);
//                    ClassForm.getInstance().getScroll().enable();
//                    ClassForm.getInstance().getValuePane2().setEnabled(true);
//                    ClassForm.getInstance().setEnabled(true);
                    repaint();
                }
            }
        }

        //Text area settings
        tuitionFeeR.setBounds(10, 210, 150, 25);


        // Panels settings
        labelPane.setBounds(0, 0, 80, 560);
//        labelPane.setBorder(BorderFactory.createLineBorder(Color.blue));
        valuePane1.setBounds(80, 0, 450, 280);
//        valuePane1.setBorder(BorderFactory.createLineBorder(Color.red));
        picPane.setBounds(500, 0, 300, 250);
//        picPane.setBorder(BorderFactory.createLineBorder(Color.green));
        valuePane2.setBounds(80, 280, 450, 280);
//        valuePane2.setBorder(BorderFactory.createLineBorder(Color.orange));
        buttonPane.setBounds(0, 560, 530, 40);
//        buttonPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        // Set panels layouts
        labelPane.setLayout(null);
        valuePane1.setLayout(null);
        picPane.setLayout(null);
        valuePane2.setLayout(null);
        buttonPane.setLayout(null);

        // Button settings
        addStudent.setBounds(5, 10, 120, 25);
        assignTeacher.setBounds(135, 10, 135, 25);
        add.setBounds(275, 10, 120, 25);
        cancel.setBounds(405, 10, 120, 25);
        addStudent.addActionListener(classCon);
        assignTeacher.addActionListener(classCon);

        // Scrollpane setting
        scroll.setBounds(300, 50, 150, 190);
        scroll.add(studentList);
        scroll.setViewportView(studentList);

        // Add labels to panels
        labelPane.add(id);
        labelPane.add(name);
        labelPane.add(code);
        labelPane.add(start);
        labelPane.add(end);
        labelPane.add(type);
        labelPane.add(numberOfLessonAvailable);
        labelPane.add(details);
        labelPane.add(tuitionFee);
        valuePane1.add(studentEnrolled);
        valuePane1.add(teacherLabel);

        // Add textfield to panels
        valuePane1.add(idText, 1);
        valuePane1.add(nameText, 1);
        valuePane1.add(codeText, 1);
        valuePane1.add(tuitionFeeR, 1);
        valuePane1.add(dateChooser1, 1);
        valuePane1.add(dateChooser2, 1);
        valuePane1.add(teacherTextField, 1);

        // Add checkbox to panel
        valuePane2.add(mon);
        valuePane2.add(tue);
        valuePane2.add(wed);
        valuePane2.add(thu);
        valuePane2.add(fri);
        valuePane2.add(sat);
        valuePane2.add(sun);

        // Add combo box to panel
        valuePane1.add(typeCB, 1);
        valuePane1.add(numberOfLessonAvailable, 1);
        valuePane2.add(monStartTime);
        valuePane2.add(tueStartTime);
        valuePane2.add(wedStartTime);
        valuePane2.add(thuStartTime);
        valuePane2.add(friStartTime);
        valuePane2.add(satStartTime);
        valuePane2.add(sunStartTime);
        valuePane2.add(monEndTime);
        valuePane2.add(tueEndTime);
        valuePane2.add(wedEndTime);
        valuePane2.add(thuEndTime);
        valuePane2.add(friEndTime);
        valuePane2.add(satEndTime);
        valuePane2.add(sunEndTime);
        valuePane2.add(monRoom);
        valuePane2.add(tueRoom);
        valuePane2.add(wedRoom);
        valuePane2.add(thuRoom);
        valuePane2.add(friRoom);
        valuePane2.add(satRoom);
        valuePane2.add(sunRoom);

        // Add buttons to panel
        buttonPane.add(addStudent);
        buttonPane.add(assignTeacher);
        buttonPane.add(add);
        add.addActionListener(classCon);
        buttonPane.add(cancel);
        cancel.addActionListener(classCon);
        tuitionFeeR.addKeyListener(classCon);
        typeCB.addActionListener(displayCurrentLesson);

        //Add checkbox listener
        mon.addActionListener(classCon);
        tue.addActionListener(classCon);
        wed.addActionListener(classCon);
        thu.addActionListener(classCon);
        fri.addActionListener(classCon);
        sat.addActionListener(classCon);
        sun.addActionListener(classCon);
        
        this.addWindowListener(classCon);

        // Add scroll to panel
        valuePane1.add(scroll, 2);

        // Add panels to frame
        add(labelPane);
        add(valuePane1);
//        add(picPane);
        add(valuePane2);
        add(buttonPane);
        setTitle("Add Class Form");

        if (LanguageController.getInstance().isClicked()) {
            try {
                resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
                //Add Class Form
                this.id.setText(resources.getString("classID"));
                this.name.setText(resources.getString("className"));
                this.code.setText(resources.getString("classCode"));
                this.start.setText(resources.getString("startDate"));
                this.end.setText(resources.getString("endDate"));
                this.tuitionFee.setText(resources.getString("tuitionFee"));
                this.type.setText(resources.getString("classType"));
                this.details.setText(resources.getString("dateTime"));
                this.teacherLabel.setText(resources.getString("Teacher"));
                this.studentEnrolled.setText(resources.getString("studentEnrolled"));
                
                this.addStudent.setText(resources.getString("addStudent"));
                this.assignTeacher.setText(resources.getString("assignTeacher"));
                this.add.setText(resources.getString("Add"));
                this.cancel.setText(resources.getString("Cancel"));
                
                this.setTitle(resources.getString("addClassForm"));
            } catch (MissingResourceException mre) {
                System.err.println("MyData.properties not found");
            }
        }

        // Frame settings
        setLayout(null);
        setSize(540, 630);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("updating!!!!!!!!!!!!!!!!!!!!!!!!!");

        // if user is adding class
        if (o instanceof StudentListModel) {
            if (ClassForm.getInstance().getOption().equalsIgnoreCase("add")) {
                System.out.println("adding students' names in list");
                if (StudentListModel.getInstance().getSelectedItem().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No students selected. ");
                } else {
                    int size = StudentListModel.getInstance().getSelectedItem().size();
                    String[] names = new String[size];
                    for (int i = 0; i < size; i++) {
                        names[i] = StudentListModel.getInstance().getSelectedItem().get(i).getName();
                    }
                    studentList.setListData(names);
                }
            }

            // if user is editing a class
            if (ClassForm.getInstance().getOption().equalsIgnoreCase("edit")) {
                if (StudentListModel.getInstance().getSelectedItem().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No students selected. ");
                } else {
                    int index = 0;
                    int size = StudentListModel.getInstance().getSelectedItem().size() + ClassForm.getInstance().getStudentList().getModel().getSize();
                    System.out.println("size of name array: " + size);
                    String[] names = new String[size];
                    for (int i = 0; i < ClassForm.getInstance().getStudentList().getModel().getSize(); i++) {
                        names[index] = (String) ClassForm.getInstance().getStudentList().getModel().getElementAt(i);
                        index++;
                    }
                    for (int i = 0; i < StudentListModel.getInstance().getSelectedItem().size(); i++) {
                        names[index] = StudentListModel.getInstance().getSelectedItem().get(i).getName();
                        index++;
                    }
                    studentList.setListData(names);
//                    StudentListModel.getInstance().getSelectedItem().clear();
                }
            }
        }

        // if get notify from StudentListView
        if (o instanceof TeacherListModel) {
            System.out.println("adding teacher's name");
            if (TeacherListModel.getInstance().getSelectedItem().isEmpty()) {
                JOptionPane.showMessageDialog(null, "No teacher selected. ");
            } else {
                int size = TeacherListModel.getInstance().getSelectedItem().size();
                String teacherName = TeacherListModel.getInstance().getSelectedItem().get(0).getName();
                teacherTextField.setText(teacherName);
//                for (int i = 0; i < size; i++) {
////                    students.add(StudentListModel.getInstance().getSelectedItem().get(i).getName());
//                    names[i] = TeacherListModel.getInstance().getSelectedItem().get(i).getName();
//                }
            }
        }

        // if get notify from DataValidation
        if (o instanceof DataValidation) {
            boolean[] results = (boolean[]) arg;

            // show message log if there's any invalid input
            for (int i = 0; i < results.length; i++) {
                if (results[i] == false) {
                    JOptionPane.showMessageDialog(null, "Invalid inputs ! Please check"
                            + " red input fields");
                    break;
                }
            }

            // check and highligh invalid input fields
            if (!results[DataValidation.CLASS_NAME]) {
                this.getNameText().setBorder(BorderFactory.createLineBorder(Color.red));
                JOptionPane.showMessageDialog(null, "Class name can not be empty");
            } else {
                this.getNameText().setBorder(null);
            }
            if (!results[DataValidation.CLASS_CODE]) {
                this.getCodeText().setBorder(BorderFactory.createLineBorder(Color.red));
                JOptionPane.showMessageDialog(null, "Class code can not be empty");
            } else {
                this.getCodeText().setBorder(null);
            }
            if (!results[DataValidation.CLASS_START_DATE]) {
                this.getDateChooser1().setBorder(BorderFactory.createLineBorder(Color.red));
                JOptionPane.showMessageDialog(null, "Please choose a start date");
                System.out.println("date is empty");
            } else {
                this.getDateChooser1().setBorder(null);
            }
            if (!results[DataValidation.CLASS_END_DATE]) {
                this.getDateChooser2().setBorder(BorderFactory.createLineBorder(Color.red));
                JOptionPane.showMessageDialog(null, "Please choose an end date");
            } else {
                this.getDateChooser2().setBorder(null);
            }
            if (!results[DataValidation.TUITION_FEE]) {
                this.getTuitionFeeR().setBorder(BorderFactory.createLineBorder(Color.red));
            } else {
                this.getTuitionFeeR().setBorder(null);
            }

            if (!results[DataValidation.MON]) {
                this.getMonStartTime().setBackground(Color.red);
                this.getMonEndTime().setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "Start Time cannot be before End Time. A class can only be between 45-60 mins");
            } else if (!results[DataValidation.MON_COL]){
                this.getMonStartTime().setBackground(Color.red);
                this.getMonEndTime().setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "Current class collides with another class in the system");
            }else {
                this.getMonStartTime().setBackground(null);
                this.getMonEndTime().setBackground(null);
            }

            if (!results[DataValidation.TUE]) {
                this.getTueStartTime().setBackground(Color.red);
                this.getTueEndTime().setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "Start Time cannot be before End Time. A class can only be between 45-60 mins");
            } else if (!results[DataValidation.TUE_COL]) {
                this.getTueStartTime().setBackground(Color.red);
                this.getTueEndTime().setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "Current class collides with another class in the system");
            } else {
                this.getTueStartTime().setBackground(null);
                this.getTueEndTime().setBackground(null);
            }

            if (!results[DataValidation.WED]) {
                this.getWedStartTime().setBackground(Color.red);
                this.getWedEndTime().setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "Start Time cannot be before End Time. A class can only be between 45-60 mins");
            } else if (!results[DataValidation.WED_COL]) {
                this.getWedStartTime().setBackground(Color.red);
                this.getWedEndTime().setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "Current class collides with another class in the system");
            } else {
                this.getWedStartTime().setBackground(null);
                this.getWedEndTime().setBackground(null);
            }

            if (!results[DataValidation.THU]) {
                this.getThuStartTime().setBackground(Color.red);
                this.getThuEndTime().setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "Start Time cannot be before End Time. A class can only be between 45-60 mins");
            } else if (!results[DataValidation.THU_COL]) {
                this.getThuStartTime().setBackground(Color.red);
                this.getThuEndTime().setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "Current class collides with another class in the system");
            } else {
                this.getThuStartTime().setBackground(null);
                this.getThuEndTime().setBackground(null);
            }

            if (!results[DataValidation.FRI]) {
                this.getFriStartTime().setBackground(Color.red);
                this.getFriEndTime().setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "Start Time cannot be before End Time. A class can only be between 45-60 mins");
            } else if (!results[DataValidation.FRI_COL]) {
                this.getFriStartTime().setBackground(Color.red);
                this.getFriEndTime().setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "Current class collides with another class in the system");
            } else {
                this.getFriStartTime().setBackground(null);
                this.getFriEndTime().setBackground(null);
            }

            if (!results[DataValidation.SAT]) {
                this.getSatStartTime().setBackground(Color.red);
                this.getSatEndTime().setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "Start Time cannot be before End Time. A class can only be between 45-60 mins");
            } else if (!results[DataValidation.SAT_COL]) {
                this.getSatStartTime().setBackground(Color.red);
                this.getSatEndTime().setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "Current class collides with another class in the system");
            } else {
                this.getSatStartTime().setBackground(null);
                this.getSatEndTime().setBackground(null);
            }
            
            if (!results[DataValidation.SUN]) {
                this.getSunStartTime().setBackground(Color.red);
                this.getSunEndTime().setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "Start Time cannot be before End Time. A class can only be between 45-60 mins");
            } else if (!results[DataValidation.SUN_COL]) {
                this.getSunStartTime().setBackground(Color.red);
                this.getSunEndTime().setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "Current class collides with another class in the system");
            } else {
                this.getSunStartTime().setBackground(null);
                this.getSunEndTime().setBackground(null);
            }
        }
    }

    class DisplayCurrentLesson implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JComboBox) {
                if (!Data.classTypeList.isEmpty()) {
                    for (ClassType ct : Data.classTypeList) {
                        if (ct.getName().equals((String) typeCB.getSelectedItem())) {
                            numberOfLessonAvailable.setText("Number of Lesson: " + " " + ct.getLessonPerWeek());
                            numberOfLesson = Integer.parseInt(ct.getLessonPerWeek());
                            break;
                        }
                    }

                }
            }
        }
    }

    /*
     * GETTER - SETTER
     */

    public JDateChooser getDateChooser1() {
        return dateChooser1;
    }

    public JDateChooser getDateChooser2() {
        return dateChooser2;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public JPanel getLabelPane() {
        return labelPane;
    }

    public void setLabelPane(JPanel labelPane) {
        this.labelPane = labelPane;
    }

    public JLayeredPane getValuePane1() {
        return valuePane1;
    }

    public void setValuePane1(JLayeredPane valuePane1) {
        this.valuePane1 = valuePane1;
    }

    public JPanel getPicPane() {
        return picPane;
    }

    public void setPicPane(JPanel picPane) {
        this.picPane = picPane;
    }

    public JPanel getValuePane2() {
        return valuePane2;
    }

    public void setValuePane2(JPanel valuePane2) {
        this.valuePane2 = valuePane2;
    }

    public JPanel getButtonPane() {
        return buttonPane;
    }

    public void setButtonPane(JPanel buttonPane) {
        this.buttonPane = buttonPane;
    }

    public JPanel getDatePickerPane() {
        return datePickerPane;
    }

    public void setDatePickerPane(JPanel datePickerPane) {
        this.datePickerPane = datePickerPane;
    }

    public JCalendar getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(JCalendar datePicker) {
        this.datePicker = datePicker;
    }

    public JTextField getNameText() {
        return nameText;
    }

    public void setNameText(JTextField nameText) {
        this.nameText = nameText;
    }

    public JTextField getCodeText() {
        return codeText;
    }

    public void setCodeText(JTextField codeText) {
        this.codeText = codeText;
    }

    public JComboBox getTypeCB() {
        return typeCB;
    }

    public void setTypeCB(JComboBox typeCB) {
        this.typeCB = typeCB;
    }

    public JComboBox getFromCB() {
        return fromCB;
    }

    public void setFromCB(JComboBox fromCB) {
        this.fromCB = fromCB;
    }

    public JComboBox getToCB() {
        return toCB;
    }

    public void setToCB(JComboBox toCB) {
        this.toCB = toCB;
    }

    public JComboBox getAtCB() {
        return atCB;
    }

    public void setAtCB(JComboBox atCB) {
        this.atCB = atCB;
    }

    public JComboBox getMonStartTime() {
        return monStartTime;
    }

    public void setMonStartTime(JComboBox monStartTime) {
        this.monStartTime = monStartTime;
    }

    public JComboBox getTueStartTime() {
        return tueStartTime;
    }

    public void setTueStartTime(JComboBox tueStartTime) {
        this.tueStartTime = tueStartTime;
    }

    public JComboBox getWedStartTime() {
        return wedStartTime;
    }

    public void setWedStartTime(JComboBox wedStartTime) {
        this.wedStartTime = wedStartTime;
    }

    public JComboBox getThuStartTime() {
        return thuStartTime;
    }

    public void setThuStartTime(JComboBox thuStartTime) {
        this.thuStartTime = thuStartTime;
    }

    public JComboBox getFriStartTime() {
        return friStartTime;
    }

    public void setFriStartTime(JComboBox friStartTime) {
        this.friStartTime = friStartTime;
    }

    public JComboBox getSatStartTime() {
        return satStartTime;
    }

    public void setSatStartTime(JComboBox satStartTime) {
        this.satStartTime = satStartTime;
    }

    public JComboBox getMonEndTime() {
        return monEndTime;
    }

    public void setMonEndTime(JComboBox monEndTime) {
        this.monEndTime = monEndTime;
    }

    public JComboBox getTueEndTime() {
        return tueEndTime;
    }

    public void setTueEndTime(JComboBox tueEndTime) {
        this.tueEndTime = tueEndTime;
    }

    public JComboBox getWedEndTime() {
        return wedEndTime;
    }

    public void setWedEndTime(JComboBox wedEndTime) {
        this.wedEndTime = wedEndTime;
    }

    public JComboBox getThuEndTime() {
        return thuEndTime;
    }

    public void setThuEndTime(JComboBox thuEndTime) {
        this.thuEndTime = thuEndTime;
    }

    public JComboBox getFriEndTime() {
        return friEndTime;
    }

    public void setFriEndTime(JComboBox friEndTime) {
        this.friEndTime = friEndTime;
    }

    public JComboBox getSatEndTime() {
        return satEndTime;
    }

    public void setSatEndTime(JComboBox satEndTime) {
        this.satEndTime = satEndTime;
    }

    public JComboBox getMonRoom() {
        return monRoom;
    }

    public void setMonRoom(JComboBox monRoom) {
        this.monRoom = monRoom;
    }

    public JComboBox getTueRoom() {
        return tueRoom;
    }

    public void setTueRoom(JComboBox tueRoom) {
        this.tueRoom = tueRoom;
    }

    public JComboBox getWedRoom() {
        return wedRoom;
    }

    public void setWedRoom(JComboBox wedRoom) {
        this.wedRoom = wedRoom;
    }

    public JComboBox getThuRoom() {
        return thuRoom;
    }

    public void setThuRoom(JComboBox thuRoom) {
        this.thuRoom = thuRoom;
    }

    public JComboBox getFriRoom() {
        return friRoom;
    }

    public void setFriRoom(JComboBox friRoom) {
        this.friRoom = friRoom;
    }

    public JComboBox getSatRoom() {
        return satRoom;
    }

    public void setSatRoom(JComboBox satRoom) {
        this.satRoom = satRoom;
    }

    public JCheckBox getMon() {
        return mon;
    }

    public void setMon(JCheckBox mon) {
        this.mon = mon;
    }

    public JCheckBox getTue() {
        return tue;
    }

    public void setTue(JCheckBox tue) {
        this.tue = tue;
    }

    public JCheckBox getWed() {
        return wed;
    }

    public void setWed(JCheckBox wed) {
        this.wed = wed;
    }

    public JCheckBox getThu() {
        return thu;
    }

    public void setThu(JCheckBox thu) {
        this.thu = thu;
    }

    public JCheckBox getFri() {
        return fri;
    }

    public void setFri(JCheckBox fri) {
        this.fri = fri;
    }

    public JCheckBox getSat() {
        return sat;
    }

    public void setSat(JCheckBox sat) {
        this.sat = sat;
    }

    public JTextField getTuitionFeeR() {
        return tuitionFeeR;
    }

    public void setTuitionFeeR(JFormattedTextField tuitionFeeR) {
        this.tuitionFeeR = tuitionFeeR;
    }

    public JButton getAddStudent() {
        return addStudent;
    }

    public void setAddStudent(JButton addStudent) {
        this.addStudent = addStudent;
    }

    public JButton getAssignTeacher() {
        return assignTeacher;
    }

    public void setAssignTeacher(JButton assignTeacher) {
        this.assignTeacher = assignTeacher;
    }

    public JButton getAdd() {
        return add;
    }

    public void setAdd(JButton done) {
        this.add = done;
    }

    public JButton getCancel() {
        return cancel;
    }

    public void setCancel(JButton cancel) {
        this.cancel = cancel;
    }

    public JScrollPane getScroll() {
        return scroll;
    }

    public void setScroll(JScrollPane scroll) {
        this.scroll = scroll;
    }

    public JList getStudentList() {
        return studentList;
    }

    public void setStudentList(JList studentList) {
        this.studentList = studentList;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public JComboBox getSunStartTime() {
        return sunStartTime;
    }

    public void setSunStartTime(JComboBox sunStartTime) {
        this.sunStartTime = sunStartTime;
    }

    public JComboBox getSunEndTime() {
        return sunEndTime;
    }

    public void setSunEndTime(JComboBox sunEndTime) {
        this.sunEndTime = sunEndTime;
    }

    public JComboBox getSunRoom() {
        return sunRoom;
    }

    public void setSunRoom(JComboBox sunRoom) {
        this.sunRoom = sunRoom;
    }

    public JCheckBox getSun() {
        return sun;
    }

    public void setSun(JCheckBox sun) {
        this.sun = sun;
    }

    public static ClassForm getUnique() {
        return unique;
    }

    public int getNumberOfdays() {
        return numberOfdays;
    }

    public void setNumberOfdays(int numberOfdays) {
        this.numberOfdays = numberOfdays;
    }

    public JCheckBox[] getDays() {
        return days;
    }

    public void setDays(JCheckBox[] days) {
        this.days = days;
    }

    public void setDateChooser1(JDateChooser dateChooser1) {
        this.dateChooser1 = dateChooser1;
    }

    public void setDateChooser2(JDateChooser dateChooser2) {
        this.dateChooser2 = dateChooser2;
    }

    public static void setUnique(ClassForm unique) {
        ClassForm.unique = unique;
    }

    public JLabel getIdText() {
        return idText;
    }

    public void setIdText(JLabel idText) {
        this.idText = idText;
    }

    public JTextField getTeacherTextField() {
        return teacherTextField;
    }

    public int getNumberOfLesson() {
        return numberOfLesson;
    }

    public void setNumberOfLesson(int numberOfLesson) {
        this.numberOfLesson = numberOfLesson;
    }
}
