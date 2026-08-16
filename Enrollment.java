public class Enrollment {

    private Student student;
    private Subject subject;
    private double marks;

    public Enrollment(Student student, Subject subject) {

        this.student = student;
        this.subject = subject;
        this.marks = -1;
    }

    public Student getStudent() {
        return student;
    }

    public Subject getSubject() {
        return subject;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {

        if (marks >= 0 && marks <= 100) {
            this.marks = marks;
        }
    }

    public String getGrade() {

        if (marks < 0) {
            return "Not Graded";
        }

        if (marks >= 80) {
            return "A";
        } else if (marks >= 70) {
            return "B";
        } else if (marks >= 60) {
            return "C";
        } else if (marks >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public boolean isPassed() {

        return marks >= 50;
    }
}