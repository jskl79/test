package com.solo.test.controller;

import com.solo.test.domain.Client;
import com.solo.test.domain.Test;
import com.solo.test.repository.ClientRepository;
import com.solo.test.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TestRepository testRepository;

    @RequestMapping("/")
    public ResponseEntity<Test> reportArticle(HttpServletRequest req) {

        Test test = new Test();

        return new ResponseEntity<Test>(test, HttpStatus.OK);
    }

    @RequestMapping("/init")
    public ResponseEntity<Client> init(HttpServletRequest req) {

        Client client1 = new Client();
        client1.setDriver("com.mysql.cj.jdbc.Driver");
        client1.setUrl("jdbc:mysql://115.68.228.63:3306/multi01?serverTimezone=UTC&characterEncoding=UTF-8");
        client1.setUsername("root");
        client1.setPassword("tck1966!");

        Client client2 = new Client();
        client2.setDriver("com.mysql.cj.jdbc.Driver");
        client2.setUrl("jdbc:mysql://115.68.228.63:3306/multi02?serverTimezone=UTC&characterEncoding=UTF-8");
        client2.setUsername("root");
        client2.setPassword("tck1966!");

        clientRepository.save(client1);
        clientRepository.save(client2);

        return new ResponseEntity<Client>(client1, HttpStatus.OK);
    }

    @RequestMapping("/list")
    public ResponseEntity<List<Test>> listTest(HttpServletRequest req) {
        System.out.println("/list");
        List<Test> tests = testRepository.findAll();

        return new ResponseEntity<List<Test>>(tests, HttpStatus.OK);
    }
}
