package com.example.CricketAppMongoAndES.utils.builders;

import com.example.CricketAppMongoAndES.entities.Player;
import com.example.CricketAppMongoAndES.entities.ScoreRecord;
import com.example.CricketAppMongoAndES.utils.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreRecordBuilder {

    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public ScoreRecordBuilder(SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    public ScoreRecord build(
            long matchId, String over, String ballOutcome, int innings,
            int totalRunsScoredAtThatInstance, int totalWicketsLoseAtThatInstance,
            Player Batsman, Player Bowler
    ) {
        return ScoreRecord.builder()
                .ballId(sequenceGeneratorService.getSequenceNumber(ScoreRecord.SEQUENCE_NAME))
                .matchId(matchId)
                .overCount(over)
                .ballOutcome(ballOutcome)
                .innings(innings)
                .totalRunsScoredByBattingTeam(totalRunsScoredAtThatInstance)
                .totalWicketsDown(totalWicketsLoseAtThatInstance)
                .batsman(Batsman)
                .bowler(Bowler)
                .build();
    }
}