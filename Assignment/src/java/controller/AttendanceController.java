/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import controller.authentication.AuthorizationController;
import dao.AttendanceDBContext;
import dao.StudentDBContext;
import entity.Account;
import entity.Attendance;
import entity.Role;
import entity.Session;
import entity.Student;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author luulo
 */
@WebServlet(name = "AttendanceController", urlPatterns = {"/attendance"})
public class AttendanceController extends AuthorizationController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account loggedAccount, ArrayList<Role> roles) throws ServletException, IOException {
        int sessionId = Integer.parseInt(request.getParameter("session_id"));
        StudentDBContext studentDb = new StudentDBContext();
        ArrayList<Student> students = studentDb.list(sessionId);
        request.setAttribute("students", students);
        request.setAttribute("sessionId", sessionId);
        request.getRequestDispatcher("view/attendance.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account loggedAccount, ArrayList<Role> roles) throws ServletException, IOException {
        String[] studentIds = request.getParameterValues("studentId");
        ArrayList<Attendance> attendances = new ArrayList<>();
        Session session = new Session();
        session.setSessionId(Integer.parseInt(request.getParameter("sessionId")));
        for(String studentId : studentIds) {
            Student student = new Student();
            student.setStudentId(studentId);
            Attendance attendance = new Attendance();
            attendance.setStudent(student);
            attendance.setSession(session);
            attendance.setDescription(request.getParameter("description" + studentId));
            attendance.setStatus(request.getParameter("status" + studentId).equals("attend"));
            attendances.add(attendance);
        }
        AttendanceDBContext attendanceDb = new AttendanceDBContext();
        attendanceDb.insert(session, attendances);
        response.getWriter().print(attendances.size());
    }

}
