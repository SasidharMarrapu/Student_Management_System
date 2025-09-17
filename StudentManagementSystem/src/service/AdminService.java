package service;

import util.DatabaseConnection;
import java.sql.*;

public class AdminService {

    // Admin login
    public boolean login(String username, String password) {
        String sql = "SELECT * FROM admins WHERE username=? AND password=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // true if record exists
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Change password
    public String changePassword(String username, String oldPass, String newPass) {
        String sqlCheck = "SELECT * FROM admins WHERE username=? AND password=?";
        String sqlUpdate = "UPDATE admins SET password=? WHERE username=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps1 = con.prepareStatement(sqlCheck);
             PreparedStatement ps2 = con.prepareStatement(sqlUpdate)) {

            ps1.setString(1, username);
            ps1.setString(2, oldPass);
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                ps2.setString(1, newPass);
                ps2.setString(2, username);
                ps2.executeUpdate();
                return "Password updated";
            } else {
                return "Error: Incorrect old password";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while changing password";
        }
    }
}
