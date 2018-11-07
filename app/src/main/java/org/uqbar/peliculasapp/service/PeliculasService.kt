package org.uqbar.peliculasapp.service

import org.uqbar.peliculasapp.domain.Pelicula
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by fernando on 11/11/15.
 */
interface PeliculasService {

    @GET("peliculas/{tituloContiene}")
    fun getPeliculas(@Path("tituloContiene") tituloContiene: String): Call<List<Pelicula>>

}