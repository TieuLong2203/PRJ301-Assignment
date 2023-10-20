/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Student;
import java.sql.Date;
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
public class StudentDBContext extends DBContext<Student> {
    
    private String generateStudentID(String lastStudentId) {
        String tail = lastStudentId.substring(2);
        int number = Integer.parseInt(tail);
        number++;
        return "HE" + number;
    }

    @Override
    public ArrayList<Student> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Student entity) {
        try {
            String studentName = entity.getStudentName();
            boolean gender = entity.isGender();
            Date dob = entity.getDob();
            String sqlGetLastStudentId = "SELECT TOP 1 student_id FROM student\n"
                    + "ORDER BY student_id DESC";
            PreparedStatement stm1 = connection.prepareStatement(sqlGetLastStudentId);
            ResultSet rs = stm1.executeQuery();
            String lastStudentId = rs.getString("student_id");
            String studentId = generateStudentID(lastStudentId);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public Student get(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void insert(int number) {
        Random random = new Random();

        String[] firstNames = {"Nguyen", "Tran", "Le", "Pham", "Hoang", "Luu", "Trinh", "Phan", "Vo", "Dang"};
        String[] middleNames = {"Van", "Thi", "Thanh", "Tieu", "Trong", "Huyen", "Ba"};
        String[] lastNames = {"Long", "My", "Quynh", "Mai", "Thuy", "Dung", "Duong", "Ngan", "Hoang", "Thao", "Dat", "Thu"};

        for (int i = 0; i < number; i++) {
            try {
                // Generate student ID
                String studentId = "HE17" + String.format("%04d", 0000 + i);

                // Generate random name
                String firstName = firstNames[random.nextInt(firstNames.length)];
                String middleName = middleNames[random.nextInt(middleNames.length)];
                String lastName = lastNames[random.nextInt(lastNames.length)];
                String studentName = firstName + " " + middleName + " " + lastName;

                // Generate student email
                String studentEmail = lastName.toLowerCase() + firstName.toLowerCase().charAt(0) + middleName.toLowerCase().charAt(0) + studentId.toLowerCase() + "@fpt.edu.vn";

                // Generate random gender (true for male, false for female)
                boolean isMale = random.nextBoolean();

                // Generate random date of birth (assuming students are born in the year 2003)
                int month = random.nextInt(12) + 1;
                int day = random.nextInt(28) + 1; // A simple assumption for the day
                String dob = "2003-" + String.format("%02d", month) + "-" + String.format("%02d", day);
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
                        + "INSERT INTO student (student_id, student_name, student_email, gender, dob)\n"
                        + "VALUES (?, ?, ?, ?, ?)\n"
                        + "INSERT INTO account_role (email, role_id)\n"
                        + "VALUES (?, ?)";
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setString(1, studentEmail);
                stm.setString(2, "123");
                stm.setString(3, "FU-HL");
                stm.setString(4, studentId);
                stm.setString(5, studentName);
                stm.setString(6, studentEmail);
                stm.setBoolean(7, isMale);
                stm.setDate(8, dobDate);
                stm.setString(9, studentEmail);
                stm.setInt(10, 3);
                stm.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
