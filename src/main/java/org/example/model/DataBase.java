package org.example.model;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import static java.util.Map.entry;

public class DataBase {
    private static DataBase instance;
    private ArrayList<Account> accounts = new ArrayList<>();
    private ArrayList<Book> books = new ArrayList<>();
    public Map<Class, ArrayList> objects = Map.ofEntries(entry(Account.class, accounts), entry(Book.class, books));

    private boolean hasBeenInitialized = false;

    public void init() throws IOException {
        if (hasBeenInitialized) return;
        for (Class aClass : objects.keySet()) {
            String fileName = aClass.getSimpleName() + ".txt";
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(fileName));
                String line = reader.readLine();
                while (line != null) {
                    Object o = new Gson().fromJson(line, aClass);
                    objects.get(aClass).add(o);
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }
        }
        hasBeenInitialized = true;
    }

    public void save(Savable s) throws Exception {
        s.validate();
        String fileName = s.getClass().getSimpleName() + ".txt";
        String json = new Gson().toJson(s);
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        try {
            writer.write(json);
            writer.newLine();
            objects.get(s.getClass()).add(s);
        } catch (Exception e) {
            System.err.println(e);
            throw e;
        } finally {
            writer.close();
        }
    }

    public Account findAccountBySSN(String ssn) {
        for (Account account : accounts) {
            if (Objects.equals(account.getSsn(), ssn)) {
                return account;
            }
        }
        return null;
    }

    public Book findBookByName(String name) {
        for (Book book : books) {
            if (book.getName().equals(name)) {
                return book;
            }
        }
        return null;
    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }
}
