package com.example.CricketAppMongoAndES.service.auxilaryservices.majorgameservice;

import com.example.CricketAppMongoAndES.configuration.Config;
import com.example.CricketAppMongoAndES.entities.Team;
import com.example.CricketAppMongoAndES.service.serviceES.serviceinterfacesES.TeamServiceES;
import com.example.CricketAppMongoAndES.service.servicemongo.serviceimplementation.TeamServiceImpl;
import com.example.CricketAppMongoAndES.service.servicemongo.serviceinterfaces.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeamSelectorService {

    private TeamService teamService;

    @Autowired
    private TeamServiceES teamServiceES;

    @Autowired
    public void setTeamRepositoryService(TeamService teamRepositoryService) {
        this.teamService = teamRepositoryService;
    }

    public List<Team> teamSelector(long team1Id, long team2Id) throws IOException {
        List<Team> teams = new ArrayList<>();
        if (Config.useMongoDatabase) {
            teams.add(teamService.getTeamById(team1Id));
            teams.add(teamService.getTeamById(team2Id));
        }
        else {
            teams.add(teamServiceES.getTeamById(team1Id));
            teams.add(teamServiceES.getTeamById(team2Id));
        }

        return teams;
    }
}