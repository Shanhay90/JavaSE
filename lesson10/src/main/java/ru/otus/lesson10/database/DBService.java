package ru.otus.lesson10.database;

import ru.otus.lesson10.user.UserDataSet;

public interface DBService extends AutoCloseable{

    String getMetaData();

    void prepareTables(Class clazz);

    void addUser(UserDataSet userDataSet);

    UserDataSet getUser(long id);

    void deleteTables(Class clazz);
}
