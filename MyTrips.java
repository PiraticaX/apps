package mychatapp.piraticax.aicarpool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

public class MyTrips extends AppCompatActivity {

    ListView lv;
   // String userid;
    int arrsize;
    String arr[];
    TextView tot,tid,tdate,tsloc,tdloc,trole;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_my_trips );
        lv=(ListView)findViewById( R.id.listview_trips );
        ca ob=new ca();
        lv.setAdapter( ob );
        /*
        String URL="https://piraticaxdk.000webhostapp.com/AI_CARPOOL/countmytriplist.php";
        StringRequest stringRequest = new StringRequest( Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responsex) {
                        arrsize=Integer.valueOf( responsex );
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MyTrips.this,"Server Could not be reached.", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put( "email",getIntent().getStringExtra( "rmail" ) );
                // params.put("time",tim);
                //Map<String,Integer> p=new HashMap<String,Integer>( );
                //params.put( "noofseats",String.valueOf( no ) );
                //  params.put("token",token);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        String URL1="https://piraticaxdk.000webhostapp.com/AI_CARPOOL/getmytriplist.php";
        StringRequest stringRequest1 = new StringRequest( Request.Method.POST, URL1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responsex) {
                        for(int i=0;i<arrsize;i++) {
                            arr[i]=responsex;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MyTrips.this,"Server Could not be reached.", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put( "email",getIntent().getStringExtra( "rmail" ) );
                // params.put("time",tim);
                //Map<String,Integer> p=new HashMap<String,Integer>( );
                //params.put( "noofseats",String.valueOf( no ) );
                //  params.put("token",token);
                return params;
            }

        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest1);*/

    }

    class ca extends BaseAdapter {

        @Override
        public int getCount() {
            return arrsize;
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
            view = getLayoutInflater().inflate( R.layout.mytrips_listview_item, null );
            tid=(TextView)findViewById( R.id.tripid );
            tdate=(TextView)findViewById( R.id.trip_time);
            tsloc=(TextView)findViewById( R.id.trip_start_loc );
            tdloc=(TextView)findViewById( R.id.trip_dest_loc );
            trole=(TextView)findViewById( R.id.triprole );
            return view;
        }

    }
}
