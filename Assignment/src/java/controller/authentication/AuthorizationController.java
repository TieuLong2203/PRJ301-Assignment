/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.authentication;

import controller.authentication.AuthenticationController;
import dao.AccountDBContext;
import entity.Account;
import entity.Role;
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
@WebServlet(name = "AuthorizationController", urlPatterns = {"/AuthorizationController"})
public abstract class AuthorizationController extends AuthenticationController {

    private boolean isAuthorized(Account loggedAccount, HttpServletRequest request) {
        String email = loggedAccount.getEmail();
        String url = request.getServletPath();
        AccountDBContext accountDb = new AccountDBContext();
        loggedAccount.setRoles(accountDb.getRoleAndFeature(email, url));
        return 1 <= loggedAccount.getRoles().size();
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account loggedAccount) throws ServletException, IOException {
        if (isAuthorized(loggedAccount, request)) {
            doGet(request, response, loggedAccount, loggedAccount.getRoles());
        } else {
            response.getWriter().print("Access denied!!!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account loggedAccount) throws ServletException, IOException {
        if (isAuthorized(loggedAccount, request)) {
            doPost(request, response, loggedAccount, loggedAccount.getRoles());
        } else {
            response.getWriter().print("Access denied!!!");
        }
    }

    protected abstract void doGet(HttpServletRequest request, HttpServletResponse response, Account loggedAccount, ArrayList<Role> roles) throws ServletException, IOException;

    protected abstract void doPost(HttpServletRequest request, HttpServletResponse response, Account loggedAccount, ArrayList<Role> roles) throws ServletException, IOException;
}
