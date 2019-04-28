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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abdullahrao.bitgoapp.ApiHandler.RequestHandler;
import com.example.abdullahrao.bitgoapp.ApiHandler.SharedPreferencesManager;
import com.example.abdullahrao.bitgoapp.Constants.constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUsername,editTextEmail,editTextPassword,editTextAge;
    private Button buttonRegister;
    private ProgressDialog progressDialog;
    private TextView txtViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        if (SharedPreferencesManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,MainActivity.class));
            return;
        }

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextAge= (EditText) findViewById(R.id.editTextAge);

        txtViewLogin = (TextView) findViewById(R.id.textViewLogin);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        progressDialog = new ProgressDialog(this);

        buttonRegister.setOnClickListener(this);
        txtViewLogin.setOnClickListener(this);


    }
    private void registerUser(){

        final String username = editTextUsername.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String age = editTextAge.getText().toString().trim();

        progressDialog.setMessage("Registering User . . !!");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, constants.signup_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                    //if (jsonObject.getString("message") == "User registered successfully"){
                       // startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    //}
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
                params.put("user_name",username);
                params.put("user_email",email);
                params.put("user_password",password);
                params.put("user_age",age);
                return params;
            }
        };
      //  RequestQueue requestQueue = Volley.newRequestQueue(this);
        //requestQueue.add(stringRequest);
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
    @Override
    public void onClick(View v) {

        if (v == buttonRegister){
            registerUser();
        }
        if (v == txtViewLogin){
            startActivity(new Intent(this,loginuser.class));
        }
    }
}
