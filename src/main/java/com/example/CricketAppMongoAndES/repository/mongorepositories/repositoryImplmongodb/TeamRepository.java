package com.example.CricketAppMongoAndES.repository.mongorepositories.repositoryImplmongodb;

import com.example.CricketAppMongoAndES.entities.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends MongoRepository<Team, Long> {
}