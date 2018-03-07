<%@ page import="io.muic.ooc.webapp.service.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>
<h2>Welcome, ${username}</h2>

<p> ${error} </p>
<table  align="center" style="border: 2px solid grey">
    <thead>
    <tr style="color:black">
        <th scope="col"><u>Username</u></th>
        <th scope="col"><u>Email</u></th>
        <th scope="col"><u>First name</u></th>
        <th scope="col"><u>Last name</u></th>
    </tr>
    </thead>
    <tbody>
    <%
        // retrieve your list from the request, with casting
        List<User> userList = (List<User>) request.getAttribute("userList");

// print the information about every category of the list
        for(User user : userList) {
            //out.println(user.getUserName());

    %>
    <tr style="color:black">
        <td><%= user.getUserName() %></td>
        <td><%= user.getEmail() %></td>
        <td><%= user.getName() %></td>
        <td><%= user.getLastname() %></td>
        <td>

            <form action="/edit" method="get" style="display: inline">
                <input type="hidden" name="toEdit" value="<%=user.getUserName()%>"/>
                <button type="submit">Edit</button>
            </form>

            <form onsubmit="return confirmDelete(document.getElementById('l').value)" name="delbtn" action="/delete" method="get" style="display: inline">
                <%--<form style="display: inline">--%>
                <input type="hidden" name="username" value="<%=user.getUserName()%>" id="l"/>
                <script type='text/javascript'>
                    function confirmDelete() {
                        <%--var u = "<%=user.getUserName()%>"--%>
                        <%--var cu = "<%=request.getAttribute("username")%>"--%>
                        <%--console.log(u+" "+cu)--%>
                        <%--&lt;%&ndash;if(u===cu) {&ndash;%&gt;--%>
                            <%--&lt;%&ndash;&lt;%&ndash;print(${username});&ndash;%&gt;&ndash;%&gt;--%>
                            <%--&lt;%&ndash;alert("beez");&ndash;%&gt;--%>
<%--&lt;%&ndash;//                            return false;&ndash;%&gt;--%>
<%--&lt;%&ndash;//                        }&ndash;%&gt;--%>
<%--&lt;%&ndash;//                        else{&ndash;%&gt;--%>
                        <%--&lt;%&ndash;}&ndash;%&gt;--%>
                            return confirm("are you sure ?");
//                        }
                    }
                </script>
                <button id="remove" type="submit" class="btn btn-outline-danger">Delete</button>
            </form>

        </td>

    </tr>
    <%
        }
    %>
    </tbody>

</table>
<br><br>
<div style="text-align: center" style="display: inline">
    <button onclick="location.href='/register';" class="button">add user</button>
<button onclick="location.href='/logout';" class="button">logout </button>

</div>
</body>
</html>
