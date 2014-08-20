package Controller;

import Custom.*;
import Model.*;
import View.*;
import View.ControlPanelFormUtilities.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class ControlPanelFormController implements ActionListener {

    private static ArrayList<String> types = new ArrayList<>();
    private static ArrayList<String> ids = new ArrayList<>();
    private static ArrayList<String> names = new ArrayList<>();
    private static ArrayList<String> infoOne = new ArrayList<>();
    private static ArrayList<String> infoTwo = new ArrayList<>();
    private static ArrayList<String> dates = new ArrayList<>();
    private static ArrayList<Boolean> status = new ArrayList<>();

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof SuperButton) {
            SuperButton but = (SuperButton) e.getSource();
            if (but.getFrontText().getText().equalsIgnoreCase("Search") || but.getFrontText().getText().equalsIgnoreCase("Tìm Kiếm")) {
                types = new ArrayList<>();
                ids = new ArrayList<>();
                names = new ArrayList<>();
                infoOne = new ArrayList<>();
                infoTwo = new ArrayList<>();
                dates = new ArrayList<>();
                status = new ArrayList<>();
                String[] input = ControlPanelForm.getInstance().getSearch().split("\\.");
                if (input.length == 2) {
                    if (input[0].equalsIgnoreCase("Manager") || input[0].equalsIgnoreCase("Quản Lí")) {
                        rightPanelLoadManager(input[1]);
                    } else if (input[0].equalsIgnoreCase("Staff") || input[0].equalsIgnoreCase("Nhân Viên")) {
                        rightPanelLoadStaff(input[1]);
                    } else if (input[0].equalsIgnoreCase("Student") || input[0].equalsIgnoreCase("Học Sinh")) {
                        rightPanelLoadStudent(input[1]);
                    } else if (input[0].equalsIgnoreCase("Teacher") || input[0].equalsIgnoreCase("Giáo viên")) {
                        rightPanelLoadTeacher(input[1]);
                    } else if (input[0].equalsIgnoreCase("Class") || input[0].equalsIgnoreCase("Lớp")) {
                        rightPanelLoadClass(input[1]);
                    }
                }
            }
        } else if (e.getSource() instanceof JButton) {
            JButton but = (JButton) e.getSource();

            if (but.getText().equalsIgnoreCase("Add") || but.getText().equalsIgnoreCase("Thêm")) {
                ChooseOptions.setUnique(null);
                ChooseOptions.getInstance().initialize();
                ControlPanelForm.getInstance().setEnabled(false);
            } else if (but.getText().equalsIgnoreCase("TimeTable") || but.getText().equalsIgnoreCase("Thời Khóa Biểu")) {
                AllTimeTable.setUnique(null);
                AllTimeTable alltime = AllTimeTable.getInstance();
                alltime.initialize();
                ControlPanelForm.getInstance().setEnabled(false);
            }
        } else if (e.getSource() instanceof JComboBox) {
            JComboBox cb = (JComboBox) e.getSource();

            if (cb.getSelectedItem().toString().equalsIgnoreCase("Manager") || cb.getSelectedItem().toString().equalsIgnoreCase("Quản Lí")) {
                ControlPanelForm.getInstance().getDeleteButton().setEnabled(true);
                ControlPanelForm.getInstance().getActivateButton().setEnabled(true);
                ControlPanelForm.getInstance().getCopyClassButton().setEnabled(false);
                rightPanelLoadManager();

            } else if (cb.getSelectedItem().toString().equalsIgnoreCase("Staff") || cb.getSelectedItem().toString().equalsIgnoreCase("Nhân Viên")) {
                ControlPanelForm.getInstance().getDeleteButton().setEnabled(true);
                ControlPanelForm.getInstance().getActivateButton().setEnabled(true);
                ControlPanelForm.getInstance().getCopyClassButton().setEnabled(false);
                rightPanelLoadStaff();

            } else if (cb.getSelectedItem().toString().equalsIgnoreCase("Student") || cb.getSelectedItem().toString().equalsIgnoreCase("Học Sinh")) {
                ControlPanelForm.getInstance().getDeleteButton().setEnabled(true);
                ControlPanelForm.getInstance().getActivateButton().setEnabled(true);
                ControlPanelForm.getInstance().getCopyClassButton().setEnabled(false);
                rightPanelLoadStudent();

            } else if (cb.getSelectedItem().toString().equalsIgnoreCase("Teacher") || cb.getSelectedItem().toString().equalsIgnoreCase("Giáo Viên")) {
                ControlPanelForm.getInstance().getDeleteButton().setEnabled(true);
                ControlPanelForm.getInstance().getActivateButton().setEnabled(true);
                ControlPanelForm.getInstance().getCopyClassButton().setEnabled(false);
                rightPanelLoadTeacher();

            } else if (cb.getSelectedItem().toString().equalsIgnoreCase("Class") || cb.getSelectedItem().toString().equalsIgnoreCase("Lớp")) {
                ControlPanelForm.getInstance().getDeleteButton().setEnabled(true);
                ControlPanelForm.getInstance().getActivateButton().setEnabled(false);
                ControlPanelForm.getInstance().getCopyClassButton().setEnabled(true);
                rightPanelLoadClass();

            } else if (cb.getSelectedItem().toString().equalsIgnoreCase("Class Type")) {
                ControlPanelForm.getInstance().getDeleteButton().setEnabled(true);
                ControlPanelForm.getInstance().getActivateButton().setEnabled(false);
                ControlPanelForm.getInstance().getCopyClassButton().setEnabled(false);
                names = new ArrayList<>();
                ArrayList<String> fees = new ArrayList<>();
                ArrayList<String> types = new ArrayList<>();
                ArrayList<String> remarks = new ArrayList<>();
                ArrayList<String> lessonPerWeeks = new ArrayList<>();

                for (int i = 0; i < Data.classTypeList.size(); i++) {
                    String name = Data.classTypeList.get(i).getName();
                    String fee = Data.classTypeList.get(i).getFeePerLesson();
                    String type = Data.classTypeList.get(i).getType();
                    String remark = Data.classTypeList.get(i).getRemarks();
                    String lessonPerWeek = Data.classTypeList.get(i).getLessonPerWeek();
                    names.add(name);
                    fees.add(fee);
                    types.add(type);
                    remarks.add(remark);
                    lessonPerWeeks.add(lessonPerWeek);
                }

                ScrollPanelInfo.getInstance().addStuffToClassTypeScrollPanel(names, fees, types, remarks, lessonPerWeeks);
            } else if (cb.getSelectedItem().toString().equalsIgnoreCase("Room")) {
                ControlPanelForm.getInstance().getDeleteButton().setEnabled(true);
                ControlPanelForm.getInstance().getActivateButton().setEnabled(false);
                ControlPanelForm.getInstance().getCopyClassButton().setEnabled(false);

                ArrayList<String> numbers = new ArrayList<>();
                ArrayList<String> types = new ArrayList<>();
                ArrayList<Double> occupiedMinutes = new ArrayList<>();

                for (int i = 0; i < Data.roomList.size(); i++) {
                    String number = Data.roomList.get(i).getNumber();
                    double occupiedMinute = Data.roomList.get(i).getOccupiedMinute();
                    String type = Data.roomList.get(i).getType();

                    numbers.add(number);
                    occupiedMinutes.add(occupiedMinute);
                    types.add(type);
                }

                ScrollPanelInfo.getInstance().addStuffToRoomScrollPanel(numbers, types, occupiedMinutes);
            }
        } else if (e.getSource() instanceof JMenuItem) {
            JMenuItem me = (JMenuItem) e.getSource();
            if (me.getText().equalsIgnoreCase("User Guide") || me.getText().equalsIgnoreCase("Hướng dẫn sử dụng")) {
                Desktop d = Desktop.getDesktop();
                try {
                    d.open(new File("UserGuide\\index.html"));
                } catch (IOException ex) {
                    ex.getMessage();
                }
            } else if (me.getText().equalsIgnoreCase("Exit") || me.getText().equalsIgnoreCase("Thoát")) {
                System.exit(0);
            } else if (me.getText().equalsIgnoreCase("Log out") || me.getText().equalsIgnoreCase("Đăng xuất")) {
                ControlPanelForm.getInstance().dispose();
                LoginForm.getInstance().setVisible(true);

            } else if (me.getText().equalsIgnoreCase("Export CSV") || me.getText().equalsIgnoreCase("Xuất CSV")) {
                System.out.println("export");
                ExportOptions.getInstance().setUnique(null);
                ExportOptions.getInstance().initialize();
                ControlPanelForm.getInstance().setEnabled(false);
            } else if (me.getText().equalsIgnoreCase("Import CSV") || me.getText().equalsIgnoreCase("Nhập CSV")) {
                System.out.println("import");
                ImportOptions.getInstance().setUnique(null);
                ImportOptions.getInstance().importInitialize();
                ControlPanelForm.getInstance().setEnabled(false);

            } else if (me.getText().equalsIgnoreCase("Add") || me.getText().equalsIgnoreCase("Thêm")) {
                ChooseOptions.setUnique(null);
                ChooseOptions.getInstance().initialize();
                ControlPanelForm.getInstance().setEnabled(false);
            } else if (me.getText().equalsIgnoreCase("TimeTable") || me.getText().equalsIgnoreCase("Thời Khóa Biểu")) {
                AllTimeTable.setUnique(null);
                AllTimeTable alltime = AllTimeTable.getInstance();
                alltime.initialize();
                ControlPanelForm.getInstance().setEnabled(false);
            }
        }
    }

    public static void rightPanelLoadManager() {
        types = new ArrayList<>();
        ids = new ArrayList<>();
        names = new ArrayList<>();
        infoOne = new ArrayList<>();
        infoTwo = new ArrayList<>();
        dates = new ArrayList<>();
        status = new ArrayList<>();
        for (int i = 0; i < Data.managerList.size(); i++) {
            String name = Data.managerList.get(i).getFirstName() + " " + Data.managerList.get(i).getLastName();
            ids.add(Data.managerList.get(i).getID());
            names.add(name);
            types.add(Data.managerList.get(i).getUserType());
            infoOne.add("");
            infoTwo.add(Data.managerList.get(i).getEmail());
            dates.add(Data.managerList.get(i).getCurrentdate());
            boolean temp;
            if (Data.managerList.get(i).getStatus().equalsIgnoreCase("Activate")) {
                temp = true;
            } else {
                temp = false;
            }
            status.add(temp);
        }

        ScrollPanelInfo.getInstance().addStuffToScrollPanel(types, ids, names, infoOne, infoTwo, dates, status);
    }

    public static void rightPanelLoadTeacher() {
        types = new ArrayList<>();
        ids = new ArrayList<>();
        names = new ArrayList<>();
        infoOne = new ArrayList<>();
        infoTwo = new ArrayList<>();
        dates = new ArrayList<>();
        status = new ArrayList<>();
        for (int i = 0; i < Data.teacherList.size(); i++) {
            String name = Data.teacherList.get(i).getFirstName() + " " + Data.teacherList.get(i).getLastName();
            ids.add(Data.teacherList.get(i).getID());
            names.add(name);
            types.add(Data.teacherList.get(i).getUserType());
            infoOne.add("");
            infoTwo.add(Data.teacherList.get(i).getEmail());
            dates.add(Data.teacherList.get(i).getCurrentdate());
            boolean temp;
            if (Data.teacherList.get(i).getStatus().equalsIgnoreCase("Activate")) {
                temp = true;
            } else {
                temp = false;
            }
            status.add(temp);
        }
        ScrollPanelInfo.getInstance().addStuffToScrollPanel(types, ids, names, infoOne, infoTwo, dates, status);
    }

    public static void rightPanelLoadStaff() {
        types = new ArrayList<>();
        ids = new ArrayList<>();
        names = new ArrayList<>();
        infoOne = new ArrayList<>();
        infoTwo = new ArrayList<>();
        dates = new ArrayList<>();
        status = new ArrayList<>();
        for (int i = 0; i < Data.staffList.size(); i++) {
            String name = Data.staffList.get(i).getFirstName() + " " + Data.staffList.get(i).getLastName();
            ids.add(Data.staffList.get(i).getID());
            names.add(name);
            types.add(Data.staffList.get(i).getUserType());
            infoOne.add("");
            infoTwo.add(Data.staffList.get(i).getEmail());
            dates.add(Data.staffList.get(i).getCurrentdate());
            boolean temp;
            if (Data.staffList.get(i).getStatus().equalsIgnoreCase("Activate")) {
                temp = true;
            } else {
                temp = false;
            }
            status.add(temp);
        }
        ScrollPanelInfo.getInstance().addStuffToScrollPanel(types, ids, names, infoOne, infoTwo, dates, status);
    }

    public static void rightPanelLoadStudent() {
        types = new ArrayList<>();
        ids = new ArrayList<>();
        names = new ArrayList<>();
        infoOne = new ArrayList<>();
        infoTwo = new ArrayList<>();
        dates = new ArrayList<>();
        status = new ArrayList<>();
        for (int i = 0; i < Data.studentList.size(); i++) {
            String name = Data.studentList.get(i).getFirstName() + " " + Data.studentList.get(i).getLastName();
            ids.add(Data.studentList.get(i).getID());
            names.add(name);
            types.add(Data.studentList.get(i).getUserType());
            infoOne.add("");
            infoTwo.add(Data.studentList.get(i).getEmail());
            dates.add(Data.studentList.get(i).getCurrentdate());
            boolean temp;
            if (Data.studentList.get(i).getStatus().equalsIgnoreCase("Activate")) {
                temp = true;
            } else {
                temp = false;
            }
            status.add(temp);
        }
        ScrollPanelInfo.getInstance().addStuffToScrollPanel(types, ids, names, infoOne, infoTwo, dates, status);
    }

    public static void rightPanelLoadClass() {
        types = new ArrayList<>();
        ids = new ArrayList<>();
        names = new ArrayList<>();
        infoOne = new ArrayList<>();
        infoTwo = new ArrayList<>();
        dates = new ArrayList<>();
        status = new ArrayList<>();
        for (int i = 0; i < Data.classList.size(); i++) {
            ids.add(Data.classList.get(i).getId());
            names.add(Data.classList.get(i).getClassName());
            types.add(Data.classList.get(i).getClassLabel());
            infoOne.add(Data.classList.get(i).getClassCode());
            infoTwo.add(Data.classList.get(i).getClassType().getName());
            dates.add(Data.classList.get(i).getCurrentDate());
            status.add(true);
        }
        ScrollPanelInfo.getInstance().addStuffToScrollPanel(types, ids, names, infoOne, infoTwo, dates, status);
    }

    public static void rightPanelLoadManager(String search) {
        //Change to 0 Tomorrow
        for (int i = 0; i < Data.managerList.size(); i++) {
            String name = Data.managerList.get(i).getFirstName() + " " + Data.managerList.get(i).getLastName();
            String[] day = Data.managerList.get(i).getCurrentdate().split("\\,");
            String[] month = day[1].split("\\s");
            if (name.contains(search) || day[0].contains(search)
                    || search.contains(month[0]) || month[1].contains(search)
                    || Data.managerList.get(i).getEmail().toString().contains(search)
                    || Data.managerList.get(i).getID().contains(search)) {
                ids.add(Data.managerList.get(i).getID());
                names.add(name);
                types.add(Data.managerList.get(i).getUserType());
                infoOne.add("");
                infoTwo.add(Data.managerList.get(i).getEmail());
                dates.add(Data.managerList.get(i).getCurrentdate());
                boolean temp;
                if (Data.managerList.get(i).getStatus().equalsIgnoreCase("Activate")) {
                    temp = true;
                } else {
                    temp = false;
                }
                status.add(temp);
            }
        }
        ScrollPanelInfo.getInstance().addStuffToScrollPanel(types, ids, names, infoOne, infoTwo, dates, status);
    }

    public static void rightPanelLoadTeacher(String search) {
        for (int i = 0; i < Data.teacherList.size(); i++) {
            String name = Data.teacherList.get(i).getFirstName() + " " + Data.teacherList.get(i).getLastName();
            String[] day = Data.teacherList.get(i).getCurrentdate().split("\\,");
            String[] month = day[1].split("\\s");
            if (name.contains(search) || day[0].contains(search)
                    || search.contains(month[0]) || month[1].contains(search)
                    || Data.teacherList.get(i).getEmail().toString().contains(search)
                    || Data.teacherList.get(i).getID().contains(search)) {
                ids.add(Data.teacherList.get(i).getID());
                names.add(name);
                types.add(Data.teacherList.get(i).getUserType());
                infoOne.add("");
                infoTwo.add(Data.teacherList.get(i).getEmail());
                dates.add(Data.teacherList.get(i).getCurrentdate());
                boolean temp;
                if (Data.teacherList.get(i).getStatus().equalsIgnoreCase("Activate")) {
                    temp = true;
                } else {
                    temp = false;
                }
                status.add(temp);
            }
        }
        ScrollPanelInfo.getInstance().addStuffToScrollPanel(types, ids, names, infoOne, infoTwo, dates, status);
    }

    public static void rightPanelLoadStaff(String search) {
        for (int i = 0; i < Data.staffList.size(); i++) {
            String name = Data.staffList.get(i).getFirstName() + " " + Data.staffList.get(i).getLastName();
            String[] day = Data.staffList.get(i).getCurrentdate().split("\\,");
            String[] month = day[1].split("\\s");
            if (name.contains(search) || day[0].contains(search)
                    || search.contains(month[0]) || month[1].contains(search)
                    || Data.staffList.get(i).getEmail().toString().contains(search)
                    || Data.staffList.get(i).getID().contains(search)) {
                ids.add(Data.staffList.get(i).getID());
                names.add(name);
                types.add(Data.staffList.get(i).getUserType());
                infoOne.add("");
                infoTwo.add(Data.staffList.get(i).getEmail());
                dates.add(Data.staffList.get(i).getCurrentdate());
                boolean temp;
                if (Data.staffList.get(i).getStatus().equalsIgnoreCase("Activate")) {
                    temp = true;
                } else {
                    temp = false;
                }
                status.add(temp);
            }
        }
        ScrollPanelInfo.getInstance().addStuffToScrollPanel(types, ids, names, infoOne, infoTwo, dates, status);
    }

    public static void rightPanelLoadStudent(String search) {
        for (int i = 0; i < Data.studentList.size(); i++) {
            String name = Data.studentList.get(i).getFirstName() + " " + Data.studentList.get(i).getLastName();
            String[] day = Data.studentList.get(i).getCurrentdate().split("\\,");
            String[] month = day[1].split("\\s");
            if (name.contains(search) || day[0].contains(search)
                    || search.contains(month[0]) || month[1].contains(search)
                    || Data.studentList.get(i).getEmail().toString().contains(search)
                    || Data.studentList.get(i).getID().contains(search)) {
                ids.add(Data.studentList.get(i).getID());
                names.add(name);
                types.add(Data.studentList.get(i).getUserType());
                infoOne.add("");
                infoTwo.add(Data.studentList.get(i).getEmail());
                dates.add(Data.studentList.get(i).getCurrentdate());
                boolean temp;
                if (Data.studentList.get(i).getStatus().equalsIgnoreCase("Activate")) {
                    temp = true;
                } else {
                    temp = false;
                }
                status.add(temp);
            }
        }
        ScrollPanelInfo.getInstance().addStuffToScrollPanel(types, ids, names, infoOne, infoTwo, dates, status);
    }

    public static void rightPanelLoadClass(String search) {
        for (int i = 0; i < Data.classList.size(); i++) {
            String[] day = Data.classList.get(i).getCurrentDate().split("\\,");
            String[] month = day[1].split("\\s");
            if (Data.classList.get(i).getClassName().contains(search) || Data.classList.get(i).getClassCode().contains(search)
                    || day[0].contains(search) || search.contains(month[0]) || month[1].contains(search)
                    || Data.classList.get(i).getClassType().getName().contains(search)
                    || Data.classList.get(i).getId().contains(search)) {
                ids.add(Data.classList.get(i).getId());
                names.add(Data.classList.get(i).getClassName());
                types.add(Data.classList.get(i).getClassLabel());
                infoOne.add(Data.classList.get(i).getClassCode());
                infoTwo.add(Data.classList.get(i).getClassType().getName());
                dates.add(Data.classList.get(i).getCurrentDate());
                status.add(true);
            }
        }
        ScrollPanelInfo.getInstance().addStuffToScrollPanel(types, ids, names, infoOne, infoTwo, dates, status);
    }
}
