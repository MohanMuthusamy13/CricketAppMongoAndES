package com.example.CricketAppMongoAndES.service.auxilaryservices.playerservice;

import com.example.CricketAppMongoAndES.configuration.Config;
import com.example.CricketAppMongoAndES.service.auxilaryservices.balltrackerservice.OverService;
import com.example.CricketAppMongoAndES.service.auxilaryservices.balltrackerservice.WicketStatusProvider;
import com.example.CricketAppMongoAndES.service.auxilaryservices.initializematchservice.MatchFormatService;
import com.example.CricketAppMongoAndES.service.auxilaryservices.majorgameservice.GameService;
import com.example.CricketAppMongoAndES.service.auxilaryservices.majorgameservice.TeamSelectorService;
import com.example.CricketAppMongoAndES.service.auxilaryservices.runtrackerservice.PlayerCenturyAndHalfCenturyService;
import com.example.CricketAppMongoAndES.service.auxilaryservices.runtrackerservice.ScoreService;
import com.example.CricketAppMongoAndES.entities.ScoreRecord;
import com.example.CricketAppMongoAndES.entities.Team;
import com.example.CricketAppMongoAndES.service.auxilaryservices.initializematchservice.MatchRecordStatusService;
import com.example.CricketAppMongoAndES.service.serviceES.serviceinterfacesES.MatchServiceES;
import com.example.CricketAppMongoAndES.service.serviceES.serviceinterfacesES.ScoreRecorderServiceES;
import com.example.CricketAppMongoAndES.service.servicemongo.serviceimplementation.ScoreRecorderServiceImpl;
import com.example.CricketAppMongoAndES.service.servicemongo.serviceinterfaces.MatchService;
import com.example.CricketAppMongoAndES.utils.Constants;
import com.example.CricketAppMongoAndES.utils.builders.ScoreRecordBuilder;
import com.example.CricketAppMongoAndES.view.ScoreBoardDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuxiliaryPlayerService {

    @Autowired
    MatchServiceES matchServiceES;

    @Autowired
    MatchService matchService;
    @Autowired
    MatchRecordStatusService statusService;
    @Autowired
    ScoreBoardDisplay scoreBoardDisplay;
    @Autowired
    TeamSelectorService teamSelectorService;
    @Autowired
    ScoreRecordBuilder scoreRecordBuilder;

    @Autowired
    ScoreRecorderServiceImpl recorderService;

    @Autowired
    ScoreRecorderServiceES serviceES;

    @Autowired
    PlayerStatsRecorder playerStatsRecorder;

    public static void addScoreToBatter(int runsScorePerBall) {
        GameService.getBattingPlayer().setScore(runsScorePerBall);
        GameService.getBattingPlayer().setBallsFaced(1);
    }

    public static void setActiveStatusForPlayers() {
        GameService.getBattingPlayer().setActiveStatus("active");
        GameService.getBowlingPlayer().setActiveStatus("active");
    }

    public void playingTeamPlayersProvider(long matchId) throws Exception {
        if (Config.useMongoDatabase) {
            GameService.setMatchTeams(matchService.getMatchById(matchId));
        }
        else {
            GameService.setMatchTeams(matchServiceES.getMatchById(matchId));
        }
        GameService.setPlayingTeams(new ArrayList<>());
        List<Team> playingTeams =  GameService.getMatchTeams().getTeamsPlayed();
        GameService.setTempMatchId(matchId);
        GameService.setTeams(teamSelectorService.teamSelector(
                playingTeams.get(Constants.FIRST_TEAM).getTeamId(),
                playingTeams.get(Constants.SECOND_TEAM).getTeamId()
        ));
        GameService.setPlayingTeamsPlayers(
                List.of(
                        GameService.getTeams().get(Constants.FIRST_TEAM).getPlayers(),
                        GameService.getTeams().get(Constants.SECOND_TEAM).getPlayers()
                )
        );
    }

    public void updateStatsAndScores(long matchId) throws Exception {
        GameService.getMatchTeams().setTeamsPlayed(GameService.getTeams());
        GameService.getMatchTeams().setMatchFormat(MatchFormatService.getPlannedMatchFormat());
        PlayerCenturyAndHalfCenturyService.centuryStatsProvider(GameService.getPlayingTeamsPlayers());
        if (Config.useMongoDatabase) {
            matchService.updateMatch(matchId, GameService.getMatchTeams());
        }
        else {
            matchServiceES.updateMatch(matchId, GameService.getMatchTeams());
        }
        statusService.saveMatchStatusRecord(matchId);
        playerStatsRecorder.savePlayerStats(GameService.getPlayingTeamsPlayers());
    }

    public void saveScoreRecord(int runsScorePerBall) {
        String overCount = String.valueOf(OverService.getOverCount()) + "." + OverService.getBallsCount();

        ScoreRecord scoreRecord = scoreRecordBuilder.build(
                GameService.getTempMatchId(),
                overCount,
                scoreBoardDisplay.runsForDisplayProvider(runsScorePerBall),
                GameService.getInnings(),
                ScoreService.getScoreOfBothTeams()[GameService.getBatting()],
                WicketStatusProvider.getWicketLose(),
                GameService.getBattingPlayer(),
                GameService.getBowlingPlayer()
        );
//        scoreRecorderRepositoryService.saveScoreRecordPerBall(
//                scoreRecordBuilder.build(
//                        GameService.getTempMatchId(),
//                        overCount,
//                        scoreBoardDisplay.runsForDisplayProvider(runsScorePerBall),
//                        GameService.getInnings(),
//                        ScoreService.getScoreOfBothTeams()[GameService.getBatting()],
//                        WicketStatusProvider.getWicketLose(),
//                        GameService.getBattingPlayer(),
//                        GameService.getBowlingPlayer()
//                )
//        );

        if(Config.useMongoDatabase) {
            recorderService.saveScoreRecordPerBall(scoreRecord);
        }
        else {
            serviceES.saveScoreRecordPerBall(scoreRecord);
        }
    }



}