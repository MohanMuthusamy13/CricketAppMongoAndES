package com.example.CricketAppMongoAndES.repository.elasticrepositories;

import com.example.CricketAppMongoAndES.entities.Match;
import org.springframework.data.elasticsearch.annotations.Query;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepositoryES extends ElasticsearchRepository<Match, Long> {

    List<Match> findByMatchFormat(String matchFormat);

    @Query("""
            {
                "nested": {
                  "path": "teams_played",
                  "query": {
                    "match": {
                      "teams_played.team_name": "?0"
                    }
                  }
                }
              }""")
    List<Match> getMatchesByTeamName(String teamName);

    @Query("""
            {
                "nested": {
                  "path": "teams_played",
                  "query": {
                    "match": {
                      "teams_played.team_name": "?0"
                    }
                  }
                }
              }""")
    List<Match> getMatchesPlayedByTeamName(String teamName);

    @Query(value = """
            {
                "nested": {
                  "path": "teams_played",
                  "query": {
                    "match": {
                      "teams_played.team_name": "?0"
                    }
                  }
                }
              }""", count = true)
    long getMatchesCountPlayedByTeamName(String teamName);
}