package Model;

import static Model.Data.classList;
import TimetableUtilities.TimetableController;
import View.ClassForm;
import View.ClassTypeForm;
import View.ControlPanelForm;
import View.ManagerForm;
import View.RoomForm;
import View.StaffForm;
import View.StudentForm;
import View.TeacherForm;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import javax.swing.JOptionPane;

public class DataValidation extends Observable {

    private final boolean INVALID = false;
    private final boolean VALID = true;
    public static final int FIRST_NAME = 0;
    public static final int LAST_NAME = 1;
    public static final int HOME_PHONE = 2;
    public static final int CELL_PHONE = 3;
    public static final int EMAIL = 4;
    public static final int ADDRESS = 5;
    public static final int USERNAME = 6;
    public static final int PASSWORD = 7;
    public static final int RETYPE_PASSWORD = 8;
    public static final int CONTACT_PERSON_NAME = 6;
    public static final int CONTACT_PERSON_PHONE = 7;
    public static final int CONTACT_PERSON_EMAIL = 8;
    public static final int CONTACT_PERSON_ADDRESS = 9;
    public static final int CLASS_NAME = 0;
    public static final int CLASS_CODE = 1;
    public static final int CLASS_START_DATE = 2;
    public static final int CLASS_END_DATE = 3;
    public static final int TUITION_FEE = 4;
    public static final int MON = 5;
    public static final int MON_COL = 6;
    public static final int TUE = 7;
    public static final int TUE_COL = 8;
    public static final int WED = 9;
    public static final int WED_COL = 10;
    public static final int THU = 11;
    public static final int THU_COL = 12;
    public static final int FRI = 13;
    public static final int FRI_COL = 14;
    public static final int SAT = 15;
    public static final int SAT_COL = 16;
    public static final int SUN = 17;
    public static final int SUN_COL = 18;
    private boolean[] results;

    /*
     * this method checks regex for username the parameter is the username
     * itself username should not be null username should not conatin any
     * special character username should only contain alphanumeric characters
     */
    public boolean checkRegexUserName(String username) {

        // username cannot be empty
        if (username.isEmpty()) {
            System.out.println("username canot be empty");
            return INVALID;
        }

        // check special character 
        if (username.matches(".*[&%$#@!~^]+.*")) {
            System.out.println("Not allow special character");
            return INVALID;
        }

        // check alphanumberic only
        if (!username.matches("^[A-Za-z0-9]{5,20}$")) {
            System.out.println("wrong username's input");
            return INVALID;
        }

        System.out.println("returning" + VALID);

        return VALID;
    }

    /*
     * this method checks the see if username already exist
     */
    public boolean checkUsernameExistence(String username) {

        // skip this method if user is editing an account 
        if (ManagerForm.getInstance() != null && ManagerForm.getInstance().getOption().equalsIgnoreCase("Edit")) {
            return VALID;
        }
        if (StaffForm.getInstance() != null && StaffForm.getInstance().getOption().equalsIgnoreCase("Edit")) {
            return VALID;
        }
        // go through all managers to check for any identical username
        for (int i = 0; i < Data.managerList.size(); i++) {
            if (username.equals(Data.managerList.get(i).getUsername())) {
                System.out.println("username taken !");
                return INVALID;
            }
        }

        // go through all staffs to check for any identical username
        for (int i = 0; i < Data.staffList.size(); i++) {
            if (username.equals(Data.staffList.get(i).getUsername())) {
                System.out.println("username taken !");
                return INVALID;
            }
        }
        return VALID;
    }

    /*
     * this method checks to ensure that: 1. username must not be empty 2.
     * username must has not already taken by someone else the methods return
     * VALID if both conditions are met else returns INVALID
     */
    public boolean checkUserName(String username) {

        // check regex for username
        if (!checkRegexUserName(username)) {
            return INVALID;
        }

        // check username existence
        if (!checkUsernameExistence(username)) {
            return INVALID;
        }
        return VALID;
    }

    /*
     * this method takes in name and check to see if: 1. the name is empty 2.
     * the name start with space/might contain only space the method returns
     * INVALID if any of the above happens else the method will return VALID
     */
    public boolean checkName(String name) {

        if (name.isEmpty()) {
            System.out.println("check name not success");
            return INVALID;
        }
        if (name.startsWith(" ")) {
            System.out.println("check name not success");
            return INVALID;
        }
        System.out.println("check name success");
        return VALID;
    }

    /*
     * this method checks regex for home phone number: 1. must be number only 2.
     * length must be 8 method returns VALID if phone number satisfy above
     * conditions else returns INVALID
     */
    public boolean checkHomePhoneNumber(String number) {
        if (!number.matches("^[0-9]{4}-[0-9]{4}$")) {
            System.out.println("check home number not success");
            return INVALID;
        }
        System.out.println("check home number success");
        return VALID;
    }

    /*
     * this method checks regex for cell phone number: 1. must be number only 2.
     * length must be 8 method returns VALID if phone number satisfy above
     * conditions else returns INVALID
     */
    public boolean checkCellPhoneNumber(String number) {
        if (number.matches("^[0-9]{3}-[0-9]{4}$")) {
            System.out.println("check cell number success");
            return VALID;
        }
        System.out.println("check cell number not success");
        return INVALID;
    }

    /*
     * this method checks regex for e-mail: 1. email must have format:
     * abc@example.com method returns VALID if the mail matches the expression
     * above else returns INVALID
     */
    public boolean checkEmail(String email) {
        if (email.matches("^[A-Za-z0-9_]+(\\.[_A-Za-z0-9]+)*@[A-Za-z]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            System.out.println("check email success");
            return VALID;
        }
        System.out.println("check email not success");
        return INVALID;
    }

    public boolean checkAddress(String address) {
        if (address.isEmpty()) {
            System.out.println("Empty address");
            return INVALID;
        } else if (address.length() > 76) {
            System.out.println("Cannot input more than 76 chars in Address field");
            return INVALID;
        }
        System.out.println("check address success");
        return VALID;
    }

    /*
     * this method checks password requirements, password has to : be at least 8
     * character long contain at least 1 upper case character contain at least 1
     * number contain at least 1 special character method returns INVALID if any
     * of the requirement above is not met else returns VALID
     */
    public boolean checkPassword(char[] pw) {

        boolean isUpper = false;
        boolean isLower = false;
        boolean isDigit = false;
        boolean isSpecs = false;
        char[] specs = {'!', '@', '#', '$', '%', '^', '&', '*'};

        if (pw == null) {
            return INVALID;
        }

        // check the length of the password
        if (pw.length < 8) {
            System.out.println("not enough length");
            return INVALID;
        }

        for (int i = 0; i < pw.length; i++) {
            if (Character.isUpperCase(pw[i])) { // any char upper case ?
                isUpper = true;
            }
            if (Character.isLowerCase(pw[i])) { // any char lower case ?
                isLower = true;
            }
            if (Character.isDigit(pw[i])) { // any char a number ?
                isDigit = true;
            }
        }

        for (Character c : specs) {
            for (int i = 0; i < pw.length; i++) {
                if (pw[i] == c) {
                    isSpecs = true;
                }
            }
        }

        if (isUpper == false) {
            System.out.println("Invalid, Must contain 1 upper case");
        }
        if (isLower == false) {
            System.out.println("Invalid, Must contain 1 lower case");
        }
        if (isDigit == false) {
            System.out.println("Invalid, Must contain 1 number");
        }
        if (isSpecs == false) {
            System.out.println("Invalid, Must contain 1 specs");
        }

        if (isUpper == false || isLower == false || isDigit == false || isSpecs == false) {
            return INVALID;
        }

        return VALID;
    }

    /*
     * this method checks if two passwords match each other
     */
    public boolean checkRetypePassword(char[] p1, char[] p2) {

        if (p1.length != p2.length) {
            System.out.println("password not matched, not same length");
            return INVALID;
        }
        for (int i = 0; i < p1.length; i++) {
            if (p1[i] != p2[i]) {
                System.out.println("password not matched");
                return INVALID;
            }
        }
        System.out.println("password matched");

        return VALID;
    }

    /*
     * this method validate all fields in a form parameter is an instance of a
     * form this method will check instance of a form then check the approriate
     * fields in a from method returns a boolean array each element in the array
     * corresponds for a field an element is false if the field felt validation
     * an element is true if the field pass validation
     */
    public void validateManagerForm(ManagerForm mf) {
        int numberOfFields;
        int numInvalidFields = 0;

        numberOfFields = ManagerForm.getInstance().getNumberOfFields();

        // initialize the array
        results = new boolean[numberOfFields];

        //validate all the field in the form
        results[FIRST_NAME] = checkName(
                ManagerForm.getInstance().getFirstnameR().getText());
        results[LAST_NAME] = checkName(
                ManagerForm.getInstance().getLastnameR().getText());
        results[HOME_PHONE] = checkHomePhoneNumber(
                ManagerForm.getInstance().getHomeNumR().getText());
        results[CELL_PHONE] = checkCellPhoneNumber(
                ManagerForm.getInstance().getPhoneNumR().getText());
        results[EMAIL] = checkEmail(
                ManagerForm.getInstance().getEmailR().getText());
        results[ADDRESS] = checkAddress(
                ManagerForm.getInstance().getAddressR().getText());
        results[USERNAME] = checkUserName(
                ManagerForm.getInstance().getUserName().getText());
        results[PASSWORD] = checkPassword(
                ManagerForm.getInstance().getPassR().getPassword());
        results[RETYPE_PASSWORD] = checkRetypePassword(
                ManagerForm.getInstance().getPassR().getPassword(),
                ManagerForm.getInstance().getRetypePassword().getPassword());

        // check to see if there's any invalid fields
        for (int i = 0; i < results.length; i++) {
            if (results[i] == false) {
                numInvalidFields++;
                System.out.println(i);
            }
        }

        System.out.println("numInvalidFields:" + numInvalidFields);

        // notify the views if any invalid input field detected
        if (numInvalidFields != 0) {
            this.addObserver(ManagerForm.getInstance());
            setChanged();
            notifyObservers(results);
        } else if (numInvalidFields == 0) {
            Data data = new Data();
            if (ManagerForm.getInstance().getOption().equalsIgnoreCase("add")) {
                System.out.println("adding new");
                data.saveManagerData(1);
            } else if (ManagerForm.getInstance().getOption().equalsIgnoreCase("edit")) {
                ManagerForm ma = ManagerForm.getInstance();
                ((Manager) Data.getCurrentEditUser()).setFirstName(ma.getFirstnameR().getText());
                ((Manager) Data.getCurrentEditUser()).setLastName(ma.getLastnameR().getText());
                ((Manager) Data.getCurrentEditUser()).setDobDate(ma.getDobDay().getSelectedItem().toString());
                ((Manager) Data.getCurrentEditUser()).setDobMonth(ma.getDobMonth().getSelectedItem().toString());
                ((Manager) Data.getCurrentEditUser()).setDobYear(ma.getDobYear().getSelectedItem().toString());
                ((Manager) Data.getCurrentEditUser()).setGender(ma.getGenderR().getSelectedItem().toString());
                ((Manager) Data.getCurrentEditUser()).setHomeNoCode(ma.getHomeNumR().getText());
                ((Manager) Data.getCurrentEditUser()).setHomeNo(ma.getHomeNumCodeR().getSelectedItem().toString());
                ((Manager) Data.getCurrentEditUser()).setPhoneNoCode(ma.getPhoneNumR().getText());
                ((Manager) Data.getCurrentEditUser()).setPhoneNo(ma.getPhoneNumCodeR().getSelectedItem().toString());
                ((Manager) Data.getCurrentEditUser()).setEmail(ma.getEmailR().getText());
                ((Manager) Data.getCurrentEditUser()).setAddress(ma.getAddressR().getText());
                ((Manager) Data.getCurrentEditUser()).setStatus(ma.getStatusR().getText());
                ((Manager) Data.getCurrentEditUser()).setUsername(ma.getUserName().getText());
                ((Manager) Data.getCurrentEditUser()).setTypingPassword(ma.getTypingPassword().getPassword());
                ((Manager) Data.getCurrentEditUser()).setDescription(ma.getInfoArea().getText());
                ((Manager) Data.getCurrentEditUser()).setImageLink(ma.getImagePath());
                data.saveManagerData(0);
            }
            ManagerForm.getInstance().dispose();
            ControlPanelForm.getInstance().setEnabled(true);
            ControlPanelForm.getInstance().setVisible(true);
        }

    }

    public void validateClassTypeForm(ClassTypeForm uf) {
        StringBuffer errorMess = new StringBuffer();
        boolean addToDataBase = true;
        boolean nameVal = true;
        String name = ClassTypeForm.getInstance().getNameText();
        String fee = ClassTypeForm.getInstance().getFeeText();

        //validate all the field in the form
        nameVal = checkName(ClassTypeForm.getInstance().getNameText());

        if (!name.matches("^([a-zA-Z ]+)") || nameVal == false) {
            addToDataBase = INVALID;
            errorMess.append("Names must be text.\n");
        }

        if (ClassTypeForm.getInstance().getOption().equalsIgnoreCase("add")) {
            for (int i = 0; i < Data.classTypeList.size(); i++) {
                if (Data.classTypeList.get(i).getName().equals(name)) {
                    addToDataBase = INVALID;
                    errorMess.append("Name has already existed.\n");
                }
            }
        }

        if (!fee.matches("^[0-9]+$")) {
            addToDataBase = INVALID;
            errorMess.append("Fee must be number.\n");
        }

        if (addToDataBase == false) {
            JOptionPane.showMessageDialog(null, errorMess.toString());
        } else {
            Data data = new Data();
            if (ClassTypeForm.getInstance().getOption().equalsIgnoreCase("add")) {
                data.saveClassTypeData(1);
            } else if (ClassTypeForm.getInstance().getOption().equalsIgnoreCase("edit")) {
                ClassTypeForm classType = ClassTypeForm.getInstance();
                String classTypeName = Data.getCurrentEditClassType().getName();
                ((ClassType) Data.getCurrentEditClassType()).setName(classType.getNameText());
                ((ClassType) Data.getCurrentEditClassType()).setFeePerLesson(classType.getFeeText());
                ((ClassType) Data.getCurrentEditClassType()).setType(classType.getTypeText());
                ((ClassType) Data.getCurrentEditClassType()).setRemarks(classType.getRemarksText());
                ((ClassType) Data.getCurrentEditClassType()).setLessonPerWeek(classType.getLessonPerWeekText());
                
                for (int i = 0; i < classList.size(); i++) {
                    if (classTypeName.equals(classList.get(i).getClassType().getName())) {
                        classList.get(i).setClassType(Data.getCurrentEditClassType());
                    }
                }
                data.saveClassData(0);
                data.saveClassTypeData(0);
            }
            ClassTypeForm.getInstance().dispose();
            ControlPanelForm.getInstance().setEnabled(true);
            ControlPanelForm.getInstance().setVisible(true);
        }
    }
    
    public void validateRoomForm(RoomForm uf) {
        boolean addToDataBase = true;
        StringBuffer error = new StringBuffer();
        if (uf.getOption().equalsIgnoreCase("add")) {
            for (int i = 0; i < Data.roomList.size(); i++) {
                if (Data.roomList.get(i).getNumber().equals(uf.getNumberText().toString())) {
                    addToDataBase = INVALID;
                    error.append("Room number has already existed.\n");
                }
            }
        }
        
        if (uf.getNumberText().equals("")) {
            error.append("Room number cannot be empty.\n");
            addToDataBase = INVALID;
        }
        
        if(uf.getTypeBox().getSelectedItem() == null){
            error.append("Type is empty.\n");
            addToDataBase = INVALID;
        }
        
        if (addToDataBase == INVALID) {
            JOptionPane.showMessageDialog(null, error.toString());
        } else {
            Data data = new Data();
            if (RoomForm.getInstance().getOption().equalsIgnoreCase("add")) {
                Room room = new Room(uf.getNumber().getText().toString(), uf.getTypeBox().getSelectedItem().toString());
                data.saveRoomData(1, room);
            } else if (RoomForm.getInstance().getOption().equalsIgnoreCase("edit")) {
                RoomForm roomForm = RoomForm.getInstance();
                ((Room) Data.getCurrentEditRoom()).setNumber(roomForm.getNumberText());
                ((Room) Data.getCurrentEditRoom()).setType(uf.getTypeBox().getSelectedItem().toString());
                
                data.saveRoomData(0, null);
            }
            RoomForm.getInstance().dispose();
            ControlPanelForm.getInstance().setEnabled(true);
            ControlPanelForm.getInstance().setVisible(true);
        }
    }

    /*
     * this method validate all fields in a form parameter is an instance of a
     * form this method will check instance of a form then check the approriate
     * fields in a from method returns a boolean array each element in the array
     * corresponds for a field an element is false if the field felt validation
     * an element is true if the field pass validation
     */
    public void validateStaffForm(StaffForm uf) {
        int numberOfFields;
        int numInvalidFields = 0;

        numberOfFields = StaffForm.getInstance().getNumberOfFields();

        // initialize the array
        results = new boolean[numberOfFields];

        //validate all the field in the form
        results[FIRST_NAME] = checkName(
                StaffForm.getInstance().getFirstnameR().getText());
        results[LAST_NAME] = checkName(
                StaffForm.getInstance().getLastnameR().getText());
        results[HOME_PHONE] = checkHomePhoneNumber(
                StaffForm.getInstance().getHomeNumR().getText());
        results[CELL_PHONE] = checkCellPhoneNumber(
                StaffForm.getInstance().getPhoneNumR().getText());
        results[EMAIL] = checkEmail(
                StaffForm.getInstance().getEmailR().getText());
        results[ADDRESS] = checkAddress(
                StaffForm.getInstance().getAddressR().getText());
        results[USERNAME] = checkUserName(
                StaffForm.getInstance().getUserName().getText());
        results[PASSWORD] = checkPassword(
                StaffForm.getInstance().getTypingPassword().getPassword());
        results[RETYPE_PASSWORD] = checkRetypePassword(
                StaffForm.getInstance().getTypingPassword().getPassword(),
                StaffForm.getInstance().getRetypePassword().getPassword());

        // check to see if there's any invalid fields
        for (int i = 0; i < results.length; i++) {
            if (results[i] == false) {
                numInvalidFields++;
            }
        }

        // notify the views if any invalid input field detected
        if (numInvalidFields != 0) {
            this.addObserver(StaffForm.getInstance());
            setChanged();
            notifyObservers(results);
        } else {
            Data data = new Data();
            if (StaffForm.getInstance().getOption().equalsIgnoreCase("add")) {
                data.saveStaffData(1);
            } else if (StaffForm.getInstance().getOption().equalsIgnoreCase("edit")) {
                data.saveStaffData(0);
            }
            StaffForm.getInstance().dispose();
            ControlPanelForm.getInstance().setEnabled(true);
            ControlPanelForm.getInstance().setVisible(true);
        }
    }

    /*
     * this method validate all fields in a form parameter is an instance of a
     * form this method will check instance of a form then check the approriate
     * fields in a from method returns a boolean array each element in the array
     * corresponds for a field an element is false if the field felt validation
     * an element is true if the field pass validation
     */
    public void validateStudentForm(StudentForm sf) {
        int numberOfFields;
        int numInvalidFields = 0;

        numberOfFields = StudentForm.getInstance().getNumberOfFields();

        // initialize the array
        results = new boolean[numberOfFields];

        //validate all the fields in the form
        results[FIRST_NAME] = checkName(
                StudentForm.getInstance().getFirstnameR().getText());
        results[LAST_NAME] = checkName(
                StudentForm.getInstance().getLastnameR().getText());
        results[HOME_PHONE] = checkHomePhoneNumber(
                StudentForm.getInstance().getHomeNumR().getText());
        results[CELL_PHONE] = checkCellPhoneNumber(
                StudentForm.getInstance().getPhoneNumR().getText());
        results[EMAIL] = checkEmail(
                StudentForm.getInstance().getEmailR().getText());
        results[ADDRESS] = checkAddress(
                StudentForm.getInstance().getAddressR().getText());
        results[CONTACT_PERSON_NAME] = checkName(
                StudentForm.getInstance().getContactNameR().getText());
        results[CONTACT_PERSON_PHONE] = checkCellPhoneNumber(
                StudentForm.getInstance().getContactPhoneR().getText());
        results[CONTACT_PERSON_EMAIL] = checkEmail(
                StudentForm.getInstance().getContactEmailR().getText());
        results[CONTACT_PERSON_ADDRESS] = checkAddress(
                StudentForm.getInstance().getContactAddressR().getText());

        // check to see if there's any invalid fields
        for (int i = 0; i < results.length; i++) {
            if (results[i] == false) {
                numInvalidFields++;
            }
        }

        // notify the views if any invalid input field detected
        if (numInvalidFields != 0) {
            this.addObserver(StudentForm.getInstance());
            setChanged();
            notifyObservers(results);
        } else {
            Data data = new Data();
            if (StudentForm.getInstance().getOption().equalsIgnoreCase("add")) {
                data.saveStudentData(1);
            } else if (StudentForm.getInstance().getOption().equalsIgnoreCase("edit")) {
                System.out.println("Edit Should work");
                data.saveStudentData(0);
            }
            StudentForm.getInstance().dispose();
            ControlPanelForm.getInstance().setEnabled(true);
            ControlPanelForm.getInstance().setVisible(true);
        }
    }

    /*
     * this method validate all fields in a form parameter is an instance of a
     * form this method will check instance of a form then check the approriate
     * fields in a from method returns a boolean array each element in the array
     * corresponds for a field an element is false if the field felt validation
     * an element is true if the field pass validation
     */
    public void validateTeacherForm(TeacherForm tf) {
        int numberOfFields;
        int numInvalidFields = 0;

        numberOfFields = TeacherForm.getInstance().getNumberOfFields();

        // initialize the array
        results = new boolean[numberOfFields];

        //validate all the fields in the form
        results[FIRST_NAME] = checkName(
                TeacherForm.getInstance().getFirstnameR().getText());
        results[LAST_NAME] = checkName(
                TeacherForm.getInstance().getLastnameR().getText());
        results[HOME_PHONE] = checkHomePhoneNumber(
                TeacherForm.getInstance().getHomeNumR().getText());
        results[CELL_PHONE] = checkCellPhoneNumber(
                TeacherForm.getInstance().getPhoneNumR().getText());
        results[EMAIL] = checkEmail(
                TeacherForm.getInstance().getEmailR().getText());
        results[ADDRESS] = checkAddress(
                TeacherForm.getInstance().getAddressR().getText());

        // check to see if there's any invalid fields
        for (int i = 0; i < results.length; i++) {
            if (results[i] == false) {
                numInvalidFields++;
            }
        }

        // notify the views if any invalid input field detected
        if (numInvalidFields != 0) {
            this.addObserver(TeacherForm.getInstance());
            setChanged();
            notifyObservers(results);
        } else {
            Data data = new Data();
            if (TeacherForm.getInstance().getOption().equalsIgnoreCase("add")) {
                data.saveTeacherData(1);
            } else if (TeacherForm.getInstance().getOption().equalsIgnoreCase("edit")) {
                data.saveTeacherData(0);
            }

            TeacherForm.getInstance().dispose();
            ControlPanelForm.getInstance().setEnabled(true);
            ControlPanelForm.getInstance().setVisible(true);
        }
    }

    /*
     * this method checks tuition fee field to make sure: value entered is not
     * null and value entered does not contain any letter the method returns
     * INVALID if the value is null or contains any letter else returns VALID
     */
    public boolean checkTuitionFee(String fee) {
        // return INVALID fee is left empty
        if (!checkEmpty(fee)) {
            return INVALID;
        }
        // return INVALID if fee has any letter
        for (int i = 0; i < fee.length(); i++) {
            if (Character.isLetter(fee.charAt(i))) {
                return INVALID;
            }
        }
        return VALID;
    }

    /*
     * this method takes in a time string, then convert and return it as an int
     * value the value will be converted as follow: 1. 9:00: String -> 9: int 2.
     * 9:30: String -> 9.5: int method returns total hour and min
     */
    public double processTimeString(String time) {
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);
        double min = Integer.parseInt(times[1]);
        if (min == 15) {
            min = .25;
        }
        if (min == 30) {
            min = .5;
        }
        if (min == 45) {
            min = .75;
        }
        double total = hour + min;
        return total;
    }

    /*
     * this method takes in th class times and check to see if: 1. start and end
     * time are the same(times difference is 0) 2. class takes longer than 2
     * hours(times difference is greater than 2) 3. end time is earlier than
     * start time(times difference is a negative) the method returns INVALID if
     * any conditions above happens else the method will return VALID
     */
    public boolean checkClassTime(String start, String end) {
        double startTime;
        double endTime;
        double teachingTime;

        startTime = processTimeString(start);
        endTime = processTimeString(end);
        teachingTime = endTime - startTime;

        if (startTime == endTime || startTime > endTime) {
            return INVALID;
        }

        if (teachingTime < .75 || teachingTime > 1) {
            return INVALID;
        }
        return VALID;
    }

    /*  
     * this method check to see whether the class has the same or overlap
     * time with another class in the system
     */
    public boolean checkClassCollision(String day, String xStartTime, String xEndTime) {

        String yStartTime;
        String yEndTime;
        boolean isEditting = false;
        int dayValue = 0;

        if (day.equals("Mon")) {
            dayValue = 0;
        }
        if (day.equals("Tue")) {
            dayValue = 1;
        }
        if (day.equals("Wed")) {
            dayValue = 2;
        }
        if (day.equals("Thu")) {
            dayValue = 3;
        }
        if (day.equals("Fri")) {
            dayValue = 4;
        }
        if (day.equals("Sat")) {
            dayValue = 5;
        }
        if (day.equals("Sun")) {
            dayValue = 6;
        }

        if (ClassForm.getInstance().getOption().equalsIgnoreCase("edit")) {
            isEditting = true;
        }

        for (int i = 0; i < Data.classList.size(); i++) {
            yStartTime = Data.classList.get(i).getFroms()[dayValue];
            yEndTime = Data.classList.get(i).getTos()[dayValue];
            if (!yStartTime.equals(yEndTime)) {
                double yStartTimeNumber = processTimeString(yStartTime);
                double yEndTimeNumber = processTimeString(yEndTime);
                double xStartTimeNumber = processTimeString(xStartTime);
                double xEndTimeNumber = processTimeString(xEndTime);

                if (isEditting) {
                    System.out.println("isEditting: " + isEditting);
                    System.out.println("xStartTimeNumber: " + xStartTimeNumber);
                    System.out.println("yStartTimeNumber: " + yStartTimeNumber);
                    System.out.println("xEndTimeNumber: " + xEndTimeNumber);
                    System.out.println("yEndTimeNumber: " + yEndTimeNumber);
                    if (xStartTimeNumber == yStartTimeNumber && xEndTimeNumber == yEndTimeNumber) {
                        System.out.println("They are both equal *****************");
                        return VALID;
                    }
                }

                if (yStartTimeNumber > xStartTimeNumber && yStartTimeNumber < xEndTimeNumber) {
                    return INVALID;
                }

                if (xEndTimeNumber > yStartTimeNumber && xEndTimeNumber < yEndTimeNumber) {
                    return INVALID;
                }

                if (xStartTimeNumber == yStartTimeNumber || xEndTimeNumber == yEndTimeNumber) {
                    return INVALID;
                }
            }
        }
        return VALID;
    }

    /*
     * this method checks if a field is empty method takes a string parameter
     * method only checks for empty string method returns INVALID if string is
     * empty
     */
    public boolean checkEmpty(String text) {
        if (text.isEmpty()) {
            return INVALID;
        }
        return VALID;
    }

    /*
     * this method take in a Date instance and return formatted String of Date
     * the format is as follow: dd/MM/yyyy
     */
    public String myformatDate(Date date) {
        String formatedDate;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        if (date == null) {
            return "";
        }
        formatedDate = df.format(date);
        return formatedDate;
    }

    /*
     * this method validate all fields in a class form parameter is an instance
     * of a class form then check the approriate fields in a from method returns
     * a boolean array each element in the array corresponds for a field an
     * element is false if the field felt validation an element is true if the
     * field pass validation
     */
    public void validateClassForm(ClassForm uf) {
        int count = 0;
        results = new boolean[ClassForm.getInstance().getNumberOfFields()];
        boolean[] roomcheck = new boolean[7];
        double[] addRoomTime = new double[7];
        String[] roomsName = new String[7];
        TimetableController timecon = new TimetableController();

        // initialize the array 
        for (int i = 0; i < results.length; i++) {
            results[i] = true;
        }

        // check class name field 
        results[CLASS_NAME] = checkEmpty(
                ClassForm.getInstance().getNameText().getText());
        // check class code field
        results[CLASS_CODE] = checkEmpty(
                ClassForm.getInstance().getCodeText().getText());
        // check start date field
        results[CLASS_START_DATE] = checkEmpty(
                myformatDate(ClassForm.getInstance().getDateChooser1().getDate()));
        // check end date field
        results[CLASS_END_DATE] = checkEmpty(
                myformatDate(ClassForm.getInstance().getDateChooser2().getDate()));
        // check tuition fee
        results[TUITION_FEE] = checkTuitionFee(
                ClassForm.getInstance().getTuitionFeeR().getText());

        // check individual class date 
        if (ClassForm.getInstance().getMon().isSelected()) {
            String startTime = (String) ClassForm.getInstance().getMonStartTime().getSelectedItem();
            String endTime = (String) ClassForm.getInstance().getMonEndTime().getSelectedItem();
            double numStartTime = timecon.getTime(startTime);
            double numEndTime = timecon.getTime(endTime);
            if(numEndTime - numStartTime == 45 || numEndTime - numStartTime == 85){
                addRoomTime[0] = 45;
            }else{
                addRoomTime[0] = 60;
            }
            roomsName[0] = ClassForm.getInstance().getMonRoom().getSelectedItem().toString();
            results[MON] = checkClassTime(startTime, endTime);
            results[MON_COL] = checkClassCollision(ClassForm.getInstance().getMon().getText(), startTime, endTime);
            roomcheck[0] = true;
        }
        if (ClassForm.getInstance().getTue().isSelected()) {
            String startTime = (String) ClassForm.getInstance().getTueStartTime().getSelectedItem();
            String endTime = (String) ClassForm.getInstance().getTueEndTime().getSelectedItem();
            double numStartTime = timecon.getTime(startTime);
            double numEndTime = timecon.getTime(endTime);
            if(numEndTime - numStartTime == 45 || numEndTime - numStartTime == 85){
                addRoomTime[1] = 45;
            }else{
                addRoomTime[1] = 60;
            }
            roomsName[1] = ClassForm.getInstance().getTueRoom().getSelectedItem().toString();
            results[TUE] = checkClassTime(startTime, endTime);
            results[TUE_COL] = checkClassCollision(ClassForm.getInstance().getTue().getText(), startTime, endTime);
            roomcheck[1] = true;
        }
        if (ClassForm.getInstance().getWed().isSelected()) {
            String startTime = (String) ClassForm.getInstance().getWedStartTime().getSelectedItem();
            String endTime = (String) ClassForm.getInstance().getWedEndTime().getSelectedItem();
            double numStartTime = timecon.getTime(startTime);
            double numEndTime = timecon.getTime(endTime);
            if(numEndTime - numStartTime == 45 || numEndTime - numStartTime == 85){
                addRoomTime[2] = 45;
            }else{
                addRoomTime[2] = 60;
            }
            roomsName[2] = ClassForm.getInstance().getWedRoom().getSelectedItem().toString();
            results[WED] = checkClassTime(startTime, endTime);
            results[WED_COL] = checkClassCollision(ClassForm.getInstance().getWed().getText(), startTime, endTime);
            roomcheck[2] = true;
        }
        if (ClassForm.getInstance().getThu().isSelected()) {
            String startTime = (String) ClassForm.getInstance().getThuStartTime().getSelectedItem();
            String endTime = (String) ClassForm.getInstance().getThuEndTime().getSelectedItem();
            double numStartTime = timecon.getTime(startTime);
            double numEndTime = timecon.getTime(endTime);
            if(numEndTime - numStartTime == 45 || numEndTime - numStartTime == 85){
                addRoomTime[3] = 45;
            }else{
                addRoomTime[3] = 60;
            }
            roomsName[3] = ClassForm.getInstance().getThuRoom().getSelectedItem().toString();
            results[THU] = checkClassTime(startTime, endTime);
            results[THU_COL] = checkClassCollision(ClassForm.getInstance().getThu().getText(), startTime, endTime);
            roomcheck[3] = true;
        }
        if (ClassForm.getInstance().getFri().isSelected()) {
            String startTime = (String) ClassForm.getInstance().getFriStartTime().getSelectedItem();
            String endTime = (String) ClassForm.getInstance().getFriEndTime().getSelectedItem();
            double numStartTime = timecon.getTime(startTime);
            double numEndTime = timecon.getTime(endTime);
            if(numEndTime - numStartTime == 45 || numEndTime - numStartTime == 85){
                addRoomTime[4] = 45;
            }else{
                addRoomTime[4] = 60;
            }
            roomsName[4] = ClassForm.getInstance().getFriRoom().getSelectedItem().toString();
            results[FRI] = checkClassTime(startTime, endTime);
            results[FRI_COL] = checkClassCollision(ClassForm.getInstance().getFri().getText(), startTime, endTime);
            roomcheck[4] = true;
        }
        if (ClassForm.getInstance().getSat().isSelected()) {
            String startTime = (String) ClassForm.getInstance().getSatStartTime().getSelectedItem();
            String endTime = (String) ClassForm.getInstance().getSatEndTime().getSelectedItem();
            double numStartTime = timecon.getTime(startTime);
            double numEndTime = timecon.getTime(endTime);
            if(numEndTime - numStartTime == 45 || numEndTime - numStartTime == 85){
                addRoomTime[5] = 45;
            }else{
                addRoomTime[5] = 60;
            }
            roomsName[5] = ClassForm.getInstance().getSatRoom().getSelectedItem().toString();
            results[SAT] = checkClassTime(startTime, endTime);
            results[SAT_COL] = checkClassCollision(ClassForm.getInstance().getSat().getText(), startTime, endTime);
            roomcheck[5] = true;
        }
        if (ClassForm.getInstance().getSun().isSelected()) {
            String startTime = (String) ClassForm.getInstance().getSunStartTime().getSelectedItem();
            String endTime = (String) ClassForm.getInstance().getSunEndTime().getSelectedItem();
            double numStartTime = timecon.getTime(startTime);
            double numEndTime = timecon.getTime(endTime);
            if(numEndTime - numStartTime == 45 || numEndTime - numStartTime == 85){
                addRoomTime[6] = 45;
            }else{
                addRoomTime[6] = 60;
            }
            roomsName[6] = ClassForm.getInstance().getSunRoom().getSelectedItem().toString();
            results[SUN] = checkClassTime(startTime, endTime);
            results[SUN_COL] = checkClassCollision(ClassForm.getInstance().getSun().getText(), startTime, endTime);
            roomcheck[6] = true;
        }

        // sum up check results to see how many check fails
        for (int i = 0; i < results.length; i++) {
            if (results[i] == false) {
                count++;
                break;
            }
        }

        // evaluate and choose the approriate action
        if (count != 0) {
            this.addObserver(ClassForm.getInstance());
            setChanged();
            notifyObservers(results);
        } else {
            Data data = new Data();
            if (ClassForm.getInstance().getOption().equalsIgnoreCase("add")) {
                Classes tempClass = null;
                data.saveClassData(1);

                for (int i = 0; i < Data.classList.size(); i++) {
                    if (Data.classList.get(i).getId().equals(ClassForm.getInstance().getIdText().getText())) {
//                        Data.classList.get(i)enrollStudent(StudentListModel.getInstance().getTempStudentLists());
                        tempClass = Data.classList.get(i);
                        break;
                    }
                }

//                StudentListModel.getInstance().initTempStudentList();
                System.out.println("****Checking temp student list****");
                for (int i = 0; i < StudentListModel.getInstance().getSelectedItem().size(); i++) {
                    System.out.println("Student " + i + " :" + StudentListModel.getInstance().getSelectedItem().get(i).getStd().getFirstName());
                }

                // add class into student and student into class
                for (int i = 0; i < StudentListModel.getInstance().getSelectedItem().size(); i++) {
                    for (int j = 0; j < Data.studentList.size(); j++) {
                        if (StudentListModel.getInstance().getSelectedItem().get(i).getStd().getID().equals(Data.studentList.get(j).getID())) {
                            Data.studentList.get(j).getClasses().add(tempClass);
                            tempClass.getStudents().add(Data.studentList.get(j));
                        }
                    }
                }

                // add class into teacher and teacher into class
                for (int i = 0; i < TeacherListModel.getInstance().getSelectedItem().size(); i++) {
                    if (Data.teacherList.get(i).getID().equals(TeacherListModel.getInstance().getSelectedItem().get(i).getTeacher().getID())) {
                        Data.teacherList.get(i).getClasses().add(tempClass);
                        tempClass.setTeacher(Data.teacherList.get(i));
                    }
                }
                
                //Room data modify
                for(int i = 0; i < roomcheck.length; i++){
                    if(roomcheck[i] == true){
                        for(int j = 0; j < Data.roomList.size();j++ ){
                            if(roomsName[i].equals(Data.roomList.get(j).getNumber())){
                                Data.roomList.get(j).setOccupiedMinute(Data.roomList.get(j).getOccupiedMinute() + addRoomTime[i]);
                            }
                        }
                    }
                }

                // save new data
                data.saveStudentData(0); // get an existing student then add class into student
                data.saveTeacherData(0); // get an existing teacher then add class into teacher
                data.saveClassData(0);
                data.saveRoomData(0, null);
                StudentListModel.getInstance().getSelectedItem().clear();
                TeacherListModel.getInstance().getSelectedItem().clear();
//                StudentListModel.getInstance().getTempStudentLists().clear();

            } else if (ClassForm.getInstance().getOption().equalsIgnoreCase("edit")) {
                System.out.println("****editing class****");

                Classes tempClass = null;

                for (int i = 0; i < Data.classList.size(); i++) {
                    if (ClassForm.getInstance().getIdText().equals(Data.classList.get(i).getId())) {
                        tempClass = Data.classList.get(i);
                        break;
                    }
                }

                for (int i = 0; i < Data.classList.size(); i++) {
                    if (Data.classList.get(i).getId().equals(ClassForm.getInstance().getIdText().getText())) {
                        tempClass = Data.classList.get(i);
                        break;
                    }
                }

                System.out.println("****Checking temp student list****");
                for (int i = 0; i < StudentListModel.getInstance().getSelectedItem().size(); i++) {
                    System.out.println("Student " + i + " :" + StudentListModel.getInstance().getSelectedItem().get(i).getStd().getFirstName());
                }

                // add class into student and student into class
                for (int i = 0; i < StudentListModel.getInstance().getSelectedItem().size(); i++) {
                    for (int j = 0; j < Data.studentList.size(); j++) {
                        if (StudentListModel.getInstance().getSelectedItem().get(i).getStd().getID().equals(Data.studentList.get(j).getID())) {
                            Data.studentList.get(j).getClasses().add(tempClass);
                            tempClass.getStudents().add(Data.studentList.get(j));
                        }
                    }
                }
                // add class into teacher and teacher into class
                for (int i = 0; i < TeacherListModel.getInstance().getSelectedItem().size(); i++) {
                    if (Data.teacherList.get(i).getID().equals(TeacherListModel.getInstance().getSelectedItem().get(i).getTeacher().getID())) {
                        Data.teacherList.get(i).getClasses().add(tempClass);
                        tempClass.setTeacher(Data.teacherList.get(i));
                    }
                }
                data.saveStudentData(0);
                data.saveTeacherData(0);
                data.saveClassData(0);
                StudentListModel.getInstance().getSelectedItem().clear();
                TeacherListModel.getInstance().getSelectedItem().clear();
            }
            ClassForm.getInstance().dispose();
            ControlPanelForm.getInstance().setEnabled(true);
            ControlPanelForm.getInstance().setVisible(true);
        }
    }

    public static void main(String[] args) {
        DataValidation dv = new DataValidation();
        Data.loadAllData();
        dv.checkClassCollision("Mon", "10:00", "11:00");
    }
}
