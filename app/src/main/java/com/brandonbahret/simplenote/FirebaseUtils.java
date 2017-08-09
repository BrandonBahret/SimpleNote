package com.brandonbahret.simplenote;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Brandon on 8/8/2017.
 */

public class FirebaseUtils {
    private static FirebaseDatabase mDatabase;

    public static FirebaseDatabase getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
        }
        return mDatabase;
    }
}
