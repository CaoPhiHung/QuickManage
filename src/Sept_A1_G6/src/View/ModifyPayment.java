/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.HistoryController;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Thinh
 */
public class ModifyPayment extends JDialog {

    private static ModifyPayment unique;
    private JLabel message = new JLabel("Please modify payment date, method and note");
    private JDateChooser chosenDate = new JDateChooser();
    private JTextArea payNote = new JTextArea(3, 3);
    private JComboBox paymethod = new JComboBox();
    private JButton modify = new JButton("Modify");
    private JButton cancel = new JButton("Cancel");
    private HistoryController hisCon = new HistoryController();

    public static ModifyPayment getInstance() {
        if (unique == null) {
            unique = new ModifyPayment();
        }
        return unique;
    }

    public void initialize() {
        setLayout(null);

        paymethod.addItem("Cash");
        paymethod.addItem("PayPal");
        paymethod.addItem("Credit Card");
        paymethod.addItem("Master Card");

        add(message);
        add(chosenDate);
        add(paymethod);
        add(payNote);
        add(modify);
        add(cancel);

        message.setBounds(45, 0, 350, 50);
        chosenDate.setBounds(30, 50, 150, 25);
        paymethod.setBounds(210, 50, 100, 25);
        payNote.setBounds(30, 100, 280, 105);
        modify.setBounds(50, 210, 100, 30);
        cancel.setBounds(170, 210, 100, 30);

        modify.addActionListener(hisCon);
        cancel.addActionListener(hisCon);

        //Dialog settings
        setTitle("Change payment");
        setSize(350, 290);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    }

    public static void setUnique(ModifyPayment unique) {
        ModifyPayment.unique = unique;
    }

    public JDateChooser getChosenDate() {
        return chosenDate;
    }

    public void setChosenDate(JDateChooser chosenDate) {
        this.chosenDate = chosenDate;
    }

    public JTextArea getPayNote() {
        return payNote;
    }

    public void setPayNote(JTextArea payNote) {
        this.payNote = payNote;
    }

    public JComboBox getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(JComboBox paymethod) {
        this.paymethod = paymethod;
    }
}
