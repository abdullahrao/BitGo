package com.example.abdullahrao.bitgoapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.example.abdullahrao.bitgoapp.CryptoCurrency.Block;
import com.example.abdullahrao.bitgoapp.CryptoCurrency.BlockChain;
import com.example.abdullahrao.bitgoapp.CryptoCurrency.Constants;
import com.example.abdullahrao.bitgoapp.CryptoCurrency.Miner;
import com.example.abdullahrao.bitgoapp.CryptoCurrency.Transaction;
import com.example.abdullahrao.bitgoapp.CryptoCurrency.TransactionOutput;
import com.example.abdullahrao.bitgoapp.CryptoCurrency.Wallet;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.Security;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class loginuser extends AppCompatActivity implements View.OnClickListener {

    //public static String us;
    private EditText txtUser_Name,txt_Pass;
    private Button btnLogin;
    private ProgressDialog progressDialog;
    private TextView txtViewLogin;
    //String UserWalletName = SharedPreferencesManager.getInstance(this).getUser_Email().toString();
    public static Wallet User,OwnerWallet,vendor;
    public static Block genesis;
    public static Miner miner;
    public static BlockChain chain;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginuser);

        getSupportActionBar().hide();

        if (SharedPreferencesManager.getInstance(this).isLoggedIn()){
            finish();
            User = new Wallet();
            Log.d("TAG1", "User Wallet Creates :" +User);

            //
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

           // UserWalletName = SharedPreferencesManager.getInstance(this).getUser_Email().toString();
            //UserBlockName = SharedPreferencesManager.getInstance(this).getUser_Name().toString();

            //Log.d("TAG1", "User Wallet Creates");
            //classobject = "Wallet "+UserWalletName+"= new Wallet()";
            // Log.d("TAG1", "User Wallet Creates"+UserWalletName);
            //classobject.toString();
            // Log.d("TAG1", "User Wallet Creates :"+classobject);

            // Wallet userA = new Wallet();
            //// HashMap<String,Wallet> User = new LinkedHashMap<>();
            ////User.put(UserWalletName,new Wallet());
            ////Wallet us = User.get(UserWalletName);
            ////Log.d("TAG1", "User Wallet Creates :" +us);
            //  Wallet UserWalletName = userA;
//        Wallet userb = new Wallet();
            OwnerWallet = new Wallet();
            vendor = new Wallet();
            Wallet lender = new Wallet();
            chain = new BlockChain();
            miner = new Miner();

            //create genesis transaction that sends 500 coins to Owner:
            Transaction genesisTransaction = new Transaction(lender.getPublicKey(), OwnerWallet.getPublicKey(), 500, null);
            genesisTransaction.generateSignature(lender.getPrivateKey());
            genesisTransaction.setTransactionId("0");
            genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.getReceiver(), genesisTransaction.getAmount(), genesisTransaction.getTransactionId()));
            BlockChain.UTXOs.put(genesisTransaction.outputs.get(0).getId(), genesisTransaction.outputs.get(0));


            //System.out.println("Constructing the genesis block...");

            Log.d("TAG1", "Constructing the genesis block...");
            genesis = new Block(Constants.GENESIS_PREV_HASH);//magical part
            genesis.addTransaction(genesisTransaction);
            miner.mine(genesis,chain);

            //System.out.println("Miner's reward: "+miner.getReward());
            Log.d("TAG1", "Miner's reward: "+miner.getReward());
            //Log.d("TAG1", "\nOwner's balance is: " + Lender.calculateBalance());
            Log.d("TAG1", "\nOwner's balance is: " + OwnerWallet.calculateBalance());



            //

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
                                object.getString("user_age")
                        );
                        //Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_SHORT).show();

                        //HashMap<String,Wallet> User = new LinkedHashMap<>();
                        //User.put(UserWalletName,new Wallet());
                        //Wallet us = User.get(UserWalletName);
                        //Log.d("TAG1", "User Wallet Creates :" +us);
                        User = new Wallet();
                        Log.d("TAG1", "User Wallet Creates :" +User);

                        //
                        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

                        // UserWalletName = SharedPreferencesManager.getInstance(this).getUser_Email().toString();
                        //UserBlockName = SharedPreferencesManager.getInstance(this).getUser_Name().toString();

                        //Log.d("TAG1", "User Wallet Creates");
                        //classobject = "Wallet "+UserWalletName+"= new Wallet()";
                        // Log.d("TAG1", "User Wallet Creates"+UserWalletName);
                        //classobject.toString();
                        // Log.d("TAG1", "User Wallet Creates :"+classobject);

                        // Wallet userA = new Wallet();
                        //// HashMap<String,Wallet> User = new LinkedHashMap<>();
                        ////User.put(UserWalletName,new Wallet());
                        ////Wallet us = User.get(UserWalletName);
                        ////Log.d("TAG1", "User Wallet Creates :" +us);
                        //  Wallet UserWalletName = userA;
//        Wallet userb = new Wallet();
                        OwnerWallet = new Wallet();
                        Wallet lender = new Wallet();
                        chain = new BlockChain();
                        miner = new Miner();

                        //create genesis transaction that sends 500 coins to Owner:
                        Transaction genesisTransaction = new Transaction(lender.getPublicKey(), OwnerWallet.getPublicKey(), 500, null);
                        genesisTransaction.generateSignature(lender.getPrivateKey());
                        genesisTransaction.setTransactionId("0");
                        genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.getReceiver(), genesisTransaction.getAmount(), genesisTransaction.getTransactionId()));
                        BlockChain.UTXOs.put(genesisTransaction.outputs.get(0).getId(), genesisTransaction.outputs.get(0));


                        //System.out.println("Constructing the genesis block...");

                        Log.d("TAG1", "Constructing the genesis block...");
                        genesis = new Block(Constants.GENESIS_PREV_HASH);//magical part
                        genesis.addTransaction(genesisTransaction);
                        miner.mine(genesis,chain);

                        //System.out.println("Miner's reward: "+miner.getReward());
                        Log.d("TAG1", "Miner's reward: "+miner.getReward());
                        //Log.d("TAG1", "\nOwner's balance is: " + Lender.calculateBalance());
                        Log.d("TAG1", "\nOwner's balance is: " + OwnerWallet.calculateBalance());



                        //


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
