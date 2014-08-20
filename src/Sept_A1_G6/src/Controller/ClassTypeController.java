package Controller;

import Model.*;
import View.*;
import java.awt.event.*;
import javax.swing.*;

public class ClassTypeController implements ActionListener, WindowListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
        ClassTypeForm sf = ClassTypeForm.getInstance();
        JButton but = (JButton) ae.getSource();
        DataValidation dv = new DataValidation();

        if (but.getText().equalsIgnoreCase("Add")) {
            Data data = new Data();
            // validate user inputs
            if (ClassTypeForm.getInstance().getOption().equalsIgnoreCase("add")) {
                dv.validateClassTypeForm(ClassTypeForm.getInstance());
            } else if (ClassTypeForm.getInstance().getOption().equalsIgnoreCase("edit")) {
                dv.validateClassTypeForm(ClassTypeForm.getInstance());
            }

        } else if (but.getText().equalsIgnoreCase("Cancel")) {
            ClassTypeForm classType = ClassTypeForm.getInstance();
            classType.dispose();
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
