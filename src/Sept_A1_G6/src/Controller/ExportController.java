/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Classes;
import Model.Data;
import View.ChooseOptions;
import View.ClassForm;
import View.ControlPanelForm;
import View.ExportOptions;
import View.ManagerForm;
import View.StaffForm;
import View.StudentForm;
import View.TeacherForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Phi Hung
 */
public class ExportController implements ActionListener, WindowListener {

    ResourceBundle resources;
    private JFileChooser fc;

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton but = (JButton) ae.getSource();
        resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
        if (but.getText().equalsIgnoreCase(resources.getString("Add2"))) {
            
            ExportOptions options = ExportOptions.getInstance();
            if (options.getTypes().getSelectedItem().equals("Class") || options.getTypes().getSelectedItem().equals("Lớp")) {
                
                exportClassFile();
                ControlPanelForm.getInstance().setEnabled(true);
                ControlPanelForm.getInstance().setVisible(true);
                JOptionPane.showMessageDialog(null, "Finished export Class data into CSV File ", "Notice", JOptionPane.INFORMATION_MESSAGE);
            } else if (options.getTypes().getSelectedItem().equals("Manager") || options.getTypes().getSelectedItem().equals("Quản Lí")) {
                exportManagerFile();
                options.dispose();
                ControlPanelForm.getInstance().setEnabled(true);
                ControlPanelForm.getInstance().setVisible(true);
                JOptionPane.showMessageDialog(null, "Finished export Manager data into CSV File ", "Notice", JOptionPane.INFORMATION_MESSAGE);
            } else if (options.getTypes().getSelectedItem().equals("Staff") || options.getTypes().getSelectedItem().equals("Nhân Viên")) {
                exportStaffFile();
                options.dispose();
                ControlPanelForm.getInstance().setEnabled(true);
                ControlPanelForm.getInstance().setVisible(true);
                JOptionPane.showMessageDialog(null, "Finished export Staff data into CSV File ", "Notice", JOptionPane.INFORMATION_MESSAGE);
            } else if (options.getTypes().getSelectedItem().equals("Teacher") || options.getTypes().getSelectedItem().equals("Giáo Viên")) {
                exportTeacherFile();
                options.dispose();
                ControlPanelForm.getInstance().setEnabled(true);
                ControlPanelForm.getInstance().setVisible(true);
                JOptionPane.showMessageDialog(null, "Finished export Teacher data into CSV File ", "Notice", JOptionPane.INFORMATION_MESSAGE);
            } else if (options.getTypes().getSelectedItem().equals("Student") || options.getTypes().getSelectedItem().equals("Học Sinh")) {
                exportStudentFile();
                options.dispose();
                ControlPanelForm.getInstance().setEnabled(true);
                ControlPanelForm.getInstance().setVisible(true);
                JOptionPane.showMessageDialog(null, "Finished export Student data into CSV File ", "Notice", JOptionPane.INFORMATION_MESSAGE);
            } else if (options.getTypes().getSelectedItem().equals("Invoice") || options.getTypes().getSelectedItem().equals("Hóa Đơn")) {
                exportInvoiceFile();
                options.dispose();
                ControlPanelForm.getInstance().setEnabled(true);
                ControlPanelForm.getInstance().setVisible(true);
                JOptionPane.showMessageDialog(null, "Finished export Invoice data into CSV File ", "Notice", JOptionPane.INFORMATION_MESSAGE);
            }
          
        } else if (but.getText().equalsIgnoreCase("Cancel") || but.getText().equalsIgnoreCase("Hủy")) {
            ExportOptions.getInstance().dispose();
            ControlPanelForm.getInstance().setEnabled(true);
            ControlPanelForm.getInstance().setVisible(true);

        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        ControlPanelForm.getInstance().setEnabled(true);
        ControlPanelForm.getInstance().setVisible(true);
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    public void exportManagerFile() {
        ArrayList<String> list = new ArrayList<>();
//        JEditorPane testbo = new JEditorPane();
//        testbo.setText("HEHIHIHIHI");
//        
        for (int i = 0; i < Data.managerList.size(); i++) {
            list.add(Data.managerList.get(i).getID() + ","
                    + Data.managerList.get(i).getFirstName() + ","
                    + Data.managerList.get(i).getLastName() + ","
                    + Data.managerList.get(i).getDobDate() + ","
                    + Data.managerList.get(i).getDobMonth() + ","
                    + Data.managerList.get(i).getDobYear() + ","
                    + Data.managerList.get(i).getGender() + ","
                    + Data.managerList.get(i).getPhoneNoCode() + ","
                    + Data.managerList.get(i).getPhoneNo() + ","
                    + Data.managerList.get(i).getHomeNoCode() + ","
                    + Data.managerList.get(i).getHomeNo() + ","
                    + Data.managerList.get(i).getEmail() + ","
                    + Data.managerList.get(i).getAddress() + ","
                    + Data.managerList.get(i).getStatus() + ","
                    + Data.managerList.get(i).getDescription() + ","
                    + Data.managerList.get(i).getUserType() + ","
                    + Data.managerList.get(i).getCurrentdate() + ","
                    + Data.managerList.get(i).getUsername() + ","
                    + charToString(Data.managerList.get(i).getTypingPassword()) + ","
                    + charToString(Data.managerList.get(i).getRetype()) + ","
                    + Data.managerList.get(i).getImageLink() 
                    );
        }

        File fh = new File("ManagerCSVFile.csv");
        if (!fh.exists()) {
            System.out.println("File doesnt exist");
        }
        Writer w = null;
        try {

            w = new PrintWriter(fh);

            //Write arrayList into the query file
            for (int i = 0; i < list.size(); i++) {
                w.write(list.get(i) + "\n");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Cant export to Manager CSV File", "Notice", JOptionPane.INFORMATION_MESSAGE);
        } finally {
            if (w != null) {
                try {
                    w.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        chooseDirectory(fh);
    }

    public void exportStaffFile() {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < Data.staffList.size(); i++) {
            list.add(Data.staffList.get(i).getID() + ","
                    + Data.staffList.get(i).getFirstName() + ","
                    + Data.staffList.get(i).getLastName() + ","
                    + Data.staffList.get(i).getDobDate() + ","
                    + Data.staffList.get(i).getDobMonth() + ","
                    + Data.staffList.get(i).getDobYear() + ","
                    + Data.staffList.get(i).getGender() + ","
                    + Data.staffList.get(i).getPhoneNoCode() + ","
                    + Data.staffList.get(i).getPhoneNo() + ","
                    + Data.staffList.get(i).getHomeNoCode() + ","
                    + Data.staffList.get(i).getHomeNo() + ","
                    + Data.staffList.get(i).getEmail() + ","
                    + Data.staffList.get(i).getAddress() + ","
                    + Data.staffList.get(i).getStatus() + ","
                    + Data.staffList.get(i).getDescription() + ","
                    + Data.staffList.get(i).getUserType() + ","
                    + Data.staffList.get(i).getCurrentdate() + ","
                    + Data.staffList.get(i).getUsername() + ","
                    + charToString(Data.staffList.get(i).getTypingPassword()) + ","
                    + charToString(Data.staffList.get(i).getRetype()) + ","
                    + Data.staffList.get(i).getImageLink());
        }

        File fh = new File("StaffCSVFile.csv");
        if (!fh.exists()) {
            System.out.println("File doesnt exist");
        }
        Writer w = null;
        try {

            w = new PrintWriter(fh);

            //Write arrayList into the query file
            for (int i = 0; i < list.size(); i++) {
                w.write(list.get(i) + "\n");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Cant export to Staff CSV File", "Notice", JOptionPane.INFORMATION_MESSAGE);
        } finally {
            if (w != null) {
                try {
                    w.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        chooseDirectory(fh);
    }
    
    private String charToString(char[] chararray){
        String toReturn = "";
        for(int i = 0; i < chararray.length; i++){
            toReturn = toReturn + chararray[i];
        }
        
        return toReturn;
    }

    public void exportStudentFile() {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < Data.studentList.size(); i++) {
            String[] classes = new String[5];
            String classType = "";
            for (int j = 0; j < Data.studentList.get(i).getClasses().size(); j++) {
                String fee = Data.studentList.get(i).getClasses().get(j).getTuitionFee().replace(",", ".");
              
                classType = Data.studentList.get(i).getClasses().get(j).getClassType().getName() + ","
                    +Data.studentList.get(i).getClasses().get(j).getClassType().getFeePerLesson() + ","
                    +Data.studentList.get(i).getClasses().get(j).getClassType().getType() + ","
                    +Data.studentList.get(i).getClasses().get(j).getClassType().getRemarks() + ","
                    +Data.studentList.get(i).getClasses().get(j).getClassType().getLessonPerWeek();
                
                classes[j] = "ArrayClass,"+Data.studentList.get(i).getClasses().get(j).getId() + ","
                    + Data.studentList.get(i).getClasses().get(j).getClassCode() + ","
                    //+ Data.studentList.get(i).getClasses().get(j).getClassType() + ","
                    + Data.studentList.get(i).getClasses().get(j).getClassName() + ","
                    + fee + ","
                    + Data.studentList.get(i).getClasses().get(j).getStartDate() + ","
                    + Data.studentList.get(i).getClasses().get(j).getEndDate() + ","
                    + Data.studentList.get(i).getClasses().get(j).getDays()[0] + ","
                    + Data.studentList.get(i).getClasses().get(j).getDays()[1] + ","
                    + Data.studentList.get(i).getClasses().get(j).getDays()[2] + ","
                    + Data.studentList.get(i).getClasses().get(j).getDays()[3] + ","
                    + Data.studentList.get(i).getClasses().get(j).getDays()[4] + ","
                    + Data.studentList.get(i).getClasses().get(j).getDays()[5] + ","
                    + Data.studentList.get(i).getClasses().get(j).getDays()[6] + ","
                    + Data.studentList.get(i).getClasses().get(j).getFroms()[0] + ","
                    + Data.studentList.get(i).getClasses().get(j).getFroms()[1] + ","
                    + Data.studentList.get(i).getClasses().get(j).getFroms()[2] + ","
                    + Data.studentList.get(i).getClasses().get(j).getFroms()[3] + ","
                    + Data.studentList.get(i).getClasses().get(j).getFroms()[4] + ","
                    + Data.studentList.get(i).getClasses().get(j).getFroms()[5] + ","
                    + Data.studentList.get(i).getClasses().get(j).getFroms()[6] + ","
                    + Data.studentList.get(i).getClasses().get(j).getTos()[0] + ","
                    + Data.studentList.get(i).getClasses().get(j).getTos()[1] + ","
                    + Data.studentList.get(i).getClasses().get(j).getTos()[2] + ","
                    + Data.studentList.get(i).getClasses().get(j).getTos()[3] + ","
                    + Data.studentList.get(i).getClasses().get(j).getTos()[4] + ","
                    + Data.studentList.get(i).getClasses().get(j).getTos()[5] + ","
                    + Data.studentList.get(i).getClasses().get(j).getTos()[6] + ","
                    + Data.studentList.get(i).getClasses().get(j).getRooms()[0] + ","
                    + Data.studentList.get(i).getClasses().get(j).getRooms()[1] + ","
                    + Data.studentList.get(i).getClasses().get(j).getRooms()[2] + ","
                    + Data.studentList.get(i).getClasses().get(j).getRooms()[3] + ","
                    + Data.studentList.get(i).getClasses().get(j).getRooms()[4] + ","
                    + Data.studentList.get(i).getClasses().get(j).getRooms()[5] + ","
                    + Data.studentList.get(i).getClasses().get(j).getRooms()[6] + ","
                    + Data.studentList.get(i).getClasses().get(j).getCurrentDate()+","+classType;
            }

            list.add(classes[0] + "\n" + classes[1] + "\n" + classes[2] + "\n" + classes[3] + "\n" + classes[4]+"\n"+
                    Data.studentList.get(i).getID() + ","
                    + Data.studentList.get(i).getFirstName() + ","
                    + Data.studentList.get(i).getLastName() + ","
                    + Data.studentList.get(i).getDobDate() + ","
                    + Data.studentList.get(i).getDobMonth() + ","
                    + Data.studentList.get(i).getDobYear() + ","
                    + Data.studentList.get(i).getGender() + ","
                    + Data.studentList.get(i).getPhoneNoCode() + ","
                    + Data.studentList.get(i).getPhoneNo() + ","
                    + Data.studentList.get(i).getHomeNoCode() + ","
                    + Data.studentList.get(i).getHomeNo() + ","
                    + Data.studentList.get(i).getEmail() + ","
                    + Data.studentList.get(i).getAddress() + ","
                    + Data.studentList.get(i).getStatus() + ","
                    + Data.studentList.get(i).getDescription() + ","
                    + Data.studentList.get(i).getUserType() + ","
                    + Data.studentList.get(i).getCurrentdate() + ","
                    + Data.studentList.get(i).getContactName() + ","
                    + Data.studentList.get(i).getContactPhoneCode() + ","
                    + Data.studentList.get(i).getContactPhone() + ","
                    + Data.studentList.get(i).getContactEmail() + ","
                    + Data.studentList.get(i).getContactAddress() + ","
                    + Data.studentList.get(i).getContactRelationShip() + ","
                    + Data.studentList.get(i).getImageLink()                  
                    );
        }

        File fh = new File("StudentCSVFile.csv");
        if (!fh.exists()) {
            System.out.println("File doesnt exist");
        }
        Writer w = null;
        try {

            w = new PrintWriter(fh);

            //Write arrayList into the query file
            for (int i = 0; i < list.size(); i++) {
                w.write(list.get(i) + "\n");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Cant export to Student CSV File", "Notice", JOptionPane.INFORMATION_MESSAGE);
        } finally {
            if (w != null) {
                try {
                    w.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        chooseDirectory(fh);
    }

    public void exportTeacherFile() {
        ArrayList<String> list = new ArrayList<>();


        for (int i = 0; i < Data.teacherList.size(); i++) {
            String[] classes = new String[5];
            String[] skilles = new String[9];
            String[] rate = new String[9];
            String classType= "";
            for (int j = 0; j < Data.teacherList.get(i).getClasses().size(); j++) {
                 String fee = Data.teacherList.get(i).getClasses().get(j).getTuitionFee().replace(",", ".");
                classType = Data.teacherList.get(i).getClasses().get(j).getClassType().getName() + ","
                    +Data.teacherList.get(i).getClasses().get(j).getClassType().getFeePerLesson() + ","
                    +Data.teacherList.get(i).getClasses().get(j).getClassType().getType() + ","
                    +Data.teacherList.get(i).getClasses().get(j).getClassType().getRemarks() + ","
                    +Data.teacherList.get(i).getClasses().get(j).getClassType().getLessonPerWeek();
                
                classes[j] = "ArrayClass,"+Data.teacherList.get(i).getClasses().get(j).getId() + ","
                    + Data.teacherList.get(i).getClasses().get(j).getClassCode() + ","
//                    + Data.teacherList.get(i).getClasses().get(j).getClassType() + ","
                    + Data.teacherList.get(i).getClasses().get(j).getClassName() + ","
                    + fee + ","
                    + Data.teacherList.get(i).getClasses().get(j).getStartDate() + ","
                    + Data.teacherList.get(i).getClasses().get(j).getEndDate() + ","
                    + Data.teacherList.get(i).getClasses().get(j).getDays()[0] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getDays()[1] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getDays()[2] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getDays()[3] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getDays()[4] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getDays()[5] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getDays()[6] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getFroms()[0] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getFroms()[1] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getFroms()[2] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getFroms()[3] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getFroms()[4] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getFroms()[5] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getFroms()[6] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getTos()[0] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getTos()[1] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getTos()[2] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getTos()[3] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getTos()[4] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getTos()[5] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getTos()[6] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getRooms()[0] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getRooms()[1] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getRooms()[2] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getRooms()[3] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getRooms()[4] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getRooms()[5] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getRooms()[6] + ","
                    + Data.teacherList.get(i).getClasses().get(j).getCurrentDate()+","+classType;
            }
            for (int j = 0; j < Data.teacherList.get(i).getSkillList().size(); j++) {
                skilles[j] = Data.teacherList.get(i).getSkillList().get(j);
            }
            for (int j = 0; j < Data.teacherList.get(i).getHourlyRate().size(); j++) {
                rate[j] = Data.teacherList.get(i).getSkillList().get(j);
            }
            
            list.add( classes[0] + "\n" + classes[1] + "\n" + classes[2] + "\n" + classes[3] + "\n" + classes[4] + "\n" +                    
                    Data.teacherList.get(i).getID() + ","
                    + Data.teacherList.get(i).getFirstName() + ","
                    + Data.teacherList.get(i).getLastName() + ","
                    + Data.teacherList.get(i).getDobDate() + ","
                    + Data.teacherList.get(i).getDobMonth() + ","
                    + Data.teacherList.get(i).getDobYear() + ","
                    + Data.teacherList.get(i).getGender() + ","
                    + Data.teacherList.get(i).getPhoneNoCode() + ","
                    + Data.teacherList.get(i).getPhoneNo() + ","
                    + Data.teacherList.get(i).getHomeNoCode() + ","
                    + Data.teacherList.get(i).getHomeNo() + ","
                    + Data.teacherList.get(i).getEmail() + ","
                    + Data.teacherList.get(i).getAddress() + ","
                    + Data.teacherList.get(i).getStatus() + ","
                    + Data.teacherList.get(i).getDescription() + ","
                    + Data.teacherList.get(i).getUserType() + ","
                    + Data.teacherList.get(i).getCurrentdate() + ","
                    + skilles[0] + "," + skilles[1] + "," + skilles[2] + "," + skilles[3] + "," + skilles[4] + ","
                    + skilles[5] + "," + skilles[6] + "," + skilles[7] + "," + skilles[8] + ","
                    + Data.teacherList.get(i).getImageLink()+","
                    + rate[0] + "," + rate[1] + "," + rate[2] + "," + rate[3] + "," + rate[4] + ","
                    + rate[5] + "," + rate[6] + "," + rate[7] + "," + rate[8]);
        }

        File fh = new File("TeacherCSVFile.csv");
        if (!fh.exists()) {
            System.out.println("File doesnt exist");
        }
        Writer w = null;
        try {

            w = new PrintWriter(fh);

            //Write arrayList into the query file
            for (int i = 0; i < list.size(); i++) {
                w.write(list.get(i) + "\n");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Cant export to Teacher CSV File", "Notice", JOptionPane.INFORMATION_MESSAGE);
        } finally {
            if (w != null) {
                try {
                    w.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        chooseDirectory(fh);
    }

    public void exportClassFile() {
        ArrayList<String> list = new ArrayList<>();
        String classType = "";
        for (int i = 0; i < Data.classList.size(); i++) {
            String fee = Data.classList.get(i).getTuitionFee().replace(",", ".");
            classType = Data.classList.get(i).getClassType().getName() + ","
                    +Data.classList.get(i).getClassType().getFeePerLesson() + ","
                    +Data.classList.get(i).getClassType().getType() + ","
                    +Data.classList.get(i).getClassType().getRemarks() + ","
                    +Data.classList.get(i).getClassType().getLessonPerWeek();
            System.out.println("classType: "+classType);        
            list.add(Data.classList.get(i).getId() + ","
                    + Data.classList.get(i).getClassCode() + ","
//                    + Data.classList.get(i).getClassType() + ","
                    + Data.classList.get(i).getClassName() + ","
                    + fee + ","
                    + Data.classList.get(i).getStartDate() + ","
                    + Data.classList.get(i).getEndDate() + ","
                    + Data.classList.get(i).getDays()[0] + ","
                    + Data.classList.get(i).getDays()[1] + ","
                    + Data.classList.get(i).getDays()[2] + ","
                    + Data.classList.get(i).getDays()[3] + ","
                    + Data.classList.get(i).getDays()[4] + ","
                    + Data.classList.get(i).getDays()[5] + ","
                    + Data.classList.get(i).getDays()[6] + ","
                    + Data.classList.get(i).getFroms()[0] + ","
                    + Data.classList.get(i).getFroms()[1] + ","
                    + Data.classList.get(i).getFroms()[2] + ","
                    + Data.classList.get(i).getFroms()[3] + ","
                    + Data.classList.get(i).getFroms()[4] + ","
                    + Data.classList.get(i).getFroms()[5] + ","
                    + Data.classList.get(i).getFroms()[6] + ","
                    + Data.classList.get(i).getTos()[0] + ","
                    + Data.classList.get(i).getTos()[1] + ","
                    + Data.classList.get(i).getTos()[2] + ","
                    + Data.classList.get(i).getTos()[3] + ","
                    + Data.classList.get(i).getTos()[4] + ","
                    + Data.classList.get(i).getTos()[5] + ","
                    + Data.classList.get(i).getTos()[6] + ","
                    + Data.classList.get(i).getRooms()[0] + ","
                    + Data.classList.get(i).getRooms()[1] + ","
                    + Data.classList.get(i).getRooms()[2] + ","
                    + Data.classList.get(i).getRooms()[3] + ","
                    + Data.classList.get(i).getRooms()[4] + ","
                    + Data.classList.get(i).getRooms()[5] + ","
                    + Data.classList.get(i).getRooms()[6] + ","
                    + Data.classList.get(i).getCurrentDate()+","
                    //+Data.classList.get(i).getClassType()+","
                    +classType);
        }

        File fh = new File("ClassCSVFile.csv");
        if (!fh.exists()) {
            System.out.println("File doesnt exist");
        }
        Writer w = null;
        try {

            w = new PrintWriter(fh);

            //Write arrayList into the query file
            for (int i = 0; i < list.size(); i++) {
                w.write(list.get(i) + "\n");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Cant export to Class CSV File", "Notice", JOptionPane.INFORMATION_MESSAGE);
        } finally {
            if (w != null) {
                try {
                    w.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        chooseDirectory(fh);
    }

    public void exportInvoiceFile() {
        ArrayList<String> list = new ArrayList<>();


        for (int i = 0; i < Data.invoiceList.size(); i++) {
            String[] classes = new String[5];
            String classType = "";
            for (int j = 0; j < Data.invoiceList.get(i).getClasses().size(); j++) {
            String fee = Data.invoiceList.get(i).getClasses().get(j).getTuitionFee().replace(",", ".");     
            
            classType = Data.invoiceList.get(i).getClasses().get(j).getClassType().getName() + ","
                    +Data.invoiceList.get(i).getClasses().get(j).getClassType().getFeePerLesson() + ","
                    +Data.invoiceList.get(i).getClasses().get(j).getClassType().getType() + ","
                    +Data.invoiceList.get(i).getClasses().get(j).getClassType().getRemarks() + ","
                    +Data.invoiceList.get(i).getClasses().get(j).getClassType().getLessonPerWeek();
                
            
            classes[j] = "ArrayClass,"+Data.invoiceList.get(i).getClasses().get(j).getId() + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getClassCode() + ","
//                    + Data.invoiceList.get(i).getClasses().get(j).getClassType() + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getClassName() + ","
                    + fee + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getStartDate() + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getEndDate() + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getDays()[0] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getDays()[1] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getDays()[2] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getDays()[3] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getDays()[4] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getDays()[5] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getDays()[6] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getFroms()[0] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getFroms()[1] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getFroms()[2] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getFroms()[3] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getFroms()[4] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getFroms()[5] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getFroms()[6] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getTos()[0] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getTos()[1] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getTos()[2] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getTos()[3] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getTos()[4] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getTos()[5] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getTos()[6] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getRooms()[0] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getRooms()[1] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getRooms()[2] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getRooms()[3] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getRooms()[4] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getRooms()[5] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getRooms()[6] + ","
                    + Data.invoiceList.get(i).getClasses().get(j).getCurrentDate()+","+classType;
           }
            String fee = Data.invoiceList.get(i).getTotalTuitionFee().replace(",", ".");
            list.add(classes[0] + "\n" + classes[1] + "\n" + classes[2] + "\n" + classes[3] + "\n" + classes[4] + "\n"
                    +Data.invoiceList.get(i).getInvoiceID() + ","
                    + fee + ","
                    + Data.invoiceList.get(i).getPaidDate() + ","
                    + Data.invoiceList.get(i).getPaidMethod() + ","
                    + Data.invoiceList.get(i).getPayment()+ ","
                    +Data.invoiceList.get(i).isIsTop()+",");
                   // +Data.invoiceList.get(i).getEp().getText());
        }

        File fh = new File("InvoiceCSVFile.csv");
        if (!fh.exists()) {
            System.out.println("File doesnt exist");
        }
        Writer w = null;
        try {

            w = new PrintWriter(fh);

            //Write arrayList into the query file
            for (int i = 0; i < list.size(); i++) {
                w.write(list.get(i) + "\n");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Cant export to Invoice CSV File", "Notice", JOptionPane.INFORMATION_MESSAGE);
        } finally {
            if (w != null) {
                try {
                    w.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        chooseDirectory(fh);
    }
    
    public void chooseDirectory(File f){
        fc = new JFileChooser();
//       int returnVal = fc.showSaveDialog(fc);
//            if (returnVal == JFileChooser.APPROVE_OPTION) {
//                fc.setSelectedFile(f);
//       
//    }
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showSaveDialog(fc);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                
             System.out.println("File: "+f.getName());
                System.out.println("Directory: "+fc.getSelectedFile());
                System.out.println("New Directory: "+fc.getCurrentDirectory().getPath()+"\\"+f.getName());
                
                File sourceFile = new File(f.getPath());
                File targetFile = new File(fc.getSelectedFile().getPath()+"\\"+f.getName());
                
                try {
                        //copy file from one location to other
                        FileUtils.copyFile(sourceFile, targetFile);
                        System.out.println("Copy Working");
                    } catch (IOException ex) {
                        System.out.println("Cant copy a file");
                    }
                
//                    final JFileChooser chooser = new JFileChooser() {
//            public void approveSelection() {
//                if (getSelectedFile().isFile()) {
//                    return;
//                } else
//                    super.approveSelection();
//            }
//    };
//    chooser.setFileSelectionMode( JFileChooser.FILES_AND_DIRECTORIES );
    
}
}
}