<%-- 
    Document   : viewCard
    Created on : Mar 4, 2023, 9:44:31 AM
    Author     : ACER
--%>

<%@page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sample.shopping.Product"%>
<%@page import="sample.shopping.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>
            Cart
        </title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/css/iziToast.min.css"
              integrity="sha512-O03ntXoVqaGUTAeAmvQ2YSzkCvclZEcPQu1eqloPaHfJ5RuNGiS4l+3duaidD801P50J28EHyonCV06CUlTSag=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
              integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <link rel="stylesheet" href="css/tableCart.css">
    </head>

    <body style="background-color: rgb(247, 243, 245);">
        <c:if test="${sessionScope.LOGIN_USER eq null }">
            <c:redirect url="login.html"></c:redirect>
        </c:if>
        <section>
            <div class="welcomeP1">
                <div class="welcomePage">
                    <div class="welcomePage__group">
                        <div>
                            <h1>Your Cart</h1>
                        </div>
                        <div class="nameAdmin">
                            <h1></h1>
                        </div>
                    </div>
                </div>
            </div>

        </section>
        <br>
        <br>
        <c:if test="${sessionScope.CART ne null}">
            <section>
                <div class="frame_2">
                    <table>
                        <thead>
                            <tr>
                                <th>No</th>
                                <th style="width: 10%"> ID</th>
                                <th style="width: 20%"> Name</th>
                                <th> Price</th>
                                <th class="quantityTh">Quantity</th>
                                <th>Total</th>
                                <th>Update</th>
                                <th>Remove</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set value="${sessionScope.CART.getCart()}" var="cart"/>
                            <c:forEach items="${cart.values()}" var="c"  varStatus="loop" >
                            <form action="MainController" method="POST">
                                <tr>
                                    <td>${loop.index+1}</td>
                                    <td>
                                        <input class="cusInputQ" type="text" name="id" value="${c.id}" readonly/>
                                    </td>
                                    <td>
                                        ${c.name}
                                    </td>
                                    <td>
                                        ${c.price}
                                    </td>
                                    <td>
                                        <input class="cusInputQ" type="number" name='quantity' min="1" value="${c.quantity}" required >
                                    </td>
                                    <td>
                                        ${c.price*c.quantity}
                                    </td>
                                    <td style="width: 10%;text-align: center;padding: 0;">
                                        <input class="inputCusS1" type="submit" name="action" value="Edit"
                                               readonly>
                                    </td>
                                    <td style="width: 10%;text-align: center;padding: 0;">
                                        <input class="inputCusS" type="submit" name="action" value="Remove"
                                               readonly>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </section>
        </c:if>
        <c:if test="${sessionScope.CART eq null}">
            <h1 style="text-align: center">Your Cart Empty !</h1>
        </c:if>
        <br>
        <section class="p3">
            <div class="groupButtonF">
                <div class="groupA">
                    <a href="ShoppingController"> <button type="button" class="btn btn-primary">Back To Shop</button></a>
                    <c:if test="${sessionScope.CART ne null}">
                        <a href="checkoutPage.jsp"><button type="button" class="btn btn-success">Check Out</button></a>
                    </c:if>
                </div>
            </div>
        </section>

        <br/>
        <br/>
        <br/>
        <br/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/core.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/md5.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/js/all.min.js"
                integrity="sha512-naukR7I+Nk6gp7p5TMA4ycgfxaZBJ7MO5iC3Fp6ySQyKFHOGfpkSZkYVWV5R7u7cfAicxanwYQ5D1e17EfJcMA=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/js/iziToast.min.js"
                integrity="sha512-Zq9o+E00xhhR/7vJ49mxFNJ0KQw1E1TMWkPTxrWcnpfEFDEXgUiwJHIKit93EW/XxE31HSI5GEOW06G6BF1AtA=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    </body>

</html>
