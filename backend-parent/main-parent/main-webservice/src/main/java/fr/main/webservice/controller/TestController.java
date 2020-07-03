package fr.main.webservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TestController {

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping(value = "/blabla")
    public ResponseEntity blabla() {
        return ResponseEntity.ok().build();
    }

}
