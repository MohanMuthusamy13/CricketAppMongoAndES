package com.example.CricketAppMongoAndES.utils.builders;

import com.example.CricketAppMongoAndES.configuration.Config;
import com.example.CricketAppMongoAndES.entities.Match;
import com.example.CricketAppMongoAndES.entities.Team;
import com.example.CricketAppMongoAndES.service.serviceES.serviceinterfacesES.TeamServiceES;
import com.example.CricketAppMongoAndES.service.servicemongo.serviceimplementation.TeamServiceImpl;
import com.example.CricketAppMongoAndES.service.servicemongo.serviceinterfaces.TeamService;
import com.example.CricketAppMongoAndES.utils.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MatchBuilder {

    private SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    private TeamService teamService;
    private TeamServiceES teamRepositoryService;

    @Autowired
    public void setSequenceGeneratorService(SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Autowired
    public void setTeamRepositoryService(TeamServiceES teamRepositoryService) {
        this.teamRepositoryService = teamRepositoryService;
    }

    public List<Team> teamsProvider(long teamId1, long teamId2) {
        if (Config.useMongoDatabase) {
            return List.of(
                    teamService.getTeamById(teamId1),
                    teamService.getTeamById(teamId2));
        }
        else {
            return List.of(
                    teamRepositoryService.getTeamById(teamId1),
                    teamRepositoryService.getTeamById(teamId2)
            );
        }
    }

    public Match build(String format, long teamId1, long teamId2) throws IOException {
        return Match.builder()
                .matchId(sequenceGeneratorService.getSequenceNumber(Match.SEQUENCE_NAME))
                .teamsPlayed(teamsProvider(teamId1, teamId2))
                .matchFormat(format)
                .build();
    }
}