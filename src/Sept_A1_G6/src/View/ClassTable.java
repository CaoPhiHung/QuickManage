package View;

import javax.swing.*;

public class ClassTable extends JFrame {

    //Private properties of Class tab
    private JScrollPane classScroll = new JScrollPane();
    private JTable classTable = new JTable();
    private String[] columnNames;
    private Object[][] data;

    //Constructor
    public ClassTable() {
    }

    public void initialize() {
        //Create scroll table
        makeDatabaseClassTable();
        classScroll.setViewportView(classTable);
        classScroll.getVerticalScrollBar().setUnitIncrement(20);
        add(classScroll);
        //Create settings
        setTitle("Time Table");
        setSize(400, 200);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void makeDatabaseClassTable() {
        String[] columnNames = {"Class Code", "Course", "Location", "Time"};
        Object[][] data = {
            {"Kathy", "Smith",
                "Snowboarding", new Integer(5)},
            {"John", "Doe",
                "Rowing", new Integer(3)},
            {"Sue", "Black",
                "Knitting", new Integer(2)},
            {"Jane", "White",
                "Speed reading", new Integer(20)},
            {"Joe", "Brown",
                "Pool", new Integer(10)},
            {"Joe", "Brown",
                "Pool", new Integer(10)},
            {"Joe", "Brown",
                "Pool", new Integer(10)},
            {"Joe", "Brown",
                "Pool", new Integer(10)},
            {"Joe", "Brown",
                "Pool", new Integer(10)},
            {"Joe", "Brown",
                "Pool", new Integer(10)},
            {"Joe", "Brown",
                "Pool", new Integer(10)},
            {"Joe", "Brown",
                "Pool", new Integer(10)}
        };
        classTable = new JTable(data, columnNames);
    }

    public static void main(String[] args) {
        ClassTable table = new ClassTable();
        table.initialize();
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(table);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(table, "Can't change look and feel", "Invalid PLAF",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
