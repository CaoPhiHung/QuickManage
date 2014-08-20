package View;

import Controller.LanguageController;
import TimetableUtilities.*;
import java.awt.event.*;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.*;

public class StudentTimeTable extends JFrame {
    //Properties

    private static StudentTimeTable unique;
    private Timetable timeTable = new Timetable();
    private JButton close = new JButton("Close");
    private ResourceBundle resources;

    //Singeton
    public static StudentTimeTable getInstance() {
        if (unique == null) {
            unique = new StudentTimeTable();
        }
        return unique;
    }

    //Initialize
    public void initialize() {
        setLayout(null);
        timeTable.settings();

        timeTable.setBounds(0, -3, 800, 600);

        //Set Close button
        close.setBounds(300, 600, 200, 30);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ControlPanelForm.getInstance().setEnabled(true);
                ControlPanelForm.getInstance().setVisible(true);
            }
        });
        add(close);
        add(timeTable);
        setTitle("Student's TimeTable");

        if (LanguageController.getInstance().isClicked()) {
            try {
                resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
                //Add Manager Form
                this.close.setText(resources.getString("Close"));
                setTitle(resources.getString("StudentTimeTable"));
            } catch (MissingResourceException mre) {
                System.err.println("MyData.properties not found");
            }
        }
        //setLayout(new BorderLayout());
        setSize(806, 660);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent we) {
                ControlPanelForm.getInstance().setEnabled(false);
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
        });
    }

    public Timetable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(Timetable timeTable) {
        this.timeTable = timeTable;
    }

    public static void setUnique(StudentTimeTable unique) {
        StudentTimeTable.unique = unique;
    }
}
