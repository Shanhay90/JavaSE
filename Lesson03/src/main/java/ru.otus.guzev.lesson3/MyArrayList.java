package ru.otus.guzev.lesson3;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class MyArrayList<T> implements List<T> {

    private static final int DEFAULT_SIZE = 0;

    private T[] array;

    private int arraySize;

    private int pointOfInsert = 0;


    public MyArrayList() {
        this.array = (T[]) new Object[DEFAULT_SIZE];
        this.arraySize = array.length;
    }

    public MyArrayList(int customSize) throws IndexOutOfBoundsException {
        if (customSize >= 0) {
            this.array = (T[]) new Object[customSize];
            this.arraySize = array.length;
        } else {
            throw new IndexOutOfBoundsException("Incorrect list size");
        }
    }

    private void checkAndResize(int pointOfInsert) {
        int delta = arraySize - pointOfInsert;
        int newSize;
        if (delta == 0) {
            newSize = arraySize + 1;
            arraySize = newSize;
            this.array = Arrays.copyOf(this.array, newSize);
        }
    }


    @Override
    public boolean add(T t) {
        checkAndResize(pointOfInsert);
        array[pointOfInsert] = t;
        pointOfInsert++;
        return true;
    }

    @Override
    public void sort(Comparator<? super T> c) {
        Arrays.sort(array, c);

    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public T get(int index) {
        return array[index];
    }

    @Override
    public T set(int index, T element) {
        return array[index] = element;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }


    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
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
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

    @Override
    public Stream<T> stream() {
        return null;
    }

    @Override
    public Stream<T> parallelStream() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        return false;
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {

    }

    @Override
    public void clear() {

    }
}
