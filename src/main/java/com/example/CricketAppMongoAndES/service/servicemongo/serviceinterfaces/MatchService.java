package com.example.CricketAppMongoAndES.service.servicemongo.serviceinterfaces;

import com.example.CricketAppMongoAndES.entities.Match;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchService {
    Match saveMatch(Match match);
    Match getMatchById(Long matchId) throws Exception;
    List<Match> getMatchesPlayedByTeamName(String teamName);
    int getMatchesCountPlayedByTeamName(String teamName);
    Match updateMatch(long matchId, Match match) throws Exception;
}