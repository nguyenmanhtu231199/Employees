import Employees.Employees;
import dao.EmployeeDAO;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();

        // Lay danh sach nhan vien - sap xep theo abc
        // C1: viet method dao: getEmployeeByASC
        // SELECT * FROM employees ORDER BY full_name

        // C2: lay toan bo danh sach -> su dung Stream sap xep??
        List<Employees> employeeList = employeeDAO.getAll();

        employeeList.stream()
                .sorted((o1, o2) -> o1.getFullName().compareTo(o2.getFullName()))
                .forEach(e -> System.out.println(e));


    }
}
