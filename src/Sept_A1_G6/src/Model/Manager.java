package Model;

public class Manager extends Staff {

    public Manager(String ID, String firstName, String lastName, String dobDate, String dobMonth,
            String dobYear, String gender, String phoneNoCode, String phoneNo, String homeNoCode,
            String homeNo, String email, String address, String status, String description, String type,
            String currentdate, String username, char[] password, char[] retype, String imageLink) {
        super(ID, firstName, lastName, dobDate, dobMonth, dobYear, gender, phoneNo, phoneNoCode,
                homeNo, homeNoCode, email, address, status, description, type, currentdate, username, password, retype, imageLink);

    }
}
