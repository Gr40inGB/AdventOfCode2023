package org.example.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Run {
    public static void main(String[] args) {

        Stream.iterate(2.0, x -> x * x).limit(10).forEach(System.out::println);

    }


    public static interface Predicate<T> {
        public boolean getSome(T t);
    }

    public static interface Consumer<T> {
        public void print(T t);
    }

    public static interface Currency<D, R> {
        public D getDollars(R r);
    }
}
