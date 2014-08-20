package View;

import Controller.*;
import Model.*;
import java.util.ArrayList;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.*;

public class GenerateForm extends JFrame {

    private static GenerateForm unique;
    private JPanel overAll = new JPanel();
    private JEditorPane ep = ep = new JEditorPane();
    private JButton print = new JButton();
    private JButton cancel = new JButton();
    private String total = new String();
    private GenerateFormController generateCon = new GenerateFormController();
    private JScrollPane scrollview = new JScrollPane();
    private ResourceBundle resources;
    //Singleton

    public static GenerateForm getInstance() {
        if (unique == null) {
            unique = new GenerateForm();
        }
        return unique;
    }

    public void initialize() {

        //String studentID, String studentName, String address, String email, String invoiceID, String date, ArrayList<Classes> allClass, String totalFee, String paymethod
        overAll.setLayout(null);

//        setGUI(studentID, studentName, address, email, invoiceID, date, allClass, totalFee, paymethod);

        scrollview.setViewportView(ep);
        //
        print = new JButton("Print");
        cancel = new JButton("Cancel");

        //set bound
        ep.setBounds(0, 0, 500, 650);
        scrollview.setBounds(0, 0, 500, 650);
        print.setBounds(150, 580, 80, 25);
        cancel.setBounds(260, 580, 80, 25);

        //add stuff
        overAll.add(print);
        overAll.add(cancel);
        overAll.add(scrollview);
        add(overAll);


        print.addActionListener(generateCon);
        cancel.addActionListener(generateCon);
        this.addWindowListener(generateCon);
        setTitle("Review Invoice Form");
        if (LanguageController.getInstance().isClicked()) {
            try {
                resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
                //Add Manager Form
                this.print.setText(resources.getString("printInvoice"));
                this.cancel.setText(resources.getString("Cancel"));
                setTitle(resources.getString("ReviewInvoiceForm"));
            } catch (MissingResourceException mre) {
                System.err.println("MyData.properties not found");
            }
        }
        //JFrame setting
        setSize(500, 650);

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public void setGUI(String studentID, String studentName, String address, String email, String invoiceID, String date, ArrayList<Classes> allClass, String totalFee, String paymethod) {
        setEditorContent();
        String fullhtml = "<html><head></head><body><center><h1> Student Invoice </h1></center><br><br>"
                + "Student ID: " + studentID + "				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Invoice ID: " + invoiceID
                + "<br><br>"
                + "Student Name: " + studentName + "			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; " + Data.getCurrentDate("MMM dd, yyyy")
                + "<br><br>"
                + "Address: " + address
                + "<br><br>"
                + "Email: " + email
                + "<br><br>"
                + "Paid Date: " + date
                + "<br><br>"
                + "Paid type: " + paymethod
                + "<br><br>"
                + "<table border='1'>"
                + "<tr><td>Class Code</td><td>Class Name</td><td>Amount (VND)</td></tr>";

        String fullTable = "";
        for (Classes cla : allClass) {
            String tempTable = "<tr><td>" + cla.getClassCode() + "</td><td>" + cla.getClassName()
                    + "</td><td>" + cla.getTuitionFee() + "</td></tr>";
            fullhtml = fullhtml + tempTable;
        }

        fullhtml = fullhtml + "<tr><td></td><td>Total</td><td>" + totalFee + "</td></tr>";

        String fullBottom = "</table><br><br><br><br>"
                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Student Signature &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Staff Signature"
                + "<br><br><br><br><br><br>Date:</body></html>";

        fullhtml = fullhtml + fullBottom;
        ep.setText(fullhtml);
    }

    public void setEditorContent() {
        ep.setLayout(null);
        ep.setEditable(false);
        ep.setContentType("text/html");
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public JEditorPane getEp() {
        return ep;
    }

    public void setEp(JEditorPane ep) {
        this.ep = ep;
    }

    public static GenerateForm getUnique() {
        return unique;
    }

    public static void setUnique(GenerateForm unique) {
        GenerateForm.unique = unique;
    }

    public JPanel getOverAll() {
        return overAll;
    }

    public void setOverAll(JPanel overAll) {
        this.overAll = overAll;
    }

    public JButton getPrint() {
        return print;
    }

    public void setPrint(JButton print) {
        this.print = print;
    }

    public JButton getCancel() {
        return cancel;
    }

    public void setCancel(JButton cancel) {
        this.cancel = cancel;
    }

    public GenerateFormController getGenerateCon() {
        return generateCon;
    }

    public void setGenerateCon(GenerateFormController generateCon) {
        this.generateCon = generateCon;
    }
}
