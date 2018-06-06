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
        ExternalConnections.getInstance().monthlyPrediction(context, id, null);
    }

    public void getData() {
        UserData user = UserData.getInstance();
        ArrayList<Double> weeks = UserData.getWeeksList();
        Double prediction = 0d;
        for (int i = 0; i < weeks.size(); i++) {
            Log.d("DEBUGG", "i-1 " + (i - 1));
            Log.d("DEBUGG", "week.get(i): " + weeks.get(i));
            Log.d("DEBUGG", "week.size(): " + weeks.size());
            Log.d("DEBUGG", "week.size() - i - 1: " + (weeks.size() - i - 1));
            double expo = ((i - 1) * (weeks.get(i) * (weeks.size() - i - 1)));
            Log.d("DEBUGG", "expo: " + expo);

            prediction += Math.pow(0.1875, expo);
            Log.d("DEBUGG", "prediction: " + prediction);
            Log.d("DEBUGG", "Math.pow: " + Math.pow(0.1875, expo));

            double gasto = weeks.get(i);
            Log.d("DEBUGG", "gasto: " + gasto);
        }
    }

}
