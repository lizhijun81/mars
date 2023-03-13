//package unsafe;
//
//import sun.misc.Unsafe;
//import java.lang.reflect.Field;
//
//public class UnsafeTest {
//    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
//
//        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[]{1,2,3,4,5});
//        atomicIntegerArray.incrementAndGet(1);
//
//        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
//        theUnsafe.setAccessible(true);
//        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
//        System.out.println(unsafe);
//
//        Player player = new Player(20, "xiaoming");
//        System.out.println(player);
//
//        // 类和实例对象以及变量的操作
//        // 通过 unsafe 修改 player 内存中的值
//        System.out.println("======类和实例对象以及变量的操作 实例化=========");
//        long age_offset = unsafe.objectFieldOffset(Player.class.getDeclaredField("age"));
//        long name_offset = unsafe.objectFieldOffset(Player.class.getDeclaredField("name"));
//        unsafe.putInt(player, age_offset, 18);
//        unsafe.putObject(player, name_offset, "小红");
//        System.out.println(player);
//
//        System.out.println("======allocateInstance 实例化=========");
//
//        // 通过 unsafe 直接创建 Player 类实例，不会调用 构造器方法，直接实例化；属性值都为默认值；
//        // 通过反射的方式
//        Player player_1 = (Player) unsafe.allocateInstance(Player.class);
//        System.out.println(player_1);
//    }
//}
//
//class Player {
//    private int age;
//    private String name;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    private Player() {
//        System.out.println("默认构造器");
//    }
//
//    public Player(int age, String name) {
//        System.out.println("指定构造器");
//        this.age = age;
//        this.name = name;
//    }
//
//    @Override
//    public String toString() {
//        return "Player{" +
//                "age=" + age +
//                ", name='" + name + '\'' +
//                '}';
//    }
//}