/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.ControlPanelFormUtilities;

import View.ControlPanelForm;
import View.PayslipGenerateView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Kelly
 */
public class GenerateListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
        ControlPanelForm.getInstance().setEnabled(false);
        PayslipGenerateView.setUnique(null);
        PayslipGenerateView.getInstance().initialize();
    }
    
}
