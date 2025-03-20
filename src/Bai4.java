import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

class Categories {
    private static int idCounter = 1;
    private int catalogId;
    private String catalogName;
    private String descriptions;
    private boolean catalogStatus;

    public Categories() {
        this.catalogId = idCounter++;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public boolean getCatalogStatus() {
        return catalogStatus;
    }

    public void inputData(Scanner scanner, Categories[] arrCategories, int currentCate) {
        System.out.print("Nhập tên danh mục: ");
        while (true) {
            this.catalogName = scanner.nextLine();
            if (this.catalogName.length() > 50) {
                System.out.println("Tên danh mục không được dài quá 50 ký tự. Vui lòng nhập lại!");
            } else {
                boolean isDuplicate = false;
                for (int i = 0; i < currentCate; i++) {
                    if (arrCategories[i].catalogName.equals(this.catalogName)) {
                        isDuplicate = true;
                        break;
                    }
                }
                if (isDuplicate) {
                    System.out.println("Tên danh mục đã tồn tại. Vui lòng nhập lại!");
                } else {
                    break;
                }
            }
        }

        System.out.print("Nhập mô tả danh mục: ");
        this.descriptions = scanner.nextLine();

        System.out.print("Nhập trạng thái danh mục (true: hoạt động, false: không hoạt động): ");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("true") || input.equals("false")) {
                this.catalogStatus = Boolean.parseBoolean(input);
                break;
            } else {
                System.out.println("Trạng thái không hợp lệ. Vui lòng nhập lại!");
            }
        }
    }

    public void displayData() {
        System.out.println("Mã danh mục: " + catalogId);
        System.out.println("Tên danh mục: " + catalogName);
        System.out.println("Mô tả: " + descriptions);
        System.out.println("Trạng thái: " + (catalogStatus ? "Hoạt động" : "Không hoạt động"));
    }
}

class Product4 {
    private String productId;
    private String productName;
    private float price;
    private String description;
    private Date created;
    private int catalogId;
    private int productStatus;

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Product4() {}

    public String getProductId() {
        return productId;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public String getProductName() {
        return productName;
    }

    public float getPrice() {
        return price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public static SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public void inputData(Scanner scanner, Product4[] arrProduct, int currentPro, Categories[] arrCategories, int currentCate) {
        System.out.print("Nhập mã sản phẩm (C, S, A + 3 ký tự): ");
        while (true) {
            this.productId = scanner.nextLine();
            if (!this.productId.matches("[CSA][0-9]{3}")) {
                System.out.println("Mã sản phẩm không hợp lệ. Vui lòng nhập lại!");
            } else {
                boolean isDuplicate = false;
                for (int i = 0; i < currentPro; i++) {
                    if (arrProduct[i].getProductId().equals(this.productId)) {
                        isDuplicate = true;
                        break;
                    }
                }
                if (isDuplicate) {
                    System.out.println("Mã sản phẩm đã tồn tại. Vui lòng nhập lại!");
                } else {
                    break;
                }
            }
        }

        System.out.print("Nhập tên sản phẩm (10-50 ký tự): ");
        while (true) {
            this.productName = scanner.nextLine();
            if (this.productName.length() < 10 || this.productName.length() > 50) {
                System.out.println("Tên sản phẩm phải từ 10-50 ký tự. Vui lòng nhập lại!");
            } else {
                boolean isDuplicate = false;
                for (int i = 0; i < currentPro; i++) {
                    if (arrProduct[i].getProductName().equals(this.productName)) {
                        isDuplicate = true;
                        break;
                    }
                }
                if (isDuplicate) {
                    System.out.println("Tên sản phẩm đã tồn tại. Vui lòng nhập lại!");
                } else {
                    break;
                }
            }
        }

        System.out.print("Nhập giá sản phẩm: ");
        while (true) {
            this.price = scanner.nextFloat();
            scanner.nextLine();
            if (this.price <= 0) {
                System.out.println("Giá phải lớn hơn 0. Vui lòng nhập lại!");
            } else {
                break;
            }
        }

        System.out.print("Nhập mô tả sản phẩm: ");
        this.description = scanner.nextLine();

        System.out.print("Nhập ngày nhập (dd/MM/yyyy): ");
        while (true) {
            String dateStr = scanner.nextLine();
            try {
                this.created = dateFormat.parse(dateStr);
                break;
            } catch (ParseException e) {
                System.out.println("Định dạng ngày không hợp lệ. Vui lòng nhập lại!");
            }
        }

        System.out.println("Chọn mã danh mục:");
        for (int i = 0; i < currentCate; i++) {
            System.out.println(arrCategories[i].getCatalogId() + ": " + arrCategories[i].getCatalogName());
        }
        while (true) {
            this.catalogId = scanner.nextInt();
            scanner.nextLine();
            boolean valid = false;
            for (int i = 0; i < currentCate; i++) {
                if (arrCategories[i].getCatalogId() == this.catalogId) {
                    valid = true;
                    break;
                }
            }
            if (valid) break;
            System.out.println("Mã danh mục không hợp lệ. Vui lòng nhập lại!");
        }

        System.out.print("Nhập trạng thái sản phẩm (0: Đang bán, 1: Hết hàng, 2: Không bán): ");
        while (true) {
            this.productStatus = scanner.nextInt();
            scanner.nextLine();
            if (this.productStatus >= 0 && this.productStatus <= 2) break;
            System.out.println("Trạng thái không hợp lệ. Vui lòng nhập lại!");
        }
    }

    public void displayData() {
        System.out.println("Mã sản phẩm: " + productId);
        System.out.println("Tên sản phẩm: " + productName);
        System.out.println("Giá: " + price);
        System.out.println("Mô tả: " + description);
        System.out.println("Ngày nhập: " + dateFormat.format(created));
        System.out.println("Mã danh mục: " + catalogId);
        System.out.println("Trạng thái: " + productStatus);
    }
}

public class Bai4 {
    private static Categories[] arrCategories = new Categories[100];
    private static Product4[] arrProducts = new Product4[100];
    private static int currentCate = 0;
    private static int currentPro = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("****************** SHOP MENU *******************");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    categoryMenu();
                    break;
                case 2:
                    productMenu();
                    break;
                case 3:
                    System.out.println("Chương trình kết thúc!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void categoryMenu() {
        while (true) {
            System.out.println("******************* CATEGORIES MENU *******************");
            System.out.println("1. Nhập thông tin các danh mục");
            System.out.println("2. Hiển thị thông tin các danh mục");
            System.out.println("3. Cập nhật thông tin danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Cập nhật trạng thái danh mục");
            System.out.println("6. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Nhập số lượng danh mục cần thêm: ");
                    int num = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 0; i < num; i++) {
                        arrCategories[currentCate] = new Categories();
                        arrCategories[currentCate].inputData(scanner, arrCategories, currentCate);
                        currentCate++;
                    }
                    break;
                case 2:
                    for (int i = 0; i < currentCate; i++) {
                        arrCategories[i].displayData();
                    }
                    break;
                case 3:
                    System.out.print("Nhập mã danh mục cần cập nhật: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    boolean foundUpdate = false;
                    for (int i = 0; i < currentCate; i++) {
                        if (arrCategories[i].getCatalogId() == updateId) {
                            foundUpdate = true;
                            while (true) {
                                System.out.println("Chọn thông tin cần cập nhật:");
                                System.out.println("1. Tên danh mục");
                                System.out.println("2. Mô tả");
                                System.out.println("3. Thoát");
                                System.out.print("Nhập lựa chọn: ");
                                int updateChoice = scanner.nextInt();
                                scanner.nextLine();
                                switch (updateChoice) {
                                    case 1:
                                        System.out.print("Nhập tên danh mục mới: ");
                                        arrCategories[i].setCatalogName(scanner.nextLine());
                                        break;
                                    case 2:
                                        System.out.print("Nhập mô tả mới: ");
                                        arrCategories[i].setDescriptions(scanner.nextLine());
                                        break;
                                    case 3:
                                        break;
                                    default:
                                        System.out.println("Lựa chọn không hợp lệ!");
                                }
                                if (updateChoice == 3) {
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    if (!foundUpdate) {
                        System.out.println("Không tìm thấy mã danh mục!");
                    }
                    break;
                case 4:
                    System.out.print("Nhập mã danh mục cần xóa: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    boolean found = false;
                    for (int i = 0; i < currentCate; i++) {
                        if (arrCategories[i].getCatalogId() == deleteId) {
                            found = true;
                            boolean hasProduct = false;
                            for (int j = 0; j < currentPro; j++) {
                                if (arrProducts[j].getCatalogId() == deleteId) {
                                    hasProduct = true;
                                    break;
                                }
                            }
                            if (hasProduct) {
                                System.out.println("Không thể xóa danh mục vì có sản phẩm thuộc danh mục này!");
                            } else {
                                for (int k = i; k < currentCate - 1; k++) {
                                    arrCategories[k] = arrCategories[k + 1];
                                }
                                currentCate--;
                                System.out.println("Xóa danh mục thành công!");
                            }
                            break;
                        }
                    }
                    if (!found) System.out.println("Mã danh mục không tồn tại!");
                    break;
                case 5:
                    System.out.print("Nhập mã danh mục cần cập nhật trạng thái: ");
                    int statusId = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 0; i < currentCate; i++) {
                        if (arrCategories[i].getCatalogId() == statusId) {
                            arrCategories[i].setCatalogStatus(!arrCategories[i].getCatalogStatus());
                            System.out.println("Cập nhật trạng thái thành công!");
                            return;
                        }
                    }
                    System.out.println("Mã danh mục không tồn tại!");
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void productMenu() {
        while (true) {
            System.out.println("***************** PRODUCTS MANAGEMENT *****************");
            System.out.println("1. Nhập thông tin các sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Cập nhật thông tin sản phẩm theo mã sản phẩm");
            System.out.println("4. Xóa sản phẩm theo mã sản phẩm");
            System.out.println("5. Tìm kiếm các sản phẩm theo tên sản phẩm");
            System.out.println("6. Tìm kiếm sản phẩm trong khoảng giá a – b");
            System.out.println("7. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhập số lượng sản phẩm cần thêm: ");
                    int num = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 0; i < num; i++) {
                        arrProducts[currentPro] = new Product4();
                        arrProducts[currentPro].inputData(scanner, arrProducts, currentPro, arrCategories, currentCate);
                        currentPro++;
                    }
                    break;
                case 2:
                    for (int i = 0; i < currentPro; i++) {
                        arrProducts[i].displayData();
                    }
                    break;
                case 3:
                    System.out.print("Nhập mã sản phẩm cần cập nhật: ");
                    String updateId = scanner.nextLine();
                    boolean found = false;

                    for (int i = 0; i < currentPro; i++) {
                        if (arrProducts[i].getProductId().equalsIgnoreCase(updateId)) {
                            found = true;
                            while (true) {
                                System.out.println("Chọn thông tin cần cập nhật:");
                                System.out.println("1. Tên sản phẩm");
                                System.out.println("2. Giá sản phẩm");
                                System.out.println("3. Mô tả sản phẩm");
                                System.out.println("4. Ngày nhập");
                                System.out.println("5. Mã danh mục");
                                System.out.println("6. Trạng thái sản phẩm");
                                System.out.println("7. Thoát");
                                System.out.print("Nhập lựa chọn: ");
                                int updateChoice = scanner.nextInt();
                                scanner.nextLine();

                                switch (updateChoice) {
                                    case 1:
                                        System.out.print("Nhập tên sản phẩm mới: ");
                                        arrProducts[i].setProductName(scanner.nextLine());
                                        break;
                                    case 2:
                                        System.out.print("Nhập giá mới: ");
                                        arrProducts[i].setPrice(scanner.nextFloat());
                                        scanner.nextLine();
                                        break;
                                    case 3:
                                        System.out.print("Nhập mô tả mới: ");
                                        arrProducts[i].setDescription(scanner.nextLine());
                                        break;
                                    case 4:
                                        System.out.print("Nhập ngày nhập mới (dd/MM/yyyy): ");
                                        while (true) {
                                            String dateStr = scanner.nextLine();
                                            try {
                                                arrProducts[i].setCreated(Product4.getDateFormat().parse(dateStr));
                                                break;
                                            } catch (ParseException e) {
                                                System.out.println("Định dạng ngày không hợp lệ. Vui lòng nhập lại!");
                                            }
                                        }
                                        break;
                                    case 5:
                                        System.out.println("Chọn mã danh mục mới:");
                                        for (int j = 0; j < currentCate; j++) {
                                            System.out.println(arrCategories[j].getCatalogId() + ": " + arrCategories[j].getCatalogName());
                                        }
                                        while (true) {
                                            int newCatalogId = scanner.nextInt();
                                            scanner.nextLine();
                                            boolean valid = false;
                                            for (int j = 0; j < currentCate; j++) {
                                                if (arrCategories[j].getCatalogId() == newCatalogId) {
                                                    arrProducts[i].setCatalogId(newCatalogId);
                                                    valid = true;
                                                    break;
                                                }
                                            }
                                            if (valid) break;
                                            System.out.println("Mã danh mục không hợp lệ. Vui lòng nhập lại!");
                                        }
                                        break;
                                    case 6:
                                        System.out.print("Nhập trạng thái sản phẩm mới (0: Đang bán, 1: Hết hàng, 2: Không bán): ");
                                        while (true) {
                                            int newStatus = scanner.nextInt();
                                            scanner.nextLine();
                                            if (newStatus >= 0 && newStatus <= 2) {
                                                arrProducts[i].setProductStatus(newStatus);
                                                break;
                                            }
                                            System.out.println("Trạng thái không hợp lệ. Vui lòng nhập lại!");
                                        }
                                        break;
                                    case 7:
                                        break;
                                    default:
                                        System.out.println("Lựa chọn không hợp lệ!");
                                }
                                if (updateChoice == 7) {
                                    break;
                                }
                            }
                        }
                    }

                    if (!found) {
                        System.out.println("Không tìm thấy sản phẩm!");
                    }
                    break;
                case 4:
                    System.out.print("Nhập mã sản phẩm cần xóa: ");
                    String deleteId = scanner.nextLine();
                    boolean deleted = false;
                    for (int i = 0; i < currentPro; i++) {
                        if (arrProducts[i].getProductId().equalsIgnoreCase(deleteId)) {
                            for (int j = i; j < currentPro - 1; j++) {
                                arrProducts[j] = arrProducts[j + 1];
                            }
                            currentPro--;
                            deleted = true;
                            System.out.println("Xóa sản phẩm thành công!");
                            break;
                        }
                    }
                    if (!deleted) System.out.println("Không tìm thấy sản phẩm!");
                    break;
                case 5:
                    System.out.print("Nhập tên sản phẩm cần tìm: ");
                    String searchName = scanner.nextLine().toLowerCase();
                    for (int i = 0; i < currentPro; i++) {
                        if (arrProducts[i].getProductName().toLowerCase().contains(searchName)) {
                            arrProducts[i].displayData();
                        }
                    }
                    break;
                case 6:
                    System.out.print("Nhập giá thấp nhất: ");
                    float minPrice = scanner.nextFloat();
                    System.out.print("Nhập giá cao nhất: ");
                    float maxPrice = scanner.nextFloat();
                    scanner.nextLine();
                    for (int i = 0; i < currentPro; i++) {
                        if (arrProducts[i].getPrice() >= minPrice && arrProducts[i].getPrice() <= maxPrice) {
                            arrProducts[i].displayData();
                        }
                    }
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}