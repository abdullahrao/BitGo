package com.example.abdullahrao.bitgoapp.Store;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.abdullahrao.bitgoapp.ApiHandler.RequestHandler;
import com.example.abdullahrao.bitgoapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.abdullahrao.bitgoapp.Constants.constants.UserStore;

public class store extends AppCompatActivity {
    //a List of type hero for holding list items
    List<Hero> heroList;

    //the listview
    ListView listView;

    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        getSupportActionBar().setTitle("Store");
        //initializing objects
        heroList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);

        //adding some values to our list

        //      heroList.add(new Hero(R.drawable.ic_launcher_foreground, "Joker", "Injustice Gang"));
        //    heroList.add(new Hero(R.drawable.ic_launcher_background, "Iron Man", "Avengers"));
        //  heroList.add(new Hero(R.drawable.ic_launcher_background, "Doctor Strange", "Avengers"));
        //heroList.add(new Hero(R.drawable.ic_launcher_background, "Captain America", "Avengers"));
        //heroList.add(new Hero(R.drawable.ic_launcher_background, "Batman", "Justice League"));

        //creating the adapter
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Store Please Wait . . ");
         progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, UserStore, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                      progressDialog.dismiss();

                try {
                    JSONArray dataitems = new JSONArray(response);

                    for (int i = 0; i < dataitems.length(); i++) {
                        JSONObject dataobj = dataitems.getJSONObject(i);

                        //Picasso a =   ;
                        //RequestCreator a = Picasso.with(getApplicationContext()).load(dataobj.getString("image"));
                        heroList.add(new Hero(dataobj.getString("image"), dataobj.getString("name"),String.valueOf(dataobj.getInt("price"))));
                        //
                        //

                    }
                    MyListAdapter adapter = new MyListAdapter(getApplicationContext(), R.layout.my_custom_list, heroList);

                    //attaching adapter to the listview
                    listView.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                     progressDialog.hide();
                Toast.makeText(getApplicationContext(), "Request Failed" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        //heroList.add(new Hero(R.drawable.ic_launcher_background, "Spiderman", "Avengers"));

    }
}
