package com.innovate.media.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;

    private String password;




    public Admin(String name, String password) {
        this.userName = name;
        this.password = password;
    }

    public Admin() {
    }

    public Admin(Long id, String password) {
        this.id = id;
        this.password = password;
    }
}
