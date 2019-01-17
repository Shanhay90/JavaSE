package ru.otus.lesson10.user;

import lombok.Data;

@Data
public class UserDataSet extends DataSet {
    private String name;
    private int age;
}
