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
public class BillDelivery {

    private double subTotal;
    private double tax;
    private double shipping;
    private double total;
    private ShippingAddress shippingAddress;

    public BillDelivery() {
    }

    public BillDelivery(double subTotal, double tax, double shipping, double total, ShippingAddress shippingAddress) {
        this.subTotal = subTotal;
        this.tax = tax;
        this.shipping = shipping;
        this.total = total;
        this.shippingAddress = shippingAddress;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

}
