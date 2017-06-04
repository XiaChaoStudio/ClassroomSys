package com.xc.util;

import java.util.UUID;

public class UUIDUtils {
	/*
	 * 获得默认UUID
	 */
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
				+ s.substring(19, 23) + s.substring(24);
	}

	/**
	 * 获得指定的UUID
	 * 
	 * @param int number 需要获得的UUID位数
	 *
	 */
	public static String getUUID(int number) {
		if (number < 1) {
			return null;
		}
		String ss = new String();
		ss = getUUID();
		return ss.substring(0, number);
	}

}
