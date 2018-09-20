package ru.otus.guzev.lesson3;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList <T> implements List {

    private static final int DEFAULT_SIZE = 10;;

    private T[] array;


    public MyArrayList() {

        array = (T[]) new Object[DEFAULT_SIZE];

    }

    public MyArrayList(int customSize) throws IndexOutOfBoundsException {
        if (customSize >= 0)
            array = (T[]) new Object[customSize];
        else throw  new IndexOutOfBoundsException("Incorrect list size");
    }


    @Override
    public int size() {
        return array.length;
    }

    @Override
    public boolean isEmpty() {
        return array.length == 0;
    }

    @Override
    public boolean contains(Object o) {
        boolean contains = false;
        for (T element: array) {
            if (element.equals((T)o))
                contains = true;
        };
        return contains;
    }

    @Override
    public Iterator iterator() {
        return null;
    }



    @Override
    public T[] toArray() {
        return array;
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }



    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return array[index];
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }


    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
