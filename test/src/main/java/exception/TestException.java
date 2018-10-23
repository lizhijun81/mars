package exception;

public class TestException {

    public void a() throws MyException {
        throw new MyException();
    }

    public void b() throws MyException {
        throw new MyException("test exception");
    }

    public void c() throws MyException {
        throw new MyException(1, "test exception");
    }

    public void d() {
        try {
            c();
        } catch (MyException e) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                System.out.println(stackTraceElement.getMethodName());
                System.out.println("================================");
            }
        }
    }

    public void e() {
        d();
    }

    public void f(){
        e();
    }

    public void h() throws Exception {
        try {
            a();
        } catch (MyException e) {
//            throw e;  // 异常中包含原来异常抛出点的调用栈信息
            throw (Exception) e.fillInStackTrace(); // // 异常中只包含该异常抛出点的调用栈信息
        }
    }

    public void g() throws RuntimeException {
//        throw new RuntimeException();
    }

    public int l(){
        try {
            a();
        } catch (MyException e) {
            System.out.println("catch exception");
        } finally {
            System.out.println("finally before");
            return 1;
        }
    }

    public static void main(String[] args) {
        TestException testException = new TestException();

//        try {
//            testException.a();
//        } catch (MyException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            testException.b();
//        } catch (MyException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            testException.c();
//        } catch (MyException e) {
//            e.printStackTrace();
//        }

        try {
            testException.h();
        } catch (Exception e) {
            System.out.println("catch exception");
        } finally {
            System.out.println("finally before");
            return;
        }
//        System.out.println("ssss");

//        testException.g();
    }
}
