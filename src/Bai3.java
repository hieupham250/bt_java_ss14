import java.util.Scanner;

interface IEmployee {
    float BASIC_SALARY = 1300000;
    void inputData(Scanner sc);
    void displayData();
    void calculateSalary();
}

class Employee implements IEmployee {
    private String id;
    private String name;
    private int year;
    private float rate;
    private float commission;
    private float salary;
    private boolean status;

    public Employee() {}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void inputData(Scanner sc) {
        System.out.print("Nhập mã nhân viên: ");
        id = sc.next();
        System.out.print("Nhập tên nhân viên: ");
        sc.nextLine();
        name = sc.nextLine();
        System.out.print("Nhập năm sinh: ");
        year = sc.nextInt();
        System.out.print("Nhập hệ số lương: ");
        rate = sc.nextFloat();
        System.out.print("Nhập hoa hồng: ");
        commission = sc.nextFloat();
        System.out.print("Nhập trạng thái (true: làm việc, false: nghỉ việc): ");
        status = sc.nextBoolean();
    }

    public void displayData() {
        System.out.println("Mã nhân viên: " + id);
        System.out.println("Tên nhân viên: " + name);
        System.out.println("Năm sinh: " + year);
        System.out.println("Hệ số lương: " + rate);
        System.out.println("Hoa hồng: " + commission);
        System.out.println("Lương: " + salary);
        System.out.println("Trạng thái: " + (status ? "Đang làm việc" : "Nghỉ việc"));
    }

    public void calculateSalary() {
        salary = rate * BASIC_SALARY + commission;
    }
}

public class Bai3 {
    private static Employee[] employees;
    private static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n******** MENU ********");
            System.out.println("1. Nhập thông tin nhân viên");
            System.out.println("2. Hiển thị thông tin nhân viên");
            System.out.println("3. Tính lương nhân viên");
            System.out.println("4. Tìm kiếm nhân viên theo tên");
            System.out.println("5. Cập nhật thông tin nhân viên");
            System.out.println("6. Xóa nhân viên theo mã");
            System.out.println("7. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Nhập số lượng nhân viên cần thêm: ");
                    int n = sc.nextInt();
                    sc.nextLine();
                    inputEmployees(sc, n);
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    calculateSalaries();
                    break;
                case 4:
                    searchEmployee(sc);
                    break;
                case 5:
                    updateEmployee(sc);
                    break;
                case 6:
                    deleteEmployee(sc);
                    break;
                case 7:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void inputEmployees(Scanner sc, int n) {
        if (employees == null) {
            employees = new Employee[n];
        } else {
            Employee[] newEmployees = new Employee[employees.length + n];
            System.arraycopy(employees, 0, newEmployees, 0, employees.length);
            employees = newEmployees;
        }

        for (int i = count; i < count + n; i++) {
            System.out.println("Nhập thông tin nhân viên thứ " + (i + 1) + ":");
            employees[i] = new Employee();
            employees[i].inputData(sc);
        }
        count += n;
    }

    private static void displayEmployees() {
        if (count == 0) {
            System.out.println("Không có nhân viên nào để hiển thị.");
            return;
        }
        for (Employee emp : employees) {
            if (emp != null) {
                emp.displayData();
                System.out.println("--------------------");
            }
        }
    }

    private static void calculateSalaries() {
        if (count == 0) {
            System.out.println("Không có nhân viên nào để tính lương.");
            return;
        }
        for (Employee emp : employees) {
            if (emp != null) {
                emp.calculateSalary();
            }
        }
        System.out.println("Đã tính lương cho tất cả nhân viên.");
    }

    private static void searchEmployee(Scanner sc) {
        System.out.print("Nhập tên nhân viên cần tìm: ");
        String name = sc.nextLine();
        for (Employee emp : employees) {
            if (emp != null && emp.getName().equalsIgnoreCase(name)) {
                emp.displayData();
                return;
            }
        }
        System.out.println("Không tìm thấy nhân viên.");
    }

    private static void updateEmployee(Scanner sc) {
        System.out.print("Nhập mã nhân viên cần cập nhật: ");
        String id = sc.next();
        for (Employee emp : employees) {
            if (emp != null && emp.getId().equals(id)) {
                emp.inputData(sc);
                return;
            }
        }
        System.out.println("Không tìm thấy nhân viên.");
    }

    private static void deleteEmployee(Scanner sc) {
        System.out.print("Nhập mã nhân viên cần xóa: ");
        String id = sc.next();
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getId().equals(id)) {
                employees[i] = null;
                System.out.println("Đã xóa nhân viên.");
                return;
            }
        }
        System.out.println("Không tìm thấy nhân viên.");
    }
}