package ru.otus.guzev.lesson3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Проверка работы на String"+"\n");
       List<String> listString = new MyArrayList<>();
        Collections.addAll(listString, "Вася", "Рома", "Афоня", "Федор", "Гриша");
        List<String> copyStings = new MyArrayList<>(listString.size());
        Collections.copy(copyStings, listString);
        System.out.println("После копирования:" + "\n" + copyStings);
        Comparator<String> comparator= new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        Collections.sort(copyStings, comparator);
        System.out.println("После сортировки:" + "\n" + copyStings + "\n");
        System.out.println("Проверка работы на Integer"+"\n");

        List<Integer> listInteger = new MyArrayList<>();
        Collections.addAll(listInteger, 342, 4564, 5778, 35, 43646, 74, 4, 56);
        List<Integer> copyInteger = new MyArrayList<>(listInteger.size());
        Collections.copy(copyInteger, listInteger);
        System.out.println("После копирования:" + "\n" + copyInteger);
        Comparator<Integer> comparatorInt  = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        Collections.sort(copyInteger);
        System.out.println("После сортировки:" + "\n" + copyInteger);


    }
}
