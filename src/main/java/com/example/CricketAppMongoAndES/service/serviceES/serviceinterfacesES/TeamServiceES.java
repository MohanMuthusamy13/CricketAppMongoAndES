package com.example.CricketAppMongoAndES.service.serviceES.serviceinterfacesES;

import com.example.CricketAppMongoAndES.entities.Team;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamServiceES {
    List<Team> getAllTeams();
    Team saveTeam(Team team);
    Team getTeamById(long id);
    Team updateTeam(long id, Team team);
    void deleteTeam(long id);
}