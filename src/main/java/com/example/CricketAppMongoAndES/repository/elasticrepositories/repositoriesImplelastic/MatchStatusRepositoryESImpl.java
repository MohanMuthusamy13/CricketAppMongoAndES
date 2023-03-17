package com.example.CricketAppMongoAndES.repository.elasticrepositories.repositoriesImplelastic;

import com.example.CricketAppMongoAndES.entities.MatchStatusRecord;
import com.example.CricketAppMongoAndES.repository.elasticrepositories.repositoryelastic.MatchStatusRepositoryES;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface MatchStatusRepositoryESImpl extends ElasticsearchRepository<MatchStatusRecord, Long>, MatchStatusRepositoryES {

    @Query("""
            {
                "bool": {
                  "should": [
                    {
                      "wildcard": {
                        "match_id": {
                          "value": "?0"
                        }
                      }
                    },
                    {
                      "fuzzy": {
                        "match_id": {
                          "value": "?0"
                        }
                      }
                    }
                  ]
                }
              }""")
    MatchStatusRecord getMatchStatus(long matchId);
}