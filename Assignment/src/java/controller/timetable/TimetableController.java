/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.timetable;

import controller.authentication.AuthorizationController;
import dao.SessionDBContext;
import dao.SlotDbContext;
import entity.Account;
import entity.Role;
import entity.Session;
import entity.Slot;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
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
        ArrayList<Session> sessions = sessionDb.list(dates.get(0), dates.get(dates.size()-1), loggedAccount);
        SlotDbContext slotDb = new SlotDbContext();
        ArrayList<Slot> slots = slotDb.list();
        request.setAttribute("email", loggedAccount.getEmail());
        request.setAttribute("dates", dates);
        request.setAttribute("slots", slots);
        request.setAttribute("sessions", sessions);
        request.setAttribute("email", loggedAccount.getEmail());
        request.getRequestDispatcher("view/timetable.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account loggedAccount, ArrayList<Role> roles) throws ServletException, IOException {
        Date startSQLDate = Date.valueOf(request.getParameter("startDate"));
        Date endSQLDate = Date.valueOf(request.getParameter("endDate"));
        SessionDBContext sessionDb = new SessionDBContext();
        ArrayList<Session> sessions = sessionDb.list(startSQLDate, endSQLDate, loggedAccount);
        SlotDbContext slotDb = new SlotDbContext();
        ArrayList<Slot> slots = slotDb.list();
        ArrayList<Date> dates = DateTimeHelper.getDateInRange(startSQLDate, endSQLDate);
        request.setAttribute("email", loggedAccount.getEmail());
        request.setAttribute("dates", dates);
        request.setAttribute("slots", slots);
        request.setAttribute("sessions", sessions);
        request.getRequestDispatcher("view/timetable.jsp").forward(request, response);
    }

}
