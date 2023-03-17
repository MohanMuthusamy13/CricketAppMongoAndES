package com.example.CricketAppMongoAndES.controller.controllerelasticsearch;

import com.example.CricketAppMongoAndES.entities.Match;
import com.example.CricketAppMongoAndES.service.serviceES.serviceinterfacesES.MatchServiceES;
import com.example.CricketAppMongoAndES.utils.builders.MatchBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cricket-game-ES/match")
public class MatchControllerES {

    private final MatchServiceES matchService;

    @Autowired
    private MatchBuilder matchBuilder;

    @Autowired
    public MatchControllerES(MatchServiceES matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(
            @PathVariable(value = "id") Long id
    ) throws Exception {
        return new ResponseEntity<>(
                matchService.getMatchById(id),
                HttpStatus.OK);
    }

    @GetMapping("/matches-by-team-name")
    public ResponseEntity<List<Match>> getMatchesPlayedByTeamName(
            @RequestParam(value = "name") String teamName
    ) {
        return new ResponseEntity<>(
                matchService.getMatchesPlayedByTeamName(teamName),
                HttpStatus.OK);
    }

    @GetMapping("/matches-count-by-team-name")
    public ResponseEntity<Long> getMatchesCountPlayedByTeamName(
            @RequestParam(value = "name") String teamName
    ) {
        return new ResponseEntity<>(
                matchService.getMatchesCountPlayedByTeamName(teamName),
                HttpStatus.OK);
    }

    @PostMapping("/create-match")
    public ResponseEntity<Match> createMatch(
            @RequestParam(value = "id1") long teamId1,
            @RequestParam(value = "id2") long teamId2,
            @RequestParam(value = "format") String format
    ) throws IOException {

        Match match = matchBuilder.build(format, teamId1, teamId2);
        return new ResponseEntity<>(matchService.saveMatch(match), HttpStatus.CREATED);
    }

}