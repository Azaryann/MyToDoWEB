package am.azaryan.mytodoweb.servlet;

import am.azaryan.mytodoweb.model.ToDo;
import am.azaryan.mytodoweb.model.User;
import am.azaryan.mytodoweb.service.ToDoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/sorted-todos-by-date")
public class SortedToDosByDateServlet extends HttpServlet {

    private final ToDoService toDoService = new ToDoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<ToDo> toDos = toDoService.getToDoByUserSortedByDate(user.getId());
        req.setAttribute("todosSort", toDos);
        req.getRequestDispatcher("/WEB-INF/todos.jsp").forward(req, resp);
    }
}
