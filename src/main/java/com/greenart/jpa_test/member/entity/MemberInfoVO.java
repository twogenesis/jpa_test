package com.greenart.jpa_test.member.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "member_info") // 연결될 테이블 설정
@JsonIgnoreProperties(
    value={"pwd", "seq"}, allowSetters = true, allowGetters = false
)
public class MemberInfoVO {
    // mi_seq;mi_id;mi_pwd;mi_name;mi_nickname;mi_reg_dt;mi_status
    // @GeneratedValue(strategy = GenerationType.IDENTITY) -> MYSQL AUTO_INCREMENT
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("seq")
    @Column(name = "mi_seq") private Integer seq;
    @Column(name = "mi_id") private String id;
    @JsonProperty("pwd")
    @Column(name = "mi_pwd") private String pwd;
    @Column(name = "mi_name") private String name;
    @Column(name = "mi_nickname") private String nickname;
    @Column(name = "mi_reg_dt") private Date regDt;
    @Column(name = "mi_status") private Integer status;
}
