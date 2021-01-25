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
    @Expose
    @SerializedName("nome") private String nome_playlist;
    @Expose
    @SerializedName("cod_playlist") private String cod_playlist;
    @Expose
    @SerializedName("tipo_playlist") private String tipo_playlist;
    @Expose
    @SerializedName("cod_film") private Integer cod_film;
    @Expose
    @SerializedName("segue_username") private String segue_username;
    @Expose
    @SerializedName("count") private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNome_playlist() {
        return nome_playlist;
    }

    public void setNome_playlist(String nome_playlist) {
        this.nome_playlist = nome_playlist;
    }

    public String getCod_playlist() {
        return cod_playlist;
    }

    public void setCod_playlist(String cod_playlist) {
        this.cod_playlist = cod_playlist;
    }

    public String getTipo_playlist() {
        return tipo_playlist;
    }

    public void setTipo_playlist(String tipo_playlist) {
        this.tipo_playlist = tipo_playlist;
    }

    public Integer getCod_film() {
        return cod_film;
    }

    public void setCod_film(Integer cod_film) {
        this.cod_film = cod_film;
    }

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

    public String getSegue_username() {
        return segue_username;
    }

    public void setSegue_username(String segue_username) {
        this.segue_username = segue_username;
    }


    // nome_utente, cognome_utente, username, password_utente, email_utente
}
