package com.example.prova1;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

public class Utente implements Parcelable {
    private String nome;
    private String cognome;
    private String username;
    private String password;
    private Image foto;
    private String email;

    protected Utente(Parcel in) {
        nome = in.readString();
        cognome = in.readString();
        username = in.readString();
        password = in.readString();
        email = in.readString();
    }

    public static final Creator<Utente> CREATOR = new Creator<Utente>() {
        @Override
        public Utente createFromParcel(Parcel in) {
            return new Utente(in);
        }

        @Override
        public Utente[] newArray(int size) {
            return new Utente[size];
        }
    };

    public Utente() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nome);
        dest.writeString(this.cognome);
        dest.writeString(this.email);
        dest.writeString(this.username);
        dest.writeString(this.password);
    }
}
