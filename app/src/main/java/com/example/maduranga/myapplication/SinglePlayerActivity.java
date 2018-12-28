package com.example.maduranga.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SinglePlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        final Button button = findViewById(R.id.resetbutton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DrawView myView = findViewById(R.id.myview);
                myView.init();
                myView.invalidate();
            }
        });
    }
}
