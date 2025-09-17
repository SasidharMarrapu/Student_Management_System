package service;

import util.DatabaseConnection;
import java.sql.*;
import java.time.LocalDate;

public class AttendanceService {

    // Mark attendance
    public String markAttendance(int roll) {
        LocalDate today = LocalDate.now();
        String sqlCheck = "SELECT * FROM attendance WHERE roll=? AND date_attended=?";
        String sqlInsert = "INSERT INTO attendance (roll, date_attended) VALUES (?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps1 = con.prepareStatement(sqlCheck);
             PreparedStatement ps2 = con.prepareStatement(sqlInsert)) {

            ps1.setInt(1, roll);
            ps1.setDate(2, Date.valueOf(today));
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                return "Attendance already marked today";
            }
            ps2.setInt(1, roll);
            ps2.setDate(2, Date.valueOf(today));
            ps2.executeUpdate();
            return "Attendance marked as Present";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error marking attendance";
        }
    }

    // Attendance percentage
    public double getAttendancePercentage(int roll) {
        String sqlTotal = "SELECT COUNT(DISTINCT date_attended) AS total FROM attendance";
        String sqlPresent = "SELECT COUNT(DISTINCT date_attended) AS present FROM attendance WHERE roll=?";
        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             PreparedStatement ps = con.prepareStatement(sqlPresent)) {

            ResultSet rsTotal = st.executeQuery(sqlTotal);
            rsTotal.next();
            int totalDays = rsTotal.getInt("total");

            ps.setInt(1, roll);
            ResultSet rsPresent = ps.executeQuery();
            rsPresent.next();
            int presentDays = rsPresent.getInt("present");

            if (totalDays == 0) return 0.0;
            return (presentDays * 100.0) / totalDays;
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    // Report
    public String getReport(int roll) {
        double percent = getAttendancePercentage(roll);
        return "Attendance % for Roll " + roll + " = " + percent + "%";
    }
}
