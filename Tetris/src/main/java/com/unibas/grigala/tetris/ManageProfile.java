package com.unibas.grigala.tetris;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;

/**
 * Created by Grigala on 6/4/13.
 */

public class ManageProfile extends ListActivity{
    private final ListActivity me = this;
    private ListAdapter mAdapter = null;
    Button newProfile;
    Button selectProfile;
    Button deleteProfile;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_profile);

        Button newProfile = (Button)findViewById(R.id.btn_manage_new);

        newProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intt = new Intent(me, TetrisBlastActivity.class);
                startActivity(intt);
            }
        });
        /**
        selectProfile.setOnClickListener((v)->{
            Intent intt = new Intent(me, SelectProfile.class);
            startActivity(intt);
        });

        deleteProfile.setOnClickListener((v)->{
            Intent intt = new Intent(me, DeleteProfile.class);
            startActivity(intt);
        });
        **/

    }

}
