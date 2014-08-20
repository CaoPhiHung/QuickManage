package View.ControlPanelFormUtilities;

import Controller.CopyClassController;
import Model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelsListener implements MouseListener {

    private int index;
    private String id;
    private boolean[] pressOrNot;
    private JPanel[] checkBoxPanel;
    private JPanel[] typePanel;
    private JPanel[] infoPanel;
    private JPanel[] datePanel;
    private JPanel[] statusPanel;
    private CopyClassController copyCon;

    public PanelsListener(int i, String id, boolean[] pressOrNot, JPanel[] checkBoxPanel,
            JPanel[] typePanel, JPanel[] infoPanel, JPanel[] datePanel, JPanel[] statusPanel, CopyClassController copyCon) {
        this.index = i;
        this.id = id;
        this.pressOrNot = pressOrNot;
        this.checkBoxPanel = checkBoxPanel;
        this.typePanel = typePanel;
        this.infoPanel = infoPanel;
        this.datePanel = datePanel;
        this.statusPanel = statusPanel;
        this.copyCon = copyCon;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        int reWhitePosition = -1;
        for (int i = 0; i < 50; i++) {
            if (pressOrNot[i] == true) {
                reWhitePosition = i;
            }
        }
        if (pressOrNot[index] == false) {
            pressOrNot[index] = true;
            Color color = new Color(255, 170, 50);
            checkBoxPanel[index].setBackground(color);
            typePanel[index].setBackground(color);
            infoPanel[index].setBackground(color);
            datePanel[index].setBackground(color);
            statusPanel[index].setBackground(color);
            //Change left panel
            String url = "";
            char charID = id.charAt(0);
            switch (charID) {
                case 'M':
                    for (Manager ma : Data.managerList) {

                        if (id.equals(ma.getID())) {
                            String day = (String) ma.getDobDate();
                            String mon = (String) ma.getDobMonth();
                            String yr = (String) ma.getDobYear();
                            String dat = day + "/" + mon + "/" + yr;
                            String gender = (String) ma.getGender();
                            PersonalInfo.getInstance().setAllPeopleLabels(ma.getID(), dat, ma.getFirstName(), ma.getLastName(),
                                    gender, ma.getPhoneNo(), ma.getEmail(), ma.getAddress(), ma.getStatus(), ma.getUserType(), ma.getPhotoLink());
                        }
                    }
                // case 'V':
//                    for (Teacher te : Data.teacherList) {
//
//                        if (id.equals(te.getID())) {
//                            String day = (String) te.getDobDate();
//                            String mon = (String) te.getDobMonth();
//                            String yr = (String) te.getDobYear();
//                            String dat = day + "/" + mon + "/" + yr;
//                            String gender = (String) te.getGender();
//                            PersonalInfo.getInstance().setAllPeopleLabels(te.getID(), dat, te.getFirstName(), te.getLastName(),
//                                    gender, te.getPhoneNo(), te.getEmail(), te.getAddress(), te.getStatus(), te.getUserType(), te.getPhotoLink());
//                        }
//                    }
                case 'T':
                    for (Staff st : Data.staffList) {

                        if (id.equals(st.getID())) {
                            String day = (String) st.getDobDate();
                            String mon = (String) st.getDobMonth();
                            String yr = (String) st.getDobYear();
                            String dat = day + "/" + mon + "/" + yr;
                            String gender = (String) st.getGender();
                            PersonalInfo.getInstance().setAllPeopleLabels(st.getID(), dat, st.getFirstName(), st.getLastName(),
                                    gender, st.getPhoneNo(), st.getEmail(), st.getAddress(), st.getStatus(), st.getUserType(), st.getPhotoLink());
                        }
                    }
//                case 'S':
//                    for (Student st : Data.studentList) {
//
//                        if (id.equals(st.getID())) {
//                            String day = (String) st.getDobDate();
//                            String mon = (String) st.getDobMonth();
//                            String yr = (String) st.getDobYear();
//                            String dat = day + "/" + mon + "/" + yr;
//                            String gender = (String) st.getGender();
//                            PersonalInfo.getInstance().setAllPeopleLabels(st.getID(), dat, st.getFirstName(), st.getLastName(),
//                                    gender, st.getPhoneNo(), st.getEmail(), st.getAddress(), st.getStatus(), st.getUserType(), st.getPhotoLink());
//                        }
//                    }
                case 'C':
                    for (Classes st : Data.classList) {

                        if (id.equals(st.getId())) {
                            PersonalInfo.getInstance().setAllClassLabels(st.getId(), st.getClassCode(), st.getClassType().getName(), st.getClassName(),
                                    "", st.getStartDate(), st.getEndDate(), "", "", st.getClassType().getName(), "", "");
                        }
                    }
                default:
                    for (Student st : Data.studentList) {

                        if (id.equals(st.getID())) {
                            String day = (String) st.getDobDate();
                            String mon = (String) st.getDobMonth();
                            String yr = (String) st.getDobYear();
                            String dat = day + "/" + mon + "/" + yr;
                            String gender = (String) st.getGender();
                            PersonalInfo.getInstance().setAllPeopleLabels(st.getID(), dat, st.getFirstName(), st.getLastName(),
                                    gender, st.getPhoneNo(), st.getEmail(), st.getAddress(), st.getStatus(), st.getUserType(), st.getPhotoLink());
                            
                            Data.setCurrentStudentEnroll(st);
                            System.out.println("Current Student Click ID: " + Data.getCurrentStudentEnroll().getID());
                        }
                    }
                    // teacher
                    for (Teacher te : Data.teacherList) {

                        if (id.equals(te.getID())) {
                            String day = (String) te.getDobDate();
                            String mon = (String) te.getDobMonth();
                            String yr = (String) te.getDobYear();
                            String dat = day + "/" + mon + "/" + yr;
                            String gender = (String) te.getGender();
                            PersonalInfo.getInstance().setAllPeopleLabels(te.getID(), dat, te.getFirstName(), te.getLastName(),
                                    gender, te.getPhoneNo(), te.getEmail(), te.getAddress(), te.getStatus(), te.getUserType(), te.getPhotoLink());
                            
                            Data.setCurrentTeacherAssign(te);
                            System.out.println("Current Teacher Click ID: " + Data.getCurrentTeacherAssign().getID());
                        }
                    }
            }

            if (reWhitePosition >= 0) {
                pressOrNot[reWhitePosition] = false;
                checkBoxPanel[reWhitePosition].setBackground(Color.WHITE);
                typePanel[reWhitePosition].setBackground(Color.WHITE);
                infoPanel[reWhitePosition].setBackground(Color.WHITE);
                datePanel[reWhitePosition].setBackground(Color.WHITE);
                statusPanel[reWhitePosition].setBackground(Color.WHITE);
            }
            copyCon.setId(id);
        } else {
            pressOrNot[index] = false;
            checkBoxPanel[index].setBackground(Color.WHITE);
            typePanel[index].setBackground(Color.WHITE);
            infoPanel[index].setBackground(Color.WHITE);
            datePanel[index].setBackground(Color.WHITE);
            statusPanel[index].setBackground(Color.WHITE);
            PersonalInfo.getInstance().setAllLabelsEmpty();//remove old info of personalPanel
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    public boolean[] getPressOrNot() {
        return pressOrNot;
    }

    public void setPressOrNot(boolean[] pressOrNot) {
        this.pressOrNot = pressOrNot;
    }

    public JPanel[] getTypePanel() {
        return typePanel;
    }

    public void setTypePanel(JPanel[] typePanel) {
        this.typePanel = typePanel;
    }

    public JPanel[] getInfoPanel() {
        return infoPanel;
    }

    public void setInfoPanel(JPanel[] infoPanel) {
        this.infoPanel = infoPanel;
    }

    public JPanel[] getDatePanel() {
        return datePanel;
    }

    public void setDatePanel(JPanel[] datePanel) {
        this.datePanel = datePanel;
    }

    public JPanel[] getStatusPanel() {
        return statusPanel;
    }

    public void setStatusPanel(JPanel[] statusPanel) {
        this.statusPanel = statusPanel;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public JPanel[] getCheckBoxPanel() {
        return checkBoxPanel;
    }

    public void setCheckBoxPanel(JPanel[] checkBoxPanel) {
        this.checkBoxPanel = checkBoxPanel;
    }
    
}
