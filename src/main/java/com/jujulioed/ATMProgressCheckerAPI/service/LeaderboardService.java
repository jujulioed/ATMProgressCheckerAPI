package com.jujulioed.ATMProgressCheckerAPI.service;

import com.jujulioed.ATMProgressCheckerAPI.DTOs.LeaderboardPlayerResponse;
import com.jujulioed.ATMProgressCheckerAPI.DTOs.LeaderboardResponse;
import com.jujulioed.ATMProgressCheckerAPI.model.PlayerProgress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class LeaderboardService {

    private final QuestProgressService questProgressService;

    @Value("${app.base-url}")
    private String baseUrl;

    public LeaderboardService(QuestProgressService questProgressService) {
        this.questProgressService = questProgressService;
    }

    public LeaderboardResponse getLeaderboard() {
        List<PlayerProgress> players = questProgressService.loadAllPlayersProgress();

        // Entender melhor essa sugestão de organização
        List<PlayerProgress> sorted = players.stream()
                .sorted(Comparator
                        .comparingDouble(PlayerProgress::getProgressPercentage).reversed()
                        .thenComparing(PlayerProgress::getCompletedQuests, Comparator.reverseOrder())
                        .thenComparing(PlayerProgress::getPlayerName))
                .toList();
        List<LeaderboardPlayerResponse> response = IntStream.range(0, sorted.size())
                .mapToObj(i -> {
                    PlayerProgress p = sorted.get(i);
                    int rank = i + 1;
                    String avatarUrl = baseUrl + "/avatars/" + p.getUUID() + ".png";

                    return new LeaderboardPlayerResponse(
                            p.getUUID(),
                            p.getPlayerName(),
                            p.getCompletedQuests(),
                            p.getTotalQuests(),
                            p.getProgressPercentage(),
                            rank,
                            avatarUrl
                    );
                })
                .toList();

        return new LeaderboardResponse(LocalDateTime.now(), response);
    }
}
