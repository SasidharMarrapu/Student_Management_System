package service;

import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    // Assign new task (Admin)
    public String assignTask(String description, int roll) {
        String sql = "INSERT INTO tasks (task_id, description, assigned_to, status) VALUES (task_seq.NEXTVAL, ?, ?, 'Pending')";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, description);
            ps.setInt(2, roll);
            ps.executeUpdate();
            return "Task assigned successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error assigning task";
        }
    }

    // Mark task complete (Student)
    public String markTaskComplete(int taskId, int roll) {
        String sql = "UPDATE tasks SET status='Complete' WHERE task_id=? AND assigned_to=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, taskId);
            ps.setInt(2, roll);
            int rows = ps.executeUpdate();
            return rows > 0 ? "Task status updated to Complete" : "Error: Task not found";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating task";
        }
    }

    // Update task status
    public String updateTaskStatus(int taskId, String status) {
        String sql = "UPDATE tasks SET status=? WHERE task_id=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, taskId);
            int rows = ps.executeUpdate();
            return rows > 0 ? "Task status updated to " + status : "Error: Task not found";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating task";
        }
    }

    // Delete task (Admin)
    public String deleteTask(int taskId) {
        String sql = "DELETE FROM tasks WHERE task_id=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, taskId);
            int rows = ps.executeUpdate();
            return rows > 0 ? "Task deleted" : "Error: Task not found";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting task";
        }
    }

    // View tasks for a student
    public List<String> getStudentTasks(int roll) {
        String sql = "SELECT * FROM tasks WHERE assigned_to=?";
        List<String> list = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, roll);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add("TaskID: " + rs.getInt("task_id") +
                         " | Desc: " + rs.getString("description") +
                         " | Status: " + rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // View all tasks (Admin)
    public List<String> getAllTasks() {
        String sql = "SELECT * FROM tasks";
        List<String> list = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add("TaskID: " + rs.getInt("task_id") +
                         " | Roll: " + rs.getInt("assigned_to") +
                         " | Desc: " + rs.getString("description") +
                         " | Status: " + rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}