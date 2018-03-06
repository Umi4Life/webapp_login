<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Log In</title>
    <link rel="icon" href="image/icon.ico">
    <style>
        .container {
            position: relative;
            text-align: center;
            color: whitesmoke;
            text-shadow:
            -0.75px -0.75px 0 #000,
            0.75px -0.75px 0 #000,
            -0.75px 0.75px 0 #000,
            0.75px 0.75px 0 #000;
        }
        .centered {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }
    </style>
</head>
    <body style="background-color:rgb(248, 213, 83);">
    <div class="container">
        <img src="image/OSOOC.jpeg" width="1200" height="800">
        <div class="centered">
            <h2>Login</h2>

            <form action="/login" method="post">
                Username:<br/>
                <input type="text" name="username"/>
                <br/>
                Password:<br/>
                <input type="password" name="password">
                <p>${error}</p>
                <br>
                <input type="submit" value="Log in">
            </form>
            <form action="/register" method="GET" style="display: inline">
                <button type="submit">Register</button>
            </form>
            <p style="color:red;">ด้านซ้ายมี OS 2 กระบอก ด้านขวามี OOC 3 กระบอก</p>
        </div>
    </div>

    </body>
</html>
