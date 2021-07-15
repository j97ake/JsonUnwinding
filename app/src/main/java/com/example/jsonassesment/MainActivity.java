package com.example.jsonassesment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;

import static com.android.volley.Request.*;

public class MainActivity extends AppCompatActivity {

    String url = "https://followchess.com/config.json";
    RecyclerView recyclerView;
    AssesmentAdapter adapter;
    ArrayList<Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.RecyclerView);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            recyclerView.setLayoutManager(new LinearLayoutManager((this)));
            getPortraitData();
        }
        else
        {
            recyclerView.setLayoutManager(new GridLayoutManager((this),2));
            getLandScapeData();
        }

        adapter = new AssesmentAdapter();
        recyclerView.setAdapter(adapter);
        list = new ArrayList<>();


    }

    private void getPortraitData() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ");
        progressDialog.show();


       JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET ,url, null,new Response.Listener<JSONObject>() {
           @Override
           public void onResponse(JSONObject response) {
               try {
                   JSONArray jsonArray = response.getJSONArray("trns");
                   for(int i=0;i<jsonArray.length();i++)
                   {
                       String dash ;
                       JSONObject jsonObject = jsonArray.getJSONObject(i);
                       Model model = new Model();
                       String images = jsonObject.optString("img");

                        //In case if image data is not available in json

                           if(images == null || images == "")
                           {
                               model.setImage(R.drawable.ic_launcher_foreground);
                           }
                           else
                           {
                               model.setImage(jsonObject.getString("img"));
                           }

                           model.setName(jsonObject.getString("name"));
                           model.setSlug(jsonObject.getString("slug"));

                           dash = jsonObject.getString("slug");
                           model.setDash(countDash(dash));
                           model.setDate("2018");
                           list.add(model);


                   }

               } catch (JSONException e) {

                   e.printStackTrace();
               }
               adapter.setList(list);
               adapter.notifyDataSetChanged();
               progressDialog.dismiss();


           }
       },new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

               progressDialog.dismiss();
               Toast.makeText(MainActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
           }
       });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }



    private void getLandScapeData() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ");
        progressDialog.show();


        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET ,url, null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("trns");
                    for(int i=0;i<jsonArray.length();i++)
                    {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Model model = new Model();
                        String images = jsonObject.optString("img");

                        //In case if image data is not available in json

                        if(images == null || images == "")
                        {
                            model.setImage(R.drawable.ic_launcher_foreground);
                        }
                        else
                        {
                            model.setImage(jsonObject.getString("img"));
                        }

                        model.setName(jsonObject.getString("name"));
                        model.setSlug(jsonObject.getString("slug"));

                        list.add(model);
                    }

                } catch (JSONException e) {

                    e.printStackTrace();
                }
                adapter.setList(list);
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();


            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request1);
    }

    private String  countDash(String dash)
    {
       int count = 0;
       String s="";
       for(int i=0;i<dash.length();i++)
       {
           if(dash.charAt(i) == '-')
           {
                 count++;
           }
       }
          s= Integer.toString(count);
       return s;
    }

}