<%@ page import="am.azaryan.mytodoweb.model.ToDo" %>
<%@ page import="am.azaryan.mytodoweb.model.ToDoStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TODO</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,600;0,700;0,800;1,300;1,400;1,600;1,700;1,800&amp;display=swap">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootlint/1.1.0/bootlint.min.js"></script>

    <link rel="stylesheet" href="/css/style.css">
    <script src="/js/todo.js"></script>
    <% ToDo todo = (ToDo) request.getAttribute("todo"); %>
</head>
<body>
<div class="container m-5 p-2 rounded mx-auto bg-light shadow">
    <!-- App title section -->
    <div class="row m-1 p-4">
        <div class="col">
            <div class="p-1 h1 text-primary text-center mx-auto display-inline-block">
                <i class="fa fa-check bg-primary text-white rounded p-2"></i>
                <u>My Todo-s</u>
            </div>
        </div>
    </div>
    <!-- Create todo section -->
    <form action="/editToDo" method="post">
        <div class="row m-1 p-3">
            <div class="col col-11 mx-auto">
                <div class="row bg-white rounded shadow-sm p-2 add-todo-wrapper align-items-center justify-content-center">
                    <div class="col" style="width: 100%; height: 45px;">
                        <input type="hidden" name="id" value="<%=todo.getId()%>">
                        Title: <input type="text" value="<%=todo.getTitle()%>" name="title">
                    </div>
                </div>
            </div>
        </div>
        <div class="row m-1 p-3">
            <div class="col col-11 mx-auto">
                <div class="row bg-white rounded shadow-sm p-2 add-todo-wrapper align-items-center justify-content-center">
                    <div class="col" style="width: 100%; height: 45px;">
                        <input type="hidden" value="<%=todo.getCreatedDate()%>" name="createdDate">
                        Finish date:<input type="date" value="<%=todo.getFinishDate()%>" name="finishDate">
                        <input type="hidden" value="<%=todo.getUser().getId()%>" name="userId">
                    </div>
                </div>
            </div>
        </div>
        <div class="row m-1 p-3">
            <div class="col col-11 mx-auto">
                <div class="row bg-white rounded shadow-sm p-2 add-todo-wrapper align-items-center justify-content-center">
                    <div class="col" style="width: 100%; height: 45px;">
                        Status:
                        <select name="status">
                            <% if (todo.getStatus() == ToDoStatus.NEW) { %>
                            <option value="<%=todo.getStatus()%>" selected>NEW</option>
                            <option value="<%=ToDoStatus.DONE%>">DONE</option>
                            <% } else if (todo.getStatus() == ToDoStatus.DONE) { %>
                            <option value="<%=ToDoStatus.DONE%>" selected>DONE</option>
                            <option value="<%=todo.getStatus()%>">NEW</option>
                            <% } %>
                        </select>
                        <input type="submit" value="Edit">
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
