/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.authentication.AuthorizationController;
import dao.CourseDBContext;
import dao.DepartmentDBContext;
import dao.GroupDBContext;
import dao.StudentDBContext;
import entity.Account;
import entity.Course;
import entity.Department;
import entity.Group;
import entity.Role;
import entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author luulo
 */
public class StudentGroupController extends AuthorizationController {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account loggedAccount, ArrayList<Role> roles) throws ServletException, IOException {
        DepartmentDBContext departmentDb = new DepartmentDBContext();
        ArrayList<Department> departments = departmentDb.list();
        String departmentId = request.getParameter("department_id");
        if (departmentId != null) {
            CourseDBContext courseDb = new CourseDBContext();
            ArrayList<Course> courses = courseDb.list(departmentId);
            request.setAttribute("courses", courses);
            request.setAttribute("department_id", departmentId);
        }
        String courseId = request.getParameter("course_id");
        if (courseId != null) {
            GroupDBContext groupDb = new GroupDBContext();
            ArrayList<Group> groups = groupDb.list(courseId);
            request.setAttribute("course_id", courseId);
            request.setAttribute("groups", groups);
        }
        String groupId = request.getParameter("group_id");
        if (groupId != null) {
            StudentDBContext studentDb = new StudentDBContext();
            ArrayList<Student> students = studentDb.listStudentOfGroup(Integer.parseInt(groupId));
            request.setAttribute("students", students);
        }
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("view/studentgroup.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account loggedAccount, ArrayList<Role> roles) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
