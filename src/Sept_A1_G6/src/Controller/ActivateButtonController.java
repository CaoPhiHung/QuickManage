package Controller;

import Model.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.JOptionPane;

public class ActivateButtonController implements ActionListener {

    private ArrayList<String> ids = new ArrayList<String>();

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!ids.isEmpty()) {
            Data data = new Data();
            data.activateButton(ids);
        } else {
            JOptionPane.showMessageDialog(null, "Please choose some contents to active/deactive");
        }
    }

    public void removeOldIDs() {
        ids = new ArrayList<String>();
    }

    public void printOut() {
        for (int i = 0; i < ids.size(); i++) {
            System.out.println(ids.get(i) + "    ");
        }
        System.out.println();
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
}
