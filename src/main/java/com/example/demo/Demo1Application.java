package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.beans.Person;
import com.example.demo.utils.Utils;

@SpringBootApplication
public class Demo1Application {
	//private static final Logger log = LoggerFactory.getLogger(Demo1Application.class);
private static final Logger logger=LogManager.getLogger(Demo1Application.class);
	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
		Person person=	new Person("FooTest", "BarTest", 20, 123456788, "ABC XYZ Street");
		logger.info("Person Data {}"+person);
		
		  String jsonString="{\n" + "\"AccountNumber\":\"MAC\",\n" +
		  "\"description\":\"Cool\",\n" + "\"price\":\"1200\",\n" +
		  "\"couponCode\":\"SUPERSALE\"\n" + "}";
		  logger.info("json"+jsonString);
		 
		
		System.out.println(Utils.removeInvalidChar(jsonString));
		
	}

}
