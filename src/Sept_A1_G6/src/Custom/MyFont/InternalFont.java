/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Custom.MyFont;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thinh
 */
public class InternalFont {

    //Constant font name
    public static final String METRO = "Metro.ttf";
    public static final String SEGOEUI = "segoeui.ttf";
    
    //Constant font size
    public static final int METRO_SUIT_SIZE = 15;

    public static Font getFont(String fontName, int style, int size) {
        Font font = null;

        try {
            InputStream fontStream = InternalFont.class.getResourceAsStream("/Custom/MyFont/" + fontName);
            font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            font = font.deriveFont(style, size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            fontStream.close();
        } catch (FontFormatException ex) {
            Logger.getLogger(InternalFont.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InternalFont.class.getName()).log(Level.SEVERE, null, ex);
        }

        return font;
    }
}
