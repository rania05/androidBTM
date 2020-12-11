package tn.esprit.btm.UI.activies;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
import tn.esprit.btm.R;
import tn.esprit.btm.UI.Helpers.SessionManager;
import tn.esprit.btm.UI.app.AppConfig;
import org.json.JSONException;
import org.json.JSONObject;
import java.nio.charset.StandardCharsets;
public class Log_activity extends AppCompatActivity {
    TextView signIn;
    TextView username;
    TextView password;
    Button btnLogin;
    ImageView btnBack;
    private SessionManager session;
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       // btnBack = findViewById(R.id.btn_register);

        username = findViewById(R.id.let_email);
        password = findViewById(R.id.let_password);
        btnLogin = findViewById(R.id.btn_login);
       /* btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/




        // Session manager
        session = new SessionManager(getApplicationContext());

        //  Check if user is already logged in or not
        if (session.isLoggedIn()) {

            Intent intent = new Intent(Log_activity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }



        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String semail = username.getText().toString().trim();
                String spassword = password.getText().toString().trim();

                // Check for empty data in the form
                if (!semail.isEmpty() && !spassword.isEmpty()) {
                    // login user
                    checkLogin(semail, spassword);
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Entrez les données s'il vous plait!", Toast.LENGTH_LONG)
                            .show();
                }
            }

        });

    }
    private void checkLogin(final String username, final String password) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            System.out.println("ena hne ");
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("mail", username);
            jsonBody.put("password", password);
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_LOGIN, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.e("VOLLEY", response);
                    // Check the JWT Token
                    Toast.makeText(getApplicationContext(),
                            "Login succed", Toast.LENGTH_LONG)
                            .show();

                    //Session  & SQL LITE HANDLER
                    session.setLogin(true,username);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        session.setToken(true, (String) jsonObject.get("token"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // Launch MainScreen Acitivty
                    Intent intent = new Intent(
                            Log_activity.this,
                            MainActivity.class);
                    startActivity(intent);
                    finish();


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
}