package Model;

public class StudentListItem {

    String id;
    String name;
    Student std;
    boolean selected = false;

    public StudentListItem(Student std) {
        id = std.getID();
        this.std = std;
        name = std.getLastName() + " " + std.getFirstName();
    }

    public Student getStd() {
        return std;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
