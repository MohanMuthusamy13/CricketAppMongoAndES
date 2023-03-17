package com.example.CricketAppMongoAndES.repository.mongorepositories.repositorymongodb;

import com.example.CricketAppMongoAndES.entities.ScoreRecord;

import java.util.List;

public interface ScoreRecorderRepository {
    List<ScoreRecord> getStatsOnParticularBall(long matchId, String over);
    List<ScoreRecord> getStatsOnParticularBallAndInnings(long matchId,
                                                         String over, int innings);
}