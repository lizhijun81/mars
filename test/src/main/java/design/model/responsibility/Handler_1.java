package design.model.responsibility;

public class Handler_1 extends Handler {

    @Override
    public void handler() {
        System.out.println("handler....1");
        if (nextHandler == null) {
            return;
        }
        nextHandler.handler();
    }
}
