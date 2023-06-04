/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ACER
 */
public class OrderPayment {

    private String productName;
    private float subTotal;
    private float shipping;
    private float tax;
    private float total;

    public OrderPayment(String productName, String subTotal, String shipping, String tax, String total) {
        this.productName = productName;
        this.subTotal = Float.parseFloat(subTotal);
        this.shipping = Float.parseFloat(shipping);
        this.tax = Float.parseFloat(tax);
        this.total = Float.parseFloat(total);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSubTotal() {
        return String.format("%.2f", subTotal);
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public String getShipping() {
        return String.format("%.2f", shipping);
    }

    public void setShipping(float shipping) {
        this.shipping = shipping;
    }

    public String getTax() {
        return String.format("%.2f", tax);
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public String getTotal() {
        return String.format("%.2f", total);
    }

    public void setTotal(float total) {
        this.total = total;
    }

}
