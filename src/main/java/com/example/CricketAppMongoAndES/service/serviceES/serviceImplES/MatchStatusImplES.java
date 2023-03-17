package com.example.CricketAppMongoAndES.service.serviceES.serviceImplES;

import com.example.CricketAppMongoAndES.entities.MatchStatusRecord;
import com.example.CricketAppMongoAndES.repository.elasticrepositories.repositoriesImplelastic.MatchStatusRepositoryESImpl;
import com.example.CricketAppMongoAndES.service.serviceES.serviceinterfacesES.MatchStatusServiceES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchStatusImplES implements MatchStatusServiceES {

    private final MatchStatusRepositoryESImpl matchStatusRepository;

    @Autowired
    public MatchStatusImplES(MatchStatusRepositoryESImpl matchStatusRepository) {
        this.matchStatusRepository = matchStatusRepository;
    }

    @Override
    public MatchStatusRecord save(MatchStatusRecord matchStatusRecord) {
        return matchStatusRepository.save(matchStatusRecord);
    }

    @Override
    public MatchStatusRecord getMatchStatus(long matchId) {
        return matchStatusRepository.getMatchStatus(matchId);
    }
}