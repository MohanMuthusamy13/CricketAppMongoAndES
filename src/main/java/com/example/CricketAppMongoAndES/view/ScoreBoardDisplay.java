package com.example.CricketAppMongoAndES.view;

import com.example.CricketAppMongoAndES.entities.Team;
import com.example.CricketAppMongoAndES.service.auxilaryservices.balltrackerservice.OverService;
import com.example.CricketAppMongoAndES.service.auxilaryservices.balltrackerservice.WicketStatusProvider;
import com.example.CricketAppMongoAndES.service.auxilaryservices.balltrackerservice.WinningStatusProvider;
import com.example.CricketAppMongoAndES.service.auxilaryservices.majorgameservice.GameService;
import com.example.CricketAppMongoAndES.service.auxilaryservices.runtrackerservice.ScoreService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScoreBoardDisplay {

    public static void printFirstInningsFinalScore() {
        System.out.printf(
                """
                   END OF FIRST INNINGS :
                        
                   TOTAL RUNS SCORED BY TEAM %d : %d
  
                   """,
                GameService.getBatting() + 1, GameService.getScoreTeams()[GameService.getBatting()]);
    }

    public String runsForDisplayProvider(int runsScorePerBall) {
        if (runsScorePerBall < 0) {
            if (runsScorePerBall == -1) {
                return "WICKET";
            }
            else if (runsScorePerBall == -2) {
                return "WIDE";
            }
            else {
                return "NO BALL";
            }
        }
        else {
            return Integer.toString(runsScorePerBall);
        }
    }


    public void showStatusPerBall() {
        System.out.printf(
                """
                        Runs Scored :     %s
                        Overs       :     %s
                        Total Score :     %d
                        Wickets Lose:     %d
                        Current Active Batter : %s\t\t\t\t\tCurrent Active Batter Score : %d
                        Current Active Bowler : %s\t\t\t\t\tCurrent Active Bowler Wickets : %d

                        %n""",
                runsForDisplayProvider(GameService.getRunsScorePerBall()),
                OverService.getOverInString(), ScoreService.getScoreOfBothTeams()[GameService.getBatting()],
                WicketStatusProvider.getWicketLose(),
                GameService.getBattingPlayer().getName(), GameService.getBattingPlayer().getScore(),
                GameService.getBowlingPlayer().getName(), GameService.getBowlingPlayer().getWicketsTaken()
        );
    }

    public String finalScoreBoard() {
        return String.format("""
                SCORE BOARD
                Score board of Team 1
                %s
                Score board of Team 2
                %s""", GameService.getPlayingTeamsPlayers().get(0), GameService.getPlayingTeamsPlayers().get(1));
    }

    public void showFinalScoreBoard() {
        System.out.println(finalScoreBoard());
    }

    public String getScoreBoard() {
        return finalScoreBoard();
    }

    public String showScoreOfBothTeams() {
        List<Team> playingTeams = GameService.getTeams();
        String result = String.format(
                """
                        FINAL SCORE
                        %s  : %d
                        %s  : %d
                        """,
                playingTeams.get(0).getTeamName(),
                ScoreService.getScoreOfBothTeams()[0],
                playingTeams.get(1).getTeamName(),
                ScoreService.getScoreOfBothTeams()[1]);
        System.out.println(result);
        return result;
    }

    public String getResults() {
        List<Team> playingTeams = GameService.getTeams();
        if (ScoreService.getScoreOfBothTeams()[0] > ScoreService.getScoreOfBothTeams()[1]) {
            String result = WinningStatusProvider.diffProvider(1);
            return String.format("%s won the match", playingTeams.get(0).getTeamName());
        }
        else if (ScoreService.getScoreOfBothTeams()[0] < ScoreService.getScoreOfBothTeams()[1]) {
            String result = WinningStatusProvider.diffProvider(2);
            return String.format("%s won the match", playingTeams.get(1).getTeamName());
        }
        return "Draw";
    }

    public void showStatusPerBallForTesting() {
        System.out.println("SCORE BOARD OF BATTING");
        System.out.println(GameService.getPlayingTeamsPlayers().get(GameService.getBatting()));

        System.out.println("SCORE BOARD OF BOWLING");
        System.out.println(GameService.getPlayingTeamsPlayers().get(Math.abs(1 - GameService.getBatting())));
    }
}