package mychatapp.piraticax.aicarpool;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Passenger extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5;
    ImageView i1;
    Button b1,b2,b3,b4;
    static String email;
    int no_of_passengers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_passenger );
        e1=(EditText)findViewById( R.id.start_point_pass );
        e2=(EditText)findViewById( R.id.end_point_pass );
        e3=(EditText)findViewById( R.id.route_pass );
        e4=(EditText)findViewById( R.id.landmark_pass );
        e5=(EditText)findViewById( R.id.time_pass );
        i1=(ImageView)findViewById( R.id.imageView );
        b1=(Button)findViewById( R.id.seat1_pass );
        b2=(Button)findViewById( R.id.seat2_pass );
        b3=(Button)findViewById( R.id.seat3_pass );
        b4=(Button)findViewById( R.id.search );

        b1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( Passenger.this, "1 PASSENGER", Toast.LENGTH_SHORT ).show();
                no_of_passengers=1;
            }
        } );
        b2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( Passenger.this, "2 PASSENGERS", Toast.LENGTH_SHORT ).show();
                no_of_passengers=2;
            }
        } );
        b3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( Passenger.this, "3 PASSENGERS", Toast.LENGTH_SHORT ).show();
                no_of_passengers=3;
            }
        } );
        b4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( Passenger.this, "SEARCHNG FOR RIDES", Toast.LENGTH_SHORT ).show();
                requests( e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),no_of_passengers );
            }
        } );

    }
    private void requests(final String sloc, final String dloc,final String rou,final int no )
    {
        email=getIntent().getStringExtra( "rmail" );
        // String mainURL = getString(R.string.mainURL);
        String URL="https://piraticaxdk.000webhostapp.com/AI_CARPOOL/update_pass.php";
        StringRequest stringRequest = new StringRequest( Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responsex) {
                        loginresults(responsex);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Passenger.this,"Server Could not be reached.", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put( "email",email);
                params.put("sloc", sloc);
                params.put("dloc", dloc);
                params.put("route", rou);
                // params.put("time",tim);
                //Map<String,Integer> p=new HashMap<String,Integer>( );
                params.put( "noofseats",String.valueOf( no ) );
                //  params.put("token",token);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
    public void loginresults(String result) {
        //String mainURL = getString(R.string.mainURL);
        if(result.contains("Success")) {
            Toast.makeText( this, "Checking for Available rides", Toast.LENGTH_SHORT ).show();
            Intent i=new Intent( Passenger.this,CurrentTrip.class );
            i.putExtra( "ssloc", e1.getText().toString());
            i.putExtra( "ddloc", e2.getText().toString());
            i.putExtra( "route", e3.getText().toString());
            i.putExtra("rmail",email);
            startActivity( i );
        } else {
            Toast.makeText(this, "Invalid Search values", Toast.LENGTH_SHORT).show();
        }
    }

}
