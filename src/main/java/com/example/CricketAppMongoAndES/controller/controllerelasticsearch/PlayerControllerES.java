package com.example.CricketAppMongoAndES.controller.controllerelasticsearch;

import com.example.CricketAppMongoAndES.entities.Player;
import com.example.CricketAppMongoAndES.service.serviceES.serviceinterfacesES.PlayerServiceES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/cricket-game-ES/player")
public class PlayerControllerES {

    private final PlayerServiceES playerService;

    @Autowired
    public PlayerControllerES(PlayerServiceES playerRepositoryService) {
        this.playerService = playerRepositoryService;
    }

    @GetMapping("/player-by-id")
    public ResponseEntity<Player> getPlayerById(
            @RequestParam(value = "id") Long id
    ) throws Exception {
        return new ResponseEntity<>(
                playerService.getPlayerById(id), HttpStatus.OK
        );
    }

    @GetMapping("/player-by-name")
    public ResponseEntity<List<Player>> getPlayerByName(
            @RequestParam(value = "name") String name
    ) throws Exception {
        return new ResponseEntity<>(
                playerService.getPlayerByName(name), HttpStatus.OK
        );
    }

    @GetMapping("/players-by-team-name")
    public ResponseEntity<List<Player>> getPlayersWithTeamName(
            @RequestParam(value = "name") String teamName
    ) {
        return new ResponseEntity<>(
                playerService.getPlayersByTeamName(teamName), HttpStatus.OK
        );
    }

    @GetMapping("/players-with-base-ability")
    public ResponseEntity<List<Player>> getPlayersWithBaseAbility(
            @RequestParam(value = "teamName") String teamName,
            @RequestParam(value = "ability") String baseAbility
    ) {
        return new ResponseEntity<>(
                playerService.getPlayersByTeamAndBaseAbility(teamName, baseAbility),
                HttpStatus.OK
        );
    }
}