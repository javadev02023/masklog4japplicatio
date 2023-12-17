package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.beans.Person;
import com.example.demo.utils.Utils;

@SpringBootApplication
public class Demo1Application {
	// private static final Logger log =
	// LoggerFactory.getLogger(Demo1Application.class);
	private static final Logger logger = LogManager.getLogger(Demo1Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
		String AccountNumber = "123456789";
		Person person = new Person("FooTest", "BarTest", 20, 123456788, "ABC XYZ Street","123456789");
		logger.info("Person Data {}" + person);
		
		  logger.info("Person Data {AccountNumber}"+AccountNumber);
		  
		  
		  String jsonString="{\n" + "\"accountNumber\":\"123456789\",\n" +
		  "\"phoneNumber\":\"1234563890\",\n" + "\"AccountNumber\":\"453456789\",\n" +
		  "\"couponCode\":\"SUPERSALE\"\n" + "}"; logger.info("json"+jsonString);
		  
		  String lastName="MCHOME"; String firstName="MCHOME2";
		  String phoneNumber="1234563890";
		  logger.info("phoneNumber='"+phoneNumber+"'");
		  logger.info("lastName="+lastName+",firstName="+firstName);
		 

	}

}
