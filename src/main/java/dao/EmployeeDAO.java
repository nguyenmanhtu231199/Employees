package dao;

import Connection.MyConnection;
import Employees.Employees;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public List<Employees> getAll() {
        List<Employees> employeesList = new ArrayList<>();
        try {
            Connection conn = MyConnection.getConnection();

            String sql = "SELECT * FROM employees_jdbc";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                Employees e = new Employees();
                e.setId(rs.getLong("id"));
                e.setFullName(rs.getString("fullName"));
                e.setCity(rs.getString("city"));
                e.setEmail(rs.getString("email"));
                e.setPhone(rs.getString("phone"));
                e.setGender(rs.getInt("gender"));
                e.setSalary(rs.getInt("salary"));
                employeesList.add(e);

            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return employeesList;
    }
    public void insert(Employees em) {
        // Bươc 1: Tạo kết nối

        // Bước 2: Chuẩn bị câu lệnh

        // Bước 3: Thực thi và xem kết quả

        // Bước 4: Đóng kết nối

        try {
            // Buoc 1
            Connection conn = MyConnection.getConnection();

            // Buoc 2
            String sql = String.format("INSERT INTO `persons` (`full_name`,`city`,`email`,`phone`,`gender`,`salary`) VALUES ('%s','%s','%s','%s','%d','%d') ",
                    em.getFullName(), em.getCity(), em.getEmail(),em.getPhone(), em.getGender(),em.getSalary()
            );

//            System.out.println(sql);

            // Buoc 3
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(sql);

            // Buoc 4:
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Employees getById(long id) {
        try {
            Connection conn = MyConnection.getConnection();
            final String sql = "SELECT * FROM employees WHERE id = " + id;

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            Employees e = null;
            if (rs.next()) {
                e = new Employees();
                e.setId(rs.getLong("id"));
                e.setCity(rs.getString("city"));
                e.setEmail(rs.getString("email"));
                e.setGender(rs.getInt("gender"));
                e.setSalary(rs.getInt("salary"));
                e.setFullName(rs.getString("full_name"));
                e.setPhone(rs.getString("phone"));
            }
            rs.close();
            stmt.close();
            conn.close();
            return e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Employees e, long id) {
        Employees tmp = getById(id);
        if(tmp == null){
            System.out.println("Không tồn tại nhân viên có id = " + id);
            return;
        }
        final String sql = String.format("UPDATE employees SET `full_name`='%s',`city`='%s',`email`='%s',`phone`='%s',`gender`='%d',`salary`='%d' WHERE `id`='%d' " ,
                e.getFullName(), e.getCity(), e.getEmail(), e.getPhone(), e.getGender(), e.getSalary(), id
        );

        System.out.println(sql);
        try{
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Cập nhật thất bại");
            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void delete(long id) {
        try {
            Connection conn = MyConnection.getConnection();
            final String sql = "DELETE FROM employees WHERE id = " + id;

            Statement stmt = conn.createStatement();

            long rs = stmt.executeUpdate(sql);
            if (rs == 0) {
                System.out.println("Xoá thất bại");
            }
            stmt.close();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
