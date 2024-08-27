package Lesson4.utils;

import Lesson4.Entity.Car;
import Lesson4.Entity.Garage;
import Lesson4.Entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DataInitializer {


    public void generateData(){

        try (Session session = HibernateUtil.getSessionFactory().openSession();){
            Transaction transaction = session.beginTransaction();
            // Создание и сохранение данных
            Car car1 = new Car();
            car1.setModel("Toyota Corolla");
            session.save(car1);

            Car car2 = new Car();
            car2.setModel("Honda Civic");
            session.save(car2);

            Garage garage = new Garage();
            garage.setLocation("Downtown");
            garage.getCars().add(car1);
            garage.getCars().add(car2);
            session.save(garage);

            Person person = new Person();
            person.setName("John Doe");
            person.getCars().add(car1);
            session.save(person);

            // Завершение транзакции
            transaction.commit();
        }



    }
}
