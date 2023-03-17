package com.example.CricketAppMongoAndES.service.servicemongo.serviceinterfaces;

import com.example.CricketAppMongoAndES.entities.Team;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamService {

    List<Team> getAllTeams();
    Team saveTeam(Team team);
    Team getTeamById(long id);
    Team updateTeam(Long id, Team team);
    void deleteTeam(Long id);
}