<%@page import="sample.shopping.Cart"%>
<%@page import="sample.shopping.Product"%>
<%@page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

    <head>
        <title>
            Check Out
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
        <link rel="stylesheet" href="css/checkoutPage.css">
    </head>
    <body>
        <c:if test="${sessionScope.CART eq null}">
            <c:redirect url="ShoppingController"></c:redirect>
        </c:if>
        <div style="height: 100px;">
        </div>
        <section>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-4">
                    </div>
                    <div class="col-lg-4">
                        <div class="tilte__check">
                            CHECK OUT PAGE
                        </div>
                        <br>
                        <hr>
                        <div class="groupTable">
                            <table>
                                <c:set value="${sessionScope.CART.getCart()}" var="cart"/>
                                <c:forEach items="${cart.values()}" var="c">
                                    <tr>
                                        <td><b>${c.name}</b></td>
                                    </tr>
                                    <tr>
                                        <td>Price:</td>
                                        <td>${c.price}</td>
                                    </tr>
                                    <tr>
                                        <td>Quantity:</td>
                                        <td>${c.quantity}</td>
                                    </tr>
                                    <tr>
                                        <td>Tax:</td>
                                        <td>10</td>
                                    </tr>
                                    <tr>
                                        <td>Shipping Fee:</td>
                                        <td>5</td>
                                    </tr>
                                    <tr>
                                        <td>${count1}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                        <br>
                        <hr>
                        <%  double totalResult = 0;
                            Cart cart = (Cart) session.getAttribute("CART");
                            Map<String, Product> cartP = cart.getCart();
                            for (Map.Entry<String, Product> en : cartP.entrySet()) {
                                totalResult += en.getValue().getPrice() * en.getValue().getQuantity();
                            }
                            totalResult += cartP.size() * 10 + cartP.size() * 5;
                        %>
                        <div class="groupTotal">
                            <div class="groupTotal__x">
                                <h3> Total: <b>$ <%=totalResult%></b></h3>
                            </div>
                        </div>
                        <br/>
                        <div class="p3">
                            <div class="groupButtonF">
                                <div class="groupA">
                                    <a href="MainController?action=Type"> <button type="button" class="btn btn-primary">Payment on delivery</button></a>
                                    <a href="PaymentConvert"><button type="button" class="btn btn-success">Payment with Paypal</button></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                    </div>
                </div>
            </div>
        </section>
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