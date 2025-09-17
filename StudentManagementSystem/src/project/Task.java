package project;

public class Task {
    private int taskId;
    private String description;
    private int assignedTo;
    private String status;

    public Task(int taskId, String description, int assignedTo, String status) {
        this.taskId = taskId;
        this.description = description;
        this.assignedTo = assignedTo;
        this.status = status;
    }

    public int getTaskId() { return taskId; }
    public void setTaskId(int taskId) { this.taskId = taskId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getAssignedTo() { return assignedTo; }
    public void setAssignedTo(int assignedTo) { this.assignedTo = assignedTo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Task{taskId=" + taskId +
                ", description='" + description + '\'' +
                ", assignedTo=" + assignedTo +
                ", status='" + status + '\'' +
                '}';
    }
}
