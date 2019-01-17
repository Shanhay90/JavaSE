package ru.otus.lesson10.executor;

import ru.otus.lesson10.user.DataSet;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Executor {

    private final String ADD_USER_REQUEST = "INSERT INTO user (\"name\",\"age\") VALUES (?, ?)";
    private final String GET_USER_BY_ID = "SELECT * FROM user WHERE id = %s";

    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void execUpdate(String update, ExecuteHandler prepare) {
        try {
            PreparedStatement stmt = connection.prepareStatement(update);
            prepare.accept(stmt);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <T extends DataSet> void save(T user) {
        try {
            PreparedStatement statement = connection.prepareStatement(ADD_USER_REQUEST);
            Field[] fields = user.getClass().getDeclaredFields();
            for (Field field : fields) {
                boolean isAccessible = field.isAccessible();
                field.setAccessible(true);
                if (field.getName().equals("name")) {
                    statement.setString(1, field.get(user).toString());
                } else if (field.getName().equals("age")) {
                    statement.setString(2, field.get(user).toString());
                }
                field.setAccessible(isAccessible);
            }
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends DataSet> T load(long id, Class<T> clazz) {
        try {
            PreparedStatement statement = connection.prepareStatement(String.format(GET_USER_BY_ID, id));
            ResultSet result = statement.executeQuery();
            T user = clazz.getConstructor().newInstance();
            Field[] fields = user.getClass().getDeclaredFields();
            while (!result.isLast()) {
                result.next();
                if (result.getLong("id") == id) {
                    for (Field field : fields) {
                        boolean isAccessible = field.isAccessible();
                        field.setAccessible(true);
                        switch (field.getName()) {
                            case "id":
                                field.set(user, result.getLong("id"));
                                break;
                            case "name":
                                field.set(user, result.getString("name"));
                                break;
                            case "age":
                                field.set(user, result.getInt("age"));
                                break;
                            default:
                                continue;
                        }
                        field.setAccessible(isAccessible);
                    }
                }
            }
            return user;
        } catch (SQLException
                | NoSuchMethodException
                | IllegalAccessException
                | InvocationTargetException
                | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    public interface ExecuteHandler {
        void accept(PreparedStatement statement) throws SQLException;
    }
}
