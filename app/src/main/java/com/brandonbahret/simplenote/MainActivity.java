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

    //region Member attributes
    private ActivityMainBinding ui;
    private ArrayList<Note> mNotes;
    private NoteAdapter mNoteAdapter;
    //endregion

    //region Methods for handling the activity's lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(ui.toolbar);

        mNotes = new ArrayList<>();
        String lorem = getString(R.string.lorem);
        mNotes.add(new Note("Title 1", lorem));
        mNotes.add(new Note("Title 2", lorem));
        mNotes.add(new Note("Title 3", lorem));
        mNotes.add(new Note("Title 4", lorem));
        mNoteAdapter = new NoteAdapter(this, mNotes);

        ui.content.noteContainer.setItemAnimator(new DefaultItemAnimator());
        ui.content.noteContainer.setLayoutManager(new LinearLayoutManager(this));
        ui.content.noteContainer.setAdapter(mNoteAdapter);

        ui.newNoteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
    //endregion -- end --

    //region Methods for handling events
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            //TODO: log out from firebase
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //endregion -- end --

}
