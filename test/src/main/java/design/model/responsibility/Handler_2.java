package design.model.responsibility;

public class Handler_2 extends Handler {

    @Override
    public void handler() {
        System.out.println("handler....2");
        if (nextHandler == null) {
            return;
        }
        nextHandler.handler();
    }
}
