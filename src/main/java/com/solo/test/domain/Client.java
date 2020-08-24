package com.solo.test.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
