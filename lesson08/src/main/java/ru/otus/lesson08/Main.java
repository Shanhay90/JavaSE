package ru.otus.lesson08;

public class Main {
    public static void main(String[] args)  {
        Department department = new Department(10);
        System.out.println("Первоначальный баланс " + department.getBalance());
        department.getAtm(1).getMoney(10000);
        System.out.println("Баланc департамента после снятия из второго банкомата " +department.getBalance());
        department.backupAllAtm();
        department.getAtm(2).getMoney(10000);
        System.out.println("Баланc департамента после снятия из третьего банкомата " + department.getBalance());
        department.restoreAllAtm();
        System.out.println("Баланc департамента после востановрения из бэкапа " + department.getBalance());
    }
}
