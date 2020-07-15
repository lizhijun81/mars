package proxy.consumer.proxy;



public class Test {
    public static void main(String[] args) {
        LubanMethod methodObj = new LubanMethod();

        LubanDao proxy = (LubanDao) ProxyUtil.newInstance(new LubanDaoImpl(), methodObj);
        proxy.query();

    }


}
