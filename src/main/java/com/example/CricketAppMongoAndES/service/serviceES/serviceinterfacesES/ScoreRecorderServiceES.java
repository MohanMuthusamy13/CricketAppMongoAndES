package com.example.CricketAppMongoAndES.service.serviceES.serviceinterfacesES;

import com.example.CricketAppMongoAndES.entities.ScoreRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreRecorderServiceES {
    ScoreRecord saveScoreRecordPerBall(ScoreRecord scoreRecorder);
    ScoreRecord getBallOutcome(long matchId, String overCount, int innings);
    List<ScoreRecord> getStatsOnParticularBall(long matchId, String over);
}