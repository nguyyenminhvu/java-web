<%-- 
    Document   : user
    Created on : Feb 8, 2023, 11:27:54 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<html>

    <head>
        <title>User</title>
    </head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/css/iziToast.min.css"
          integrity="sha512-O03ntXoVqaGUTAeAmvQ2YSzkCvclZEcPQu1eqloPaHfJ5RuNGiS4l+3duaidD801P50J28EHyonCV06CUlTSag=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
          integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link rel="stylesheet" href="css/user.css">
    <link rel="stylesheet" href="css/menuDropdown.css">


    <body style="background-image: url(./img/pexels-andreea-ch-1166644.jpg);">
        <c:if test="${sessionScope.LOGIN_USER.roleID ne 'US'|| sessionScope.LOGIN_USER==null}">
            <c:redirect url="login.html"></c:redirect>
        </c:if>
        <br/>
        <br/>
        <br/>
        <br/>
        <section>
            <div class="container-fluid">
                <form action="">
                    <div class="row">
                        <div class="col-lg-4 col-md-2">
                        </div>
                        <div class="col-lg-4 cuscol col-md-8">
                            <div class="frameOver">
                                <div class="frameOver_x">
                                    <div class="frameOver_welcome">
                                        <h4 style="text-shadow: 1px 2px #c5c2c2;">Your Information (US)</h4>
                                    </div>
                                    <div class="groupInput">
                                        <div class="titleInput">
                                            Username <b style="color: red;">*</b>
                                        </div>
                                        <div class="typeInput">
                                            <input class="typeInput__content" type="text" placeholder="Username" value="${sessionScope.LOGIN_USER.userID}" disabled>
                                        </div>
                                        <div class="objectError">
                                            <p class="cusError"></p>
                                        </div>
                                    </div>
                                    <div class="groupInput">
                                        <div class="titleInput">
                                            Password <b style="color: red;">*</b>
                                        </div>
                                        <div class="typeInput">
                                            <input class="typeInput__content" type="password" placeholder="Password" value='********'
                                                   disabled>
                                        </div>
                                        <div class="objectError">
                                            <p class="cusError"></p>
                                        </div>
                                    </div>
                                    <div class="groupInput">
                                        <div class="titleInput">
                                            Full Name <b style="color: red;">*</b>
                                        </div>
                                        <div class="typeInput">
                                            <input class="typeInput__content" type="text" placeholder="Full Name" value="${sessionScope.LOGIN_USER.fullName}" disabled> 
                                        </div>
                                        <div class="objectError">
                                            <p class="cusError"></p>
                                        </div>
                                    </div>
                                    <div class="groupInput">
                                        <div class="titleInput">
                                            Role <b style="color: red;">*</b>
                                        </div>
                                        <div class="typeInput">
                                            <input class="typeInput__content" type="text" placeholder="Role" value="${sessionScope.LOGIN_USER.roleID}"
                                                   disabled>
                                        </div>
                                        <div class="objectError">
                                            <p class="cusError"></p>
                                        </div>
                                    </div>

                                    <div class="buttonSubmit">
                                        <a href="MainController?action=Logout"> <button type="button" class="btn btn-secondary cusButton">Log Out</button></a>
                                    </div>
                                </div>
                            </div>
                            <div style="position: absolute; width: 70%;display: flex; justify-content: end;height: 50px; left: 0" >
                                <div style="position: relative; right: ;bottom: 0;height: 100%; display: flex;justify-content: end;flex-direction: column">
                                    <nav>
                                        <menu>
                                            <menuitem id="demo1">
                                            <a id="mainDrop"><div style="width: 70px;border-radius: 20px ; overflow: hidden" ><img style="width: 100%" 
                                                                                                                                   src="img/146-1468479_my-profile-icon-blank-profile-picture-circle-hd.png" /> </div></i></a>
                                            <menu>
                                                <br>
                                                <menuitem><a style="text-decoration: none;" href="ShoppingController" id="mainDrop1"><i style="padding-right:  8px;" class="fa-solid fa-fan"></i>Flower Shop</a></menuitem>
                                                <menuitem><a style="text-decoration: none;" href="MainController?action=Logout" id="mainDrop1"><i style="padding-right:  8px;" class="fa-solid fa-right-to-bracket"></i>Log Out</a>
                                                </menuitem>
                                            </menu>
                                            </menuitem>
                                        </menu>
                                    </nav>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-2">
                        </div>
                    </div>
                </form>
            </div>
        </section>

        <script>
            function shop() {
                window.location = 'ShoppingController';
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