package com.example.hp.loginpage.ModelClass;

public class UserProfile {
    public String userName;
    public String userId;
    public String userBatch;
    public String userPhone;
    public String userEmail;
    public String userBloodGroup;

    public UserProfile(){

    }

    public UserProfile(String userName, String userId, String userBatch, String userPhone, String userEmail, String userBloodGroup) {
        this.userName = userName;
        this.userId = userId;
        this.userBatch = userBatch;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userBloodGroup = userBloodGroup;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserBatch() {
        return userBatch;
    }

    public void setUserBatch(String userBatch) {
        this.userBatch = userBatch;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserBloodGroup() {
        return userBloodGroup;
    }

    public void setUserBloodGroup(String userBloodGroup) {
        this.userBloodGroup = userBloodGroup;
    }
}
