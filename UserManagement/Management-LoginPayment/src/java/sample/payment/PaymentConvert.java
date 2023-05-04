/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.payment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.modelFeature.OrderPayment;
import sample.shopping.Cart;
import sample.shopping.Product;

/**
 *
 * @author ACER
 */
@WebServlet(name = "PaymentConvert", urlPatterns = {"/PaymentConvert"})
public class PaymentConvert extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "PaymentAuthor";
    private static final double SHIPPING = 5.00;
    private static final double TAX = 10.00;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart != null) {
                Map<String, Product> mapCart = cart.getCart();
                List<OrderPayment> listOrderPayments = new ArrayList<>();
                for (Map.Entry<String, Product> entry : mapCart.entrySet()) {
                    double subTotal = entry.getValue().getPrice() * entry.getValue().getQuantity();
                    OrderPayment orderPayment = new OrderPayment(entry.getValue().getName(),
                            String.format("%.2f", entry.getValue().getPrice() * entry.getValue().getQuantity()),
                            String.format("%.2f", SHIPPING),
                            String.format("%.2f", TAX),
                            entry.getValue().getQuantity(),
                            String.format("%.2f", entry.getValue().getPrice())
                    );
                    listOrderPayments.add(orderPayment);
                }
                request.setAttribute("listOrderPayments", listOrderPayments);
                request.setAttribute("where", "fromConvert");
                url = SUCCESS;
            }
        } catch (Exception e) {
            System.out.println("Error at PaymentConvert: " + e.toString());
            request.setAttribute("errorMessage", "Error at PaymentConvert: " + e.toString());
            request.setAttribute("ERROR", "Sory ! Some thing went wrong with your cart !");
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
