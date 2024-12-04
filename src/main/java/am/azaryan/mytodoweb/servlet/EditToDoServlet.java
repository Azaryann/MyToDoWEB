package am.azaryan.mytodoweb.servlet;

import am.azaryan.mytodoweb.model.ToDo;
import am.azaryan.mytodoweb.model.ToDoStatus;
import am.azaryan.mytodoweb.model.User;
import am.azaryan.mytodoweb.service.ToDoService;
import am.azaryan.mytodoweb.service.UserService;
import am.azaryan.mytodoweb.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/editToDo")
public class EditToDoServlet extends HttpServlet {

    private final ToDoService toDoService = new ToDoService();
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ToDo toDo = toDoService.getToDoById(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("todo", toDo);
        req.getRequestDispatcher("/WEB-INF/editToDo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        Date createdDate = DateUtil.stringToDate(req.getParameter("createdDate"));
        Date finishDate = DateUtil.stringToDate(req.getParameter("finishDate"));
        int userId = Integer.parseInt(req.getParameter("userId"));
        ToDoStatus status = ToDoStatus.valueOf(req.getParameter("status"));

        User user = userService.getUserById(userId);

        toDoService.update(ToDo.builder()
                .id(id)
                .title(title)
                .createdDate(createdDate)
                .finishDate(finishDate)
                .user(user)
                .status(status)
                .build());

        resp.sendRedirect("/todos");
    }
}
