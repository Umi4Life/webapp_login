<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Register</title>
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
        <form method="POST" action="/register" class="form-signin">
            <h2 style="color:whitesmoke; text-align: center">Create Account</h2>

            <div class="form-group">
                Username:<br/>
                <input type="text" name="username"/>
                <br/>
                Email:<br/>
                <input type="email" name="email">
                <br/>
                Password:<br/>
                <input type="password" name="password">
                <br/>
                <p style="color:red;">${error}</p>
                <div class="text-center">
                    <form action="/register" method="post">
                        <button type="submit">Confirm</button><br/>
                    </form>
                    <br/>
                    <button type="button" onclick="window.location.href='/'">Cancel
                    </button>
                </div>
            </div>
        </form>
    </div>


</div>

</body>
</html>

