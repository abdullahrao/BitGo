package com.example.abdullahrao.bitgoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdullahrao.bitgoapp.ApiHandler.SharedPreferencesManager;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    //CardView card_View = findViewById(R.id.btn_challenge);
    //TextView name1,email1,age1;
    private Button btn_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!SharedPreferencesManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,loginuser.class));
        }
        btn_logout = (Button) findViewById(R.id.btn_logout);

        btn_logout.setOnClickListener(this);
       // To hide action bar on specific activity
        getSupportActionBar().hide();
        // card_View.setOnClickListener(this);
        findViewById(R.id.btn_challenge).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_challenge) {
            //Toast.makeText(getApplicationContext(), "Clicked Challenge", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),DailyChallenge.class);
            startActivity(intent);
            //startActivity(new Intent(getApplicationContext(),DailyChallenge.class));
        }
        if (v == btn_logout){
            SharedPreferencesManager.getInstance(this).User_Logout();
            finish();
            startActivity(new Intent(this,loginuser.class));
        }

    }
}
