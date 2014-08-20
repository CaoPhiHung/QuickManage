/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author minh
 */
public class TeacherListItem {

    String id;
    String name;
    Teacher teacher;
    boolean selected = false;

    public TeacherListItem(Teacher teacher) {
        id = teacher.getID();
        this.teacher = teacher;
        name = teacher.getLastName() + " " + teacher.getFirstName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
}
