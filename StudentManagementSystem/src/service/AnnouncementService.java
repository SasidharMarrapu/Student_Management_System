package service;

import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementService {

    // Post announcement
    public String postAnnouncement(String message) {
        String sql = "INSERT INTO announcements (id, message) VALUES (ann_seq.NEXTVAL, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, message);
            ps.executeUpdate();
            return "Announcement posted successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error posting announcement";
        }
    }

    // Edit announcement
    public String editAnnouncement(int id, String newMessage) {
        String sql = "UPDATE announcements SET message=? WHERE id=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newMessage);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            return rows > 0 ? "Announcement updated" : "Error: Announcement not found";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating announcement";
        }
    }

    // Delete announcement
    public String deleteAnnouncement(int id) {
        String sql = "DELETE FROM announcements WHERE id=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0 ? "Announcement deleted" : "Error: Announcement not found";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting announcement";
        }
    }

    // View announcements
    public List<String> viewAnnouncements() {
        String sql = "SELECT * FROM announcements";
        List<String> list = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add("Announcement ID: " + rs.getInt("id") +
                         " | Message: " + rs.getString("message"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
