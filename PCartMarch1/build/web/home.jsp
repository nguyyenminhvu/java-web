<%-- 
    Document   : home
    Created on : Mar 1, 2023, 10:03:43 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Product</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/css/iziToast.min.css"
              integrity="sha512-O03ntXoVqaGUTAeAmvQ2YSzkCvclZEcPQu1eqloPaHfJ5RuNGiS4l+3duaidD801P50J28EHyonCV06CUlTSag=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
              integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&family=Roboto:wght@300&display=swap"
              rel="stylesheet">   
    </head>
    <body>
        <style>
            .cusA:hover{
                transform: scale(1.2,1.2);
                transition: 0.4s;
                cursor: pointer;
            }
        </style>
        <br/>
        <br/>
        <h1 style="text-align: center">Product</h1>
        <br/>
        <br/>
        <h2 style="text-align: center"><a style="color: black" href="cart.jsp" ><i class="fa-solid fa-cart-plus cusA"></i></a> ${requestScope.size}</h2>
        <br/>
        <br/>
        <div class="container">
            <div class="row">
                <c:forEach items="${requestScope.listProduct}" var="p">

                    <div style="border: 1px solid black; text-align: center;height: 150px;margin-top: 20px" class="col-lg-2">
                        <div style="display: flex; flex-direction: column;justify-content: space-around;height: 90%;">
                            <div style="font-size: 110%;font-weight: 600">${p.name}</div>
                            <div>Price: ${p.price}</div>
                            <div>Quantity: ${p.quantity}</div>
                            <form action="AddToCartController">
                                <input name="id" value="${p.id}" hidden/>
                                <input name="quantity" value="1" hidden/>
                                <div style="display: flex;justify-content: center"><button type="submit" style="width: 60%;border-radius: 20px; ">Add to Cart</button></div>
                            </form>
                        </div>
                    </div>

                </c:forEach>

            </div>
        </div>

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
