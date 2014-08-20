package View;

import Controller.*;
import Custom.*;
import Model.*;
import View.ControlPanelFormUtilities.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;
import javax.imageio.*;
import javax.swing.*;
import org.jdesktop.xswingx.*;

public class ControlPanelForm extends JFrame implements Observer {

    private static ControlPanelForm unique;
    //Controllers for the form and buttons
    private ControlPanelFormController cpCon = new ControlPanelFormController();
    private DeleteButtonController deleteCon = new DeleteButtonController();
    private ActivateButtonController activateCon = new ActivateButtonController();
    private CopyClassController copyCon = new CopyClassController();
    private ReportChooseController reportChooseCon = new ReportChooseController();
    private LanguageController lanCon = new LanguageController();
    //This font will be used for all components in this class
    private Font font = new Font("Serif", Font.ROMAN_BASELINE, 13);
    //declare menu bar
    private JMenuBar menuBar = new JMenuBar();
    //declare menu
    private JMenu file = new JMenu("File");
    private JMenu help = new JMenu("Help");
    private JMenu language = new JMenu("Language");
    private JMenu hotKeys = new JMenu("Hot Keys");
    //declare menu item
    private JMenuItem exit = new JMenuItem("Exit");
    private JMenuItem logout = new JMenuItem("Log out");
    private JMenuItem english = new JMenuItem("English");
    private JMenuItem vietnamese = new JMenuItem("Vietnamese");
    private JMenuItem userGuide = new JMenuItem("User Guide");
    private JMenuItem exportCSV = new JMenuItem("Export CSV");
    private JMenuItem importCSV = new JMenuItem("Import CSV");
    //
    private JMenuItem addHot = new JMenuItem("Add");
    private JMenuItem deleteHot = new JMenuItem("Delete");
    private JMenuItem activeHot = new JMenuItem("Active");
    private JMenuItem allTimeTableHot = new JMenuItem("TimeTable");
    private JMenuItem copyClassHot = new JMenuItem("Copy Class");
    private JMenuItem reportHot = new JMenuItem("Report");
    //One mother Panel contains all sub panels
    private JPanel mainPanel = new JPanel();
    /*There are three sub panels in Main Panel: 
     * Function panel (Location:Top)
     * Personal Info panel (Location: Left)
     * ScrollPaneInfo panel (Location: Right)
     */
    private JPanel functionPanel = new JPanel();
    private PersonalInfo personalInfo;
    private ScrollPanelInfo scrollInfo;
    //Components of functionPanel 
    private JButton addButton = new JButton("Add");
    private JButton deleteButton = new JButton("Delete");
    private JButton activateButton = new JButton("Activate");
    private JButton timeTableButton = new JButton("TimeTable");
    private JButton copyClassButton = new JButton("Copy Class");
    private JButton reportButton = new JButton("Report");
    private String[] comboBoxContentsForManager = new String[]{"Manager", "Staff", "Class", "Teacher", "Student", "Class Type", "Room"};
    private String[] comboBoxContentsForStaff = new String[]{"Staff", "Class", "Teacher", "Student", "Room"};
    private JComboBox userType = new JComboBox();
    private String identifyUser;
    private JTextField searchField = new JTextField();
    private SuperButton search = new SuperButton("Search", SuperButton.WHITE_TO_BLUE, SuperButton.BLACK_COLOR);
    private BufferedImage myPicture;
    private JLabel picLabel;
    ResourceBundle resources;
    //Observer of this Panel
    private Data dat = new Data();

    public static ControlPanelForm getInstance() {
        if (unique == null) {
            unique = new ControlPanelForm();
        }
        return unique;
    }

    public void initialize() {
        //Set layout
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.BLACK);
        //Initialize components

        if (Data.getCurrentUser() instanceof Manager) {
            identifyUser = "Manager";
        } else {
            identifyUser = "Staff";
            reportButton.setEnabled(false);
        }
        if (identifyUser.equalsIgnoreCase("Manager")) {

            userType = new JComboBox(comboBoxContentsForManager);
        } else if (identifyUser.equalsIgnoreCase("Staff")) {

            userType = new JComboBox(comboBoxContentsForStaff);
        }

        //
        userGuide.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.ALT_MASK));
        importCSV.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.ALT_MASK));
        exportCSV.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, ActionEvent.ALT_MASK));
        logout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.ALT_MASK));
        english.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, ActionEvent.ALT_MASK));
        vietnamese.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, ActionEvent.ALT_MASK));
        //
        addHot.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.CTRL_MASK));
        deleteHot.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.CTRL_MASK));
        activeHot.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, ActionEvent.CTRL_MASK));
        allTimeTableHot.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.CTRL_MASK));
        copyClassHot.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.CTRL_MASK));
        reportHot.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, ActionEvent.CTRL_MASK));


        makeFunctionPanel();
        personalInfo = PersonalInfo.getInstance();
        personalInfo.initialize();
        scrollInfo = ScrollPanelInfo.getInstance();
        scrollInfo.initialize(deleteCon, activateCon, copyCon);

        //Add components
        file.add(importCSV);
        file.add(exportCSV);
        file.add(logout);
        file.add(exit);
        language.add(english);
        language.add(vietnamese);
        help.add(userGuide);
        hotKeys.add(addHot);
        hotKeys.add(deleteHot);
        hotKeys.add(activeHot);
        hotKeys.add(allTimeTableHot);
        hotKeys.add(copyClassHot);
        hotKeys.add(reportHot);
        menuBar.add(file);
        menuBar.add(language);
        menuBar.add(hotKeys);
        menuBar.add(help);
        add(menuBar, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        mainPanel.add(functionPanel);
        mainPanel.add(personalInfo);
        mainPanel.add(scrollInfo);

        //set font
        addButton.setFont(new Font("Serif", Font.PLAIN, 16));
        deleteButton.setFont(new Font("Serif", Font.PLAIN, 16));
        timeTableButton.setFont(new Font("Serif", Font.PLAIN, 16));
        activateButton.setFont(new Font("Serif", Font.PLAIN, 16));
        copyClassButton.setFont(new Font("Serif", Font.PLAIN, 16));
        reportButton.setFont(new Font("Serif", Font.PLAIN, 16));

        //Set UI
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Can't change look and feel", "Invalid PLAF",
                    JOptionPane.ERROR_MESSAGE);
        }

        //Set settings for the ControlPanelForm
        setTitle("Quick Manage");
        setSize(999, 710);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //Set listerners and Observer
        setControllers();
        dat.addObserver(this);
    }

    public void setControllers() {
        addButton.addActionListener(cpCon);
        userType.addActionListener(cpCon);
        timeTableButton.addActionListener(cpCon);
        exit.addActionListener(cpCon);
        logout.addActionListener(cpCon);
        english.addActionListener(lanCon);
        vietnamese.addActionListener(lanCon);
        userGuide.addActionListener(cpCon);
        deleteButton.addActionListener(deleteCon);
        activateButton.addActionListener(activateCon);
        copyClassButton.addActionListener(copyCon);
        reportButton.addActionListener(reportChooseCon);
        search.addActionListener(cpCon);

        //hot keys
        addHot.addActionListener(cpCon);
        deleteHot.addActionListener(deleteCon);
        activeHot.addActionListener(activateCon);
        allTimeTableHot.addActionListener(cpCon);
        copyClassHot.addActionListener(copyCon);
        reportHot.addActionListener(reportChooseCon);
        exportCSV.addActionListener(cpCon);
        importCSV.addActionListener(cpCon);
    }

    public void makeFunctionPanel() {
        //Set layout and background color for function panel
        functionPanel.setLayout(null);
        functionPanel.setBackground(new Color(213, 227, 255)); // Set color for top layout
        //Initialize
        DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        userType.setRenderer(dlcr);//make the text appear center
        PromptSupport.setPrompt("Search...", searchField);//search hint
        URL url = ControlPanelForm.class.getResource("/Images/CP.png");//initialize image
        try {
            myPicture = ImageIO.read(url);
        } catch (IOException ex) {
            Logger.getLogger(ControlPanelForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setOpaque(false);
        //Set location of buttons panel components
        functionPanel.setBounds(0, 0, 993, 75);
        addButton.setBounds(5, 5, 120, 30);
        deleteButton.setBounds(130, 5, 120, 30);
        activateButton.setBounds(255, 5, 120, 30);
        timeTableButton.setBounds(380, 5, 140, 30);
        copyClassButton.setBounds(525, 5, 140, 30);
        reportButton.setBounds(670, 5, 120, 30);
        userType.setBounds(335, 40, 120, 30);
        searchField.setBounds(465, 40, 355, 30);
        search.setBounds(828, 40, 80, 30);
        picLabel.setBounds(900, 10, 100, 60);
        //set font for all components of both panels
        addButton.setFont(font);
        deleteButton.setFont(font);
        activateButton.setFont(font);
        timeTableButton.setFont(font);
        userType.setFont(font);
        searchField.setFont(font);
        search.setFont(font);
        copyClassButton.setFont(font);
        reportButton.setFont(font);

        //Add components to function panel
        functionPanel.add(addButton);
        functionPanel.add(deleteButton);
        functionPanel.add(activateButton);
        functionPanel.add(timeTableButton);
        functionPanel.add(copyClassButton);
        functionPanel.add(reportButton);
        functionPanel.add(userType);
        functionPanel.add(searchField);
        functionPanel.add(search);
        functionPanel.add(picLabel);
    }

    @Override
    public void update(Observable o, Object arg) {
    }

    public String getSearch() {
        return userType.getSelectedItem().toString() + "." + this.searchField.getText();
    }

    public SuperButton getSearchButton() {
        return search;
    }

    public static void setUnique(ControlPanelForm unique) {
        ControlPanelForm.unique = unique;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getActivateButton() {
        return activateButton;
    }

    public JButton getCopyClassButton() {
        return copyClassButton;
    }

    public JButton getReportButton() {
        return reportButton;
    }

    public JComboBox getUserType() {
        return userType;
    }

    public JButton getTimeTableButton() {
        return timeTableButton;
    }

    public JMenu getFile() {
        return file;
    }

    public JMenu getHelp() {
        return help;
    }

    public JMenu getLanguage() {
        return language;
    }

    public JMenuItem getExit() {
        return exit;
    }

    public JMenuItem getLogout() {
        return logout;
    }

    public JMenuItem getEnglish() {
        return english;
    }

    public JMenuItem getVietnamese() {
        return vietnamese;
    }

    public JMenuItem getUserGuide() {
        return userGuide;
    }

    public String[] getComboBoxContentsForManager() {
        return comboBoxContentsForManager;
    }

    public void setComboBoxContentsForManager(String[] comboBoxContentsForManager) {
        this.comboBoxContentsForManager = comboBoxContentsForManager;
    }

    public String[] getComboBoxContentsForStaff() {
        return comboBoxContentsForStaff;
    }

    public void setComboBoxContentsForStaff(String[] comboBoxContentsForStaff) {
        this.comboBoxContentsForStaff = comboBoxContentsForStaff;
    }

    public JMenu getHotKeys() {
        return hotKeys;
    }

    public JMenuItem getAddHot() {
        return addHot;
    }

    public JMenuItem getDeleteHot() {
        return deleteHot;
    }

    public JMenuItem getActiveHot() {
        return activeHot;
    }

    public JMenuItem getAllTimeTableHot() {
        return allTimeTableHot;
    }

    public JMenuItem getCopyClassHot() {
        return copyClassHot;
    }

    public JMenuItem getReportHot() {
        return reportHot;
    }

    public JMenuItem getExportCSV() {
        return exportCSV;
    }

    public JMenuItem getImportCSV() {
        return importCSV;
    }
}
