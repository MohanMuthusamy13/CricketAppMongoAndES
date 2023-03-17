package com.example.CricketAppMongoAndES.repository.elasticrepositories;

import com.example.CricketAppMongoAndES.entities.ScoreRecord;
import org.springframework.data.elasticsearch.annotations.Query;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRecorderRepositoryES extends ElasticsearchRepository<ScoreRecord, Long> {

    @Query("""
            {
                "bool": {
                  "must": [
                    {
                      "match": {
                        "match_id": "?0"
                      }
                    },
                    {
                      "match": {
                        "over_count": "?1"
                      }
                    },
                    {
                      "match": {
                        "innings": ?2
                      }
                    }
                  ]
                }
              }""")
    ScoreRecord getBallOutcome(long matchId, String overCount, int innings);

    @Query("""
            {
                "bool": {
                  "must": [
                    {
                      "match": {
                        "match_id": "?0"
                      }
                    },
                    {
                      "match": {
                        "over_count": "?1"
                      }
                    }
                  ]
                }
              }""")
    List<ScoreRecord> getStatsOnParticularBallOnBothInnings(long matchId, String overCount);

}