package com.brandonbahret.simplenote;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.brandonbahret.simplenote.databinding.ActivityMainBinding;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int RC_LOGIN = 0x010;

    //region Member attributes
    private ActivityMainBinding ui;
    private ArrayList<Note> mNotes;
    private NoteAdapter mNoteAdapter;

    private String mUserID;

    private FirebaseAuth mFireAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseDatabase mFireDatabase;
    private DatabaseReference mFireNotesRef;
    //endregion

    //region Methods for handling the activity's lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(ui.toolbar);

        //INIT: Firebase components
        mFireAuth = FirebaseAuth.getInstance();
        mFireDatabase = FirebaseDatabase.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    String userID = user.getUid();
                    onSignInInit(userID);
                }
                else {
                    // User is signed out
                    onSignOutCleanUp();
                    startSignInActivity();
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFireAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener != null) {
            mFireAuth.removeAuthStateListener(mAuthStateListener);
        }
//        detachDatabaseReadListener();
//        mMessageAdapter.clear();
    }

    private void onSignOutCleanUp() {

    }

    private void onSignInInit(String userID) {
        mUserID = userID;
        String userPath = "users/" + userID + "/notes";
        mFireNotesRef = mFireDatabase.getReference().child(userPath);

        mNotes = new ArrayList<>();
        mNoteAdapter = new NoteAdapter(this, mUserID, mNotes);

        ui.content.noteContainer.setItemAnimator(new DefaultItemAnimator());
        ui.content.noteContainer.setLayoutManager(new LinearLayoutManager(this));
        ui.content.noteContainer.setAdapter(mNoteAdapter);

        ui.newNoteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NoteEditorActivity.startActivity(MainActivity.this, mUserID);
            }
        });
    }

    private void startSignInActivity() {List<AuthUI.IdpConfig> authProviders = Arrays.asList(
            new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
            new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()
    );

        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setTheme(R.style.AppThemeFirebase)
                        .setLogo(R.drawable.ic_file_notes_document)
                        .setIsSmartLockEnabled(false)
                        .setAvailableProviders(authProviders)
                        .build(),
                RC_LOGIN
        );
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_LOGIN && resultCode == RESULT_OK){
            if (resultCode == RESULT_OK) {

            }
            else {
                finish();
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            // log out from firebase
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                            // user is now signed out
                            startSignInActivity();
                        }
                    });

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //endregion -- end --

}
