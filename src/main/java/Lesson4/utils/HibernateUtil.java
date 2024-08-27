package Lesson4.utils;

import Lesson4.Entity.Bike;
import Lesson4.Entity.Car;
import Lesson4.Entity.Garage;
import Lesson4.Entity.Person;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Car.class)
                .addAnnotatedClass(Bike.class)
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Garage.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
