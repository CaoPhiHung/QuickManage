package Model;

import java.util.ArrayList;

public class Teacher extends User {

    ArrayList<String> skillList = new ArrayList<>();
    ArrayList<String> hourlyRate = new ArrayList<>();
    ArrayList<Classes> classes = new ArrayList<>();
    private String currentPayRated; 

    public Teacher(String ID, String firstName, String lastName, Object dobDate, Object dobMonth,
            Object dobYear, Object gender, Object phoneNoCode, String phoneNo, Object homeNoCode,
            String homeNo, String email, String address, String status, String description, String type,
            String currentdate, ArrayList<String> skillList, ArrayList<Classes> classes, ArrayList<String> hourlyRate,String imageLink) {
        super(ID, firstName, lastName, dobDate, dobMonth, dobYear, gender, phoneNo, phoneNoCode,
                homeNo, homeNoCode, email, address, status, description, type, currentdate, imageLink);

        this.skillList = skillList;
        this.classes = classes;
        this.hourlyRate = hourlyRate;
    }

    public ArrayList<String> getSkillList() {
        return skillList;
    }

    public void setSkillList(ArrayList<String> skillList) {
        this.skillList = skillList;
    }

    public ArrayList<Classes> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Classes> classes) {
        this.classes = classes;
    }

    public ArrayList<String> getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(ArrayList<String> hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
