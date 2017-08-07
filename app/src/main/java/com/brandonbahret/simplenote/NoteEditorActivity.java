package com.brandonbahret.simplenote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class NoteEditorActivity extends AppCompatActivity {

    public static final int RC_NOTE = 0x001;
    public static final String NOTE_KEY = "NOTE";
    private EditText mNoteField;
    private EditText mNoteTitle;
    private Note mNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        mNoteField = (EditText)findViewById(R.id.note_field);
        mNoteTitle = (EditText)findViewById(R.id.note_title);
        mNoteField.requestFocus();

        Intent data = getIntent();
        if(data.hasExtra(NOTE_KEY)){
            mNote = (Note) data.getSerializableExtra(NOTE_KEY);
            mNoteField.setText(mNote.getText());
            mNoteTitle.setText(mNote.getName());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.note_editor_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case(R.id.action_delete):{
                //TODO: Delete the note from the database
            }break;

            case(R.id.action_save):{
                //TODO: Save note in the database
            }break;

            case(R.id.action_send):{
                //TODO: Send note
            }break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void startActivity(AppCompatActivity context){
        Intent launchIntent = new Intent(context, NoteEditorActivity.class);
        context.startActivityForResult(launchIntent, RC_NOTE);
    }

    public static void startActivity(AppCompatActivity context, Note note){
        Intent launchIntent = new Intent(context, NoteEditorActivity.class);
        launchIntent.putExtra("NOTE", note);
        context.startActivityForResult(launchIntent, RC_NOTE);
    }
}
