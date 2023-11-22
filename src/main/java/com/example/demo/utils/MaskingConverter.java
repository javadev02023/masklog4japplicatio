package com.example.demo.utils;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

import java.util.Arrays;

@Plugin(name = "MaskingConverter", category = "Converter")
@ConverterKeys({"mask"})
public class MaskingConverter extends LogEventPatternConverter {

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
        // Replace sensitive values with masked value    	
        message = message.replaceAll("(?<=firstName=')[^']+?(?=')|(?<=\"firstName\":\")[^\"]+?(?=\")", "****");
		
		message = message.replaceAll("(?<=lastName=')[^']+?(?=')|(?<=\"lastName\":\")[^\"]+?(?=\")", "****");
		message = message.replaceAll("(?<=age=)\\d+(?=(,|\\s|}))|(?<=\"age\":)\\d+(?=(,|\\s|}))", "****");
		message = message.replaceAll("(?<=creditCardNumber=)\\d+(?=(,|\\s|}))|(?<=\"creditCardNumber\":)\\d+(?=(,|\\s|}))","****");
		 
        return message;
    }
   
}