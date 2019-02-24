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
    private String clientName;
    private String password;
    private String mailBox;
    private String realName;
    private String mobile;
    private Integer cityId;
    private Integer companyId;
    private Integer occupationId;
}
