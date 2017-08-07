package com.brandonbahret.simplenote;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.brandonbahret.simplenote.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding ui;
    private ArrayList<Note> mNotes;
    private NoteAdapter mNoteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(ui.toolbar);

        mNotes = new ArrayList<>();
        mNotes.add(new Note("one", "hello world"));
        mNotes.add(new Note("two", "hello world"));
        mNotes.add(new Note("three", "hello world"));
        mNotes.add(new Note("four", "hello world"));
        mNoteAdapter = new NoteAdapter(this, mNotes);

        ui.content.noteContainer.setItemAnimator(new DefaultItemAnimator());
        ui.content.noteContainer.setLayoutManager(new LinearLayoutManager(this));
        ui.content.noteContainer.setAdapter(mNoteAdapter);

        ui.newNoteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Open note editor activity
                NoteEditorActivity.startActivity(MainActivity.this);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            //TODO: log out from firebase
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
