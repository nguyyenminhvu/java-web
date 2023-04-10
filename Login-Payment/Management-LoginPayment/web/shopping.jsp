<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

    <head>
        <title>
            Shopping
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
        <link rel="stylesheet" href="./css/shopping.css">
        <link rel="stylesheet" href="./css/menuDropdown.css">

    </head>

    <body>
        <div style="height: 50px;">

        </div>
        <section>
            <div class="titleShop">
                <div class="titleShop__content">
                    <h1>FLOWER SHOP</h1>
                </div>

                <c:if test="${sessionScope.LOGIN_USER ne null}">
                    <div style="position: absolute; width: 90%;display: flex; justify-content: end;height: 128px; left: 0" >
                        <div class="iconGroup">
                            <div  class="iconGroup__x">
                                <a href="viewCart.jsp?total=${requestScope.total}"> <i class="fa-solid fa-cart-shopping cusIcon"></i></a>(${sessionScope.CART.getCart().size()})
                            </div>
                        </div>
                        <div style="position: relative; right: ;bottom: 0;height: 100%; display: flex;justify-content: end;flex-direction: column">
                            <nav>
                                <menu>
                                    <menuitem id="demo1">
                                    <a id="mainDrop"><div style="width: 70px;border-radius: 20px ; overflow: hidden" ><img style="width: 100%" 
                                                                                                                           src="img/146-1468479_my-profile-icon-blank-profile-picture-circle-hd.png" /> </div></i></a>
                                    <menu>
                                        <br>
                                        <c:if test="${sessionScope.LOGIN_USER.roleID eq 'US'}">
                                            <menuitem><a style="text-decoration: none;" href="user.jsp" id="mainDrop1"><i style="padding-right:  8px;" class="fa-solid fa-user"></i>Your Information</a></menuitem>
                                            </c:if>
                                            <c:if test="${sessionScope.LOGIN_USER.roleID eq 'AD'}">
                                            <menuitem><a style="text-decoration: none;" href="ListController" id="mainDrop1"><i style="padding-right:  8px;" class="fa-solid fa-user"></i>Your Information</a></menuitem>
                                            </c:if>
                                        <menuitem><a style="text-decoration: none;" href="ShoppingController" id="mainDrop1"><i style="padding-right:  8px;" class="fa-solid fa-fan"></i>Flower Shop</a></menuitem>
                                        <menuitem><a style="text-decoration: none;" href="MainController?action=Logout" id="mainDrop1"> <i style="padding-right:  8px;" class="fa-solid fa-right-to-bracket"></i>Log Out</a>
                                        </menuitem>
                                    </menu>
                                    </menuitem>
                                </menu>
                            </nav>
                        </div>
                        <div class="iconGroup">
                            <div style="  display: flex;
                                 flex-direction: column;
                                 justify-content: center;" class="iconGroup__x">
                                <b>${sessionScope.LOGIN_USER.fullName}</b>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${sessionScope.LOGIN_USER eq null}">
                    <div style="position: absolute; width: 89%;display: flex; justify-content: end;height: 118px; left: 0" >
                        <div style="position: relative; right: ;bottom: 0;height: 100%; display: flex;justify-content: end;flex-direction: column">
                            <a href="login.html"><button type="button" class="btn btn-success">Login<i style="padding-left: 8px" class="fa-solid fa-eye"></i></button></a>
                        </div>
                    </div>
                </c:if>
            </div>
        </section>
        <div class="container">
            <hr>
        </div>

        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-8">
                    <div class="container-fluid">
                        <div  class="row">
                            <c:forEach items="${requestScope.listProduct}" var="p">
                                <div style="margin-top:50px;" class="col-lg-2 cusCol2">
                                    <form action="MainController" method="POST">
                                        <div class="cusImg">
                                            <img src="${p.img}"
                                                 alt="">
                                        </div>
                                        <div class="tilteFlower">
                                            ${p.name}
                                        </div>
                                        <div class="priceFlower">
                                            $${p.price}
                                        </div>
                                        <div class="buttonAdd">
                                            <div>
                                                <select class="selectAdd" name="quantity">
                                                    <option selected>1</option>
                                                    <option>2</option>
                                                    <option>3</option>
                                                    <option>4</option>
                                                    <option>5</option>
                                                    <option>8</option>
                                                    <option>10</option>
                                                    <input name="action" value="Add" hidden/>
                                                </select>
                                            </div>
                                            <div>
                                                <input type="text" name="id" value="${p.id}" hidden>
                                                <input type="text" name="name" value="${p.name}" hidden>
                                                <input type="text" name="price" value="${p.price}" hidden>
                                                <button type="submit" class="btn btn-dark">Add to Cart</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="col-lg-1">

                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
        </div>


        <script>
            window.onload = function submit() {
                iziToast.success({
                    title: 'FARM: ',
                    message: '${requestScope.Message} !',
                    position: 'topRight'
                })
                iziToast.fail
            }
        </script>
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