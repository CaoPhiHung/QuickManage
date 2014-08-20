package View;

import Controller.*;
import Model.*;
import TimetableUtilities.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class EnrollmentForm extends JFrame implements Observer {

    //Properties
    private static EnrollmentForm unique;
    protected JLabel id = new JLabel("ID: ");
    protected JLabel name = new JLabel("Name: ");
    protected JLabel idR = new JLabel("IDR");
    protected JLabel nameR = new JLabel("NameR");
    protected Timetable timeTable = new Timetable();
    protected JButton close = new JButton("Close");
    protected JScrollPane scroll = new JScrollPane();
    protected JScrollPane enrollScroll = new JScrollPane();
    protected JList classesList = new JList();
    protected JList enrolledList = new JList();
    protected JLabel classNote = new JLabel("Classes");
    protected JLabel enrollNote = new JLabel("Enrolled");
    protected ArrayList<String> allClass;
    protected ArrayList<String> enrolled = new ArrayList<>();
    protected EnrollmentFormController enCon = new EnrollmentFormController();
    private ResourceBundle resources;
    //Singeton

    public static EnrollmentForm getInstance() {
        if (unique == null) {
            unique = new EnrollmentForm();
        }
        return unique;
    }

    //Initialize
    public void initialize(String option) {
        setLayout(null);
        enCon.addObserver(this);

        //Get all classes and put to allClass arraylist
        allClass = new ArrayList<>();
        ArrayList<String> tempClasses = new ArrayList<>();
        for (Classes cla : Data.classList) {
            boolean correct = true;
            for (String tem : tempClasses) {
                if (cla.getClassType().equals(tem)) {
                    correct = false;
                    break;
                }
            }

            if (correct) {
                allClass.add(cla.getClassType().getName());
                tempClasses.add(cla.getClassType().getName());
            }
        }

        //Settings
        classesList.setListData(allClass.toArray());
        classesList.setToolTipText("classesList");
        enrolledList.setToolTipText("enrolledList");


        timeTable.settings();
        classNote.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 25));
        enrollNote.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));

        //Adding
        scroll.setViewportView(classesList);
        enrollScroll.setViewportView(enrolledList);

        add(id);
        add(name);
        add(idR);
        add(nameR);
        add(timeTable);
        add(close);
        add(scroll);
        add(enrollScroll);
        add(classNote);
        add(enrollNote);

        id.setBounds(5, 5, 50, 20);
        name.setBounds(5, 30, 50, 20);
        idR.setBounds(50, 5, 100, 20);
        nameR.setBounds(50, 30, 200, 20);
        timeTable.setBounds(174, -3, 800, 600);
        close.setBounds(360, 600, 200, 30);
        classesList.setBounds(0, 0, 100, 100);
        classNote.setBounds(40, 285, 100, 40);
        enrollNote.setBounds(40, 50, 100, 40);
        //
        scroll.setBounds(3, 321, 165, 275);
        enrollScroll.setBounds(3, 80, 165, 200);

        if (option.equals("Set")) {
            //SEt listeners
            close.addActionListener(enCon);
            classesList.addListSelectionListener(enCon);
            enrolledList.addListSelectionListener(enCon);
        }
        this.addWindowListener(enCon);
        setTitle("Enrollment Form");
        if (LanguageController.getInstance().isClicked()) {
            try {
                resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
                //Add Manager Form
                this.close.setText(resources.getString("Close"));
                this.id.setText(resources.getString("ID"));
                this.name.setText(resources.getString("FirstName"));
                this.classNote.setText(resources.getString("Class"));
                this.enrollNote.setText(resources.getString("enroll"));
                setTitle(resources.getString("EnrollmentForm"));
            } catch (MissingResourceException mre) {
                System.err.println("MyData.properties not found");
            }
        }

        //JFrame's settings 
        setSize(980, 660);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

    }

    @Override
    public void update(Observable o, Object arg) {

        enrolledList.setListData(enrolled.toArray());
        System.out.println("Update enrolled List");
    }

    public static void setUnique(EnrollmentForm unique) {
        EnrollmentForm.unique = unique;
    }

    public JLabel getIdR() {
        return idR;
    }

    public void setIdR(JLabel id) {
        this.idR = id;
    }

    public JLabel getNames() {
        return nameR;
    }

    public void setNames(JLabel name) {
        this.nameR = name;
    }

    public Timetable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(Timetable timeTable) {
        this.timeTable = timeTable;
    }

    public EnrollmentFormController getEnCon() {
        return enCon;
    }

    public void setEnCon(EnrollmentFormController enCon) {
        this.enCon = enCon;
    }

    public ArrayList<String> getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(ArrayList<String> enrolled) {
        this.enrolled = enrolled;
    }
}
