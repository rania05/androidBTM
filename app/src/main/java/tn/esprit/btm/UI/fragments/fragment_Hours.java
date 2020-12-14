package tn.esprit.btm.UI.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Entity.Transport;

import adapter.TransportAdapter;
import tn.esprit.btm.R;
import tn.esprit.btm.UI.activies.AddAbonnement;
import tn.esprit.btm.UI.activies.AddTickets;
import tn.esprit.btm.UI.app.AppConfig;

import static android.R.layout.simple_spinner_item;

public class fragment_Hours extends Fragment  {

    public ArrayList countryName = new ArrayList();


    private RecyclerView recyclerView;


    //final Calendar cldr = Calendar.getInstance();

    ArrayList<Transport> transportArrayList = new ArrayList<>();

    private TransportAdapter mAdapter;

//
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment__hours, container, false);
        Button BusButton = rootView.findViewById(R.id.btn_BusH);
        LinearLayout L = rootView.findViewById(R.id.contentBus);
        Button TrainButton = rootView.findViewById(R.id.btn_TrainH);
        Button MetroButton = rootView.findViewById(R.id.btn_MetroH);
        Spinner spinnerVille = rootView.findViewById(R.id.spinnerVille);

        //   searchView=rootView.findViewById(R.id.search);
        recyclerView = rootView.findViewById(R.id.recycler_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        mAdapter = new TransportAdapter(transportArrayList);

        recyclerView.setAdapter(mAdapter);
        // loadSpinnerData();
        ArrayAdapter<String> spinnerAdapterVille = new ArrayAdapter<String>(getActivity(), simple_spinner_item, countryName);
        spinnerAdapterVille.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVille.setAdapter(spinnerAdapterVille);

        BusButton.setOnClickListener(v -> {






            if (mAdapter.getItemCount() != 0) {
                transportArrayList.clear();


                fillDataBus();

                mAdapter.notifyDataSetChanged();
            } else {
                fillDataBus();

                mAdapter.notifyDataSetChanged();
            }
        });
        TrainButton.setOnClickListener(v -> {

                    if (mAdapter.getItemCount() != 0) {
                        transportArrayList.clear();
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
               transportArrayList.clear();
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




    /************* fonctions *****************************/


    //


    public void fillDataBus() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, AppConfig.URL_BUS,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    System.out.println(jsonArray);
                    Transport categorie = new Transport();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject employee = jsonArray.getJSONObject(i);


                        categorie.setRegion(employee.getString("ville"));
                        categorie.setLigne(employee.getString("ligne"));
                        categorie.setType(employee.getString("type"));
                        categorie.setDepart(employee.getString("nom"));
                        categorie.setHeure(employee.getString("heure"));
                        categorie.setNumero(employee.getInt("numero"));
                        transportArrayList.add(categorie);

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
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, AppConfig.URL_TRAIN, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    System.out.println(jsonArray);
                    Transport categorie = new Transport();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject employee = jsonArray.getJSONObject(i);


                        categorie.setRegion(employee.getString("ville"));
                        categorie.setLigne(employee.getString("ligne"));
                        categorie.setType(employee.getString("type"));
                        categorie.setDepart(employee.getString("nom"));
                        categorie.setHeure(employee.getString("heure"));
                        categorie.setNumero(employee.getInt("numero"));
                        transportArrayList.add(categorie);

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

    public void fillDataMetro() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, AppConfig.URL_METRO, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    System.out.println(jsonArray);
                    Transport categorie = new Transport();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject employee = jsonArray.getJSONObject(i);


                        categorie.setRegion(employee.getString("ville"));
                        categorie.setLigne(employee.getString("ligne"));
                        categorie.setType(employee.getString("type"));
                        categorie.setDepart(employee.getString("nom"));
                        categorie.setHeure(employee.getString("heure"));
                        categorie.setNumero(employee.getInt("numero"));
                        transportArrayList.add(categorie);

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

    public void loadSpinnerData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        System.out.println("ena hne ");
        JsonArrayRequest stringRequest = new JsonArrayRequest(Method.GET, AppConfig.URL_HOURS, null, new Response.Listener<JSONArray>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(JSONArray response) {
                System.out.println("step 1");
                try {
                    System.out.println("step 2");
                    System.out.println("step 3");

                    for (int i = 0; i < response.length(); i++) {

                        JSONObject villeobject = response.getJSONObject(i);

                        String villeSp=villeobject.getString("ville");
                        System.out.println("fct2 "+villeSp);
                        countryName.add(villeSp);


                        System.out.println("ajoutit f country ");
                    }

                    System.out.println("step 4");
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("error catch");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error response");
            }
        });
        requestQueue.add(stringRequest);
    }



}

