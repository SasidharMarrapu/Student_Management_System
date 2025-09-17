package service;

import project.Student;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    // Add new student
    public boolean addStudent(Student s) {
        String sql = "INSERT INTO students (roll, name, password, email, phone, marks, grade) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, s.getRoll());
            ps.setString(2, s.getName());
            ps.setString(3, s.getPassword());
            ps.setString(4, s.getEmail());
            ps.setString(5, s.getPhone());
            ps.setDouble(6, s.getMarks());
            ps.setString(7, s.getGrade());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Search student by roll
    public Student searchStudent(int roll) {
        String sql = "SELECT * FROM students WHERE roll = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, roll);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student s = new Student(rs.getInt("roll"), rs.getString("name"), rs.getString("password"));
                s.setEmail(rs.getString("email"));
                s.setPhone(rs.getString("phone"));
                s.setMarks(rs.getDouble("marks"));
                s.setGrade(rs.getString("grade"));
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Delete student
    public boolean deleteStudent(int roll) {
        String sql = "DELETE FROM students WHERE roll = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, roll);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update student
    public boolean updateStudent(Student updated) {
        String sql = "UPDATE students SET name=?, email=?, phone=?, password=?, marks=?, grade=? WHERE roll=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, updated.getName());
            ps.setString(2, updated.getEmail());
            ps.setString(3, updated.getPhone());
            ps.setString(4, updated.getPassword());
            ps.setDouble(5, updated.getMarks());
            ps.setString(6, updated.getGrade());
            ps.setInt(7, updated.getRoll());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Student login
    public Student login(int roll, String pass) {
        String sql = "SELECT * FROM students WHERE roll=? AND password=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, roll);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student s = new Student(rs.getInt("roll"), rs.getString("name"), rs.getString("password"));
                s.setEmail(rs.getString("email"));
                s.setPhone(rs.getString("phone"));
                s.setMarks(rs.getDouble("marks"));
                s.setGrade(rs.getString("grade"));
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Return all students
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Student s = new Student(rs.getInt("roll"), rs.getString("name"), rs.getString("password"));
                s.setEmail(rs.getString("email"));
                s.setPhone(rs.getString("phone"));
                s.setMarks(rs.getDouble("marks"));
                s.setGrade(rs.getString("grade"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
