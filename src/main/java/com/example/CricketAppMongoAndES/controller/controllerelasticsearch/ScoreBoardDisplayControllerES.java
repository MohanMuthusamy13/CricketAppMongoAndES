package com.example.CricketAppMongoAndES.controller.controllerelasticsearch;

import com.example.CricketAppMongoAndES.entities.MatchStatusRecord;
import com.example.CricketAppMongoAndES.service.serviceES.serviceinterfacesES.MatchStatusServiceES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cricket-game-ES/display")
public class ScoreBoardDisplayControllerES {

    private final MatchStatusServiceES matchStatusService;

    @Autowired
    public ScoreBoardDisplayControllerES(MatchStatusServiceES matchStatusService) {
        this.matchStatusService = matchStatusService;
    }

    @GetMapping("/match-status-record/{id}")
    public ResponseEntity<MatchStatusRecord> getMatchStatusRecord(
            @PathVariable(value = "id") long matchId
    ) {
        return new ResponseEntity<>(
                matchStatusService.getMatchStatus(matchId),
                HttpStatus.OK
        );
    }
}