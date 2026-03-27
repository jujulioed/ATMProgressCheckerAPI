package com.jujulioed.ATMProgressCheckerAPI.service;

import com.jujulioed.ATMProgressCheckerAPI.model.PlayerProgress;
import com.jujulioed.ATMProgressCheckerAPI.serverInteraction.PlayerQuestBook;
import com.jujulioed.ATMProgressCheckerAPI.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


@Service
public class QuestProgressService {

    @Value("${players.data.path}")
    private String playersDataPath;

    @Value("${all-quests-path}")
    private String allQuestsPath;

    public List<PlayerProgress> loadAllPlayersProgress() {
        ArrayList<Path> playersFiles = Utils.getAllFilesInPath(playersDataPath);

        ArrayList<PlayerProgress> playerProgresses = new ArrayList<>();
        for(Path p : playersFiles) {
            PlayerQuestBook pp = new PlayerQuestBook(p.toString(), allQuestsPath);
            playerProgresses.add(pp.getPlayerStats());
        }

        return playerProgresses;
    }
}
