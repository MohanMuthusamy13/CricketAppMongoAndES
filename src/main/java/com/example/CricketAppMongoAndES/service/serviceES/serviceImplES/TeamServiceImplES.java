package com.example.CricketAppMongoAndES.service.serviceES.serviceImplES;

import com.example.CricketAppMongoAndES.entities.Team;
import com.example.CricketAppMongoAndES.exceptionhandler.NotFoundException;
import com.example.CricketAppMongoAndES.repository.elasticrepositories.repositoriesImplelastic.TeamRepositoryESImpl;
import com.example.CricketAppMongoAndES.service.serviceES.serviceinterfacesES.TeamServiceES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImplES implements TeamServiceES {

    private final TeamRepositoryESImpl teamRepository;

    @Autowired
    public TeamServiceImplES(TeamRepositoryESImpl teamRepository) {
        this.teamRepository = teamRepository;
    }
    @Override
    public List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<>();
        Iterable<Team> teamIterable =  teamRepository.findAll();
        for (Team team : teamIterable) {
            teams.add(team);
        }
        System.out.println(teams);
        return teams;
    }

    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team getTeamById(long id) {
        Team team = teamRepository.findById(id).orElse(null);
        if (team == null){
            throw new NotFoundException("Please enter the valid ID");
        }
        return team;
    }

    public Team updateTeam(long id, Team updatedTeam) {
        Team team = teamRepository.findById(id).orElse(null);

        if (team == null) {
            throw new NotFoundException("Team does not exist with the respective id");
        }

        team.setTeamName(updatedTeam.getTeamName());
        team.setMatchesPlayed(updatedTeam.getMatchesPlayed());
        team.setPlayers(updatedTeam.getPlayers());

        return teamRepository.save(team);
    }

    @Override
    public void deleteTeam(long id) {
        teamRepository.deleteById(id);
    }
}