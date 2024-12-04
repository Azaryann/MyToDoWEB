<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/register.css">
</head>
<body>
<% if(request.getSession().getAttribute("msg") != null) { %>
<span style="color: red"><%=request.getSession().getAttribute("msg")%></span>
<% } %>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-5">
            <div class="card">
                <h2 class="card-title text-center">Register</h2>
                <div class="card-body py-md-4">
                    <form _lpchecked="1" action="/register" method="post">
                        <div class="form-group">
                            <input type="text" class="form-control" id="name" placeholder="Name" name="name">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="surname" placeholder="Surname" name="surname">
                        </div>
                        <div class="form-group">
                            <input type="email" class="form-control" id="email" placeholder="Email" name="email">
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" id="password" placeholder="Password"  name="password">
                        </div>
                        <div class="d-flex flex-row align-items-center justify-content-between">
                            <a href="/login">Login</a>
                            <button class="btn btn-primary">Create Account</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
