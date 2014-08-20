package Controller;

import View.*;
import java.awt.event.*;
import java.awt.print.*;
import java.util.logging.*;
import javax.swing.*;

public class ReportFormController implements ActionListener, WindowListener {

    @Override
    public void actionPerformed(ActionEvent ae) {



        JButton but = (JButton) ae.getSource();
        if (ae.getSource() instanceof JButton) {
            if (but.getText().equalsIgnoreCase("Print") || but.getText().equalsIgnoreCase("In")) {
                try {
                    ReportForm.getInstance().getEp().print();
                } catch (PrinterException ex) {
                    Logger.getLogger(ReportFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (but.getText().equalsIgnoreCase("Cancel") || but.getText().equalsIgnoreCase("Hủy")) {
                ReportForm report = ReportForm.getInstance();
                report.dispose();
                ControlPanelForm.getInstance().setEnabled(true);
                ControlPanelForm.getInstance().setVisible(true);
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent we) {
    }

    @Override
    public void windowClosing(WindowEvent we) {
        ControlPanelForm.getInstance().setEnabled(true);
        ControlPanelForm.getInstance().setVisible(true);
    }

    @Override
    public void windowClosed(WindowEvent we) {
    }

    @Override
    public void windowIconified(WindowEvent we) {
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
    }

    @Override
    public void windowActivated(WindowEvent we) {
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }
}
