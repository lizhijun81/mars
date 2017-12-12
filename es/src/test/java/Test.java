

public class Test {
    private Long a;
    public Long getA() {
        return a;
    }
    public void setA(Long a) {
        this.a = a;
    }
    public static void main(String[] args) {
        Test test = new Test();// 基础数据类型的封装类型null，在拆箱是会报null point
//        long a = test.getA();


        if("0".equals(null)){//字符串和null不相等
            System.out.println("sssss");
        }
        System.out.println("qqqqqqqq");

//        String s = "a人s";
//        System.out.println(s.substring(0,6));
    }
}
