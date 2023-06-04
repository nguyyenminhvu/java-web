/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.DAO;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ACER
 */
public class Cart {

    private Map<Integer, Product> cart;

    public Cart() {
    }

    public Cart(Map<Integer, Product> cart) {
        this.cart = cart;
    }

    public Map<Integer, Product> getCart() {
        return cart;
    }

    public void setCart(Map<Integer, Product> cart) {
        this.cart = cart;
    }

    public void addToCart(Product p, Map<Integer, Product> mapPro) {

    }

    public Map<Integer, Product> addToCart(String txt) {
        String s[] = txt.split(",");
        dao.DAO dao = new DAO();
        Map<Integer, Product> mapPro = new HashMap<>();
        for (String ss : s) {
            String temp[] = ss.split(":");
            Product newPro = new Product(Integer.parseInt(temp[0]),
                    dao.getProductById(Integer.parseInt(temp[0])).getName(),
                    dao.getProductById(Integer.parseInt(temp[0])).getPrice(),
                    Integer.parseInt(temp[1]));
            if (mapPro.containsKey(newPro.getId())) {
                int quantity = mapPro.get(newPro.getId()).getQuantity() + newPro.getQuantity();
                newPro.setQuantity(quantity);
            }
            if (newPro.getQuantity() < 1) {
                if (mapPro.containsKey(newPro.getId())) {
                    mapPro.remove(newPro.getId());
                }
            } else {
                mapPro.put(newPro.getId(), newPro);
            }
        }
        return mapPro;
    }

    public void removeCart(Product p) {
        if (this.cart.containsKey(p.getId())) {
            this.cart.remove(p.getId());
        }
    }

    public Product getProductByIdInCart(int id) {
        if (this.cart.containsKey(id)) {
            return this.cart.get(id);
        }
        return null;
    }

    public int getSizeCart() {
        return this.cart.size();
    }

    public static void main(String[] args) {
        Cart cart = new Cart();
        String txt = "1:1,1:2,2:2";
        System.out.println(cart.addToCart(txt).size());
    }
}
