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

@WebServlet(name = "UpdateCarServlet", value = "/updateCar")
public class UpdateCarServlet extends HttpServlet {

    private CarDao carDao;

    public UpdateCarServlet() {
        this.carDao = new CarDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Car car = new Car();
        String model = req.getParameter("model");
        String type = req.getParameter("type");
        String idObj = req.getParameter("id");

        if (!idObj.isEmpty()) {

            long id = Long.parseLong(idObj);
            car.setId(id);
            car.setModel(model);
            car.setType(type);
      }
        else {
            car.setModel(model);
            car.setType(type);
        }

        carDao.updateCar(car);
        List<Car> cars = carDao.getAllCars();
        req.setAttribute("car", cars);

        req.getRequestDispatcher("cars.jsp").forward(req, resp);

    }
}
