/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String SEARCH = "Search";
    private static final String SEARCH_CONTROLLER = "SearchController";
    private static final String DELETE = "Delete";
    private static final String DELETE_CONTROLLER = "DeleteController";
    private static final String UPDATE = "Update";
    private static final String UPDATE_CONTROLLER = "UpdateController";
    private static final String CREATE = "Create";
    private static final String CREATE_CONTROLLER = "CreateController";
    private static final String ADD = "Add";
    private static final String ADD_CONTROLLER = "AddController";
    private static final String VIEW = "View";
    private static final String VIEW_CART = "viewCart.jsp";
    private static final String EDIT = "Edit";
    private static final String EDIT_CONTROLLER = "EditController";
    private static final String REMOVE = "Remove";
    private static final String REMOVE_CONTROLLER = "RemoveController";
    private static final String SHOPPING = "Shopping";
    private static final String SHOPPING_PAGE = "ShoppingController";
    private static final String CHECKOUT_PAYMENT = "Checkout";
    private static final String CHECKOUT_PAYMENT_PAGE = "CheckoutController";
    private static final String CHECKOUT_DELIVERY = "CheckoutDelivery";
    private static final String CHECKOUT_DELIVERY_PAGE = "CheckoutDeliveryController";
    private static final String AUTHENGOOGLE = "AuthenGoogle";
    private static final String AUTHENGOOGLE_PAGE = "AuthenLoginGoogle";
    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_PAGE = "LogoutController";
    private static final String TYPEFORM = "Type";
    private static final String TYPEFORM_PAGE = "formInformation.jsp";
    private static final String LIST_INACTIVE = "Inactive";
    private static final String LIST_INACTIVE_PAGE = "InactiveController";
    private static final String SEARCH_INACTIVE = "SearchInactive";
    private static final String SEARCH_INACTIVE_PAGE = "SearchInactiveController";
    private static final String TEST = "Test";
    private static final String TEST_PAGE = "TestController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if (SEARCH.equals(action)) {
                url = SEARCH_CONTROLLER;
            } else if (DELETE.equals(action)) {
                url = DELETE_CONTROLLER;
            } else if (UPDATE.equals(action)) {
                url = UPDATE_CONTROLLER;
            } else if (CREATE.equals(action)) {
                url = CREATE_CONTROLLER;
            } else if (ADD.equals(action)) {
                url = ADD_CONTROLLER;
            } else if (VIEW.equals(action)) {
                url = VIEW_CART;
            } else if (EDIT.equals(action)) {
                url = EDIT_CONTROLLER;
            } else if (REMOVE.equals(action)) {
                url = REMOVE_CONTROLLER;
            } else if (SHOPPING.equals(action)) {
                url = SHOPPING_PAGE;
            } else if (CHECKOUT_PAYMENT.equals(action)) {
                url = CHECKOUT_PAYMENT_PAGE;
            } else if (CHECKOUT_DELIVERY.equals(action)) {
                url = CHECKOUT_DELIVERY_PAGE;
            } else if (AUTHENGOOGLE.equals(action)) {
                url = AUTHENGOOGLE_PAGE;
            } else if (LOGOUT.equals(action)) {
                url = LOGOUT_PAGE;
            } else if (TYPEFORM.equals(action)) {
                request.setAttribute("MC", "MC");
                url = TYPEFORM_PAGE;
            } else if (LIST_INACTIVE.equals(action)) {
                url = LIST_INACTIVE_PAGE;
            } else if (SEARCH_INACTIVE.equals(action)) {
                url = SEARCH_INACTIVE_PAGE;
            } else if (TEST.equals(action)) {
                url = TEST_PAGE;
            } else {
                request.setAttribute("ERROR", "Your action not support!!!  ");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
