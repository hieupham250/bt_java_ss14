import java.util.Scanner;

class Product {
    private String productId;
    private String productName;
    private float importPrice;
    private float exportPrice;
    private float profit;
    private int quantity;
    private String descriptions;
    private boolean status;

    public Product() {}

    public void inputData(Scanner scanner, Product[] products, int count) {
        while (true) {
            System.out.print("Nhập mã sản phẩm (4 ký tự): ");
            productId = scanner.next();
            if (productId.length() == 4 && !isDuplicateId(products, count, productId)) {
                break;
            }
            System.out.println("Mã sản phẩm phải có 4 ký tự và không được trùng!");
        }

        scanner.nextLine();
        while (true) {
            System.out.print("Nhập tên sản phẩm (6-50 ký tự): ");
            productName = scanner.nextLine();
            if (productName.length() >= 6 && productName.length() <= 50) {
                break;
            }
            System.out.println("Tên sản phẩm phải từ 6-50 ký tự!");
        }

        while (true) {
            System.out.print("Nhập giá nhập: ");
            importPrice = scanner.nextFloat();
            if (importPrice > 0) {
                break;
            }
            System.out.println("Giá nhập phải lớn hơn 0!");
        }

        while (true) {
            System.out.print("Nhập giá xuất: ");
            exportPrice = scanner.nextFloat();
            if (exportPrice >= importPrice * 1.2) {
                break;
            }
            System.out.println("Giá xuất phải lớn hơn ít nhất 20% so với giá nhập!");
        }
        calculateProfit();

        while (true) {
            System.out.print("Nhập số lượng sản phẩm: ");
            quantity = scanner.nextInt();
            if (quantity > 0) {
                break;
            }
            System.out.println("Số lượng sản phẩm phải lớn hơn 0!");
        }

        scanner.nextLine();
        System.out.print("Nhập mô tả sản phẩm: ");
        descriptions = scanner.nextLine();

        System.out.print("Sản phẩm có đang bán không? (true/false): ");
        status = scanner.nextBoolean();
    }

    public void displayData() {
        System.out.println("------------------------------------------------------");
        System.out.println("Mã SP: " + productId + " | Tên SP: " + productName);
        System.out.println("Giá nhập: " + importPrice + " | Giá xuất: " + exportPrice + " | Lợi nhuận: " + profit);
        System.out.println("Số lượng: " + quantity + " | Mô tả: " + descriptions);
        System.out.println("Trạng thái: " + (status ? "Đang bán" : "Không bán"));
    }

    public void calculateProfit() {
        profit = exportPrice - importPrice;
    }

    public String getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }
    public void updateQuantity(int amount) {
        this.quantity += amount;
    }
    public boolean isAvailable(int amount) {
        return this.quantity >= amount;
    }
    public void sellProduct(int amount) {
        if (isAvailable(amount)) {
            this.quantity -= amount;
        }
    }
    public void toggleStatus() {
        this.status = !this.status;
    }

    private boolean isDuplicateId(Product[] products, int count, String id) {
        for (int i = 0; i < count; i++) {
            if (products[i] != null && products[i].getProductId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}

public class Bai2 {
    private static final int MAX_PRODUCTS = 100;
    private static Product[] products = new Product[MAX_PRODUCTS];
    private static int productCount = 0;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n*********************** MENU **************************");
            System.out.println("1. Nhập thông tin n sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Tính lợi nhuận các sản phẩm");
            System.out.println("4. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addProducts();
                    break;
                case 2:
                    displayProducts();
                    break;
                case 3:
                    calculateAllProfits();
                    break;
                case 4:
                    System.out.println("Thoát chương trình!");
                    break;
                default:
                    System.out.println("Chọn sai, vui lòng chọn lại!");
            }
        } while (choice != 4);
    }

    private static void addProducts() {
        System.out.print("Nhập số lượng sản phẩm: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            if (productCount < MAX_PRODUCTS) {
                products[productCount] = new Product();
                products[productCount].inputData(sc, products, productCount);
                productCount++;
            } else {
                System.out.println("Danh sách sản phẩm đã đầy!");
                break;
            }
        }
    }

    private static void displayProducts() {
        for (int i = 0; i < productCount; i++) {
            products[i].displayData();
        }
    }

    private static void calculateAllProfits() {
        for (int i = 0; i < productCount; i++) {
            products[i].calculateProfit();
        }
        System.out.println("Đã tính lợi nhuận cho tất cả sản phẩm!");
    }
}
