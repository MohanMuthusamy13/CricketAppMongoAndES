package com.example.CricketAppMongoAndES.service.serviceES.serviceImplES;

import com.example.CricketAppMongoAndES.entities.Player;
import com.example.CricketAppMongoAndES.exceptionhandler.NotFoundException;
import com.example.CricketAppMongoAndES.repository.elasticrepositories.repositoriesImplelastic.PlayerRepositoryESImpl;
import com.example.CricketAppMongoAndES.service.serviceES.serviceinterfacesES.PlayerServiceES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImplES implements PlayerServiceES {

    private final PlayerRepositoryESImpl playerRepository;

    @Autowired
    public PlayerServiceImplES(PlayerRepositoryESImpl playerRepository) {
        this.playerRepository = playerRepository;
    }
    @Override
    public Player getPlayerById(long id) throws Exception {
        Player player = playerRepository.findById(id).orElse(null);
        if (player == null) {
            throw new NotFoundException("Player with the given id does not exist");
        }
        return player;
    }

    @Override
    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player updatePlayer(long id, Player player) {
        Player tempPlayer = playerRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Player with the id is not found")
                );

        tempPlayer.setScore(player.getScore());
        tempPlayer.setBallsFaced(player.getBallsFaced());
        tempPlayer.setBallsBowled(player.getBallsBowled());
        tempPlayer.setWicketsTaken(player.getWicketsTaken());
        tempPlayer.setMatchesPlayed(player.getMatchesPlayed());
        tempPlayer.setNoOfFours(player.getNoOfFours());
        tempPlayer.setNoOfSixes(player.getNoOfSixes());
        tempPlayer.setHalfCenturies(player.getHalfCenturies());
        tempPlayer.setCenturies(player.getCenturies());
        tempPlayer.setActiveStatus(player.getActiveStatus());

        playerRepository.save(tempPlayer);
        return tempPlayer;
    }

    @Override
    public List<Player> getPlayersByTeamAndBaseAbility(String teamName, String baseAbility) {
        return playerRepository.getPlayersByTeamAndBaseAbility(teamName, baseAbility);
    }

    @Override
    public List<Player> getPlayerByName(String name) {
        return playerRepository.getPlayerByName(name);
    }

    @Override
    public List<Player> getPlayersByTeamName(String teamName) {
        return playerRepository.getPlayersByTeamName(teamName);
    }
}