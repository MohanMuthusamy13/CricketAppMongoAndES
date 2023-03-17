package com.example.CricketAppMongoAndES.service.auxilaryservices.playerservice;

import com.example.CricketAppMongoAndES.configuration.Config;
import com.example.CricketAppMongoAndES.entities.Player;
import com.example.CricketAppMongoAndES.service.serviceES.serviceinterfacesES.PlayerServiceES;
import com.example.CricketAppMongoAndES.service.servicemongo.serviceinterfaces.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerStatsRecorder {

    @Autowired
    public PlayerService playerService;

    @Autowired
    public PlayerServiceES playerServiceES;

    public void savePlayerStat(List<Player> updatedPlayers) {
        for (Player player : updatedPlayers) {
            player.setMatchesPlayed(player.getMatchesPlayed() + 1);
            if (Config.useMongoDatabase) {
                playerService.updatePlayer(player.getId(), player);
            }
            else {
                playerServiceES.updatePlayer(player.getId(), player);
            }
        }
    }
    public void savePlayerStats(List players) {
        for (Object player : players) {
            savePlayerStat((List<Player>)player);
        }
    }
}