package am.azaryan.mytodoweb.servlet;

import am.azaryan.mytodoweb.model.ToDo;
import am.azaryan.mytodoweb.model.ToDoStatus;
import am.azaryan.mytodoweb.model.User;
import am.azaryan.mytodoweb.service.ToDoService;
import am.azaryan.mytodoweb.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/addToDo")
public class AddToDoServlet extends HttpServlet {

    private final ToDoService toDoService = new ToDoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<ToDo> todos = toDoService.getAllToDos(user.getId());
        req.setAttribute("todos", todos);
        req.getRequestDispatcher("/WEB-INF/addToDo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title = req.getParameter("title");
        Date finishDate = DateUtil.stringToDate(req.getParameter("finishDate"));

        User user = (User) req.getSession().getAttribute("user");

        toDoService.add(ToDo.builder()
                .title(title)
                .createdDate(new Date())
                .finishDate(finishDate)
                .user(user)
                .status(ToDoStatus.NEW)
                .build());

        resp.sendRedirect("/todos");
    }
}
