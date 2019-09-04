package com.example.abdullahrao.bitgoapp.Store;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.abdullahrao.bitgoapp.ApiHandler.RequestHandler;
import com.example.abdullahrao.bitgoapp.ApiHandler.SharedPreferencesManager;
import com.example.abdullahrao.bitgoapp.CryptoCurrency.Block;
import com.example.abdullahrao.bitgoapp.CryptoCurrency.Wallet;
import com.example.abdullahrao.bitgoapp.R;
import com.example.abdullahrao.bitgoapp.StepCounter.StepCounter;
import com.example.abdullahrao.bitgoapp.loginuser;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.abdullahrao.bitgoapp.Constants.constants.OrderInsert;

//we need to extend the ArrayAdapter class as we are building an adapter
public class MyListAdapter extends ArrayAdapter<Hero> {

    //the list values in the List of type hero
    List<Hero> heroList;

    //activity context
    Context context;

    //the layout resource file for the list items
    int resource;

    //constructor initializing the values
    public MyListAdapter(Context context, int resource, List<Hero> heroList) {
        super(context, resource, heroList);
        this.context = context;
        this.resource = resource;
        this.heroList = heroList;
    }

    //this will return the ListView Item as a View
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(resource, null, false);

        //getting the view elements of the list from the view
        ImageView imageView = view.findViewById(R.id.imageView);
        final TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewTeam = view.findViewById(R.id.textViewTeam);
        Button buttonDelete = view.findViewById(R.id.buttonDelete);

        //getting the hero of the specified position
        final Hero hero = heroList.get(position);

        //RequestCreator hero.getImage();
        //adding values to the list item
        //imageView.setImageDrawable(context.getResources().getDrawable( hero.getImage()));
        //imageView.setImageBitmap(hero.getImage());
        //Picasso.with(getContext()).load(String.valueOf(hero.image)).into(imageView);
        //Picasso.with(context).
       // imageView.setImageURI(hero.image);
        //Picasso.LoadedFrom.valueOf(String.valueOf(hero.image));
       // imageView.setImageDrawable();
        //imageView.setImageBitmap(RequestCreator.class.cast(hero.image));
       // imageView.setImageDrawable(Picasso.with(context).load("").into((Target) hero.getImage()));
        textViewName.setText(hero.getName());
        textViewTeam.setText(hero.getTeam());
        Picasso.with(context).load(hero.getImage()).into(imageView);


        //adding a click listener to the button to remove item from the list
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double userbalance =  loginuser.User.calculateBalance();
                if (Double.parseDouble(hero.getTeam()) <= userbalance){

                    Block block2 = new Block(StepCounter.block1.getHash());
                   // Wallet vendor = new Wallet();

                    block2.addTransaction(loginuser.User.transferMoney(loginuser.vendor.getPublicKey(),Double.parseDouble(hero.getTeam())));
                    loginuser.miner.mine(block2,loginuser.chain);
                    Toast.makeText(getContext(),"Thank You!! Will be Delivered To Your Address :-)",Toast.LENGTH_LONG).show();
                    Log.d("TAG1", "\nVendor's balance is: " + loginuser.vendor.calculateBalance());
                    Log.d("TAG1", "\nuserA's balance is: " + loginuser.User.calculateBalance());
                    Log.d("TAG1", "Miner's reward: "+loginuser.miner.getReward());

                    final String userid = SharedPreferencesManager.getInstance(getContext()).getUser_ID().toString();
                    final String useraddress = SharedPreferencesManager.getInstance(getContext()).getUser_Age();
                    final String usermail = SharedPreferencesManager.getInstance(getContext()).getUser_Email();
                    final String pname = textViewName.getText().toString();

                    //String Request to insert data
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, OrderInsert, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<>();
                            params.put("user_id",userid);
                            params.put("address",useraddress);
                            params.put("pname",usermail);
                            params.put("email",pname);
                            return params;
                        }
                    };
                    RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

                    //we will call this method to remove the selected value from the list
                    //we are passing the position which is to be removed in the method
                    removeHero(position);
                    notifyDataSetChanged();


                }else{
                    Toast.makeText(getContext(),"Sorry You have Not Enough Coins!",Toast.LENGTH_LONG).show();
                }

            }
        });
        return view;

    }

    //this method will remove the item from the list
    private void removeHero(final int position) {
        //Creating an alert dialog to confirm the deletion
       // AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //builder.setTitle("Are you sure you want to Buy this?");

        //if the response is positive in the alert
        //builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
          //  @Override
            //public void onClick(DialogInterface dialogInterface, int i) {

                //removing the item
                 heroList.remove(position);

                //reloading the list
     //           notifyDataSetChanged();
            //}
        //});

        //if response is negative nothing is being done
        //builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
          //  @Override
            //public void onClick(DialogInterface dialogInterface, int i) {

            //}
        //});

        //creating and displaying the alert dialog
        //AlertDialog alertDialog = builder.create();
        //alertDialog.show();
    }

}
