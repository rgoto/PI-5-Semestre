package br.senac.sp.aplicativopiv2.Utilities;

import org.json.JSONException;
import org.json.JSONObject;

public interface VolleyCallback {
    void onSuccess(JSONObject result) throws JSONException;
}
