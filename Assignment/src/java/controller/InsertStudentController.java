/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import controller.authentication.AuthorizationController;
import dao.CampusDBContext;
import dao.StudentDBContext;
import entity.Account;
import entity.Campus;
import entity.Role;
import entity.Student;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author luulo
 */
@WebServlet(name="InsertController", urlPatterns={"/insert"})
public class InsertStudentController extends AuthorizationController{
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account loggedAccount, ArrayList<Role> roles) throws ServletException, IOException {
        CampusDBContext campusDb = new CampusDBContext();
        ArrayList<Campus> campuses = campusDb.list();
        request.setAttribute("campuses", campuses);
        request.getRequestDispatcher("view/insert.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account loggedAccount, ArrayList<Role> roles) throws ServletException, IOException {
        String studentName = request.getParameter("studentName");
        boolean gender = request.getParameter("gender").equalsIgnoreCase("male");
        Date dob = Date.valueOf(request.getParameter("dob"));
        Student paramStudent= new Student();
        paramStudent.setDob(dob);
        paramStudent.setGender(gender);
        paramStudent.setStudentName(studentName);
        Campus paramCampus = new Campus();
        paramCampus.setCampusId(request.getParameter("campus"));
        StudentDBContext studentDb = new StudentDBContext();
        studentDb.insert(paramStudent, paramCampus);
        response.getWriter().print("Insert student: " + studentName + " successfully");
    }

}
