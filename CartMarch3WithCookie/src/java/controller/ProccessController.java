/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cart;
import model.Product;

/**
 *
 * @author ACER
 */
@WebServlet(name = "ProccessController", urlPatterns = {"/ProccessController"})
public class ProccessController extends HttpServlet {

    private static final String SUCCESS = "LoadCartController";

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
        String url = SUCCESS;
        Cookie cook[] = request.getCookies();
        String txt = "";
        String action = request.getParameter("action");
        for (Cookie cookie : cook) {
            if (cookie.getName().equalsIgnoreCase("CART")) {
                txt = cookie.getValue();
            }
        }
        Cart cart = new Cart();
        try {
            if (!txt.isEmpty()) {
                Map<Integer, Product> mapCart = cart.addToCart(txt);
                int id = Integer.parseInt(request.getParameter("id"));
                if ("ChangeQuantity".equalsIgnoreCase(action)) {
                    int change = Integer.parseInt(request.getParameter("change"));
                    if (change == -1 && mapCart != null) {
                        if (mapCart.get(id).getQuantity() > 1) {
                            mapCart.get(id).setQuantity(mapCart.get(id).getQuantity() + change);
                        } else if (mapCart.get(id).getQuantity() == 1) {
                            mapCart.remove(id);
                        }
                    } else if (change == 1 && mapCart != null) {
                        DAO dao = new DAO();
                        if (dao.getProductById(id).getQuantity() > mapCart.get(id).getQuantity()) {
                            mapCart.get(id).setQuantity(mapCart.get(id).getQuantity() + change);
                        }
                    }
                } else if ("Remove".equalsIgnoreCase(action)) {
                    if (mapCart.containsKey(id)) {
                        mapCart.remove(id);
                    }
                }
                txt = "";
                if (mapCart != null) {
                    for (Map.Entry<Integer, Product> entry : mapCart.entrySet()) {
                        if (txt.isEmpty()) {
                            txt = entry.getKey() + ":" + entry.getValue().getQuantity();
                        } else {
                            txt += "," + entry.getKey() + ":" + entry.getValue().getQuantity();
                        }
                    }
                    request.setAttribute("txt", txt);
                    Cookie cookie = new Cookie("CART", txt);
                    cookie.setMaxAge(3 * 60 * 60 * 24);
                    response.addCookie(cookie);
                } else {
                    for (Cookie cookie : cook) {
                        if (cookie.getName().equalsIgnoreCase("CART")) {
                            cookie.setMaxAge(0);
                        }
                    }
                }
            }

        } catch (NumberFormatException e) {
            request.setAttribute("catches", "Bi catch");
            System.out.println("Error at ProccessController: " + e.toString());
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
