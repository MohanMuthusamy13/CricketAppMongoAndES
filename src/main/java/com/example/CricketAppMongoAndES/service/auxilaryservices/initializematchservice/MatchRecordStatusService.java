package com.example.CricketAppMongoAndES.service.auxilaryservices.initializematchservice;

import com.example.CricketAppMongoAndES.configuration.Config;
import com.example.CricketAppMongoAndES.service.auxilaryservices.majorgameservice.GameService;
import com.example.CricketAppMongoAndES.service.auxilaryservices.runtrackerservice.ScoreService;
import com.example.CricketAppMongoAndES.entities.MatchStatusRecord;
import com.example.CricketAppMongoAndES.service.serviceES.serviceinterfacesES.MatchStatusServiceES;
import com.example.CricketAppMongoAndES.service.servicemongo.serviceinterfaces.MatchStatusService;
import com.example.CricketAppMongoAndES.utils.SequenceGeneratorService;
import com.example.CricketAppMongoAndES.view.ScoreBoardDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MatchRecordStatusService {

    @Autowired
    MatchStatusService matchStatusService;

    @Autowired
    MatchStatusServiceES matchStatusServiceES;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    ScoreBoardDisplay scoreBoardDisplay;

    public void saveMatchStatusRecord(long matchId) {
        Map<Long, Integer> scoreUtilityMap = new HashMap<>() {{
            put(GameService.getTeams().get(0).getTeamId(), ScoreService.getScoreOfBothTeams()[0]);
            put(GameService.getTeams().get(1).getTeamId(), ScoreService.getScoreOfBothTeams()[1]);
        }};

        MatchStatusRecord matchStatusRecord = MatchStatusRecord.builder()
                .matchStatusRecordId(sequenceGeneratorService.getSequenceNumber(MatchStatusRecord.SEQUENCE_NAME))
                .matchId(matchId)
                .matchStatus(scoreBoardDisplay.getResults())
                .scoreOfBothTeams(scoreUtilityMap)
                .build();

        if (Config.useMongoDatabase) {
            matchStatusService.save(matchStatusRecord);
        }
        else {
            matchStatusServiceES.save(matchStatusRecord);
        }
    }
}