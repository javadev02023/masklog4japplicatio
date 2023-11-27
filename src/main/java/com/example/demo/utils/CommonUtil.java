package com.example.demo.utils;

public class CommonUtil {
	private static final int SKIP_ONE = 1;
	private static final int START_SKIP_TWO = 2;
	private static final int SKIP_FOUR_END = 4;
	private static final char X = 'X';
	private static final char SKIP_UNDERSCORE = '-';

	public static String maskedAccount(String accountNumber) {
		StringBuilder sb = new StringBuilder(accountNumber);
		for (int i = 0; i < sb.length() - SKIP_FOUR_END; i++) {
			sb.setCharAt(i, X);
		}
		return sb.toString();
	}

	public static String maskedFirstName(String firstName) {
		StringBuilder sb = new StringBuilder(firstName);
		for (int i = 0; i < sb.length() - SKIP_ONE; i++) {
			sb.setCharAt(i, X);
		}
		return sb.toString();
	}

	public static String maskedPhoneNumber(String phone) {
		StringBuilder sb = new StringBuilder(phone);
		for (int i = 0; i < sb.length() - SKIP_FOUR_END; i++) {

			if (sb.charAt(i) != SKIP_UNDERSCORE) {
				sb.setCharAt(i, X);
			}
		}
		return sb.toString();
	}

	public static String maskedAccount2(String accountNumber) {
		StringBuilder sb = new StringBuilder(accountNumber);
		for (int i = START_SKIP_TWO; i < sb.length() - SKIP_FOUR_END; i++) {

			sb.setCharAt(i, X);
		}
		return sb.toString();

	}

}