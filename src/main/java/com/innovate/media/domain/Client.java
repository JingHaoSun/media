package com.innovate.media.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String client_name;
    private String password;
    private String mailBox;
    private String real_name;
    private String mobile;
    private Integer city_id;
    private Integer company_id;
    private Integer occupation_id;
}
