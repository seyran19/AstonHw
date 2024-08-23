package Lesson3.servlet.UserServlets;


import Lesson3.dao.UserDao;
import Lesson3.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "GetUserServlet", value = "/getUsers")
public class GetAllUsersServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // Получение списка пользователей
            List<User> userList = userDao.getAllUsers();

            // Добавление списка пользователей в атрибуты запроса
            req.setAttribute("userList", userList);

            // Перенаправление на JSP для отображения данных
            req.getRequestDispatcher("user.jsp").forward(req, resp);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
