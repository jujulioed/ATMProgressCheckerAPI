package com.jujulioed.ATMProgressCheckerAPI.DTOs;

public record LeaderboardPlayerResponse(
        String uuid,
        String playerName,
        int completedQuests,
        int totalQuests,
        double progressPercentage,
        int rank,
        String avatarUrl
) {
}
