package com.example.CricketAppMongoAndES.service.servicemongo.serviceinterfaces;


import com.example.CricketAppMongoAndES.entities.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerService {
    Player getPlayerById(Long id) throws Exception;
    List<Player> getPlayersWithTeamName(String teamName);
    List<Player> getPlayersWithBaseAbility(String baseAbility);
    Player findByName(String name) throws Exception;
    Player savePlayer(Player player);
    Player updatePlayer(long id, Player player);
    Player getOverAllMaxScorer();
    Player getOverallMaxWicketTaker();
    Player getMaxBoundariesHitter();
    Player getMaxSixesHitter();
}