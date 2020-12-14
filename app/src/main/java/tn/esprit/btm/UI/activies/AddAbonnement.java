package tn.esprit.btm.UI.activies;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import Entity.Transport;
import tn.esprit.btm.R;
import tn.esprit.btm.UI.Helpers.SessionManager;
import tn.esprit.btm.UI.app.AppConfig;

import static android.R.layout.simple_spinner_item;

public class AddAbonnement extends AppCompatActivity {
    String[] typeTransport= {"bus","train","metro"};
    String[] villeSp = {"ben arous ","tunis","ariana"};
    String[] LigneTransport= {"as","cd","bb"};
    String[] Depart= {"d1","d2","d3"};
    String[] Destination= {"dd1","dd2","dd3"};
    String[] Duree= {"1 mois","3mois","6mois","12 mois"};
    SessionManager session;
    public ArrayList<String> ville = new ArrayList<String>(); //this can't be an array

    Spinner spinnerType,spinnerLigne,spinnerVille,spinnerDepart,spinnerDestination,spinnerDuree;


boolean debut,fin =false;
    Button Abonner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_abonnement);
        spinnerType= (Spinner)findViewById(R.id.spinnerType);
        spinnerLigne= (Spinner) findViewById(R.id.spinnerLigne);
        spinnerVille= (Spinner) findViewById(R.id.spinnerVille);
        spinnerDepart= (Spinner) findViewById(R.id.spinnerDepart);
        spinnerDestination= (Spinner) findViewById(R.id.spinnerDestination);
        spinnerDuree= (Spinner) findViewById(R.id.spinnerDuree);
        Abonner=(Button)findViewById(R.id.btn_Abonner);


        ArrayAdapter<String> spinnerAdapterType = new ArrayAdapter <String> (this, simple_spinner_item,typeTransport);
        ArrayAdapter<String> spinnerAdapterLigne = new ArrayAdapter<String> (this, simple_spinner_item,LigneTransport);
        ArrayAdapter<String> spinnerAdapterVille = new ArrayAdapter<String>(this, simple_spinner_item,villeSp);

        ArrayAdapter<String> spinnerAdapterDepart = new ArrayAdapter<String> (this, simple_spinner_item,Depart);
        ArrayAdapter<String> spinnerAdapterDestination = new ArrayAdapter<String> (this, simple_spinner_item,Destination);
        ArrayAdapter<String> spinnerAdapterDuree = new ArrayAdapter<String> (this, simple_spinner_item,Duree);
        Context mContext = this;
        session = new SessionManager(mContext);


        //loadSpinnerDataVille();
        loadSpinnerDataLigne();
        //loadSpinnerDataDepartDest();


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
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
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

                        Toast.makeText(getApplicationContext(),
                                "abonnement succed", Toast.LENGTH_LONG)
                                .show();



                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VOLLEY", error.toString());
                        if (error.networkResponse.statusCode == 401) {
                            Toast.makeText(getApplicationContext(),
                                    "Please verify your credentials", Toast.LENGTH_LONG)
                                    .show();
                        } else {
                            Toast.makeText(getApplicationContext(),
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

        RequestQueue requestQueue = Volley.newRequestQueue(this);
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
}