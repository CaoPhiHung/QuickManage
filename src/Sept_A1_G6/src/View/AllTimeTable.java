package View;

import Controller.*;
import Model.*;
import TimetableUtilities.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class AllTimeTable extends JFrame implements Observer {

    //Properties
    private static AllTimeTable unique;
    private Timetable timetable = new Timetable();
    private JLabel classNote = new JLabel("Class");
    private JLabel teacherNote = new JLabel("Teacher");
    private JLabel studentNote = new JLabel("Student");
    private JPanel overAll = new JPanel();
    private JButton close = new JButton("Close");
    private JList listClass = new JList();
    private JList listTeacher = new JList();
    private JList listStudent = new JList();
    private JScrollPane scrollStudent = new JScrollPane();
    private JScrollPane scrollTeacher = new JScrollPane();
    private JScrollPane scrollClass = new JScrollPane();
    private ArrayList<String> allClass;
    private ArrayList<String> allTeacher;
    private ArrayList<String> allStudent;
    private AllTimeTableController timCon = new AllTimeTableController();
    private ResourceBundle resources;

    //Singleton
    public static AllTimeTable getInstance() {
        if (unique == null) {
            unique = new AllTimeTable();
        }
        return unique;
    }

    //Initialize
    public void initialize() {

        //settings
        timCon.addObserver(this);

        overAll.setLayout(null);
        timetable.settings();
        classNote.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        teacherNote.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        studentNote.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));

        scrollClass.setViewportView(listClass);
        scrollTeacher.setViewportView(listTeacher);
        scrollStudent.setViewportView(listStudent);

        allClass = new ArrayList<>();
        allTeacher = new ArrayList<>();
        allStudent = new ArrayList<>();


        //Check all type
        ArrayList<String> tempType = new ArrayList<>();
        for (Classes cla : Data.classList) {
            boolean correct = true;
            for (String tem : tempType) {
                if (cla.getClassType().getName().equals(tem)) {
                    correct = false;
                    break;
                }
            }

            if (correct) {
                tempType.add(cla.getClassType().getName());
            }
        }

        //Display class corresponding totype
        for (int i = 0; i < tempType.size(); i++) {
            for (Classes cla : Data.classList) {
                if (cla.getClassType().getName().equals(tempType.get(i))) {
                    String total = cla.getId() + " | " + cla.getClassName() + " | " + cla.getClassType();
                    allClass.add(total);
                }
            }
        }
        listClass.setListData(allClass.toArray());
        listTeacher.setListData(allTeacher.toArray());
        listStudent.setListData(allStudent.toArray());

        //Adding

        overAll.add(classNote);
        overAll.add(teacherNote);
        overAll.add(studentNote);
        overAll.add(scrollClass);
        overAll.add(scrollTeacher);
        overAll.add(scrollStudent);

        //
        overAll.add(timetable);
        overAll.add(close);

        //
        classNote.setBounds(825, 0, 100, 30);
        teacherNote.setBounds(810, 180, 120, 30);
        studentNote.setBounds(810, 355, 120, 30);
        //
        close.setBounds(360, 598, 200, 30);
        timetable.setBounds(0, -3, 800, 600);
        //
        listClass.setBounds(0, 0, 100, 100);
        listStudent.setBounds(0, 0, 100, 100);
        listTeacher.setBounds(0, 0, 100, 100);
        //
        scrollClass.setBounds(802, 30, 130, 150);
        scrollTeacher.setBounds(802, 210, 130, 145);
        scrollStudent.setBounds(802, 385, 130, 210);

        add(overAll);
        setTitle("All TimeTable");

        if (LanguageController.getInstance().isClicked()) {
            try {
                resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
                //Add Manager Form
                this.classNote.setText(resources.getString("Class"));
                this.teacherNote.setText(resources.getString("Teacher"));
                this.studentNote.setText(resources.getString("Student"));
                this.close.setText(resources.getString("Close"));
                setTitle(resources.getString("AllTimeTable"));

            } catch (MissingResourceException mre) {
                System.err.println("MyData.properties not found");
            }
        }


        //Set Listener
        close.addActionListener(timCon);
        listClass.addListSelectionListener(timCon);

        //JFrame's settings
        setSize(940, 660);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        this.addWindowListener(timCon);
    }

    @Override
    public void update(Observable o, Object arg) {
        listTeacher.setListData(allTeacher.toArray());
        listStudent.setListData(allStudent.toArray());
    }

    public AllTimeTableController getTimCon() {
        return timCon;
    }

    public static void setUnique(AllTimeTable unique) {
        AllTimeTable.unique = unique;
    }

    public ArrayList<String> getAllClass() {
        return allClass;
    }

    public void setAllClass(ArrayList<String> allClass) {
        this.allClass = allClass;
    }

    public Timetable getTimetable() {
        return timetable;
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
    }

    public ArrayList<String> getAllTeacher() {
        return allTeacher;
    }

    public void setAllTeacher(ArrayList<String> allTeacher) {
        this.allTeacher = allTeacher;
    }

    public ArrayList<String> getAllStudent() {
        return allStudent;
    }

    public void setAllStudent(ArrayList<String> allStudent) {
        this.allStudent = allStudent;
    }
}
