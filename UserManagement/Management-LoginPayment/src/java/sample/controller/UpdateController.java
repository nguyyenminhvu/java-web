/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.user.UserDAO;
import sample.user.UserDTO;

/**
 *
 * @author ACER
 */
@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "SearchController";
    private static final String UPDATE_PAGE = "update.jsp";

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
        String url = UPDATE_PAGE;
        try {
            String userId = request.getParameter("userId");
            UserDAO dao = new UserDAO();
            UserDTO user = dao.getUserById(userId);
            String search = request.getParameter("search");
            if (user != null) {
                request.setAttribute("userObject", user);
                request.setAttribute("rs", search);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error at UpdateController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
        String url = ERROR;
        try {
            String userId = request.getParameter("userId");
            String fullname = request.getParameter("fullName");
            String roleid = request.getParameter("roleId");
            UserDAO dao = new UserDAO();
            String search = request.getParameter("search");
            UserDTO updateUser = new UserDTO(userId, fullname, roleid, "");
            boolean checkUpdate = dao.update(updateUser);
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user.getUserID().equalsIgnoreCase(userId)) {
                user.setUserID(userId);
                user.setFullName(fullname);
                user.setRoleID(roleid);
                session.setAttribute("LOGIN_USER", user);
            }
            if (checkUpdate) {
                request.setAttribute("search", search);
                url = SUCCESS;
            }
        } catch (SQLException e) {
            log("Error at UpdateController: " + e.toString());
            request.setAttribute("ERROR", "Error at UpdateController with ERROR CODE: "+e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
