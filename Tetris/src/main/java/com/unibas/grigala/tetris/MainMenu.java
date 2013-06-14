package com.unibas.grigala.tetris;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Grigala on 5/11/12.
 */

public class MainMenu extends Activity {
    private final Activity me = this;
    public static final String TAG = "Tetris";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        Button singleBtn = (Button)findViewById(R.id.btn_main_menu_single);
        Button multiBtn = (Button)findViewById(R.id.btn_main_menu_multi);
        Button manageBtn = (Button)findViewById(R.id.btn_main_menu_manage);
        Button settingBtn = (Button)findViewById(R.id.btn_main_menu_settings);
        Button leaderBtn = (Button)findViewById(R.id.btn_main_menu_leader);
        Button helpBtn = (Button)findViewById(R.id.btn_main_menu_help);
        TextView verTxt = (TextView)findViewById(R.id.txt_main_menu_ver);
        verTxt.setText(getString(R.string.version));

        //Button Listenerses
        singleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intt = new Intent(me, NewActivity.class);
                startActivity(intt);
                Log.d(TAG, "Singleplayer button was clicked!");
            }
        });

        multiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intt = new Intent(me, ConnectionActivity.class);
                startActivity(intt);
                Log.d(TAG, "Multiplayer button was clicked!");
            }
        });

        manageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intt = new Intent(me, ManageProfile.class);
                startActivity(intt);
                Log.d(TAG, "Manage button was clicked!");
            }
        });

        /**
        settingBtn.setOnClickListener((v) -> {
                Intent intt = new Intent(me, Settings.class);
                startActivity(intt);
                Log.d(TAG, "Settings button was clicked!");

        });

        leaderBtn.setOnClickListener((v) -> {
            Intent intt = new Intent(me, Leaderboard.class);
            startActivity(intt);
            Log.d(TAG, "Leaderboard button was clicked!");
        });

        helpBtn.setOnClickListener((v) -> {
            Intent intt = new Intent(me, Help.class);
            startActivity(intt);
            Log.d(TAG, "Help button was clicked!");
        });
        **/
    }
}
