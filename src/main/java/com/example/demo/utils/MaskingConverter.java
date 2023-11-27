package com.example.demo.utils;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Plugin(name = "MaskingConverter", category = "Converter")
@ConverterKeys({"mask"})
public class MaskingConverter extends LogEventPatternConverter {
	private static final String firstNamePattern="(?<=firstName=')[^']+?(?=')|(?<=\"firstName\":\")[^\"]+?(?=\")";
	private static final String accountNumberPattern="(?<=AccountNumber=')[^']+?(?=')|(?<=\"AccountNumber\":\")[^\"]+?(?=\")";
	private static final String lastNamePattern="(?<=lastName=')[^']+?(?=')|(?<=\"lastName\":\")[^\"]+?(?=\")";
	private static final String phoneNumberPattern="(?<=phoneNumber=')[^']+?(?=')|(?<=\"phoneNumber\":\")[^\"]+?(?=\")";
	private static final String accountNumber2Pattern="(?<=accountNumber=')[^']+?(?=')|(?<=\"accountNumber\":\")[^\"]+?(?=\")";

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
        String maskedMessage = maskSensitiveValues(formattedMessage);
        toAppendTo.setLength(0);
        toAppendTo.append(maskedMessage);
    }

	private String maskSensitiveValues(String message) {
		message = message.replaceAll(accountNumberPattern,CommonUtil.maskedAccount(extractFieldValue(message, accountNumberPattern)));
		message = message.replaceAll(firstNamePattern,CommonUtil.maskedFirstName(extractFieldValue(message, firstNamePattern)));
		message = message.replaceAll(lastNamePattern,CommonUtil.maskedFirstName(extractFieldValue(message, lastNamePattern)));
		message = message.replaceAll(phoneNumberPattern,CommonUtil.maskedPhoneNumber(extractFieldValue(message, phoneNumberPattern)));
		message = message.replaceAll(accountNumber2Pattern,CommonUtil.maskedAccount2(extractFieldValue(message, accountNumber2Pattern)));
	
    	
		/*
		 * // Replace sensitive values with masked value message =
		 * message.replaceAll(firstNamePattern, "****");
		 * 
		 * message = message.replaceAll(
		 * "(?<=lastName=')[^']+?(?=')|(?<=\"lastName\":\")[^\"]+?(?=\")", "****");
		 * message = message.replaceAll(
		 * "(?<=age=)\\d+(?=(,|\\s|}))|(?<=\"age\":)\\d+(?=(,|\\s|}))", "****"); message
		 * = message.replaceAll(
		 * "(?<=creditCardNumber=)\\d+(?=(,|\\s|}))|(?<=\"creditCardNumber\":)\\d+(?=(,|\\s|}))"
		 * ,"****");
		 */
		 
        return message;
    }
    private static String extractFieldValue(String input,String regexPatern) {
        Pattern pattern = Pattern.compile(regexPatern);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group();
        } else {
            return "No match found";
        }
    }
   
}