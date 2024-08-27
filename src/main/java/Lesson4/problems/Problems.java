package Lesson4.problems;

import Lesson4.Entity.Person;
import Lesson4.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Problems {

    public void nPlusOneProblem() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Person> persons = session.createQuery("from Person", Person.class).list();
        for (Person person : persons) {
            System.out.println(person.getName() + " has " + person.getCars().size() + " cars");
        }

        session.close();

    }

    public void nPlusOneProblemSolution() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Person> persons = session.createQuery("from Person p join fetch p.cars", Person.class).list();
        session.close();
    }

    public void lazyInitializationException(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Person person = session.get(Person.class, 1);
        session.close();
        person.getCars().size();

    }

    public void lazyInitializationExceptionSolution(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Person> list = session.createQuery("from Person p join fetch p.cars", Person.class).list();
        session.close();
    }
}
