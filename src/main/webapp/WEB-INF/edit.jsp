<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Edit User</title>
    <link rel="icon" href="image/icon.ico">
    <style>
        .container {
            position: relative;
            text-align: center;
            color: whitesmoke;
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
        }
    </style>
</head>
<body style="background-color:#E9A9E8;">
<div class="container">
    <img src="image/rkk.jpg" width="1200" height="800">
    <div class="centered">
        <form method="POST" action="/edit" class="form-signin">
            <h2 style="color:white; text-align: center"> Editing ${toEdit} </h2>
            <div class="form-group">
                New Email:<br/>
                <input type="email" name="email">
                <br/>
                New Password:<br/>
                <input type="password" name="password">
                <br/>
                <p style="color:red;">${error}</p>
                <div class="text-center">
                    <form action="/edit" method="post">
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

