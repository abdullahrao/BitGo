package com.example.abdullahrao.bitgoapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abdullahrao.bitgoapp.ApiHandler.RequestHandler;
import com.example.abdullahrao.bitgoapp.ApiHandler.SharedPreferencesManager;
import com.example.abdullahrao.bitgoapp.StepCounter.StepCounter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.abdullahrao.bitgoapp.Constants.constants.DataItem_URL;


import static com.example.abdullahrao.bitgoapp.Constants.constants.userTake_Challenge;

public class DailyChallenge extends AppCompatActivity implements View.OnClickListener {


  private TextView Txtcount_steps ,Txt_ExpDate,Txt_Mxg,Txt_reward,name12,email12,age12,id12;
  private Button btn_StartChallenge;
  private ImageView imgRun,imgDollar;
  private ProgressDialog progressDialog;
  public static Integer USER_CHALLENGE_ID;
  public static int stepTotalCount ;
  public static Integer rewardPrice;
   //int id2;
   // public static final String DataItem_URL = "http://192.168.10.2:82/adminpanel/MyApi/selectapi.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_challenge);

        //getSupportActionBar().hide();

        if (!SharedPreferencesManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,loginuser.class));
        }

         Txtcount_steps =(TextView) findViewById(R.id.txtsteps);
        Txt_reward =(TextView) findViewById(R.id.txtreward);
        Txt_ExpDate = (TextView) findViewById(R.id.txtexpdate);
        Txt_Mxg = (TextView) findViewById(R.id.mxg);
        btn_StartChallenge = (Button) findViewById(R.id.btn_StartChallenge);
        imgRun = (ImageView) findViewById(R.id.imgRun);
        imgDollar = (ImageView) findViewById(R.id.imgDollar);


         progressDialog = new ProgressDialog(this);

        id12 = (TextView) findViewById(R.id.userid12);
        name12 = (TextView) findViewById(R.id.username12);
        email12 = (TextView) findViewById(R.id.useremail12);
       age12 = (TextView) findViewById(R.id.userage12);

        //id12.setText(SharedPreferencesManager.getInstance(this).getUser_ID().toString());
       //name12.setText(SharedPreferencesManager.getInstance(this).getUser_Name());
        //email12.setText(SharedPreferencesManager.getInstance(this).getUser_Email());
        //age12.setText(SharedPreferencesManager.getInstance(this).getUser_Age().toString());


        btn_StartChallenge.setOnClickListener(this);
       loaddata();
       // Txt_reward = rewardPrice;
    }
    final String user_ID = SharedPreferencesManager.getInstance(this).getUser_ID().toString();

    private void loaddata() {

        progressDialog.setMessage("Loading Please Wait . . ");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, DataItem_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {
                    JSONArray dataitems = new JSONArray(response);
                    if(dataitems.length() == 0 ){
                        Txt_Mxg.setText("No Challenge Available");
                        btn_StartChallenge.setVisibility(View.GONE);
                        imgRun.setVisibility(View.GONE);
                        imgDollar.setVisibility(View.GONE);
                    }else {
                        for (int i = 0; i < dataitems.length(); i++) {
                            JSONObject dataobj = dataitems.getJSONObject(i);

                            //  String id = dataobj.getString("Challenge_id");
                            // String ageFrom = dataobj.getString("ageFrom");
                            //String ageTo = dataobj.getString("ageTo");
                            //String Reward = dataobj.getString("Reward");


                            //id1.setText(id);
                            //agefrom1.setText(ageFrom);
                            //ageto1.setText(ageTo);
                            //reward1.setText(Reward);

                            //id1.setText(dataobj.getString("Challenge_id"));
                            //agefrom1.setText(dataobj.getString("ageFrom"));
                            //ageto1.setText(dataobj.getString("ageTo"));
                            //reward1.setText(dataobj.getString("Reward"));
                            USER_CHALLENGE_ID = dataobj.getInt("Challenge_id");
                            Txtcount_steps.setText(dataobj.getString("No_Steps"));
                            Txt_reward.setText(dataobj.getString("Reward"));
                            Txt_ExpDate.setText(dataobj.getString("Challenge_ExpireDate"));
                             stepTotalCount = Integer.parseInt(dataobj.getString("No_Steps"));
                             //reward price static variable
                             rewardPrice =Integer.parseInt(dataobj.getString("Reward"));
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(),"Request Failed"+error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


        //Volley.newRequestQueue(this).add(stringRequest);
        //More efficient way Singleton pattern
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }


    @Override
    public void onClick(View v) {

        if (v == btn_StartChallenge)
        {
            //finishAffinity();
            userChallengeStart();
            Intent i = new Intent(DailyChallenge.this,StepCounter.class);
            startActivity(i);

        }
    }

    private void userChallengeStart() {

        final String word = "Challenge Completed Already";
        progressDialog.setMessage("Loading Please Wait . . ");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, userTake_Challenge, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                        Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();

        //            String word1 =jsonObject.getString("message");
          //          if (word1 == word){
            //         startActivity(new Intent(DailyChallenge.this,MainActivity.class));
              //       finish();
                //    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            progressDialog.hide();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> params = new HashMap<>();
            params.put("user_Id",user_ID);
            params.put("challenge_Id",USER_CHALLENGE_ID.toString());
            return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

}
