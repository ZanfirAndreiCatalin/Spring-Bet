package com.javaproject.SpringbootFirstApplication;

public class Bet {
    private int id;
    private String name;
    private String sport;
    private double odd;

    public Bet() {} // Needed for Jackson (JSON <-> Java)

    public Bet(int id, String name, String sport, double odd) {
        this.id = id;
        this.name = name;
        this.sport = sport;
        this.odd = odd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public double getOdd() {
        return odd;
    }

    public void setOdd(double odd) {
        this.odd = odd;
    }
}
