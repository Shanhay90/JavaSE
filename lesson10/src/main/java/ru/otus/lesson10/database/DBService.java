package ru.otus.lesson10.database;

import ru.otus.lesson10.user.UserDataSet;

public interface DBService extends AutoCloseable{

    String getMetaData();

    void prepareTables();

    void addUser(UserDataSet userDataSet);

    UserDataSet getUser(long id);

    void deleteTables();
}
