package Lesson3.servlet.UserServlets;

import Lesson3.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddUserServlet", value = "/addUsers")
public class AddUserServlet extends HttpServlet {


    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Получаем параметр из формы
        String name = req.getParameter("name");

            try {
                // Добавляем пользователя в базу данных
                userDao.addUser(name);

                // Перенаправляем на JSP страницу с обновленным списком пользователей
                req.setAttribute("userList", userDao.getAllUsers());
                resp.sendRedirect("getUsers");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    }

}
