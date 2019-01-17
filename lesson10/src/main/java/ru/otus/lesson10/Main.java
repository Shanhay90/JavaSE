package ru.otus.lesson10;

import ru.otus.lesson10.database.DBService;
import ru.otus.lesson10.database.DBServiceInterface;
import ru.otus.lesson10.user.UserDataSet;


public class Main {
    public static void main(String[] args) throws Exception{
        try(DBServiceInterface service = new DBService()){
            System.out.println(service.getMetaData());
            service.prepareTables();
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
            service.deleteTables();
        }
    }
}
