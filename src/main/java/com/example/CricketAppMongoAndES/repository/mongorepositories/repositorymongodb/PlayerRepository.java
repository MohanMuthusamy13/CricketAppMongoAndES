package com.example.CricketAppMongoAndES.repository.mongorepositories.repositorymongodb;

import com.example.CricketAppMongoAndES.entities.Player;

import java.util.List;

public interface PlayerRepository {
    Player getOverallMaximumScorer();
    Player getOverallMaxWicketTaker();
    Player getMaxBoundariesHitter();
    Player getMaxSixesHitter();
    List<Player> findByTeamName(String teamName);
    List<Player> findByBaseAbility(String baseAbility);
    Player findByName(String name);
}