package com.example.queue_manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    private UUID uuid = UUID.randomUUID();
    private int id;
    private String playerName;
    private String position;
    private int age;
    private int games;
    private int minutesPlayed;
    private double per;
    private double tsPercent;
    private double threePAR;
    private double ftr;
    private double offensiveRBPercent;
    private double defensiveRBPercent;
    private double totalRBPercent;
    private double assistPercent;
    private double stealPercent;
    private double blockPercent;
    private double turnoverPercent;
    private double usagePercent;
    private double offensiveWS;
    private double defensiveWS;
    private double winShares;
    private double winSharesPer;
    private double offensiveBox;
    private double defensiveBox;
    private double box;
    private double vorp;
    private String team;
    private int season;
}
