import java.util.ArrayList;
import java.util.List;

public class Student extends Person {

    private String program;
    private List<Subject> subjects;

    public Student(
            int id,
            String name,
            String email,
            String phone,
            String program) {

        super(id, name, email, phone);

        this.program = program;
        this.subjects = new ArrayList<>();
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void enrollSubject(Subject subject) {

        if (!subjects.contains(subject)) {
            subjects.add(subject);
        }
    }

    public boolean isEnrolled(Subject subject) {
        return subjects.contains(subject);
    }

    @Override
    public String displayProfile() {

        return "Student ID: " + getId()
                + "\nName: " + getName()
                + "\nEmail: " + getEmail()
                + "\nPhone: " + getPhone()
                + "\nProgram: " + program;
    }
}