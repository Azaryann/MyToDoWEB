package am.azaryan.mytodoweb.servlet;

import am.azaryan.mytodoweb.service.ToDoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteToDo")
public class DeleteToDoServlet extends HttpServlet {

    private final ToDoService toDoService = new ToDoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        toDoService.delete(id);
        resp.sendRedirect("/todos");
    }
}
