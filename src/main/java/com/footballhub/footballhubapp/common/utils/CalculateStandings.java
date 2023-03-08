package com.footballhub.footballhubapp.common.utils;

import com.footballhub.footballhubapp.common.models.PremierLeagueModel;

import java.util.*;

public class CalculateStandings {

    private final List<PremierLeagueModel> request;
    private final HashMap<String, HashMap<String, Integer>> response = new HashMap<>();

    public CalculateStandings(List<PremierLeagueModel> request) {
        this.request = request;
    }

    /*
     * Club Name, MP, W, D, L, GF, GA, GD, Pts
     */
    private List<HashMap<String, Object>> init() {
        List<HashMap<String, Object>> mapList = new ArrayList<>();
        for (Object club : getClubs()) {
            HashMap<String, Object> clubStanding = new HashMap<>();
            clubStanding.put("Club", club);
            clubStanding.put("MP", 0);
            clubStanding.put("W", 0);
            clubStanding.put("D", 0);
            clubStanding.put("L", 0);
            clubStanding.put("GF", 0);
            clubStanding.put("GA", 0);
            clubStanding.put("GD", 0);
            clubStanding.put("Pts", 0);
            mapList.add(clubStanding);
        }
        return mapList;
    }

    public List<HashMap<String, Object>> evaluate() {
        List<HashMap<String, Object>> map = init();
        // Iterate over each entity of DB
        for (PremierLeagueModel model : this.request) {
            // Check if the game has been played
            if (model.getHomeTeamScore() != null && model.getAwayTeamScore() != null) {
                String homeTeam = model.getHomeTeam();
                String awayTeam = model.getAwayTeam();
                int homeTeamScore = model.getHomeTeamScore();
                int awayTeamScore = model.getAwayTeamScore();
                teamStandingCalculator(map, homeTeam, awayTeam, homeTeamScore, awayTeamScore);
            }
        }
        sortStanding(map);
        return map;
    }

    private String calculateGameResult(int homeTeamScore, int awayTeamScore) {
        if (homeTeamScore > awayTeamScore) {
            return "W";
        } else if (homeTeamScore == awayTeamScore) {
            return "D";
        }
        return "L";
    }

    private void teamStandingCalculator(List<HashMap<String, Object>> map, String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) {
        HashMap<String, Object> homeTeamCurrentStanding = new HashMap<>();
        HashMap<String, Object> awayTeamCurrentStanding = new HashMap<>();
        for (HashMap<String, Object> team : map) {
            String teamClub = (String) team.get("Club");
            if (teamClub.equalsIgnoreCase(homeTeam)) {
                homeTeamCurrentStanding = team;
            }
            if (teamClub.equalsIgnoreCase(awayTeam)) {
                awayTeamCurrentStanding = team;
            }
        }
        // MP
        int homeMP = (int) homeTeamCurrentStanding.get("MP");
        int awayMP = (int) awayTeamCurrentStanding.get("MP");
        // W
        int homeWin = (int) homeTeamCurrentStanding.get("W");
        int awayWin = (int) awayTeamCurrentStanding.get("W");
        // L
        int homeLose = (int) homeTeamCurrentStanding.get("L");
        int awayLose = (int) awayTeamCurrentStanding.get("L");
        // D
        int homeDraw = (int) homeTeamCurrentStanding.get("D");
        int awayDraw = (int) awayTeamCurrentStanding.get("D");
        // GF
        int homeGF = (int) homeTeamCurrentStanding.get("GF");
        int awayGF = (int) awayTeamCurrentStanding.get("GF");
        // GA
        int homeGA = (int) homeTeamCurrentStanding.get("GA");
        int awayGA = (int) awayTeamCurrentStanding.get("GA");
        // pts
        int homePts = (int) homeTeamCurrentStanding.get("Pts");
        int awayPts = (int) awayTeamCurrentStanding.get("Pts");
        // calculate game result || W, D, L, Pts
        switch (calculateGameResult(homeTeamScore, awayTeamScore)) {
            case "W":
                // W
                homeTeamCurrentStanding.put("W", homeWin + 1);
                awayTeamCurrentStanding.put("L", awayLose + 1);
                // pts
                homeTeamCurrentStanding.put("Pts", homePts + 3);
                break;
            case "D":
                // D
                homeTeamCurrentStanding.put("D", homeDraw + 1);
                awayTeamCurrentStanding.put("D", awayDraw + 1);
                // pts
                homeTeamCurrentStanding.put("Pts", homePts + 1);
                awayTeamCurrentStanding.put("Pts", awayPts + 1);
                break;
            default:
                // L
                homeTeamCurrentStanding.put("L", homeLose + 1);
                awayTeamCurrentStanding.put("W", awayWin + 1);
                // pts
                awayTeamCurrentStanding.put("Pts", awayPts + 3);
                break;
        }
        // GF
        homeTeamCurrentStanding.put("GF", homeGF + homeTeamScore);
        awayTeamCurrentStanding.put("GF", awayGF + awayTeamScore);
        // GA
        homeTeamCurrentStanding.put("GA", homeGA + awayTeamScore);
        awayTeamCurrentStanding.put("GA", awayGA + homeTeamScore);
        // GD
        homeTeamCurrentStanding.put("GD", (int) homeTeamCurrentStanding.get("GF") - (int) homeTeamCurrentStanding.get("GA"));
        awayTeamCurrentStanding.put("GD", (int) awayTeamCurrentStanding.get("GF") - (int) awayTeamCurrentStanding.get("GA"));
        // MP
        homeTeamCurrentStanding.put("MP", homeMP + 1);
        awayTeamCurrentStanding.put("MP", awayMP + 1);
    }

    private HashSet<Object> getClubs() {
        HashSet<Object> clubs = new HashSet<>();
        for (PremierLeagueModel model : this.request) {
            clubs.add(model.getHomeTeam());
        }
        return clubs;
    }

    private void sortStanding(List<HashMap<String, Object>> standing) {
        Collections.sort(standing, new Comparator<HashMap<String, Object>>() {
            @Override
            public int compare(HashMap<String, Object> o1, HashMap<String, Object> o2) {
                int gamePoint1 = o1.get("Pts") != null ? (int) o1.get("Pts") : 0;
                int gamePoint2 = o2.get("Pts") != null ? (int) o2.get("Pts") : 0;
                if (gamePoint1 == gamePoint2) {
                    int goalDifference1 = o1.get("GD") != null ? (int) o1.get("GD") : 0;
                    int goalDifference2 = o2.get("GD") != null ? (int) o2.get("GD") : 0;
                    return Integer.compare(goalDifference2, goalDifference1);
                }
                return Integer.compare(gamePoint2, gamePoint1);
            }
        });
    }
}
