package View;

import Controller.*;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ImportOptions extends ExportOptions {

    private static ImportOptions unique;
    ImportController imCon = new ImportController();
    private ResourceBundle resources;

    //Singleton
    public static ImportOptions getInstance() {
        if (unique == null) {
            unique = new ImportOptions();
        }
        return unique;
    }

    public void importInitialize() {
        super.initialize();
        this.setTitle("Import CSV File");
        this.addButton.removeActionListener(exCon);
        this.cancelButton.removeActionListener(exCon);
        this.addButton.addActionListener(imCon);
        this.cancelButton.addActionListener(imCon);
        this.getInstruction().setText("Choose a type to Import");
        if (LanguageController.getInstance().isClicked()) {
            try {
                resources = ResourceBundle.getBundle(LanguageController.getInstance().getDatabaseName());
                this.addButton.setText(resources.getString("choose"));
                this.cancelButton.setText(resources.getString("Cancel"));
                this.getInstruction().setText(resources.getString("chooseImportFile"));
                this.setTitle(resources.getString("importCSV"));
            } catch (MissingResourceException mre) {
                System.err.println("MyData.properties not found");
            }
        }
    }

    public static void setUnique(ImportOptions unique) {
        ImportOptions.unique = unique;
    }
}
