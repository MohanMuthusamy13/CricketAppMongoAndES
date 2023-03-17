package com.example.CricketAppMongoAndES.service.serviceES.serviceinterfacesES;

import com.example.CricketAppMongoAndES.entities.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerServiceES {
    Player getPlayerById(long id) throws Exception;
    Player savePlayer(Player player);
    Player updatePlayer(long id, Player player);
    List<Player> getPlayerByName(String name);
    List<Player> getPlayersByTeamAndBaseAbility(String teamName, String baseAbility);
    List<Player> getPlayersByTeamName(String teamName);

}