package com.footballhub.footballhubapp.common.utils;

import com.footballhub.footballhubapp.common.models.PremierLeagueModel;

import java.util.HashMap;
import java.util.List;

public class CalculateStandings {

    private final List<PremierLeagueModel> request;
    private List<PremierLeagueModel> response;

    public CalculateStandings(List<PremierLeagueModel> request) {
        this.request = request;
    }

    public List<PremierLeagueModel> evaluate() {
        HashMap<String, Integer> map = new HashMap<>();
        // Iterate over each entity of DB
        for (PremierLeagueModel model : this.request) {
            String homeTeam = model.getHomeTeam();
            String awayTeam = model.getAwayTeam();
            int homeTeamScore = model.getHomeTeamScore();
            int awayTeamScore = model.getAwayTeamScore();
        }
        return response;
    }


}
