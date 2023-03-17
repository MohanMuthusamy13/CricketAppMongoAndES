package com.example.CricketAppMongoAndES.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "score_recorder")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "score_recorder")
public class ScoreRecord {

    @Transient
    public static final String SEQUENCE_NAME = "score_recorder_seq";

    @Id
    @Field(type = FieldType.Long, name = "ball_id")
    private long ballId;

    @Field(type = FieldType.Keyword, name = "match_id")
    private long matchId;

    @Field(type = FieldType.Text, name = "over_count")
    private String overCount;

    @Field(type = FieldType.Keyword, name = "ball_outCome")
    private String ballOutcome;

    @Field(type = FieldType.Integer, name = "innings")
    private int innings;

    @Field(type = FieldType.Integer, name = "total_runs_scored")
    private int totalRunsScoredByBattingTeam;

    @Field(type = FieldType.Integer, name = "total_wickets_down")
    private int totalWicketsDown;

    @Field(type = FieldType.Object, name = "batsman")
    private Player batsman;

    @Field(type = FieldType.Object, name = "bowler")
    private Player bowler;
}