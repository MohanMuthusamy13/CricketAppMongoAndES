package com.example.CricketAppMongoAndES.repository.elasticrepositories;

import com.example.CricketAppMongoAndES.entities.Team;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepositoryES extends ElasticsearchRepository<Team, Long> {
}