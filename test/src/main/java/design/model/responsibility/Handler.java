package design.model.responsibility;

public abstract class Handler {

    public Handler nextHandler;

    public abstract void handler();

}
