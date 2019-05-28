package com.example.abdullahrao.bitgoapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.abdullahrao.bitgoapp.ApiHandler.RequestHandler;
import com.example.abdullahrao.bitgoapp.ApiHandler.SharedPreferencesManager;
import com.example.abdullahrao.bitgoapp.Constants.constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class loginuser extends AppCompatActivity implements View.OnClickListener {

    private EditText txtUser_Name,txt_Pass;
    private Button btnLogin;
    private ProgressDialog progressDialog;
    private TextView txtViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginuser);

        getSupportActionBar().hide();

        if (SharedPreferencesManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,MainActivity.class));
            return;
        }

        txtUser_Name = (EditText) findViewById(R.id.editTextUsername1);
        txt_Pass = (EditText) findViewById(R.id.editTextPassword1);
        btnLogin = (Button) findViewById(R.id.buttonLogin);
        txtViewLogin = (TextView) findViewById(R.id.textViewLogin);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait. . . ");

        btnLogin.setOnClickListener(this);
        txtViewLogin.setOnClickListener(this);
    }
    private void userLogin(){
        final String user_name = txtUser_Name.getText().toString().trim();
        final String user_pass = txt_Pass.getText().toString().trim();

        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, constants.Login_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject object = new JSONObject(response);
                    if (!object.getBoolean("error")){
                        SharedPreferencesManager.getInstance(getApplicationContext()).UserLogin(
                                object.getInt("user_id"),
                                object.getString("user_name"),
                                object.getString("user_email"),
                                object.getInt("user_age")
                        );
                        //Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),object.getString("message"),Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user_name",user_name);
                params.put("user_password",user_pass);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if (v == btnLogin){
            userLogin();
        }
        if (v == txtViewLogin){
            startActivity(new Intent(this,RegisterUser.class));
        }
    }
}
