package service;

import project.Student;
import java.util.*;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    // Services (now all JDBC-based)
    private static AdminService adminService = new AdminService();
    private static StudentService studentService = new StudentService();
    private static AttendanceService attendanceService = new AttendanceService();
    private static TaskService taskService = new TaskService();
    private static MarksService marksService = new MarksService();
    private static AnnouncementService announcementService = new AnnouncementService();
    private static ProfileService profileService = new ProfileService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Admin Login");
            System.out.println("2. Student Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> adminLogin();
                case 2 -> studentLogin();
                case 3 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    // =================== ADMIN LOGIN ===================
    private static void adminLogin() {
        System.out.print("Enter username: ");
        String user = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        if (adminService.login(user, pass)) {
            System.out.println(" Admin logged in successfully");
            adminMenu();
        } else {
            System.err.println(" Error: Invalid Username / Password");
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Assign Task");
            System.out.println("6. Enter/Update Marks");
            System.out.println("7. Post Announcement");
            System.out.println("8. Edit/Delete Announcement");
            System.out.println("9. View All Students");
            System.out.println("10. Logout");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> updateStudent();
                case 3 -> deleteStudent();
                case 4 -> searchStudent();
                case 5 -> assignTask();
                case 6 -> enterOrUpdateMarks();
                case 7 -> postAnnouncement();
                case 8 -> editOrDeleteAnnouncement();
                case 9 -> viewAllStudents();
                case 10 -> { return; }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    // =================== STUDENT LOGIN ===================
    private static void studentLogin() {
        System.out.print("Enter roll number: ");
        int roll = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        Student s = studentService.login(roll, pass);
        if (s != null) {
            System.out.println(" Login successful. Welcome " + s.getName());
            System.out.println(attendanceService.markAttendance(roll));
            studentMenu(s);
        } else {
            System.out.println(" Error: Invalid credentials");
        }
    }

    private static void studentMenu(Student s) {
        while (true) {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. View Tasks");
            System.out.println("2. Update Task Status");
            System.out.println("3. View Marks");
            System.out.println("4. View Announcements");
            System.out.println("5. View Profile");
            System.out.println("6. Update Email");
            System.out.println("7. Update Phone");
            System.out.println("8. Change Password");
            System.out.println("9. Attendance Report");
            System.out.println("10. Logout");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> taskService.getStudentTasks(s.getRoll()).forEach(System.out::println);
                case 2 -> updateTaskStatus(s.getRoll());
                case 3 -> System.out.println(marksService.viewMarks(s.getRoll()));
                case 4 -> announcementService.viewAnnouncements().forEach(System.out::println);
                case 5 -> System.out.println(profileService.viewProfile(s.getRoll()));
                case 6 -> {
                    System.out.print("Enter new email: ");
                    String email = sc.nextLine();
                    System.out.println(profileService.updateEmail(s.getRoll(), email));
                }
                case 7 -> {
                    System.out.print("Enter new phone: ");
                    String phone = sc.nextLine();
                    System.out.println(profileService.updatePhone(s.getRoll(), phone));
                }
                case 8 -> {
                    System.out.print("Enter old password: ");
                    String oldPass = sc.nextLine();
                    System.out.print("Enter new password: ");
                    String newPass = sc.nextLine();
                    System.out.println(profileService.changePassword(s.getRoll(), oldPass, newPass));
                }
                case 9 -> System.out.println(attendanceService.getReport(s.getRoll()));
                case 10 -> { return; }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    // ======= ADMIN FUNCTIONS =======
    private static void addStudent() {
        System.out.print("Roll: ");
        int roll = sc.nextInt();
        sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();
        Student s = new Student(roll, name, pass);
        System.out.println(studentService.addStudent(s) ? "Student added" : "Error adding student");
    }

    private static void updateStudent() {
        System.out.print("Roll to update: ");
        int roll = sc.nextInt();
        sc.nextLine();
        System.out.print("New Name: ");
        String name = sc.nextLine();
        System.out.print("New Password: ");
        String pass = sc.nextLine();
        Student s = new Student(roll, name, pass);
        System.out.println(studentService.updateStudent(s) ? "Student updated" : "Error updating student");
    }

    private static void deleteStudent() {
        System.out.print("Roll to delete: ");
        int roll = sc.nextInt();
        sc.nextLine();
        System.out.println(studentService.deleteStudent(roll) ? "Student deleted" : "Error deleting student");
    }

    private static void searchStudent() {
        System.out.print("Enter roll: ");
        int roll = sc.nextInt();
        sc.nextLine();
        Student s = studentService.searchStudent(roll);
        if (s != null) {
            System.out.println(s);
        } else {
            System.out.println("Student not found");
        }
    }

    private static void assignTask() {
        System.out.print("Enter roll: ");
        int roll = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter task: ");
        String task = sc.nextLine();
        System.out.println(taskService.assignTask(task, roll));
    }

    private static void enterOrUpdateMarks() {
        System.out.println("1. Enter Marks  2. Update Marks");
        int choice = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter roll: ");
        int roll = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter marks: ");
        int marks = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            System.out.println(marksService.enterMarks(roll, marks));
        } else if (choice == 2) {
            System.out.println(marksService.updateMarks(roll, marks));
        } else {
            System.out.println("Invalid choice");
            return;
        }

        // Show student marks after operation
        System.out.println(marksService.viewMarks(roll));
    }


    private static void postAnnouncement() {
        System.out.print("Enter announcement: ");
        String ann = sc.nextLine();
        System.out.println(announcementService.postAnnouncement(ann));
    }

    private static void editOrDeleteAnnouncement() {
        System.out.println("1. Edit  2. Delete");
        int ch = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter announcement ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        if (ch == 1) {
            System.out.print("Enter new text: ");
            String txt = sc.nextLine();
            System.out.println(announcementService.editAnnouncement(id, txt));
        } else if (ch == 2) {
            System.out.println(announcementService.deleteAnnouncement(id));
        }
    }

    private static void viewAllStudents() {
        studentService.getAllStudents().forEach(System.out::println);
    }

    // =================== STUDENT FUNCTIONS ===================
    private static void updateTaskStatus(int roll) {
        System.out.print("Enter task ID: ");
        int taskId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter status (Complete/Pending): ");
        String status = sc.nextLine();
        System.out.println(taskService.updateTaskStatus(taskId, status));
    }
}
