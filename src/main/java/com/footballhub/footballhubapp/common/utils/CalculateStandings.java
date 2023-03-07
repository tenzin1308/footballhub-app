package com.footballhub.footballhubapp.common.utils;

import com.footballhub.footballhubapp.common.models.PremierLeagueModel;

import java.util.*;

public class CalculateStandings {

    private final List<PremierLeagueModel> request;
    private List<Map.Entry<String, Integer>> response;

    public CalculateStandings(List<PremierLeagueModel> request) {
        this.request = request;
    }

    public List<Map.Entry<String, Integer>> evaluate() {
        HashMap<String, Integer> map = new HashMap<>();
        // Iterate over each entity of DB
        for (PremierLeagueModel model : this.request) {
            String homeTeam = model.getHomeTeam();
            String awayTeam = model.getAwayTeam();
            int homeTeamScore = model.getHomeTeamScore();
            int awayTeamScore = model.getAwayTeamScore();
            if (map.containsKey(homeTeam)) {
                int currentScore = map.get(homeTeam);
                map.put(homeTeam, currentScore + homeTeamScore);
            } else {
                map.put(homeTeam, homeTeamScore);
            }
            if (map.containsKey(awayTeam)) {
                int currentScore = map.get(awayTeam);
                map.put(awayTeam, currentScore + awayTeamScore);
            } else {
                map.put(awayTeam, awayTeamScore);
            }
        }
        // Sort it with decreasing order
        response = new ArrayList<>(map.entrySet());
        Collections.sort(response, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        return response;
    }


}
