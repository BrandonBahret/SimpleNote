package com.brandonbahret.simplenote;

import java.io.Serializable;

/**
 * Created by Brandon on 8/6/2017.
 * Data model for storing notes.
 */

public class Note implements Serializable {

    private String name;
    private String text;

    public Note(){
        // Required for firebase
    }

    public Note(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
