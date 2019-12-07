package design.model.responsibility;

public class Handler_3 extends Handler {
    @Override
    public void handler() {
        System.out.println("handler....3");
        if (nextHandler == null) {
            return;
        }
        nextHandler.handler();
    }
}
