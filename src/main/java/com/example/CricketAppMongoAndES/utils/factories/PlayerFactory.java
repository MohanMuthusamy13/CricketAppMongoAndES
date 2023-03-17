package com.example.CricketAppMongoAndES.utils.factories;

import com.example.CricketAppMongoAndES.configuration.Config;
import com.example.CricketAppMongoAndES.entities.Player;
import com.example.CricketAppMongoAndES.entities.Team;
import com.example.CricketAppMongoAndES.service.serviceES.serviceinterfacesES.PlayerServiceES;
import com.example.CricketAppMongoAndES.service.servicemongo.serviceinterfaces.PlayerService;
import com.example.CricketAppMongoAndES.utils.Constants;
import com.example.CricketAppMongoAndES.utils.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerFactory {

    private final SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private PlayerServiceES playerServiceES;
    @Autowired
    public PlayerFactory(SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    public Player createBatsmanWithInitialConditions(String playerName, String teamName, Team team) {
        Player player = new Player(
                sequenceGeneratorService.getSequenceNumber(Player.SEQUENCE_NAME),
                playerName,
                Constants.INITIAL_SCORE,
                Constants.INITIAL_BALL_FACED_COUNT,
                Constants.INITIAL_BALL_COUNT,
                Constants.INITIAL_WICKETS_TAKEN,
                "Batsman",
                Constants.INITIAL_MATCHES_PLAYED_COUNT,
                Constants.INITIAL_FOURS_COUNT,
                Constants.INITIAL_SIXES_COUNT,
                Constants.INITIAL_HALF_CENTURIES_COUNT,
                Constants.INITIAL_CENTURIES_COUNT,
                "inactive", teamName);
        if (Config.useMongoDatabase) {
            playerService.savePlayer(player);
        }
        else {
            playerServiceES.savePlayer(player);
        }
        return player;
    }

    public Player createBowlerWithInitialConditions(String playerName, String teamName, Team team) {
        Player player = new Player(
                sequenceGeneratorService.getSequenceNumber(Player.SEQUENCE_NAME),
                playerName,
                Constants.INITIAL_SCORE,
                Constants.INITIAL_BALL_FACED_COUNT,
                Constants.INITIAL_BALL_COUNT,
                Constants.INITIAL_WICKETS_TAKEN,
                "Bowler",
                Constants.INITIAL_MATCHES_PLAYED_COUNT,
                Constants.INITIAL_FOURS_COUNT,
                Constants.INITIAL_SIXES_COUNT,
                Constants.INITIAL_HALF_CENTURIES_COUNT ,
                Constants.INITIAL_CENTURIES_COUNT,
                "inactive",teamName);
        if (Config.useMongoDatabase) {
            playerService.savePlayer(player);
        }
        else {
            playerServiceES.savePlayer(player);
        }
        return player;
    }

    public Player createPlayer(String name, String baseAbility,String teamName, Team team) {
        if (baseAbility.equals("Batsman")) {
            return createBatsmanWithInitialConditions(name, teamName,  team);
        }
        else {
            return createBowlerWithInitialConditions(name, teamName, team);
        }
    }
}