/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Account;
import entity.Lecture;
import entity.Room;
import entity.Session;
import entity.Slot;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            String sqlGetSession = "SELECT ses.group_id, ses.lecture_id, ses.slot_id, ses.room_id, g.group_name FROM student s\n"
                    + "INNER JOIN student_belong_to_group sbtg ON s.student_id = sbtg.student_id\n"
                    + "INNER JOIN [session] ses ON ses.group_id = sbtg.group_id\n"
                    + "INNER JOIN [group] g ON g.group_id = ses.group_id\n"
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
                slot.setSlotId("");
            }
            return sessions;
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
