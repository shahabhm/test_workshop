package org.example.model;

import java.util.ArrayList;
import java.util.Objects;

public class Book extends Savable {


    String name;
    String genre;
    int totalCount;
    int availableStock;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    public Book(String name, String genre, int totalCount, int availableStock) {
        this.name = name;
        this.genre = genre;
        this.totalCount = totalCount;
        this.availableStock = availableStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return totalCount == book.totalCount && availableStock == book.availableStock && Objects.equals(name, book.name) && Objects.equals(genre, book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, genre, totalCount, availableStock);
    }
}
