package ru.otus.lesson10.executor;

import ru.otus.lesson10.user.DataSet;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Executor {

    private final String ADD_REQUEST = "INSERT INTO %s (%s) VALUES (%s)";
    private final String GET_BY_ID = "SELECT * FROM %s WHERE id = %s";

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
            String tableName = user.getClass().getSimpleName();
            String names = "";
            String values = "";
            Field[] fields = user.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                boolean isAccessible = field.isAccessible();
                field.setAccessible(true);
                names = names.concat("\"" + field.getName() + "\"");
                values = values.concat("\'" + field.get(user).toString() + "\'");
                if (i < fields.length - 1) {
                    names = names.concat(",");
                    values = values.concat(",");
                }
                field.setAccessible(isAccessible);
            }
            PreparedStatement statement = connection.prepareStatement(String.format(ADD_REQUEST,tableName, names, values));
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends DataSet> T load(long id, Class<T> clazz) {
        try {
            String tableName = clazz.getSimpleName();
            PreparedStatement statement = connection.prepareStatement(String.format(GET_BY_ID,tableName, id));
            ResultSet result = statement.executeQuery();
            T user = clazz.getConstructor().newInstance();
            Field[] fields = user.getClass().getDeclaredFields();
            user.setId(id);
            result.next();
            for (Field field : fields) {
                boolean isAccessible = field.isAccessible();
                field.setAccessible(true);
                field.set(user, result.getObject(field.getName()));
                field.setAccessible(isAccessible);
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
