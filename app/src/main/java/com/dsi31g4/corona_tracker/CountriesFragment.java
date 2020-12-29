package com.dsi31g4.corona_tracker;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;


public class CountriesFragment extends Fragment {
    TextView casedeathNumber,recoveredNumber,deathNumber;
    PieChart pieChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_countrier, container, false);
        casedeathNumber = root.findViewById(R.id.casedeathNumber);
        recoveredNumber = root.findViewById(R.id.recoveredNumber);
        deathNumber = root.findViewById(R.id.deathNumber);
        pieChart = root.findViewById(R.id.piechart);
        getData();
return root;

    }
    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        String url = "https://disease.sh/v3/covid-19/all";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jsonObject = new JSONObject(response.toString());

                    casedeathNumber.setText(jsonObject.getString("cases"));
                    deathNumber.setText(jsonObject.getString("deaths"));
                    recoveredNumber.setText(jsonObject.getString("recovered"));
                    pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(casedeathNumber.getText().toString()), Color.parseColor("#EF5350")));
                    pieChart.addPieSlice(new PieModel("Recoverd",Integer.parseInt(recoveredNumber.getText().toString()), Color.parseColor("#66BB6A")));
                    pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(deathNumber.getText().toString()), Color.parseColor("#272829")));
                    pieChart.startAnimation();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Error Response", error.toString());
            }
        });

        queue.add(stringRequest);
    }
}