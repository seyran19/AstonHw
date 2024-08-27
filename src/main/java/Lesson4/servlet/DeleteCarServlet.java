package Lesson4.servlet;

import Lesson4.DAO.CarDao;
import Lesson4.Entity.Car;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteCarServlet", value = "/deleteCar")
public class DeleteCarServlet extends HttpServlet {

    private CarDao carDao;

    public DeleteCarServlet() {
        carDao = new CarDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("id"));
        Car car = carDao.getCarById(id);
        carDao.deleteCar(car);
        List<Car> cars = carDao.getAllCars();
        req.setAttribute("car", cars);

        req.getRequestDispatcher("cars.jsp").forward(req, resp);
    }
}
