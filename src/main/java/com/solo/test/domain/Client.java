package com.solo.test.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter @NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id @GeneratedValue
    @Column(name = "client_id")
    private long id;

    private String name;

    private String driver;
    private String username;
    private String password;
    private String url;

}
