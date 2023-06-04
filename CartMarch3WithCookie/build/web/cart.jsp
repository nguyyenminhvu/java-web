<%-- 
    Document   : cart
    Created on : Mar 1, 2023, 10:58:43 PM
    Author     : ACER
--%>

<%@page import="model.Cart"%>
<%@page import="model.Product"%>
<%@page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <style>
            td{
                text-align: center
            }
        </style>

        <c:if test="${not(requestScope.mapCart eq null)}">
            <table border='1px solid black' width='25%'>
                <thead>
                <th>No</th>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total Price</th>
                <th>Action</th>
            </thead>
            <tbody>
                <c:set var="cart" value="${requestScope.mapCart}" />
                <c:forEach items="${cart.values()}" var="c">
                    <c:set value="${count+1}" var="count1"/>
                    <tr>
                        <td> ${count1} </td>
                        <td> ${c.name} </td>
                        <td> ${c.price } </td>
                        <td><form action="ProccessController" method="POST">
                                <input name="id" value="${c.id}" hidden/>
                                <button name="change" value="-1">-</button>  ${c.quantity }  <button name="change" value="1">+</button>
                                <input  name="action" value="ChangeQuantity" hidden/>
                            </form></td>
                        <td>${c.price*c.quantity}</td>
                        <td><form action="ProccessController" method="POST">
                                <input name="id" value="${c.id}" hidden/>
                                <button name="action" value="Remove">Remove</button>
                            </form></td>
                    </tr>

                </c:forEach>
            </tbody>

        </table>
        <h2>Total Price: ${requestScope.totalPrice}</h2>
    </c:if>
    <c:if test="${requestScope.mapCart eq null}">
        <h2>Cart Empty</h2>
    </c:if>

    <a href="ListController"> <button>Get Back To Shop</button></a>
    <a href="CheckOutController"><button>Check Out</button></a>

</body>
</html>
