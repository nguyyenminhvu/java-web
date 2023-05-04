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
import sample.modelFeature.AccountGoogleError;
import sample.user.UserDAO;
import sample.user.UserDTO;

/**
 *
 * @author ACER
 */
@WebServlet(name = "AuthenLoginGoogle", urlPatterns = {"/AuthenLoginGoogle"})
public class AuthenLoginGoogle extends HttpServlet {

    private static final String ALREADY = "ShoppingController";
    private static final String NOT_YET = "confirmGoogle.jsp";
    private static final String ERROR_LOGIN = "login.html";
    private static final String ERROR_ACCOUNT = "confirmGoogle.jsp";

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AuthenLoginGoogle</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AuthenLoginGoogle at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String url = ERROR_LOGIN;
        try {
            HttpSession session = request.getSession();
            String nameGoogle = (String) request.getAttribute("nameGoogle");
            String emailGoogle = (String) request.getAttribute("emailGoogle");
            UserDAO userDAO = new UserDAO();
            String userEmail[] = emailGoogle.split("@");
            boolean check = userDAO.checkExisted(userEmail[0]);
            if (check) {
                UserDTO user = userDAO.getUserById(userEmail[0]);
                session.setAttribute("LOGIN_USER", user);
                url = ALREADY;
            } else {
                request.setAttribute("nameGoogle", nameGoogle);
                request.setAttribute("userEmail", userEmail[0]);
                request.setAttribute("action", "cre");
                url = NOT_YET;
            }
        } catch (Exception e) {
            System.out.println("Error at AuthenLoginGoogle: " + e.toString());
            request.setAttribute("ERROR", "Error at AuthenLoginGoogle: " + e.toString());
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
        String url = ERROR_LOGIN;
        try {
            HttpSession session = request.getSession();
            UserDAO dao = new UserDAO();
            AccountGoogleError accountGoogleError = new AccountGoogleError();
            String nameGoogle = request.getParameter("nameGoogle");
            String userEmail = request.getParameter("userEmail");
            String password = request.getParameter("password");
            String repassword = request.getParameter("repassword");
            String roleId = request.getParameter("roleId");
            boolean check = true;
            if (password.length() < 5) {
                check = false;
                accountGoogleError.setPasswordError("Password must be more than 5 charater !");
                url = ERROR_ACCOUNT;
                request.setAttribute("userEmail", userEmail);
                request.setAttribute("nameGoogle", nameGoogle);
                request.setAttribute("action", "cre");
            }
            if (!password.equals(repassword)) {
                check = false;
                accountGoogleError.setRePasswordError("Two password not equal !");
                url = ERROR_ACCOUNT;
                request.setAttribute("userEmail", userEmail);
                request.setAttribute("nameGoogle", nameGoogle);
                request.setAttribute("action", "cre");
            }
            if (check) {
                boolean checkExist = dao.checkExisted(userEmail);
                while (checkExist) {
                    String code = "123456789";
                    String result = null;
                    for (int i = 0; i < 1; i++) {
                        int index = (int) (code.length() * Math.random());
                        result = code.substring(index, index + 1);
                    }
                    userEmail += result;
                    checkExist = dao.checkExisted(userEmail);
                }
                UserDTO userDTO = new UserDTO(userEmail, nameGoogle, roleId, password);
                dao.insert(userDTO);
                session.setAttribute("LOGIN_USER", userDTO);
                url = ALREADY;
            } else {
                request.setAttribute("errorGoogle", accountGoogleError);
            }

        } catch (Exception e) {
            System.out.println("Error at AuthenLoginGoogle(POST): " + e.toString());
            request.setAttribute("ERROR", "Error at AuthenLoginGoogle(POST): " + e.toString());
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
