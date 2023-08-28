/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NhanNT.controller;

import NhanNT.DAO.loginDAO;
import NhanNT.model.InsertError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jtkjm
 */
public class createAccountController extends HttpServlet {

    private final String LOGINPAGE = "login.html";
    private final String REGISTERPAGE = "createNewAccount.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = REGISTERPAGE;

        String username = request.getParameter("newUsername");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        String fullname = request.getParameter("fullname");
        loginDAO dao = new loginDAO();
        InsertError errors = new InsertError();
        boolean err = false;
        try {
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                errors.setUsernameLengthErr("username must have 6-20 characters");
                err = true;
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                errors.setPasswordLengthErr("password must have 6-30 characters");
                err = true;
            }
            if (!confirm.trim().equals(password.trim())) {
                errors.setConfirmNotMatch("confirm password not match");
                err = true;
            }
            if (fullname.trim().length() < 2 || fullname.trim().length() > 50) {
                errors.setFullnameLengthErr("fullname must have 2-50 characters");
                err = true;
            }
            if (err) {
                request.setAttribute("INSERTERROR", errors);
            } else {

                boolean insResult = dao.insertRecord(username, password, fullname, false);
                if (insResult) {
                    url = LOGINPAGE;
                }
            }
        } catch (SQLException e) {
            errors.setUsernameExisted(username + " is existed already!");
            request.setAttribute("INSERTERROR", errors);
            e.printStackTrace();
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
