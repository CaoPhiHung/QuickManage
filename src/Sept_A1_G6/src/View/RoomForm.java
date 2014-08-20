package View;

import Controller.RoomFormController;
import Model.Data;
import java.awt.Font;
import javax.swing.*;

public class RoomForm extends JFrame{
    private static RoomForm unique;
    private String option ="";
    private JLabel idLabel = new JLabel("Room Number: ");
    private JLabel typeLabel = new JLabel("Type: ");
    private JTextField id = new JTextField();
    private JComboBox<String> type = new JComboBox<>(); 
    private JButton add = new JButton("Add");
    private JButton cancel = new JButton("Cancel");
    private RoomFormController roomCon = new RoomFormController();
    
    public static RoomForm getInstance() {
        if (unique == null) {
            unique = new RoomForm();
        }
        return unique;
    }
    
    public static void setUnique(RoomForm unique) {
        RoomForm.unique = unique;
    }
    
    public void initialize() {
        
        setLayout(null);
        Font font = new Font("Comic sans ms", Font.ROMAN_BASELINE, 13);
        
        this.addWindowListener(roomCon);
        add.addActionListener(roomCon);
        cancel.addActionListener(roomCon);
        
        for (int i = 0; i < Data.classTypeList.size(); i++) {
            type.addItem(Data.classTypeList.get(i).getName());
        }
        
        typeLabel.setFont(font);
        type.setFont(font);
        idLabel.setFont(font);
        id.setFont(font);
        add.setFont(font);
        cancel.setFont(font);
        
        idLabel.setBounds(30, 40, 100, 20);
        typeLabel.setBounds(30, 80, 60, 20);
        id.setBounds(120, 40, 100, 28);
        type.setBounds(120, 80, 100, 28);
        add.setBounds(40, 150, 80, 30);
        cancel.setBounds(130, 150, 80, 30);
        
        add(idLabel);
        add(typeLabel);
        add(id);
        add(type);
        add(add);
        add(cancel);
        
        setTitle("Room");
        setSize(260, 250);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        
        //Set UI
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Can't change look and feel", "Invalid PLAF",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public JTextField getNumber() {
        return id;
    }
    
    public String getNumberText() {
        return id.getText().toString();
    }

    public void setNumber(JTextField id) {
        this.id = id;
    }

    public JComboBox<String> getTypeBox() {
        return type;
    }

    public void setType(JComboBox<String> type) {
        this.type = type;
    }
    
}
