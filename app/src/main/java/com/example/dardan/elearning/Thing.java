package com.example.dardan.elearning;

/**
 * Created by Dardan on 4/5/2016.
 */
public class Thing {
    private int image;
    private int sound;
    private String text;
    private int noise;

    public Thing(int image, int sound, String text, int noise) {
        this.image = image;
        this.sound = sound;
        this.text = text;
        this.noise = noise;
    }

    public Thing(int image, int sound, String text) {
        this(image, sound, text, 0);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNoise() {
        return noise;
    }

    public boolean hasNoise() {
        return this.noise != 0;
    }

    public int getSound() { return sound; }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
