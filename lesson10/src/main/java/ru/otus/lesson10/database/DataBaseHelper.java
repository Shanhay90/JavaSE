package ru.otus.lesson10.database;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseHelper {
    public static void createDataBase() {
        try {
            Class.forName("org.h2.Driver").getConstructor().newInstance();
        } catch (InstantiationException
                | IllegalAccessException
                | NoSuchMethodException
                | InvocationTargetException
                | ClassNotFoundException e) {
            throw new RuntimeException(e.getCause());
        }
    }


    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:h2:mem:testDatabase", "root", "root");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
}
