package br.senac.sp.aplicativopiv2.Utilities;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.github.mikephil.charting.data.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.senac.sp.aplicativopiv2.LogadoActivity;
import br.senac.sp.aplicativopiv2.MainActivity;

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

    public void loginUser(String email, String pass, final Context context, final VolleyCallback callback) {
        String url ="http://pi4sem.rbbr.com.br/teste/login.php?email=" + email + "&senha=" + pass;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            UserData user = UserData.getInstance();

                            if (response.getInt("success") == 1) {
                                JSONObject object = response.getJSONArray("dados").getJSONObject(0);
                                user.setId(object.getInt("id"));
                                user.setFirstName(object.getString("first_name"));
                                user.setMiddleName(object.getString("middle_name"));
                                user.setName(user.getFirstName() + " " + user.getMiddleName());
                                user.setEmail(object.getString("email"));
                                user.setUrlPhoto(object.getString("img_url"));

                                Log.d("DEBUGG", user.getUrlPhoto());
                                UserData.getInstance().setInstance(user);

                                callback.onSuccess(object);
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

    public void getDataById(final Context context, int id, final VolleyCallback callback) {
        String url ="http://pi4sem.rbbr.com.br/teste/getDadosUSER.php?id=" + id ;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            UserData user = UserData.getInstance();

                            if (response.getInt("success") == 1) {
                                JSONArray object = response.getJSONArray("user");

                                ArrayList<Double> gastoList = getGastoList();
                                ArrayList<Double> potenciaList = getPotenciaList();
                                ArrayList<String> horarioList = getHorarioList();

                                for (int i = 0; i < object.length(); i++) {
                                    JSONObject c = object.getJSONObject(i);

                                    Double gasto = c.getDouble("gasto");
                                    Double potencia = c.getDouble("potencia");
                                    String horario = c.getString("horario");

                                    gastoList.add(gasto);
                                    potenciaList.add(potencia);
                                    horarioList.add(horario);
                                }
                                user.setGastoList(gastoList);
                                user.setPotenciaList(potenciaList);
                                user.setHorarioList(horarioList);

                                callback.onSuccess(response);
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


    public void getStateOfLamps(final Context context, int id, final VolleyCallback callback) {
        String url = "http://pi4sem.rbbr.com.br/teste/verificaEstado.php?id=" + id;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getInt("success") == 1) {
                                JSONObject object = response.getJSONArray("dados").getJSONObject(0);

                                UserData.getInstance().setStateLamp1(object.getInt("lamp1"));
                                UserData.getInstance().setStateLamp2(object.getInt("lamp2"));
                                Log.d("USER DATA", "" + UserData.getInstance().getStateLamp1());
                                Log.d("USER DATA", "" + UserData.getInstance().getStateLamp2());
                                callback.onSuccess(response);
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

    public void setStateOfLamps (final Context context, int state, int id, final VolleyCallback callback) {
        String url = "http://pi4sem.rbbr.com.br/teste/mudaEstado.php?lamp1=" + state + "&id=" + id;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getInt("success") == 1) {
                                callback.onSuccess(response);
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

    public void getDataFloatById (final Context context, int id, final VolleyCallback callback) {
        String url ="http://pi4sem.rbbr.com.br/teste/getDados.php?id=" + id ;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getInt("success") == 1) {
                                callback.onSuccess(response);
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

    public void monthlyPrediction(final Context context, int id, final VolleyCallback callback) {
        String url = "http://pi4sem.rbbr.com.br/teste/previsaoMensal.php?id=" + id ;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getInt("success") == 1) {
                                callback.onSuccess(response);
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

    public void getGasto2Min(final Context context, int id, final VolleyCallback callback) {
            String url = "http://pi4sem.rbbr.com.br/teste/getDadosMin.php?id=" + id ;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            UserData user = UserData.getInstance();

                            if (response.getInt("success") == 1) {
                                JSONArray object = response.getJSONArray("user");

                                ArrayList<Double> gastoList = getGasto2minList();

                                for (int i = 0; i < object.length(); i++) {
                                    JSONObject c = object.getJSONObject(i);

                                    Double gasto = c.getDouble("gasto");

                                    gastoList.add(gasto);
                                }
                                user.setGasto2minList(gastoList);

                                callback.onSuccess(response);
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
