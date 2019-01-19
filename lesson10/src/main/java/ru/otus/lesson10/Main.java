package ru.otus.lesson10;

import ru.otus.lesson10.database.DBService;
import ru.otus.lesson10.database.DBServicePrepared;
import ru.otus.lesson10.user.UserDataSet;


public class Main {
    public static void main(String[] args) throws Exception{
        try(DBService service = new DBServicePrepared()){
            System.out.println(service.getMetaData());
            service.prepareTables(UserDataSet.class);
            UserDataSet user = new UserDataSet();
            user.setName("Joe");
            user.setAge(22);
            service.addUser(user);
            UserDataSet user2 = new UserDataSet();
            user2.setName("Jane");
            user2.setAge(15);
            service.addUser(user2);
            UserDataSet userFromTable = service.getUser(1);
            System.out.println(userFromTable.toString());
            service.deleteTables(UserDataSet.class);
        }
    }
}
