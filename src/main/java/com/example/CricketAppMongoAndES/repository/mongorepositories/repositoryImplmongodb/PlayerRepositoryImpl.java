package com.example.CricketAppMongoAndES.repository.mongorepositories.repositoryImplmongodb;

import com.example.CricketAppMongoAndES.entities.Player;
import com.example.CricketAppMongoAndES.repository.mongorepositories.repositorymongodb.PlayerRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepositoryImpl extends MongoRepository<Player, Long>, PlayerRepository {

    @Aggregation(pipeline = {
            "{'$sort' : {score : -1}}",
            "{'$limit' : 1}"
    })
    Player getOverallMaximumScorer();

    @Aggregation(pipeline = {
            "{'$sort' : {wicketsTaken : -1}}",
            "{'$limit' : 1}"
    })
    Player getOverallMaxWicketTaker();

    @Aggregation(pipeline = {
            "{$sort: {\"noOfFours\" : -1}}",
            "{$limit: 1}"
    })
    Player getMaxBoundariesHitter();

    @Aggregation(pipeline = {
            "{$sort: {\"noOfSixes\" : -1}}",
            "{$limit: 1}"
    })
    Player getMaxSixesHitter();

    List<Player> findByTeamName(String teamName);

    List<Player> findByBaseAbility(String baseAbility);

    Player findByName(String name);

}