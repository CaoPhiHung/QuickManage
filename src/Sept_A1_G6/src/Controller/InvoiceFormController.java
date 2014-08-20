package Controller;

import Custom.*;
import Model.*;
import View.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Observable;
import javax.swing.*;
import javax.swing.event.*;

public class InvoiceFormController extends Observable implements ActionListener, WindowListener, ListSelectionListener {

    public static final int CHOOSE_CLASS_OPTION = 1;
    public static final int SHOW_DETAIL_OPTION = 2;
    public static final int SHOW_PAID = 3;
    public static final int SHOW_UNPAID = 4;
    private String currentClassClick;
    private String currentEnrollClick;
    private String paidDate = "";
    private String paidType = "";
    private String paidNote = "";
    private String payment = "";
    private int alreadygenerated = 0;
    private int allowToPay = 0;

    @Override
    public void actionPerformed(ActionEvent ae) {

        Data data = new Data();

        if (ae.getSource() instanceof JButton) {
            JButton but = null;
            SuperButton supbut = null;
            if (ae.getSource() instanceof SuperButton) {
                supbut = (SuperButton) ae.getSource();
            } else {
                but = (JButton) ae.getSource();
            }

            //If Button equals Invoice
            if (supbut != null && supbut.getFrontText().getText().equals("Invoice") || supbut != null && supbut.getFrontText().getText().equals("Hóa Đơn")) {
                InvoiceForm.setUnique(null);
                InvoiceForm in = InvoiceForm.getInstance();
                in.initialize();
                in.getStudentIdR().setText(Data.getCurrentStudentEnroll().getID());
                in.getStudentNameR().setText(Data.getCurrentStudentEnroll().getFirstName() + Data.getCurrentStudentEnroll().getLastName());

                in.getInvoiceIdR().setText(Data.autoInvoiceGenerateId());
                ControlPanelForm.getInstance().setEnabled(false);

                //If Button equals Done
            }
            if (but != null && but.getText().equals("Save") || but != null && but.getText().equals("Lưu")) {

//                alreadygenerated = 0;
                if (InvoiceForm.getInstance().getAllInvoice().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "There are no classes to generate an invoice");
                    return;
                }

//                for (int i = 0; i < Data.getCurrentStudentEnroll().getInvoices().size(); i++) {
//                    if (Data.getCurrentStudentEnroll().getInvoices().get(i).getInvoiceID().equals(InvoiceForm.getInstance().getInvoiceIdR().getText())) {
//                        JOptionPane.showMessageDialog(null, "Already generated invoice for today");
//                        alreadygenerated = 1;
//                    }
//                }

                DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");

                if (InvoiceForm.getInstance().getPaid().isSelected()) {
                    if (InvoiceForm.getInstance().getPaidDateR().getCalendar() != null) {

                        allowToPay = 1;
                        paidDate = dateFormat.format(InvoiceForm.getInstance().getPaidDateR().getDate()).toString();
                        paidType = (String) InvoiceForm.getInstance().getPaidMethodR().getSelectedItem();
                        paidNote = (String) InvoiceForm.getInstance().getPaidNoteR().getText();
                        payment = "paid";


                        GenerateForm.getInstance().setGUI(Data.getCurrentStudentEnroll().getID(), (Data.getCurrentStudentEnroll().getFirstName() + Data.getCurrentStudentEnroll().getLastName()),
                                Data.getCurrentStudentEnroll().getAddress(), Data.getCurrentStudentEnroll().getEmail(), InvoiceForm.getInstance().getInvoiceIdR().getText(), paidDate, Data.getCurrentStudentEnroll().getClasses(),
                                InvoiceForm.getInstance().getTotalTuitionFeeR().getText(), paidType);

                    } else {
                        JOptionPane.showMessageDialog(null, "Please pick a Paid date");
                    }
                } else {

                    if (InvoiceForm.getInstance().getPaidDateR().getCalendar() != null) {
                        allowToPay = 1;
                        paidDate = dateFormat.format(InvoiceForm.getInstance().getPaidDateR().getDate()).toString();
                        paidType = "";
                        paidNote = "";
                        payment = "unpaid";

                        GenerateForm.getInstance().setGUI(Data.getCurrentStudentEnroll().getID(), (Data.getCurrentStudentEnroll().getFirstName() + Data.getCurrentStudentEnroll().getLastName()),
                                Data.getCurrentStudentEnroll().getAddress(), Data.getCurrentStudentEnroll().getEmail(), InvoiceForm.getInstance().getInvoiceIdR().getText(), paidDate, Data.getCurrentStudentEnroll().getClasses(),
                                InvoiceForm.getInstance().getTotalTuitionFeeR().getText(), paidType);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please pick a Unpaid date");
                    }
                }
//
                if (allowToPay == 1) {

                    Invoice tempInv = new Invoice(InvoiceForm.getInstance().getInvoiceIdR().getText(), Data.getCurrentStudentEnroll().getClasses(), InvoiceForm.getInstance().getTotalTuitionFeeR().getText(), paidDate, paidType, paidNote, payment);

                    tempInv.setInvoiceID(InvoiceForm.getInstance().getInvoiceIdR().getText());

                    tempInv.setEp(GenerateForm.getInstance().getEp());
//                
                    Data.getCurrentStudentEnroll().getInvoices().add(tempInv);
                    data.saveStudentData(0);
                    data.saveInvoiceData(1, tempInv);


                    InvoiceForm invoice = InvoiceForm.getInstance();
                    invoice.dispose();
                    ControlPanelForm.getInstance().setEnabled(true);
                    ControlPanelForm.getInstance().setVisible(true);
                    allowToPay = 0;
                }


                //If Button equals Generate
            } else if (but != null && but.getText().equalsIgnoreCase("Print Review") || but != null && but.getText().equalsIgnoreCase("In Mẫu")) {
                DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");

                if (InvoiceForm.getInstance().getPaid().isSelected()) {
                    if (InvoiceForm.getInstance().getPaidDateR().getCalendar() != null) {

                        paidDate = dateFormat.format(InvoiceForm.getInstance().getPaidDateR().getDate()).toString();
                        paidType = (String) InvoiceForm.getInstance().getPaidMethodR().getSelectedItem();

                        InvoiceForm.getInstance().setEnabled(false);
                        GenerateForm.setUnique(null);
                        GenerateForm rf = GenerateForm.getInstance();
                        rf.setGUI(Data.getCurrentStudentEnroll().getID(), (Data.getCurrentStudentEnroll().getFirstName() + Data.getCurrentStudentEnroll().getLastName()),
                                Data.getCurrentStudentEnroll().getAddress(), Data.getCurrentStudentEnroll().getEmail(), InvoiceForm.getInstance().getInvoiceIdR().getText(), paidDate, Data.getCurrentStudentEnroll().getClasses(),
                                InvoiceForm.getInstance().getTotalTuitionFeeR().getText(), paidType);
                        rf.initialize();
                        rf.getGenerateCon().setCurrentView("invoice");
                    } else {
                        JOptionPane.showMessageDialog(null, "Please pick a paidDate");
                    }
                } else {
                    paidDate = dateFormat.format(InvoiceForm.getInstance().getPaidDateR().getDate()).toString();
                    paidType = "";

                    InvoiceForm.getInstance().setEnabled(false);
                    GenerateForm.setUnique(null);
                    GenerateForm rf = GenerateForm.getInstance();
                    rf.setGUI(Data.getCurrentStudentEnroll().getID(), (Data.getCurrentStudentEnroll().getFirstName() + Data.getCurrentStudentEnroll().getLastName()),
                            Data.getCurrentStudentEnroll().getAddress(), Data.getCurrentStudentEnroll().getEmail(), InvoiceForm.getInstance().getInvoiceIdR().getText(), paidDate, Data.getCurrentStudentEnroll().getClasses(),
                            InvoiceForm.getInstance().getTotalTuitionFeeR().getText(), paidType);
                    rf.initialize();
                    rf.getGenerateCon().setCurrentView("invoice");
                }

                //If Button equals History
            } else if (but != null && but.getText().equalsIgnoreCase("History") || but != null && but.getText().equalsIgnoreCase("Lịch Sử")) {
                HistoryView.setUnique(null);
                HistoryView hisVi = HistoryView.getInstance();
                hisVi.initialize(Data.getCurrentStudentEnroll().getInvoices());
                InvoiceForm.getInstance().setEnabled(false);

                //If Button equals cancel
            } else if (but != null && but.getText().equalsIgnoreCase("Cancel") || but != null && but.getText().equalsIgnoreCase("Hủy")) {
                InvoiceForm invoice = InvoiceForm.getInstance();
                invoice.dispose();
                ControlPanelForm.getInstance().setEnabled(true);
                ControlPanelForm.getInstance().setVisible(true);
            }
        } else if (ae.getSource() instanceof JRadioButton) {
            if ("Paid".equalsIgnoreCase(ae.getActionCommand())) {
                InvoiceForm.getInstance().getPaidDate().setText("Paid Date:");
                setModify(SHOW_PAID);

            } else if ("Unpaid".equalsIgnoreCase(ae.getActionCommand())) {
                InvoiceForm.getInstance().getPaidDate().setText("Unpaid Date:");
                InvoiceForm.getInstance().getPaidNoteR().setText("");
                setModify(SHOW_UNPAID);

            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList li = (JList) e.getSource();
        String item = (String) li.getSelectedValue();

        if (e.getValueIsAdjusting() == true && item != null && li.getName().equals("classlist")) {
            this.currentClassClick = item;
            setModify(SHOW_DETAIL_OPTION);

        }
//        } else if (e.getValueIsAdjusting() == true && item != null && li.getName().equals("invoicelist")) {
//            this.currentEnrollClick = item;
//        }
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

    public void setModify() {
        setChanged();
        notifyObservers();
    }

    public void setModify(int value) {
        setChanged();
        notifyObservers(value);
    }

    public String getCurrentClassClick() {
        return currentClassClick;
    }

    public void setCurrentClassClick(String currentClassClick) {
        this.currentClassClick = currentClassClick;
    }

    public String getCurrentEnrollClick() {
        return currentEnrollClick;
    }

    public void setCurrentEnrollClick(String currentEnrollClick) {
        this.currentEnrollClick = currentEnrollClick;
    }
}
