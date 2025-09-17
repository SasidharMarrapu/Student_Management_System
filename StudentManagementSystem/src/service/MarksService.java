package service;

import util.DatabaseConnection;
import java.sql.*;

public class MarksService {

    // Enter marks for a student
    public String enterMarks(int roll, int marks) {
        String sqlCheck = "SELECT marks FROM students WHERE roll=?";
        String sqlInsert = "UPDATE students SET marks=?, grade=? WHERE roll=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps1 = con.prepareStatement(sqlCheck);
             PreparedStatement ps2 = con.prepareStatement(sqlInsert)) {

            ps1.setInt(1, roll);
            ResultSet rs = ps1.executeQuery();
            if (rs.next() && rs.getInt("marks") != 0) {
                return "Error: Marks already exist for this student. Use update instead.";
            }
            ps2.setInt(1, marks);
            ps2.setString(2, calculateGrade(marks));
            ps2.setInt(3, roll);
            ps2.executeUpdate();
            return "Marks saved successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error saving marks";
        }
    }

    // Update marks
    public String updateMarks(int roll, int newMarks) {
        String sql = "UPDATE students SET marks=?, grade=? WHERE roll=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, newMarks);
            ps.setString(2, calculateGrade(newMarks));
            ps.setInt(3, roll);
            int rows = ps.executeUpdate();
            return rows > 0 ? "Marks updated, Grade recalculated" : "Error: Student not found";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating marks";
        }
    }

    // View marks
    public String viewMarks(int roll) {
        String sql = "SELECT marks, grade FROM students WHERE roll=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, roll);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return "Roll: " + roll + " | Marks: " + rs.getInt("marks") + " | Grade: " + rs.getString("grade");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No marks found for this student";
    }

    // Helper: Calculate grade
    private String calculateGrade(int marks) {
        if (marks >= 90) return "A+";
        else if (marks >= 75) return "A";
        else if (marks >= 60) return "B";
        else if (marks >= 40) return "C";
        else return "F";
    }
}
