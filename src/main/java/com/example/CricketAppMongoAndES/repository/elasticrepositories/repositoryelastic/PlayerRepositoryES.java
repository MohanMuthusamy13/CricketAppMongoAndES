package com.example.CricketAppMongoAndES.repository.elasticrepositories.repositoryelastic;

import com.example.CricketAppMongoAndES.entities.Player;
import org.springframework.data.elasticsearch.annotations.Query;

import java.util.List;

public interface PlayerRepositoryES {
    List<Player> getPlayerByName(String name);
    List<Player> getPlayersByTeamAndBaseAbility(String teamName, String baseAbility);
    List<Player> getPlayersByTeamName(String teamName);
}