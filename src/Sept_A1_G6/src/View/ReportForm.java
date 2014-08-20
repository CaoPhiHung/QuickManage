package View;

import Controller.*;
import Model.*;
import java.util.*;
import javax.swing.*;

public class ReportForm extends JFrame implements Observer {

    private static ReportForm unique;
    private ReportFormController reportCon = new ReportFormController();
    private JScrollPane scrollview = new JScrollPane();
    private JPanel overAll;
    private JEditorPane ep;
    private JButton print;
    private JButton cancel;
    private ResourceBundle resources;
    //Dynamic variables
    private String monthlyReport = "Monthly Report : ";
    private String mon = "";
    private String managerName = "Manager Name";
    private String reportTime = "Report Time";
    private String paid = "Paid";
    private String unpaid = "Unpaid";
    private String studentId = "Student ID";
    private String studentName = "Student Name";
    private String classA = "Class";
    private String paidType = "Paid Type";
    private String paidDate = "Paid Date";
    private String unPaidDate = "Unpaid Date";
    private String paidNote = "Paid Note";
    private String amountVnd = "Amount (VND)";
    private String totalA = "Total";
    private String managerSing = "Manager Singature";
    private String date = "Date";

    //Singleton
    public static ReportForm getInstance() {
        if (unique == null) {
            unique = new ReportForm();
        }
        return unique;
    }

    public void initialize(String month, String year, ArrayList<Student> student) {
        overAll = new JPanel();
        ep = new JEditorPane();
        overAll.setLayout(null);
        ep.setLayout(null);
        ep.setEditable(false);
        ep.setContentType("text/html");
        showReport(month, year, student);
        scrollview.setViewportView(ep);

        //
        print = new JButton("Print");
        cancel = new JButton("Cancel");

        //set bound
        scrollview.setBounds(0, 0, 480, 550);
        ep.setBounds(0, 0, 480, 550);
        print.setBounds(140, 553, 80, 25);
        cancel.setBounds(240, 553, 80, 25);

        //add stuff
        overAll.add(print);
        overAll.add(cancel);
        overAll.add(scrollview);
        add(overAll);

        print.addActionListener(reportCon);
        cancel.addActionListener(reportCon);
        this.addWindowListener(reportCon);

        setTitle("Report Form");
        if (LanguageController.getInstance().isClicked()) {
            try {
                resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
                this.print.setText(resources.getString("printInvoice"));
                this.cancel.setText(resources.getString("Cancel"));
                this.setTitle(resources.getString("ReportForm"));
            } catch (MissingResourceException mre) {
                System.err.println("MyData.properties not found");
            }
        }

        //JFrame setting
        setSize(485, 610);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @Override
    public void update(Observable o, Object o1) {
    }

    public void showReport(String month, String year, ArrayList<Student> student) {
        this.mon = month;
        if (LanguageController.getInstance().isClicked()) {
            try {
                resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
                this.monthlyReport = (resources.getString("MonthlyReport"));
                this.mon = (resources.getString(month));
                this.managerName = (resources.getString("ManagerName"));
                this.reportTime = (resources.getString("reportTime"));
                this.paid = (resources.getString("paid"));
                this.unpaid = (resources.getString("unpaid"));

                this.studentId = (resources.getString("StudentID"));
                this.studentName = (resources.getString("StudentName"));
                this.classA = (resources.getString("Class"));
                this.paidType = (resources.getString("PaidType"));
                this.paidDate = (resources.getString("PaidDate"));
                this.unPaidDate = (resources.getString("UnPaidDate"));
                this.paidNote = (resources.getString("PaidNote"));
                this.amountVnd = (resources.getString("AmountVND"));
                this.totalA = (resources.getString("Total"));
                this.managerSing = (resources.getString("managerSing"));
                this.date = (resources.getString("date"));


            } catch (MissingResourceException mre) {
                System.err.println("MyData.properties not found");
            }
        }

        String submonth = month.substring(0, 3);
        Data data = new Data();

        String fullhtml =
                "<html><head></head><body><center><h1>" + monthlyReport + mon + " " + year + "</h1></center><br><br>"
                + managerName + " : " + Data.getCurrentUser().getFirstName() + " " + Data.getCurrentUser().getLastName()
                + "<br><br>"
                + reportTime + " : " + submonth + ", " + year
                + "<br><br>"
                + "<table border='1'>"
                + "<tr><td><b>" + paid + "</b></td></tr>"
                + "<tr><td>" + studentId + "</td><td>" + studentName + "</td><td>" + classA + "</td><td>" + paidType + "</td><td>" + paidDate + "</td><td>" + paidNote + "</td><td>" + amountVnd + "</td></tr>";

        String allClass = "";
        int totalpay = 0;
        String totalpayamount = "";

        for (int i = 0; i < student.size(); i++) {
            allClass = "";

            for (int k = 0; k < student.get(i).getClasses().size(); k++) {
                String toAddClass = student.get(i).getClasses().get(k).getClassCode() + " " + student.get(i).getClasses().get(k).getClassName() + "\n";
                allClass = allClass + toAddClass;
            }
            for (int j = 0; j < student.get(i).getInvoices().size(); j++) {
                String[] splitDate = student.get(i).getInvoices().get(j).getPaidDate().split(" ");
                if (student.get(i).getInvoices().get(j).getPayment().equals("paid") && splitDate[0].equals(submonth) && splitDate[2].equals(year)) {
                    String paidrow = "<tr><td>" + student.get(i).getID()
                            + "</td><td>" + student.get(i).getFirstName() + " " + student.get(i).getLastName()
                            + "</td><td>" + allClass + "</td><td>" + student.get(i).getInvoices().get(j).getPaidMethod()
                            + "</td><td>" + student.get(i).getInvoices().get(j).getPaidDate() + "</td><td>" + student.get(i).getInvoices().get(j).getPaidNote()
                            + "</td><td>" + student.get(i).getInvoices().get(j).getTotalTuitionFee() + "</td></tr>";
                    fullhtml = fullhtml + paidrow;
                    String totalFee = data.convertFee(student.get(i).getInvoices().get(j).getTotalTuitionFee());
                    totalpay = totalpay + Integer.parseInt(totalFee);
                }
            }
        }
        totalpayamount = data.commaFee(totalpay + "");

        String total = "<tr><td></td><td></td><td></td><td></td><td></td><td>" + totalA + "</td><td>" + totalpayamount + "</td></tr>";
        fullhtml = fullhtml + total;

        totalpay = 0;
        totalpayamount = "";

        String middlehtml =
                "</table>"
                + "<br><br><br><br><br><br><br><br>"
                + "<table border='1'>"
                + "<tr><td><b>" + unpaid + "</b></td></tr>"
                + "<tr><td>" + studentId + "</td><td>" + studentName + "</td><td>" + classA + "</td><td>" + paidType + "</td><td>" + unPaidDate + "</td><td>" + paidNote + "</td><td>" + amountVnd + "</td></tr>";

        fullhtml = fullhtml + middlehtml;

        for (int i = 0; i < student.size(); i++) {
            allClass = "";

            for (int k = 0; k < student.get(i).getClasses().size(); k++) {
                String toAddClass = student.get(i).getClasses().get(k).getClassCode() + " " + student.get(i).getClasses().get(k).getClassName() + "\n";
                allClass = allClass + toAddClass;
            }
            for (int j = 0; j < student.get(i).getInvoices().size(); j++) {
                String[] splitDate = student.get(i).getInvoices().get(j).getPaidDate().split(" ");
                if (student.get(i).getInvoices().get(j).getPayment().equals("unpaid") && splitDate[0].equals(submonth) && splitDate[2].equals(year)) {
                    String paidrow = "<tr><td>" + student.get(i).getID()
                            + "</td><td>" + student.get(i).getFirstName() + " " + student.get(i).getLastName()
                            + "</td><td>" + allClass + "</td><td>" + student.get(i).getInvoices().get(j).getPaidMethod()
                            + "</td><td>" + student.get(i).getInvoices().get(j).getPaidDate() + "</td><td>" + student.get(i).getInvoices().get(j).getPaidNote()
                            + "</td><td>" + student.get(i).getInvoices().get(j).getTotalTuitionFee() + "</td></tr>";
                    fullhtml = fullhtml + paidrow;
                    String totalFee = data.convertFee(student.get(i).getInvoices().get(j).getTotalTuitionFee());
                    totalpay = totalpay + Integer.parseInt(totalFee);
                }
            }
        }
        totalpayamount = data.commaFee(totalpay + "");

        String totalunpaid = "<tr><td></td><td></td><td></td><td></td><td></td><td>" + totalA + "</td><td>" + totalpayamount + "</td></tr>";
        fullhtml = fullhtml + totalunpaid;

        String bothtml =
                "</table>"
                + "<br><br><br>"
                + managerSing
                + "<br><br><br>"
                + date
                + "</body>"
                + "</html>";

        fullhtml = fullhtml + bothtml;

        this.ep.setText(fullhtml);



    }

    public JEditorPane getEp() {
        return ep;
    }

    public void setEp(JEditorPane ep) {
        this.ep = ep;
    }

    public static void setUnique(ReportForm unique) {
        ReportForm.unique = unique;
    }
}
