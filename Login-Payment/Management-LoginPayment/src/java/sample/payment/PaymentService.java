/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.payment;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import sample.modelFeature.OrderPayment;
import sample.shopping.Cart;

/**
 *
 * @author ACER
 */
public class PaymentService {

    private static final String CLIENT_ID = "ASMZzrjokluzYyGqL89LEABYqOfuhhp4sjR7zkvs0_SxjFjZdplad8ArOAxnkwhi5ucW2tgfWvUbpBkQ";
    private static final String CLIENT_SECRET = "ELjUv9DIZ6qyiZWrGJczQH-umvvscjhDR5rkDyLXabJt234P2FPoqYH3kePrdFuhzul5FrsIqjdMFmjF";
    private static final String MODE = "sandbox";

    public String authorizePayment(List<OrderPayment> orderPayments) throws PayPalRESTException {
        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransactions = getTransactionInformation(orderPayments);
        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransactions).setRedirectUrls(redirectUrls).setPayer(payer)
                .setIntent("authorize");

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        Payment approvedPayment = requestPayment.create(apiContext);

        System.out.println(approvedPayment);
        return getApprovalLink(approvedPayment);
    }

    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;
        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
            }
        }
        return approvalLink;
    }

    private List<Transaction> getTransactionInformation(List<OrderPayment> orderPayments) {
        List<Transaction> listTransaction = new ArrayList<Transaction>();
        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<Item>();
        Transaction transaction = new Transaction();
        float shipping_raw = 0;
        float tax_raw = 0;
        float subtotal_raw = 0;
        float total_raw = 0;
        for (OrderPayment item2 : orderPayments) {
            shipping_raw += Float.parseFloat(item2.getShipping());
            tax_raw += Float.parseFloat(item2.getTax());
            subtotal_raw += Float.parseFloat(item2.getSubTotal());
        }
        total_raw = subtotal_raw + tax_raw + shipping_raw;
        Details details = new Details();
        details.setShipping(String.format("%.2f", shipping_raw));
        details.setTax(String.format("%.2f", tax_raw));
        details.setSubtotal(String.format("%.2f", subtotal_raw));

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(String.format("%.2f", total_raw));
        amount.setDetails(details);

        transaction.setAmount(amount);
        transaction.setDescription("Giao dich ngam");
        for (OrderPayment orderDetail2 : orderPayments) {
            Item item = new Item();
            item.setCurrency("USD").setName(orderDetail2.getProductName()).setPrice(orderDetail2.getPrice())
                    .setQuantity(orderDetail2.getQuantity());
            String u = orderDetail2.getProductName();
            System.out.println("name ne" + u);
            items.add(item);
        }

        itemList.setItems(items);
        transaction.setItemList(itemList);
        listTransaction.add(transaction);

        System.out.println(listTransaction.get(0).getAmount().getDetails().getTax());
        return listTransaction;
    }

    private RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8084/UserManagerment_T4S2_JSP_JSTL/viewCart.jsp");
        redirectUrls.setReturnUrl("http://localhost:8084/UserManagerment_T4S2_JSP_JSTL/review_payment");
        return redirectUrls;
    }

    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        return Payment.get(apiContext, paymentId);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        Payment payment = new Payment().setId(paymentId);

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        return payment.execute(apiContext, paymentExecution);
    }

    private Payer getPayerInformation() {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName("Nguyen Minh").setLastName("Vu").setEmail("nguyyenminhvu@gmail.com");
        payer.setPayerInfo(payerInfo);
        return payer;
    }

}
