package com.example.CricketAppMongoAndES.service.servicemongo.serviceinterfaces;

import com.example.CricketAppMongoAndES.entities.MatchStatusRecord;
import org.springframework.stereotype.Service;

@Service
public interface MatchStatusService {
    MatchStatusRecord save(MatchStatusRecord matchStatusRecord);
    MatchStatusRecord getMatchRecordByMatchId(long matchId);
    String getMatchStatusByMatchId(long matchId);
}