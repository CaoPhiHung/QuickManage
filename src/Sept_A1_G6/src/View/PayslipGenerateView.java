package View;

import Controller.PayslipController;
import Model.Data;
import TimetableUtilities.TimetableController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Date;
import javax.swing.*;

public class PayslipGenerateView extends JFrame {

    private static PayslipGenerateView unique;
    private JLabel teacherId = new JLabel("Teacher Id: ", JLabel.CENTER);
    private JLabel teacherName = new JLabel("Teacher Name: ", JLabel.CENTER);
    private JLabel classesName = new JLabel("Classes", JLabel.CENTER);
    private JLabel numberOfLessons = new JLabel("Number of lessons: ", JLabel.CENTER);
    private JLabel totalTeachingHours = new JLabel("Total teaching hours: ", JLabel.CENTER);
    private JLabel hourlyPayRate = new JLabel("Monthly pay rate: ", JLabel.CENTER);
    private JLabel moneyEarn = new JLabel("Money earn: ", JLabel.CENTER);
    private JLabel salary = new JLabel("Total salary for this month: ", JLabel.CENTER);
    private JLabel salaryR = new JLabel("",JLabel.CENTER);
    private JButton generate = new JButton("Generate");
    private JButton cancel = new JButton("Cancel");
    private PayslipController payslipCon = new PayslipController();
    private JPanel thetable = new JPanel();
    private JPanel toptable = new JPanel();
    private JScrollPane scrollview = new JScrollPane();

    public static PayslipGenerateView getInstance() {
        if (PayslipGenerateView.unique == null) {
            PayslipGenerateView.unique = new PayslipGenerateView();
        }
        return PayslipGenerateView.unique;
    }

    public static void setUnique(PayslipGenerateView unique) {
        PayslipGenerateView.unique = unique;
    }

    public void initialize() {
        setLayout(null);
        thetable.setLayout(new FlowLayout(FlowLayout.CENTER));
        thetable.setBorder(BorderFactory.createLineBorder(Color.blue));

        toptable.setLayout(new FlowLayout(FlowLayout.CENTER));
//        toptable.setBorder(BorderFactory.createLineBorder(Color.yellow));

        JPanel firstrow = new JPanel();
        firstrow.setLayout(new GridLayout(1, 5));
//        firstrow.setBorder(BorderFactory.createLineBorder(Color.red));

        classesName.setBorder(BorderFactory.createLineBorder(Color.black));
        numberOfLessons.setBorder(BorderFactory.createLineBorder(Color.black));
        totalTeachingHours.setBorder(BorderFactory.createLineBorder(Color.black));
        hourlyPayRate.setBorder(BorderFactory.createLineBorder(Color.black));
        moneyEarn.setBorder(BorderFactory.createLineBorder(Color.black));
        firstrow.add(classesName);
        firstrow.add(numberOfLessons);
        firstrow.add(totalTeachingHours);
        firstrow.add(hourlyPayRate);
        firstrow.add(moneyEarn);

        toptable.add(firstrow);

        double totalSalary = 0;
        //The table shows
        for (int i = 0; i < Data.getCurrentTeacherAssign().getClasses().size(); i++) {
            JPanel eachrow = new JPanel();
            eachrow.setLayout(new GridLayout(1, 5));
            eachrow.setPreferredSize(new Dimension(590, 50));
//            eachrow.setBorder(BorderFactory.createLineBorder(Color.PINK));

            //First column - Classes
            JLabel classNameA = new JLabel(Data.getCurrentTeacherAssign().getClasses().get(i).getClassName(), JLabel.CENTER);
            eachrow.add(classNameA);

            //Number of lesson
            int totalWeekDay = 0;
            for (int j = 0; j < Data.getCurrentTeacherAssign().getClasses().get(i).getDays().length; j++) {
                int currentWeekDay = 0;
                String currentD = Data.getCurrentTeacherAssign().getClasses().get(i).getDays()[j];
                Date before = Data.getCurrentTeacherAssign().getClasses().get(i).getStartDate();
                Date after = Data.getCurrentTeacherAssign().getClasses().get(i).getEndDate();
                currentWeekDay = Data.getWeekDay(before, after, currentD);
                totalWeekDay = totalWeekDay + currentWeekDay;

            }
            JLabel numberhour = new JLabel(totalWeekDay + "", JLabel.CENTER);
            eachrow.add(numberhour);

            //Total teaching hours
            double totalTeachingMinute = 0;
            double inHour = 0;
            int countInLesson = 0;
            for (int k = 0; k < totalWeekDay; k++) {
                if (countInLesson != totalWeekDay) {
                    for (int j = 0; j < Data.getCurrentTeacherAssign().getClasses().get(i).getFroms().length; j++) {
                        TimetableController timcon = new TimetableController();
                        double endtime = 0;
                        double starttime = 0;
                        double diff = 0;

                        String striTo = Data.getCurrentTeacherAssign().getClasses().get(i).getTos()[j];
                        String striFrom = Data.getCurrentTeacherAssign().getClasses().get(i).getFroms()[j];
                        endtime = timcon.getTime(striTo);
                        starttime = timcon.getTime(striFrom);
                        diff = endtime - starttime;
                        System.out.println("Different : " + diff);
                        if (diff == 45.0 || diff == 85.0) {
                            totalTeachingMinute = totalTeachingMinute + 45;
                            countInLesson++;
                        } else if (diff == 100.0) {
                            totalTeachingMinute = totalTeachingMinute + 60;
                            countInLesson++;
                        }
                    }
                }

            }
            inHour = totalTeachingMinute / 60;
            JLabel teachingHour = new JLabel(inHour + "", JLabel.CENTER);
            eachrow.add(teachingHour);

            //Hourly payrate
            double hourlyRate = 0;
            for(int j = 0; j < Data.getCurrentTeacherAssign().getSkillList().size(); j++){
                if(Data.getCurrentTeacherAssign().getSkillList().get(j).equals(Data.getCurrentTeacherAssign().getClasses().get(i).getClassType().getName())){
                    double tempRate = Double.parseDouble(Data.getCurrentTeacherAssign().getHourlyRate().get(j));
                    hourlyRate = hourlyRate + tempRate;
                }
            }
            JLabel hourlyRateLabel = new JLabel(hourlyRate + "", JLabel.CENTER);
            eachrow.add(hourlyRateLabel);
            
            //Money earn
            double totalMoney = totalWeekDay * hourlyRate;
            JLabel totalMoneyLabel = new JLabel(totalMoney + "", JLabel.CENTER);
            eachrow.add(totalMoneyLabel);
            
            totalSalary = totalSalary + totalMoney;
            thetable.add(eachrow);
        }
        
        salaryR.setText(totalSalary + "");
        teacherId.setBounds(20, 10, 200, 30);
        teacherName.setBounds(20, 40, 200, 30);

//        numberOfLessons.setBounds(20, 190, 200, 30);
//        totalTeachingHours.setBounds(20, 100, 200, 30);
//        hourlyPayRate.setBounds(20, 130, 200, 30);
//        moneyEarn.setBounds(20, 160, 200, 30);

        salary.setBounds(20, 70, 200, 30);
        salaryR.setBounds(150, 70, 200, 30);
        toptable.setBounds(30, 100, 600, 50);
        scrollview.setBounds(30, 150, 600, 250);

        generate.setBounds(170, 445, 90, 30);
        cancel.setBounds(350, 445, 90, 30);

//        scrollview.setLayout(null);
        scrollview.setViewportView(thetable);
//        thetable.setBounds(0, 0, 300, 500);

        thetable.setPreferredSize(new Dimension(600, Data.getCurrentTeacherAssign().getClasses().size() * 55));

        add(scrollview);
        add(toptable);
        add(teacherId);
        add(teacherName);
        add(salary);
        add(salaryR);
        add(generate);
        add(cancel);
//        add(numberOfLessons);
//        add(totalTeachingHours);
//        add(hourlyPayRate);
//        add(moneyEarn);

        this.addWindowListener(payslipCon);
        generate.addActionListener(payslipCon);
        cancel.addActionListener(payslipCon);

        setTitle("Payslip");
        setSize(650, 540);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set UI
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Can't change look and feel", "Invalid PLAF",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        PayslipGenerateView payslip = PayslipGenerateView.getInstance();
        payslip.initialize();
    }
}
