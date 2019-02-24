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
    private String workName;
    private String description;
    private String picture;
    private String workLabel;
    private String video;
    private String cover;
    private int clientId;
    private int categoryId;
    private int collect;
    private int likes;
    private Date releaseTime;
}
