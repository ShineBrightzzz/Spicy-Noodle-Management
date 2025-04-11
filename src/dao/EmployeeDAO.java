package dao;

import entities.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import database.dbConnect;

public class EmployeeDAO {
    private Connection conn;

    public EmployeeDAO() {
        try {
            conn = dbConnect.getConnection(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (id, name, address, gender, date_of_birth, phone) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, employee.getId());
            ps.setString(2, employee.getName());
            ps.setString(3, employee.getAddress());
            ps.setString(4, employee.getGender());
            ps.setDate(5, Date.valueOf(employee.getDateOfBirth()));
            ps.setString(6, employee.getPhoneNumber());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee employee) {
        String sql = "UPDATE employees SET name = ?, address = ?, gender = ?, date_of_birth = ?, phone = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getAddress());
            ps.setString(3, employee.getGender());
            ps.setDate(4, Date.valueOf(employee.getDateOfBirth()));
            ps.setString(5, employee.getPhoneNumber());
            ps.setInt(6, employee.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("gender"),
                    rs.getDate("date_of_birth").toLocalDate(),
                    rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employee emp = new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("gender"),
                    rs.getDate("date_of_birth").toLocalDate(),
                    rs.getString("phone")
                );
                employees.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }
}
