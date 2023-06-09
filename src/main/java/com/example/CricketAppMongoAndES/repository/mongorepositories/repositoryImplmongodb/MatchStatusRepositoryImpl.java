package com.example.CricketAppMongoAndES.repository.mongorepositories.repositoryImplmongodb;

import com.example.CricketAppMongoAndES.entities.MatchStatusRecord;
import com.example.CricketAppMongoAndES.repository.mongorepositories.repositorymongodb.MatchStatusRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchStatusRepositoryImpl extends MongoRepository<MatchStatusRecord, Long>, MatchStatusRepository {

    @Query(value = "{matchId : ?0}")
    Optional<MatchStatusRecord> getMatchRecordByMatchId(long matchId);

    @Query(value = "{matchId : ?0}", fields = "{matchStatus : 1}")
    Optional<String> getMatchStatusByMatchId(long matchId);

}