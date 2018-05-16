package callback;

public class Main {
    public static void main(String[] args) {
        Student student = new Student("YY");

        student.question((Callback<Student>) Student::toString);
    }
}
