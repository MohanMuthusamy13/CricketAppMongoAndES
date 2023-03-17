package com.example.CricketAppMongoAndES.repository.elasticrepositories;

import com.example.CricketAppMongoAndES.entities.MatchStatusRecord;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface MatchStatusRepositoryES extends ElasticsearchRepository<MatchStatusRecord, Long> {

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