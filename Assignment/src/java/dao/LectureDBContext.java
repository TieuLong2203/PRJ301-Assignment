/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Lecture;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luulo
 */
public class LectureDBContext extends DBContext<Lecture> {

    @Override
    public ArrayList<Lecture> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Lecture entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Lecture entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Lecture entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Lecture get(Lecture entity) {
        try {
            String sqlGetLecture = "SELECT lecture_id, lecture_name, lecture_email, gender, dob FROM lecture l\n"
                    + "WHERE lecture_email = ? ";
            PreparedStatement stm = connection.prepareStatement(sqlGetLecture);
            stm.setString(1, entity.getLectureEmail());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Lecture lecture = new Lecture();
                lecture.setLectureEmail(rs.getString("lecture_email"));
                lecture.setLectureId(rs.getString("lecture_id"));
                lecture.setLectureName(rs.getString("lecture_name"));
                lecture.setGender(rs.getBoolean("gender"));
                lecture.setDob(rs.getDate("dob"));
                return lecture;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LectureDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insert(int number) {
        Random random = new Random();

        String[] firstNames = {"Nguyen", "Tran", "Le", "Pham", "Hoang", "Luu", "Trinh", "Phan", "Vo", "Dang"};
        String[] middleNames = {"Van", "Thi", "Thanh", "Tieu", "Trong", "Huyen", "Ba"};
        String[] lastNames = {"Long", "My", "Quynh", "Mai", "Thuy", "Dung", "Duong", "Ngan", "Hoang", "Thao", "Dat", "Thu"};
        for (int i = 0; i < number; i++) {
            try {
                String firstName = firstNames[random.nextInt(firstNames.length)];
                String middleName = middleNames[random.nextInt(middleNames.length)];
                String lastName = lastNames[random.nextInt(lastNames.length)];
                String lectureName = firstName + " " + middleName + " " + lastName;

                // Generate student email
                String lectureId = lastName.toLowerCase() + firstName.toLowerCase().charAt(0) + middleName.toLowerCase().charAt(0) + String.format("%02d", 00 + i);
                String lectureEmail = lectureId + "@fe.edu.vn";

                // Generate random gender (true for male, false for female)
                boolean isMale = random.nextBoolean();

                // Generate random date of birth (assuming students are born in the year 2003)
                int month = random.nextInt(12) + 1;
                int day = random.nextInt(28) + 1; // A simple assumption for the day
                String dob = "1985-" + String.format("%02d", month) + "-" + String.format("%02d", day);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date javaDate = null;
                try {
                    javaDate = dateFormat.parse(dob);
                } catch (ParseException ex) {
                    Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
                java.sql.Date dobDate = new java.sql.Date(javaDate.getTime());

                String sql = "INSERT INTO account (email, password, campus_id)\n"
                        + "VALUES (?, ?, ?);\n"
                        + "INSERT INTO lecture (lecture_id, lecture_name, lecture_email, dob, gender)\n"
                        + "VALUES (?, ?, ?, ?, ?)\n"
                        + "INSERT INTO account_role (email, role_id)\n"
                        + "VALUES (?, ?)";
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setString(1, lectureEmail);
                stm.setString(2, "123");
                stm.setString(3, "FU-HL");
                stm.setString(4, lectureId);
                stm.setString(5, lectureName);
                stm.setString(6, lectureEmail);
                stm.setBoolean(8, isMale);
                stm.setDate(7, dobDate);
                stm.setString(9, lectureEmail);
                stm.setInt(10, 2);
                stm.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(LectureDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
