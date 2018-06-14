package com.vedangbhardwaj.interviewpreperation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.Toolbar;
import android.content.DialogInterface;
import android.content.Intent;
import com.google.firebase.auth.FirebaseAuth;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterClass.onItemClickListener{

    private RecyclerView mrecycle;
    private AdapterClass mAdapter;
    private ArrayList<ItemClass> mList;
    private RequestQueue mRequest;

    public static final String ExtraHeading = "question";
    public static final String Ans = "Answer";
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setLogo(R.drawable.logotitle);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        mrecycle = findViewById(R.id.recycler_view);
        mrecycle.setHasFixedSize(true);
        mrecycle.setLayoutManager(new LinearLayoutManager(this));

        mList = new ArrayList<>();
        mRequest = Volley.newRequestQueue(this);
        parseJson();

    }

    private void setSupportActionBar(Toolbar toolbar) {
    }


    public void parseJson(){
        String url = "https://learncodeonline.in/api/android/datastructure.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("questions");

                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject q = jsonArray.getJSONObject(i);

                                String q1 = q.getString("question");
                                String a1 = q.getString("Answer");

                                //now adding the fecthed data to the ITEM LISt
                                mList.add(new ItemClass(q1,a1));
                            }

                            //now adding all the elements to the adapter
                            mAdapter = new AdapterClass(MainActivity.this,mList);
                            mrecycle.setAdapter(mAdapter);
                            mAdapter.setOnItemClickListener(MainActivity.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
            }
        });
        mRequest.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent moreIntent = new Intent(this,MoreActivity.class);
        ItemClass clickitem = mList.get(position);

        moreIntent.putExtra(ExtraHeading,clickitem.getQuestion());
        moreIntent.putExtra(Ans,clickitem.getAnswer());

        startActivity(moreIntent);
    }

    public void onBackPressed() {

        //put the AlertDialog code here
        new android.support.v7.app.AlertDialog.Builder(MainActivity.this)
                .setTitle("Logout")
                .setMessage("Would you like to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // logout
                        Intent ot = new Intent(MainActivity.this, login.class);
                        startActivity(ot);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // user doesn't want to logout
                    }
                })
                .show();
    }
}
