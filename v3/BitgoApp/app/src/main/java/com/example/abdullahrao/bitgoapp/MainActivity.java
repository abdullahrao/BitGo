package com.example.abdullahrao.bitgoapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdullahrao.bitgoapp.ApiHandler.SharedPreferencesManager;
import com.example.abdullahrao.bitgoapp.CryptoCurrency.Wallet;
import com.example.abdullahrao.bitgoapp.Store.store;

import java.security.Security;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

//    String UserWalletName;
    //CardView card_View = findViewById(R.id.btn_challenge);
    //TextView name1,email1,age1;
    private Button btn_logout;
    Dialog myDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (!SharedPreferencesManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,loginuser.class));
        }
           // loginuser.User = new Wallet();
        //Log.d("TAG1", "User Wallet Creates :" +loginuser.User);

        btn_logout = (Button) findViewById(R.id.btn_logout);



        btn_logout.setOnClickListener(this);
       // To hide action bar on specific activity
        getSupportActionBar().hide();
        // card_View.setOnClickListener(this);
        findViewById(R.id.btn_store).setOnClickListener(this);
        findViewById(R.id.btn_challenge).setOnClickListener(this);
        findViewById(R.id.btn_wallet).setOnClickListener(this);
        findViewById(R.id.btn_changepass).setOnClickListener(this);
        myDialog = new Dialog(this);
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
        if (v.getId() == R.id.btn_wallet){

          // String UserWalletName = SharedPreferencesManager.getInstance(this).getUser_Email().toString();

            //HashMap<String,Wallet> User = new LinkedHashMap<>();
            //User.put(UserWalletName,new Wallet());
            //Wallet us = User.get(UserWalletName);
            //Log.d("TAG1", "User Wallet Creates :" +us);


           // Log.d("TAG1", "\nUser's balance is: " + loginuser.User.calculateBalance());
            //Log.d("TAG1", "\nUser's balance is: " + us.calculateBalance());
            TextView txtclose,txtnameUser,txtcoins;
            double userbalance =  loginuser.User.calculateBalance();
            myDialog.setContentView(R.layout.custompopup);
            txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
            txtnameUser = (TextView) myDialog.findViewById(R.id.txt_nameofuser);
            txtcoins = (TextView) myDialog.findViewById(R.id.txtCoins);
            txtclose.setText("X");
            txtnameUser.setText(SharedPreferencesManager.getInstance(this).getUser_Name());
            //txtcoins.setText(loginuser.User.calculateBalance());
           txtcoins.setText(String.valueOf(userbalance));

            txtclose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.dismiss();
                }
            });
            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            myDialog.show();
        }
        if (v.getId() == R.id.btn_store) {
            Intent intent = new Intent(getApplicationContext(),store.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.btn_changepass) {
            Intent intent = new Intent(getApplicationContext(),passwordChange.class);
            startActivity(intent);
        }
    }
}
