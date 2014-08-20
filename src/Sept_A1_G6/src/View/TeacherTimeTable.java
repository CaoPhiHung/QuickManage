package View;

import Controller.LanguageController;
import TimetableUtilities.*;
import java.awt.*;
import java.awt.event.*;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.*;

public class TeacherTimeTable extends JFrame {

    private static TeacherTimeTable unique;
    private Timetable timeTable = new Timetable();
    private JPanel timeTablePanel = new JPanel(new GridLayout(1, 1));
    private JButton close = new JButton("Close");
    private ResourceBundle resources;

    //Singeton
    public static TeacherTimeTable getInstance() {
        if (unique == null) {
            unique = new TeacherTimeTable();
        }
        return unique;
    }

    public void initialize() {
        setLayout(null);
        timeTable = new Timetable();
        timeTable.settings();
        //remove old time tables
        timeTablePanel.removeAll();
        //JFrame's settings
        timeTablePanel.setBounds(0, -3, 820, 600);
        timeTablePanel.add(timeTable);
        add(timeTablePanel);
        //set close button
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
        setTitle("Teacher's TimeTable");

        if (LanguageController.getInstance().isClicked()) {
            try {
                resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
                //Add Manager Form
                this.close.setText(resources.getString("Close"));
                setTitle(resources.getString("TeacherTimeTable"));
            } catch (MissingResourceException mre) {
                System.err.println("MyData.properties not found");
            }
        }
        //Set settings for the ManagerForm
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

    public static TeacherTimeTable getUnique() {
        return unique;
    }

    public static void setUnique(TeacherTimeTable unique) {
        TeacherTimeTable.unique = unique;
    }

    public Timetable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(Timetable timeTable) {
        this.timeTable = timeTable;
    }
}
