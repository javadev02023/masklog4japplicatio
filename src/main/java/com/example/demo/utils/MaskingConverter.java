package com.example.demo.utils;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Plugin(name = "MaskingConverter", category = "Converter")
@ConverterKeys({ "mask" })
public class MaskingConverter extends LogEventPatternConverter {

	private static final String firstNamePattern = "(?<=firstName=')[^']+?(?=')|(?<=\"firstName\":\")[^\"]+?(?=\")|(?<=firstName=)[^')]+?(?=,)";
	private static final String accountNumberPattern = "(?<=\\\"AccountNumber\\\":\\\")[^\\\"]+?(?=\\\")|(?<=AccountNumber=')[^']+?(?=')|(?<=\\\"AccountNumber\\\":\\\")[^\\\"]+?(?=\\\")";

	private static final String lastNamePattern = "(?<=lastName=')[^']+?(?=')|(?<=\"lastName\":\")[^\"]+?(?=\")|(?<=lastName=)[^')]+?(?=,)";
	private static final String phoneNumberPattern = "(?<=phoneNumber=')[^']+?(?=')|(?<=\"phoneNumber\":\")[^\"]+?(?=\")";
	private static final String accountNumber2Pattern = "(?<=accountNumber=')[^']+?(?=')|(?<=\"accountNumber\":\")[^\"]+?(?=\")";
//added code here
	private static final Map<String, Pattern> patterns = new HashMap<>();
	// added
	private static final List<String> applicableFields = new ArrayList<String>();

	static {
		patterns.put("AccountNumber", Pattern.compile(accountNumberPattern));
		patterns.put("firstName", Pattern.compile(firstNamePattern));
		patterns.put("lastName", Pattern.compile(lastNamePattern));
		patterns.put("phoneNumber", Pattern.compile(phoneNumberPattern));
		patterns.put("accountNumber", Pattern.compile(accountNumber2Pattern));
		// if mask needed field should be exist here
		applicableFields.add("firstName");
		applicableFields.add("AccountNumber");
		applicableFields.add("lastName");
		applicableFields.add("phoneNumber");
		applicableFields.add("accountNumber");
	}
	private final PatternLayout patternLayout;

	protected MaskingConverter(String[] options) {
		super("mask", "mask");
		this.patternLayout = createPatternLayout(options);
	}

	public static MaskingConverter newInstance(String[] options) {
		return new MaskingConverter(options);
	}

	private PatternLayout createPatternLayout(String[] options) {
		System.out.println("Options: " + Arrays.toString(options));
		if (options == null || options.length == 0) {
			throw new IllegalArgumentException("Options for MaskingConverter are missing.");
		}
		return PatternLayout.newBuilder().withPattern(options[0]).build();
	}

	@Override
	public void format(LogEvent event, StringBuilder toAppendTo) {
		String formattedMessage = patternLayout.toSerializable(event);
		String maskedMessage = null;
		boolean containsWord = isMaskApplicable(formattedMessage, applicableFields);
		if (containsWord) {
			maskedMessage = maskSensitiveValues(formattedMessage);
			toAppendTo.setLength(0);
			toAppendTo.append(maskedMessage);
		} else {
			maskedMessage = formattedMessage;
		}
	}

	public static String maskSensitiveValues(String message) {
		for (Map.Entry<String, Pattern> entry : patterns.entrySet()) {
			String type = entry.getKey();
			Pattern pattern = entry.getValue();
			String maskedValue = maskFieldValue(message, pattern, type);
			message = message.replaceAll(pattern.pattern(), maskedValue);
		}
		return message;
	}

	private static String maskFieldValue(String message, Pattern pattern, String type) {
		String fieldValue = "";
		switch (type) {
		case "AccountNumber":
			fieldValue = extractFieldValue(message, pattern);
			return CommonUtil.maskedAccount(fieldValue);
		case "firstName":
			fieldValue = extractFieldValue(message, pattern);
			return CommonUtil.maskedFirstName(fieldValue);
		case "lastName":
			fieldValue = extractFieldValue(message, pattern);
			return CommonUtil.maskedFirstName(fieldValue);
		case "phoneNumber":
			fieldValue = extractFieldValue(message, pattern);
			return CommonUtil.maskedPhoneNumber(fieldValue);
		case "accountNumber":
			fieldValue = extractFieldValue(message, pattern);
			return CommonUtil.maskedAccount2(fieldValue);
		default:
			return fieldValue;
		}
	}

	private static String extractFieldValue(String message, Pattern pattern) {
		Matcher matcher = pattern.matcher(message);
		return matcher.find() ? matcher.group() : "not found";
	}

	private static boolean isMaskApplicable(String message, List<String> fieldList) {
		String patternString = "\\b(" + String.join("|", fieldList) + ")\\b";
		Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(message);

		return matcher.find();
	}

}