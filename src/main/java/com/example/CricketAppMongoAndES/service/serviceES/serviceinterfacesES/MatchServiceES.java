package com.example.CricketAppMongoAndES.service.serviceES.serviceinterfacesES;

import com.example.CricketAppMongoAndES.entities.Match;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchServiceES {

    Match saveMatch(Match match);
    Match getMatchById(long matchId);
    Match updateMatch(long matchId, Match match);

    List<Match> getMatchesPlayedByTeamName(String teamName);
    List<Match> getMatchesByMatchFormat(String matchFormat);
    List<Match> getMatchesByTeamName(String teamName);

    long getMatchesCountPlayedByTeamName(String teamName);
}