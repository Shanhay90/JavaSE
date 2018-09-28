package ru.otus.guzev.lesson3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> listInteger = new MyArrayList<>();
        Collections.addAll(listInteger, 342, 4564, 5778, 35, 43646, 74, 4, 56, 1, 2, 3, 4);

        List<Integer> copyInteger = new MyArrayList<>(listInteger.size());
        Collections.copy(copyInteger, listInteger);
        System.out.println(copyInteger);
    }
}
