package com.jujulioed.ATMProgressCheckerAPI.DTOs;

import java.time.LocalDateTime;
import java.util.List;

public record LeaderboardResponse(
        LocalDateTime generatedAt,
        List<LeaderboardPlayerResponse> players
) {
}
