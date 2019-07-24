package com.example.nobroke;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyleview;
    RecyclerView.Adapter adapter;
    List<DataModel> dataModels;
    String URL="https://demo5269675.mockable.io/getProperties";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyleview= findViewById(R.id.recyleview);
        recyleview.setHasFixedSize(true);
        recyleview.setLayoutManager(new LinearLayoutManager(this ));
        dataModels=new ArrayList<>();
        loadRecylerData();

    }
    private void loadRecylerData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data");
        progressDialog.show();

        StringRequest stringRequest =   new StringRequest(Request.Method.GET,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i=0; i < jsonArray.length();i++)
                            {
                                JSONObject o = jsonArray.getJSONObject(i);
                                DataModel model = new DataModel(o.getString("rent"),
                                        o.getString("propertySize"),
                                        o.getString("propertyTitle")
                                );
                                dataModels.add(model);
                            }
                            adapter = new DataAdapter(getApplicationContext(), dataModels);
                            recyleview.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
