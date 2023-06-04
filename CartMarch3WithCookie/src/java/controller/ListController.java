/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;
import model.Product;

/**
 *
 * @author ACER
 */
@WebServlet(name = "ListController", urlPatterns = {"/ListController"})
public class ListController extends HttpServlet {

    private static final String SUCCESS = "home.jsp";
    private static final String CART_PAGE = "cart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        Cookie cookie[] = request.getCookies();
        String txt = "";
        DAO dao = new DAO();
        Cart cart = new Cart();
        Object ob = request.getAttribute("size");
        try {
            if (ob == null) {
                if (cookie != null) {
                    for (Cookie c : cookie) {
                        if (c.getName().equals("CART")) {
                            txt = c.getValue();
                        }
                    }
                }
                if (!txt.isEmpty()) {
                    int test = cart.addToCart(txt).size();
                    request.setAttribute("size", cart.addToCart(txt).size());
                }
            }
            List<Product> listProduct = dao.getAllProduct();
            request.setAttribute("listProduct", listProduct);

        } catch (ExceptionInInitializerError e) {
            System.out.println("Error at ListController: " + e.toString());
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
