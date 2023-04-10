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
import sample.user.UserDAO;
import sample.user.UserDTO;
import sample.user.UserError;

/**
 *
 * @author ACER
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

    private static final String ERROR = "signUp.jsp";
    private static final String SUCCESS = "login.html";

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
        UserError userError = new UserError();
        try {
            String userId = request.getParameter("userId");
            String fullName = request.getParameter("fullName");
            String roleId = request.getParameter("roleId");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            //validation
            UserDAO dao = new UserDAO();

            boolean checkValidation = true;
//               boolean checkExisted = dao.checkExisted(userId);
//            if (checkExisted) {
//                userError.setUserIdError("Duplicated UserID");
//                checkValidation = false;
//            }
            if (userId.trim().length() > 10 || userId.trim().length() < 2) {
                userError.setUserIdError("UserId[2,10]");
                checkValidation = false;
            }
            if (fullName.trim().length() > 20 || fullName.trim().length() < 5) {
                userError.setFullNameError("fullName[5,20]");
                checkValidation = false;
            }
            if (!confirm.equals(password)) {
                userError.setConfirmError("Two Password not equal !");
                checkValidation = false;
            }
            if (checkValidation) {
                UserDTO user = new UserDTO(userId, fullName, roleId, password);
//                boolean checkInsert = dao.insert(user);
                boolean checkInsert = dao.insertv2(user);
                if (checkInsert) {
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Unknown error!!");
                }
            } else {
                request.setAttribute("USER_ERROR", userError);
            }

        } catch (Exception e) {
            log("Error at CreateController: " + e.toString());
            if (e.toString().contains("duplicate")) {
                userError.setUserIdError("Username already exist!");
                request.setAttribute("USER_ERROR", userError);
            }
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
