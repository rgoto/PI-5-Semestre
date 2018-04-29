package br.senac.sp.aplicativopiv2.Utilities;

public class UserData {
    private String name;
    private String email;
    private String urlPhoto;
    private int id;
    private static UserData instance;

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
    public void setUrlPhoto(String urlPhoto) { this.urlPhoto = urlPhoto; }

    protected void setInstance(UserData userData) { instance = userData; }
}

