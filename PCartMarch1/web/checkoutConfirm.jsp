<%-- 
    Document   : checkoutConfirm
    Created on : Mar 13, 2023, 11:26:42 AM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check Out Page</title>
    </head>
    <body>
        <h1>Check Out Page!</h1>
        <form action="PaymentAuthor" >
            <table>
                   <c:set var="cart" value="${sessionScope.CART.getCart()}" />
                <c:forEach items="${cart.values()}" var="c" >
                    <tr>
                        <td><b>${c.name}</b></td>                    
                    </tr>
                    <tr>
                        <td>Price: </td>
                        <td>${c.price}</td>

                    </tr>
                    <tr>
                        <td>Quantity: </td>
                        <td>${c.quantity}</td>

                    </tr>
                    <tr>
                        <td>Shipping: </td>
                        <td>10$</td>
                    </tr>
                    <tr>
                        <td>Tax: </td>
                        <td>2$</td>                    
                    </tr>
                    <tr><td><br></td></tr>
                </c:forEach>
                  
            </table>
                   <h2>  <b> Total: <b/>${param.total} $ <h2/> 
                   
                  <input type="submit" value="Check Out"/>
        </form>
    </body>
</html>
