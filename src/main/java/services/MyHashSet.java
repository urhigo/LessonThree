package services;

import java.util.LinkedList;
import java.util.Iterator;

public class MyHashSet<E> {
    private static final int INITIAL_CAPACITY = 16;
    private LinkedList<E>[] buckets;
    private int size = 0;

    public MyHashSet() {
        buckets = new LinkedList[INITIAL_CAPACITY];
    }

    public boolean add(E e) {
        int bucketIndex = getBucketIndex(e);
        LinkedList<E> bucket = buckets[bucketIndex];
        if (bucket == null) {
            bucket = new LinkedList<>();
            buckets[bucketIndex] = bucket;
        }

        for (E element : bucket) {
            if (element.equals(e)) {
                return false; // Element already exists
            }
        }

        bucket.add(e);
        size++;
        if (size > buckets.length * 0.75) {
            resize();
        }
        return true;
    }

    public boolean contains(E e) {
        int bucketIndex = getBucketIndex(e);
        LinkedList<E> bucket = buckets[bucketIndex];
        if (bucket != null) {
            for (E element : bucket) {
                if (element.equals(e)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean remove(E e) {
        int bucketIndex = getBucketIndex(e);
        LinkedList<E> bucket = buckets[bucketIndex];
        if (bucket != null) {
            Iterator<E> iterator = bucket.iterator();
            while (iterator.hasNext()) {
                E element = iterator.next();
                if (element.equals(e)) {
                    iterator.remove();
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    private int getBucketIndex(E e) {
        return e.hashCode() % buckets.length;
    }

    private void resize() {
        int newSize = buckets.length * 2;
        LinkedList<E>[] newBuckets = new LinkedList[newSize];
        for (LinkedList<E> bucket : buckets) {
            if (bucket != null) {
                for (E element : bucket) {
                    int bucketIndex = element.hashCode() % newSize;
                    LinkedList<E> newBucket = newBuckets[bucketIndex];
                    if (newBucket == null) {
                        newBucket = new LinkedList<>();
                        newBuckets[bucketIndex] = newBucket;
                    }
                    newBucket.add(element);
                }
            }
        }
        buckets = newBuckets;
    }


    public Iterator<E> iterator() {
        LinkedList<E> allElements = new LinkedList<>();
        for (LinkedList<E> bucket : buckets) {
            if (bucket != null) {
                allElements.addAll(bucket);
            }
        }
        return allElements.iterator();

    }
}
