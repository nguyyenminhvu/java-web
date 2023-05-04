<html>

    <head>
        <title>Sign Up</title>
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
    <link rel="stylesheet" href="css/signUp.css">

    <body style="background-image: url(./img/pexels-andreea-ch-1166644.jpg);">
        <section>
            <div class="container-fluid">
                <form  action="MainController" method="post" id="f1">
                    <div class="row">
                        <div class="col-lg-4 col-md-2">
                        </div>
                        <div class="col-lg-4 cuscol col-md-8">
                            <div class="frameOver">
                                <div class="frameOver_x">
                                    <div class="frameOver_welcome">
                                        <h4 style="text-shadow: 1px 2px #c5c2c2;">Please type your Information</h4>
                                    </div>
                                    <div class="groupInput">
                                        <div class="titleInput">
                                            Username <b style="color: red;">*</b>
                                        </div>
                                        <div class="typeInput">
                                            <input class="typeInput__content" type="text" placeholder="Username" name="userId" required>
                                        </div>
                                        <div class="objectError">
                                            <p class="cusError">${requestScope.USER_ERROR.userIdError}  ${requestScope.ERROR}</p>
                                        </div>
                                    </div>
                                    <div class="groupInput">
                                        <div class="titleInput">
                                            Password <b style="color: red;">*</b>
                                        </div>
                                        <div class="typeInput">
                                            <input class="typeInput__content" type="password" placeholder="Password" name="password" 
                                                   required>
                                        </div>
                                        <div class="objectError">
                                            <p class="cusError"> </p>
                                        </div>
                                    </div>
                                    <div class="groupInput">
                                        <div class="titleInput">
                                            Re-Password <b style="color: red;">*</b>
                                        </div>
                                        <div class="typeInput">
                                            <input class="typeInput__content" type="password" placeholder="Re-Password " name="confirm" 
                                                   required>
                                        </div>
                                        <div class="objectError">
                                            <p class="cusError"> ${requestScope.USER_ERROR.confirmError}</p>
                                        </div>
                                    </div>
                                    <div class="groupInput">
                                        <div class="titleInput">
                                            Full Name <b style="color: red;">*</b>
                                        </div>
                                        <div class="typeInput">
                                            <input class="typeInput__content" type="text" placeholder="Full Name"   name="fullName"  required>
                                            <input class="typeInput__content" type="text" name="roleId" value='US'  hidden>
                                        </div>
                                        <div class="objectError">
                                            <p class="cusError">${requestScope.USER_ERROR.fullNameError}</p>
                                        </div>
                                    </div>
                                    <br/>
                                    <div class="groupInput1">
                                        <div class="g-recaptcha cusCapcha" data-sitekey="6LfbaAolAAAAAMbxj9dJkIyNgi_19VPHpD0A5w88"></div>  
                                        <div class="objectError1">
                                            <p id="errorCap" class="cusError"></p>
                                        </div>
                                    </div>
                                    <div class="buttonSubmit">
                                        <input  name="action" value="Create" hidden/>
                                        <button type="submit" class="btn btn-dark cusButton"  name="action" value="Create">Sign Up</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-2">
                        </div>
                    </div>
                </form>
            </div>
        </section>
        <br/>
        <br/>
        <br/>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <script>
            window.onload = function () {
                let isValid = false;
                const form = document.getElementById("f1");
                const error = document.getElementById("errorCap");

                form.addEventListener("submit", function (event) {
                    event.preventDefault();
                    const response = grecaptcha.getResponse();
                    if (response) {
                        form.submit();
                    } else {
                        error.innerHTML = "Please check";
                    }
                });
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