/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Thinh
 */
public class PaySlip implements Serializable {

    private String payslipID;
    private String totalSalary;
    private ArrayList<String> numberOfLesson = new ArrayList<>();
    private ArrayList<String> numberTeachingHours = new ArrayList<>();
    private ArrayList<String> hourlyPayRate = new ArrayList<>();
    private ArrayList<String> moneyEarned = new ArrayList<>();

    public PaySlip() {
    };
    
    public PaySlip(ArrayList<String> numberOfLesson, ArrayList<String> numberTeachingHours, ArrayList<String> hourlyPayRate,
            ArrayList<String> moneyEarned) {
        double totalSalary = 0;
        this.numberOfLesson = numberOfLesson;
        this.numberTeachingHours = numberTeachingHours;
        this.hourlyPayRate = hourlyPayRate;
        this.moneyEarned = moneyEarned;
        for (String stri : moneyEarned) {
            totalSalary = totalSalary + Double.parseDouble(stri);
        }
        this.totalSalary = totalSalary + "";
    }

    public String getPayslipID() {
        return payslipID;
    }

    public void setPayslipID(String payslipID) {
        this.payslipID = payslipID;
    }

    public String getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(String totalSalary) {
        this.totalSalary = totalSalary;
    }

    public ArrayList<String> getNumberOfLesson() {
        return numberOfLesson;
    }

    public void setNumberOfLesson(ArrayList<String> numberOfLesson) {
        this.numberOfLesson = numberOfLesson;
    }

    public ArrayList<String> getNumberTeachingHours() {
        return numberTeachingHours;
    }

    public void setNumberTeachingHours(ArrayList<String> numberTeachingHours) {
        this.numberTeachingHours = numberTeachingHours;
    }

    public ArrayList<String> getHourlyPayRate() {
        return hourlyPayRate;
    }

    public void setHourlyPayRate(ArrayList<String> hourlyPayRate) {
        this.hourlyPayRate = hourlyPayRate;
    }

    public ArrayList<String> getMoneyEarned() {
        return moneyEarned;
    }

    public void setMoneyEarned(ArrayList<String> moneyEarned) {
        this.moneyEarned = moneyEarned;
    }
}
