/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Custom;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thinh
 */
import Custom.MyFont.InternalFont;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Thinh
 */
public final class SuperButton extends JButton {

    //Constant color
    public static final Color RED_COLOR = MyColor.RED_COLOR;
    public static final Color WHITE_COLOR = MyColor.WHITE_COLOR;
    public static final Color LIGHT_GRAY = MyColor.LIGHT_GRAY;
    public static final Color BLACK_COLOR = MyColor.BLACK_COLOR;
    public static final Color GRAY_COLOR = MyColor.GRAY_COLOR;
    //Constant String replicate GradientColor
    public static final String WHITE_TO_BLUE = "whitetoblue";
    public static final String WHITE_TO_BLUE_LIGHT = "whitetobluelight";
    public static final String BLACK_TO_BLACK = "blacktoblack";
    //Constant button type
    public static final int TEXT_TYPE = 1;
    public static final int IMAGE_TYPE = 2;
    //Constant image button type
    public static final String EDIT_BUTTON = "editbutton";
    public static final String ASSIGNENROLL_BUTTON = "assignenrollbutton";
    public static final String TIMETABLE_BUTTON = "timetablebutton";
    public static final String INVOICE_BUTTON = "invoicebutton";
    public static final String CLOSE_BUTTON = "closebutton";
    //Constant Language String
    public static final String VIETNAMESE = "vietnamese";
    public static final String ENGLISH = "english";
    //Properties
    private int currentType = 0;
    private String currentText = "";
    private Font segoeuiFont;
    private JLabel frontText = new JLabel("abc");
    private JLabel frontImage;
    private BufferedImage bufferImage;
    private GradientPaint bg;
    private Color fg;
    private Color currentLineColor;
    private int currentWidth = 0;
    private int currentHeight = 0;
    private int fontSize = 0;
    private MyLis mylis = new MyLis(this);


    /*A constructor that set text, background and foreground of the button
     * with default metro Font
     */
    public SuperButton(String text, String background, Color foreground) {
        super();

        //Settings for JButton
        this.currentType = TEXT_TYPE;
        this.currentText = text;
        this.setLayout(null);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
//        this.setBorderPainted(false);
        this.setName(this.currentText);
        this.setToolTipText(this.currentText);
        this.bg = settingGradientColor(background);
        this.fg = foreground;
        this.currentLineColor = SuperButton.LIGHT_GRAY;

        //Create new JLabel and put text in the middle
        frontText = new JLabel(this.currentText, JLabel.CENTER);
        segoeuiFont = InternalFont.getFont(InternalFont.SEGOEUI, Font.PLAIN, 12);
        frontText.setFont(segoeuiFont);

        //Add to custom button
        this.add(this.frontText);
//        super.setText(text);
        this.addMouseListener(mylis);
    }

    /*A constructor that set Image, background and foreground of the button
     * with default metro Font
     */
    public SuperButton(String buttonType, String text, String background, Color foreground) {
        super();

        //Settings for Image SuperButton
        this.currentType = IMAGE_TYPE;
        this.currentText = text;
//        this.currentLanguage = language;
        this.setLayout(null);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
//        this.setBorderPainted(false);
        this.setName(this.currentText);
        this.setToolTipText(this.currentText);
        this.bg = settingGradientColor(background);
        this.fg = foreground;
        this.currentLineColor = SuperButton.LIGHT_GRAY;

        //Create CENTER image Jlabel
        frontText = new JLabel(this.currentText, JLabel.CENTER);
        segoeuiFont = InternalFont.getFont(InternalFont.SEGOEUI, Font.PLAIN, 12);
        frontText.setFont(segoeuiFont);

        try {
            bufferImage = ImageIO.read(new File(settingImagePath(buttonType)));
        } catch (IOException ex) {
            Logger.getLogger(SuperButton.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Add custom button
        this.add(this.frontText, BorderLayout.SOUTH);
//        super.setText(text);
        this.addMouseListener(mylis);
    }

    //Override setbounds to setbounds text
    @Override
    public void setBounds(int x, int y, int width, int height) {
        if (currentType == TEXT_TYPE) {
            currentWidth = width;
            currentHeight = height;
            fontSize = (width + height) / 7;
            System.out.println(fontSize);
            segoeuiFont = InternalFont.getFont(InternalFont.SEGOEUI, Font.PLAIN, fontSize);
            frontText.setFont(segoeuiFont);

            if (frontText != null) {
                frontText.setBounds(0, 0, currentWidth, currentHeight);
            }
        } else if (currentType == IMAGE_TYPE) {
            currentWidth = width;
            currentHeight = height;
            segoeuiFont = InternalFont.getFont(InternalFont.SEGOEUI, Font.PLAIN, 14);
            frontText.setFont(segoeuiFont);

            if (frontText != null) {
                frontText.setBounds(0, currentHeight - 30, currentWidth-2, 30);
            }
        }

        super.setBounds(x, y, width, height);
    }

    //Override paintCom to repaint when using listener
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (currentType == TEXT_TYPE) {
            //Draw outside lines
            g.setColor(this.currentLineColor);
            g.drawRect(0, 0, currentWidth - 1, currentHeight - 1);

            //Draw the background color

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setPaint(this.bg);
            g2d.fillRect(1, 1, currentWidth - 2, currentHeight - 2);
            frontText.setForeground(this.fg);
        } else if (currentType == IMAGE_TYPE) {
            int middleImage = (currentWidth / 2) - 20;
            int stringSize = this.currentText.length();

            //Draw text position
//            frontText.setBounds((currentWidth / 2) - (stringSize*3),currentHeight - 30 , currentWidth, 30);

            //Draw the background color
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setPaint(this.bg);

            g2d.fillOval(middleImage, 0, 40, 40);
//            g2d.fillRect(0,0,currentWidth-1, currentHeight-1);
            g2d.drawImage(bufferImage, middleImage, 0, this);
            frontText.setForeground(this.fg);
        }

    }

    //This method will return a GradientPaint object base of the GrandientPaint constant type String
    public GradientPaint settingGradientColor(String currentTheme) {
        GradientPaint gp = null;
        if (currentTheme.equals(SuperButton.WHITE_TO_BLUE)) {
            gp = new GradientPaint(0, 0,
                    new Color(255, 255, 255), 300, 50,
                    new Color(102, 153, 255));
        } else if (currentTheme.equals(SuperButton.WHITE_TO_BLUE_LIGHT)) {
            gp = new GradientPaint(0, 0,
                    new Color(255, 255, 255), 300, 50,
                    new Color(255, 235, 235));
        } else if (currentTheme.equals(SuperButton.BLACK_TO_BLACK)) {
            gp = new GradientPaint(0, 0,
                    new Color(0, 0, 0), 300, 50,
                    new Color(0, 0, 0));
        }
        return gp;
    }

    /*
     * This method will get the constant image type and return a string to get image
     */
    public String settingImagePath(String imagepath) {
        String toReturn = null;
        if (imagepath.equals(EDIT_BUTTON)) {
            toReturn = getClass().getResource("/Images/edit-icon2.png").getPath();
        } else if (imagepath.equals(ASSIGNENROLL_BUTTON)) {
            toReturn = getClass().getResource("/Images/assignenroll.png").getPath();
        } else if (imagepath.equals(TIMETABLE_BUTTON)) {
            toReturn = getClass().getResource("/Images/timetable.png").getPath();
        } else if (imagepath.equals(INVOICE_BUTTON)) {
            toReturn = getClass().getResource("/Images/invoice.png").getPath();
        } else if (imagepath.equals(CLOSE_BUTTON)) {
            toReturn = getClass().getResource("/Images/close_icon.png").getPath();
        }

        return toReturn;
    }

    /*======================================================================================= 
     * BUNCH OF GET - SET
     * BUNCH OF GET - SET
     */
    @Override
    public void setText(String text) {
        frontText.setText(text);
//        super.setText(text);

    }

    @Override
    public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }

    public JLabel getFrontText() {
        return frontText;
    }

    public void setFrontText(JLabel over) {
        this.frontText = over;
    }

    public GradientPaint getBg() {
        return bg;
    }

    public void setBg(GradientPaint bg) {
        this.bg = bg;
    }

    public Color getFg() {
        return fg;
    }

    public void setFg(Color fg) {
        this.fg = fg;
    }

    public int getCurrentWidth() {
        return currentWidth;
    }

    public void setCurrentWidth(int currentWidth) {
        this.currentWidth = currentWidth;
    }

    public int getCurrentHeight() {
        return currentHeight;
    }

    public void setCurrentHeight(int currentHeight) {
        this.currentHeight = currentHeight;
    }

    public Color getCurrentLineColor() {
        return currentLineColor;
    }

    public void setCurrentLineColor(Color currentLineColor) {
        this.currentLineColor = currentLineColor;
    }

    public int getCurrentType() {
        return currentType;
    }

    public void setCurrentType(int currentType) {
        this.currentType = currentType;
    }
}

class MyLis implements MouseListener {

    SuperButton mb;

    public MyLis(SuperButton mb) {
        this.mb = mb;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mb.setBg(mb.settingGradientColor(SuperButton.WHITE_TO_BLUE));
        mb.setCurrentLineColor(SuperButton.LIGHT_GRAY);
        mb.setFg(SuperButton.BLACK_COLOR);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (mb.getCurrentType() == mb.TEXT_TYPE) {
            mb.setBg(mb.settingGradientColor(SuperButton.BLACK_TO_BLACK));
            mb.setCurrentLineColor(SuperButton.BLACK_COLOR);
            mb.setFg(SuperButton.WHITE_COLOR);
        } else if (mb.getCurrentType() == mb.IMAGE_TYPE) {
            mb.setFg(SuperButton.GRAY_COLOR);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        mb.setBg(mb.settingGradientColor(SuperButton.WHITE_TO_BLUE_LIGHT));
        mb.setCurrentLineColor(SuperButton.WHITE_COLOR);
        mb.setFg(SuperButton.GRAY_COLOR);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mb.setBg(mb.settingGradientColor(SuperButton.WHITE_TO_BLUE));
        mb.setCurrentLineColor(SuperButton.LIGHT_GRAY);
        mb.setFg(SuperButton.BLACK_COLOR);
    }
}
