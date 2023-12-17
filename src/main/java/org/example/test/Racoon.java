package org.example.test;

public class Racoon {
    String name;
    int age;

    public Racoon(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Racoon{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
