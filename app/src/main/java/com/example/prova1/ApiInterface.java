package com.example.prova1;

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

    //https://api.themoviedb.org/3/movie/popular?api_key=2e3034b8c110830b946972c7d30f5cb5&language=en-US&page=1
}
