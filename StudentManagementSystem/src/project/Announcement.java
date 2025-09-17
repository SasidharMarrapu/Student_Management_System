package project;

public class Announcement {
    private int id;
    private String message;

    public Announcement(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    @Override
    public String toString() {
        return "Announcement{id=" + id + ", message='" + message + "'}";
    }
}
