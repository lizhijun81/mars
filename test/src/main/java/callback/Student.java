package callback;

public class Student {

    private String name;

    public Student(String name) {
        this.name = name;
    }

    public void question(Callback callback) {
        System.out.println("调用前=========");
        callback.tellAnswer("xiaoming");
        System.out.println("调用后=========");
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
