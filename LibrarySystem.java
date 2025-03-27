import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Book {
    private String bookId;
    private String title;
    private String author;
    private String genre;
    private String availabilityStatus;

    public Book(String bookId, String title, String author, String genre, String availabilityStatus) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availabilityStatus = availabilityStatus;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    @Override
    public String toString() {
        return "ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Genre: " + genre + ", Availability: " + availabilityStatus;
    }
}

class Library {
    private Map<String, Book> books;

    public Library() {
        books = new HashMap<>();
    }

    // Check if Book ID is unique
    public boolean isUniqueBookId(String bookId) {
        return !books.containsKey(bookId);
    }

    // Add a new book
    public void addBook() {
        Scanner scanner = new Scanner(System.in);

        String bookId;
        while (true) {
            System.out.print("Enter Book ID: ");
            bookId = scanner.nextLine().trim();
            if (isUniqueBookId(bookId)) {
                break;
            } else {
                System.out.println("Book ID must be unique. Please enter a different ID.");
            }
        }

        String title;
        while (true) {
            System.out.print("Enter Book Title: ");
            title = scanner.nextLine().trim();
            if (!title.isEmpty()) {
                break;
            } else {
                System.out.println("Title cannot be empty. Please enter a valid title.");
            }
        }

        String author;
        while (true) {
            System.out.print("Enter Book Author: ");
            author = scanner.nextLine().trim();
            if (!author.isEmpty()) {
                break;
            } else {
                System.out.println("Author cannot be empty. Please enter a valid author.");
            }
        }

        System.out.print("Enter Book Genre: ");
        String genre = scanner.nextLine().trim();

        String availabilityStatus;
        while (true) {
            System.out.print("Enter Book Availability Status (Available/Checked Out): ");
            availabilityStatus = scanner.nextLine().trim();
            if (availabilityStatus.equals("Available") || availabilityStatus.equals("Checked Out")) {
                break;
            } else {
                System.out.println("Invalid status. Please enter 'Available' or 'Checked Out'.");
            }
        }

        Book newBook = new Book(bookId, title, author, genre, availabilityStatus);
        books.put(bookId, newBook);
        System.out.println("Book added successfully.");
    }

    // View all books
    public void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("All Books in Catalog:");
            for (Book book : books.values()) {
                System.out.println(book);
            }
        }
    }

    // Search for a book by ID or title
    public void searchBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Search by (1) Book ID or (2) Book Title: ");
        String searchOption = scanner.nextLine().trim();

        if (searchOption.equals("1")) {
            System.out.print("Enter Book ID: ");
            String bookId = scanner.nextLine().trim();
            if (books.containsKey(bookId)) {
                System.out.println(books.get(bookId));
            } else {
                System.out.println("Book not found.");
            }
        } else if (searchOption.equals("2")) {
            System.out.print("Enter Book Title: ");
            String title = scanner.nextLine().trim().toLowerCase();
            boolean found = false;
            for (Book book : books.values()) {
                if (book.getTitle().toLowerCase().contains(title)) {
                    System.out.println(book);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Book not found.");
            }
        } else {
            System.out.println("Invalid option.");
        }
    }

    // Update book details
    public void updateBookDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Book ID to update: ");
        String bookId = scanner.nextLine().trim();

        if (books.containsKey(bookId)) {
            Book book = books.get(bookId);
            System.out.println("Updating details for the book:");
            System.out.println(book);

            String title;
            while (true) {
                System.out.print("Enter new Title (Leave blank to keep current): ");
                title = scanner.nextLine().trim();
                if (title.isEmpty() || !title.equals(book.getTitle())) {
                    break;
                }
            }
            if (!title.isEmpty()) {
                book.setTitle(title);
            }

            String author;
            while (true) {
                System.out.print("Enter new Author (Leave blank to keep current): ");
                author = scanner.nextLine().trim();
                if (author.isEmpty() || !author.equals(book.getAuthor())) {
                    break;
                }
            }
            if (!author.isEmpty()) {
                book.setAuthor(author);
            }

            System.out.print("Enter new Genre (Leave blank to keep current): ");
            String genre = scanner.nextLine().trim();
            if (!genre.isEmpty()) {
                book.setGenre(genre);
            }

            String availabilityStatus;
            while (true) {
                System.out.print("Enter new Availability Status (Available/Checked Out): ");
                availabilityStatus = scanner.nextLine().trim();
                if (availabilityStatus.equals("Available") || availabilityStatus.equals("Checked Out")) {
                    book.setAvailabilityStatus(availabilityStatus);
                    break;
                } else {
                    System.out.println("Invalid status. Please enter 'Available' or 'Checked Out'.");
                }
            }

            System.out.println("Book details updated successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    // Delete a book record
    public void deleteBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Book ID to delete: ");
        String bookId = scanner.nextLine().trim();

        if (books.containsKey(bookId)) {
            books.remove(bookId);
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }
}

public class LibrarySystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\nWelcome to Digital Library Book Management System");
            System.out.println("1. Add a Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book by ID or Title");
            System.out.println("4. Update Book Details");
            System.out.println("5. Delete a Book Record");
            System.out.println("6. Exit System");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    library.addBook();
                    break;
                case "2":
                    library.viewAllBooks();
                    break;
                case "3":
                    library.searchBook();
                    break;
                case "4":
                    library.updateBookDetails();
                    break;
                case "5":
                    library.deleteBook();
                    break;
                case "6":
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

