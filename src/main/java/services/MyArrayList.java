package services;

import java.util.Arrays;

public class MyArrayList<E> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] elements;
    private int size = 0;


    public MyArrayList() {
        elements = new Object[INITIAL_CAPACITY];
    }


    public void add(E e) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = e;
    }


    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) elements[index];
    }


    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        E oldValue = (E) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
        return oldValue;
    }

    private void resize() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
    }

}
