package TimetableUtilities;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class CoverPanel extends JPanel {

    //Properties
    private String classid = "";
    private int corX = 0;
    private int corY = 0;
    private Color color = Color.BLACK;
    private String classID = "";
    private String className = "";
    private String classCode = "";
    private String stringText = "";
    private String room = "";
    private String day = "";
    private String startTime = "";
    private String endTime = "";

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    private JLabel covertext = new JLabel("haha");
    private int ratio = 0;

    public CoverPanel() {
        setLayout(null);
        color = new Color(generateColor());

        setBackground(color);

        add(covertext);
//        covertext.setHorizontalAlignment(SwingConstants.CENTER);
        setVisible(true);

    }

    /**
     * @return the corX
     */
    public int getCorX() {
        return corX;
    }

    /**
     * @param corX the corX to set
     */
    public void setCorX(int corX) {
        this.corX = corX;
    }

    /**
     * @return the corY
     */
    public int getCorY() {
        return corY;
    }

    /**
     * @param corY the corY to set
     */
    public void setCorY(int corY) {
        this.corY = corY;
    }

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public void setStringText(String stringText) {
        this.stringText = stringText;
        this.covertext.setText(stringText);
        covertext.setBounds(0, 0, 100, 26 * ratio);
    }

    private int generateColor() {
        Random ran = new Random();
        return ran.nextInt() * ran.nextInt() * 30;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public JLabel getCovertext() {
        return covertext;
    }

    public void setCovertext(JLabel covertext) {
        this.covertext = covertext;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }
}
