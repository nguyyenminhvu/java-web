/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sample.user.UserDTO;
import sample.utils.DBUtils;

/**
 *
 * @author ACER
 */
public class ProductDAO {

    private static final String SELECT_ALL = "SELECT Product.id, Product.name,Product.price,Product.quantity,Product.img FROM Product;";
    private static final String CREATE_ORDER = "INSERT INTO Orders (userId,totalMoney,dateCreate) VALUES(?,?,?);";
    private static final String CREATE_ORDER_DETAIL = "INSERT INTO OrderDetail VALUES(?,?,?,?,?,?);";
    private static final String UPDATE_PRODUCT = "UPDATE Product SET quantity=? WHERE id=?";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT id, name, price, quantity,img FROM Product WHERE id=?;";
    private static final double TAX = 10.0;
    private static final double SHIPPING = 5.0;

    public List<Product> getAllProduct() throws ClassNotFoundException {
        List<Product> listProduct = new ArrayList<>();
        try {
            PreparedStatement st = DBUtils.getConnection().prepareStatement(SELECT_ALL);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getString("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("quantity"), rs.getString("img"));
                listProduct.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error at getAllProduct: " + e.toString());
        }
        return listProduct;
    }

    public void createOrderDetail(int orderId, Cart cart) throws ClassNotFoundException {
        try {
            for (Map.Entry<String, Product> en : cart.getCart().entrySet()) {
                PreparedStatement st = DBUtils.getConnection().prepareStatement(CREATE_ORDER_DETAIL);
                st.setInt(1, orderId);
                st.setString(2, en.getValue().getId());
                st.setInt(3, en.getValue().getQuantity());
                st.setDouble(4, en.getValue().getPrice());
                st.setDouble(5, TAX);
                st.setDouble(6, SHIPPING);
                st.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error at CreateOrderDetail(ProductDAO): " + e.toString());
        }
    }

    public Product getProductById(String id) throws ClassNotFoundException {
        try {
            PreparedStatement st = DBUtils.getConnection().prepareStatement(SELECT_PRODUCT_BY_ID);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product p = new Product(id, rs.getString("name"), rs.getDouble("price"), rs.getInt("quantity"), rs.getString("img"));
                return p;
            }
        } catch (SQLException e) {
            System.out.println("Error at getProductById(ProductDAO): " + e.toString());
        }
        return null;
    }

    public void updateProduct(Product p) throws ClassNotFoundException {
        try {
            PreparedStatement st = DBUtils.getConnection().prepareStatement(UPDATE_PRODUCT);
            st.setInt(1, getProductById(p.getId()).getQuantity() - p.getQuantity());
            st.setString(2, p.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error at updateProduct(ProductDAO): " + e.toString());
        }
    }

    public double getTotal(Cart cart) {
        double total = 0;
        for (Map.Entry<String, Product> entry : cart.getCart().entrySet()) {
            total += entry.getValue().getPrice() * entry.getValue().getQuantity();
        }
        total += TAX * cart.getCart().size() + SHIPPING * cart.getCart().size();
        return total;
    }

    public boolean createOrder(UserDTO user, Cart cart) throws ClassNotFoundException {
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        boolean check = false;
//        Cart carts = (Cart) cart.getCart();
        double totalMoney = getTotal(cart);
        try {
            PreparedStatement st = DBUtils.getConnection().prepareStatement(CREATE_ORDER);
            st.setString(1, user.getUserID());
            st.setDouble(2, totalMoney);
            st.setDate(3, date);
            st.executeUpdate();
            String sql = "SELECT TOP 1 id FROM Orders ORDER BY id DESC;";
            PreparedStatement st1 = DBUtils.getConnection().prepareStatement(sql);
            ResultSet rs = st1.executeQuery();
            if (rs.next()) {
                int orderId = rs.getInt(1);
                createOrderDetail(orderId, cart);
                for (Map.Entry<String, Product> entry : cart.getCart().entrySet()) {
                    updateProduct(entry.getValue());
                }
                check = true;
            }
        } catch (SQLException e) {
            System.out.println("Error at createOrder(ProductDAO): " + e.toString());
        }
        return check;
    }

//    public static void main(String[] args) throws ClassNotFoundException {
////        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
////        LocalDateTime now = LocalDateTime.now();
////        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
////    
////        UserDTO user = new UserDTO("test", "testest", "US", "test");
//        ProductDAO dao = new ProductDAO();
////        List<Product> pList = dao.getAllProduct();
////        for (int i = 0; i < pList.size(); i++) {
////            System.out.println(pList.get(i).getImg());
////        }
////        System.out.println(dao.getAllProduct().size());
////        String t = "http://localhost:8084/UserManagerment_T4S2_JSP/login.html";
////        int index = t.lastIndexOf("/");
////        String resource = t.substring(index + 1);
//        UserDTO user = new UserDTO("test", "testest", "US", "test");
//        Product p = new Product("1", "Rose", 200, 999, "img/1.jpg");
//        Product p1 = new Product("2", "Impatiens", 250, 999, "img/2.jpg");
//        Product p2 = new Product("3", "Geranium", 100, 999, "img/3.jpg");
//        Map<String, Product> mapCart = new HashMap<>();
//        Cart cart = new Cart(mapCart);
//        cart.add(p);
//        cart.add(p1);
//        cart.add(p2);
//        dao.createOrder(user, cart);
//        //   System.out.println(dao.getProductById("1").getName());
//
//    }
}
//  private String id;
//    private String name;
//    private double price;
//    private int quantity;
//    private String img;
