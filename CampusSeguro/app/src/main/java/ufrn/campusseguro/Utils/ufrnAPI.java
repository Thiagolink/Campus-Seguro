package ufrn.campusseguro.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ca.mimic.oauth2library.OAuthResponse;

/**
 * Created by Wesley Reuel on 07/12/2017.
 */

public class ufrnAPI extends AsyncTask<String, Void, OAuthResponse> {
    private Context mContext;
    private static final String KEY_ACCESS_TOKEN = "ACCESS_TOKEN";
    private static final String KEY_REFRESH_TOKEN = "REFRESH_TOKEN";
    private static final String KEY_EXPIRES_IN = "EXPIRES_IN";
    private static final String KEY_TOKEN_TYPE = "TOKEN_TYPE";
    private static final String KEY_USER_INFO = "USER_INFO";
    private static final String TAG = "ufrnAPI";

    public ufrnAPI(Context context) {

        mContext = context;
    }

    @Override
    protected OAuthResponse doInBackground(String... strings) {


        SharedPreferences sharedPreferences = mContext.getSharedPreferences(KEY_USER_INFO, 0);
        String tokenFinal = sharedPreferences.getString(KEY_TOKEN_TYPE, null);
        String tokenAcesso = sharedPreferences.getString(KEY_ACCESS_TOKEN, null);
        HttpURLConnection urlConnection = null;
        String resultado = "";
        String AuthorizationInfo = "bearer " + tokenAcesso;
        try {
            URL url = new URL("https://apitestes.info.ufrn.br/usuario/v0.1/usuarios/info");

            urlConnection = (HttpURLConnection) url
                    .openConnection();


            urlConnection.setRequestProperty("Authorization", AuthorizationInfo);

            urlConnection.setRequestProperty("x-api-key", "ubk1Oj0Lh6I5APyEHtlCqtARyg7yh2Z5ZRpVv8ML");

            InputStream in = urlConnection.getInputStream();


            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                System.out.print(current);
                resultado += current;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        Log.w(TAG,resultado);
        String[] res = resultado.split(",");
        Log.w(TAG, res[0]);
        String login, nome;
        login = res[2].substring(res[2].indexOf(":")+2, res[2].length()-1);
        nome = res[3].substring(res[3].indexOf(":")+2, res[3].length()-1);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("login", login);
        editor.putString("nome", nome);
        editor.commit();
        return  null;
    }
}

