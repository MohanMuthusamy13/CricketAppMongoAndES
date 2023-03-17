package com.example.CricketAppMongoAndES.service.serviceES.serviceImplES;

import com.example.CricketAppMongoAndES.entities.Match;
import com.example.CricketAppMongoAndES.exceptionhandler.NotFoundException;
import com.example.CricketAppMongoAndES.repository.elasticrepositories.repositoriesImplelastic.MatchRepositoryESImpl;
import com.example.CricketAppMongoAndES.service.serviceES.serviceinterfacesES.MatchServiceES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImplES implements MatchServiceES {

    private final MatchRepositoryESImpl matchRepository;

    @Autowired
    public MatchServiceImplES(MatchRepositoryESImpl matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    @Override
    public Match getMatchById(long matchId) {
        return matchRepository
                .findById(matchId)
                .orElseThrow(
                        () -> new NotFoundException("MatchId is not found")
                );
    }

    @Override
    public Match updateMatch(long matchId, Match updatedMatch) {
        Match match = matchRepository.findById(matchId).orElseThrow(
                () -> new NotFoundException("Match Id is not found")
        );

        match.setMatchFormat(updatedMatch.getMatchFormat());
        match.setTeamsPlayed(updatedMatch.getTeamsPlayed());
        match.setMatchStatus(updatedMatch.getMatchStatus());

        matchRepository.save(match);

        return match;
    }

    @Override
    public List<Match> getMatchesByMatchFormat(String matchFormat) {
        return matchRepository.findByMatchFormat(matchFormat);
    }

    @Override
    public List<Match> getMatchesByTeamName(String teamName) {
        return matchRepository.getMatchesByTeamName(teamName);
    }

    @Override
    public List<Match> getMatchesPlayedByTeamName(String teamName) {
        return matchRepository.getMatchesPlayedByTeamName(teamName);
    }

    @Override
    public long getMatchesCountPlayedByTeamName(String teamName) {
        return matchRepository.getMatchesCountPlayedByTeamName(teamName);
    }
}