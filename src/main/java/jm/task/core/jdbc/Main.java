package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.*;

public class Main {
    public static void main(String[] args){
        // implement algorithm here
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();
        userDao.saveUser("John", "Doe", (byte) 30);
        userDao.saveUser("Jane", "Smith", (byte) 25);
        userDao.saveUser("Mike", "Johnson", (byte) 40);
        userDao.saveUser("Emily", "Davis", (byte) 22);

        for (User user: userDao.getAllUsers()){
            System.out.println(user);
        }


    }
}
