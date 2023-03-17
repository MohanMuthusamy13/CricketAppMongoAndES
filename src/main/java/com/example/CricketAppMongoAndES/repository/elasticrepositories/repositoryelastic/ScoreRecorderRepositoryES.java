package com.example.CricketAppMongoAndES.repository.elasticrepositories.repositoryelastic;

import com.example.CricketAppMongoAndES.entities.ScoreRecord;

import java.util.List;

public interface ScoreRecorderRepositoryES {
    ScoreRecord getBallOutcome(long matchId, String overCount, int innings);
    List<ScoreRecord> getStatsOnParticularBallOnBothInnings(long matchId, String overCount);
}