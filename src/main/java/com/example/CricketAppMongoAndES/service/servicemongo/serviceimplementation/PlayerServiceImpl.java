package com.example.CricketAppMongoAndES.service.servicemongo.serviceimplementation;


import com.example.CricketAppMongoAndES.entities.Player;
import com.example.CricketAppMongoAndES.exceptionhandler.NotFoundException;
import com.example.CricketAppMongoAndES.repository.mongorepositories.repositoryImplmongodb.MatchRepositoryImpl;
import com.example.CricketAppMongoAndES.repository.mongorepositories.repositoryImplmongodb.PlayerRepositoryImpl;
import com.example.CricketAppMongoAndES.service.servicemongo.serviceinterfaces.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepositoryImpl playerRepository;

    @Autowired
    private MatchRepositoryImpl matchRepository;

    public PlayerServiceImpl(PlayerRepositoryImpl playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player getPlayerById(Long id) {
        Player player = playerRepository.findById(id).orElse(null);
        if (player == null) {
            throw new NotFoundException("Player with the given id does not exist");
        }
        return player;
    }

    @Override
    public Player findByName(String name) {
        Player player = playerRepository.findByName(name);
        if (player == null) {
            throw new NotFoundException("Player with the given name does not exist");
        }
        return player;
    }

    @Override
    public List<Player> getPlayersWithTeamName(String teamName) {
        return playerRepository.findByTeamName(teamName);
    }

    @Override
    public List<Player> getPlayersWithBaseAbility(String baseAbility) {
        return playerRepository.findByBaseAbility(baseAbility);
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
    public Player getOverAllMaxScorer() {
        return playerRepository.getOverallMaximumScorer();
    }

    @Override
    public Player getOverallMaxWicketTaker() {
        return playerRepository.getOverallMaxWicketTaker();
    }

    @Override
    public Player getMaxBoundariesHitter() {
        return playerRepository.getMaxBoundariesHitter();
    }

    @Override
    public Player getMaxSixesHitter() {
        return playerRepository.getMaxSixesHitter();
    }
}