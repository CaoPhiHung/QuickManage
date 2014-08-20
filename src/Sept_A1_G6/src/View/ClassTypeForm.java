/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ClassTypeController;
import java.awt.Font;
import javax.swing.*;

/**
 *
 * @author Kelly
 */
public class ClassTypeForm extends JFrame{
    
    private static ClassTypeForm unique;
    private String option ="";
    private JLabel nameLabel = new JLabel("Name: ");
    private JLabel feePerLessonLabel = new JLabel("Fee per lesson: ");
    private JLabel typeLabel = new JLabel("Type: ");
    private JLabel remarksLabel = new JLabel("Remarks: ");
    private JLabel lessonPerWeekLabel = new JLabel("Lesson per week: ");
    private JTextField name = new JTextField();
    private JTextField feePerLesson = new JTextField();
    private JComboBox<String> type = new JComboBox<>(); 
    private JTextField remarks = new JTextField();
    private JComboBox<String> lessonPerWeek = new JComboBox<>(); 
    private JButton add = new JButton("Add");
    private JButton cancel = new JButton("Cancel");
    private ClassTypeController classTypeCon = new ClassTypeController();
    
    public static ClassTypeForm getInstance() {
        if (unique == null) {
            unique = new ClassTypeForm();
        }
        return unique;
    }
    
    public static void setUnique(ClassTypeForm unique) {
        ClassTypeForm.unique = unique;
    }
    
    public void initialize() {
        
        setLayout(null);
        Font font = new Font("Comic sans ms", Font.ROMAN_BASELINE, 13);
        
        this.addWindowListener(classTypeCon);
        add.addActionListener(classTypeCon);
        cancel.addActionListener(classTypeCon);
        
        type.addItem("Individual");
        type.addItem("Group");
        type.addItem("Dual");
        lessonPerWeek.addItem("1");
        lessonPerWeek.addItem("2");
        lessonPerWeek.addItem("3");
        lessonPerWeek.addItem("4");
        lessonPerWeek.addItem("5");
        lessonPerWeek.addItem("6");
        lessonPerWeek.addItem("7");
        
        nameLabel.setFont(font);
        feePerLessonLabel.setFont(font);
        typeLabel.setFont(font);
        remarksLabel.setFont(font);
        lessonPerWeekLabel.setFont(font);
        name.setFont(font);
        feePerLesson.setFont(font);
        type.setFont(font);
        remarks.setFont(font);
        lessonPerWeek.setFont(font);
        add.setFont(font);
        cancel.setFont(font);
        
        nameLabel.setBounds(30, 40, 50, 20);
        feePerLessonLabel.setBounds(30, 80, 100, 20);
        typeLabel.setBounds(30, 120, 60, 20);
        remarksLabel.setBounds(30, 160, 60, 20);
        lessonPerWeekLabel.setBounds(30, 200, 120, 20);
        name.setBounds(140, 40, 160, 28);
        feePerLesson.setBounds(140, 80, 160, 28);
        type.setBounds(140, 120, 100, 28);
        remarks.setBounds(140, 160, 160, 28);
        lessonPerWeek.setBounds(140, 200, 60, 30);
        add.setBounds(60, 260, 80, 30);
        cancel.setBounds(180, 260, 80, 30);
        
        add(nameLabel);
        add(feePerLessonLabel);
        add(name);
        add(feePerLesson);
        add(typeLabel);
        add(remarksLabel);
        add(lessonPerWeekLabel);
        add(type);
        add(remarks);
        add(lessonPerWeek);
        add(add);
        add(cancel);
        
        setTitle("Class Type");
        setSize(330, 350);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        
        //Set UI
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Can't change look and feel", "Invalid PLAF",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setOption(String option) {
        this.option = option;
    }
    
    public String getOption() {
        return this.option;
    }

    public String getNameText() {
        return name.getText();
    }

    public String getFeeText() {
        return feePerLesson.getText();
    }
    
    public JTextField getNameField() {
        return name;
    }

    public JTextField getFeeField() {
        return feePerLesson;
    }

    public String getTypeText() {
        return type.getSelectedItem().toString();
    }

    public String getRemarksText() {
        return remarks.getText();
    }

    public String getLessonPerWeekText() {
        return lessonPerWeek.getSelectedItem().toString();
    }

    public JComboBox<String> getTypeBox() {
        return type;
    }

    public JTextField getRemarks() {
        return remarks;
    }

    public JComboBox<String> getLessonPerWeek() {
        return lessonPerWeek;
    }
    
    

}
