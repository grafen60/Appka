package com.malousek.appka.Model.NASA_DbReadModel;

import com.malousek.appka.Data.Classes.Meteorite;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Jan Malousek on 13.07.2018.
 */
public interface Api {

    String BASE_URL = "https://data.nasa.gov/resource/";

    String APP_TOKEN = "ipPKOIYAcoWQVywvVqTG0TT9u";

    @GET("https://data.nasa.gov/resource/y77d-th95.json?$where=year >= '2011-01-01T00:00:00.000'&$limit=50000&$order=mass DESC&$select=name,id,recclass,mass,year,reclat,reclong&%24%24app_token="+APP_TOKEN)
    Call<List<Meteorite>> getMeteorites();

}
