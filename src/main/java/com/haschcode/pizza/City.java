package com.haschcode.pizza;

import java.util.ArrayList;
import java.util.List;

public class City {

    private Integer rows;

    private Integer columns;

    private List<Car> cars;

    private List<Ride> rides;

    private Integer bonus;

    private Integer maxTime;

    public City(Integer rows, Integer columns, List<Car> cars, Integer bonus, Integer maxTime) {
        this.rows = rows;
        this.columns = columns;
        this.cars = cars;
        this.bonus = bonus;
        this.maxTime = maxTime;
        this.rides = new ArrayList<>();
    }


    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    public Integer getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Integer maxTime) {
        this.maxTime = maxTime;
    }

    public void addRide(Ride ride){
        this.rides.add(ride);
    }
}
