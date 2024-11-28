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
    private int gamesPlayed;
    private float inningsPitched;
    private int wins;
    private int losses;
    private int saves;
    private int holds;
    private int hitsAllowed;
    private int homeRunsAllowed;
    private int walksAllowed;
    private int hitByPitch;
    private int strikeouts;
    private int runsAllowed;
    private int earnedRuns;
    private Float earnedRunAverage;
    private Float whip;
    private Float kPerBb;
}
