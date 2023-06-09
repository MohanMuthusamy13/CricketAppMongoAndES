package com.example.CricketAppMongoAndES.service.auxilaryservices.initializematchservice;


import com.example.CricketAppMongoAndES.enums.Toss;
import com.example.CricketAppMongoAndES.service.auxilaryservices.majorgameservice.GameService;
import com.example.CricketAppMongoAndES.utils.Constants;
import com.example.CricketAppMongoAndES.view.TossDisplay;

public class TossService {

    private TossService() {}

    public static int getTossResult(int minimum, int maximum) {
        return (int)Math.floor(Math.random() * (maximum - minimum + 1) + minimum);
    }

    public static void startTossing() {

        Toss[] tossPossibilities = new Toss[] {Toss.HEAD, Toss.TAIL};
        Toss tossWinner = tossPossibilities[getTossResult(0, 1)];
        int teamSelected = tossWinner.getBattingOrderIndicator();
        TossDisplay.tossDisplayed(teamSelected);
        if (teamSelected == 0) {
            GameService.setBatting(Constants.SECOND_TEAM);
            GameService.setBowling(Constants.FIRST_TEAM);
        }
        else {
            GameService.setBatting(Constants.FIRST_TEAM);
            GameService.setBowling(Constants.SECOND_TEAM);
        }
    }
}