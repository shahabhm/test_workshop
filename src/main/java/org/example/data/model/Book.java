package org.example.data.model;

public class Book {
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
}
