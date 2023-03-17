package com.example.CricketAppMongoAndES.repository.elasticrepositories.repositoriesImplelastic;

import com.example.CricketAppMongoAndES.entities.Team;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepositoryESImpl extends ElasticsearchRepository<Team, Long> {
}