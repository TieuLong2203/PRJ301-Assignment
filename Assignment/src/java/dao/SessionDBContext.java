/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Account;
import entity.Course;
import entity.Group;
import entity.Lecture;
import entity.Room;
import entity.Session;
import entity.Slot;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luulo
 */
public class SessionDBContext extends DBContext<Session> {

    @Override
    public ArrayList<Session> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Session get(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Session> list(Date startDate, Date endDate, Account loggedAccount) {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            String sqlGetSession = "SELECT ses.session_id, ses.group_id, g.group_name, ses.lecture_id, g.course_id, sl.slot_id, sl.start_time, sl.end_time, r.room_id, ses.session_date FROM [session] ses\n"
                    + "INNER JOIN [group] g ON g.group_id = ses.group_id\n"
                    + "INNER JOIN student_belong_to_group sbtg ON g.group_id = sbtg.group_id\n"
                    + "INNER JOIN student s ON s.student_id = sbtg.student_id\n"
                    + "INNER JOIN slot sl ON sl.slot_id = ses.slot_id\n"
                    + "INNER JOIN room r ON r.room_id = ses.room_id\n"
                    + "WHERE ses.session_date >= ? AND ses.session_date <= ? AND s.student_email = ?";
            PreparedStatement stm = connection.prepareStatement(sqlGetSession);
            stm.setDate(1, startDate);
            stm.setDate(2, endDate);
            stm.setString(3, loggedAccount.getEmail());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setRoomId(rs.getString("room_id"));
                Lecture lecture = new Lecture();
                lecture.setLectureId(rs.getString("lecture_id"));
                Slot slot = new Slot();
                slot.setSlotId(Integer.parseInt(rs.getString("slot_id")));
                slot.setEndTime(rs.getTime("end_time").toString());
                slot.setStartTime(rs.getTime("start_time").toString());
                Group group = new Group();
                group.setGroupId(Integer.parseInt(rs.getString("group_id")));
                group.setGroupName(rs.getString("group_name"));
                Course course = new Course();
                course.setCourseId(rs.getString("course_id"));
                Session ses = new Session();
                ses.setSessionId(Integer.parseInt(rs.getString("session_id")));
                ses.setCourse(course);
                ses.setLecture(lecture);
                ses.setGroup(group);
                ses.setRoom(room);
                ses.setSlot(slot);
                ses.setSessionDate(rs.getDate("session_date"));
                sessions.add(ses);
            }
            return sessions;
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessions;
    }

}
