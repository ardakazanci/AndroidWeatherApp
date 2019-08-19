package com.ardakazanci.weatherapp.Model;

import java.util.List;

public class OpenWeatherMap {

    private Coord coord;
    private Clouds clouds;
    private List<Weather> weather;
    private Sys sys;
    private Wind wind;
    private Rain rain;
    private Main main;

    private int dt;
    private String base;
    private int id;
    private String name;
    private int cod;


    public OpenWeatherMap(Coord coord, Clouds clouds, List<Weather> weather, Sys sys, Wind wind, Rain rain, Main main, int dt, String base, int id, String name, int cod) {
        this.coord = coord;
        this.clouds = clouds;
        this.weather = weather;
        this.sys = sys;
        this.wind = wind;
        this.rain = rain;
        this.main = main;
        this.dt = dt;
        this.base = base;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }


    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
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

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}
