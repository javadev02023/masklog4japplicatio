package com.example.demo.utils;

public class Utils {
	public static String removeInvalidChar(String s) {
		return s.replace("\n", "").replace("\\", "");
		
	}

}
