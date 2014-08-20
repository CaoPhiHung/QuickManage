package Model;

import java.io.Serializable;

public abstract class User implements Serializable {

    private String ID;
    private String firstName;
    private String lastName;
    private Object dobDate;
    private Object dobMonth;
    private Object dobYear;
    private Object gender;
    private String phoneNo;
    private Object phoneNoCode;
    private String homeNo;
    private Object homeNoCode;
    private String email;
    private String address;
    private String status;
    private String imageLink;
    private String description;
    private String userType;
    private String currentdate;

    public User(){};
    public User(String ID, String firstName, String lastName, Object dobDate,
            Object dobMonth, Object dobYear, Object gender, String phoneNo,
            Object phoneNoCode, String homeNo, Object homeNoCode, String email,
            String address, String status, String description, String userType, String currentdate, String imageLink) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dobDate = dobDate;
        this.dobMonth = dobMonth;
        this.dobYear = dobYear;
        this.gender = gender;
        this.phoneNo = phoneNo;
        this.phoneNoCode = phoneNoCode;
        this.homeNo = homeNo;
        this.homeNoCode = homeNoCode;
        this.email = email;
        this.address = address;
        this.status = status;
        //this.photoLink = photoLink;
        this.description = description;
        this.userType = userType;
        this.currentdate = currentdate;
        this.imageLink = imageLink;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Object getDobDate() {
        return dobDate;
    }

    public void setDobDate(Object dobDate) {
        this.dobDate = dobDate;
    }

    public Object getDobMonth() {
        return dobMonth;
    }

    public void setDobMonth(Object dobMonth) {
        this.dobMonth = dobMonth;
    }

    public Object getDobYear() {
        return dobYear;
    }

    public void setDobYear(Object dobYear) {
        this.dobYear = dobYear;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Object getPhoneNoCode() {
        return phoneNoCode;
    }

    public void setPhoneNoCode(Object phoneNoCode) {
        this.phoneNoCode = phoneNoCode;
    }

    public String getHomeNo() {
        return homeNo;
    }

    public void setHomeNo(String homeNo) {
        this.homeNo = homeNo;
    }

    public Object getHomeNoCode() {
        return homeNoCode;
    }

    public void setHomeNoCode(Object homeNoCode) {
        this.homeNoCode = homeNoCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhotoLink() {
        return imageLink;
    }

    public void setPhotoLink(String photoLink) {
        this.imageLink = photoLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
