package com.example.hp.loginpage.ModelClass;

public class TeacherModelClass {

    private String teacherPossition;
    private String teacherName;
    private String teacherDep;
    private String teacherPhone;
    private String teacherEmail;

    public TeacherModelClass() {

    }

    public TeacherModelClass(String teacherPossition, String teacherName, String teacherDep, String teacherPhone, String teacherEmail) {
        this.teacherPossition = teacherPossition;
        this.teacherName = teacherName;
        this.teacherDep = teacherDep;
        this.teacherPhone = teacherPhone;
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherPossition() {
        return teacherPossition;
    }

    public void setTeacherPossition(String teacherPossition) {
        this.teacherPossition = teacherPossition;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherDep() {
        return teacherDep;
    }

    public void setTeacherDep(String teacherDep) {
        this.teacherDep = teacherDep;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }
}
