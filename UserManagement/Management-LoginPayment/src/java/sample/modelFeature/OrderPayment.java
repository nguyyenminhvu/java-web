/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.modelFeature;

/**
 *
 * @author ACER
 */
public class OrderPayment {

    private String productName;
    private float subTotal;
    private float shipping;
    private float tax;
    private int quantity;
    private float price;

    public OrderPayment(String productName, String subTotal, String shipping, String tax,  int quantity, String price) {
        this.productName = productName;
        this.subTotal = Float.parseFloat(subTotal);
        this.shipping = Float.parseFloat(shipping);
        this.tax = Float.parseFloat(tax);
        this.price = Float.parseFloat(price);
        this.quantity = quantity;
    }

    public String getPrice() {
        return String.format("%.2f", price);
    }

    public String getQuantity() {
        return Integer.toString(quantity);
    }

    public String getProductName() {
        return productName;
    }

    public String getSubTotal() {
        return String.format("%.2f", (quantity * price));
    }

    public String getShipping() {
        return String.format("%.2f", shipping);
    }

    public String getTax() {
        return String.format("%.2f", tax);
    }

}
