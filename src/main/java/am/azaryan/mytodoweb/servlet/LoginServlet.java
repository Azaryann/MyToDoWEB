package am.azaryan.mytodoweb.servlet;

import am.azaryan.mytodoweb.model.User;
import am.azaryan.mytodoweb.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User userByEmail = userService.getByEmail(email);
        if (userByEmail != null && userByEmail.getPassword().equals(password)) {
            req.getSession().setAttribute("user", userByEmail);
            resp.sendRedirect("/addToDo");
        } else {
            req.getSession().setAttribute("msg", "Invalid email or password!");
            resp.sendRedirect("/login");
        }
    }
}
