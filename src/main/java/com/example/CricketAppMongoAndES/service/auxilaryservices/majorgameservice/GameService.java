package com.example.CricketAppMongoAndES.service.auxilaryservices.majorgameservice;


import com.example.CricketAppMongoAndES.service.auxilaryservices.balltrackerservice.IllegalBallTrackerService;
import com.example.CricketAppMongoAndES.service.auxilaryservices.balltrackerservice.OverService;
import com.example.CricketAppMongoAndES.service.auxilaryservices.balltrackerservice.WicketStatusProvider;
import com.example.CricketAppMongoAndES.service.auxilaryservices.balltrackerservice.WinningStatusProvider;
import com.example.CricketAppMongoAndES.service.auxilaryservices.initializematchservice.ResetGameService;
import com.example.CricketAppMongoAndES.service.auxilaryservices.initializematchservice.TossService;
import com.example.CricketAppMongoAndES.service.auxilaryservices.runtrackerservice.RunsGenerator;
import com.example.CricketAppMongoAndES.entities.Match;
import com.example.CricketAppMongoAndES.entities.Player;
import com.example.CricketAppMongoAndES.entities.Team;
import com.example.CricketAppMongoAndES.repository.mongorepositories.repositoryImplmongodb.TeamRepository;
import com.example.CricketAppMongoAndES.service.auxilaryservices.playerservice.AuxiliaryPlayerService;
import com.example.CricketAppMongoAndES.service.auxilaryservices.runtrackerservice.ScoreService;
import com.example.CricketAppMongoAndES.utils.Constants;
import com.example.CricketAppMongoAndES.view.ScoreBoardDisplay;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Data
@Component
public class GameService {

    @Getter @Setter
    private static List playingTeamsPlayers;
    @Getter @Setter
    private static List playingTeams;
    private static int currentBatter;
    private static int currentBowler = Constants.FIRST_BOWLER_IN_TEAM;
    @Getter @Setter
    private static int[] scoreTeams = new int[2];
    @Getter @Setter
    private static int Innings;
    @Getter @Setter
    private static int Batting;
    @Setter
    private static int Bowling;
    @Getter @Setter
    private static int totalOvers;
    @Setter
    private static boolean legalBallFlag = false;
    private static String matchFormat;
    @Getter @Setter
    private static String flagForTeamWinningIndicationOnSecondInnings = "";
    @Setter
    private static int wickets;
    @Getter @Setter
    private static long tempMatchId;
    @Getter @Setter
    private static int runsScorePerBall;
    @Getter @Setter
    private static List<Team> teams = new ArrayList<>();
    @Getter @Setter
    private static Match matchTeams;
    private final TeamRepository teamRepository;


    @Autowired
    AuxiliaryPlayerService auxiliaryPlayerService;
    @Autowired
    WicketStatusProvider wicketTracker;
    @Autowired
    ScoreBoardDisplay scoreBoardDisplay;
    @Autowired
    RunsGenerator runsGenerator;
    WinningStatusProvider checkWinning = new WinningStatusProvider();

    public GameService(TeamRepository teamRepository) {
        gameServiceProvider();
        this.teamRepository = teamRepository;
    }

    public void gameServiceProvider() {
        ScoreService.setScoreOfBothTeams(new int[2]);
        GameService.scoreTeams = ScoreService.getScoreOfBothTeams();
        GameService.wickets = WicketStatusProvider.getWicketLose();
        GameService.Innings = Constants.FIRST_INNINGS;
    }

    public static Player getBattingPlayer() {
        return ((ArrayList<Player>) playingTeamsPlayers.get(Batting)).get(currentBatter);
    }

    public static Player getBowlingPlayer() {
        return ((ArrayList<Player>) playingTeamsPlayers.get(Math.abs(1 - Batting))).get(currentBowler);
    }

    public static void setCurrentBatter(int currentBatter) {
        getBattingPlayer().setActiveStatus("active");
        GameService.currentBatter = currentBatter;
    }

    public static void setCurrentBowler(int currentBowler) {
        getBowlingPlayer().setActiveStatus("active");
        GameService.currentBowler = currentBowler;
    }

    public static void setNextBowler() {
        if (currentBowler < Constants.LAST_BOWLER_IN_TEAM) {
            currentBowler += 1;
        }
        else {
            setCurrentBowler(Constants.FIRST_BOWLER_IN_TEAM);
        }
    }

    public static void setNextBatter() {
        GameService.currentBatter += 1;
    }

    public void scoreAccumulator(int Batting, int runsScorePerBall) {
        ScoreService.addScore(Batting, runsScorePerBall);
        AuxiliaryPlayerService.addScoreToBatter(runsScorePerBall);
        GameService.setLegalBallFlag(true);
    }

    public void startBattingAndBowling() {
        runsScorePerBall = runsGenerator.runsGeneratorByAbility(getBattingPlayer().getBaseAbility());

        switch (runsScorePerBall) {
            case Constants.WICKET:
                wicketTracker.gotWicket();
                GameService.setLegalBallFlag(true);
                break;
            case Constants.WIDE:
                IllegalBallTrackerService.wideTracker();
                GameService.setLegalBallFlag(false);
                break;
            case Constants.NO_BALL:
                IllegalBallTrackerService.noBallTracker();
                GameService.setLegalBallFlag(false);
                break;
            case Constants.DOT_BALL, Constants.ONE_RUN,
                    Constants.TWO_RUNS, Constants.THREE_RUNS, Constants.FIVE_RUNS:
                scoreAccumulator(Batting, runsScorePerBall);
                break;
            case Constants.BOUNDARY:
                GameService.getBattingPlayer().setNoOfFours(1);
                scoreAccumulator(Batting, runsScorePerBall);
                break;
            case Constants.SIX:
                GameService.getBattingPlayer().setNoOfSixes(1);
                scoreAccumulator(Batting, runsScorePerBall);
                break;
        }

        if (legalBallFlag) {
            OverService.BowlingStarts();
        }

        if (checkWinning.checkWinningStatusNumber() != 3) {
            checkWinning.checkWinningStatus();
            scoreBoardDisplay.showFinalScoreBoard();
            System.exit(0);
        }
        else {
            if (legalBallFlag)
                OverService.IncreaseBallCount();
        }

        if (Innings == Constants.SECOND_INNINGS) {
            if (!checkWinning.checkWinningStatusForSecondInnings().equals("")){
//                flagForTeamWinningIndicationOnSecondInnings = "Game Over";
                flagForTeamWinningIndicationOnSecondInnings = checkWinning.checkWinningStatusForSecondInnings();
            }
        }

        scoreBoardDisplay.showStatusPerBall();
        auxiliaryPlayerService.saveScoreRecord(runsScorePerBall);
    }

    public String startGame(long matchId) throws Exception {
        auxiliaryPlayerService.playingTeamPlayersProvider(matchId);
        TossService.startTossing();
        AuxiliaryPlayerService.setActiveStatusForPlayers();
        while (Innings <= Constants.SECOND_INNINGS) {
            if ((WicketStatusProvider.getWicketLose() > Constants.LAST_WICKET || OverService.getOverCount() == totalOvers)
                    && Innings == Constants.FIRST_INNINGS) {
                wicketTracker.startSecondInnings();
            }
            else if ((Innings == Constants.SECOND_INNINGS && (OverService.getOverCount() == totalOvers)) ||
                    ((GameService.flagForTeamWinningIndicationOnSecondInnings).equals("Current Team Wins")) ||
                    ((GameService.flagForTeamWinningIndicationOnSecondInnings).equals("Current Team Loses")) ||
                    (WicketStatusProvider.getWicketLose() >= Constants.LAST_WICKET
                            && WicketStatusProvider.isAllWicketsDownInSecondInnings())){
                scoreBoardDisplay.showScoreOfBothTeams();
                checkWinning.checkWinningStatus();
                break;
            }
            else {
                startBattingAndBowling();
            }
        }
        auxiliaryPlayerService.updateStatsAndScores(matchId);
        ResetGameService.resetGame();
        return "Match has Completed";
    }
}