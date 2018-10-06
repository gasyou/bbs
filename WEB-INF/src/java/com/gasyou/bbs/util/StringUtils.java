package com.gasyou.bbs.util;

public final class StringUtils {

	public static final String EMPTY = "";

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean equals(String str1, String str2) {
		return value(str1, EMPTY).equals(value(str2, EMPTY));
	}

	public static String value(String str, String defaultValue) {
		return isEmpty(str) ? defaultValue : str;
	}
}
