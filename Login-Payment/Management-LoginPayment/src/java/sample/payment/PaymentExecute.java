/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.payment;

import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.shopping.Cart;
import sample.shopping.ProductDAO;
import sample.user.UserDAO;
import sample.user.UserDTO;

/**
 *
 * @author ACER
 */
@WebServlet(name = "PaymentExecute", urlPatterns = {"/PaymentExecute"})
public class PaymentExecute extends HttpServlet {

    private static final String SUCCESS = "receipt.jsp";
    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String paymentId = request.getParameter("paymentId");
        String PayerID = request.getParameter("PayerID");
        int n = 0;
        String url = ERROR;
        UserDAO userDAO = new UserDAO();
        ProductDAO productDAO = new ProductDAO();
        HttpSession session = request.getSession();
        boolean check = false;
        try {
            Cart cart = (Cart) session.getAttribute("CART");
            UserDTO userDTO = (UserDTO) session.getAttribute("LOGIN_USER");
            UserDTO userDTO1 = userDAO.getUserById(userDTO.getUserID());
            if (userDTO1 != null && cart != null) {
                if (productDAO.createOrder(userDTO1, cart)) {
                    check = true;
                }
            }
            if (check) {
                PaymentService paymentServices = new PaymentService();
                Payment payment = paymentServices.executePayment(paymentId, PayerID);
                PayerInfo payerInfo = payment.getPayer().getPayerInfo();
                Transaction transaction = payment.getTransactions().get(0);
                request.setAttribute("payer", payerInfo);
                request.setAttribute("transaction", transaction);
                url = SUCCESS;
                session.removeAttribute("CART");
            } else {
                request.setAttribute("Sorry ! We can't payment right now, try login again !!", n);
            }
        } catch (PayPalRESTException e) {
            System.out.println("Error at ExecutePaymentServlet ne Vu: " + e.toString());
            request.setAttribute("ERROR", "Sorry! We can't payment right now. Let's try Login again !!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PaymentExecute.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("ERROR", "Sorry! Some thing went wrong with your CART. Let's try Login again !!");
        } catch (Exception ex) {
            request.setAttribute("ERROR", "Sorry! Some thing went wrong with your CART. Let's try Login again !!");
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
