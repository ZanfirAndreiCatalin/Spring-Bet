package com.javaproject.SpringbootFirstApplication;

import java.util.ArrayList;
import java.util.List;

public class FootballMatch {
    private int id;
    private String homeTeam;
    private String awayTeam;
    private String date;
    private String time;
    private List<Bet> bets = new ArrayList<>();

    public FootballMatch() {}

    public FootballMatch(int id, String homeTeam, String awayTeam, String date, String time) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void addBet(Bet bet) {
        this.bets.add(bet);
    }
}
