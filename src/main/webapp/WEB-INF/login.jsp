<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="/css/login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
</head>
<body>
<% if(request.getSession().getAttribute("msg") != null) { %>
<span style="color: red"><%=request.getSession().getAttribute("msg")%></span>
<% } %>
<div class="center">
    <input type="checkbox" id="show">
    <label for="show" class="show-btn">View Form</label>
    <div class="container">
        <label for="show" class="close-btn fas fa-times" title="close"></label>
        <div class="text">
            Login Form
        </div>
        <form action="/login" method="post">
            <div class="data">
                <label>Email</label>
                <input type="email" required name="email">
            </div>
            <div class="data">
                <label>Password</label>
                <input type="password" required name="password">
            </div>
            <div class="btn">
                <div class="inner"></div>
                <button type="submit">login</button>
            </div>
            <div class="signup-link">
                Not a member? <a href="/register">Signup now</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>