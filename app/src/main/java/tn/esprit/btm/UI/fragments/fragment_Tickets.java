package tn.esprit.btm.UI.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

import Entity.Ticket;
import Entity.Transport;
import Entity.User;
import adapter.TicketAdapter;
import adapter.TransportAdapter;
import tn.esprit.btm.R;
import tn.esprit.btm.UI.Helpers.SessionManager;
import tn.esprit.btm.UI.app.AppConfig;


public class fragment_Tickets extends Fragment {

    SessionManager session;


    private RecyclerView recyclerView;
    ArrayList<Ticket> ticketArrayList = new ArrayList<>();

    private TicketAdapter mAdapter;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment__tickets, container, false);
        Button TrainButton = rootView.findViewById(R.id.btn_TrainT);
        Button MetroButton = rootView.findViewById(R.id.btn_MetroT);
        Button BusButton = rootView.findViewById(R.id.btn_BusT);
        Context mContext = getContext();
        session = new SessionManager(mContext);
        recyclerView = rootView.findViewById(R.id.recycler_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        mAdapter = new TicketAdapter(ticketArrayList);

        recyclerView.setAdapter(mAdapter);

        BusButton.setOnClickListener(v -> {






            if (mAdapter.getItemCount() != 0) {
                ticketArrayList.clear();


                fillDataBus();

                mAdapter.notifyDataSetChanged();
            } else {
                fillDataBus();

                mAdapter.notifyDataSetChanged();
            }
        });
        TrainButton.setOnClickListener(v -> {

                    if (mAdapter.getItemCount() != 0) {
                        ticketArrayList.clear();
                        fillDataTrain();
                        mAdapter.notifyDataSetChanged();

                    } else {
                        fillDataTrain();
                        mAdapter.notifyDataSetChanged();

                    }
                }

        );
        MetroButton.setOnClickListener(v -> {

            if (mAdapter.getItemCount() != 0) {
                ticketArrayList.clear();
                fillDataMetro();
                mAdapter.notifyDataSetChanged();

            } else {
                fillDataMetro();
                mAdapter.notifyDataSetChanged();
            }

            mAdapter.notifyDataSetChanged();
        });
        return rootView;
    }

    public void fillDataMetro() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, AppConfig.URL_TICKET_metro + session.getUserByToken() ,
                        null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    System.out.println(jsonArray);
                    Ticket categorie = new Ticket();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject employee = jsonArray.getJSONObject(i);


                        categorie.setDate(employee.getString("date"));
                        categorie.setLigne(employee.getString("ligne"));
                        categorie.setGouv(employee.getString("gouv"));
                        categorie.setStationDepart(employee.getString("stationDepart"));
                        categorie.setStationArrive(employee.getString("stationArrive"));
                        categorie.setPrix(employee.getDouble("prix"));
                        categorie.setId(employee.getString("id"));

                        ticketArrayList.add(categorie);


                    }

                    mAdapter.notifyDataSetChanged();

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
        requestQueue.add(request);

    }

    public void fillDataTrain() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, AppConfig.URL_TICKET_train + session.getUserByToken() ,
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            System.out.println(jsonArray);
                            Ticket categorie = new Ticket();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);


                                categorie.setDate(employee.getString("date"));
                                categorie.setLigne(employee.getString("ligne"));
                                categorie.setGouv(employee.getString("gouv"));
                                categorie.setStationDepart(employee.getString("stationDepart"));
                                categorie.setStationArrive(employee.getString("stationArrive"));
                                categorie.setPrix(employee.getDouble("prix"));
                                categorie.setId(employee.getString("id"));

                                ticketArrayList.add(categorie);


                            }

                            mAdapter.notifyDataSetChanged();

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
        requestQueue.add(request);

    }

    public void fillDataBus() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, AppConfig.URL_TICKET_bus + session.getUserByToken() ,
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            System.out.println(jsonArray);
                            Ticket categorie = new Ticket();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);


                                categorie.setDate(employee.getString("date"));
                                categorie.setLigne(employee.getString("ligne"));
                                categorie.setGouv(employee.getString("gouv"));
                                categorie.setStationDepart(employee.getString("stationDepart"));
                                categorie.setStationArrive(employee.getString("stationArrive"));
                                categorie.setPrix(employee.getDouble("prix"));
                                categorie.setId(employee.getString("id"));

                                ticketArrayList.add(categorie);


                            }

                            mAdapter.notifyDataSetChanged();

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
        requestQueue.add(request);

    }
}