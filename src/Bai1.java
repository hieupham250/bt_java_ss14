import java.util.Scanner;

class Book {
    private String bookId;
    private String bookName;
    private float importPrice;
    private float exportPrice;
    private String author;
    private float interest;
    private int year;

    public Book() {
    }

    public Book(String bookId, String bookName, float importPrice, float exportPrice, String author, int year) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.author = author;
        this.year = year;
        calculateInterest();
    }

    public void inputBook(Scanner sc, Book[] books, int count) {
        boolean valid;
        sc.nextLine();
        do {
            valid = true;
            System.out.print("Nhập mã sách: ");
            bookId = sc.nextLine();
            for (int i = 0; i < count; i++) {
                if (books[i].bookId.equals(bookId)) {
                    valid = false;
                    System.out.println("Mã sách phải là duy nhất! Vui lòng nhập lại.");
                    break;
                }
            }
        } while (!valid);

        do {
            System.out.print("Nhập tên sách (định dạng BXXX): ");
            bookName = sc.nextLine();
        } while (!bookName.matches("B\\d{3}"));

        do {
            System.out.print("Nhập giá nhập: ");
            importPrice = sc.nextFloat();
        } while (importPrice <= 0);

        do {
            System.out.print("Nhập giá xuất (ít nhất cao hơn 30% so với giá nhập): ");
            exportPrice = sc.nextFloat();
        } while (exportPrice < importPrice * 1.3);

        sc.nextLine();
        do {
            System.out.print("Nhập tác giả (6-50 ký tự): ");
            author = sc.nextLine();
        } while (author.length() < 6 || author.length() > 50);

        do {
            System.out.print("Nhập năm xuất bản (sau năm 2000): ");
            year = sc.nextInt();
        } while (year <= 2000);

        calculateInterest();
    }

    public void calculateInterest() {
        interest = exportPrice - importPrice;
    }

    public void displayBook() {
        System.out.printf("%-10s %-10s %-10.2f %-10.2f %-20s %-5d %-10.2f\n",
                bookId, bookName, importPrice, exportPrice, author, year, interest);
    }

    public String getBookName() {
        return bookName;
    }

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }
}

public class Bai1 {
    private static Scanner sc = new Scanner(System.in);
    private static Book[] books;

    public static void main(String[] args) {
        System.out.print("Nhập số lượng sách: ");
        int n = sc.nextInt();
        books = new Book[n];
        menu();
        sc.close();
    }

    private static void menu() {
        int choice;
        do {
            System.out.println("\n********************* MENU ******************");
            System.out.println("1. Nhập thông tin sách");
            System.out.println("2. Tính lợi nhuận sách");
            System.out.println("3. Hiển thị thông tin sách");
            System.out.println("4. Tìm sách theo tên");
            System.out.println("5. Thống kê số lượng sách theo năm xuất bản");
            System.out.println("6. Thống kê số lượng sách theo tác giả");
            System.out.println("7. Thoát");
            System.out.print("Chọn một tùy chọn: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    inputBooks();
                    break;
                case 2:
                    calculateInterest();
                    break;
                case 3:
                    displayBooks();
                    break;
                case 4:
                    searchBookByName();
                    break;
                case 5:
                    countBooksByYear();
                    break;
                case 6:
                    countBooksByAuthor();
                    break;
                case 7:
                    System.out.println("Đang thoát...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        } while (true);
    }

    private static void inputBooks() {
        for (int i = 0; i < books.length; i++) {
            books[i] = new Book();
            books[i].inputBook(sc, books, i);
        }
    }

    private static void calculateInterest() {
        for (Book book : books) {
            book.calculateInterest();
        }
        System.out.println("Đã tính lợi nhuận!");
    }

    private static void displayBooks() {
        System.out.printf("%-10s %-10s %-10s %-10s %-20s %-5s %-10s\n", "Mã Sách", "Tên", "Giá Nhập", "Giá Xuất", "Tác Giả", "Năm", "Lợi Nhuận");
        for (Book book : books) {
            book.displayBook();
        }
    }

    private static void searchBookByName() {
        sc.nextLine();
        System.out.print("Nhập tên sách cần tìm: ");
        String searchName = sc.nextLine();
        for (Book book : books) {
            if (book.getBookName().equalsIgnoreCase(searchName)) {
                book.displayBook();
            }
        }
    }

    private static void countBooksByYear() {
        System.out.print("Nhập năm để thống kê số lượng sách: ");
        int searchYear = sc.nextInt();
        int countYear = 0;
        for (Book book : books) {
            if (book.getYear() == searchYear) {
                countYear++;
            }
        }
        System.out.println("Tổng số sách xuất bản năm " + searchYear + ": " + countYear);
    }

    private static void countBooksByAuthor() {
        sc.nextLine();
        System.out.print("Nhập tên tác giả để thống kê số lượng sách: ");
        String searchAuthor = sc.nextLine();
        int countAuthor = 0;
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(searchAuthor)) {
                countAuthor++;
            }
        }
        System.out.println("Tổng số sách của tác giả " + searchAuthor + ": " + countAuthor);
    }
}
