package service;

import util.DatabaseConnection;
import java.sql.*;

public class ProfileService {

    // Update Email
    public String updateEmail(int roll, String newEmail) {
        String sql = "UPDATE students SET email=? WHERE roll=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newEmail);
            ps.setInt(2, roll);
            int rows = ps.executeUpdate();
            return rows > 0 ? "Email updated successfully" : "Error: Student not found";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating email";
        }
    }

    // Update Phone
    public String updatePhone(int roll, String newPhone) {
        String sql = "UPDATE students SET phone=? WHERE roll=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newPhone);
            ps.setInt(2, roll);
            int rows = ps.executeUpdate();
            return rows > 0 ? "Phone number updated successfully" : "Error: Student not found";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating phone";
        }
    }

    // Change Password
    public String changePassword(int roll, String oldPassword, String newPassword) {
        String sqlCheck = "SELECT * FROM students WHERE roll=? AND password=?";
        String sqlUpdate = "UPDATE students SET password=? WHERE roll=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps1 = con.prepareStatement(sqlCheck);
             PreparedStatement ps2 = con.prepareStatement(sqlUpdate)) {

            ps1.setInt(1, roll);
            ps1.setString(2, oldPassword);
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                ps2.setString(1, newPassword);
                ps2.setInt(2, roll);
                ps2.executeUpdate();
                return "Password updated successfully";
            } else {
                return "Error: Incorrect old password";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating password";
        }
    }

    // View Profile
    public String viewProfile(int roll) {
        String sql = "SELECT * FROM students WHERE roll=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, roll);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return "Roll: " + rs.getInt("roll") +
                       " | Name: " + rs.getString("name") +
                       " | Email: " + rs.getString("email") +
                       " | Phone: " + rs.getString("phone") +
                       " | Marks: " + rs.getInt("marks") +
                       " | Grade: " + rs.getString("grade");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error: Student not found";
    }
}
