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
public class Session extends BaseEntity {

    private int sessionId;
    private Group group;
    private Lecture lecture;
    private Slot slot;
    private Room room;
    private int sessionIndex;
    private Date sessionDate;
    private Course course;

    public Session() {
    }

    public Session(int sessionId, Group group, Lecture lecture, Slot slot, Room room, int sessionIndex, Date sessionDate, Course course) {
        this.sessionId = sessionId;
        this.group = group;
        this.lecture = lecture;
        this.slot = slot;
        this.room = room;
        this.sessionIndex = sessionIndex;
        this.sessionDate = sessionDate;
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getSessionIndex() {
        return sessionIndex;
    }

    public void setSessionIndex(int sessionIndex) {
        this.sessionIndex = sessionIndex;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

}
