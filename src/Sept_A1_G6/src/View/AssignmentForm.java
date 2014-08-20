package View;

import Controller.*;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.*;

public class AssignmentForm extends EnrollmentForm {

    //Properties
    private static AssignmentForm uniqueAss;
    private AssignmentFormController asCon = new AssignmentFormController();
    private ResourceBundle resources;

    public static AssignmentForm getInstanceAss() {
        if (uniqueAss == null) {
            uniqueAss = new AssignmentForm();
        }
        return uniqueAss;
    }

    @Override
    public void initialize(String option) {
        super.initialize("No");
        asCon.addObserver(this);

        //Settings
        enrollNote.setText("Assigned");
        enrolledList.setToolTipText("assignedList");

        //Set listeners
        close.addActionListener(asCon);
        classesList.addListSelectionListener(asCon);
        enrolledList.addListSelectionListener(asCon);

        //Set settings for the ManagerForm
        this.addWindowListener(asCon);
        setTitle("Assignment Form");
        if (LanguageController.getInstance().isClicked()) {
            try {
                resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
                //Add Manager Form
                this.close.setText(resources.getString("Close"));
                this.id.setText(resources.getString("ID"));
                this.name.setText(resources.getString("FirstName"));
                this.enrollNote.setText(resources.getString("assign"));
                setTitle(resources.getString("AssignmentForm"));
            } catch (MissingResourceException mre) {
                System.err.println("MyData.properties not found");
            }
        }


        //Set look and feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Can't change look and feel", "Invalid PLAF",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static AssignmentForm getUniqueAss() {
        return uniqueAss;
    }

    public static void setUniqueAss(AssignmentForm uniqueAss) {
        AssignmentForm.uniqueAss = uniqueAss;
    }

    public AssignmentFormController getAsCon() {
        return asCon;
    }

    public void setAsCon(AssignmentFormController asCon) {
        this.asCon = asCon;
    }
}
