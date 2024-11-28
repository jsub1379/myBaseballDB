package com.example.mybaseballdb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Pitcher {
    @Id
    private String playerName;
    private String team;
    private Integer gamesPlayed;
    private Float inningsPitched;
    private Integer wins;
    private Integer losses;
    private Integer saves;
    private Integer holds;
    private Integer hitsAllowed;
    private Integer homeRunsAllowed;
    private Integer walksAllowed;
    private Integer hitByPitch;
    private Integer strikeouts;
    private Integer runsAllowed;
    private Integer earnedRuns;
    private Float earnedRunAverage;
    private Float whip;
    private Float kPerBb;
}
