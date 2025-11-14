package PersonalLibraryManager.src;

import java.util.*;
import java.io.*;

/**
 * Manages the collection of books.
 * Shows Java collection use, file I/O, and basic CRUD logic.
 */
public class Library {
    private List<Book> books;
    private int nextId = 1;
    private static final String BOOKS_FILE = "books.txt";

    public Library() {
        books = new ArrayList<>();
        loadFromFile();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(String title, String author, int year, String isbn) {
        books.add(new Book(nextId++, title, author, year, isbn));
    }

    public boolean removeBook(int id) {
        return books.removeIf(book -> book.getId() == id);
    }

    public boolean updateBook(int id, String title, String author, int year, String isbn) {
        for (Book book : books) {
            if (book.getId() == id) {
                book.setTitle(title);
                book.setAuthor(author);
                book.setYear(year);
                book.setIsbn(isbn);
                return true;
            }
        }
        return false;
    }

    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) return book;
        }
        return null;
    }

    public List<Book> searchBooks(String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BOOKS_FILE))) {
            for (Book book : books) {
                bw.write(book.getId() + "," +
                        book.getTitle().replace(",", " ") + "," +
                        book.getAuthor().replace(",", " ") + "," +
                        book.getYear() + "," +
                        book.getIsbn().replace(",", " "));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        File file = new File(BOOKS_FILE);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            int maxId = 0;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int id = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String author = parts[2];
                    int year = Integer.parseInt(parts[3]);
                    String isbn = parts[4];
                    books.add(new Book(id, title, author, year, isbn));
                    if (id > maxId) maxId = id;
                }
            }
            nextId = maxId + 1;
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }
}
