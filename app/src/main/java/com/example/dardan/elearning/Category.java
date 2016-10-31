package com.example.dardan.elearning;

import java.util.ArrayList;

/**
 * Created by Dardan on 4/12/2016.
 */
public class Category {
    ArrayList<Thing> things;
    int currentIndex=0;
    String title;
    int image;
    int highscore;
    int color;
    int theme;

    public Category(String title,int image,int highscore,int color,int theme){
        this.title=title;
        this.image=image;
        this.highscore=highscore;
        this.color=color;
        this.theme=theme;
        this.things=new ArrayList<>();
    }
    public void addThing(Thing thing){
        things.add(thing);
    }

    public ArrayList<Thing> getListOfThings(){
        return this.things;
    }

    public Thing nextThing() {
        return things.get(++currentIndex);
    }

    public Thing prevThing() {
        return things.get(--currentIndex);
    }

    public Thing currentThing() {
        return things.get(currentIndex);
    }

    boolean hasNextThing(){
        return currentIndex < things.size()-1;
    }

    boolean hasPrevThing(){
        return currentIndex > 0;
    }

    public void goToFirstThing(){
        currentIndex=0;
    }
}
