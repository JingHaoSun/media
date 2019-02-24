package com.innovate.media.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class AdminLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long adminId;
    private String loginIp;
    private Timestamp loginTime;

    public AdminLog( Long adminId, String loginIp, Timestamp loginTime) {
        this.adminId = adminId;
        this.loginIp = loginIp;
        this.loginTime = loginTime;
    }
    public AdminLog(){}
}
