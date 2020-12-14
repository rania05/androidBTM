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

import Entity.Transport;
import tn.esprit.btm.R;
import tn.esprit.btm.UI.Helpers.SessionManager;
import tn.esprit.btm.UI.activies.Log_activity;
import tn.esprit.btm.UI.activies.MainActivity;
import tn.esprit.btm.UI.app.AppConfig;

import static android.R.layout.simple_spinner_item;


public class fragment_abonnements extends Fragment  {

    String[] typeTransport= {"bus","train","metro"};
    String[] villeSp = {"ben arous ","tunis","ariana"};
    String[] LigneTransport= {"as","cd","bb"};
    String[] Depart= {"d1","d2","d3"};
    String[] Destination= {"dd1","dd2","dd3"};
    String[] Duree= {"1 mois","3mois","6mois","12 mois"};
    SessionManager session;
    public ArrayList<String> ville = new ArrayList<String>(); //this can't be an array list 5atrou ye5edh simple array

    // public ArrayList LigneTransport = new ArrayList();
   // public ArrayList Depart = new ArrayList();
   // public ArrayList Destination = new ArrayList();
    Spinner spinnerType,spinnerLigne,spinnerVille,spinnerDepart,spinnerDestination,spinnerDuree;

String Testt;



    /* final Calendar cldr= Calendar.getInstance();
    EditText dateDebut ,dateFin;
boolean debut,fin =false;*/
Button Abonner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        //declaration
        View rootView = inflater.inflate(R.layout.fragment_abonnements, container, false);
         spinnerType= (Spinner)rootView.findViewById(R.id.spinnerType);
         spinnerLigne= (Spinner) rootView.findViewById(R.id.spinnerLigne);
       spinnerVille= (Spinner) rootView.findViewById(R.id.spinnerVille);
      spinnerDepart= (Spinner) rootView.findViewById(R.id.spinnerDepart);
      spinnerDestination= (Spinner) rootView.findViewById(R.id.spinnerDestination);
        spinnerDuree= (Spinner) rootView.findViewById(R.id.spinnerDuree);
        int selectedVilleSpinner;
        Abonner=(Button)rootView.findViewById(R.id.btn_Abonner);

        /*for (int c = 0; c < ville.size(); c++) {
            villeSp[c] = ville.get(c);
        }*/

        ArrayAdapter<String> spinnerAdapterType = new ArrayAdapter <String> (getActivity(), simple_spinner_item,typeTransport);
        ArrayAdapter<String> spinnerAdapterLigne = new ArrayAdapter<String> (getActivity(), simple_spinner_item,LigneTransport);
        ArrayAdapter<String> spinnerAdapterVille = new ArrayAdapter<String>(getActivity(), simple_spinner_item,villeSp);

        ArrayAdapter<String> spinnerAdapterDepart = new ArrayAdapter<String> (getActivity(), simple_spinner_item,Depart);
        ArrayAdapter<String> spinnerAdapterDestination = new ArrayAdapter<String> (getActivity(), simple_spinner_item,Destination);
        ArrayAdapter<String> spinnerAdapterDuree = new ArrayAdapter<String> (getActivity(), simple_spinner_item,Duree);
        Context mContext = getContext();
        session = new SessionManager(mContext);


        //loadSpinnerDataVille();
        loadSpinnerDataLigne();
        loadSpinnerDataDepartDest();


        //working with spinner
        spinnerAdapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(spinnerAdapterType);

        spinnerAdapterLigne.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLigne.setAdapter(spinnerAdapterLigne);

        spinnerAdapterVille.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVille.setAdapter(spinnerAdapterVille);

        spinnerAdapterDepart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepart.setAdapter(spinnerAdapterDepart);

        spinnerAdapterDestination.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDestination.setAdapter(spinnerAdapterDestination);

        spinnerAdapterDuree.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDuree.setAdapter(spinnerAdapterDuree);

/*
        spinnerVille.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
Testt=spinnerVille.getSelectedItem().toString();
                System.out.println("spinner 3ta"+Testt);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
*/





spinnerVille.setSelection(0);
spinnerType.setSelection(0);
spinnerDuree.setSelection(0);
 spinnerDestination.setSelection(0);
        spinnerDepart.setSelection(0);
        spinnerLigne.setSelection(0);



/*
        DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                cldr.set(Calendar.YEAR,year);
                cldr.set(Calendar.MONTH,month);
                cldr.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                changeEditText();
            }

        };
dateDebut.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        debut=true;
        fin=false;

        new DatePickerDialog(getActivity(),d,cldr.get(Calendar.YEAR),cldr.get(Calendar.MONTH),cldr.get(Calendar.DAY_OF_MONTH)).show();
    }
});
dateFin.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   fin=true;
                                   debut=false;
                                   new DatePickerDialog(getActivity(),d,cldr.get(Calendar.YEAR),cldr.get(Calendar.MONTH),cldr.get(Calendar.DAY_OF_MONTH)).show();
                               }
                           }
);*/

Abonner.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View v) {   try {
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
            System.out.println("ena hne ");
            JSONObject jsonBody = new JSONObject();

            jsonBody.put("moyenTransport", spinnerType.getSelectedItem().toString());
            jsonBody.put("prix",5);
            jsonBody.put("ligne", spinnerLigne.getSelectedItem().toString());
            jsonBody.put("depart", spinnerDepart.getSelectedItem().toString());
           jsonBody.put("arrive", spinnerDestination.getSelectedItem().toString());
            jsonBody.put("duree", spinnerDuree.getSelectedItem().toString());
        jsonBody.put("gouv", spinnerVille.getSelectedItem().toString());
            jsonBody.put("iduser", session.getUserByToken());
            jsonBody.put("image", "image");
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_AddAbn, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.e("VOLLEY", response);
                    // Check the JWT Token

                    Toast.makeText(getActivity().getApplicationContext(),
                            "abonnement succed", Toast.LENGTH_LONG)
                            .show();



                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                    if (error.networkResponse.statusCode == 401) {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Please verify your credentials", Toast.LENGTH_LONG)
                                .show();
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "A problem has been occured , please retry later.", Toast.LENGTH_LONG)
                                .show();
                    }
                }

            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public byte[] getBody() throws AuthFailureError {
                    return requestBody == null ? null : requestBody.getBytes(StandardCharsets.UTF_8);
                }


                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = response.toString();
                        responseString = new String(response.data, StandardCharsets.UTF_8);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    });

        return rootView;
    }
/*
    public void loadSpinnerDataVille( ) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        System.out.println("ena hne ");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, AppConfig.URL_HOURS,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    System.out.println(jsonArray);
                    Transport categorie = new Transport();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject employee = jsonArray.getJSONObject(i);



                 categorie.setLigne(employee.getString("ville"));

                        ville.add(categorie.getLigne());
                        villeSp[i] = employee.getString("ville");

                    }


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
*/

    public void loadSpinnerDataLigne() {

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        System.out.println("ena hne ");
        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET,AppConfig.URL_HOURS, null, new Response.Listener<JSONArray>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(JSONArray response) {
                System.out.println("step 1");
                try {
                    JSONArray jsonArray = response.getJSONArray(Integer.parseInt("data"));
                    System.out.println(jsonArray);
                    Transport categorie = new Transport();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject employee = jsonArray.getJSONObject(i);



                        categorie.setLigne(employee.getString("ville"));

                        ville.add(categorie.getLigne());
                        villeSp[i] = employee.getString("ville");

                    }


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
        requestQueue.add(stringRequest); //aya nighty
    }
    public void loadSpinnerDataDepartDest() {

    }

}