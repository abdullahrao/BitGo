package com.example.abdullahrao.bitgoapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.abdullahrao.bitgoapp.ApiHandler.RequestHandler;
import com.example.abdullahrao.bitgoapp.ApiHandler.SharedPreferencesManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.abdullahrao.bitgoapp.Constants.constants.ChangePwd;

public class passwordChange extends AppCompatActivity implements View.OnClickListener {

    EditText txt_OldPass,txt_NewPass,txt_ConfPass;
    Button btn_ChangePass;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);

        txt_OldPass =(EditText) findViewById(R.id.txt_OldPass);
        txt_NewPass =(EditText) findViewById(R.id.txt_NewPass);
        txt_ConfPass =(EditText) findViewById(R.id.txt_ConPass);

        btn_ChangePass = (Button) findViewById(R.id.btn_ChangePassword);

        progressDialog = new ProgressDialog(this);
        btn_ChangePass.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        if (v == btn_ChangePass){
            final String oldpass = txt_OldPass.getText().toString();
            final String newpass = txt_NewPass.getText().toString();
            final String confpass = txt_ConfPass.getText().toString();
           final String userid = SharedPreferencesManager.getInstance(getApplicationContext()).getUser_ID().toString();

            progressDialog.setMessage("Loading Please Wait!");
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, ChangePwd, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                progressDialog.dismiss();

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();

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
                    params.put("user_id",userid);
                    params.put("user_password",oldpass);
                    params.put("New_password", newpass);
                    params.put("Conf_password",confpass);
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }

    }

}
