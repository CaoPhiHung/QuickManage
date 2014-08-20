package View;

import Controller.*;
import Model.*;
import com.toedter.calendar.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;

public class InvoiceForm extends JFrame implements Observer {

    private static InvoiceForm unique;
    private InvoiceFormController invoiceCon = new InvoiceFormController();
    private JLabel classesEnrolled;
    private JLabel classCode;
    private JLabel className;
    private JLabel startDate;
    private JLabel endDate;
    private JLabel tuitionFee;
    private JLabel totalTuitionFee;
    private JLabel paidDate;
    private JLabel paidMethod;
    private JLabel paidNote;
    private JLabel invoiceId;
    private JLabel studentId;
    private JLabel studentName;
    //
    private JList classesList = new JList();
    private ArrayList<String> allInvoice;
    private JScrollPane scrollClass = new JScrollPane();
    //
    private JTextField invoiceIdR;
    private JTextField studentIdR;
    private JTextField studentNameR;
    private JTextField classCodeR;
    private JTextField classNameR;
    private JTextField startDateR;
    private JTextField endDateR;
    private JTextField tuitionFeeR;
    private JTextField totalTuitionFeeR;
    private JComboBox paidMethodR;
    private JTextField paidNoteR;
    private JSeparator separator1 = new JSeparator(SwingConstants.VERTICAL);
    private JRadioButton paid;
    private JRadioButton unpaid;
    private JButton saveInvoice;
    private JButton cancel;
    private JButton generateInvoice;
    private JButton historyInvoice;
    JDateChooser paidDateR = new JDateChooser();
    private ButtonGroup buttonGroup;
    private ResourceBundle resources;

    //Singleton
    public static InvoiceForm getInstance() {
        if (unique == null) {
            unique = new InvoiceForm();
        }
        return unique;
    }

    public void initialize() {
        setLayout(null);
        invoiceCon.addObserver(this);
        Data data = new Data();

        allInvoice = new ArrayList<>();

        int totalFee = 0;
        for (int i = 0; i < Data.getCurrentStudentEnroll().getClasses().size(); i++) {
            allInvoice.add(Data.getCurrentStudentEnroll().getClasses().get(i).getClassCode());
            totalFee = totalFee + Integer.parseInt(data.convertFee(Data.getCurrentStudentEnroll().getClasses().get(i).getTuitionFee()));
        }

        //Class list settings
        classesList.setName("classlist");
        classesList.setListData(allInvoice.toArray());
        scrollClass.setViewportView(classesList);

        paidDateR = new JDateChooser();

        saveInvoice = new JButton("Save");
        cancel = new JButton("Cancel");
        generateInvoice = new JButton("Print Review");
        historyInvoice = new JButton("History");

        classesEnrolled = new JLabel("Classes");
        invoiceId = new JLabel("Invoice ID:");
        studentId = new JLabel("Student ID:");
        studentName = new JLabel("Student Name:");
        classCode = new JLabel("Class Code:");
        className = new JLabel("Class Name:");
        startDate = new JLabel("Start Date:");
        endDate = new JLabel("End Date:");
        tuitionFee = new JLabel("Tuition Fee:");
        totalTuitionFee = new JLabel("Total Fee:");
        paidDate = new JLabel("Paid Date:");
        paidMethod = new JLabel("Paid Method:");
        paidNote = new JLabel("Paid Note:");
        paid = new JRadioButton("Paid");
        unpaid = new JRadioButton("Unpaid");

        classCodeR = new JTextField("");
        classNameR = new JTextField("");
        startDateR = new JTextField("");
        endDateR = new JTextField("");
        tuitionFeeR = new JTextField("");
        totalTuitionFeeR = new JTextField(data.commaFee(totalFee + ""));

        paidMethodR = new JComboBox<>();
        paidNoteR = new JTextField();
        invoiceIdR = new JTextField("");
        studentIdR = new JTextField("");
        studentNameR = new JTextField("");

        paidMethodR.addItem("Cash");
        paidMethodR.addItem("PayPal");
        paidMethodR.addItem("Credit Card");
        paidMethodR.addItem("Master Card");

        //add label
        add(classesEnrolled);
        add(classCode);
        add(className);
        add(startDate);
        add(endDate);
        add(tuitionFee);
        add(totalTuitionFee);
        add(scrollClass);
        add(separator1);
        add(paidDate);
        add(paidMethod);
        add(paidNote);
        add(historyInvoice);
        add(invoiceId);
        add(studentId);
        add(studentName);

        //add text field
        add(classCodeR);
        add(classNameR);
        add(startDateR);
        add(endDateR);
        add(tuitionFeeR);
        add(totalTuitionFeeR);
        add(paidMethodR);
        add(paidDateR);
        add(paidNoteR);
        add(paid);
        add(unpaid);
        add(saveInvoice);
        add(cancel);
        add(generateInvoice);
        add(invoiceIdR);
        add(studentIdR);
        add(studentNameR);

        //set bound for label
        classesEnrolled.setBounds(70, 10, 100, 25);
        invoiceId.setBounds(230, 10, 80, 25);
        studentId.setBounds(230, 40, 80, 25);
        studentName.setBounds(230, 70, 90, 25);
        classCode.setBounds(230, 100, 100, 25);
        className.setBounds(230, 130, 100, 25);
        startDate.setBounds(230, 160, 100, 25);
        endDate.setBounds(230, 190, 100, 25);
        tuitionFee.setBounds(230, 220, 100, 25);
        totalTuitionFee.setBounds(230, 250, 100, 25);
        scrollClass.setBounds(7, 40, 200, 115);

        paidDate.setBounds(10, 190, 90, 25);
        paidMethod.setBounds(10, 220, 100, 25);
        paidNote.setBounds(10, 250, 100, 25);

        separator1.setBounds(220, 10, 10, 270);
        //set bound for text field
        invoiceIdR.setBounds(315, 10, 125, 25);
        studentIdR.setBounds(315, 40, 125, 25);
        studentNameR.setBounds(315, 70, 125, 25);
        classCodeR.setBounds(315, 100, 125, 25);
        classNameR.setBounds(315, 130, 125, 25);
        startDateR.setBounds(315, 160, 125, 25);
        endDateR.setBounds(315, 190, 125, 25);
        tuitionFeeR.setBounds(315, 220, 125, 25);
        totalTuitionFeeR.setBounds(315, 250, 125, 25);
        paidMethodR.setBounds(85, 220, 100, 25);
        paidNoteR.setBounds(85, 250, 125, 25);
        paid.setBounds(40, 160, 80, 25);
        unpaid.setBounds(105, 160, 100, 25);
        historyInvoice.setBounds(10, 290, 100, 25);
        generateInvoice.setBounds(120, 290, 100, 25);
        saveInvoice.setBounds(230, 290, 100, 25);
        cancel.setBounds(340, 290, 100, 25);

        //set invi
        classCodeR.setOpaque(false);
        classNameR.setOpaque(false);
        startDateR.setOpaque(false);
        endDateR.setOpaque(false);
        tuitionFeeR.setOpaque(false);
        totalTuitionFeeR.setOpaque(false);
        invoiceIdR.setOpaque(false);
        studentIdR.setOpaque(false);
        studentNameR.setOpaque(false);
        classCodeR.setEditable(false);
        classNameR.setEditable(false);
        startDateR.setEditable(false);
        endDateR.setEditable(false);
        tuitionFeeR.setEditable(false);
        totalTuitionFeeR.setEditable(false);
        invoiceIdR.setEditable(false);
        studentIdR.setEditable(false);
        studentNameR.setEditable(false);

        paidDateR.setBounds(85, 190, 125, 25);

        //set listener for radio button
        paid.setActionCommand("Paid");
        unpaid.setActionCommand("Unpaid");
        paid.setSelected(true);


        buttonGroup = new ButtonGroup();
        buttonGroup.add(paid);
        buttonGroup.add(unpaid);


        //set listener
        generateInvoice.addActionListener(invoiceCon);
        historyInvoice.addActionListener(invoiceCon);
        saveInvoice.addActionListener(invoiceCon);
        cancel.addActionListener(invoiceCon);
//        moveForward.addActionListener(invoiceCon);
//        moveBackward.addActionListener(invoiceCon);

        classesList.addListSelectionListener(invoiceCon);
//        invoiceList.addListSelectionListener(invoiceCon);

        paid.addActionListener(invoiceCon);
//        paid.addChangeListener(radioListener);
//        paid.addItemListener(radioListener);
        unpaid.addActionListener(invoiceCon);
//        unpaid.addChangeListener(radioListener);
//        unpaid.addItemListener(radioListener);

        this.addWindowListener(invoiceCon);

        setTitle("Invoice Form");
        if (LanguageController.getInstance().isClicked()) {
            try {
                resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
                //Add Manager Form
                this.saveInvoice.setText(resources.getString("saveInvoice"));
                this.cancel.setText(resources.getString("Cancel"));
                this.generateInvoice.setText(resources.getString("generateInvoice"));
                this.historyInvoice.setText(resources.getString("historyInvoice"));

                this.paid.setText(resources.getString("paid"));
                this.unpaid.setText(resources.getString("unpaid"));
                this.paidDate.setText(resources.getString("paidDate"));
                this.paidMethod.setText(resources.getString("paidMethod"));
                this.paidNote.setText(resources.getString("paidNote"));

                this.invoiceId.setText(resources.getString("invoiceId"));
                this.studentId.setText(resources.getString("studentId"));
                this.studentName.setText(resources.getString("studentName"));
                this.classCode.setText(resources.getString("classCode"));
                this.className.setText(resources.getString("className"));
                this.startDate.setText(resources.getString("startDate"));
                this.endDate.setText(resources.getString("endDate"));
                this.tuitionFee.setText(resources.getString("tuitionFee"));
                this.totalTuitionFee.setText(resources.getString("totalTuitionFee"));
                paidMethodR.removeAllItems();
                this.paidMethodR.addItem(resources.getString("Cash"));
                this.paidMethodR.addItem(resources.getString("PayPal"));
                this.paidMethodR.addItem(resources.getString("CreditCard"));
                this.paidMethodR.addItem(resources.getString("MasterCard"));
                
                this.classesEnrolled.setText(resources.getString("classesEnrolled"));

                setTitle(resources.getString("InvoiceForm"));
            } catch (MissingResourceException mre) {
                System.err.println("MyData.properties not found");
            }
        }
        //JFrame setting
        setSize(455, 350);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @Override
    public void update(Observable o, Object o1) {
        if (o1 instanceof Integer) {
            InvoiceFormController contemp = (InvoiceFormController) o;
            if (o1.equals(InvoiceFormController.SHOW_DETAIL_OPTION)) {
                for (Classes cla : Data.classList) {
                    if (cla.getClassCode().equals(contemp.getCurrentClassClick())) {
                        classCodeR.setText(cla.getClassCode());
                        classNameR.setText(cla.getClassName());

                        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");

                        startDateR.setText(dateFormat.format(cla.getStartDate()).toString());
                        endDateR.setText(dateFormat.format(cla.getEndDate()).toString());
                        tuitionFeeR.setText(cla.getTuitionFee());

                    }
                }
            } else if (o1.equals(InvoiceFormController.SHOW_PAID)) {
                paidMethodR.setEnabled(true);
                paidNoteR.setEnabled(true);
            } else if (o1.equals(InvoiceFormController.SHOW_UNPAID)) {
                paidMethodR.setEnabled(false);
                paidNoteR.setEnabled(false);
            }
        }
    }

    public JLabel getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(JLabel paidDate) {
        this.paidDate = paidDate;
    }

    public JTextField getClassCodeR() {
        return classCodeR;
    }

    public void setClassCodeR(JTextField classCodeR) {
        this.classCodeR = classCodeR;
    }

    public JTextField getClassNameR() {
        return classNameR;
    }

    public void setClassNameR(JTextField classNameR) {
        this.classNameR = classNameR;
    }

    public JTextField getStartDateR() {
        return startDateR;
    }

    public void setStartDateR(JTextField startDateR) {
        this.startDateR = startDateR;
    }

    public JTextField getEndDateR() {
        return endDateR;
    }

    public void setEndDateR(JTextField endDateR) {
        this.endDateR = endDateR;
    }

    public JTextField getTuitionFeeR() {
        return tuitionFeeR;
    }

    public void setTuitionFeeR(JTextField tuitionFeeR) {
        this.tuitionFeeR = tuitionFeeR;
    }

    public JTextField getTotalTuitionFeeR() {
        return totalTuitionFeeR;
    }

    public void setTotalTuitionFeeR(JTextField totalTuitionFeeR) {
        this.totalTuitionFeeR = totalTuitionFeeR;
    }

    public JComboBox getPaidMethodR() {
        return paidMethodR;
    }

    public void setPaidMethodR(JComboBox paidMethodR) {
        this.paidMethodR = paidMethodR;
    }

    public JTextField getPaidNoteR() {
        return paidNoteR;
    }

    public void setPaidNoteR(JTextField paidNoteR) {
        this.paidNoteR = paidNoteR;
    }

    public JDateChooser getPaidDateR() {
        return paidDateR;
    }

    public void setPaidDateR(JDateChooser paidDateR) {
        this.paidDateR = paidDateR;
    }

    public static void setUnique(InvoiceForm unique) {
        InvoiceForm.unique = unique;
    }

    public JTextField getInvoiceIdR() {
        return invoiceIdR;
    }

    public void setInvoiceIdR(JTextField invoiceIdR) {
        this.invoiceIdR = invoiceIdR;
    }

    public JTextField getStudentIdR() {
        return studentIdR;
    }

    public void setStudentIdR(JTextField studentIdR) {
        this.studentIdR = studentIdR;
    }

    public JTextField getStudentNameR() {
        return studentNameR;
    }

    public void setStudentNameR(JTextField studentNameR) {
        this.studentNameR = studentNameR;
    }

    public ArrayList<String> getAllInvoice() {
        return allInvoice;
    }

    public void setAllInvoice(ArrayList<String> allInvoice) {
        this.allInvoice = allInvoice;
    }

    public JRadioButton getPaid() {
        return paid;
    }

    public void setPaid(JRadioButton paid) {
        this.paid = paid;
    }

    public JRadioButton getUnpaid() {
        return unpaid;
    }

    public void setUnpaid(JRadioButton unpaid) {
        this.unpaid = unpaid;
    }
}
