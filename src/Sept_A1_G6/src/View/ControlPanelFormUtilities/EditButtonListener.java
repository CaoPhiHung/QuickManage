package View.ControlPanelFormUtilities;

import Model.Data;
import View.ControlPanelForm;
import java.awt.event.*;
import java.text.*;
import java.util.logging.*;

public class EditButtonListener implements ActionListener {

    private String id;

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Data data = new Data();
        try {
            ControlPanelForm.getInstance().setEnabled(false);
            data.Edit(id);
        } catch (ParseException ex) {
            Logger.getLogger(EditButtonListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
