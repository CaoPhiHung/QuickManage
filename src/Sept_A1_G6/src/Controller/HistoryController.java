package Controller;

import Model.*;
import View.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class HistoryController extends Observable implements ActionListener, WindowListener {

    private static boolean modified = false;
    private static JButton currentBut;

    @Override
    public void actionPerformed(ActionEvent e) {
        Data data = new Data();
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");

        if (e.getSource() instanceof JButton) {
            JButton eBut = (JButton) e.getSource();

            if (eBut.getText().equals("Close") || eBut.getText().equals("Đóng")) {
                HistoryView.getInstance().dispose();
                ControlPanelForm.getInstance().setEnabled(true);
                ControlPanelForm.getInstance().setVisible(true);
                InvoiceForm.getInstance().setEnabled(true);
                InvoiceForm.getInstance().setVisible(true);
            } else if (eBut.getText().equals("Delete") || eBut.getText().equals("Xóa")) {
                String id = eBut.getName();
                System.out.println("ID when pressing Delete " + id);

                //Delete inside current student
                for (Invoice inv : Data.getCurrentStudentEnroll().getInvoices()) {
                    if (inv.getInvoiceID().equals(id)) {
                        Data.getCurrentStudentEnroll().getInvoices().remove(inv);
                        break;
                    }
                }

                //Delete in invoiceList in Data
                for (Invoice inv : Data.invoiceList) {
                    if (inv.getInvoiceID().equals(id)) {
                        Data.invoiceList.remove(inv);
                        break;
                    }
                }

                //Save to files
                data.saveStudentData(0);
                data.saveInvoiceData(0, null);

                //Notify view with the id of student
                setModify(id);

            } else if (eBut.getText().equals("paid") || eBut.getText().equals("unpaid")) {
                String id = eBut.getName();
                for (int i = 0; i < HistoryView.getInstance().getRowBut().size(); i++) {
                    if (HistoryView.getInstance().getRowBut().get(i).getName().equals(id)) {
                        if (HistoryView.getInstance().getRowBut().get(i).getText().equals("unpaid")) {
                            int confirmOp = JOptionPane.showOptionDialog(null, "By changing from paid to unpaid. Payment information (such as paid date, paid method, paid note will be lost. Are you sure?", "Payment Confirmation",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
                            if (confirmOp == 0) {
                                HistoryView.getInstance().getRowBut().get(i).setText("paid");
                                for (int j = 0; j < Data.getCurrentStudentEnroll().getInvoices().size(); j++) {
                                    if (Data.getCurrentStudentEnroll().getInvoices().get(j).getInvoiceID().equals(id)) {
                                        if (Data.getCurrentStudentEnroll().getInvoices().get(i).getPayment().equals("paid")) {
                                            Data.getCurrentStudentEnroll().getInvoices().get(i).setPayment("unpaid");
                                            Data.getCurrentStudentEnroll().getInvoices().get(i).setPaidDate("");
                                            Data.getCurrentStudentEnroll().getInvoices().get(i).setPaidMethod("");
                                            Data.getCurrentStudentEnroll().getInvoices().get(i).setPaidNote("");
                                            Data.getCurrentStudentEnroll().getInvoices().get(i).resetEditor(Data.getCurrentStudentEnroll().getID(),
                                                    (Data.getCurrentStudentEnroll().getFirstName() + Data.getCurrentStudentEnroll().getLastName()),
                                                    Data.getCurrentStudentEnroll().getAddress(), Data.getCurrentStudentEnroll().getEmail(),
                                                    Data.getCurrentStudentEnroll().getInvoices().get(i).getInvoiceID(),
                                                    Data.getCurrentStudentEnroll().getInvoices().get(i).getPaidDate(), Data.getCurrentStudentEnroll().getClasses(),
                                                    Data.getCurrentStudentEnroll().getInvoices().get(i).getTotalTuitionFee(), Data.getCurrentStudentEnroll().getInvoices().get(i).getPaidMethod());
                                        }
                                    }
                                }
                                data.saveStudentData(0);
                                data.saveInvoiceData(0, null);
                            }
                        } else if (HistoryView.getInstance().getRowBut().get(i).getText().equals("paid")) {
                            ModifyPayment.setUnique(null);
                            ModifyPayment mopa = ModifyPayment.getInstance();
                            mopa.initialize();
                            JOptionPane.showMessageDialog(null, "By changing from unpaid to paid. Please fill in payment detail");
                            currentBut = HistoryView.getInstance().getRowBut().get(i);
                        }
                    }
                }


            } else if (eBut.getText().equals("Print") || eBut.getText().equals("In Mẫu")) {
                String id = eBut.getName();
                JEditorPane tempedit = null;
                for (int i = 0; i < Data.getCurrentStudentEnroll().getInvoices().size(); i++) {
                    if (Data.getCurrentStudentEnroll().getInvoices().get(i).getInvoiceID().equals(id)) {
                        tempedit = Data.getCurrentStudentEnroll().getInvoices().get(i).getEp();
                    }
                }

                GenerateForm.setUnique(null);
                GenerateForm rf = GenerateForm.getInstance();
                String fullhtml = tempedit.getText();
                rf.setEditorContent();
                rf.getEp().setText(fullhtml);
                rf.initialize();
                rf.getGenerateCon().setCurrentView("history");
                HistoryView.getInstance().setEnabled(false);
            } else if (eBut.getText().equals("Modify")) {

                if (ModifyPayment.getInstance().getChosenDate().getCalendar() != null) {
                    currentBut.setText("unpaid");
                    modified = true;
                    for (int i = 0; i < Data.getCurrentStudentEnroll().getInvoices().size(); i++) {
                        if (Data.getCurrentStudentEnroll().getInvoices().get(i).getInvoiceID().equals(currentBut.getName())) {
                            if (Data.getCurrentStudentEnroll().getInvoices().get(i).getPayment().equals("unpaid")) {
                                Data.getCurrentStudentEnroll().getInvoices().get(i).setPayment("paid");
                                String tempModifiedDate = dateFormat.format(ModifyPayment.getInstance().getChosenDate().getDate()).toString();
                                Data.getCurrentStudentEnroll().getInvoices().get(i).setPaidDate(tempModifiedDate);
                                Data.getCurrentStudentEnroll().getInvoices().get(i).setPaidMethod((String) ModifyPayment.getInstance().getPaymethod().getSelectedItem());
                                Data.getCurrentStudentEnroll().getInvoices().get(i).setPaidNote(ModifyPayment.getInstance().getPayNote().getText());
                                Data.getCurrentStudentEnroll().getInvoices().get(i).resetEditor(Data.getCurrentStudentEnroll().getID(),
                                        (Data.getCurrentStudentEnroll().getFirstName() + Data.getCurrentStudentEnroll().getLastName()),
                                        Data.getCurrentStudentEnroll().getAddress(), Data.getCurrentStudentEnroll().getEmail(),
                                        Data.getCurrentStudentEnroll().getInvoices().get(i).getInvoiceID(),
                                        Data.getCurrentStudentEnroll().getInvoices().get(i).getPaidDate(), Data.getCurrentStudentEnroll().getClasses(),
                                        Data.getCurrentStudentEnroll().getInvoices().get(i).getTotalTuitionFee(), Data.getCurrentStudentEnroll().getInvoices().get(i).getPaidMethod());

                                if (Data.getCurrentUser() instanceof Manager) {
                                } else if (Data.getCurrentUser() instanceof Staff) {
                                    currentBut.setEnabled(false);
                                }
                            }
                        }
                    }
                    data.saveStudentData(0);
                    data.saveInvoiceData(0, null);
                    ModifyPayment.getInstance().dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Please pick a paid date");
                }

            } else if (eBut.getText().equals("Cancel")) {
                modified = false;
                ModifyPayment.getInstance().dispose();
            }
        }
    }

    public void setModify(String id) {
        setChanged();
        notifyObservers(id);
    }

    @Override
    public void windowOpened(WindowEvent we) {
    }

    @Override
    public void windowClosing(WindowEvent we) {
        ControlPanelForm.getInstance().setEnabled(true);
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
}
