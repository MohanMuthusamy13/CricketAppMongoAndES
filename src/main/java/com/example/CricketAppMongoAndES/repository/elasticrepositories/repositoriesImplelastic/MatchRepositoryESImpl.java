package com.example.CricketAppMongoAndES.repository.elasticrepositories.repositoriesImplelastic;

import com.example.CricketAppMongoAndES.entities.Match;
import com.example.CricketAppMongoAndES.repository.elasticrepositories.repositoryelastic.MatchRepositoryES;
import org.springframework.data.elasticsearch.annotations.Query;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepositoryESImpl extends ElasticsearchRepository<Match, Long>, MatchRepositoryES {

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