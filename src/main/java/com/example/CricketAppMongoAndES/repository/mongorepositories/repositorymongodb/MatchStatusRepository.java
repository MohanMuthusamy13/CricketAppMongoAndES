package com.example.CricketAppMongoAndES.repository.mongorepositories.repositorymongodb;

import com.example.CricketAppMongoAndES.entities.MatchStatusRecord;

import java.util.Optional;

public interface MatchStatusRepository {
    Optional<MatchStatusRecord> getMatchRecordByMatchId(long matchId);
    Optional<String> getMatchStatusByMatchId(long matchId);
}