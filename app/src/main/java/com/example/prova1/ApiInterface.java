package com.example.prova1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

   @GET("/3/movie/{category}")
     Call<MovieResults> listOfMovies(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
);

   @FormUrlEncoded
   @POST("verificausername_aws.php")
    Call<Note> searchUsername(
     @Field("user") String username
   );

    @FormUrlEncoded
    @POST("verificamail_aws.php")
    Call<Note> searchEmail(
            @Field("email") String username
    );

    @FormUrlEncoded
    @POST("registrazione_aws.php")
    Call<Note> registrazione(
            @Field("nome") String nome,
            @Field("cognome") String cognome,
            @Field("username") String username,
            @Field("pass") String password,
            @Field("email") String email,
            @Field("foto_profilo") String foto
    );

    @FormUrlEncoded
    @POST("login_aws.php")
    Call<List<Note>> login(
            @Field("username_utente") String username,
            @Field("password_utente") String password
    );

    @GET("/3/search/movie")
    Call<MovieResults> searchMovies(
            @Query("api_key") String apiKey,
            @Query("query") String query,
            @Query("language") String language,
            @Query("page") int page

    );

    @GET("/3/discover/movie")
    Call<MovieResults> genreMovies(
            @Query("api_key") String apiKey,
            @Query("with_genres") String genre

    );

    @FormUrlEncoded
    @POST("recuperaplaylist_aws.php")
    Call<List<Note>> recupera_playlist(
            @Field("username") String username
    );

 @FormUrlEncoded
 @POST("inserisci_film_aws.php")
 Call<Note> inserisci_film(
         @Field("nome") String nome_playlist,
         @Field("username") String username,
         @Field("cod_playlist") String cod_playlist,
         @Field("tipo_playlist") String tipo_playlist,
         @Field("cod_film") int cod_film
 );

 @GET("/3/movie/{id}")
 Call<MovieResults> movieById(
         @Path("id") Integer id,
         @Query("api_key") String apiKey


 );

 @FormUrlEncoded
 @POST("cercautente_aws.php")
 Call<List<Note>> cercaUtente(
         @Field("username") String username
 );

 @FormUrlEncoded
 @POST("eliminafilm_aws.php")
 Call<Note> elimina_film(
         @Field("cod_film") int cod_film,
         @Field("username") String username,
          @Field("tipo_playlist") String tipo_playlist
 );

 @FormUrlEncoded
 @POST("seguiti_aws.php")
 Call<List<Note>> elencaSeguiti(
         @Field("username") String username
 );

 @FormUrlEncoded
 @POST("contaseguiti_aws.php")
 Call<List<Note>> contaSeguiti(
         @Field("username") String username
 );

 @FormUrlEncoded
 @POST("contafollower_aws.php")
 Call<List<Note>> contaFollower(
         @Field("segue_username") String username
 );

 @FormUrlEncoded
 @POST("segueutente_aws.php")
 Call<Note> segueUtente(
         @Field("username") String username,
         @Field("segue_username") String username_segue
 );








    //https://api.themoviedb.org/3/movie/popular?api_key=2e3034b8c110830b946972c7d30f5cb5&language=en-US&page=1
}
