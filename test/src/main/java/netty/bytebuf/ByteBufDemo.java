package netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledHeapByteBuf;

public class ByteBufDemo {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.buffer();

        String test = "abcdefgh";
        byte[] params = new byte[test.length()];
        for (int i = 0; i < test.length(); i++) {
            params[i] = (byte) test.charAt(i);
        }

        byteBuf.writeBytes(params);

        String s = ByteBufUtil.hexDump(byteBuf);
        System.out.println(s);

    }
}
