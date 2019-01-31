package com.bodovix.tutorial3applifecycle;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button startTimerBtn;
    Button dialNumberBtn;
    Button openBrowserBtn;
    Button showMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText1);
        startTimerBtn = findViewById(R.id.startTimerBtn);
        dialNumberBtn = findViewById(R.id.dialNumberBtn);
        openBrowserBtn = findViewById(R.id.openBrowserBtn);
        showMap = findViewById(R.id.showMapBtn);
    }

    public void startTimer_Click(View view) {
        Intent intent = new Intent(MainActivity.this,TimerActivity.class);
        intent.putExtra("timerStart",System.currentTimeMillis());
        intent.putExtra("durationSeconds",Integer.parseInt(editText.getText().toString()));
        startActivity(intent);
    }

    public void dialNumber_Click(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + editText.getText()));
        startActivity(intent);
    }

    public void openBrowser_Click(View view) {
        Uri webPage = Uri.parse("http://www.abertay.ac.uk");
        Intent webIntent = new Intent(Intent.ACTION_VIEW,webPage);
        startActivity(webIntent);
    }

    public void showMap_Click(View view) {
        // Map point based on address
        Uri location = Uri.parse("geo:0,0?q=Abertay+University,Dundee,UK");
        // Or map point based on latitude/longitude
        // z param is zoom level
        //Uri location = Uri.parse("geo:56.4633099,-2.9761057?z=16");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
        startActivity(mapIntent);
    }
}
