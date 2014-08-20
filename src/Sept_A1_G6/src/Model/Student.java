package Model;

import java.util.ArrayList;

public class Student extends User {

    ArrayList<Classes> classes = new ArrayList<>();
    ArrayList<Invoice> invoices = new ArrayList<>();

    private String contactName;
    private String contactPhoneCode;
    private String contactPhone;
    private String contactEmail;
    private String contactAddress;
    private String contactRelationShip;

    public Student() {
    };
    
    public Student(String ID, String firstName, String lastName, String dobDate, String dobMonth,
            String dobYear, String gender, String phoneNoCode, String phoneNo, String homeNoCode,
            String homeNo, String email, String address, String status, String description, String type,
            String currentdate, ArrayList<Classes> classes, String imageLink, String Name, String phoneCode, String Phone, String Email, String Address, String RelationShip) {
        super(ID, firstName, lastName, dobDate, dobMonth, dobYear, gender, phoneNo, phoneNoCode,
                homeNo, homeNoCode, email, address, status, description, type, currentdate, imageLink);

        this.classes = classes;
        this.contactName = Name;
        this.contactPhoneCode = phoneCode;
        this.contactPhone = Phone;
        this.contactEmail = Email;
        this.contactAddress = Address;
        this.contactRelationShip = RelationShip;
    }

    /* this method adds a class into classes arraylist 
     * parameter is a class object
     */
    public void addClass(Classes cl) {
        classes.add(cl);
    }

    public ArrayList<Classes> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Classes> classes) {
        this.classes = classes;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhoneCode() {
        return contactPhoneCode;
    }

    public void setContactPhoneCode(String contactPhoneCode) {
        this.contactPhoneCode = contactPhoneCode;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getContactRelationShip() {
        return contactRelationShip;
    }

    public void setContactRelationShip(String contactRelationShip) {
        this.contactRelationShip = contactRelationShip;
    }

    public ArrayList<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(ArrayList<Invoice> invoices) {
        this.invoices = invoices;
    }

}
