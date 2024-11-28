package com.example.mybaseballdb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Batter {
    @Id
    private String playerName;
    private String team;
    private Integer gamesPlayed;
    private Integer atBats;
    private Integer hits;
    private Integer doubles;
    private Integer triples;
    private Integer homeRuns;
    private Integer runsBattedIn;
    private Integer stolenBases;
    private Integer caughtStealing;
    private Integer walks;
    private Integer hitByPitch;
    private Integer strikeouts;
    private Integer errors;
    private Integer sacrificeBunts;
    private Integer sacrificeFlies;
    private Float battingAverage;
    private Float onBasePercentage;
    private Float sluggingPercentage;
    private Float ops;
}
