public class Teacher extends Person {

    private String department;

    public Teacher(
            int id,
            String name,
            String email,
            String phone,
            String department) {

        super(id, name, email, phone);

        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String displayProfile() {

        return "Teacher ID: " + getId()
                + "\nName: " + getName()
                + "\nEmail: " + getEmail()
                + "\nPhone: " + getPhone()
                + "\nDepartment: " + department;
    }
}