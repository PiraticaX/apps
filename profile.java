package mychatapp.piraticax.aicarpool;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class profile extends Activity {

    ImageView imv;
    ImageButton imgbtn;
    TextView tv1,tv2;
    Button b1,b2,b3,b4;
    String us;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile );
        imv=(ImageView)findViewById(R.id.header_cover_image);
        imgbtn=(ImageButton) findViewById(R.id.user_profile_photo);
        tv1=(TextView)findViewById(R.id.user_profile_name);
        tv2=(TextView)findViewById(R.id.user_profile_short_bio);
        b1=(Button)findViewById(R.id.mytrips);
        b2=(Button)findViewById(R.id.drive);
        b3=(Button)findViewById(R.id.ride);
        b4=(Button)findViewById(R.id.start);
        imgbtn.setBackgroundResource( R.drawable.img2 );
        us=getIntent().getStringExtra( "email" );
        tv2.setText( us );
        final String u=tv2.getText().toString();
        b2.setEnabled( false );
        b3.setEnabled( false );
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(profile.this, "YOUR TRIPS", Toast.LENGTH_SHORT).show();
                Intent i=new Intent( profile.this,MyTrips.class );
                i.putExtra( "rmail",u );
                startActivity( i );
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(profile.this, "YOUR DRIVING", Toast.LENGTH_SHORT).show();
                Intent i=new Intent( profile.this,Driver.class );
                i.putExtra( "rmail",u );
                startActivity( i );
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(profile.this, "SAFE JOURNEY", Toast.LENGTH_SHORT).show();
                Intent i=new Intent( profile.this,Passenger.class );
                i.putExtra( "rmail",u );
                startActivity( i );
            }
        });
        b4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b2.setEnabled( true );
                b3.setEnabled( true );
            }
        } );

    }
}
