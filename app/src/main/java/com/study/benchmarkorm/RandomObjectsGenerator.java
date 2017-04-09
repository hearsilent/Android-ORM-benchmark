package com.study.benchmarkorm;

import com.study.benchmarkorm.model.Book;
import com.study.benchmarkorm.model.Library;
import com.study.benchmarkorm.model.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class RandomObjectsGenerator {
    private static Random random = new Random();
    private static RandomString randomString = new RandomString(150);

    public RandomObjectsGenerator() {

    }

    public Book nextBook() {
        return new Book(randomString.nextString(), randomString.nextString(), random.nextInt(), random.nextInt());
    }

    public List<Book> generateBooks(int quantity) {
        List<Book> books = new ArrayList<>(quantity);
        for (int i = 0; i < quantity; i++) {
            books.add(nextBook());
        }
        return books;
    }

    public Person nextPerson() {
        return new Person(randomString.nextString(), randomString.nextString(),
                new Date(Math.abs(random.nextLong())), random.nextBoolean()? "male": "female",
                Math.abs(random.nextLong()) % ((int)Math.pow(10, 15)));
    }

    public List<Person> generatePersons(int quantity) {
        List<Person> persons = new ArrayList<>(quantity);
        for (int i = 0; i < quantity; i++) {
            persons.add(nextPerson());
        }
        return persons;
    }

    public Library nextLibrary(List<Book> books, List<Person> persons) {
        return new Library(randomString.nextString(), randomString.nextString(), persons, books);
    }


    public static class RandomString {

        private static final char[] symbols;

        static {
            StringBuilder tmp = new StringBuilder();
            for (char ch = '0'; ch <= '9'; ++ch)
                tmp.append(ch);
            for (char ch = 'a'; ch <= 'z'; ++ch)
                tmp.append(ch);
            for (char ch = 'A'; ch <= 'Z'; ++ch)
                tmp.append(ch);
            tmp.append(" ");
            symbols = tmp.toString().toCharArray();
        }

        private final Random random = new Random();

        private final int maxStringLength;

        public RandomString(int maxStringLength) {
            this.maxStringLength = maxStringLength;
        }

        public String nextString(int length) {
            if (length < 1) {
                throw new IllegalArgumentException("String length must be greater than 0");
            }
            char[] buf = new char[length];
            for (int idx = 0; idx < buf.length; ++idx)
                buf[idx] = symbols[random.nextInt(symbols.length)];
            return new String(buf);
        }

        public String nextString() {
            return nextString(Math.abs(random.nextInt()) % maxStringLength + 1);
        }
    }
}
