/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "DeleteServlet", urlPatterns = {"/DeleteController"})
public class DeleteController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "InactiveController";
    private static final String SUCCESSV2 = "ListController";

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
        String url = ERROR;
        boolean flag = true;
        try {
            UserDAO dao = new UserDAO();
            HttpSession session = request.getSession();
            String userID = request.getParameter("userId");
            String status = request.getParameter("status");
            String s1 = "1";
            String s0 = "0";
            if (s1.equals(status)) {
                flag = true;
            } else if (s0.equals(status)) {
                flag = false;
            }
            UserDTO u = (UserDTO) session.getAttribute("LOGIN_USER");
            if (u.getUserID().equalsIgnoreCase(userID)) {
                String noti = u.getFullName() + " dang login, khong the xoa";
                request.setAttribute("noti", noti);
                if (flag == true) {
                    url = SUCCESSV2;
                } else {
                    url = SUCCESS;
                }
            } else {
                boolean checkDelete = dao.updateStatus(userID, flag);
                if (checkDelete) {
                    if (flag == true) {
                        url = SUCCESSV2;
                    } else {
                        url = SUCCESS;
                    }
                }
            }
        } catch (Exception e) {
            log("Error at DeleteController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
