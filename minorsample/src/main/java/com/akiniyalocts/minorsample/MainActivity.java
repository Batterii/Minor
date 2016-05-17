package com.akiniyalocts.minorsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.akiniyalocts.minor.MinorView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MinorView home = (MinorView)findViewById(R.id.last_home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home.selected();
            }
        });

        home.addNotifcation(8);

        final MinorView lists = (MinorView)findViewById(R.id.lists);

        lists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
