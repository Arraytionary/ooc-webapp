<%--
  Created by IntelliJ IDEA.
  User: Arraytionary
  Date: 3/6/2018 AD
  Time: 9:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <p>${error}</p>
    <div style="text-align: center">
        <h2>Edit user ${toEdit}</h2>
        <form action="/edit" method="post">
            Password:<br/>
            <input type="password" name="password">
            <br/>
            Name:<br/>
            <input type="text" name="name">
            <br/>
            Last name:<br/>
            <input type="text" name="lastname">
            <br/>
            Email:<br/>
            <input type="email" name="email">
            <br><br>
            <input type="submit" value="Submit">
        </form>
        <button onclick="location.href='/user';" class="button">back to user's page</button>
    <%--<form action="login.jsp" methos="get">--%>
            <%--<input type="submit" value="back to login page" />--%>
        <%--</form>--%>
    </div>
<body>

</body>
</html>
