package com.example.CricketAppMongoAndES.repository.elasticrepositories.repositoryelastic;

import com.example.CricketAppMongoAndES.entities.MatchStatusRecord;

public interface MatchStatusRepositoryES {
    MatchStatusRecord getMatchStatus(long matchId);
}