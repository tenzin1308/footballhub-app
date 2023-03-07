package com.footballhub.footballhubapp.common.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "epl-table")
public class PremierLeagueModel {

    @Id
    private ObjectId id;
    private int MatchNumber;
    private int RoundNumber;
    private String DateUtc;
    private String Location;
    private String HomeTeam;
    private String AwayTeam;
    private String Group;
    private Integer HomeTeamScore;
    private Integer AwayTeamScore;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getMatchNumber() {
        return MatchNumber;
    }

    public void setMatchNumber(int matchNumber) {
        MatchNumber = matchNumber;
    }

    public int getRoundNumber() {
        return RoundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        RoundNumber = roundNumber;
    }

    public String getDateUtc() {
        return DateUtc;
    }

    public void setDateUtc(String dateUtc) {
        DateUtc = dateUtc;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getHomeTeam() {
        return HomeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        HomeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return AwayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        AwayTeam = awayTeam;
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }

    public Integer getHomeTeamScore() {
        return HomeTeamScore;
    }

    public void setHomeTeamScore(Integer homeTeamScore) {
        HomeTeamScore = homeTeamScore;
    }

    public Integer getAwayTeamScore() {
        return AwayTeamScore;
    }

    public void setAwayTeamScore(Integer awayTeamScore) {
        AwayTeamScore = awayTeamScore;
    }
}
