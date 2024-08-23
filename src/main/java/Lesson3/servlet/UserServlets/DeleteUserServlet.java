package Lesson3.servlet.UserServlets;

import Lesson3.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteUserServlet", value = "/deleteUser")
public class DeleteUserServlet extends HttpServlet {


    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Получаем параметр из формы
        int id = Integer.parseInt(req.getParameter("id"));

        try {
            // Удаляем пользователя из базы данных
            userDao.deleteUser(id);

            // Перенаправляем на JSP страницу с обновленным списком пользователей
            req.setAttribute("userList", userDao.getAllUsers());
            resp.sendRedirect("getUsers");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
