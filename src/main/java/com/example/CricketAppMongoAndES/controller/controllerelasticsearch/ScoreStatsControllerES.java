package com.example.CricketAppMongoAndES.controller.controllerelasticsearch;

import com.example.CricketAppMongoAndES.entities.ScoreRecord;
import com.example.CricketAppMongoAndES.service.serviceES.serviceinterfacesES.ScoreRecorderServiceES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cricket-game-ES/score-stats")
public class ScoreStatsControllerES {

    private final ScoreRecorderServiceES scoreRecorderService;

    @Autowired
    public ScoreStatsControllerES(ScoreRecorderServiceES scoreRecorderService) {
        this.scoreRecorderService = scoreRecorderService;
    }

    @GetMapping("/compare-stats/{id}")
    public ResponseEntity<List<ScoreRecord>> getScoreStatOnParticularBall(
            @PathVariable(value = "id") long matchId,
            @RequestParam(value = "over") String over
    ) {
        return new ResponseEntity<>(
                scoreRecorderService.getStatsOnParticularBall(matchId, over),
                HttpStatus.OK
        );
    }

    @GetMapping("/stats-particular-ball/{id}")
    public ResponseEntity<ScoreRecord> getStatsOnParticularBallAndInnings(
            @PathVariable(value = "id") long matchId,
            @RequestParam(value = "over") String over,
            @RequestParam(value = "innings") int innings
    ) {
        return new ResponseEntity<>(
                scoreRecorderService
                        .getBallOutcome(matchId, over, innings),
                HttpStatus.OK
        );
    }
}