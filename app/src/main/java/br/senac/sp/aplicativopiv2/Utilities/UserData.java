package br.senac.sp.aplicativopiv2.Utilities;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

public class UserData {
    private String name;
    private String email;
    private String urlPhoto;
    private String firstName;
    private String middleName;

    private int id;
    private static UserData instance;
    private ArrayList<Entry> gastoList;
    private ArrayList<Entry> potenciaList;

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

    public ArrayList<Entry> getGastoList() {    return gastoList;   }
    public void setGastoList(ArrayList<Entry> gastoList) {  this.gastoList = gastoList;   }

    public ArrayList<Entry> getPotenciaList() { return potenciaList;    }
    public void setPotenciaList(ArrayList<Entry> potenciaList) {    this.potenciaList = potenciaList;   }
}

