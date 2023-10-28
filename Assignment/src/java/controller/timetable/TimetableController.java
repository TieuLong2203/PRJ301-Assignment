/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.timetable;

import controller.authentication.AuthorizationController;
import dao.AttendanceDBContext;
import dao.LectureDBContext;
import dao.SessionDBContext;
import dao.SlotDbContext;
import entity.Account;
import entity.Attendance;
import entity.Lecture;
import entity.Role;
import entity.Session;
import entity.Slot;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import util.DateTimeHelper;

/**
 *
 * @author luulo
 */
public class TimetableController extends AuthorizationController {

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account loggedAccount, ArrayList<Role> roles) throws ServletException, IOException {
        ArrayList<Date> dates = DateTimeHelper.getCurrentWeekDates();
        SessionDBContext sessionDb = new SessionDBContext();
        ArrayList<Session> sessions = sessionDb.list(dates.get(0), dates.get(dates.size() - 1), loggedAccount);
        SlotDbContext slotDb = new SlotDbContext();
        ArrayList<Slot> slots = slotDb.list();
        LectureDBContext lectureDb = new LectureDBContext();
        request.setAttribute("email", loggedAccount.getEmail());
        request.setAttribute("dates", dates);
        request.setAttribute("slots", slots);
        request.setAttribute("sessions", sessions);
        request.setAttribute("email", loggedAccount.getEmail());
        String regex = "^[a-zA-Z]+[0-9]+@fe.edu.vn$";
        boolean matching = loggedAccount.getEmail().matches(regex);
        if (matching) {
            Lecture param = new Lecture();
            param.setLectureEmail(loggedAccount.getEmail());
            Lecture lecture = lectureDb.get(param);
            request.setAttribute("lecture", lecture);
            request.getRequestDispatcher("view/timetable_lecture.jsp").forward(request, response);
        } else {
            ArrayList<Attendance> attendances = new ArrayList<>();
            AttendanceDBContext attendanceDb = new AttendanceDBContext();
            for (Session session : sessions) {
                Attendance attendance = attendanceDb.get(session, loggedAccount.getEmail());
                if (attendance != null) {
                    attendances.add(attendance);
                }
            }
            ArrayList<Lecture> lectures = new ArrayList<>();
            for (Session session : sessions) {
                Lecture lecture = lectureDb.get(session.getLecture());
                lectures.add(lecture);
            }
            request.setAttribute("attendances", attendances);
            request.setAttribute("lectures", lectures);
            request.getRequestDispatcher("view/timetable.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account loggedAccount, ArrayList<Role> roles) throws ServletException, IOException {
        Date startSQLDate = Date.valueOf(request.getParameter("startDate"));
        Date endSQLDate = Date.valueOf(request.getParameter("endDate"));
        SessionDBContext sessionDb = new SessionDBContext();
        ArrayList<Session> sessions = sessionDb.list(startSQLDate, endSQLDate, loggedAccount);
        SlotDbContext slotDb = new SlotDbContext();
        ArrayList<Slot> slots = slotDb.list();
        LectureDBContext lectureDb = new LectureDBContext();
        ArrayList<Date> dates = DateTimeHelper.getDateInRange(startSQLDate, endSQLDate);
        request.setAttribute("email", loggedAccount.getEmail());
        request.setAttribute("dates", dates);
        request.setAttribute("slots", slots);
        request.setAttribute("sessions", sessions);
        String regex = "^[a-zA-Z]+[0-9]+@fe.edu.vn$";
        boolean matching = loggedAccount.getEmail().matches(regex);
        if (matching) {
            Lecture param = new Lecture();
            param.setLectureEmail(loggedAccount.getEmail());
            Lecture lecture = lectureDb.get(param);
            request.setAttribute("lecture", lecture);
            request.getRequestDispatcher("view/timetable_lecture.jsp").forward(request, response);
        } else {
            ArrayList<Attendance> attendances = new ArrayList<>();
            AttendanceDBContext attendanceDb = new AttendanceDBContext();
            for (Session session : sessions) {
                Attendance attendance = attendanceDb.get(session, loggedAccount.getEmail());
                if (attendance != null) {
                    attendances.add(attendance);
                }
            }
            ArrayList<Lecture> lectures = new ArrayList<>();
            for (Session session : sessions) {
                Lecture lecture = lectureDb.get(session.getLecture());
                lectures.add(lecture);
            }
            request.setAttribute("attendances", attendances);
            request.setAttribute("lectures", lectures);
            request.getRequestDispatcher("view/timetable.jsp").forward(request, response);
        }
    }

}
