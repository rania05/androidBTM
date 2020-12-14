package tn.esprit.btm.UI.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import Entity.Abonnement;

import Entity.Transport;
import adapter.AbonnementAdapter;

import tn.esprit.btm.R;
import tn.esprit.btm.UI.Helpers.SessionManager;
import tn.esprit.btm.UI.activies.AddAbonnement;
import tn.esprit.btm.UI.activies.Log_activity;
import tn.esprit.btm.UI.activies.MainActivity;
import tn.esprit.btm.UI.app.AppConfig;

import static android.R.layout.simple_spinner_item;


public class fragment_abonnements extends Fragment {
    SessionManager session;


    private RecyclerView recyclerView;
    ArrayList<Abonnement> abonnementsArrayList = new ArrayList<>();

    private AbonnementAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_abonnements, container, false);
        Button TrainButton = rootView.findViewById(R.id.btn_TrainA);
        Button MetroButton = rootView.findViewById(R.id.btn_MetroA);
        Button BusButton = rootView.findViewById(R.id.btn_BusA);
        Button Achat = rootView.findViewById(R.id.btn_AchatABN);
        Context mContext = getContext();
        session = new SessionManager(mContext);
        recyclerView = rootView.findViewById(R.id.recycler_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        mAdapter = new AbonnementAdapter(abonnementsArrayList);

        recyclerView.setAdapter(mAdapter);
        Achat.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddAbonnement.class);
            startActivity(intent);





        });
        BusButton.setOnClickListener(v -> {






            if (mAdapter.getItemCount() != 0) {
                abonnementsArrayList.clear();


                fillDataBus();

                mAdapter.notifyDataSetChanged();
            } else {
                fillDataBus();

                mAdapter.notifyDataSetChanged();
            }
        });
        TrainButton.setOnClickListener(v -> {

                    if (mAdapter.getItemCount() != 0) {
                        abonnementsArrayList.clear();
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
                abonnementsArrayList.clear();
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
                (Request.Method.GET, AppConfig.URL_ABN_metro + session.getUserByToken() ,
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            System.out.println(jsonArray);
                            Abonnement categorie = new Abonnement();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);


                                categorie.setDateDebut(employee.getString("dateD"));
                                categorie.setLigne(employee.getString("ligne"));
                                categorie.setVille(employee.getString("gouv"));
                                categorie.setDepart(employee.getString("depart"));
                                categorie.setDestination(employee.getString("arrive"));
                                categorie.setPrix(employee.getDouble("prix"));
                                categorie.setId(employee.getString("id"));
                                categorie.setDuree(employee.getString("duree"));
                                abonnementsArrayList.add(categorie);


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
                (Request.Method.GET, AppConfig.URL_ABN_train + session.getUserByToken() ,
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            System.out.println(jsonArray);
                            Abonnement categorie = new Abonnement();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);


                                categorie.setDateDebut(employee.getString("dateD"));
                                categorie.setLigne(employee.getString("ligne"));
                                categorie.setVille(employee.getString("gouv"));
                                categorie.setDepart(employee.getString("depart"));
                                categorie.setDestination(employee.getString("arrive"));
                                categorie.setPrix(employee.getDouble("prix"));
                                categorie.setId(employee.getString("id"));
                                categorie.setDuree(employee.getString("duree"));
                                abonnementsArrayList.add(categorie);


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
                (Request.Method.GET, AppConfig.URL_ABN_bus + session.getUserByToken() ,
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            System.out.println(jsonArray);
                            Abonnement categorie = new Abonnement();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);


                                categorie.setDateDebut(employee.getString("dateD"));
                                categorie.setLigne(employee.getString("ligne"));
                                categorie.setVille(employee.getString("gouv"));
                                categorie.setDepart(employee.getString("depart"));
                                categorie.setDestination(employee.getString("arrive"));
                                categorie.setPrix(employee.getDouble("prix"));
                                categorie.setId(employee.getString("id"));
                                categorie.setDuree(employee.getString("duree"));
                                abonnementsArrayList.add(categorie);


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