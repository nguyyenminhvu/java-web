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
        <link rel="stylesheet" href="css/receipts.css">
    </head>

    <body>
        <c:if test="${requestScope.bill eq null}">
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
                            THANK YOU FOR YOU CHOICE
                        </div>
                        <br>
                        <hr>
                        <div class="groupTable">
                            <table>
                                <tr>
                                    <td><b>Payment Information</b> </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>Merchane:</td>
                                    <td>Flower Store</td>
                                </tr>
                                <tr>
                                    <td>Payer: </td>
                                    <td>${requestScope.bill.shippingAddress.firstName} ${requestScope.bill.shippingAddress.lastName}</td>
                                </tr>
                                <tr>
                                    <td>Subtotal: </td>
                                    <td>${requestScope.bill.subTotal}</td>
                                </tr>
                                <tr>
                                    <td>Tax:</td>
                                    <td>${requestScope.bill.tax}</td>
                                </tr>
                                <tr>
                                    <td>Shipping Fee:</td>
                                    <td>${requestScope.bill.shipping}</td>
                                </tr>
                                <tr>
                                    <td>Total:</td>
                                    <td>${requestScope.bill.total}</td>
                                </tr>
                                <tr>
                                    <td><br></td>
                                </tr>
                                <tr>
                                    <td><b>Payer Information</b> </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>
                                        First Name: 
                                    </td>
                                    <td>
                                        ${requestScope.bill.shippingAddress.firstName}
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Last Name: 
                                    </td>
                                    <td>
                                        ${requestScope.bill.shippingAddress.lastName}
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Email:
                                    </td>
                                    <td>
                                        ${requestScope.bill.shippingAddress.email}
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Address:
                                    </td>
                                    <td>
                                        ${requestScope.bill.shippingAddress.address}
                                    </td>
                                </tr>

                            </table>
                        </div>
                        <br>
                        <hr>
                        <br/>
                        <div class="p3">
                            <div class="groupButtonF">
                                <div class="groupA">
                                    <a href="ShoppingController"> <button type="button" class="btn btn-primary">Back To Shop</button></a>
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