package View;

import Controller.StudentListController;
import Model.Data;
import Model.StudentListItem;
import java.awt.Font;
import java.awt.TextField;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class StudentListView extends JFrame {

    private JPanel searchPanel = new JPanel();
    private JPanel studentPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JButton search = new JButton("Search");
    private JButton enroll = new JButton("Enroll");
    private JButton cancel = new JButton("Cancel");
    private JList list;
    private JScrollPane scroll = new JScrollPane();
    private TextField searchField = new TextField();
    private StudentListController slc = new StudentListController();
    private static StudentListView unique;

    /*
     * This method creates a singleton of StudentListView
     */
    public static StudentListView getInstance() {
        if (unique == null) {
            unique = new StudentListView();
        }
        return unique;
    }
    /*
     * This method initialize student list the method read all student in
     * Data.studentList and put them into a custom JList
     */

    public void initialize() {

        Font font = new Font("Agency FB", Font.PLAIN, 14);

        StudentListItem[] items;

        /*
         * This code fragment leave out all the students who have already
         * enrolled in the current evaluating class
         *
         * IF there is no students in the class add all students into items for
         * display ELSE IF student is not the one already enrolled in current
         * class add the student into intems for display
         */
        if (ClassForm.getInstance().getStudentList().getModel().getSize() == 0) {
            System.out.println("No students in class yet");
            items = new StudentListItem[Data.studentList.size()];
            for (int i = 0; i < Data.studentList.size(); i++) {
                items[i] = new StudentListItem(Data.studentList.get(i));
            }
        } else {
            System.out.println("Some students in class");
            System.out.println("size of student list in data: " + Data.studentList.size());
            System.out.println("size of student list: " + ClassForm.getInstance().getStudentList().getModel().getSize());
            int index = 0;
            items = new StudentListItem[Data.studentList.size() - ClassForm.getInstance().getStudentList().getModel().getSize()];
            for (int i = 0; i < Data.studentList.size(); i++) {
                String studentName;
                int count = 0;
                for (int j = 0; j < ClassForm.getInstance().getStudentList().getModel().getSize(); j++) {
                    studentName = Data.studentList.get(i).getLastName() + " " + Data.studentList.get(i).getFirstName();
                    System.out.println(studentName);
                    System.out.println(ClassForm.getInstance().getStudentList().getModel().getElementAt(j));
                    if (studentName.equals(ClassForm.getInstance().getStudentList().getModel().getElementAt(j))) {
                        count++;
                    }
                }
                System.out.println(count);
                if (count == 0) {
                    items[index] = new StudentListItem(Data.studentList.get(i));
                    System.out.println("added students into items");
                    System.out.println(items[index].getId());
                    index++;
                }
            }
        }

//        items = Data.managerList.toArray(new StudentListItem[Data.managerList.size()]);

        list = new JList(items);
        list.setCellRenderer(new StudentListRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addMouseListener(slc);
        System.out.println(Data.studentList.size());
        System.out.println(items.length);

        // buttons settings
        search.setBounds(220, 13, 70, 25);
        enroll.setBounds(50, 10, 80, 25);
        cancel.setBounds(170, 10, 80, 25);
        enroll.addMouseListener(slc);
        cancel.addMouseListener(slc);
        search.addMouseListener(slc);

        // searchField settings
        searchField.setBounds(10, 15, 200, 20);
        searchField.setFont(font);

        // searchPanel settings
        searchPanel.setLayout(null);
        searchPanel.setBounds(0, 0, 300, 50);
//        searchPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));

        // buttonPanel settings
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(0, 370, 300, 50);
//        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        buttonPanel.add(enroll);
        buttonPanel.add(cancel);

        // add textField to panels
        searchPanel.add(searchField);

        // add button to searchPanel
        searchPanel.add(search);

        // studentPanel settings
        studentPanel.setLayout(null);
        studentPanel.setBounds(0, 50, 300, 320);
//        studentPanel.setBorder(BorderFactory.createLineBorder(Color.red));

        // scroll settings
        scroll.setBounds(0, 0, 300, 320);

        // add list to scroll pane
        scroll.add(list);
        scroll.setViewportView(list);
        studentPanel.add(scroll);

        // add stuffs to frame
        add(searchPanel);
        add(studentPanel);
        add(buttonPanel);

        // frame settings
        setLayout(null);
        setResizable(false);
        setTitle("Student List");
        setSize(306, 450);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public StudentListController getSlc() {
        return slc;
    }

    public JList getList() {
        return list;
    }

    /*
     * getters for these buttons so the child class can get these buttons and
     * add other listeners
     */
    public JButton getSearch() {
        return search;
    }

    public JButton getEnroll() {
        return enroll;
    }

    public JButton getCancel() {
        return cancel;
    }

    public TextField getSearchField() {
        return searchField;
    }

    public static void setUnique(StudentListView unique) {
        StudentListView.unique = unique;
    }
    
    
}
