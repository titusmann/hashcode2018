package com.haschcode.pizza;

import java.util.ArrayList;
import java.util.List;

public class Car {

    private Integer columnPos;
    private Integer rowPos;
    private Integer time;
    private List<Rides> rides = new ArrayList<>();

    public Car() {
    }

    public Integer getColumnPos() {
        return columnPos;
    }

    public void setColumnPos(Integer columnPos) {
        this.columnPos = columnPos;
    }

    public Integer getRowPos() {
        return rowPos;
    }

    public void setRowPos(Integer rowPos) {
        this.rowPos = rowPos;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public List<Rides> getRides() {
        return rides;
    }

    public void setRides(List<Rides> rides) {
        this.rides = rides;
    }
}
