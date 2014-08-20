package Controller;

import Model.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class CopyClassController implements ActionListener {

    private String id;

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println(id);
        if (id != null) {
            Data data = new Data();
            data.copyClasses(id);
        } else {
            JOptionPane.showMessageDialog(null, "Please choose some classes to copy");
        }
    }

    public void removeOldIDs() {
        this.id = new String();
    }

    public void setId(String id) {
        this.id = id;
    }
}
