package am.azaryan.mytodoweb.servlet;

import am.azaryan.mytodoweb.model.ToDo;
import am.azaryan.mytodoweb.model.ToDoStatus;
import am.azaryan.mytodoweb.model.User;
import am.azaryan.mytodoweb.service.ToDoService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/changeStatus")
public class ChangeStatusServlet extends HttpServlet {

    private final ToDoService toDoService = new ToDoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String toDoId = req.getParameter("toDoId");
        User user = (User) req.getSession().getAttribute("user");
        if (!toDoId.chars().allMatch(Character::isDigit)) {
            req.getSession().setAttribute("msg", "invalid field");
            resp.sendRedirect("/todos");
        } else {
            ToDo toDo = toDoService.getToDoWithUserIdAndToDoId(user.getId(), Integer.parseInt(toDoId));
            if (toDo != null) {
                if (toDo.getStatus() == ToDoStatus.NEW) {
                    toDoService.changeToDoStatus(ToDoStatus.DONE, Integer.parseInt(toDoId));
                } else {
                    toDoService.changeToDoStatus(ToDoStatus.NEW, Integer.parseInt(toDoId));
                }
            }
            resp.sendRedirect("/todos");
        }
    }
}
