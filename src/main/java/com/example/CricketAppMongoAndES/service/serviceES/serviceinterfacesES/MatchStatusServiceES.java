package com.example.CricketAppMongoAndES.service.serviceES.serviceinterfacesES;

import com.example.CricketAppMongoAndES.entities.MatchStatusRecord;
import org.springframework.stereotype.Service;

@Service
public interface MatchStatusServiceES {
    MatchStatusRecord save(MatchStatusRecord matchStatusRecord);
    MatchStatusRecord getMatchStatus(long matchId);


}