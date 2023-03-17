package com.example.CricketAppMongoAndES.repository.elasticrepositories.repositoriesImplelastic;

import com.example.CricketAppMongoAndES.entities.ScoreRecord;
import com.example.CricketAppMongoAndES.repository.elasticrepositories.repositoryelastic.ScoreRecorderRepositoryES;
import org.springframework.data.elasticsearch.annotations.Query;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRecorderRepositoryESImpl extends ElasticsearchRepository<ScoreRecord, Long>, ScoreRecorderRepositoryES {

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