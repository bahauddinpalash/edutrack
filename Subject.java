public class Subject {

    private String subjectId;
    private String subjectName;
    private int creditHours;
    private Teacher teacher;

    public Subject(
            String subjectId,
            String subjectName,
            int creditHours) {

        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.creditHours = creditHours;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {

        String teacherName =
                teacher == null
                        ? "Not Assigned"
                        : teacher.getName();

        return subjectId
                + " - "
                + subjectName
                + " (" + creditHours + " credits)"
                + " | Teacher: "
                + teacherName;
    }
}