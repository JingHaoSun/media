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
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String work_name;
    private String description;
    private String picture;
    private String work_label;
    private String video;
    private String cover;
    private String comment;
    private int client_id;
    private int category_id;
    private int collect;
    private int likes;
    private Date release_time;
}
