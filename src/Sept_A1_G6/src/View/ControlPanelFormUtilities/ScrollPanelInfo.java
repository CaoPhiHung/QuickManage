package View.ControlPanelFormUtilities;

import Controller.*;
import Custom.SuperButton;
import Model.Data;
import View.ClassTypeForm;
import View.ControlPanelForm;
import View.RoomForm;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.util.*;
import java.util.logging.*;
import javax.imageio.*;
import javax.swing.*;

public class ScrollPanelInfo extends JPanel implements Observer {

    private static ScrollPanelInfo unique;
    //This font will be used for all components in this class
    private Font font = new Font("Serif", Font.ROMAN_BASELINE, 13);
    //Right panel's components
    private JScrollPane scroll = new JScrollPane();
    private JPanel scrollPanel = new JPanel();
    private JPanel[] infoRow = new JPanel[100];
    private JPanel[] checkBoxPanels = new JPanel[100];
    private JPanel[] typePanels = new JPanel[100];
    private JPanel[] infoPanels = new JPanel[100];
    private JPanel[] datePanels = new JPanel[100];
    private JPanel[] statusPanels = new JPanel[100];
    private boolean[] pressOrNot = new boolean[100];
    private PanelsListener listener;
    private DeleteButtonController deleteCon;
    private ActivateButtonController activateCon;
    private CopyClassController copyCon;
    ResourceBundle resources;

    public static ScrollPanelInfo getInstance() {
        if (unique == null) {
            unique = new ScrollPanelInfo();
        }
        return unique;
    }

    public static void setUnique(ScrollPanelInfo unique) {
        ScrollPanelInfo.unique = unique;
    }

    public void initialize(DeleteButtonController deleteCon, ActivateButtonController activateCon, CopyClassController copyCon) {
        this.deleteCon = deleteCon;
        this.activateCon = activateCon;
        this.copyCon = copyCon;
        //set right panel style
        setLayout(null);
        setBackground(Color.WHITE);
        setBounds(328, 77, 665, 595);
        //set scroll settings
        scroll.setBounds(-2, -2, 668, 595);//Move scroll to hide the white line of ScrollPaneInfo panel
        scroll.setViewportView(scrollPanel);
        scroll.getVerticalScrollBar().setUnitIncrement(20);
        scrollPanel.setLayout(new GridLayout(0, 1, 0, 0));
        //Add ScrollPane to ScrollPaneInfo Panel
        add(scroll);
    }

    public void removeScrollPane() {
        scrollPanel.removeAll();
    }

    public void addStuffToScrollPanel(ArrayList<String> types, ArrayList<String> ids, ArrayList<String> names, ArrayList<String> infoOne,
            ArrayList<String> infoTwo, ArrayList<String> dates, ArrayList<Boolean> status) {
        //Remove everything start adding new stuff
        deleteCon.removeOldIDs();//remove old database in the buttons else it would accidentally delete old data
        activateCon.removeOldIDs();
        copyCon.removeOldIDs();
        PersonalInfo.getInstance().setAllLabelsEmpty();
        scrollPanel.removeAll();
        infoRow = new JPanel[100];
        pressOrNot = new boolean[100];
        checkBoxPanels = new JPanel[100];
        typePanels = new JPanel[100];
        infoPanels = new JPanel[100];
        datePanels = new JPanel[100];
        statusPanels = new JPanel[100];
        //Add many rows of info
        for (int i = 0; i < 50; i++) {
            //Create an info row Panel
            infoRow[i] = new JPanel();
            infoRow[i].setLayout(null);
            infoRow[i].setPreferredSize(new Dimension(0, 120));
            if (i >= types.size()) {//make the remaining panels become white
                infoRow[i].setBackground(Color.WHITE);
                if (i == types.size()) {//make the last panel top border black for the scroll to look fashionable
                    infoRow[i].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
                }
                scrollPanel.add(infoRow[i]);
            } else {
                //CheckBox panel
                checkBoxPanels[i] = newCheckBoxPanel(ids.get(i));
                checkBoxPanels[i].setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
                //Type Panel
                typePanels[i] = newTypePanel(types.get(i));
                typePanels[i].setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
                //Info Panel
                infoPanels[i] = newInfoPanel(ids.get(i), names.get(i), infoOne.get(i), infoTwo.get(i), types.get(i));
                infoPanels[i].setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
                //Date Panel
                datePanels[i] = newDatePanel(dates.get(i));
                datePanels[i].setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
                //Status Panel
                statusPanels[i] = newStatusPanel(status.get(i));
                statusPanels[i].setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));

                //Set listener for panels
                //Each panel listener has to have its own database cause once a panel is clicked
                //It does not simply change the color of itself. It change the color of others too
                //So pass all the addresses to each listener we can change the precise panel
                //and because of the same addresses, it will link back and change the database at
                //this class too
                listener = new PanelsListener(i, ids.get(i), pressOrNot, checkBoxPanels,
                        typePanels, infoPanels, datePanels, statusPanels, copyCon);
                checkBoxPanels[i].addMouseListener(listener);
                typePanels[i].addMouseListener(listener);
                infoPanels[i].addMouseListener(listener);
                datePanels[i].addMouseListener(listener);
                statusPanels[i].addMouseListener(listener);
                //Add five panels to an manager row panel
                infoRow[i].add(checkBoxPanels[i]);
                infoRow[i].add(infoPanels[i]);
                infoRow[i].add(datePanels[i]);
                infoRow[i].add(typePanels[i]);
                infoRow[i].add(statusPanels[i]);
                //Add a manager rowpanel to ScrollPane
                scrollPanel.add(infoRow[i]);
            }
        }
        //Update Components and UI
        revalidate();
        repaint();
    }

    public void addStuffToClassTypeScrollPanel(ArrayList<String> names, ArrayList<String> fees,
            ArrayList<String> types, ArrayList<String> remarks, ArrayList<String> lessonPerWeeks) {
        //Remove everything start adding new stuff
        deleteCon.removeOldIDs();//remove old database in the buttons else it would accidentally delete old data
        PersonalInfo.getInstance().setAllLabelsEmpty();
        scrollPanel.removeAll();
        infoRow = new JPanel[100];
        //Add many rows of info
        for (int i = 0; i < 50; i++) {
            //Create an info row Panel
            infoRow[i] = new JPanel();
            infoRow[i].setLayout(null);
            infoRow[i].setPreferredSize(new Dimension(0, 140));
            if (i >= names.size()) {//make the remaining panels become white
                infoRow[i].setBackground(Color.WHITE);
                if (i == names.size()) {//make the last panel top border black for the scroll to look fashionable
                    infoRow[i].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
                }
                scrollPanel.add(infoRow[i]);
            } else {
                JCheckBox checkBox = new JCheckBox();
                checkBox.setBounds(10, 60, 20, 20);
                checkBox.addItemListener(new CheckBoxListener(names.get(i), deleteCon, "Class Type"));

                BufferedImage typeImage;
                JLabel picLabel = new JLabel();
                URL url = ScrollPanelInfo.class.getResource("/Images/Class.png");
                try {
                    typeImage = ImageIO.read(url);
                    picLabel = new JLabel(new ImageIcon(typeImage));
                    picLabel.setBounds(40, 20, 80, 70);
                } catch (IOException ex) {
                    Logger.getLogger(ControlPanelForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                picLabel.setOpaque(false);
                JLabel label = new JLabel("Class Type");
                label.setBounds(50, 100, 70, 20);
                label.setFont(font);

                Font size = new Font("Comic sans ms", 12, 12);

                JLabel name = new JLabel("Class type name: " + names.get(i));
                name.setBounds(160, 10, 200, 20);
                name.setFont(font);

                JLabel fee = new JLabel("Fee per lesson: " + fees.get(i).toString());
                fee.setBounds(160, 35, 200, 20);
                fee.setFont(font);

                JLabel type = new JLabel("Type: " + types.get(i).toString());
                type.setBounds(160, 60, 200, 20);
                type.setFont(font);

                JLabel remark = new JLabel("Remarks: " + remarks.get(i).toString());
                remark.setBounds(160, 85, 200, 20);
                remark.setFont(font);

                JLabel lessonPerWeek = new JLabel("Lesson per week: " + lessonPerWeeks.get(i).toString());
                lessonPerWeek.setBounds(160, 110, 200, 20);
                lessonPerWeek.setFont(font);

                SuperButton editButton = new SuperButton(SuperButton.EDIT_BUTTON, "Edit", SuperButton.WHITE_TO_BLUE, SuperButton.BLACK_COLOR);
                EditListener editListener = new EditListener(names.get(i));
                editButton.addActionListener(editListener);
                editButton.setBounds(550, 40, 60, 60);

                //Add a manager rowpanel to ScrollPane
                infoRow[i].add(checkBox);
                infoRow[i].add(picLabel);
                infoRow[i].add(label);
                infoRow[i].add(name);
                infoRow[i].add(fee);
                infoRow[i].add(type);
                infoRow[i].add(remark);
                infoRow[i].add(lessonPerWeek);
                infoRow[i].add(editButton);
                infoRow[i].setBackground(Color.WHITE);
                infoRow[i].setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
                scrollPanel.add(infoRow[i]);
            }
        }
        //Update Components and UI
        revalidate();
        repaint();
    }

    public void addStuffToRoomScrollPanel(ArrayList<String> numbers, ArrayList<String> types,
            ArrayList<Double> occupiedMinutes) {
        //Remove everything start adding new stuff
        deleteCon.removeOldIDs();//remove old database in the buttons else it would accidentally delete old data
        PersonalInfo.getInstance().setAllLabelsEmpty();
        scrollPanel.removeAll();
        infoRow = new JPanel[100];
        //Add many rows of info
        for (int i = 0; i < 50; i++) {
            //Create an info row Panel
            infoRow[i] = new JPanel();
            infoRow[i].setLayout(null);
            infoRow[i].setPreferredSize(new Dimension(0, 120));
            if (i >= numbers.size()) {//make the remaining panels become white
                infoRow[i].setBackground(Color.WHITE);
                if (i == numbers.size()) {//make the last panel top border black for the scroll to look fashionable
                    infoRow[i].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
                }
                scrollPanel.add(infoRow[i]);
            } else {
                JCheckBox checkBox = new JCheckBox();
                checkBox.setBounds(10, 50, 20, 20);
                checkBox.addItemListener(new CheckBoxListener(numbers.get(i).toString(), deleteCon, "Room"));

                BufferedImage typeImage;
                JLabel picLabel = new JLabel();
                URL url = ScrollPanelInfo.class.getResource("/Images/Class.png");
                try {
                    typeImage = ImageIO.read(url);
                    picLabel = new JLabel(new ImageIcon(typeImage));
                    picLabel.setBounds(40, 15, 80, 70);
                } catch (IOException ex) {
                    Logger.getLogger(ControlPanelForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                picLabel.setOpaque(false);
                JLabel label = new JLabel("Room");
                label.setBounds(60, 95, 70, 20);
                label.setFont(font);

                Font size = new Font("Comic sans ms", 12, 12);

                JLabel number = new JLabel("Room Number: " + numbers.get(i));
                number.setBounds(160, 20, 200, 20);
                number.setFont(font);

                JLabel type = new JLabel("Type: " + types.get(i).toString());
                type.setBounds(160, 45, 200, 20);
                type.setFont(font);

                JLabel occupied = new JLabel("Occupied minutes: " + occupiedMinutes.get(i));
                occupied.setBounds(160, 70, 200, 20);
                occupied.setFont(font);

                SuperButton editButton = new SuperButton(SuperButton.EDIT_BUTTON, "Edit", SuperButton.WHITE_TO_BLUE, SuperButton.BLACK_COLOR);
                EditListenerRoom editListener = new EditListenerRoom(numbers.get(i));
                editButton.addActionListener(editListener);
                editButton.setBounds(550, 35, 60, 60);

                //Add a manager rowpanel to ScrollPane
                infoRow[i].add(checkBox);
                infoRow[i].add(picLabel);
                infoRow[i].add(label);
                infoRow[i].add(number);
                infoRow[i].add(type);
                infoRow[i].add(occupied);
                infoRow[i].add(editButton);
                infoRow[i].setBackground(Color.WHITE);
                infoRow[i].setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
                scrollPanel.add(infoRow[i]);
            }
        }
        //Update Components and UI
        revalidate();
        repaint();
    }

    public class EditListener implements ActionListener {

        private String name;

        public EditListener(String name) {
            this.name = name;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Data data = new Data();
            try {
                ControlPanelForm.getInstance().setEnabled(false);
                ClassTypeForm.getInstance().setOption("edit");
                data.editClassType(this.name);
            } catch (Exception ex) {
                Logger.getLogger(EditButtonListener.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public class EditListenerRoom implements ActionListener {

        private String number;

        public EditListenerRoom(String number) {
            this.number = number;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Data data = new Data();
            try {
                ControlPanelForm.getInstance().setEnabled(false);
                RoomForm.getInstance().setOption("edit");
                data.editRoom(this.number);
            } catch (Exception ex) {
                Logger.getLogger(EditButtonListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public JPanel newCheckBoxPanel(String id) {
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(null);
        checkBoxPanel.setBounds(0, 0, 40, 120);
        checkBoxPanel.setBackground(Color.WHITE);
        //Create a small check box and add it to CheckBox panel
        JCheckBox checkBox = new JCheckBox();
        checkBox.setBounds(10, 50, 20, 20);
        checkBox.addItemListener(new CheckBoxListener(id, deleteCon, activateCon));
        checkBoxPanel.add(checkBox);
        return checkBoxPanel;
    }

    public JPanel newTypePanel(String type) {
        JPanel typePanel = new JPanel();
        typePanel.setBounds(40, 0, 90, 120);
        typePanel.setLayout(null);
        typePanel.setBackground(Color.WHITE);
        //picture
        BufferedImage typeImage;
        JLabel picLabel = new JLabel();
        if (type.equals("Class")) {
            URL url = ScrollPanelInfo.class.getResource("/Images/Class.png");
            try {
                typeImage = ImageIO.read(url);
                picLabel = new JLabel(new ImageIcon(typeImage));
                picLabel.setBounds(3, 5, 80, 70);
            } catch (IOException ex) {
                Logger.getLogger(ControlPanelForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (type.equals("Teacher")) {
            URL url = ScrollPanelInfo.class.getResource("/Images/teacher.png");
            try {
                typeImage = ImageIO.read(url);
                picLabel = new JLabel(new ImageIcon(typeImage));
                picLabel.setBounds(3, 5, 80, 70);
            } catch (IOException ex) {
                Logger.getLogger(ControlPanelForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (type.equals("Staff")) {
            URL url = ScrollPanelInfo.class.getResource("/Images/staff.png");
            try {
                typeImage = ImageIO.read(url);
                picLabel = new JLabel(new ImageIcon(typeImage));
                picLabel.setBounds(3, 5, 80, 70);
            } catch (IOException ex) {
                Logger.getLogger(ControlPanelForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (type.equals("Manager")) {
            URL url = ScrollPanelInfo.class.getResource("/Images/manager.png");
            try {
                typeImage = ImageIO.read(url);
                picLabel = new JLabel(new ImageIcon(typeImage));
                picLabel.setBounds(3, 5, 80, 70);
            } catch (IOException ex) {
                Logger.getLogger(ControlPanelForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            URL url = ScrollPanelInfo.class.getResource("/Images/student.png");
            try {
                typeImage = ImageIO.read(url);
                picLabel = new JLabel(new ImageIcon(typeImage));
                picLabel.setBounds(-17, 5, 120, 70);
            } catch (IOException ex) {
                Logger.getLogger(ControlPanelForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        picLabel.setOpaque(false);
        typePanel.add(picLabel);
        //Label
        JLabel typeLabel = new JLabel(type);
        typeLabel.setFont(font);
        if (type.equals("Staff") || type.equals("Class")) {
            typeLabel.setBounds(28, 90, 80, 20);
        } else {
            typeLabel.setBounds(20, 90, 80, 20);
        }
        typePanel.add(typeLabel);
        return typePanel;
    }

    @SuppressWarnings("empty-statement")
    public JPanel newInfoPanel(String id, String name, String infoOne, String infoTwo, String type) {
        JPanel infoPanel = new JPanel();
        infoPanel.setBounds(130, 0, 368, 120);
        infoPanel.setLayout(null);
        infoPanel.setBackground(Color.WHITE);
        resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
        //Labels
        JLabel idLabel = new JLabel(resources.getString("ID") + " " + id);
        idLabel.setFont(font);
        idLabel.setBounds(16, 10, 200, 20);

        if (type.equals("Class")) {
            //Class Name
            JLabel nameLabel = new JLabel(resources.getString("className") + " " + name);
            nameLabel.setFont(font);
            nameLabel.setBounds(16, 37, 300, 20);
            //Class Code
            JLabel classCodeLabel = new JLabel(resources.getString("classCode") + " " + infoOne);
            classCodeLabel.setFont(font);
            classCodeLabel.setBounds(16, 64, 400, 20);
            //Class type
            JLabel classType = new JLabel(resources.getString("classType") + " " + infoTwo);
            classType.setFont(font);
            classType.setBounds(16, 91, 300, 20);

            infoPanel.add(idLabel);
            infoPanel.add(nameLabel);
            infoPanel.add(classCodeLabel);
            infoPanel.add(classType);
        } else {
            //Name
            JLabel nameLabel = new JLabel();

            switch (type) {
                case "Student":
                    nameLabel = new JLabel(resources.getString("StudentName") + ": " + name);
                    break;
                case "Teacher":
                    nameLabel = new JLabel(resources.getString("TeacherName") + ": " + name);
                    break;
                case "Manager":
                    nameLabel = new JLabel(resources.getString("ManagerName") + ": " + name);
                    break;
                case "Staff":
                    nameLabel = new JLabel(resources.getString("StaffName") + ": " + name);
                    break;
            }
            nameLabel.setFont(font);
            nameLabel.setBounds(16, 37, 300, 20);

            //Class 
            JLabel email = new JLabel(resources.getString("Email") + " " + infoTwo);
            email.setFont(font);
            if (!infoOne.equals("")) {
                JLabel classLabel = new JLabel(resources.getString("Class") + ": " + infoOne);
                classLabel.setFont(font);
                classLabel.setBounds(16, 64, 400, 20);
                infoPanel.add(classLabel);
                //Emails
                email.setBounds(16, 91, 300, 20);
            } else {
                //emails
                email.setBounds(16, 64, 300, 20);
            }

            infoPanel.add(idLabel);
            infoPanel.add(nameLabel);
            infoPanel.add(email);
        }
        return infoPanel;
    }

    public JPanel newDatePanel(String date) {
        JPanel datePanel = new JPanel();
        datePanel.setBounds(498, 0, 90, 120);
        datePanel.setLayout(null);
        datePanel.setBackground(Color.WHITE);
        //Label
        JLabel dateLabel = new JLabel(date);
        dateLabel.setFont(font);
        dateLabel.setBounds(9, 45, 100, 30);
        datePanel.add(dateLabel);
        return datePanel;
    }

    public JPanel newStatusPanel(boolean status) {
        JPanel statusPanel = new JPanel();
        statusPanel.setBounds(587, 0, 60, 120);
        statusPanel.setLayout(null);
        statusPanel.setBackground(Color.WHITE);
        //picture
        BufferedImage statusImage;
        JLabel picLabel = new JLabel();
        if (status == true) {
            URL url = ScrollPanelInfo.class.getResource("/Images/Green.png");
            try {
                statusImage = ImageIO.read(url);
                picLabel = new JLabel(new ImageIcon(statusImage));
            } catch (IOException ex) {
                Logger.getLogger(ControlPanelForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            URL url = ScrollPanelInfo.class.getResource("/Images/Red.png");
            try {
                statusImage = ImageIO.read(url);
                picLabel = new JLabel(new ImageIcon(statusImage));
            } catch (IOException ex) {
                Logger.getLogger(ControlPanelForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        picLabel.setBounds(9, 37, 43, 43);
        picLabel.setOpaque(false);
        statusPanel.add(picLabel);
        return statusPanel;
    }

    @Override
    public void update(Observable o, Object arg) {
    }

    public PanelsListener getListener() {
        return listener;
    }

    public void setListener(PanelsListener listener) {
        this.listener = listener;
    }
}
