package com.jujulioed.ATMProgressCheckerAPI.model;

public class PlayerProgress {
    private String UUID;
    private String playerName;
    private int completedQuests;
    private int totalQuests;
    private double progressPercentage;
    private int rank;
    private String avatarUrl;

    public PlayerProgress() {}

    public PlayerProgress(String uuid, String playerName, int completedQuests, int totalQuests, double progressPercentage, int rank, String avatarUrl) {
        this.UUID = uuid;
        this.playerName = playerName;
        this.completedQuests = completedQuests;
        this.totalQuests = totalQuests;
        this.progressPercentage = progressPercentage;
        this.rank = rank;
        this.avatarUrl = avatarUrl;
    }

    public String getUUID() {
        return UUID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getCompletedQuests() {
        return completedQuests;
    }

    public int getTotalQuests() {
        return totalQuests;
    }

    public double getProgressPercentage() {
        return progressPercentage;
    }

    public int getRank() {
        return rank;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
