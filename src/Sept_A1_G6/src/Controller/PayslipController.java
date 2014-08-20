package Controller;

import Model.Data;
import Model.DataValidation;
import View.ControlPanelForm;
import View.PayslipGenerateView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class PayslipController implements ActionListener, WindowListener  {

    @Override
    public void actionPerformed(ActionEvent ae) {
        PayslipGenerateView sf = PayslipGenerateView.getInstance();
        JButton but = (JButton) ae.getSource();
        DataValidation dv = new DataValidation();

        if (but.getText().equalsIgnoreCase("Generate")) {
            JOptionPane.showMessageDialog(null, "Haven't implemented yet");

        } else if (but.getText().equalsIgnoreCase("Cancel")) {
            sf.dispose();
            ControlPanelForm.getInstance().setEnabled(true);
            ControlPanelForm.getInstance().setVisible(true);
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
