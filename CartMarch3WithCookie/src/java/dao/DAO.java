/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Product;

/**
 *
 * @author ACER
 */
public class DAO extends DBContext {

    public List<Product> getAllProduct() {
        String sql = "select * from product";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt("id"), rs.getString("name"), rs.getFloat("price"), rs.getInt("quantity"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Product getProductById(int id) {
        String sql = "select * from product where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product p = new Product(rs.getInt("id"), rs.getString("name"), rs.getFloat("price"), rs.getInt("quantity"));
                return p;
            }
        } catch (SQLException e) {
            System.out.println("Error at DAO: " + e);
        }
        return null;
    }

    public boolean checkOut(Map<Integer, Product> mapCart) {
        boolean check = false;
        int sizes = mapCart.size();
        for (Map.Entry<Integer, Product> entry : mapCart.entrySet()) {
            String sql = "update product set quantity=? where id=?";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setInt(1, getProductById(entry.getValue().getId()).getQuantity() - entry.getValue().getQuantity());
                st.setInt(2, entry.getKey());
                st.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error at DAO: " + e);
            }
            sizes = sizes - 1;
        }
        if (sizes <= 0) {
            check = true;
        }
        return check;
    }

    public static void main(String[] args) {
        DAO d = new DAO();
        List<Product> list = d.getAllProduct();
        System.out.println(list.size());
    }
}
