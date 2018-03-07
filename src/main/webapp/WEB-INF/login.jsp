<html>
    <body>
        <h2>Login</h2>
        <p>${error}</p>
        <div style="text-align: center">
        <form action="/login" method="post">
            Username:<br/>
            <input type="text" name="username"/>
            <br/>
            Password:<br/>
            <input type="password" name="password">
            <br><br>
            <input type="submit" value="Login">
        </form>
            <%--<form action="/register" method="get">--%>
                <%--<input type="submit" value="create an account" />--%>
            <%--</form>--%>
        </div>
    </body>
</html>
