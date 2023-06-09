package com.example.CricketAppMongoAndES.repository.mongorepositories.repositoryImplmongodb;

import com.example.CricketAppMongoAndES.entities.ScoreRecord;
import com.example.CricketAppMongoAndES.repository.mongorepositories.repositorymongodb.ScoreRecorderRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRecorderRepositoryImpl extends MongoRepository<ScoreRecord, Long>, ScoreRecorderRepository {

    @Query(value = "{\"matchId\" : ?0, \"overCount\" : \"?1\"}}")
    List<ScoreRecord> getStatsOnParticularBall(long matchId, String over);

    @Query(value = "{\"matchId\" : ?0, \"overCount\" : \"?1\", \"innings\" : ?2}")
    List<ScoreRecord> getStatsOnParticularBallAndInnings(long matchId,
                                                   String over, int innings);

}