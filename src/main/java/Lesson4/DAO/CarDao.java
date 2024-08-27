package Lesson4.DAO;

import Lesson4.Entity.Car;
import Lesson4.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class CarDao {

    private HibernateUtil hibernateUtil;

    public CarDao() {
        this.hibernateUtil = new HibernateUtil();
    }

    public List<Car> getAllCars() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Car> cars = session.createQuery("from Car", Car.class).list();
        return cars;
    }

    public Car getCarById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Car car = session.get(Car.class, id);
        return car;
    }

    public void addCar(Car car) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(car);
        session.getTransaction().commit();
    }

    public void updateCar(Car car) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(car);
        session.getTransaction().commit();
    }

    public void deleteCar(Car car) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(car);
        session.getTransaction().commit();
    }
}
