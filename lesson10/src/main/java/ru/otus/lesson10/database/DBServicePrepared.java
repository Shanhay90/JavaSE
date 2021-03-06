package ru.otus.lesson10.database;

import ru.otus.lesson10.executor.Executor;
import ru.otus.lesson10.user.UserDataSet;

import java.sql.Connection;
import java.sql.SQLException;


public class DBServicePrepared implements DBService {

    private static final String CREATE_TABLE_FOR_CLAZZ = "create table if not exists %s (id bigint(20) auto_increment, \"name\" varchar(256), \"age\" integer(3), primary key (id))";
    private static final String DELETE_TABLE_FOR_CLAZZ = "drop table %s";


    private final Connection connection;

    public DBServicePrepared() {
        this.connection = DataBaseHelper.getConnection();
    }
    @Override
    public String getMetaData()  {
        try {
            return String.format("Connected to URL: %s,\nDB name: %s", connection.getMetaData().getURL(), connection.getMetaData().getDatabaseProductName());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
    @Override
    public void  prepareTables(Class clazz) {
        String tableName = clazz.getSimpleName();
        Executor executor = new Executor(connection);
        executor.execUpdate(String.format(CREATE_TABLE_FOR_CLAZZ, tableName), statement -> {
            if (statement.executeUpdate()!=0){ throw new SQLException("Неверно выполнен запрос в БД");}
        });
    }

    @Override
    public void addUser(UserDataSet userDataSet){
        Executor executor = new Executor(connection);
        executor.save(userDataSet);
    }

    @Override
    public UserDataSet getUser(long id){
        Executor executor = new Executor(connection);
        return executor.load(id, UserDataSet.class);
    }
    @Override
    public void deleteTables(Class clazz) {
        String tableName = clazz.getSimpleName();
        Executor executor = new Executor(connection);
        executor.execUpdate(String.format(DELETE_TABLE_FOR_CLAZZ, tableName), statement -> {
            if (statement.executeUpdate()!=0){ throw new SQLException("Неверно выполнен запрос в БД");}
        });
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
