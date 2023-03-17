package com.example.CricketAppMongoAndES.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "match_status")
@CompoundIndexes(
        @CompoundIndex(name = "compound_index_id_status",
                       def = "{matchId : 1, matchStatus : 1}")
)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "match_status")
public class MatchStatusRecord {
    public static final String SEQUENCE_NAME = "match_status_recorder_seq";

    @Id
    @Field(type = FieldType.Long, name = "match_status_record_id")
    private long matchStatusRecordId;

    @Field(type = FieldType.Keyword, name = "match_id")
    private long matchId;

    @Field(type = FieldType.Object, name = "score_of_teams")
    private Map<Long, Integer> scoreOfBothTeams;

    @Field(type = FieldType.Text, name = "match_status")
    private String matchStatus;
}