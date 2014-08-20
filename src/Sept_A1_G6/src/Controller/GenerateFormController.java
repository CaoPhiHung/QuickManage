package Controller;

import View.*;
import java.awt.event.*;
import java.awt.print.*;
import java.util.logging.*;
import javax.swing.*;

public class GenerateFormController implements ActionListener, WindowListener {

    private String currentView = "";

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton but = (JButton) ae.getSource();
        if (ae.getSource() instanceof JButton) {
            if (but.getText().equalsIgnoreCase("Print") || but.getText().equalsIgnoreCase("In")) {
                try {
                    GenerateForm.getInstance().getEp().print();
                    if (currentView.equals("invoice")) {
                        GenerateForm.getInstance().dispose();
                        GenerateForm.getInstance().setTotal("");
                        ControlPanelForm.getInstance().setEnabled(false);
                        ControlPanelForm.getInstance().setVisible(true);
                        InvoiceForm.getInstance().setEnabled(true);
                        InvoiceForm.getInstance().setVisible(true);
                    } else {
                        GenerateForm.getInstance().dispose();
                        HistoryView.getInstance().setEnabled(true);
                        HistoryView.getInstance().setVisible(true);
                    }
                } catch (PrinterException ex) {
                    Logger.getLogger(GenerateFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (but.getText().equalsIgnoreCase("Cancel") || but.getText().equalsIgnoreCase("Hủy")) {
                if (currentView.equals("invoice")) {
                    GenerateForm.getInstance().dispose();
                    GenerateForm.getInstance().setTotal("");
                    ControlPanelForm.getInstance().setEnabled(false);
                    ControlPanelForm.getInstance().setVisible(true);
                    InvoiceForm.getInstance().setEnabled(true);
                    InvoiceForm.getInstance().setVisible(true);
                } else {
                    GenerateForm.getInstance().dispose();
                    HistoryView.getInstance().setEnabled(true);
                    HistoryView.getInstance().setVisible(true);
                }

            }
        }
    }

    @Override
    public void windowOpened(WindowEvent we) {
    }

    @Override
    public void windowClosing(WindowEvent we) {
        ControlPanelForm.getInstance().setEnabled(false);
        ControlPanelForm.getInstance().setVisible(true);
        InvoiceForm.getInstance().setEnabled(true);
        InvoiceForm.getInstance().setVisible(true);
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

    public String getCurrentView() {
        return currentView;
    }

    public void setCurrentView(String currentView) {
        this.currentView = currentView;
    }
}
