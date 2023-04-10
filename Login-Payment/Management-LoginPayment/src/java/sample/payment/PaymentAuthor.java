/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.payment;

import com.paypal.base.rest.PayPalRESTException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.shopping.Cart;

import sample.modelFeature.OrderPayment;
import sample.shopping.Product;

/**
 *
 * @author ACER
 */
@WebServlet(name = "PaymentAuthor", urlPatterns = {"/PaymentAuthor"})
public class PaymentAuthor extends HttpServlet {

    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        List<OrderPayment> orderPaymentList = (List<OrderPayment>) request.getAttribute("listOrderPayments");
        String where = (String) request.getAttribute("where");
        try {
            if (where != null) {
                PaymentService paymentServices = new PaymentService();
                String approvalLink = paymentServices.authorizePayment(orderPaymentList);
                response.sendRedirect(approvalLink);
            } else {
                request.setAttribute("ERROR", "Sory! Your action not support");
                request.getRequestDispatcher(url).forward(request, response);
            }
        } catch (PayPalRESTException e) {
            System.out.println("Error at AuthorizePaymentServlet ne Vu: " + e.toString());
            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("ERROR", "Sorry ! Your action not support !");
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
