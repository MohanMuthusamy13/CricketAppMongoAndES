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
@Document("teams")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "teams")
public class Team {

    @Transient
    public static final String SEQUENCE_NAME = "sequence_for_teams";

    @Id
    @Field(type = FieldType.Long, name = "team_id")
    private long teamId;

    @Field(type = FieldType.Keyword, name = "team_name")
    private String teamName;

    @Field(type = FieldType.Integer, name = "matches_played")
    private int matchesPlayed;

    @Field(type = FieldType.Nested, name = "players")
    private List<Player> players;

}