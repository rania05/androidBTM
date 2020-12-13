package tn.esprit.btm.UI.activies;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

import tn.esprit.btm.R;
import tn.esprit.btm.UI.Helpers.SessionManager;
import tn.esprit.btm.UI.app.AppConfig;

public class AddTickets extends AppCompatActivity {

    private EditText txtMoyen, txtGouv, txtLigne ,txtDepart, txtArrive;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tickets);
        txtMoyen = findViewById(R.id.moyenTransportTicket);
        txtGouv = findViewById(R.id.gouverneratTicket);
        txtLigne = findViewById(R.id.ligneTicket);
        txtDepart = findViewById(R.id.stationDepartTicket);
        txtArrive = findViewById(R.id.stationArriveTicket);
        Button btn_achat = findViewById(R.id.btn_acheterTicket);
        String ligne ="ligne";
        String moyen ="moyen";
        String gouv ="gouv";
        String depart ="depart";

        Bundle extras=getIntent().getExtras();
        if(extras !=null)
        {
            ligne =extras.getString("ligne");
            moyen =extras.getString("moyen");
            gouv =extras.getString("gouv");
            depart =extras.getString("depart");

            //behi taba3ni, rit eli ajoutitou lkoooooool el bullshit listener fi 20 alf blasa fas5ou lkooooooooool

        }
        txtLigne.setText(ligne);

        txtDepart.setText(depart);
        txtGouv.setText(gouv);
        txtMoyen.setText(moyen);
        btn_achat.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {   try {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                System.out.println("ena hne ");
                JSONObject jsonBody = new JSONObject();

                jsonBody.put("moyenTransport", txtMoyen.getText().toString());
                jsonBody.put("ligne","Sud");
               // jsonBody.put("ligne", txtLigne.getText().toString());
                jsonBody.put("stationDepart", txtDepart.getText().toString());
                jsonBody.put("stationArrive", txtArrive.getText().toString());

                jsonBody.put("gouv", txtGouv.getText().toString());
                jsonBody.put("iduser", session.getUserByToken());
                final String requestBody = jsonBody.toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_AddTicket, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                 Log.e("VOLLEY", response);
                        // Check the JWT Token

                        Toast.makeText(getApplicationContext(),
                                "ticket succed", Toast.LENGTH_LONG)
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
}