package ru.otus.lesson08;

public class Main {
    public static void main(String[] args)  {
        Department department = new Department(10);
        System.out.println(department.getBalance());
        department.getAtms().get(1).getMoney(10000);
        System.out.println(department.getBalance());
        department.setDepartmentDefaultStatus();
        System.out.println(department.getBalance());
    }
}
