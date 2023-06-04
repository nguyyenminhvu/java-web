/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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

    public void test() {

    }

    public static void main(String[] args) {
        Product p = new Product(2, "He", 23, 4);
        Cart cart = new Cart();
        cart.addToCart(p);
        cart.getSize();
        System.out.println(cart.getSize());
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

    public float getTotalPrice() {
        float result = 0;
        for (Map.Entry<Integer, Product> entry : cart.entrySet()) {
            result += entry.getValue().getQuantity() * entry.getValue().getPrice();
        }
        return result;
    }

    public Product getProductById(int id) {
        for (Map.Entry<Integer, Product> entry : cart.entrySet()) {
            if (entry.getKey() == id) {
                return entry.getValue();
            }
        }
        return null;
    }

    public boolean addToCart(Product p) {
        boolean check = false;
        if (this.cart == null) {
            this.cart = new HashMap<>();
        }
        if (this.cart.containsKey(p.getId())) {
            check = true;
            int quantity_raw = this.cart.get(p.getId()).getQuantity() + p.getQuantity();
            p.setQuantity(quantity_raw);
        }
        if (p.getQuantity() < 1) {
            removeFromCart(p);
        } else {
            cart.put(p.getId(), p);
        }

        return check;
    }

    public int getSize() {
        return cart.size();
    }

    public boolean removeFromCart(Product p) {
        boolean check = false;
        boolean check1 = false;
        for (Integer key : cart.keySet()) {
            if (key.equals(p.getId())) {
                check1 = true;
            }
        }
        if (check1) {
            cart.remove(p.getId());
            check = true;
        }
        return check;
    }

}
