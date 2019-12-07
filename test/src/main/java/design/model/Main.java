package design.model;

import design.model.responsibility.Handler_1;
import design.model.responsibility.Handler_2;
import design.model.responsibility.Handler_3;

public class Main {

    public static void main(String[] args) {
        Handler_1 handler_1 = new Handler_1();
        Handler_2 handler_2 = new Handler_2();
        Handler_3 handler_3 = new Handler_3();

        handler_1.nextHandler = handler_2;

        handler_2.nextHandler = handler_3;

        handler_1.handler();
    }
}
