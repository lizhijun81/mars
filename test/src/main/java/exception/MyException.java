package exception;

public class MyException extends Exception {
    private int code;

    public MyException() { }

    public MyException(String msg) {
        super(msg);
    }

    public MyException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public String toString() {
        return "MyException{" + "code=" + code + ", msg=" + super.getMessage() + "}";
    }
}
