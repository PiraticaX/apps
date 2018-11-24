package mychatapp.piraticax.aicarpool;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class login extends Activity {

    String umail,pass;
    EditText logmail,logpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        logmail = (EditText) findViewById( R.id.login_uname );
        logpass = (EditText) findViewById( R.id.login_pass );
        Button log_btn = (Button) findViewById( R.id.login_button );
        log_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requests( logmail.getText().toString(),logpass.getText().toString() );
            }
        } );

    }
    private void requests(final String username, final String password)
    {
        // String mainURL = getString(R.string.mainURL);
        String URL = "https://piraticaxdk.000webhostapp.com/AI_CARPOOL/login.php";
        StringRequest stringRequest = new StringRequest( Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responsex) {
                        loginresults(responsex,username);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(login.this,"Server Could not be reached.", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("emailid", username);
                params.put("password", password);
                //  params.put("token",token);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
    public void loginresults(String result,String username) {
        //String mainURL = getString(R.string.mainURL);
        if(result.contains("Login Success")) {
            Intent i = new Intent(this, profile.class);
            i.putExtra( "email",username );
            startActivity(i);
        } else {
            Toast.makeText(this, "Invalid Login Details.", Toast.LENGTH_SHORT).show();
        }
    }


    public void signup(View view) {
        Intent i=new Intent(login.this,register.class);
        startActivity( i );
    }
}
