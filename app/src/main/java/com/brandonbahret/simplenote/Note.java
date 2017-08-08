package com.brandonbahret.simplenote;

import android.app.Activity;
import android.content.Intent;

import java.io.Serializable;

/**
 * Created by Brandon on 8/6/2017.
 * Data model for storing notes.
 */

public class Note implements Serializable {

    private String name;
    private String text;
    private String pushId;

    //region Constructors
    public Note(String name, String text, String pushId) {
        this.name = name;
        this.text = text;
        this.pushId = pushId;
    }

    public Note(String name, String text) {
        this(name, text, null);
    }

    public Note(){
        // Required for firebase
    }
    //endregion

    public static void sendNote(Activity context, Note note) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, note.getName());
            shareIntent.putExtra(Intent.EXTRA_TEXT, note.getText());
            context.startActivity(Intent.createChooser(shareIntent, "Share with..."));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //region Getters & Setters
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
    //endregion
}
