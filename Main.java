import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private EducationSystem system;

    private JTextArea outputArea;

    public Main() {

        system = new EducationSystem();

        setTitle("EduTrack - Quality Education Management System");

        setSize(900, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        createGUI();
    }

    private void createGUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout(10, 10));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15
                )
        );

        // =====================================
        // TITLE
        // =====================================

        JLabel title =
                new JLabel(
                        "EduTrack - SDG 4: Quality Education",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        mainPanel.add(
                title,
                BorderLayout.NORTH
        );

        // =====================================
        // BUTTON PANEL
        // =====================================

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(4, 2, 10, 10)
                );

        JButton addStudent =
                new JButton("Add Student");

        JButton addTeacher =
                new JButton("Add Teacher");

        JButton addSubject =
                new JButton("Add Subject");

        JButton enroll =
                new JButton("Enroll Subject");

        JButton marks =
                new JButton("Add Marks");

        JButton attendance =
                new JButton("Mark Attendance");

        JButton evaluation =
                new JButton("Academic Evaluation");

        JButton viewStudents =
                new JButton("View Students");

        buttonPanel.add(addStudent);
        buttonPanel.add(addTeacher);
        buttonPanel.add(addSubject);
        buttonPanel.add(enroll);
        buttonPanel.add(marks);
        buttonPanel.add(attendance);
        buttonPanel.add(evaluation);
        buttonPanel.add(viewStudents);

        mainPanel.add(
                buttonPanel,
                BorderLayout.WEST
        );

        // =====================================
        // OUTPUT
        // =====================================

        outputArea =
                new JTextArea();

        outputArea.setEditable(false);

        outputArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(outputArea);

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =====================================
        // BUTTON EVENTS
        // =====================================

        addStudent.addActionListener(
                e -> addStudent()
        );

        addTeacher.addActionListener(
                e -> addTeacher()
        );

        addSubject.addActionListener(
                e -> addSubject()
        );

        enroll.addActionListener(
                e -> enrollStudent()
        );

        marks.addActionListener(
                e -> addMarks()
        );

        attendance.addActionListener(
                e -> markAttendance()
        );

        evaluation.addActionListener(
                e -> evaluateStudent()
        );

        viewStudents.addActionListener(
                e -> viewStudents()
        );

        setContentPane(mainPanel);
    }

    // ==========================================
    // ADD STUDENT
    // ==========================================

    private void addStudent() {

        try {

            int id =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Student ID:"
                            )
                    );

            String name =
                    JOptionPane.showInputDialog(
                            this,
                            "Student Name:"
                    );

            String email =
                    JOptionPane.showInputDialog(
                            this,
                            "Email:"
                    );

            String phone =
                    JOptionPane.showInputDialog(
                            this,
                            "Phone:"
                    );

            String program =
                    JOptionPane.showInputDialog(
                            this,
                            "Program:"
                    );

            Student student =
                    new Student(
                            id,
                            name,
                            email,
                            phone,
                            program
                    );

            if (system.addStudent(student)) {

                showMessage(
                        "Student added successfully!"
                );

            } else {

                showMessage(
                        "Student ID already exists!"
                );
            }

        } catch (Exception e) {

            showMessage(
                    "Invalid input."
            );
        }
    }

    // ==========================================
    // ADD TEACHER
    // ==========================================

    private void addTeacher() {

        try {

            int id =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Teacher ID:"
                            )
                    );

            String name =
                    JOptionPane.showInputDialog(
                            this,
                            "Teacher Name:"
                    );

            String email =
                    JOptionPane.showInputDialog(
                            this,
                            "Email:"
                    );

            String phone =
                    JOptionPane.showInputDialog(
                            this,
                            "Phone:"
                    );

            String department =
                    JOptionPane.showInputDialog(
                            this,
                            "Department:"
                    );

            Teacher teacher =
                    new Teacher(
                            id,
                            name,
                            email,
                            phone,
                            department
                    );

            if (system.addTeacher(teacher)) {

                showMessage(
                        "Teacher added successfully!"
                );

            } else {

                showMessage(
                        "Teacher ID already exists!"
                );
            }

        } catch (Exception e) {

            showMessage(
                    "Invalid input."
            );
        }
    }

    // ==========================================
    // ADD SUBJECT
    // ==========================================

    private void addSubject() {

        try {

            String id =
                    JOptionPane.showInputDialog(
                            this,
                            "Subject ID:"
                    );

            String name =
                    JOptionPane.showInputDialog(
                            this,
                            "Subject Name:"
                    );

            int credits =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Credit Hours:"
                            )
                    );

            Subject subject =
                    new Subject(
                            id,
                            name,
                            credits
                    );

            if (system.addSubject(subject)) {

                showMessage(
                        "Subject added successfully!"
                );

            } else {

                showMessage(
                        "Subject already exists!"
                );
            }

        } catch (Exception e) {

            showMessage(
                    "Invalid input."
            );
        }
    }

    // ==========================================
    // ENROLL STUDENT
    // ==========================================

    private void enrollStudent() {

        try {

            int studentId =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Student ID:"
                            )
                    );

            String subjectId =
                    JOptionPane.showInputDialog(
                            this,
                            "Subject ID:"
                    );

            if (system.enrollStudent(
                    studentId,
                    subjectId)) {

                showMessage(
                        "Student enrolled successfully!"
                );

            } else {

                showMessage(
                        "Enrollment failed.\n"
                                + "Check Student ID, Subject ID "
                                + "or duplicate enrollment."
                );
            }

        } catch (Exception e) {

            showMessage(
                    "Invalid input."
            );
        }
    }

    // ==========================================
    // ADD MARKS
    // ==========================================

    private void addMarks() {

        try {

            int studentId =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Student ID:"
                            )
                    );

            String subjectId =
                    JOptionPane.showInputDialog(
                            this,
                            "Subject ID:"
                    );

            double marks =
                    Double.parseDouble(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Marks (0-100):"
                            )
                    );

            if (marks < 0 || marks > 100) {

                showMessage(
                        "Marks must be between 0 and 100."
                );

                return;
            }

            if (system.addMarks(
                    studentId,
                    subjectId,
                    marks)) {

                showMessage(
                        "Marks added successfully!"
                );

            } else {

                showMessage(
                        "Enrollment not found."
                );
            }

        } catch (Exception e) {

            showMessage(
                    "Invalid input."
            );
        }
    }

    // ==========================================
    // ATTENDANCE
    // ==========================================

    private void markAttendance() {

        try {

            int studentId =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Student ID:"
                            )
                    );

            String subjectId =
                    JOptionPane.showInputDialog(
                            this,
                            "Subject ID:"
                    );

            String[] options = {
                    "Present",
                    "Absent"
            };

            int choice =
                    JOptionPane.showOptionDialog(
                            this,
                            "Attendance:",
                            "Mark Attendance",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]
                    );

            if (choice == -1) {
                return;
            }

            boolean present = choice == 0;

            if (system.markAttendance(
                    studentId,
                    subjectId,
                    present)) {

                showMessage(
                        "Attendance recorded!"
                );

            } else {

                showMessage(
                        "Enrollment not found."
                );
            }

        } catch (Exception e) {

            showMessage(
                    "Invalid input."
            );
        }
    }

    // ==========================================
    // ACADEMIC EVALUATION
    // ==========================================

    private void evaluateStudent() {

        try {

            int id =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Student ID:"
                            )
                    );

            Student student =
                    system.findStudent(id);

            if (student == null) {

                showMessage(
                        "Student not found."
                );

                return;
            }

            double average =
                    system.getAverageMarks(id);

            String evaluation =
                    system.evaluateStudent(student);

            StringBuilder result =
                    new StringBuilder();

            result.append(
                    "===== ACADEMIC EVALUATION =====\n\n"
            );

            result.append(
                    "Student: "
                            + student.getName()
                            + "\n"
            );

            result.append(
                    "Average Marks: "
                            + String.format(
                                    "%.2f",
                                    average
                            )
                            + "\n\n"
            );

            result.append(
                    "Evaluation:\n"
            );

            result.append(
                    evaluation
            );

            outputArea.setText(
                    result.toString()
            );

        } catch (Exception e) {

            showMessage(
                    "Invalid input."
            );
        }
    }

    // ==========================================
    // VIEW STUDENTS
    // ==========================================

    private void viewStudents() {

        StringBuilder result =
                new StringBuilder();

        result.append(
                "========== STUDENTS ==========\n\n"
        );

        if (system.getStudents().isEmpty()) {

            result.append(
                    "No students found."
            );

        } else {

            for (Student student :
                    system.getStudents()) {

                result.append(
                        "ID: "
                                + student.getId()
                                + "\n"
                );

                result.append(
                        "Name: "
                                + student.getName()
                                + "\n"
                );

                result.append(
                        "Email: "
                                + student.getEmail()
                                + "\n"
                );

                result.append(
                        "Program: "
                                + student.getProgram()
                                + "\n"
                );

                result.append(
                        "Average: "
                                + String.format(
                                        "%.2f",
                                        system.getAverageMarks(
                                                student.getId()
                                        )
                                )
                                + "\n"
                );

                result.append(
                        "Status: "
                                + system.evaluateStudent(
                                        student
                                )
                                + "\n"
                );

                result.append(
                        "-----------------------------\n"
                );
            }
        }

        outputArea.setText(
                result.toString()
        );
    }

    // ==========================================
    // MESSAGE
    // ==========================================

    private void showMessage(String message) {

        JOptionPane.showMessageDialog(
                this,
                message
        );
    }

    // ==========================================
    // MAIN
    // ==========================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            Main app = new Main();

            app.setVisible(true);
        });
    }
}