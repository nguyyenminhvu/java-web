<%-- 
    Document   : adminInactive
    Created on : Mar 18, 2023, 8:52:10 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>
            Home
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
        <link rel="stylesheet" href="css/inActive.css">
        <link rel="stylesheet" href="css/menuDropdown.css">
    </head>

    <body style="background-color: rgb(43, 30, 54);">
        <c:if test="${sessionScope.LOGIN_USER.roleID ne 'AD'|| sessionScope.LOGIN_USER==null}">
            <c:redirect url="login.html"></c:redirect>
        </c:if>
        <section>
            <div class="welcomeP1">
                <div class="welcomePage">
                    <div class="welcomePage__group">
                        <div>
                            <h1>Welcome</h1>
                        </div>
                        <div class="nameAdmin">
                            <h1>${sessionScope.LOGIN_USER.fullName} (Inactive User Page)</h1>
                        </div>
                    </div>
                    <br/>
                    <hr/>
                </div>
                <div style="position: absolute; width: 85.6%;display: flex; justify-content: end;height: 110px; left: 0" >
                    <div style="position: relative; right: ;bottom: 0;height: 100%; display: flex;justify-content: end;flex-direction: column">
                        <nav>
                            <menu>
                                <menuitem id="demo1">
                                <a id="mainDrop"><div style="width: 70px;border-radius: 20px ; overflow: hidden" ><img style="width: 100%" 
                                                                                                                       src="img/146-1468479_my-profile-icon-blank-profile-picture-circle-hd.png" /> </div></i></a>
                                <menu>
                                    <br>
                                    <menuitem><a style="text-decoration: none;" href="ShoppingController" id="mainDrop1"><i style="padding-right:  8px;" class="fa-solid fa-fan"></i>Flower Shop</a></menuitem>
                                    <menuitem><a style="text-decoration: none;" href="ListController" id="mainDrop1"><i style="padding-right:  8px;" class="fa-solid fa-fan"></i>Admin Page</a></menuitem>
                                    <menuitem><a style="text-decoration: none;" href="MainController?action=Logout" id="mainDrop1"><i style="padding-right:  8px;" class="fa-solid fa-right-to-bracket"></i>Log Out</a>
                                    </menuitem>
                                </menu>
                                </menuitem>
                            </menu>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <section>
            <div class="searchG1">
                <div class="searchGroup">
                    <div class="iconSearch">
                        <i style="margin: auto;color: aliceblue;" class="fa-solid fa-magnifying-glass"></i>
                    </div>
                    <div class="groupInputSearch">
                        <form  action="MainController">
                            <input class="inputSearch" type="text" placeholder="Search....." value="${param.search}" name="search">
                            <input name="action" value="SearchInactive" hidden/>
                            <input class="cusSubmit" type="submit"  value="Search"/>
                        </form>
                    </div>
                </div>
            </div>
            <br/>
            <c:if test="${requestScope.LIST_USER ne null}">
                <div class="frame_2">
                    <table>
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>User ID</th>
                                <th>Full Name</th>
                                <th>Role ID</th>
                                <th>Password</th>
                                <th>Status</th>
                                <th>Set Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.LIST_USER}" var="list" varStatus="loop">
                                <tr>
                                    <td>
                                        ${loop.index+1}
                                    </td>
                                    <td>
                                        ${list.userID}
                                    </td>
                                    <td>
                                        ${list.fullName}
                                    </td>
                                    <td>
                                        ${list.roleID}
                                    </td>
                                    <td>
                                        ********
                                    </td>
                                    <td style="width: 10%;text-align: center;padding: 0;">
                                        <i style="font-size: 150%;" class="fa-brands fa-expeditedssl"></i>
                                    </td>
                                    <td style="width: 10%;text-align: center;padding: 10px 0;">
                                        <button onclick="asking('${list.userID}', '${requestScope.rs}')" type="button" class="btn btn-success cusRemove"><i
                                                class="fa-solid fa-key" name="action" value="Delete"></i></button>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </div>
            </c:if>
            <br/>
            <br/>
            <br/>
            <c:if test="${requestScope.LIST_USER eq null }">
                <h2 style="text-align: center;color: white">No users are down</h2>
            </c:if>
        </section>
        <div style="height: 250px">
        </div>
        <script>
            function asking(id, search) {
                if (confirm('Are you sure? ' + id + ' will be back !!')) {
                    window.location = 'MainController?action=Delete&userId=' + id + '&search=' + search + '&status=0';
                }
            }
            function submitFail(id) {
                iziToast.error({
                    title: 'FARM: ',
                    message: id,
                    position: 'topRight',
                });
            }
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