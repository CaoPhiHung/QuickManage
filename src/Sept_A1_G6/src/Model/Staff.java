package Model;

public class Staff extends User {

    private String username;
    private char[] password;
    private char[] retype;

    public Staff(String ID, String firstName, String lastName, String dobDate, String dobMonth,
            String dobYear, String gender, String phoneNoCode, String phoneNo, String homeNoCode,
            String homeNo, String email, String address, String status, String description, String type,
            String currentdate, String username, char[] password, char[] retype, String imageLink) {
        super(ID, firstName, lastName, dobDate, dobMonth, dobYear, gender, phoneNo, phoneNoCode,
                homeNo, homeNoCode, email, address, status, description, type, currentdate, imageLink);
        this.username = username;
        this.password = password;
        this.retype = retype;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getTypingPassword() {
        return password;
    }

    public void setTypingPassword(char[] password) {
        this.password = password;
    }

    public char[] getRetype() {
        return retype;
    }

    public void setRetype(char[] retype) {
        this.retype = retype;
    }
}
