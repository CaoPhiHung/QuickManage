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
public class TeacherListModel extends Observable {
    private ArrayList<TeacherListItem> selectedItems = new ArrayList<>();
    private ArrayList<Teacher> tempTeacherList = new ArrayList<>();
    private int count  = 0;

    public void initTempTeacherList() {
        for (int i = 0; i < selectedItems.size(); i++) {
            tempTeacherList.add(selectedItems.get(i).getTeacher());
        }
    }

    public ArrayList<Teacher> getTempTeacherLists() {
        return tempTeacherList;
    }
    
    private static TeacherListModel unique;

    /* only one instance of TeacherListModel can exist at a time */
    public static TeacherListModel getInstance() {
        if (unique == null) {
            unique = new TeacherListModel();
        }
        return unique;
    }

    /* this method add an item into arraylist StudentListItem */
    public void addItem(TeacherListItem tli) {
        selectedItems.add(tli);
        count++;
    }

    /* this method removes an item from array list StudentListItem when an
     * item is uncheck from the List
     * because arrayList add at the end
     */
    public void removeItem(TeacherListItem tli) {
        selectedItems.remove(tli);
        count--;
    }
    
    /* getter for arraylist selectedItems */
    public ArrayList<TeacherListItem> getSelectedItem() {
        return selectedItems;
    }

    /* this method add and notify class form as observer */
    public void notifyClassForm() {
        addObserver(ClassForm.getInstance());
        setChanged();
        notifyObservers(selectedItems);
    }

    public int getCount() {
        return count;
    }

    /* check method - list all the items selected */
    public void listItems() {
        for (int i = 0; i < selectedItems.size(); i++) {
            System.out.println(i);
            System.out.println(selectedItems.get(i).getName());
        }
    }
}
