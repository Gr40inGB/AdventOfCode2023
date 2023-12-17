package org.example.test;

@FunctionalInterface
public interface MyConverter<T, D> {

    public T convert(D d);

}
