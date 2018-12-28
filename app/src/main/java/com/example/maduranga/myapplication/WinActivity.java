package com.example.maduranga.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        Intent intent = getIntent();
        String message =
                intent.getStringExtra(Constants.EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.text_message);
        textView.setText(message);
    }

//    @Override
//    public void onBackPressed() {
//        final DrawView view = findViewById(R.id.myview);
//        view.init();
//        view.invalidate();
//        super.onBackPressed();
//    }
}
