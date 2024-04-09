<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Items</title>
</head>
<body>
    <h1>Available Items</h1>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
        </tr>
        <c:forEach var="item" items="${itemList}">
            <tr>
                <td>${item.name}</td>
                <td>${item.description}</td>
                <td>${item.price}</td>
                <td><button id="addToCart" type="button">Add To Cart</button>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
