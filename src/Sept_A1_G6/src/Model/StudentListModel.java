/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.ClassForm;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author minh
 */
public class StudentListModel extends Observable {

    private ArrayList<StudentListItem> selectedItems = new ArrayList<>();
    private ArrayList<Student> tempStudentList = new ArrayList<>();

    public void initTempStudentList() {
        for (int i = 0; i < selectedItems.size(); i++) {
            tempStudentList.add(selectedItems.get(i).getStd());
        }
    }

    public ArrayList<Student> getTempStudentLists() {
        return tempStudentList;
    }
    private static StudentListModel unique;

    /* only one instance of StudentListModel can exist at a time */
    public static StudentListModel getInstance() {
        if (unique == null) {
            unique = new StudentListModel();
        }
        return unique;
    }

    /* this method add an item(student) into arraylist StudentListItem */
    public void addItem(StudentListItem sli) {
        selectedItems.add(sli);
    }

    /* this method removes an item from array list StudentListItem when an
     * item is uncheck from the List
     * because arrayList add at the end
     */
    public void removeItem(StudentListItem sli) {
        selectedItems.remove(sli);
    }

    /* getter for arraylist selectedItems */
    public ArrayList<StudentListItem> getSelectedItem() {
        return selectedItems;
    }

    /* this method add and notify class form as observer */
    public void notifyClassForm() {
        addObserver(ClassForm.getInstance());
        setChanged();
        notifyObservers(selectedItems);
    }

    /* check method - list all the items selected */
    public void listItems() {
        for (int i = 0; i < selectedItems.size(); i++) {
            System.out.println(i);
            System.out.println(selectedItems.get(i).getName());
        }
    }


    public void setTempStudentList(ArrayList<Student> tempStudentList) {
        this.tempStudentList = tempStudentList;
    }
    
}
