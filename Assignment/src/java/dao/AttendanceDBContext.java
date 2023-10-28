/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Attendance;
import entity.Session;
import entity.Student;
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
public class AttendanceDBContext extends DBContext<Attendance> {

    @Override
    public ArrayList<Attendance> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attendance entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Attendance entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Attendance entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attendance get(Attendance entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void insert(Session session, ArrayList<Attendance> attendances) {
        try {
            connection.setAutoCommit(false);
            String sqlUpdateSession = "UPDATE [session] SET is_attended = 1 WHERE session_id =?";
            PreparedStatement stmUpdateSession = connection.prepareStatement(sqlUpdateSession);
            stmUpdateSession.setInt(1, session.getSessionId());
            stmUpdateSession.executeUpdate();

            String sqlDeleteAttendance = "DELETE FROM attendance\n"
                    + "WHERE session_id = ?";
            PreparedStatement stmDeleteAttendance = connection.prepareStatement(sqlDeleteAttendance);
            stmDeleteAttendance.setInt(1, session.getSessionId());
            stmDeleteAttendance.executeUpdate();

            for (Attendance attendance : attendances) {
                String sqlInsertAttendance = "INSERT INTO [attendance]\n"
                        + "           ([session_id]\n"
                        + "           ,[student_id]\n"
                        + "           ,[status]\n"
                        + "           ,[description]\n"
                        + "           ,[date_time])\n"
                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,GETDATE())";
                PreparedStatement stmInsertAttendance = connection.prepareStatement(sqlInsertAttendance);
                stmInsertAttendance.setInt(1, session.getSessionId());
                stmInsertAttendance.setString(2, attendance.getStudent().getStudentId());
                stmInsertAttendance.setBoolean(3, attendance.isStatus());
                stmInsertAttendance.setString(4, attendance.getDescription());
                stmInsertAttendance.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public Attendance get(Session session, String studentEmail) {
        try {
            String sqlGetStudentAttendance = "SELECT a.session_id, a.date_time, a.[status], a.[description] FROM attendance a\n"
                    + "INNER JOIN student s ON s.student_id = a.student_id\n"
                    + "INNER JOIN account ac ON ac.email = s.student_email\n"
                    + "WHERE ac.email = ? and a.session_id = ?";
            PreparedStatement stm = connection.prepareStatement(sqlGetStudentAttendance);
            stm.setString(1, studentEmail);
            stm.setInt(2, session.getSessionId());
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                Attendance attendance = new Attendance();
                attendance.setSession(session);
                Student student = new Student();
                student.setStudentEmail(studentEmail);
                attendance.setStudent(student);
                attendance.setDate_time(rs.getDate("date_time"));
                attendance.setDescription(rs.getString("description"));
                attendance.setStatus(rs.getBoolean("status"));
                return attendance;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
