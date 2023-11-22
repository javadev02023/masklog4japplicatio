package com.example.demo.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class PersonController {

    private final Logger logger=LogManager.getLogger(PersonController.class);

    @GetMapping("list/person")
    public ResponseEntity<List<Person>> getPersonList() throws JsonProcessingException {
        List<Person> personList = List.of(
                new Person("Foo", "Bar", 25, 123456789, "ABC XYZ Street"),
                new Person("Baz", "Qux", 30, 987654321, "MNO PQR Street")
        );

        logger.info("Person List {}", personList);

        String jsonOutput = new ObjectMapper().writeValueAsString(personList);

        logger.info("Person List - JSON output {}", jsonOutput);

        return new ResponseEntity<>(personList, HttpStatus.OK);
    }
}