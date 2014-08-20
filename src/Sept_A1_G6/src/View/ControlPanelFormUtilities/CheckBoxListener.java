package View.ControlPanelFormUtilities;

import Controller.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class CheckBoxListener implements ItemListener {

    private String id;
    private String name;
    private String type;
    private DeleteButtonController deleteCon;
    private ActivateButtonController activateCon;

    public CheckBoxListener(String id, DeleteButtonController deleteCon, ActivateButtonController activateCon) {
        this.id = id;
        this.deleteCon = deleteCon;
        this.activateCon = activateCon;
    }

    public CheckBoxListener(String name, DeleteButtonController deleteCon, String type) {
        this.name = name;
        this.deleteCon = deleteCon;
        this.type = type;
    }

    public void itemStateChanged(ItemEvent e) {
        //If checked, return the id that this listener is holding
        //the problem is what if many checkboxes are checked at the same time
        //And if unchecked, how can it remove the id that it has passed to the delete button
        if (this.id != null) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                deleteCon.setDeleteData(this.id);
                activateCon.setDeleteData(this.id);
            }
            if (e.getStateChange() == ItemEvent.DESELECTED) {
                deleteCon.removeDeleteData(this.id);
                activateCon.removeDeleteData(this.id);
            }
        } else {
            if (this.type.equals("Class Type")) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    deleteCon.setDeleteDataClassType(this.name);
                }
                if (e.getStateChange() == ItemEvent.DESELECTED) {
                    deleteCon.removeDeleteDataClassType(this.name);
                }
            } else {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    deleteCon.setDeleteRoom(this.name);
                }
                if (e.getStateChange() == ItemEvent.DESELECTED) {
                    deleteCon.removeDeleteRoom(this.name);
                }
            }

        }

    }
}
