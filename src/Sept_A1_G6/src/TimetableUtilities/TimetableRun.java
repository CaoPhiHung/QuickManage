package TimetableUtilities;

import javax.swing.*;

public class TimetableRun extends JFrame {

    private static TimetableRun unique;
    private Timetable timetable;
    private JButton but = new JButton("Test");
    private JButton butDel = new JButton("Delete");

    public static TimetableRun getInstance() {
        if (unique == null) {
            unique = new TimetableRun();
        }
        return unique;
    }

    public void initialize() {
        //settings
        setLayout(null);
        timetable = new Timetable();
        timetable.settings();

        add(timetable);
        add(but);
        add(butDel);

        timetable.setBounds(0, 0, 800, 600);
        but.setBounds(850, 650, 100, 80);
        butDel.setBounds(850, 550, 100, 80);
        but.addMouseListener(timetable.getTimeController());
        butDel.addMouseListener(timetable.getTimeController());

        //JFrame's settings 
        //setLayout(new BorderLayout());
        setTitle("Timetable");
        setSize(1024, 768);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TimetableRun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TimetableRun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TimetableRun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TimetableRun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        TimetableRun run = TimetableRun.getInstance();
        run.initialize();
    }
}
