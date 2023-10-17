/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.authentication;

import dao.AccountDBContext;
import entity.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author luulo
 */
@WebServlet(name="LoginController", urlPatterns={"/login"})
public class LoginController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("index.html").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Account param = new Account();
        param.setEmail(request.getParameter("email"));
        param.setPassword(request.getParameter("password"));
        AccountDBContext accountDb = new AccountDBContext();
        Account loggedAccount = accountDb.get(param);
        if(loggedAccount != null) {
            String remember = request.getParameter("remember");
            
            HttpSession session = request.getSession();
            session.setAttribute("account", loggedAccount);
            
            if(remember != null) {
                Cookie cookieEmail = new Cookie("email", loggedAccount.getEmail());
                Cookie cookiePassword = new Cookie("passowrd", loggedAccount.getPassword());
                cookieEmail.setMaxAge(3600*24);
                cookiePassword.setMaxAge(3600*24);
                response.addCookie(cookieEmail);
                response.addCookie(cookiePassword);
            }
            response.sendRedirect("home");
        }
        else {
            response.getWriter().print("Incorrect username or password");
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
