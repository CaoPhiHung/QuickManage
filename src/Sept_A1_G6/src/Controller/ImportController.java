package Controller;

import Model.*;
import View.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import org.apache.commons.io.*;

public class ImportController implements ActionListener, WindowListener {

    ResourceBundle resources;
    private JFileChooser fc;

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton but = (JButton) ae.getSource();
        resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
        if (but.getText().equalsIgnoreCase(resources.getString("Add2"))) {

            ImportOptions options = ImportOptions.getInstance();
            if (options.getTypes().getSelectedItem().equals("Class") || options.getTypes().getSelectedItem().equals("Lớp")) {
                loadClassFile();
                importFile("ClassCSVFile.csv");
                options.dispose();
                ControlPanelForm.getInstance().setEnabled(true);
                ControlPanelForm.getInstance().setVisible(true);
            } else if (options.getTypes().getSelectedItem().equals("Manager") || options.getTypes().getSelectedItem().equals("Quản Lí")) {
                loadManagerFile();
                importFile("ManagerCSVFile.csv");
                options.dispose();
                ControlPanelForm.getInstance().setEnabled(true);
                ControlPanelForm.getInstance().setVisible(true);
            } else if (options.getTypes().getSelectedItem().equals("Staff") || options.getTypes().getSelectedItem().equals("Nhân Viên")) {
                loadStaffFile();
                importFile("StaffCSVFile.csv");
                options.dispose();
                ControlPanelForm.getInstance().setEnabled(true);
                ControlPanelForm.getInstance().setVisible(true);
            } else if (options.getTypes().getSelectedItem().equals("Teacher") || options.getTypes().getSelectedItem().equals("Giáo Viên")) {
                loadTeacherFile();
                importFile("TeacherCSVFile.csv");
                options.dispose();
                ControlPanelForm.getInstance().setEnabled(true);
                ControlPanelForm.getInstance().setVisible(true);
            } else if (options.getTypes().getSelectedItem().equals("Student") || options.getTypes().getSelectedItem().equals("Học Sinh")) {
                loadStudentFile();
                importFile("StudentCSVFile.csv");
                options.dispose();
                ControlPanelForm.getInstance().setEnabled(true);
                ControlPanelForm.getInstance().setVisible(true);
            } else if (options.getTypes().getSelectedItem().equals("Invoice") || options.getTypes().getSelectedItem().equals("Hóa Đơn")) {
                importFile("InvoiceCSVFile.csv");
                options.dispose();
                ControlPanelForm.getInstance().setEnabled(true);
                ControlPanelForm.getInstance().setVisible(true);
            }

        } else if (but.getText().equalsIgnoreCase("Cancel") || but.getText().equalsIgnoreCase("Hủy")) {
            ImportOptions.getInstance().dispose();
            ControlPanelForm.getInstance().setEnabled(true);
            ControlPanelForm.getInstance().setVisible(true);
        }
    }

    public void importFile(String name) {
        Data data = new Data();
        if (fc == null) {
            fc = new JFileChooser();
        }

        //Show it.
        int returnVal = fc.showDialog(fc, "Choose");

        //Process the results.
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println(fc.getSelectedFile().getPath());


            //absolute path for source file to be copied
            String source = fc.getSelectedFile().getPath();
            //directory where file will be copied
            String workingDir = System.getProperty("user.dir");
            String target = workingDir + "\\src\\";
            //name of source file
            File sourceFile = new File(source);
            System.out.println("Source: " + source);
            System.out.println("Target: " + target);
            File targetFile = new File(target + name);

            try {
                //copy file from one location to other
                FileUtils.copyFile(sourceFile, targetFile);
                if (name.equalsIgnoreCase("ManagerCSVFile.csv")) {
                    data.saveManagerData(0);
                    JOptionPane.showMessageDialog(null, "Finished import Manager CSV File ", "Notice", JOptionPane.INFORMATION_MESSAGE);
                } else if (name.equalsIgnoreCase("StaffCSVFile.csv")) {
                    data.saveStaffData(0);
                    JOptionPane.showMessageDialog(null, "Finished import Staff CSV File ", "Notice", JOptionPane.INFORMATION_MESSAGE);
                } else if (name.equalsIgnoreCase("TeacherCSVFile.csv")) {
                    data.saveTeacherData(0);
                    JOptionPane.showMessageDialog(null, "Finished import Teacher CSV File ", "Notice", JOptionPane.INFORMATION_MESSAGE);
                } else if (name.equalsIgnoreCase("StudentCSVFile.csv")) {
                    data.saveStudentData(0);
                    JOptionPane.showMessageDialog(null, "Finished import Student CSV File ", "Notice", JOptionPane.INFORMATION_MESSAGE);
                } else if (name.equalsIgnoreCase("ClassCSVFile.csv")) {
                    data.saveClassData(0);
                    JOptionPane.showMessageDialog(null, "Finished import Class CSV File ", "Notice", JOptionPane.INFORMATION_MESSAGE);
                } else if (name.equalsIgnoreCase("InvoiceCSVFile.csv")) {
                    JOptionPane.showMessageDialog(null, "Finished import Invoice CSV File ", "Notice", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (IOException ex) {
            }
        }
    }

    public void loadManagerFile() {
        String[] loadList;

        File fh = new File("ManagerCSVFile.csv");
        if (!fh.exists()) {
            System.out.println(" does not exists");
        }
        Scanner s = null;
        try {
            s = new Scanner(fh);
            int i = 0;
            while (s.hasNextLine()) {
                String temp = s.nextLine();
                loadList = temp.split(",");
                System.out.println("Pass: " + loadList[19]);
                Manager man = new Manager(loadList[0], loadList[1], loadList[2], loadList[3], loadList[4],
                        loadList[5], loadList[6], loadList[8], loadList[7], loadList[10], loadList[9], loadList[11],
                        loadList[12], loadList[13], loadList[14], loadList[15], loadList[16] + "," + loadList[17], loadList[18],
                        loadList[19].toCharArray(), loadList[20].toCharArray(), loadList[21]);
                Data.managerList.add(man);
                i++;
            }
        } catch (Exception ex) {
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }

    public void loadStaffFile() {
        String[] loadList;

        File fh = new File("StaffCSVFile.csv");
        if (!fh.exists()) {
            System.out.println(" does not exists");
        }
        Scanner s = null;
        try {
            s = new Scanner(fh);
            int i = 0;
            while (s.hasNextLine()) {
                String temp = s.nextLine();
                loadList = temp.split(",");

                Staff staff = new Staff(loadList[0], loadList[1], loadList[2], loadList[3], loadList[4],
                        loadList[5], loadList[6], loadList[8], loadList[7], loadList[10], loadList[9], loadList[11],
                        loadList[12], loadList[13], loadList[14], loadList[15], loadList[16] + "," + loadList[17], loadList[18],
                        loadList[19].toCharArray(), loadList[20].toCharArray(), loadList[21]);
                Data.staffList.add(staff);
                i++;
            }
        } catch (Exception ex) {
        } finally {
            if (s != null) {
                s.close();
            }
        }

    }

    public void loadStudentFile() {
        String[] loadList;
        ArrayList<Classes> enrolled = null;
        File fh = new File("StudentCSVFile.csv");
        if (!fh.exists()) {
            System.out.println(" does not exists");
        }
        Scanner s = null;
        try {
            s = new Scanner(fh);
            int i = 0;
            while (s.hasNextLine()) {
                String temp = s.nextLine();
                loadList = temp.split(",");

                if (i == 0) {
                    enrolled = new ArrayList<>();
                }

                if (i % 6 == 1) {
                    enrolled = new ArrayList<>();
                }
                if (loadList[0].equalsIgnoreCase("null")) {
                    System.out.println("No Class");
                    System.out.println("Row: " + i);
                } else if (loadList[0].equalsIgnoreCase("ArrayClass")) {
                    DateFormat formatter = new SimpleDateFormat("E MMM dd kk:mm:ss z yyyy");
                    Date returnStartDate = (Date) formatter.parse(loadList[6]);
                    Date returnEndDate = (Date) formatter.parse(loadList[7]);
                    ClassType ct = new ClassType(loadList[37],loadList[38], loadList[39], loadList[40], loadList[41]);
                    Classes classes = new Classes(loadList[1], loadList[2], ct, loadList[3], loadList[4],
                            returnStartDate, returnEndDate, loadList[7], loadList[8], loadList[9], loadList[10], loadList[11],
                            loadList[12], loadList[13], loadList[14], loadList[15], loadList[16], loadList[17], loadList[18],
                            loadList[19], loadList[20], loadList[21], loadList[22], loadList[23], loadList[24], loadList[25], loadList[26], loadList[27], loadList[28], loadList[39], loadList[30], loadList[31], loadList[32], loadList[33], loadList[34], loadList[35] + "," + loadList[36]);
                    enrolled.add(classes);
                    System.out.println("Row: " + i);
                } else {
                    Student student = new Student(loadList[0], loadList[1], loadList[2], loadList[3], loadList[4],
                            loadList[5], loadList[6], loadList[7], loadList[8], loadList[9], loadList[10], loadList[11],
                            loadList[12], loadList[13], loadList[14], loadList[15], loadList[16] + "," + loadList[17], enrolled, loadList[24], loadList[19],
                            loadList[20], loadList[21], loadList[22], loadList[23], loadList[17]);
                    Data.studentList.add(student);
                    System.out.println("Row: " + i);

                }
                i++;
            }
        } catch (Exception ex) {
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }

    public void loadClassFile() {
        String[] loadList;

        File fh = new File("ClassCSVFile.csv");
        if (!fh.exists()) {
            System.out.println(" does not exists");
        }
        Scanner s = null;
        try {
            s = new Scanner(fh);
            int i = 0;
            while (s.hasNextLine()) {
                String temp = s.nextLine();
                loadList = temp.split(",");
                DateFormat formatter = new SimpleDateFormat("E MMM dd kk:mm:ss z yyyy");
                Date returnStartDate = (Date) formatter.parse(loadList[5]);
                Date returnEndDate = (Date) formatter.parse(loadList[6]);
                    ClassType ct = new ClassType(loadList[37],loadList[38], loadList[39], loadList[40], loadList[41]);
                    Classes classes = new Classes(loadList[1], loadList[2], ct, loadList[3], loadList[4],
                            returnStartDate, returnEndDate, loadList[7], loadList[8], loadList[9], loadList[10], loadList[11],
                            loadList[12], loadList[13], loadList[14], loadList[15], loadList[16], loadList[17], loadList[18],
                            loadList[19], loadList[20], loadList[21], loadList[22], loadList[23], loadList[24], loadList[25], loadList[26], loadList[27], loadList[28], loadList[39], loadList[30], loadList[31], loadList[32], loadList[33], loadList[34], loadList[35] + "," + loadList[36]);                Data.classList.add(classes);
               Data.classList.add(classes);
                            i++;
            }
        } catch (Exception ex) {
        } finally {
            if (s != null) {
                s.close();
            }
        }

    }

    public void loadTeacherFile() {
        String[] loadList;
        ArrayList<Classes> assigned = null;
        File fh = new File("TeacherCSVFile.csv");
        if (!fh.exists()) {
            System.out.println(" does not exists");
        }
        Scanner s = null;
        try {
            s = new Scanner(fh);
            int i = 0;
            while (s.hasNextLine()) {
                String temp = s.nextLine();
                loadList = temp.split(",");

                if (i == 0) {
                    assigned = new ArrayList<>();
                }

                if (i % 6 == 1) {
                    assigned = new ArrayList<>();
                }
                if (loadList[0].equalsIgnoreCase("null")) {
                    System.out.println("No Class");
                    System.out.println("Row: " + i);
                } else if (loadList[0].equalsIgnoreCase("ArrayClass")) {
                    DateFormat formatter = new SimpleDateFormat("E MMM dd kk:mm:ss z yyyy");
                    Date returnStartDate = (Date) formatter.parse(loadList[6]);
                    Date returnEndDate = (Date) formatter.parse(loadList[7]);
                    ClassType ct = new ClassType(loadList[37],loadList[38], loadList[39], loadList[40], loadList[41]);
                    Classes classes = new Classes(loadList[1], loadList[2], ct, loadList[3], loadList[4],
                            returnStartDate, returnEndDate, loadList[7], loadList[8], loadList[9], loadList[10], loadList[11],
                            loadList[12], loadList[13], loadList[14], loadList[15], loadList[16], loadList[17], loadList[18],
                            loadList[19], loadList[20], loadList[21], loadList[22], loadList[23], loadList[24], loadList[25], loadList[26], loadList[27], loadList[28], loadList[39], loadList[30], loadList[31], loadList[32], loadList[33], loadList[34], loadList[35] + "," + loadList[36]);
                    assigned.add(classes);
                    System.out.println("Row: " + i);
                } else {

                    ArrayList<String> skilles = new ArrayList<>();
                    for (int j = 18; j <= 26; j++) {
                        skilles.add(loadList[j]);
                    }
                    ArrayList<String> rate= new ArrayList<>();
                    for (int j = 28; j <= 36; j++) {
                        rate.add(loadList[j]);
                    }
                    Teacher teacher = new Teacher(loadList[0], loadList[1], loadList[2], (Object) loadList[3], (Object) loadList[4],
                            (Object) loadList[5], (Object) loadList[6], (Object) loadList[7], loadList[8], (Object) loadList[9], loadList[10], loadList[11],
                            loadList[12], loadList[13], loadList[14], loadList[15], loadList[16] + "," + loadList[17], skilles, assigned,rate,loadList[27]);
                    Data.teacherList.add(teacher);
                    System.out.println("Row: " + i);

                }
                i++;
            }
        } catch (Exception ex) {
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }

    public void loadInvoiceFile() {
        String[] loadList;
        ArrayList<Classes> assigned = null;
        File fh = new File("InvoiceCSVFile.csv");
        if (!fh.exists()) {
            System.out.println(" does not exists");
        }
        Scanner s = null;
        try {
            s = new Scanner(fh);
            int i = 0;
            while (s.hasNextLine()) {
                String temp = s.nextLine();
                loadList = temp.split(",");

                if (i == 0) {
                    assigned = new ArrayList<>();
                }

                if (i % 6 == 1) {
                    assigned = new ArrayList<>();
                }
                if (loadList[0].equalsIgnoreCase("null")) {
                    System.out.println("No Class");
                    System.out.println("Row: " + i);
                } else if (loadList[0].equalsIgnoreCase("ArrayClass")) {
                    DateFormat formatter = new SimpleDateFormat("E MMM dd kk:mm:ss z yyyy");
                    Date returnStartDate = (Date) formatter.parse(loadList[6]);
                    Date returnEndDate = (Date) formatter.parse(loadList[7]);
                    ClassType ct = new ClassType(loadList[37],loadList[38], loadList[39], loadList[40], loadList[41]);
                    Classes classes = new Classes(loadList[1], loadList[2], ct, loadList[3], loadList[4],
                            returnStartDate, returnEndDate, loadList[7], loadList[8], loadList[9], loadList[10], loadList[11],
                            loadList[12], loadList[13], loadList[14], loadList[15], loadList[16], loadList[17], loadList[18],
                            loadList[19], loadList[20], loadList[21], loadList[22], loadList[23], loadList[24], loadList[25], loadList[26], loadList[27], loadList[28], loadList[39], loadList[30], loadList[31], loadList[32], loadList[33], loadList[34], loadList[35] + "," + loadList[36]);
                   assigned.add(classes);
                    System.out.println("Row: " + i);
                } else {


                    Invoice invoice = new Invoice(loadList[0], assigned, loadList[1], loadList[2] + "," + loadList[3],
                            loadList[4], loadList[5], loadList[6]);

                    Data.invoiceList.add(invoice);
                    System.out.println("Row: " + i);

                }
                i++;
            }
        } catch (Exception ex) {
        } finally {
            if (s != null) {
                s.close();
            }
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
}