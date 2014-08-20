package Model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JEditorPane;

public class Invoice implements Serializable {

    private String invoiceID;
    private String idNoNumber;
    private String idNumber;
    private String totalTuitionFee;
    private ArrayList<Classes> classes = new ArrayList<>();
    private String currentDate;
    private String paidDate;
    private String paidMethod;
    private String paidNote;
    private String payment;
    private boolean isTop = true;
    private JEditorPane ep = new JEditorPane();

    public Invoice(String invoiceID,
            ArrayList<Classes> classes, String totalTuitionFee,
            String paidDate, String paidMethod, String paidNote, String payment) {

        
        this.classes = classes;
        this.totalTuitionFee = totalTuitionFee;
        this.paidDate = paidDate;
        this.paidMethod = paidMethod;
        this.paidNote = paidNote;
        this.payment = payment;
        this.invoiceID = invoiceID;
        
        String[] tempID = invoiceID.split("-");
        this.idNoNumber = tempID[0] + "-" +tempID[1];
        this.idNumber = tempID[2];
        
        this.currentDate = Data.getCurrentDate("MMM dd, yyyy");
    }

    public void resetEditor(String studentID, String studentName, String address, String email, String invoiceID, String date, ArrayList<Classes> allClass, String totalFee, String paymethod) {
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
                + "<br><br><br><br>Date:</body></html>";

        fullhtml = fullhtml + fullBottom;
        ep.setText(fullhtml);
    }

    public void setEditorContent() {
        ep.setLayout(null);
        ep.setEditable(false);
        ep.setContentType("text/html");
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public ArrayList<Classes> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Classes> classes) {
        this.classes = classes;
    }

    public String getTotalTuitionFee() {
        return totalTuitionFee;
    }

    public void setTotalTuitionFee(String totalTuitionFee) {
        this.totalTuitionFee = totalTuitionFee;
    }

    public String getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }

    public String getPaidMethod() {
        return paidMethod;
    }

    public void setPaidMethod(String paidMethod) {
        this.paidMethod = paidMethod;
    }

    public String getPaidNote() {
        return paidNote;
    }

    public void setPaidNote(String paidNote) {
        this.paidNote = paidNote;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public JEditorPane getEp() {
        return ep;
    }

    public void setEp(JEditorPane ep) {
        this.ep = ep;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getIdNoNumber() {
        return idNoNumber;
    }

    public void setIdNoNumber(String idNoNumber) {
        this.idNoNumber = idNoNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public boolean isIsTop() {
        return isTop;
    }

    public void setIsTop(boolean isTop) {
        this.isTop = isTop;
    }
}
