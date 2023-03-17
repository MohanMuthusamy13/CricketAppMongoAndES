package com.example.CricketAppMongoAndES.controller.controllermongodb;

import com.example.CricketAppMongoAndES.service.auxilaryservices.initializematchservice.MatchFormatService;
import com.example.CricketAppMongoAndES.service.auxilaryservices.majorgameservice.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cricket-game")

public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/start-match")
    public ResponseEntity<String> startMatch(
            @RequestParam(value = "id") long matchId,
            @RequestParam(value = "format") String matchFormat
    ) throws Exception {
        MatchFormatService.matchFormatScheduler(matchFormat);
        gameService.startGame(matchId);
        return new ResponseEntity<>("Match is completed", HttpStatus.OK);
    }

}