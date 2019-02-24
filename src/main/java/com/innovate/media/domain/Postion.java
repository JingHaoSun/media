package com.innovate.media.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Postion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int clientId;
    private String postionName;
    private int salary;
    private int workage;
    private String description;
    private int recruitment;
    private String postionLabel;
    private String contact;
    private String phone;
}
