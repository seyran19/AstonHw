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

@WebServlet(name = "AddCarServlet" , value = "/addCar")
public class AddCarServlet extends HttpServlet {

    private CarDao carDao;

    public AddCarServlet() {
        carDao = new CarDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String model = req.getParameter("model");
        String type = req.getParameter("type");

        Car car = new Car(type, model);
        carDao.addCar(car);
        List<Car> cars = carDao.getAllCars();
        req.setAttribute("car", cars);

        req.getRequestDispatcher("cars.jsp").forward(req, resp);


    }
}
