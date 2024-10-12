package com.javaproject.SpringbootFirstApplication;

import java.util.ArrayList;
import java.util.List;

public class FootballMatch {
    private String homeTeam;
    private String awayTeam;
    private String date;
    private String time;

    private final List<Bet> bets; // List of bets for this match
    private int Id;

    public FootballMatch(int Id, String homeTeam, String awayTeam, String date, String time) {
        this.Id = Id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = date;
        this.time = time;
        this.bets = new ArrayList<>();
    }
    public String getHomeTeam(){
        return homeTeam;
    }
    public void setHomeTeam(String homeTeam){
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam(){
        return awayTeam;
    }
    public void  setAwayTeam(String awayTeam){
        this.awayTeam = awayTeam;
    }

    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }

    public String getTime(){
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void addBet(Bet bet) {
        this.bets.add(bet);
    }
}