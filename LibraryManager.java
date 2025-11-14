package PersonalLibraryManager.src;

import java.util.Scanner;

/**
 * Main class to run the Library Manager program.
 * Handles menu, user input, and interacts with Library.
 */
public class LibraryManager {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("========== Personal Library Manager ==========");
        boolean running = true;
        while (running) {
            showMenu();
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1": addBookUI(); break;
                case "2": library.listBooks(); break;
                case "3": updateBookUI(); break;
                case "4": deleteBookUI(); break;
                case "5": searchBooksUI(); break;
                case "6": library.saveToFile(); System.out.println("Books saved. Goodbye!"); running = false; break;
                default: System.out.println("Invalid option. Try again.");
            }
            System.out.println();
        }
    }

    private static void showMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Add Book");
        System.out.println("2. List All Books");
        System.out.println("3. Update Book");
        System.out.println("4. Delete Book");
        System.out.println("5. Search Books");
        System.out.println("6. Save & Exit");
    }

    private static void addBookUI() {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Year: ");
        int year = readInt();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        library.addBook(title, author, year, isbn);
        System.out.println("Book added.");
    }

    private static void updateBookUI() {
        System.out.print("Enter book ID to update: ");
        int id = readInt();
        Book book = library.findBookById(id);
        if (book != null) {
            System.out.print("New Title (leave blank for same): ");
            String title = scanner.nextLine();
            System.out.print("New Author (leave blank for same): ");
            String author = scanner.nextLine();
            System.out.print("New Year (0 for same): ");
            int year = readInt();
            System.out.print("New ISBN (leave blank for same): ");
            String isbn = scanner.nextLine();
            boolean updated = library.updateBook(
                    id,
                    title.isEmpty() ? book.getTitle() : title,
                    author.isEmpty() ? book.getAuthor() : author,
                    year == 0 ? book.getYear() : year,
                    isbn.isEmpty() ? book.getIsbn() : isbn
            );
            if (updated) {
                System.out.println("Book updated.");
            } else {
                System.out.println("Error updating book.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void deleteBookUI() {
        System.out.print("Enter book ID to delete: ");
        int id = readInt();
        boolean removed = library.removeBook(id);
        if (removed) {
            System.out.println("Book deleted.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void searchBooksUI() {
        System.out.print("Enter a keyword to search (title or author): ");
        String keyword = scanner.nextLine();
        var found = library.searchBooks(keyword);
        if (found.isEmpty()) {
            System.out.println("No books were found.");
        } else {
            for (Book book : found) {
                System.out.println(book);
            }
        }
    }

    // Helper method for safe int input
    private static int readInt() {
        while (true) {
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                if (input.isEmpty()) return 0;
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}
