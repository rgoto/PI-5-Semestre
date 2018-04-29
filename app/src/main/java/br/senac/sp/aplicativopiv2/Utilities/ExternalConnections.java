package br.senac.sp.aplicativopiv2.Utilities;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import br.senac.sp.aplicativopiv2.LogadoActivity;

public class ExternalConnections extends UserData {
    private static ExternalConnections instance;

    ExternalConnections() {
        super();
    }

    public static synchronized ExternalConnections getInstance() {
        if (instance == null)
            instance = new ExternalConnections();

        return instance;
    }

    public void loginUser(String email, String pass, final Context context) {
        String url ="http://pi4sem.rbbr.com.br/teste/login.php?email=" + email + "&senha=" + pass;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            UserData user = UserData.getInstance();

                            if (response.getInt("success") == 1) {
                                JSONObject object = response.getJSONArray("dados").getJSONObject(0);
                                user.setName(object.getString("nome"));
                                user.setEmail(object.getString("email"));
                                user.setId(object.getInt("id"));

                                UserData.getInstance().setInstance(user);

                                Intent it = new Intent(context, LogadoActivity.class);
                                context.startActivity(it);
                            }
                            else {
                                Toast.makeText(context, "Não foi possivel realizar o login", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        VolleyRequest.getInstance(context).addToRequestQueue(jsObjRequest);
    }
}
