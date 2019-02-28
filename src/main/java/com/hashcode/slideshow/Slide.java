package com.hashcode.slideshow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

public class Slide {
    public Photo[] photos;

    public Slide(Photo[] photos) {
        this.photos = photos;
    }

    public Set<String> getTags(){
        HashSet<String> tags = new HashSet<String>();
        for(Photo photo : photos) {
            tags.addAll(photo.tags);
        }

        return tags;
    }


    public Integer getNumberTagas(){
        return  getTags().size();
    }

    @Override
    public String toString() {
        String result = "";
        for (Photo photo : photos){
            result = result + photo.position + " ";}

        return result;
    }

    public int getPunctuationFor(Slide testSlide) {
        this.getTags();
        int equals = 0;
        Iterator tagsOnThis = this.getTags().iterator();
        while(tagsOnThis.hasNext()){
            String tag = (String) tagsOnThis.next();
            if (testSlide.getTags().contains(tag)) {
                equals++;
            }
        }
        int tagsExclusiveOnS1 = this.getTags().size() - equals;
        int tagsExclusiveOnS2 = testSlide.getTags().size() - equals;

        return Math.min(Math.min(tagsExclusiveOnS1,tagsExclusiveOnS2),equals);
    }
}
