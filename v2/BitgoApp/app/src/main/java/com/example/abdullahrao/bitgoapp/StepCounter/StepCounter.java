package com.example.abdullahrao.bitgoapp.StepCounter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.abdullahrao.bitgoapp.ApiHandler.RequestHandler;
import com.example.abdullahrao.bitgoapp.ApiHandler.SharedPreferencesManager;
import com.example.abdullahrao.bitgoapp.DailyChallenge;
import com.example.abdullahrao.bitgoapp.MainActivity;
import com.example.abdullahrao.bitgoapp.R;
import com.example.abdullahrao.bitgoapp.loginuser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.abdullahrao.bitgoapp.Constants.constants.UserChallenge_Complete;


public class StepCounter extends AppCompatActivity implements SensorEventListener, StepListener {
    int TotalStepsPause;
    boolean isPlaying = true;
    private TextView textView;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final int TEXT_NUM_STEPS =  DailyChallenge.stepTotalCount;
    private int numSteps;
    TextView TvSteps;
    Button BtnStart;
    private ProgressDialog progressDialog;
    ProgressBar mProgress;
    String challengestatus,challengeId,userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);

        getSupportActionBar().hide();

        if (!SharedPreferencesManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,loginuser.class));
        }

        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);


        Resources res = getResources();
        final Drawable drawable = res.getDrawable(R.drawable.circular);
        mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);
        mProgress.setProgress(0);   // Main Progress
        mProgress.setSecondaryProgress(100); // Secondary Progress
        mProgress.setMax(TEXT_NUM_STEPS); // Maximum Progress
        mProgress.setProgressDrawable(drawable);


        TvSteps = (TextView) findViewById(R.id.tv);
        BtnStart = (Button) findViewById(R.id.btn_start);
        progressDialog = new ProgressDialog(this);

        TvSteps.setText(TEXT_NUM_STEPS+" / 0");
        numSteps = 0;
        sensorManager.registerListener(StepCounter.this, accel, SensorManager.SENSOR_DELAY_FASTEST);




        BtnStart.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {

                if (isPlaying == true) {
                    TotalStepsPause = numSteps;
                    sensorManager.unregisterListener(StepCounter.this);
                    isPlaying = false;
                    BtnStart.setBackground(getDrawable(R.drawable.ic_play_arrow_black_24dp));
                    //mProgress.setProgress(numSteps);
                    //TvSteps.setText(numSteps+ "%");
                }

                else{

                    numSteps = TotalStepsPause;
                    sensorManager.registerListener(StepCounter.this, accel, SensorManager.SENSOR_DELAY_FASTEST);
                    isPlaying = true;
                    BtnStart.setBackground(getDrawable(R.drawable.ic_pause_circle_filled_black_24dp));
                }


            }
        });



    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        TvSteps.setText(TEXT_NUM_STEPS+" / "+ numSteps);
        mProgress.setProgress(numSteps);
        if (TEXT_NUM_STEPS == numSteps){


           ChallengeCompleteStatus();

            finishAffinity();

            Intent i = new Intent(StepCounter.this,MainActivity.class);
            startActivity(i);


        }
    }
        private void ChallengeCompleteStatus(){
            userId = SharedPreferencesManager.getInstance(this).getUser_ID().toString();
            challengestatus = "UnComplete";
            //challengeId
            progressDialog.setMessage("Challenge Complete . . ");
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, UserChallenge_Complete, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    progressDialog.dismiss();

                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        Toast.makeText(StepCounter.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

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
                    params.put("userchallenge_Status",challengestatus);
                    params.put("challenge_Id",DailyChallenge.USER_CHALLENGE_ID.toString());
                    params.put("user_Id",userId);
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

        }
}
