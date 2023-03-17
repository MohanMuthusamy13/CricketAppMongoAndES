package com.example.CricketAppMongoAndES.utils.builders;

import com.example.CricketAppMongoAndES.entities.Team;
import com.example.CricketAppMongoAndES.repository.mongorepositories.repositoryImplmongodb.TeamRepository;
import com.example.CricketAppMongoAndES.service.auxilaryservices.playerservice.PlayersService;
import com.example.CricketAppMongoAndES.utils.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamBuilder {

    private SequenceGeneratorService sequenceGeneratorService;
    private PlayersService playersTeamService;
    private TeamRepository teamRepository;

    @Autowired
    public void setSequenceGeneratorService(SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Autowired
    public void setPlayersTeamService(PlayersService playersTeamService) {
        this.playersTeamService = playersTeamService;
    }

    @Autowired
    public void setTeamRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team build(String teamName) {
        return Team.builder()
                .teamId(sequenceGeneratorService.getSequenceNumber(Team.SEQUENCE_NAME))
                .teamName(teamName).players(playersTeamService.getPlayers(teamName))
                .matchesPlayed(1).build();
    }
}