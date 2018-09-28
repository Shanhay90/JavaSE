package ru.otus.guzev.lesson3;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class MyArrayList<T> implements List<T> {

    private static final int DEFAULT_SIZE = 0;

    private T[] array;

    private int pointOfInsert = 0;


    public MyArrayList() {
        this.array = (T[]) new Object[DEFAULT_SIZE];
    }

    public MyArrayList(int customSize) throws IndexOutOfBoundsException {
        if (customSize >= 0) {
            this.array = (T[]) new Object[customSize];
        } else {
            throw new IndexOutOfBoundsException("Incorrect list size");
        }
    }

    private void checkAndResize(int pointOfInsert) {
        int delta = array.length - pointOfInsert;
        int newSize;
        if (delta == 0) {
            newSize = array.length + 1;
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
        throw new RuntimeException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new RuntimeException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new RuntimeException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new RuntimeException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new RuntimeException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new RuntimeException();
    }


    @Override
    public void add(int index, T element) {
        throw new RuntimeException();
    }

    @Override
    public T remove(int index) {
        throw new RuntimeException();
    }

    @Override
    public int indexOf(Object o) {
        throw new RuntimeException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new RuntimeException();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                throw new RuntimeException();
            }

            @Override
            public T next() {
                Object o = array[index];
                index++;
                return (T) o;
            }

            @Override
            public boolean hasPrevious() {
                throw new RuntimeException();
            }

            @Override
            public T previous() {
                throw new RuntimeException();
            }

            @Override
            public int nextIndex() {
                throw new RuntimeException();
            }

            @Override
            public int previousIndex() {
                throw new RuntimeException();
            }

            @Override
            public void remove() {
                throw new RuntimeException();
            }

            @Override
            public void set(T t) {
                array[index-1] = t;
            }

            @Override
            public void add(T t) {
                throw new RuntimeException();
            }
        };
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new RuntimeException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new RuntimeException();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        throw new RuntimeException();
    }

    @Override
    public Spliterator<T> spliterator() {
        throw new RuntimeException();
    }

    @Override
    public Stream<T> stream() {
        throw new RuntimeException();
    }

    @Override
    public Stream<T> parallelStream() {
        throw new RuntimeException();
    }

    @Override
    public boolean isEmpty() {
        throw new RuntimeException();
    }

    @Override
    public boolean contains(Object o) {
        throw new RuntimeException();
    }

    @Override
    public Iterator<T> iterator() {
        throw new RuntimeException();
    }

    @Override
    public Object[] toArray() {
        throw new RuntimeException();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new RuntimeException();
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        throw new RuntimeException();
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        throw new RuntimeException();
    }

    @Override
    public void clear() {
        throw new RuntimeException();
    }
}
