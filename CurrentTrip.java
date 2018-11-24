package mychatapp.piraticax.aicarpool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CurrentTrip extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5,t6;
    Button b;
    Button b1;
    int arr_size;
    String arr[];
    ListView l;
    String pasid,ssloc,vno,ddloc,route;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_current_trip );
        l = (ListView) findViewById( R.id.avai_rides );
        pasid = String.valueOf( getIntent().getStringArrayExtra( "passid" ) );
        ssloc = getIntent().getStringExtra( "ssloc" );
        ddloc = getIntent().getStringExtra( "ddloc" );
        route = getIntent().getStringExtra( "route" );
        vno = getIntent().getStringExtra( "vno" );
        ca ob = new ca();
        l.setAdapter( ob );


        //t6.setText( arr_size );


    }
   /* private void requests(final String sloc, final String dloc,final String rou )
    {
        // String mainURL = getString(R.string.mainURL);

        String URL="https://piraticaxdk.000webhostapp.com/AI_CARPOOL/gettriplist.php";
        StringRequest stringRequest = new StringRequest( Request.Method.POST, URL,
                new Response.Listener<String>() {
            JSONObject json;
                    @Override
                    public void onResponse(String responsex) {
                        JSONArray values = null;
                        try {
                            values = json.getJSONArray(responsex);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < values.length(); i++) {
                            int id = 0;
                            String sloc = null,dloc=null;
                            JSONObject animal = null;
                            try {
                                animal = values.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                id = animal.getInt("drid");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                sloc = animal.getString("start_loc");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                dloc = animal.getString("dest_loc");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            arr[i]=String.valueOf( id )+","+sloc+","+dloc;
                            Toast.makeText( CurrentTrip.this, arr[i], Toast.LENGTH_SHORT ).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CurrentTrip.this,"Server Could not be reached.", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put( "email",getIntent().getStringExtra( "rmail" ) );
                params.put("sloc", sloc);
                params.put("dloc", dloc);
                params.put("route", rou);
                // params.put("time",tim);
                //Map<String,Integer> p=new HashMap<String,Integer>( );
                //params.put( "noofseats",String.valueOf( no ) );
                //  params.put("token",token);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }*/


    class ca extends BaseAdapter {

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate( R.layout.current_trip_litem, null );
            t1 =(TextView)findViewById( R.id.dest);
            t2=(TextView)findViewById( R.id.route );
            t3=(TextView)findViewById( R.id.no_of_seats );
            t4=(TextView)findViewById( R.id.vechno );
            t5=(TextView)findViewById( R.id.name );
            b=(Button)findViewById( R.id.btnavairides );
            b.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText( CurrentTrip.this, "ride booked", Toast.LENGTH_SHORT ).show();
                }
            } );
            return view;
        }

    }
}
