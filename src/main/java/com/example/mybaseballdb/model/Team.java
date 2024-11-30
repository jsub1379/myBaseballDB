package com.example.mybaseballdb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Team {
    @Id
    private String teamName; // 팀 이름 (Primary Key)
    private String homeStadium; // 홈구장 이름
    private Integer foundingYear; // 창단년도
    private Integer championships; // 우승 횟수

    @ManyToOne
    @JoinColumn(name = "stadium_name")
    private Stadium stadium; // 야구장 정보와 연결
}
