/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;

/**
 *
 * @author luulo
 */
public class Lecture extends BaseEntity {

    private String lectureId;
    private String lectureName;
    private String lectureEmail;
    private Date dob;
    private boolean gender;

    public Lecture() {
    }

    public Lecture(String lectureId, String lectureName, String lectureEmail, Date dob, boolean gender) {
        this.lectureId = lectureId;
        this.lectureName = lectureName;
        this.lectureEmail = lectureEmail;
        this.dob = dob;
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getLectureId() {
        return lectureId;
    }

    public void setLectureId(String lectureId) {
        this.lectureId = lectureId;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public String getLectureEmail() {
        return lectureEmail;
    }

    public void setLectureEmail(String lectureEmail) {
        this.lectureEmail = lectureEmail;
    }

}
