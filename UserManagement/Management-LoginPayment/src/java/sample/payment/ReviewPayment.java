/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.payment;

import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.shopping.Cart;
import sample.shopping.Product;

/**
 *
 * @author ACER
 */
@WebServlet(name = "ReviewPayment", urlPatterns = {"/review_payment"})
public class ReviewPayment extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "reviewPayment.jsp";
    private static final double SHIPPING = 5.00;
    private static final double TAX = 10.00;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ReviewPayment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReviewPayment at " + request.getContextPath() + "</h1>");
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
        String url = ERROR;
        String paymentId = request.getParameter("paymentId");
        String PayerId = request.getParameter("PayerID");
        try {
            double totalResult = 0;
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart != null) {
                Map<String, Product> cartMap = cart.getCart();
                for (Map.Entry<String, Product> entry : cartMap.entrySet()) {
                    totalResult += entry.getValue().getPrice() * entry.getValue().getQuantity();
                }
                totalResult += cartMap.size() * TAX + cartMap.size() * SHIPPING;
                PaymentService paymentServices = new PaymentService();
                Payment payment = paymentServices.getPaymentDetails(paymentId);
                PayerInfo payerInfo = payment.getPayer().getPayerInfo();
                Transaction transaction = payment.getTransactions().get(0);
                ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();

                request.setAttribute("payer", payerInfo);
                request.setAttribute("transaction", transaction);

                request.setAttribute("shippingAddress", shippingAddress);
                request.setAttribute("totalResult", totalResult);
                url = SUCCESS + "?paymentId=" + paymentId + "&PayerID=" + PayerId;
            } else {
                request.setAttribute("ERROR", "Sorry! Something went wrong with your cart.");
            }
        } catch (PayPalRESTException e) {
            System.out.println("Error at ReviewPaymentService ne Vu: " + e.toString());
            request.setAttribute("ERROR", "Sorry! We can't payment right now");
        } catch (Exception e) {
            request.setAttribute("ERROR", "Sorry! Something went wrong with your cart.");
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
