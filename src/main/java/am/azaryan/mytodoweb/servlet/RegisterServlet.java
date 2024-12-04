package am.azaryan.mytodoweb.servlet;

import am.azaryan.mytodoweb.model.User;
import am.azaryan.mytodoweb.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (userService.getByEmail(email) != null) {
            req.getSession().setAttribute("msg", "User with email " + email + " already exists!");
            resp.sendRedirect("/register");
        } else {
            userService.add(User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .build());

            req.getSession().setAttribute("msg", "User successfully registered!");
            resp.sendRedirect("/register");
        }
    }
}
