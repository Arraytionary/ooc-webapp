<html>
<body>
<h2>Register</h2>
<p>${error}</p>
<div style="text-align: center">
    <form action="/register" method="post">
        Username:<br/>
        <input type="text" name="username"/>
        <br/>
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
    <form action="/user" methos="get">
        <input type="submit" value="back to user page" />
    </form>
</div>
</body>
</html>
