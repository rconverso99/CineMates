package com.example.prova1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Note {
    @Expose
    @SerializedName("username") private String username;
    @Expose
    @SerializedName("nome_utente") private String nome;
    @Expose
    @SerializedName("cognome_utente") private String cognome;
    @Expose
    @SerializedName("password_utente") private String password;
    @Expose
    @SerializedName("email_utente") private String email;
    @Expose
    @SerializedName("msg") private String message;
    @Expose
    @SerializedName("foto_profilo") private String foto;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    // nome_utente, cognome_utente, username, password_utente, email_utente
}
