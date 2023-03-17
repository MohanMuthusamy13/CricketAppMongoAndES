package com.example.CricketAppMongoAndES.service.auxilaryservices.runtrackerservice;

import com.example.CricketAppMongoAndES.entities.Player;
import com.example.CricketAppMongoAndES.service.auxilaryservices.playerservice.PlayersService;

import java.util.List;

public class PlayerCenturyAndHalfCenturyService {

    public static void saveStatsOfCenturies(Player player) {
        PlayersService.checkForCenturiesAndHalfCenturies(player);
    }

    public static void playerStat(List<Player> players) {
        for (Player player : players) {
            saveStatsOfCenturies(player);
        }
    }

    public static void centuryStatsProvider(List playerTeams) {
        for (Object players : playerTeams) {
            playerStat((List<Player>) players);
        }
    }
}