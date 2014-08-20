package Controller;

import Model.Data;
import Model.DataValidation;
import View.ClassTypeForm;
import View.ControlPanelForm;
import View.RoomForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;

public class RoomFormController implements ActionListener, WindowListener {
    @Override
    public void actionPerformed(ActionEvent ae) {
        RoomForm sf = RoomForm.getInstance();
        JButton but = (JButton) ae.getSource();
        DataValidation dv = new DataValidation();

        if (but.getText().equalsIgnoreCase("Add")) {
            // validate user inputs
            if (sf.getOption().equalsIgnoreCase("add")) {
                dv.validateRoomForm(RoomForm.getInstance());
            } else if (sf.getOption().equalsIgnoreCase("edit")) {
                dv.validateRoomForm(RoomForm.getInstance());
            }

        } else if (but.getText().equalsIgnoreCase("Cancel")) {
            RoomForm roomForm = RoomForm.getInstance();
            roomForm.dispose();
            ControlPanelForm.getInstance().setEnabled(true);
            ControlPanelForm.getInstance().setVisible(true);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        ControlPanelForm.getInstance().setEnabled(true);
        ControlPanelForm.getInstance().setVisible(true);
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
