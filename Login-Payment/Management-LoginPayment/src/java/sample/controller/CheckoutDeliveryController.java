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
import sample.modelFeature.BillDelivery;
import sample.modelFeature.ShippingAddress;
import sample.shopping.Cart;
import sample.shopping.Product;
import sample.shopping.ProductDAO;
import sample.user.UserDAO;
import sample.user.UserDTO;

/**
 *
 * @author ACER
 */
@WebServlet(name = "CheckoutDeliveryController", urlPatterns = {"/CheckoutDeliveryController"})
public class CheckoutDeliveryController extends HttpServlet {

    private static final double TAX = 10.0;
    private static final double SHIPPING = 5.0;
    private static final String SUCCESS = "receiptDelivery.jsp";
    private static final String ERROR = "error.jsp";

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
        HttpSession session = request.getSession();
        ProductDAO dao = new ProductDAO();
        UserDAO uDao = new UserDAO();
        try {
            UserDTO userDTO = (UserDTO) session.getAttribute("LOGIN_USER");
            UserDTO user = uDao.getUserById(userDTO.getUserID());
            Cart cart = (Cart) session.getAttribute("CART");
            if (userDTO != null && user != null) {
                if (dao.createOrder(user, cart)) {
                    String firstName = request.getParameter("firstName");
                    String lastName = request.getParameter("lastName");
                    String email = request.getParameter("email");
                    String phone = request.getParameter("phone");
                    String address = request.getParameter("address");
                    double subTotal = 0;
                    double total = 0;
                    double tax = TAX * cart.getCart().size();
                    double shipping = SHIPPING * cart.getCart().size();
                    for (Product p : cart.getCart().values()) {
                        subTotal += p.getPrice() * p.getQuantity();
                    }
                    total += subTotal + tax + shipping;
                    ShippingAddress shippingAddress = new ShippingAddress(firstName, lastName, email, phone, address);
                    BillDelivery billDelivery = new BillDelivery(subTotal, tax, shipping, total, shippingAddress);
                    request.setAttribute("bill", billDelivery);
                    url = SUCCESS;
                    session.removeAttribute("CART");
                }
            }
        } catch (Exception e) {
            System.out.println("Error at CheckoutDeliveryController" + e.toString());
            request.setAttribute("ERROR", "Something went wrong with your cart, back home or try login again !!");
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
