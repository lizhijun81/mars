package com.lizj.socket;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageUtil {

	/**
	 * 返回len位的数字，不会少于len长度
	 * 
	 * @param len（4位=1000，5位=10000，6位=100000）
	 * @return
	 */
	public static int randomNum(int len) {
		return (int) ((Math.random() * 9 + 1) * len);
	}

	/**
	 * int 与 byte 的相互转换
	 * 
	 * @param x
	 * @return
	 */
	public static byte intToByte(int x) {
		return (byte) x;
	}

	/**
	 * byte 与 int 的相互转换
	 * 
	 * @param b
	 * @return
	 */
	public static int byteToInt(byte b) {
		// Java 总是把 byte 当做有符处理；我们可以通过将其和 0xFF 进行二进制与得到它的无符值
		return b & 0xFF;
	}

	private static boolean isHexData(char data) {
		return (data >= '0' && data <= '9') || (data >= 'a' && data <= 'f');
	}

	private static boolean isNumber(char data) {
		return data >= '0' && data <= '9';
	}

	private static int getHighData(char data) {
		if (isNumber(data))
			return (data - '0') << 4;
		else
			return (data - 'a' + 10) << 4;
	}

	private static int getLowData(char data) {
		if (isNumber(data))
			return data - '0';
		else
			return (data - 'a') + 10;
	}

	private static byte getByteByHighAndLowBit(char highBit, char lowBit) throws Exception {
		if (!(isHexData(highBit) && isHexData(lowBit))) {
			System.out.println(highBit);
			System.out.println(lowBit);
			throw new Exception("需要解析的数据不合法，请确认！");
		}
		return (byte) (getHighData(highBit) + getLowData(lowBit));
	}

	public static byte[] hexToBytes(String hexString) throws Exception {
		byte[] bytes = new byte[hexString.length() / 2];
		int index = 0;
		for (int i = 0; i < hexString.length(); i++) {
			char highBit = hexString.charAt(i);
			char lowBit = hexString.charAt(++i);
			byte data = getByteByHighAndLowBit(highBit, lowBit);
			bytes[index++] = data;
		}
		return bytes;
	}

	public static int hexToInteger(String hex) {
		int value = 0;
		for (int i = 0; i < hex.length(); i++) {
			value <<= 4;
			char data = hex.charAt(i);
			value += getLowData(data);
		}
		return value;
	}

	public static String byteToHexString(byte[] bytes, boolean isSpace) {
	    String format = isSpace ? "%02X " : "%02X";
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format(format, b));
		}
		return sb.toString();
	}

	public static String getTime(long timeMillis) {
		if (timeMillis == 0) {
			timeMillis = System.currentTimeMillis();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date(timeMillis));
	}

}
