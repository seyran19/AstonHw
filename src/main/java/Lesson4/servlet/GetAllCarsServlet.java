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

@WebServlet(name = "GetAllCarsServlet", value = "/getAllCars")
public class GetAllCarsServlet extends HttpServlet {

    private CarDao carDao;

    public GetAllCarsServlet() {
        this.carDao = new CarDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Car> cars = carDao.getAllCars();
        req.setAttribute("car", cars);
        req.getRequestDispatcher("cars.jsp").forward(req, resp);
    }
}
