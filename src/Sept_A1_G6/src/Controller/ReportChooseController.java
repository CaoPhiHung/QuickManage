package Controller;

import Model.*;
import View.*;
import java.awt.event.*;
import javax.swing.*;

public class ReportChooseController implements ActionListener, WindowListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() instanceof JButton) {
            JButton but = (JButton) ae.getSource();

            if (but.getText().equalsIgnoreCase("Report") || but.getText().equalsIgnoreCase("Báo Cáo")) {
                ReportChooseForm.setUnique(null);
                ReportChooseForm rcf = ReportChooseForm.getInstance();
                rcf.initialize();
            } else if (but.getText().equalsIgnoreCase("Choose") || but.getText().equalsIgnoreCase("Chọn")) {
                String month = (String) ReportChooseForm.getInstance().getMonthCombo().getSelectedItem();
                String year = (String) ReportChooseForm.getInstance().getYearCombo().getSelectedItem();
                ReportForm.setUnique(null);
                ReportForm rf = ReportForm.getInstance();
                rf.initialize(month, year, Data.studentList);
                ReportChooseForm report = ReportChooseForm.getInstance();
                report.dispose();
            } else if (but.getText().equalsIgnoreCase("Cancel") || but.getText().equalsIgnoreCase("Hủy")) {
                ReportChooseForm report = ReportChooseForm.getInstance();
                report.dispose();
                ControlPanelForm.getInstance().setEnabled(true);
                ControlPanelForm.getInstance().setVisible(true);
            }
        }

        if (ae.getSource() instanceof JMenuItem) {
            JMenuItem me = (JMenuItem) ae.getSource();
            if (me.getText().equalsIgnoreCase("Report") || me.getText().equalsIgnoreCase("Báo Cáo")) {
                ReportChooseForm.setUnique(null);
                ReportChooseForm rcf = ReportChooseForm.getInstance();
                rcf.initialize();
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
