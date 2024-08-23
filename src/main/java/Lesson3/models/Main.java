package Lesson3.models;

import Lesson3.dao.UserDao;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDao();
        System.out.println(userDao.getAllUsers());
    }
}
