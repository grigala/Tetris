package com.unibas.grigala.tetris;

/**
 * Created by Grigala on 6/4/13.
 */

public class Profile {
    private static Profile instance = null;
    private static String mName;
    private static String mId;
    private static int mLastDifficult;
    public static final int DEFAULT_DIFFICULT = 4;//Default difficult of the game
    private void Profile() {
        this.mName = "Giorgi";
        this.mId = "1";
        mLastDifficult = DEFAULT_DIFFICULT;
    }
    public Profile getInstance() {
        if(instance == null) {
            instance = new Profile();
        };
        return instance;
    }
}
