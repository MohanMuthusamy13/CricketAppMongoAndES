package com.example.CricketAppMongoAndES.service.servicemongo.serviceinterfaces;

import com.example.CricketAppMongoAndES.entities.ScoreRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreRecorderService {
    ScoreRecord saveScoreRecordPerBall(ScoreRecord scoreRecorder);
    List<ScoreRecord> getStatsOnParticularBall(long matchId, String over);
    List<ScoreRecord> getStatsOnParticularBallAndInnings(long matchId, String over, int innings);

}