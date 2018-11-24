package mychatapp.piraticax.aicarpool;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class register extends Activity {

    String URL = "https://piraticaxdk.000webhostapp.com/AI_CARPOOL/register.php";
    EditText usname;
    EditText usmail;
    EditText usphno;
    EditText uspass;
    EditText uscpass;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );
        b=(Button)findViewById(R.id.register_registerbtn);
        ImageButton imgbtn=(ImageButton)findViewById(R.id.register_addimg);
        usname=(EditText)findViewById(R.id.register_uname);
        usmail=(EditText)findViewById(R.id.register_emailid);
        usphno=(EditText)findViewById(R.id.register_phno);
        uspass=(EditText)findViewById(R.id.register_pass);
        uscpass=(EditText)findViewById(R.id.register_cpass);
        b.setEnabled(false);
        final String uname,umail,uphno,upass,ucpass;
        uname=usname.getText().toString();
        umail=usmail.getText().toString();
        uphno=usphno.getText().toString();
        upass=uspass.getText().toString();
        ucpass=uscpass.getText().toString();
        if(uname!=null && umail!=null && uphno!=null && upass!=null) {
            if(ucpass.equals( upass )) {
                b.setEnabled( true );
            }
            else{
                Toast.makeText( this, "passwords dont match", Toast.LENGTH_SHORT ).show();
            }
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requests(usname.getText().toString(),usmail.getText().toString(),usphno.getText().toString(),uspass.getText().toString());
            }
        });

    }
    private void requests(final String username, final String email,final String password,final String phno )
    {
        // String mainURL = getString(R.string.mainURL);
        StringRequest stringRequest = new StringRequest( Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responsex) {
                        loginresults(responsex, email);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(register.this,"Server Could not be reached.", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("emailid", email);
                params.put("pass", password);
                params.put("phno", phno);
                //  params.put("token",token);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
    public void loginresults(String result, String email) {
        //String mainURL = getString(R.string.mainURL);
        if(result.contains("Success")) {
            Toast.makeText( this, "Created User Successfully . Please Login...", Toast.LENGTH_SHORT ).show();
            Intent i=new Intent(register.this,login.class);
            //i.putExtra("rmail",email);
            startActivity( i );
        } else {
            Toast.makeText(this, "Invalid Register Details.", Toast.LENGTH_SHORT).show();
        }
    }
}
