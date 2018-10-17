package com.wszib;

public class Person {

    private String name;
    private String surname;
    private int numer;


    public Person(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public Person(String surname) {
        this("Andrzej", surname);
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }

    public void setNumer(int n) {
        this.numer = n;
    }

    public String greet() {
        return "Hi, I'm " + name + " " + surname;
    }

    public String mail() {
        if (numer == 0)
            return name + "." + surname + "@mex.com";
        else
            return name + "." + surname + numer + "@mex.com";
    }

}