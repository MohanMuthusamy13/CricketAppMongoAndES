package com.example.CricketAppMongoAndES.service.servicemongo.serviceimplementation;

import com.example.CricketAppMongoAndES.entities.Team;
import com.example.CricketAppMongoAndES.exceptionhandler.NotFoundException;
import com.example.CricketAppMongoAndES.repository.mongorepositories.repositoryImplmongodb.TeamRepository;
import com.example.CricketAppMongoAndES.service.servicemongo.serviceinterfaces.TeamService;
import com.example.CricketAppMongoAndES.utils.builders.TeamBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    TeamRepository teamRepository;
    @Autowired
    TeamBuilder teamCreationService;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
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

    public Team updateTeam(Long id, Team updatedTeam) {
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
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}