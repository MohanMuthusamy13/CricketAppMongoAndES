package com.example.CricketAppMongoAndES.repository.elasticrepositories.repositoryelastic;

import com.example.CricketAppMongoAndES.entities.Match;

import java.util.List;

public interface MatchRepositoryES {
    List<Match> findByMatchFormat(String matchFormat);
    List<Match> getMatchesByTeamName(String teamName);
    List<Match> getMatchesPlayedByTeamName(String teamName);
    long getMatchesCountPlayedByTeamName(String teamName);

}