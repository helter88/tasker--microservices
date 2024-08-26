package com.example.queue_manager.service.dto;

public record PlayerDto(
        int id,
        String playerName,
        String position,
        int age,
        int games,
        int minutesPlayed,
        double per,
        double tsPercent,
        double threePAR,
        double ftr,
        double offensiveRBPercent,
        double defensiveRBPercent,
        double totalRBPercent,
        double assistPercent,
        double stealPercent,
        double blockPercent,
        double turnoverPercent,
        double usagePercent,
        double offensiveWS,
        double defensiveWS,
        double winShares,
        double winSharesPer,
        double offensiveBox,
        double defensiveBox,
        double box,
        double vorp,
        String team,
        int season
) {
}
