package com.example.demo.utils;

public class CommonUtil {
	private static final int SKIP_ONE = 1;
	private static final int START_SKIP_TWO = 2;
	private static final int SKIP_FOUR_END = 4;
	private static final char X = 'X';
	private static final char SKIP_UNDERSCORE = '-';

	public static String maskedAccount(String accountNumber) {
		StringBuilder sb = new StringBuilder();
		if(sb.length()!=0) {
		
		for (int i = 0; i < sb.length() - SKIP_FOUR_END; i++) {
			sb.setCharAt(i, X);
		}
		}
		return sb.toString();
	}

	public static String maskedFirstName(String firstName) {
		StringBuilder sb = new StringBuilder(firstName);
		if(sb.length()!=0) {
		for (int i = SKIP_ONE; i < sb.length(); i++) {
			sb.setCharAt(i, X);
		}
		}
		return sb.toString();
	}

	public static String maskedPhoneNumber(String phone) {
		StringBuilder maskedPhone = new StringBuilder();
		if(maskedPhone.length()!=0) {
		// Mask the first six digits
		maskedPhone.append("XXX-XXX-");
		maskedPhone.append(phone.substring(6));
        // Display the last four digits
		}
		return maskedPhone.toString();
	}

	public static String maskedAccount2(String accountNumber) {
		StringBuilder sb = new StringBuilder(accountNumber);
		if(sb.length()!=0) {
		for (int i = START_SKIP_TWO; i < sb.length() - SKIP_FOUR_END; i++) {

			sb.setCharAt(i, X);
		}
		}
		return sb.toString();

	}

}