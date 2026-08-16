import java.io.*;
import java.util.*;

public class EducationSystem implements AcademicSupport {

    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;
    private ArrayList<Subject> subjects;
    private ArrayList<Enrollment> enrollments;
    private ArrayList<AttendanceRecord> attendanceRecords;

    private HashMap<Integer, Double> studentPerformance;

    private final String FILE_NAME = "students.txt";

    public EducationSystem() {

        students = new ArrayList<>();
        teachers = new ArrayList<>();
        subjects = new ArrayList<>();
        enrollments = new ArrayList<>();
        attendanceRecords = new ArrayList<>();

        studentPerformance = new HashMap<>();

        loadStudents();
    }

    // ==========================================
    // STUDENT MANAGEMENT
    // ==========================================

    public boolean addStudent(Student student) {

        if (findStudent(student.getId()) != null) {
            return false;
        }

        students.add(student);

        saveStudents();

        return true;
    }

    public Student findStudent(int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    // ==========================================
    // TEACHER MANAGEMENT
    // ==========================================

    public boolean addTeacher(Teacher teacher) {

        if (findTeacher(teacher.getId()) != null) {
            return false;
        }

        teachers.add(teacher);

        return true;
    }

    public Teacher findTeacher(int id) {

        for (Teacher teacher : teachers) {

            if (teacher.getId() == id) {
                return teacher;
            }
        }

        return null;
    }

    // ==========================================
    // SUBJECT MANAGEMENT
    // ==========================================

    public boolean addSubject(Subject subject) {

        if (findSubject(subject.getSubjectId()) != null) {
            return false;
        }

        subjects.add(subject);

        return true;
    }

    public Subject findSubject(String id) {

        for (Subject subject : subjects) {

            if (subject.getSubjectId()
                    .equalsIgnoreCase(id)) {

                return subject;
            }
        }

        return null;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    // ==========================================
    // ENROLLMENT
    // ==========================================

    public boolean enrollStudent(
            int studentId,
            String subjectId) {

        Student student = findStudent(studentId);

        Subject subject = findSubject(subjectId);

        if (student == null || subject == null) {
            return false;
        }

        if (student.isEnrolled(subject)) {
            return false;
        }

        student.enrollSubject(subject);

        Enrollment enrollment =
                new Enrollment(student, subject);

        enrollments.add(enrollment);

        AttendanceRecord attendance =
                new AttendanceRecord(student, subject);

        attendanceRecords.add(attendance);

        return true;
    }

    // ==========================================
    // FIND ENROLLMENT
    // ==========================================

    public Enrollment findEnrollment(
            int studentId,
            String subjectId) {

        for (Enrollment enrollment : enrollments) {

            if (enrollment.getStudent().getId() == studentId
                    && enrollment.getSubject()
                    .getSubjectId()
                    .equalsIgnoreCase(subjectId)) {

                return enrollment;
            }
        }

        return null;
    }

    // ==========================================
    // ADD MARKS
    // ==========================================

    public boolean addMarks(
            int studentId,
            String subjectId,
            double marks) {

        Enrollment enrollment =
                findEnrollment(studentId, subjectId);

        if (enrollment == null) {
            return false;
        }

        enrollment.setMarks(marks);

        updatePerformance(studentId);

        return true;
    }

    // ==========================================
    // ATTENDANCE
    // ==========================================

    public boolean markAttendance(
            int studentId,
            String subjectId,
            boolean attended) {

        for (AttendanceRecord record : attendanceRecords) {

            if (record.getStudent().getId() == studentId
                    && record.getSubject()
                    .getSubjectId()
                    .equalsIgnoreCase(subjectId)) {

                record.addClass(attended);

                return true;
            }
        }

        return false;
    }

    public AttendanceRecord getAttendance(
            int studentId,
            String subjectId) {

        for (AttendanceRecord record : attendanceRecords) {

            if (record.getStudent().getId() == studentId
                    && record.getSubject()
                    .getSubjectId()
                    .equalsIgnoreCase(subjectId)) {

                return record;
            }
        }

        return null;
    }

    // ==========================================
    // PERFORMANCE
    // ==========================================

    private void updatePerformance(int studentId) {

        double total = 0;
        int count = 0;

        for (Enrollment enrollment : enrollments) {

            if (enrollment.getStudent().getId() == studentId
                    && enrollment.getMarks() >= 0) {

                total += enrollment.getMarks();
                count++;
            }
        }

        if (count > 0) {

            studentPerformance.put(
                    studentId,
                    total / count
            );
        }
    }

    public double getAverageMarks(int studentId) {

        if (!studentPerformance.containsKey(studentId)) {

            updatePerformance(studentId);
        }

        return studentPerformance.getOrDefault(
                studentId,
                0.0
        );
    }

    // ==========================================
    // ACADEMIC SUPPORT
    // ==========================================

    @Override
    public boolean needsAcademicSupport(Student student) {

        double average =
                getAverageMarks(student.getId());

        boolean lowMarks = average > 0 && average < 50;

        boolean lowAttendance = false;

        for (AttendanceRecord record : attendanceRecords) {

            if (record.getStudent().getId()
                    == student.getId()) {

                if (record.needsAttention()) {

                    lowAttendance = true;
                    break;
                }
            }
        }

        return lowMarks || lowAttendance;
    }

    @Override
    public String evaluateStudent(Student student) {

        double average =
                getAverageMarks(student.getId());

        if (average == 0) {

            return "No academic data available.";
        }

        if (needsAcademicSupport(student)) {

            return "Academic support recommended.";
        }

        if (average >= 80) {

            return "Excellent academic performance.";
        }

        if (average >= 70) {

            return "Good academic performance.";
        }

        if (average >= 50) {

            return "Satisfactory academic performance.";
        }

        return "Student is at academic risk.";
    }

    // ==========================================
    // POLYMORPHISM DEMONSTRATION
    // ==========================================

    public String getProfile(Person person) {

        return person.displayProfile();
    }

    // ==========================================
    // FILE HANDLING
    // ==========================================

    private void saveStudents() {

        try {

            PrintWriter writer =
                    new PrintWriter(
                            new FileWriter(FILE_NAME)
                    );

            for (Student student : students) {

                writer.println(
                        student.getId() + "|" +
                        student.getName() + "|" +
                        student.getEmail() + "|" +
                        student.getPhone() + "|" +
                        student.getProgram()
                );
            }

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "Error saving students: "
                            + e.getMessage()
            );
        }
    }

    private void loadStudents() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(file)
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length == 5) {

                    Student student =
                            new Student(
                                    Integer.parseInt(data[0]),
                                    data[1],
                                    data[2],
                                    data[3],
                                    data[4]
                            );

                    students.add(student);
                }
            }

            reader.close();

        } catch (IOException | NumberFormatException e) {

            System.out.println(
                    "Error loading students: "
                            + e.getMessage()
            );
        }
    }

    // ==========================================
    // GETTERS
    // ==========================================

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public ArrayList<Enrollment> getEnrollments() {
        return enrollments;
    }

    public ArrayList<AttendanceRecord> getAttendanceRecords() {
        return attendanceRecords;
    }
}