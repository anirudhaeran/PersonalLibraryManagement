package PersonalLibraryManager.src;

/**
 * Represents a single book in the library.
 * Shows understanding of Java OOP and encapsulation.
 */
public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
    private String isbn;

    public Book(int id, String title, String author, int year, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public String getIsbn() { return isbn; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setYear(int year) { this.year = year; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    @Override
    public String toString() {
        return String.format("ID: %d | Title: %s | Author: %s | Year: %d | ISBN: %s", id, title, author, year, isbn);
    }
}
