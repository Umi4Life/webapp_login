<%@ page import="io.muic.ooc.webapp.database.User" %>
<%@ page import="io.muic.ooc.webapp.database.MySQL" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Do yuo kno de way</title>
    <link rel="icon" href="image/icon.ico">
    <style>
        .container {
            position: relative;
            text-align: center;
            color: black;
        }
        .centered {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            text-shadow:
                    -0.75px -0.75px 0 #000,
                    0.75px -0.75px 0 #000,
                    -0.75px 0.75px 0 #000,
                    0.75px 0.75px 0 #000;
            font-size: 24px;
        }

    </style>
</head>
<body style="background-color:#E9A9E8 ;">
<div class="container">
    <h2>Welcome, 『${username}』</h2>
    <p>
        User information:<br/>
        Username - ${username}<br/>
        Email - ${email}
    </p>
    <form action="/register" method="GET" style="display: inline">
        <button type="submit">Add User</button>
    </form>
    <form action="/logout" method="GET" style="display: inline">
        <button type="submit">Logout</button>
    </form>

    <p style="color:red">${error}</p>
</div>
<div class="container">
    <img src="image/rkk.jpg" width="1200" height="800">
    <div class="centered">
        <table  align="center" style="border: 2px solid whitesmoke">
            <thead>
            <tr style="color:white">
                <th scope="col"><u>Username</u></th>
                <th scope="col"><u>Email</u></th>
                <th scope="col"><u>Action</u></th>
            </tr>
            </thead>
            <tbody>
            <%
                List<User> users = MySQL.getUsers();
                for (User u : users) {
            %>
            <tr style="color:whitesmoke">
                <td><%= u.getUsername() %></td>
                <td><%= u.getEmail() %></td>
                <td>

                    <form action="/edit" method="get" style="display: inline">
                        <input type="hidden" name="toEdit" value="<%=u.getUsername()%>"/>
                        <button type="submit">Edit</button>
                    </form>

                    <form onsubmit="return confirmDelete()" name="delbtn" action="/delete" method="get" style="display: inline">
                        <%--<form style="display: inline">--%>
                        <input type="hidden" name="username" value="<%=u.getUsername()%>"/>
                        <script type='text/javascript'>
                            function confirmDelete() {
                                return confirm("Kill this guy?");
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
    </div>
</div>
</body>
</html>
