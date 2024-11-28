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
    private int gamesPlayed;
    private int plateAppearances;
    private int hits;
    private int doubles;
    private int triples;
    private int homeRuns;
    private int runsBattedIn;
    private int stolenBases;
    private int caughtStealing;
    private int walks;
    private int hitByPitch;
    private int strikeouts;
    private int errors;
    private Float battingAverage;
    private Float onBasePercentage;
    private Float sluggingPercentage;
    private Float ops;
}
