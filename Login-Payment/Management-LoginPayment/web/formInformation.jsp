<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

    <head>
        <title>
            Confirm
        </title>
        <link rel="stylesheet" href="./css/formInformation.css">
    </head>

    <body>
        <c:if test="${requestScope.MC eq null}" >
            <c:redirect url="ShoppingController"></c:redirect>
        </c:if>
        <form action="MainController" id="msform" method="POST">
            <fieldset>
                <h2 class="fs-title">Personal Details</h2>
                <h3 class="fs-subtitle">See you soon</h3>
                <input type="text" name="firstName" placeholder="First Name" required />
                <p class="cusError"> </p>
                <input type="text" name="lastName" placeholder="Last Name" required />
                <p class="cusError"> </p>
                <input type="number" name="phone" placeholder="Phone" required />
                <p class="cusError"></p>
                <input type="text" name="email" placeholder="Email" required />
                <p class="cusError"></p>
                <textarea name="address" placeholder="Address" required></textarea>
                <p class="cusError"></p>
                <br>
                <br>
                <input name="action" value="CheckoutDelivery" hidden/>
                <button type="submit" class="customSubmit">Confirm</button>
            </fieldset>
        </form>
    </body>
    <script src="./js/checkoutPayment.js"></script>
</html>