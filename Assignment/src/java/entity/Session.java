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

    private String sessionId;
    private Group group;
    private Lecture lecture;
    private Slot slot;
    private Room room;
    private int sessionIndex;
    private Date sessionDate;

    public Session() {
    }

    public Session(String sessionId, Group group, Lecture lecture, Slot slot, Room room, int sessionIndex, Date sessionDate) {
        this.sessionId = sessionId;
        this.group = group;
        this.lecture = lecture;
        this.slot = slot;
        this.room = room;
        this.sessionIndex = sessionIndex;
        this.sessionDate = sessionDate;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
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
