package com.jujulioed.ATMProgressCheckerAPI.controller;

import com.jujulioed.ATMProgressCheckerAPI.DTOs.LeaderboardResponse;
import com.jujulioed.ATMProgressCheckerAPI.service.LeaderboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/leaderboards")
public class Leaderboards {

    private final LeaderboardService leaderboardService;

    public Leaderboards(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @GetMapping("/alive")
    public String healthCheck() {
        return "The API is alive";
    }

    @GetMapping
    public LeaderboardResponse leaderboards() {
        return leaderboardService.getLeaderboard();
    }
}
