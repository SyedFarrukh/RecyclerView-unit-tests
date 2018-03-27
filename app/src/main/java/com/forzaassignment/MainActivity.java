package com.forzaassignment;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.forzaassignment.Adapter.TeamAdapter;
import com.forzaassignment.Model.TeamModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

   private ArrayList<TeamModel> teamModels = new ArrayList<>();
   private TeamAdapter adapter;
   @BindView(R.id.recycler_view) RecyclerView recyclerView;
    RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        queue = Volley.newRequestQueue(this);
        apiCall();
    }


    public void apiCall(){
        final ProgressDialog loading = ProgressDialog.show(MainActivity.this, "Please wait...","Fetching data from server...",false,false);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TeamAdapter(teamModels, MainActivity.this);
        recyclerView.setAdapter(adapter);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Config.BASE_URL,
                new Response.Listener<JSONArray>()
                {
                    @TargetApi(Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String name = jsonObject.getString("name");
                                String national = jsonObject.getString("national");
                                String country_name = jsonObject.getString("country_name");

                                teamModels.add(new TeamModel(name, national, country_name));
                                loading.dismiss();
                                adapter.notifyDataSetChanged();

                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                loading.dismiss();
                            } finally {
                                adapter.notifyItemChanged(i);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                System.out.println(error.getMessage());
            }
        });
        queue.add(jsonArrayRequest);

    }

}
