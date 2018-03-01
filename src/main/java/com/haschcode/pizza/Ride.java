package com.haschcode.pizza;

public class Ride {

    private Integer rowInitial;
    private Integer rowEnd;
    private Integer columnInitial;
    private Integer columnEnd;
    private Integer timeInitial;
    private Integer timeEnd;

    private int id;

    public Ride(Integer rowInitial, Integer rowEnd, Integer columnInitial, Integer columnEnd, Integer timeInitial, Integer timeEnd, int id) {
        this.rowInitial = rowInitial;
        this.rowEnd = rowEnd;
        this.columnInitial = columnInitial;
        this.columnEnd = columnEnd;
        this.timeInitial = timeInitial;
        this.timeEnd = timeEnd;
        this.id = id;
    }

    public Integer getRowInitial() {
        return rowInitial;
    }

    public void setRowInitial(Integer rowInitial) {
        this.rowInitial = rowInitial;
    }

    public Integer getRowEnd() {
        return rowEnd;
    }

    public void setRowEnd(Integer rowEnd) {
        this.rowEnd = rowEnd;
    }

    public Integer getColumnInitial() {
        return columnInitial;
    }

    public void setColumnInitial(Integer columnInitial) {
        this.columnInitial = columnInitial;
    }

    public Integer getColumnEnd() {
        return columnEnd;
    }

    public void setColumnEnd(Integer columnEnd) {
        this.columnEnd = columnEnd;
    }

    public Integer getTimeInitial() {
        return timeInitial;
    }

    public void setTimeInitial(Integer timeInitial) {
        this.timeInitial = timeInitial;
    }

    public Integer getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Integer timeEnd) {
        this.timeEnd = timeEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "rowInitial=" + rowInitial +
                ", rowEnd=" + rowEnd +
                ", columnInitial=" + columnInitial +
                ", columnEnd=" + columnEnd +
                ", timeInitial=" + timeInitial +
                ", timeEnd=" + timeEnd +
                ", id=" + id +
                '}';
    }
}
