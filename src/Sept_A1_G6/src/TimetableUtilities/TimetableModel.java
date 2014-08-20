package TimetableUtilities;

import java.util.*;
import javax.swing.*;

public class TimetableModel extends Observable {

    private ArrayList<CoverPanel> chosenClass = new ArrayList<>();
    private String[] weekday = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
    private String[] weektime = {
        "09:00 ~ 09:15", "09:15 ~ 09:30", "09:30 ~ 09:45","09:45 ~ 10:00",
        "10:00 ~ 10:15", "10:15 ~ 10:30", "10:30 ~ 10:45","10:45 ~ 11:00",
        "11:00 ~ 11:15", "11:15 ~ 11:30", "11:30 ~ 11:45","11:45 ~ 12:00",
        "12:00 ~ 12:15", "12:15 ~ 12:30", "12:30 ~ 12:45","12:45 ~ 13:00",
        "13:00 ~ 13:15", "13:15 ~ 13:30", "13:30 ~ 13:45","13:45 ~ 14:00",
        "14:00 ~ 14:15", "14:15 ~ 14:30", "14:30 ~ 14:45","14:45 ~ 15:00",
        "15:00 ~ 15:15", "15:15 ~ 15:30", "15:30 ~ 15:45","15:45 ~ 16:00",
        "16:00 ~ 16:15", "16:15 ~ 16:30", "16:30 ~ 16:45","16:45 ~ 17:00",
        "17:00 ~ 17:15", "17:15 ~ 17:30", "17:30 ~ 17:45","17:45 ~ 18:00",
        "18:00 ~ 18:15", "18:15 ~ 18:30", "18:30 ~ 18:45","18:45 ~ 19:00",
        "19:00 ~ 19:15", "19:15 ~ 19:30", "19:30 ~ 19:45","19:45 ~ 20:00",
        };
    private JPanel[] tableCell = new JPanel[11 * 4 * 8];

    public void setChosenClass(ArrayList<CoverPanel> chosenClass) {
        this.chosenClass = chosenClass;
    }

    public ArrayList<CoverPanel> getChosenClass() {
        return chosenClass;
    }

    public String[] getWeekday() {
        return weekday;
    }

    public String[] getWeektime() {
        return weektime;
    }

    public JPanel[] getTableCell() {
        return tableCell;
    }

    public void change() {
        setChanged();
        notifyObservers();
    }

    public void resetChosenClass() {
        this.chosenClass = new ArrayList<>();
    }
}
