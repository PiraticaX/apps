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

public class Driver extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5;
    ImageView i1;
    String sloc,dloc,rou,tim,vno;
    Button b1,b2,b3,b4;
    int no_of_seats_available;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_driver );
        e1=(EditText)findViewById( R.id.start_point );
        e2=(EditText)findViewById( R.id.end_point );
        e3=(EditText)findViewById( R.id.route );
        e4=(EditText)findViewById( R.id.vehicleno );
        e5=(EditText)findViewById( R.id.time );
        i1=(ImageView)findViewById( R.id.imageView2 );
        b1=(Button)findViewById( R.id.seat1 );
        b2=(Button)findViewById( R.id.seat2 );
        b3=(Button)findViewById( R.id.seat3 );
        b4=(Button)findViewById( R.id.update );
        sloc=e1.getText().toString();
        dloc=e2.getText().toString();
        rou=e3.getText().toString();
        tim=e5.getText().toString();
        vno=e4.getText().toString();
        b1.setEnabled( false );
        b2.setEnabled( false );
        b3.setEnabled( false );
        b4.setEnabled( false );
        if(sloc!=null && dloc!=null && rou!=null && tim!=null && vno!=null) {
            b1.setEnabled( true );
            b2.setEnabled( true );
            b3.setEnabled( true );
            b1.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText( Driver.this, "1 SEAT AVAILABLE", Toast.LENGTH_SHORT ).show();
                    no_of_seats_available = 1;
                    b4.setEnabled( true );
                }
            } );
            b2.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText( Driver.this, "2 SEAT AVAILABLE", Toast.LENGTH_SHORT ).show();
                    no_of_seats_available = 2;
                    b4.setEnabled( true );
                }
            } );
            b3.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText( Driver.this, "3 SEAT AVAILABLE", Toast.LENGTH_SHORT ).show();
                    no_of_seats_available = 3;
                    b4.setEnabled( true );
                }
            } );
            b4.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    requests(e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),e4.getText().toString(),no_of_seats_available);
                }
            } );
        }
    }
    private void requests(final String sloc, final String dloc,final String rou,final String vno,final int no )
    {
        // String mainURL = getString(R.string.mainURL);
        String URL="https://piraticaxdk.000webhostapp.com/AI_CARPOOL/update_ride.php";
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
                        Toast.makeText(Driver.this,"Server Could not be reached.", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put( "email",getIntent().getStringExtra( "rmail" ) );
                params.put("sloc", sloc);
                params.put("dloc", dloc);
                params.put("route", rou);
                params.put("vno", vno);
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
            Toast.makeText( this, "Driver status updated", Toast.LENGTH_SHORT ).show();
            Toast.makeText( this, "Passengers will contact you", Toast.LENGTH_SHORT ).show();
            Intent i=new Intent( Driver.this,CurrentTrip.class );
            i.putExtra( "vno",e4.getText().toString() );
        } else {
            Toast.makeText(this, "Invalid Update(driver) values", Toast.LENGTH_SHORT).show();
        }
    }
}
