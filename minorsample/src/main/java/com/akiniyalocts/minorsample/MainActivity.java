package com.akiniyalocts.minorsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.akiniyalocts.minor.MinorView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private MainAdapter mainAdapter;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        initMinor();

        initRecycler();
    }

    private void initMinor() {
        final MinorView home = (MinorView)findViewById(R.id.last_home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home.selected();
            }
        });

        home.addNotifcation(87);

        final MinorView lists = (MinorView)findViewById(R.id.lists);

        lists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initRecycler() {
        recyclerView = (RecyclerView)findViewById(R.id.recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mainAdapter = new MainAdapter(this);

        recyclerView.setAdapter(mainAdapter);
    }
}
