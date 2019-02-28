package com.hashcode.slideshow;

import java.util.ArrayList;

public class Photo {
    public ArrayList<String> tags;
    public boolean isHorizontal;
    public int position;

//    public enum Horientacion
//    {
//        HORIZONTAL, VERTICAL
//
//    }

    public Photo(boolean isHorizontal, ArrayList<String> tags, int position) {
        this.tags = tags;
        this.isHorizontal = isHorizontal;
        this.position = position;
    }

    @Override
    public String toString() {
        return "isHorizontal: " + isHorizontal + "; position: " + position +", tags: " + tags.toString();
    }



}
