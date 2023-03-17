package com.example.CricketAppMongoAndES.repository.mongorepositories.repositorymongodb;

import com.example.CricketAppMongoAndES.entities.Match;

import java.util.List;

public interface MatchRepository {
    List<Match> getMatchesPlayedByTeamName(String teamName);
    int getMatchesCountPlayedByTeamName(String teamName);

}