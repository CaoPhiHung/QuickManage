package View;

import Controller.*;
import Custom.MyFont.InternalFont;
import Custom.SuperButton;
import Controller.LanguageController;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Logger;
import javax.swing.*;

public class LoginForm extends JFrame {

    //Propeties
    private static LoginForm unique;
    private LanguageController lanCon = new LanguageController();
    private JPanel overAll = new JPanel();
    private JPanel mainPanel = new JPanel();
    private JMenuBar menuBar = new JMenuBar();
    private JMenuItem close = new JMenuItem("Close", KeyEvent.VK_F2);
    private JMenu manage = new JMenu("Manage");
    private JMenu language = new JMenu("Language");
    private JMenuItem english = new JMenuItem("English");
    private JMenuItem vietnamese = new JMenuItem("Vietnamese");
    private JLabel quickmanage = new JLabel("Quick Manage");
    private JLabel usernameL = new JLabel("Username:");
    private JLabel passwordL = new JLabel("Password:");
    private JTextField usernameF = new JTextField(80);
    private JLabel backgroundImg = new JLabel() {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint gp = new GradientPaint(0, 0,
                    new Color(150, 189, 228), 450, 450,
                    new Color(150, 228, 228));
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            super.paintComponent(g);
        }
    };
    private JPasswordField passwordF = new JPasswordField(80);
    private SuperButton login = new SuperButton("Log in", SuperButton.WHITE_TO_BLUE, SuperButton.BLACK_COLOR);
    private SuperButton exit = new SuperButton("Exit", SuperButton.WHITE_TO_BLUE, SuperButton.BLACK_COLOR);
    private LoginFormController loginController = new LoginFormController();

    //Singleton
    public static LoginForm getInstance() {
        if (unique == null) {
            unique = new LoginForm();
        }
        return unique;
    }

    //Initialize
    public void initialize() {

        //Settings
        createSettings();

        //Set Listener
        createListener();

        //Adding to JFrame and Components
        createAdding();

        //JFrame's settings 
        setTitle("Quick Manage - Login");
        setSize(450, 280);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        this.addWindowListener(loginController);
        

        //Set look and feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Can't change look and feel", "Invalid PLAF",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void createSettings() {
        overAll.setLayout(new BorderLayout());
        mainPanel.setLayout(null);
        manage.setMnemonic(KeyEvent.VK_M);
        close.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F2, ActionEvent.SHIFT_MASK));
        usernameF.setFont(new Font("Serif", Font.ITALIC, 16));
        passwordF.setFont(new Font("Serif", Font.PLAIN, 16));
        usernameL.setFont(new Font("Serif", Font.PLAIN, 16));
        passwordL.setFont(new Font("Serif", Font.PLAIN, 16));
        //usernameL.setFont(InternalFont.getFont(InternalFont.METRO, Font.PLAIN, 16));
        //passwordL.setFont(InternalFont.getFont(InternalFont.METRO, Font.PLAIN, 16));
//        quickmanage.setFont(new Font("Comic sans ms", Font.PLAIN, 30));
        quickmanage.setFont(InternalFont.getFont(InternalFont.METRO, Font.PLAIN, 38));
    }

    public void createAdding() {
        add(overAll);

        menuBar.add(manage);
        menuBar.add(language);
        manage.add(close);
        language.add(english);
        language.add(vietnamese);

        mainPanel.add(usernameL);
        mainPanel.add(passwordL);
        mainPanel.add(usernameF);
        mainPanel.add(passwordF);
        mainPanel.add(quickmanage);
        mainPanel.add(login);
        mainPanel.add(exit);
        mainPanel.add(backgroundImg);

        overAll.add(menuBar, BorderLayout.NORTH);
        overAll.add(mainPanel, BorderLayout.CENTER);

        quickmanage.setBounds(110, 5, 400, 60);
        usernameL.setBounds(40, 80, 100, 30);
        usernameF.setBounds(140, 80, 260, 30);
        passwordL.setBounds(40, 140, 100, 30);

        passwordF.setBounds(140, 140, 260, 30);
        login.setBounds(320, 190, 80, 30);

        exit.setBounds(230, 190, 80, 30);

        backgroundImg.setBounds(0, 0, 484, 230);
    }

    public void createListener() {
        login.addMouseListener(loginController);
        close.addActionListener(loginController);
        this.addKeyListener(loginController);
        usernameF.addKeyListener(loginController);
        passwordF.addKeyListener(loginController);
        exit.addMouseListener(loginController);
        english.addActionListener(lanCon);
        vietnamese.addActionListener(lanCon);
    }

    public static void setUnique(LoginForm unique) {
        LoginForm.unique = unique;
    }

    public JTextField getUsernameF() {
        return usernameF;
    }

    public JPasswordField getPasswordF() {
        return passwordF;
    }

    public SuperButton getLogin() {
        return login;
    }

    public SuperButton getExit() {
        return exit;
    }

    public JLabel getUsernameL() {
        return usernameL;
    }

    public JLabel getPasswordL() {
        return passwordL;
    }

    public JMenuItem getClose() {
        return close;
    }

    public JMenu getManage() {
        return manage;
    }

    public JMenu getLanguage() {
        return language;
    }

    public JMenuItem getEnglish() {
        return english;
    }

    public JMenuItem getVietnamese() {
        return vietnamese;
    }
    
    
}
