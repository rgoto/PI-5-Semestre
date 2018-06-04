package br.senac.sp.aplicativopiv2.Utilities;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;

public class ExpenditureForecast {

    private ExpenditureForecast() {}

    private static ExpenditureForecast instance;

    public static synchronized ExpenditureForecast getInstance() {
        if (instance == null)
            instance = new ExpenditureForecast();

        return instance;
    }

    public void monthlyPrediction (int id, Context context) {
        ExternalConnections.getInstance().monthlyPrediction(context, id, new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) throws JSONException {
                JSONArray obj = result.getJSONArray("dados");

                ArrayList<Double> week = new ArrayList<>();
                double prediction = 0;

                /*for (int i = 0; i < obj.length(); i++) {
                    JSONObject data = obj.getJSONObject(i);

                    Double value = data.getDouble("week");
                    Log.d("DEBUGG", "valor de week " + value);
                    week.add(value);
                }*/

                for (int i = obj.length(); i > 0; i--) {
                    JSONObject data = obj.getJSONObject(i);
                    Log.d("DEBUGG", "i-1 " + (i - 1));
                    Log.d("DEBUGG", "week.get(i): " + data.getDouble("week"));
                    Log.d("DEBUGG", "week.size(): " + obj.length());
                    Log.d("DEBUGG", "week.size() - i - 1: " + (obj.length() - i - 1));

                    Double value = data.getDouble("week");
                    if (value > 0.1) {
                        Double expo = ((i - 1) * (value * (obj.length() - i - 1)));
                        Log.d("DEBUG", "expo" + expo);
                        prediction +=  Math.pow(0.1875, expo);
                    }

                }

                /*Log.d("DEBUGG", "week.size(): " + week.size());
                for (int i = week.size(); i > 0; i--) {
                    Log.d("DEBUGG", "i-1 " + (i - 1));
                    Log.d("DEBUGG", "week.get(i): " + week.get(i));
                    Log.d("DEBUGG", "week.size(): " + week.size());
                    Log.d("DEBUGG", "week.size() - i - 1: " + (week.size() - i - 1));

                    Double expo = ((i - 1) * (week.get(i) * (week.size() - i - 1)));
                    Log.d("DEBUG", "expo" + expo);
                    prediction +=  Math.pow(0.1875, expo);
                }*/

                UserData.getInstance().setPrevisao(prediction);
            }
        });
    }

}
