package ufrn.campusseguro.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import sinfo.ufrn.br.japi.JApi;
import sinfo.ufrn.br.japi.JApiWebView;
import ufrn.campusseguro.R;

public class Login extends AppCompatActivity {

    private static final String CLIENT_ID_VALUE = "campusseguro-id";
    private static final String SECRET_KEY = "segredo";
    private static final String STATE = "ubk1Oj0Lh6I5APyEHtlCqtARyg7yh2Z5ZRpVv8ML";
    private static final String TAG = "MyActivity";

    private static final String REDIRECT_URI = "https://apitestes.info.ufrn.br";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(JApi.getAccessToken(this) == null){
            JApiWebView japiWebView = (JApiWebView) findViewById(R.id.japiwebview);
            japiWebView.loadJapiWebView(REDIRECT_URI, CLIENT_ID_VALUE, SECRET_KEY, this, MainActivity.class);
        }else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
}
