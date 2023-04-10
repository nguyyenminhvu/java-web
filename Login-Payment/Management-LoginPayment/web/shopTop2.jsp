<%-- 
    Document   : shopTop2
    Created on : Mar 22, 2023, 11:13:22 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Top 2</title>
    </head>
    <body>
        <h1>Hello Top 2 User</h1>
        <table>
            <thead>
                <tr>
                    <th>
                        No
                    </th>
                    <th>
                        User id
                    </th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.listTop2}" var="u" varStatus="index">
                    <tr>
                        <td>
                            ${index.index+1}
                        </td>
                        <td>
                            ${u.userID}
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
