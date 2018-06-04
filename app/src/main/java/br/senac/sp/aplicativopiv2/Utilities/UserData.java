package br.senac.sp.aplicativopiv2.Utilities;

import com.github.mikephil.charting.data.Entry;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserData {
    private String name;
    private String email;
    private String urlPhoto;
    private String firstName;
    private String middleName;

    private int id;
    private static UserData instance;
    private static ArrayList<Double> gastoList;
    private static ArrayList<Double> potenciaList;
    private static ArrayList<String> horarioList;
    private static ArrayList<Double> gasto2minList;

    private int stateLamp1;
    private int stateLamp2;
    private double previsao;

    UserData(){ super(); }

    public static synchronized UserData getInstance(){
        if (instance == null)
            instance = new UserData();

        return instance;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUrlPhoto() { return urlPhoto; }
    public void setUrlPhoto(String urlPhoto) {
        if (urlPhoto.isEmpty() || urlPhoto.equals("null")) {
            this.urlPhoto = "http://i.imgur.com/DvpvklR.png";
        } else {
            this.urlPhoto = urlPhoto;
        }
    }
    protected void setInstance(UserData userData) { instance = userData; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public static synchronized ArrayList<Double> getGastoList() {
        if (gastoList == null)
            gastoList = new ArrayList<>();

        return gastoList;
    }
    public void setGastoList(ArrayList<Double> list) {
        gastoList = list;
    }

    public static synchronized ArrayList<Double> getPotenciaList() {
        if (potenciaList == null)
            potenciaList = new ArrayList<>();

        return potenciaList;
    }
    public void setPotenciaList(ArrayList<Double> list) {
        potenciaList = list;
    }

    public static synchronized ArrayList<String> getHorarioList() {
        if (horarioList == null)
            horarioList = new ArrayList<>();

        return horarioList;
    }
    public void setHorarioList(ArrayList<String> list) {
        horarioList = list;
    }

    public int getStateLamp1() {
        return stateLamp1;
    }
    public void setStateLamp1(int stateLamp1) {
        this.stateLamp1 = stateLamp1;
    }

    public int getStateLamp2() {
        return stateLamp2;
    }
    public void setStateLamp2(int stateLamp2) {
        this.stateLamp2 = stateLamp2;
    }

    public double getPrevisao() {
        return previsao;
    }
    public void setPrevisao(double previsao) {
        this.previsao = previsao;
    }

    public static synchronized ArrayList<Double> getGasto2minList() {
        if (gasto2minList == null)
            gasto2minList = new ArrayList<>();

        return gasto2minList;
    }
    public void setGasto2minList(ArrayList<Double> list) {
        gasto2minList = list;
    }
}

