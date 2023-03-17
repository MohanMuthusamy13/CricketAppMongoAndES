package com.example.CricketAppMongoAndES.repository.elasticrepositories;

import com.example.CricketAppMongoAndES.entities.Player;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepositoryES extends ElasticsearchRepository<Player, Long> {

    @Query("""
            {
                "bool": {
                  "should": [
                    {
                      "wildcard": {
                        "name": {
                          "value": "*?0*"
                        }
                      }
                    },
                    {
                      "fuzzy": {
                        "name": {
                          "value": "?0"
                        }
                      }
                    }
                  ]
                  , "minimum_should_match": 1
                }
              }""")
    List<Player> getPlayerByName(String name);

    @Query("""
            {
                "bool": {
                  "must": [
                    {
                      "match": {
                        "team_name": "?0"
                      }
                    },
                    {
                      "match": {
                        "base_ability": "?1"
                      }
                    }
                  ]
                }
              }""")
    List<Player> getPlayersByTeamAndBaseAbility(String teamName, String baseAbility);


    @Query("""
            {
                "match": {
                  "team_name": "?0"
                }
              }""")
    List<Player> getPlayersByTeamName(String teamName);




}