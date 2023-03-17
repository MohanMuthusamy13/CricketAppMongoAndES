package com.example.CricketAppMongoAndES.service.servicemongo.serviceimplementation;

import com.example.CricketAppMongoAndES.entities.ScoreRecord;
import com.example.CricketAppMongoAndES.repository.mongorepositories.repositoryImplmongodb.ScoreRecorderRepositoryImpl;
import com.example.CricketAppMongoAndES.service.servicemongo.serviceinterfaces.ScoreRecorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreRecorderServiceImpl implements ScoreRecorderService {

    private ScoreRecorderRepositoryImpl scoreRecorderRepository;

    @Autowired
    public ScoreRecorderServiceImpl(ScoreRecorderRepositoryImpl scoreRecorderRepository) {
        this.scoreRecorderRepository = scoreRecorderRepository;
    }

    @Override
    public ScoreRecord saveScoreRecordPerBall(ScoreRecord scoreRecorder) {
        return scoreRecorderRepository.save(scoreRecorder);
    }

    @Override
    public List<ScoreRecord> getStatsOnParticularBall(long matchId, String over) {
        return scoreRecorderRepository.getStatsOnParticularBall(matchId, over);
    }

    @Override
    public List<ScoreRecord> getStatsOnParticularBallAndInnings(long matchId, String over, int innings) {
        return scoreRecorderRepository.getStatsOnParticularBallAndInnings(matchId, over, innings);
    }
}