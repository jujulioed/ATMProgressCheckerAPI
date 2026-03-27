package com.jujulioed.ATMProgressCheckerAPI.serverInteraction;

import com.jujulioed.ATMProgressCheckerAPI.model.PlayerProgress;
import com.jujulioed.ATMProgressCheckerAPI.utils.Utils;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;

public class PlayerQuestBook {
    private String BEGIN_PATTERN = ":";

    private String allQuestsPath;

    private String UUID;
    private String playerName;
    private int completedQuests;
    private int totalQuests;
    private double progressPercentage;

    public static ArrayList<String> allTheQuests;
    public ArrayList<String> finished;


    public PlayerQuestBook(String playerQuestPath, String allQuestsPathInput) {
        allQuestsPath = allQuestsPathInput;

        if (allTheQuests == null) {
            String data = Utils.getFileData(allQuestsPath);
            allTheQuests = getQuestIds(data);
            totalQuests = allTheQuests.size();
        }

        getPlayerUuid(playerQuestPath);
        getPlayerName(playerQuestPath);
        updateFinishedQuests(playerQuestPath);
    }


    
    //        this.UUID = uuid;
    //        this.playerName = playerName;
    //        this.completedQuests = completedQuests;
    //        this.totalQuests = totalQuests;
    //        this.progressPercentage = progressPercentage;
    public PlayerProgress getPlayerStats() {
        PlayerProgress pp = new PlayerProgress(this.UUID, this.playerName, this.completedQuests, this.totalQuests, this.progressPercentage);
        return pp;
    }
    

    // Retorna todos os IDs de todas as quests
    private ArrayList<String> getQuestIds(String data) {
        String[] allData = data.split("\\s+");
        ArrayList<String> allQuests = new ArrayList<>();

        for(String i : allData) {
            if(i.contains("quest.") && i.contains(".title")) {
                allQuests.add(i.replace("quest.", "").replace(".title:", ""));
            }
        }
        return allQuests;
    }

    private void updateFinishedQuests(String playerDataPath) {
        String playerData = Utils.getFileData(playerDataPath);
        this.finished = extractData("completed", playerData);
        this.completedQuests = finished.size();
        this.totalQuests = allTheQuests.size();
        this.progressPercentage = ((float) this.finished.size() /allTheQuests.size()) * 100;
    }

    private void getPlayerName(String playerDataPath) {
        String playerDate = Utils.getFileData(playerDataPath);
        this.playerName = extractPlayerName(playerDate);
    }

    private String extractPlayerName(String playerData) {
        // Separa por espaços em branco
        String[] allData = playerData.split("\\s+");

        boolean take = false;
        String tag = "name:";
        for (String i : allData) {
            if (take) {
                return i.replaceAll("#.*", "").replaceAll("^\"|\"$", "");
            }

            if (i.equals(tag)) {
                take = true;
            }
        }

        return null;
    }

    private void getPlayerUuid(String playerDataPath) {
        String playerData = Utils.getFileData(playerDataPath);
        this.UUID = extractUUID(playerData);
    }

    private String extractUUID(String playerData) {
        // Separa por espaços em branco
        String[] allData = playerData.split("\\s+");

        boolean take = false;
        String tag = "uuid:";
        for (String i : allData) {
            if(take) {
                return i.replace("\"", "");
            }

            // Se estou na string tag, a próximo string é a desejado
            if(i.equals(tag)) {
                take = true;
            }
        }

        return null;
    }


    private ArrayList<String> extractData(String element, String data) {
        ArrayList<String> quests = new ArrayList<>();
        element = element + BEGIN_PATTERN;

        // Separa a string pelos espaços em branco
        String[] elements = data.split("\\s+");

        boolean take = false;
        for (String i : elements) {
            if(i.equals("}")) {
                take = false;
            }

            if(take && !i.contains("{") && !i.contains("L") ) {
                // Usando replace para remover o ":" do fim da string
                quests.add(i.replace(":", ""));
            }

            if(i.equals(element)) {
                take = true;
            }
        }
        return quests;
    }

    public String getProgressPercentage() {
        return this.progressPercentage + "%";
    }

    public int getCompletedQuests() {
        return this.completedQuests;
    }
}
