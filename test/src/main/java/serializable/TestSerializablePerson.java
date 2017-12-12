package serializable;

import java.io.*;

public class TestSerializablePerson {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        serializablePerson();
        System.out.println(deserializablePerson().toString());
    }

    /**
     * 序列化
     * @throws IOException
     */
    public static void serializablePerson() throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream(
                        new File("person.txt")));

        Person person = new Person();
        person.setName("小明");
        person.setAge(20);
        person.setSex("男");

        outputStream.writeObject(person);
        outputStream.close();
    }

    /**
     * 反序列化
     */
    public static Person deserializablePerson() throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream(
                        new File("person.txt")));
        return (Person) inputStream.readObject();
    }
}
