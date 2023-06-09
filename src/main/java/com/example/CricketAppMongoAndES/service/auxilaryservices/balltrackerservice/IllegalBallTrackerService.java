package com.example.CricketAppMongoAndES.service.auxilaryservices.balltrackerservice;

import com.example.CricketAppMongoAndES.service.auxilaryservices.majorgameservice.GameService;
import com.example.CricketAppMongoAndES.service.auxilaryservices.runtrackerservice.ScoreService;
import com.example.CricketAppMongoAndES.utils.Constants;
import lombok.Getter;
import lombok.Setter;

// MAKING THE CLASS NON-INITIALIZABLE AS THIS IS UTILITY CLASS
public class IllegalBallTrackerService {

    private static final int ignoreBallCount = -1;

    @Getter @Setter
    private static int wideBalls;
    @Getter @Setter
    private static int noBalls;

    private IllegalBallTrackerService() {}

    public static void wideTracker() {
        ScoreService.addScore(GameService.getBatting(), Constants.ILLEGAL_BALL_RUN);
        GameService.getBowlingPlayer().setBallsBowled(ignoreBallCount);
        wideBalls++;
    }

    public static void noBallTracker() {
        ScoreService.addScore(GameService.getBatting(), Constants.ILLEGAL_BALL_RUN);
        GameService.getBowlingPlayer().setBallsBowled(ignoreBallCount);
        noBalls++;
    }
}