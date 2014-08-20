package View;

import Controller.LanguageController;
import Custom.*;
import Custom.MyFont.*;
import Model.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class DashBoardView extends JDialog {

    //Properties
    private static DashBoardView unique;
    private static final int SCENE_WIDTH = 950;
    private static final int SCENE_HEIGHT = 650;
    private SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy"); //format it as per your requirement
    private JLabel wrapAll;
    private JLabel wrapContent;
    private JLabel dashboardTitle;
    //Properties - All LabelBoard
    private LabelBoard wrapClass;
    private LabelBoard wrapStudent;
    private LabelBoard wrapTeacher;
    private LabelBoard wrapRatio;
    private LabelBoard wrapPaidCurrent;
    private LabelBoard wrapPaidLast;
    private LabelBoard wrapUnpaidCurrent;
    private LabelBoard wrapUnpaidLast;
    private SuperButton closeButton;
    private String numClass = "Number of Class";
    private String numTeacher = "Number of Teacher";
    private String numStudent = "Number of Student";
    private String ratio = "Capacity Utilization Ratio";
    private String totalPaidThis = "Total Paid this month";
    private String totalUnpaidThis = "Total Unpaid this month";
    private String totalPaidLast = "Total Paid last month";
    private String totalUnpaidLast = "Total Unpaid last month";
    private String dashboard = "Dashboard on";
    private String close = "Close";
    private ResourceBundle resources;

    /*
     * Unique instance
     */
    public static DashBoardView getInstance() {
        if (unique == null) {
            unique = new DashBoardView();
        }
        return unique;
    }

    //Initialize the dialog
    public void initialize() {

        //Settings components
        setLayout(null);
        Calendar currentDate = Calendar.getInstance();
        //set language
        setTitle("DashBoard");
        if (LanguageController.getInstance().isClicked()) {
            try {
                resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
                this.numClass = (resources.getString("numClass"));
                this.numStudent = (resources.getString("numStudent"));
                this.numTeacher = (resources.getString("numTeacher"));
                this.ratio = (resources.getString("ratio"));
                this.dashboard = (resources.getString("dashboard"));
                this.close = (resources.getString("Close"));

                this.totalPaidThis = (resources.getString("totalPaidThis"));
                this.totalUnpaidThis = (resources.getString("totalUnpaidThis"));
                this.totalPaidLast = (resources.getString("totalPaidLast"));
                this.totalUnpaidLast = (resources.getString("totalUnpaidLast"));

                this.setTitle(resources.getString("Dashboard"));

            } catch (MissingResourceException mre) {
                System.err.println("MyData.properties not found");
            }
        }
        //DashBoard Title
        SimpleDateFormat formatterSecond = new SimpleDateFormat("MMM dd, yyyy");
        String displayDate = formatterSecond.format(currentDate.getTime());
        dashboardTitle = new JLabel(dashboard + " " + displayDate, JLabel.CENTER);
        dashboardTitle.setFont(InternalFont.getFont(InternalFont.SEGOEUI, Font.PLAIN, 30));
        dashboardTitle.setForeground(MyColor.BLUE_SKY_P1);

        //Wrap Class and components
        wrapClass = new LabelBoard();
        wrapClass.initialize(MyColor.LIGHT_BLUE, MyColor.LIGHT_YELLOW,
                MyColor.LIGHT_YELLOW, MyColor.RED_COLOR, numClass, Data.classList.size() + "");

        //Wrap Teacher and components
        wrapTeacher = new LabelBoard();
        wrapTeacher.initialize(MyColor.LIGHT_PURPLE, MyColor.WHITE_COLOR,
                MyColor.LIGHT_YELLOW, MyColor.RED_COLOR, numTeacher, Data.teacherList.size() + "");

        //Wrap Student and components
        wrapStudent = new LabelBoard();
        wrapStudent.initialize(MyColor.MIX_LIGHT_ORANGE_YELLOW, MyColor.LIGHT_ORANGE,
                MyColor.LIGHT_YELLOW, MyColor.RED_COLOR, numStudent, Data.studentList.size() + "");

        //Wrap Ratio and omponents
        DecimalFormat df = new DecimalFormat("#.##");
        wrapRatio = new LabelBoard();
        wrapRatio.initialize(MyColor.DARK_GREEN_P1, MyColor.BLACK_COLOR,
                MyColor.LIGHT_YELLOW, MyColor.RED_COLOR, ratio, df.format(Data.ratio * 100) + " %");

        //Wrap Current Month Paid and components
        wrapPaidCurrent = new LabelBoard();
        wrapPaidCurrent.initialize(MyColor.MIX_LIGHT_ORANGE_YELLOW, MyColor.LIGHT_ORANGE,
                MyColor.LIGHT_YELLOW, MyColor.RED_COLOR, totalPaidThis, currentTotalPaid());

        //Wrap Current Month Unpaid and components
        wrapUnpaidCurrent = new LabelBoard();
        wrapUnpaidCurrent.initialize(MyColor.MIX_LIGHT_ORANGE_YELLOW, MyColor.LIGHT_ORANGE,
                MyColor.LIGHT_YELLOW, MyColor.RED_COLOR, totalUnpaidThis, currentTotalUnpaid());

        //Wrap Last Month Paid and components
        wrapPaidLast = new LabelBoard();
        wrapPaidLast.initialize(MyColor.MIX_LIGHT_ORANGE_YELLOW, MyColor.LIGHT_ORANGE,
                MyColor.LIGHT_YELLOW, MyColor.RED_COLOR, totalPaidLast, lastTotalPaid());

        //Wrap Last Month Unpaid and components
        wrapUnpaidLast = new LabelBoard();
        wrapUnpaidLast.initialize(MyColor.MIX_LIGHT_ORANGE_YELLOW, MyColor.LIGHT_ORANGE,
                MyColor.LIGHT_YELLOW, MyColor.RED_COLOR, totalUnpaidLast, lastTotalUnpaid());

        //Wrap Content
        wrapAll = new JLabel();
        wrapAll.setLayout(null);
        wrapAll.setOpaque(true);
        wrapAll.setBackground(MyColor.LIGHT_GREEN_M2);

        wrapContent = new JLabel();
        wrapContent.setLayout(null);
        wrapContent.setOpaque(true);
        wrapContent.setBackground(MyColor.LIGHT_BROWN);

        wrapContent.add(wrapClass);
        wrapContent.add(wrapTeacher);
        wrapContent.add(wrapStudent);
        wrapContent.add(wrapRatio);
        wrapContent.add(wrapPaidCurrent);
        wrapContent.add(wrapUnpaidCurrent);
        wrapContent.add(wrapUnpaidLast);
        wrapContent.add(wrapPaidLast);

        wrapClass.setBounds(100, 0, 200, 100);
        wrapTeacher.setBounds(314, 0, 200, 100);
        wrapStudent.setBounds(525, 0, 200, 100);
        wrapRatio.setBounds(314, 110, 200, 100);
        wrapPaidCurrent.setBounds(160, 230, 200, 100);
        wrapUnpaidCurrent.setBounds(160, 350, 200, 100);
        wrapPaidLast.setBounds(460, 230, 200, 100);
        wrapUnpaidLast.setBounds(460, 350, 200, 100);


        // Create Button and add to Frame
        closeButton = new SuperButton(SuperButton.CLOSE_BUTTON, close, SuperButton.WHITE_TO_BLUE, SuperButton.BLACK_COLOR);

        wrapAll.add(wrapContent);
        wrapContent.setBounds(50, 70, 850, 460);

        add(dashboardTitle);
        add(closeButton);
        add(wrapAll);


        wrapAll.setBounds(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
        dashboardTitle.setBounds(250, 5, 450, 50);
        closeButton.setBounds(455, 550, 40, 70);

        //Set listener
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DashBoardView.getInstance().dispose();
            }
        });

        //Dialog's settings appearance
        setSize(SCENE_WIDTH, SCENE_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private class LabelBoard extends JLabel {

        JLabel boardTitle;
        JLabel boardLabel;

        //initialize
        public void initialize(Color backgroundTitle, Color foregroundTitle,
                Color backgroundLabel, Color foregroundLabel, String title,
                String number) {
            //Wrap Class and components

            this.setLayout(null);
            this.setOpaque(true);

            boardTitle = new JLabel(title, JLabel.CENTER);
            boardTitle.setFont(InternalFont.getFont(InternalFont.SEGOEUI, Font.PLAIN, 14));
            boardTitle.setForeground(foregroundTitle);
            boardTitle.setLayout(null);
            boardTitle.setOpaque(true);
            boardTitle.setBackground(backgroundTitle);

            boardLabel = new JLabel(number, JLabel.CENTER);
            boardLabel.setFont(InternalFont.getFont(InternalFont.SEGOEUI, Font.BOLD, 23));
            boardLabel.setForeground(foregroundLabel);
            boardLabel.setLayout(null);
            boardLabel.setOpaque(true);
            boardLabel.setBackground(backgroundLabel);

            this.add(boardTitle);
            this.add(boardLabel);
            boardTitle.setBounds(0, 0, 200, 50);
            boardLabel.setBounds(0, 50, 200, 50);
        }
    }

    private String currentTotalPaid() {
        String date = "";
        String submonth;
        String year;
        String toReturn;

        //MMM dd, yyyy
        Calendar currentDate = Calendar.getInstance(); //Get the current date
        date = formatter.format(currentDate.getTime());
        submonth = date.split(" ")[0].substring(0, 3);
        year = date.split(" ")[2];
        toReturn = getTotalPaid(submonth, year);
        return toReturn + " VND";
    }

    private String currentTotalUnpaid() {
        String date = "";
        String submonth;
        String year;
        String toReturn;

        Calendar currentDate = Calendar.getInstance(); //Get the current date
        date = formatter.format(currentDate.getTime());
        submonth = date.split(" ")[0].substring(0, 3);
        year = date.split(" ")[2];
        toReturn = getTotalUnpaid(submonth, year);
        return toReturn + " VND";
    }

    private String lastTotalPaid() {
        String date = "";
        String submonth;
        String year;
        String toReturn;

        Calendar currentDate = Calendar.getInstance(); //Get the current date
        currentDate.add(Calendar.MONTH, -1);
        date = formatter.format(currentDate.getTime());
        submonth = date.split(" ")[0].substring(0, 3);
        year = date.split(" ")[2];
        toReturn = getTotalPaid(submonth, year);
        return toReturn + " VND";
    }

    private String lastTotalUnpaid() {
        String date = "";
        String submonth;
        String year;
        String toReturn;

        Calendar currentDate = Calendar.getInstance(); //Get the current date
        currentDate.add(Calendar.MONTH, -1);
        date = formatter.format(currentDate.getTime());
        submonth = date.split(" ")[0].substring(0, 3);
        year = date.split(" ")[2];
        toReturn = getTotalUnpaid(submonth, year);
        return toReturn + " VND";
    }

    //Get the String for totalpaid given a month in 3 chars
    private String getTotalPaid(String submonth, String year) {
        Data data = new Data();

        int totalpay = 0;
        String totalpayamount = "";

        for (int i = 0; i < Data.studentList.size(); i++) {

            for (int j = 0; j < Data.studentList.get(i).getInvoices().size(); j++) {
                String[] splitDate = Data.studentList.get(i).getInvoices().get(j).getPaidDate().split(" ");
                if (Data.studentList.get(i).getInvoices().get(j).getPayment().equals("paid") && splitDate[0].equals(submonth) && splitDate[2].equals(year)) {
                    String totalFee = data.convertFee(Data.studentList.get(i).getInvoices().get(j).getTotalTuitionFee());
                    totalpay = totalpay + Integer.parseInt(totalFee);
                }
            }

        }
        totalpayamount = data.commaFee(totalpay + "");
        return totalpayamount;
    }

    //Get the String for totalunpaid given a month in 3 chars
    private String getTotalUnpaid(String submonth, String year) {
        Data data = new Data();

        int totalpay = 0;
        String totalpayamount = "";

        for (int i = 0; i < Data.studentList.size(); i++) {

            for (int j = 0; j < Data.studentList.get(i).getInvoices().size(); j++) {
                String[] splitDate = Data.studentList.get(i).getInvoices().get(j).getPaidDate().split(" ");
                if (Data.studentList.get(i).getInvoices().get(j).getPayment().equals("unpaid") && splitDate[0].equals(submonth) && splitDate[2].equals(year)) {
                    String totalFee = data.convertFee(Data.studentList.get(i).getInvoices().get(j).getTotalTuitionFee());
                    totalpay = totalpay + Integer.parseInt(totalFee);
                }
            }

        }
        totalpayamount = data.commaFee(totalpay + "");
        return totalpayamount;
    }

    public static void setUnique(DashBoardView unique) {
        DashBoardView.unique = unique;
    }
}
