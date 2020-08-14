package com.solo.test.controller;

import com.solo.test.domain.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class TestController {

    @RequestMapping("/")
    public ResponseEntity<Test> reportArticle(HttpServletRequest req) {

        Test test = new Test();

        return new ResponseEntity<Test>(test, HttpStatus.OK);
    }
}
