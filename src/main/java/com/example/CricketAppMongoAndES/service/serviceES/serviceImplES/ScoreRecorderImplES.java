package com.example.CricketAppMongoAndES.service.serviceES.serviceImplES;

import com.example.CricketAppMongoAndES.entities.ScoreRecord;
import com.example.CricketAppMongoAndES.repository.elasticrepositories.repositoriesImplelastic.ScoreRecorderRepositoryESImpl;
import com.example.CricketAppMongoAndES.service.serviceES.serviceinterfacesES.ScoreRecorderServiceES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreRecorderImplES implements ScoreRecorderServiceES {

    private final ScoreRecorderRepositoryESImpl scoreRecorderRepository;

    @Autowired
    public ScoreRecorderImplES(ScoreRecorderRepositoryESImpl scoreRecorderRepository) {
        this.scoreRecorderRepository = scoreRecorderRepository;
    }

    @Override
    public ScoreRecord saveScoreRecordPerBall(ScoreRecord scoreRecorder) {
        return scoreRecorderRepository.save(scoreRecorder);
    }

    @Override
    public ScoreRecord getBallOutcome(long matchId, String overCount, int innings) {
        return scoreRecorderRepository.getBallOutcome(matchId, overCount, innings);
    }

    @Override
    public List<ScoreRecord> getStatsOnParticularBall(long matchId, String over) {
        return scoreRecorderRepository.getStatsOnParticularBallOnBothInnings(matchId, over);
    }
}