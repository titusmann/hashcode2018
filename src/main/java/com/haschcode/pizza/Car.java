package com.haschcode.pizza;

import java.util.ArrayList;
import java.util.List;

public class Car {

    private Integer columnPos = 0;
    private Integer rowPos = 0;
    private Integer time = 0;
    private List<Ride> rides = new ArrayList<>();

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

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    @Override
    public String toString() {
        return "Car{" +
                "columnPos=" + columnPos +
                ", rowPos=" + rowPos +
                ", time=" + time +
                ", rides=" + rides +
                '}';
    }
}
