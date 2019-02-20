package reflection;

import classload.MyClassLoad;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class ReflectionTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchFieldException {

//        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[]{1,2,3,4,5});
//        atomicIntegerArray.incrementAndGet(1);


        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        System.out.println(unsafe);

        long age_offset = unsafe.objectFieldOffset(Player.class.getDeclaredField("age"));
        long name_offset = unsafe.objectFieldOffset(Player.class.getDeclaredField("name"));

        Player player = new Player(20, "xiaoming");
        System.out.println(player);

        // 通过 unsafe 修改 player 内存中的值
        unsafe.putInt(player, age_offset, 18);
        unsafe.putObject(player, name_offset, "小红");
        System.out.println(player);
    }
}

class Player {
    private int age;
    private String name;
    private Player() {
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public Player(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}