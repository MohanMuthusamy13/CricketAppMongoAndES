package com.example.CricketAppMongoAndES.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "matches")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "matches")
public class Match {

    @Transient
    public static final String SEQUENCE_NAME = "sequence_for_matches";

    @Id
    @Field(type = FieldType.Long, name = "match_id")
    private long matchId;

    @Field(type = FieldType.Keyword, name = "match_format")
    private String matchFormat;

    @Field(type = FieldType.Nested, name = "teams_played")
    private List<Team> teamsPlayed;

    @Field(type = FieldType.Text, name = "match_status")
    private String matchStatus;
}