package View;

import Controller.*;
import Model.*;

public class TeacherListView extends StudentListView {

    private static TeacherListView unique;
    private TeacherListController tlc = new TeacherListController();

    /* this method creates a singleton for TeacherListView */
    public static TeacherListView getInstance() {
        if (unique == null) {
            unique = new TeacherListView();
        }
        return unique;
    }

    /* this method calls the initialize() of its super class
     * I overwrote super class's initialize() to leave room for any changes in
     * TeacherListView
     */
    @Override
    public void initialize() {

        // initianlize the super class view
        super.initialize();

        // add another listener for the buttons
        super.getCancel().addMouseListener(tlc);
        super.getEnroll().removeMouseListener(super.getSlc());
        super.getEnroll().addMouseListener(tlc);
        super.getSearch().addMouseListener(tlc);


        // get all teachers from teacher list and put in array
        TeacherListItem[] items = new TeacherListItem[Data.teacherList.size()];
        for (int i = 0; i < Data.teacherList.size(); i++) {
            items[i] = new TeacherListItem(Data.teacherList.get(i));
        }
        // put in teacher list in to list
        super.getList().setListData(items);
        super.getList().setCellRenderer(new TeacherListRenderer());
        super.getList().removeMouseListener(super.getSlc());
        super.getList().addMouseListener(new TeacherListController());

        // change text of enroll button from Enroll -> Assign
        super.getEnroll().setText("Assign");
        // change title of the view from Student List -> Teacher List
        setTitle("Teacher List");
    }

    public static TeacherListView getUnique() {
        return unique;
    }

    public static void setUnique(TeacherListView unique) {
        TeacherListView.unique = unique;
    }
    
    
    
}
