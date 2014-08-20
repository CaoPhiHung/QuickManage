package View;

import Controller.*;
import Model.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class HistoryView extends JFrame implements Observer {

    //Properties
    private static HistoryView unique;
    private HistoryController hisCon = new HistoryController();
    private JScrollPane scrollview = new JScrollPane();
    private JPanel overAll = new JPanel();
    private JPanel containerTop = new JPanel();
    private JPanel containerBot = new JPanel();
    private ArrayList<Invoice> listInvoice;
    private ArrayList<JPanel> rowPanel = new ArrayList<>();
    private ArrayList<JButton> rowBut = new ArrayList<>();
    private JButton closeBut = new JButton("Close");
    private JLabel invoiceID = new JLabel("Invoice ID");
    private JLabel printReview = new JLabel("Print review");
    private JLabel paymentStatus = new JLabel("Change payment status");
    private JLabel delete = new JLabel("Delete");
    JButton printBut;
    private ResourceBundle resources;
    //Get instance

    public static HistoryView getInstance() {
        if (unique == null) {
            unique = new HistoryView();
        }
        return unique;
    }

    //Initialize
    public void initialize(ArrayList<Invoice> tempinv) {
        //Settings
        setLayout(null);
        listInvoice = tempinv;
        hisCon.addObserver(this);
        containerBot.setLayout(new FlowLayout((FlowLayout.LEFT)));

        if (Data.getCurrentUser() instanceof Manager) {
            containerTop.setLayout(new GridLayout(1, 4));

        } else if (Data.getCurrentUser() instanceof Staff) {
            containerTop.setLayout(new GridLayout(1, 3));
        }

        containerTop.setBorder(BorderFactory.createLineBorder(Color.black));
        containerBot.setBorder(BorderFactory.createLineBorder(Color.black));

        scrollview.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        //Add to Container Top
        JPanel invID = new JPanel();
        invID.add(invoiceID);
        invID.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
        containerTop.add(invID);

        JPanel printRev = new JPanel();
        printRev.add(printReview);
        printRev.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
        containerTop.add(printRev);

        JPanel payPanel = new JPanel();
        payPanel.add(paymentStatus);
        payPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
        containerTop.add(payPanel);

        if (Data.getCurrentUser() instanceof Manager) {
            JPanel deleteRow = new JPanel();
            deleteRow.add(delete);
            deleteRow.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
            containerTop.add(deleteRow);
        }

        //Add to Container Bot
        for (int i = 0; i < listInvoice.size(); i++) {
            JPanel eachRow = new JPanel();
            if (Data.getCurrentUser() instanceof Manager) {
                eachRow.setLayout(new GridLayout(1, 4));

            } else if (Data.getCurrentUser() instanceof Staff) {
                eachRow.setLayout(new GridLayout(1, 3));
            }

            eachRow.setName(listInvoice.get(i).getInvoiceID());

            JPanel infoRow = new JPanel();
            infoRow.add(new JLabel(listInvoice.get(i).getInvoiceID()));
            infoRow.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
            eachRow.add(infoRow);

            JPanel info2Row = new JPanel();
            printBut = new JButton("Print");
            printBut.addActionListener(hisCon);
            info2Row.add(printBut);
            info2Row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
            printBut.setName(listInvoice.get(i).getInvoiceID());
            eachRow.add(info2Row);

            JPanel info3Row = new JPanel();
            String payStatus = listInvoice.get(i).getPayment();
            if (payStatus.equals("paid")) {
                payStatus = "unpaid";
            } else {
                payStatus = "paid";
            }

            JButton paymentBut = new JButton(payStatus);
            if (Data.getCurrentUser() instanceof Manager) {
            } else if (Data.getCurrentUser() instanceof Staff) {
                if (paymentBut.getText().equals("unpaid")) {
                    paymentBut.setEnabled(false);
                }
            }

            paymentBut.addActionListener(hisCon);
            info3Row.add(paymentBut);
            info3Row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
            paymentBut.setName(listInvoice.get(i).getInvoiceID());
            eachRow.add(info3Row);
            rowBut.add(paymentBut);

            if (Data.getCurrentUser() instanceof Manager) {
                JPanel info4Row = new JPanel();
                JButton deleteBut = new JButton("Delete");
                deleteBut.addActionListener(hisCon);
                info4Row.add(deleteBut);
                info4Row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
                deleteBut.setName(listInvoice.get(i).getInvoiceID());
                eachRow.add(info4Row);
            }

            eachRow.setPreferredSize(new Dimension(660, 50));
//            eachRow.setBorder(BorderFactory.createLineBorder(Color.red));
            containerBot.add(eachRow);
            rowPanel.add(eachRow);
        }

        scrollview.setViewportView(containerBot);
        containerBot.setPreferredSize(new Dimension(690, listInvoice.size() * 57));

        overAll.setLayout(null);
        overAll.add(containerTop);
        overAll.add(scrollview);

        add(overAll);
        add(closeBut);

        overAll.setBounds(0, 0, 690, 520);
        containerTop.setBounds(0, 0, 690, 50);
        containerBot.setBounds(0, 0, 100, 50);

        scrollview.setBounds(0, 50, 690, 500);
        closeBut.setBounds(300, 520, 100, 30);

        closeBut.addActionListener(hisCon);
        this.addWindowListener(hisCon);

        setTitle("History Invoice");

        if (LanguageController.getInstance().isClicked()) {
            try {
                resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
                //Add Manager Form
                this.invoiceID.setText(resources.getString("InvoiceId"));
                this.printReview.setText(resources.getString("generateInvoice"));
                this.paymentStatus.setText(resources.getString("paymentStatus"));
                this.delete.setText(resources.getString("Delete"));
                this.printBut.setText(resources.getString("printHistory"));
                this.closeBut.setText(resources.getString("Close"));
                setTitle(resources.getString("HistoryInvoice"));
            } catch (MissingResourceException mre) {
                System.err.println("MyData.properties not found");
            }
        }
        //JFrame Settings 
        setSize(700, 580);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            containerBot.removeAll();
            ArrayList<JPanel> tempPa = new ArrayList<>();
            for (int i = 0; i < rowPanel.size(); i++) {
                if (rowPanel.get(i).getName().equals(arg)) {
                    tempPa.add(rowPanel.get(i));
                }
            }
            for (JPanel tpa : tempPa) {
                rowPanel.remove(tpa);
            }

            for (JPanel jp : rowPanel) {
                containerBot.add(jp);
            }
        }
        this.validate();
        this.repaint();
    }

    public static HistoryView getUnique() {
        return unique;
    }

    public static void setUnique(HistoryView unique) {
        HistoryView.unique = unique;
    }

    public ArrayList<JButton> getRowBut() {
        return rowBut;
    }

    public void setRowBut(ArrayList<JButton> rowBut) {
        this.rowBut = rowBut;
    }
}
