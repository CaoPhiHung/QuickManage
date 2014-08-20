package Controller;

import Model.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.JOptionPane;

public class DeleteButtonController implements ActionListener {

    private ArrayList<String> ids = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> numbers = new ArrayList<>();

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (!ids.isEmpty()) {
            Data data = new Data();
            data.delete(ids);
        }else if(!names.isEmpty()) {
            Data data = new Data();
            data.deleteClassType(names);
        } else if(!numbers.isEmpty()) {
            Data data = new Data();
            data.deleteRoom(numbers);
        } else {
            JOptionPane.showMessageDialog(null, "Please choose some contents to delete");
        }
    }

    public void removeOldIDs() {
        ids = new ArrayList<>();
        names = new ArrayList<>();
        numbers = new ArrayList<>();
    }

    public void setDeleteData(String id) {
        ids.add(id);
    }

    public void removeDeleteData(String id) {
        for (int i = 0; i < ids.size(); i++) {
            if (this.ids.get(i) == id) {
                ids.remove(i);
            }
        }
    }

    public void setDeleteDataClassType(String name) {
        names.add(name);
    }

    public void removeDeleteDataClassType(String name) {
        for (int i = 0; i < names.size(); i++) {
            if (this.names.get(i) == name) {
                names.remove(i);
            }
        }
    }
    
    public void setDeleteRoom(String number) {
        numbers.add(number);
    }

    public void removeDeleteRoom(String number) {
        for (int i = 0; i < numbers.size(); i++) {
            if (this.numbers.get(i).equals(number)) {
                numbers.remove(i);
            }
        }
    }
}
