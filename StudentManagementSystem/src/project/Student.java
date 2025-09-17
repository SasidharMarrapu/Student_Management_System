package project;

public class Student {
    private int roll;
    private String name;
    private String password;
    private String email;
    private String phone;
    private double marks;
    private String grade;

    public Student(int roll, String name, String password) {
        this.roll = roll;
        this.name = name;
        this.password = password;
    }

    // Full constructor for DB retrieval
    public Student(int roll, String name, String password, String email, String phone, double marks, String grade) {
        this.roll = roll;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.marks = marks;
        this.grade = grade;
    }

    // Getters & Setters
    public int getRoll() { return roll; }
    public void setRoll(int roll) { this.roll = roll; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = marks; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return "Student{roll=" + roll +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", marks=" + marks +
                ", grade='" + grade + '\'' +
                '}';
    }
}
