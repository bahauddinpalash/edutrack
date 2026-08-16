public class AttendanceRecord {

    private Student student;
    private Subject subject;
    private int totalClasses;
    private int attendedClasses;

    public AttendanceRecord(
            Student student,
            Subject subject) {

        this.student = student;
        this.subject = subject;
        this.totalClasses = 0;
        this.attendedClasses = 0;
    }

    public Student getStudent() {
        return student;
    }

    public Subject getSubject() {
        return subject;
    }

    public int getTotalClasses() {
        return totalClasses;
    }

    public int getAttendedClasses() {
        return attendedClasses;
    }

    public void addClass(boolean attended) {

        totalClasses++;

        if (attended) {
            attendedClasses++;
        }
    }

    public double getAttendancePercentage() {

        if (totalClasses == 0) {
            return 0;
        }

        return ((double) attendedClasses
                / totalClasses) * 100;
    }

    public boolean needsAttention() {

        return getAttendancePercentage() < 75;
    }
}