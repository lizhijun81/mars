package jvm.gc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * jvm gc 工具 查看 内存、线程、gc 监控信息
 * -Xmx600m  -Xms600m -XX:SurvivorRatio=8
 */
public class GCToolsTest {

    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws InterruptedException {
        List<ByteHold> list = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            list.add(new ByteHold(RANDOM.nextInt(1000)));
            Thread.sleep(500);
        }
    }
}

class ByteHold {
    private byte[] byteHold = null;

    public ByteHold(int byteLength) {
        this.byteHold = new byte[byteLength];
    }
}
