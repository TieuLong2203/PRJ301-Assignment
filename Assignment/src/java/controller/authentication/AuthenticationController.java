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

/**
 *
 * @author luulo
 */
@WebServlet(name = "AuthenticationController", urlPatterns = {"/AuthenticationController"})
public abstract class AuthenticationController extends HttpServlet {

    private boolean isAuthentication(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("account");
        if (account != null) {
            return true;
        } else {
            String email = null;
            String password = null;
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (email != null && password != null) {
                    break;
                }
                if (cookie.equals("email")) {
                    email = cookie.getValue();
                }
                if (cookie.equals("password")) {
                    password = cookie.getValue();
                }
            }
            if (email != null && password != null) {
                AccountDBContext accountDb = new AccountDBContext();
                Account param = new Account();
                param.setEmail(email);
                param.setPassword(password);
                account = accountDb.get(param);
                if (account != null) {
                    request.getSession().setAttribute("account", account);
                    return true;
                }
            }
            return false;
        }
    }

    protected abstract void doGet(HttpServletRequest request, HttpServletResponse response, Account loggedAccount)
            throws ServletException, IOException;

    protected abstract void doPost(HttpServletRequest request, HttpServletResponse response, Account loggedAccount)
            throws ServletException, IOException;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (isAuthentication(request)) {
            doGet(request, response, (Account) request.getSession().getAttribute("account"));
        } else {
            response.sendRedirect("/login");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (isAuthentication(request)) {
            doPost(request, response, (Account) request.getSession().getAttribute("account"));
        } else {
            response.sendRedirect("/login");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
