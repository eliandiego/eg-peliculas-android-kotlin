package org.uqbar.peliculasapp.domain

import java.io.Serializable

/**
 * Created by fernando on 1/11/2017.
 */

class Genero(var descripcion: String) : Serializable {

    override fun toString(): String {
        return descripcion
    }
}

 class Pelicula(var titulo:String, var genero: Genero?, var sinopsis:String, var actores:String):Serializable {
    var id:Long? = null
    val descripcionGenero:String
        get() = if (genero == null) {
            ""
        } else genero!!.descripcion

    override fun toString():String {
        return titulo
    }

}
