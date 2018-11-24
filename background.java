package mychatapp.piraticax.aicarpool;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class background extends AsyncTask<String,Void,String> {

    AlertDialog dialog;
    Context context;
    public background() {
    this.context=context;
    }
    @Override
    protected void onPreExecute() {
    dialog =new AlertDialog.Builder( context ).create();
    dialog.setTitle( "LOGIN STATUS" );
    }

    @Override
    protected void onPostExecute(String s) {
        dialog.setMessage( s );
        dialog.show();
    }

    @Override
    protected String doInBackground(String... voids) {
        String res="";
        String mail=voids[0];
        String pass=voids[1];
        String ur="http://localhost:8080/login.php";
        try{
            URL url = new URL(ur);
            HttpURLConnection http =(HttpURLConnection)url.openConnection();
            http.setRequestMethod( "POST" );
            http.setDoInput( true );
            http.setDoOutput( true );
            OutputStream ops =http.getOutputStream();
            BufferedWriter wr=new BufferedWriter( new OutputStreamWriter( ops,"UTF-8" ) );
            String data=URLEncoder.encode( "emailid","UTF-8" )+URLEncoder.encode(mail,"UTF-8")+"&&"
                    +URLEncoder.encode( "pass","UTF-8" )+URLEncoder.encode( pass,"UTF-8" );

            wr.write(data);
            wr.flush();
            wr.close();
            ops.close();
            InputStream ips=http.getInputStream();
            BufferedReader br=new BufferedReader( new InputStreamReader( ips,"ISO-8859-1" ) );
            String line="";
            while((line=br.readLine())!=null){
                res+=line;
            }
            br.close();
            ips.close();
            http.disconnect();
            return res;



        }catch(Exception e){
            res=e.getMessage();
        }
        return null;
    }
}
