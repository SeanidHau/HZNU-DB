package model;

public class Book {
    private String bookNumber;
    private String category;
    private String bookName;
    private String publisher;
    private String author;
    private double price;
    private int bookTotal;
    private int inventory;

    public Book() {}

    public Book(String bookNumber, String category, String bookName, String publisher,
                String author, double price, int bookTotal, int inventory) {
        this.bookNumber = bookNumber;
        this.category = category;
        this.bookName = bookName;
        this.publisher = publisher;
        this.author = author;
        this.price = price;
        this.bookTotal = bookTotal;
        this.inventory = inventory;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBookTotal() {
        return bookTotal;
    }

    public void setBookTotal(int bookTotal) {
        this.bookTotal = bookTotal;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
}
