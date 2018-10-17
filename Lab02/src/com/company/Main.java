package com.company;

import com.wszib.Person;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Person> lista = new ArrayList<Person>();
        Scanner scaner = new Scanner(System.in).useDelimiter("\\s");

        System.out.println("Type stop to stop!");

        while (true) {
            String name = scaner.next();
            if (name.equals("stop")) break;
            String surname = scaner.next();
            Person osoba = new Person(name.toLowerCase(), surname.toLowerCase());
            int n = 0;
            if (lista.size() > 0) {
                for (int i = 0; i < lista.size(); i++) {
                    if ((lista.get(i).getName().equals(osoba.getName())) && (lista.get(i).getSurname().equals(osoba.getSurname()))) {
                        n++;
                    }
                }
            }
            osoba.setNumer(n);
            System.out.println(osoba.mail());
            lista.add(osoba);
        }
    }
}