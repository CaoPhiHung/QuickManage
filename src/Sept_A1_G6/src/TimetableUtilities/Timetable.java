package TimetableUtilities;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Timetable extends JPanel implements Observer {

    //Properties
    private JLayeredPane layer = new JLayeredPane();
    private JPanel overAll = new JPanel();
    private TimetableController timeController = new TimetableController(this);
    private TimetableModel timeModel = new TimetableModel();

    public Timetable() {
    }

    //settings method
    public void settings() {

        //settings
        timeModel.addObserver(this);
        int count = 0;
        int checktime = 0;
        int checkday = 0;
        int tableCellNumber = 0;
        Color color = new Color(176, 196, 222);

        setLayout(null);
        overAll.setLayout(new GridLayout(11*4, 8));
        setSize(new Dimension(800, 600));

//        setBorder(BorderFactory.createLineBorder(Color.red));


        for (int i = 0; i < (11 * 4 * 8); i++) {
            String toadd = "";
            JLabel inside = new JLabel();
            JPanel wrapinside = new JPanel();
            wrapinside.setLayout(null);

            //Add Weekday
            if (i >= 1 && i <= 7) {
                toadd = timeModel.getWeekday()[count];
                count++;
                color = new Color(176, 196, 222);
                inside.setText(toadd);

                //Add weektime
            } else if (i != 0 && i % 8 == 0) {
                checkday = 0;
                toadd = timeModel.getWeektime()[count];
                color = new Color(176, 196, 222);
                count++;
                if (i >= 16) {
                    checktime++;
                }
                inside.setText(toadd);

                //Put color to the rest of field
            } else if (i != 0) {
                color = new Color(240, 248, 255);
                toadd = timeModel.getWeekday()[checkday]
                        + timeModel.getWeektime()[checktime];
                checkday++;
            }
            if (i == 7) {
                count = 0;
            }

            inside.setHorizontalAlignment(SwingConstants.CENTER);

            wrapinside.setBackground(color);
            wrapinside.setBorder(BorderFactory.createLineBorder(Color.gray));

            while (toadd.length() != 16) {
                toadd = toadd + "+";
            }
            wrapinside.setName(toadd);

            wrapinside.add(inside);
            wrapinside.setBounds(0, 0, 100, 100);
            inside.setBounds(0, -4, 100, 20);

            timeModel.getTableCell()[tableCellNumber] = wrapinside;
            tableCellNumber++;
            overAll.add(wrapinside);
        }
        
        layer.add(overAll, JLayeredPane.DEFAULT_LAYER);
        overAll.setBounds(0, 0, 800, 600);
        layer.setBounds(0, 0, 800, 600);
        this.add(layer);

    }

    @Override
    public void update(Observable o, Object arg) {

        layer.removeAll();
        layer.validate();
        layer.repaint();

        createDisplayTable();
    }

    public void createDisplayTable() {

        for (int i = 0; i < timeModel.getTableCell().length; i++) {
            overAll.add(timeModel.getTableCell()[i]);
        }

        layer.add(overAll, JLayeredPane.DEFAULT_LAYER);

        for (int i = 0; i < timeModel.getChosenClass().size(); i++) {
            overAll.add(timeModel.getChosenClass().get(i));
            layer.add(timeModel.getChosenClass().get(i), JLayeredPane.POPUP_LAYER);
            timeModel.getChosenClass().get(i).setBounds(timeModel.getChosenClass().get(i).getCorX(),
                    timeModel.getChosenClass().get(i).getCorY(), 100, 13 * timeModel.getChosenClass().get(i).getRatio());
        }

        this.add(layer);

    }

    public TimetableController getTimeController() {
        return timeController;
    }

    public TimetableModel getTimeModel() {
        return timeModel;
    }

    public void setPanelListener() {
        for (CoverPanel item : timeModel.getChosenClass()) {
            item.addMouseListener(timeController);
        }
    }
}
