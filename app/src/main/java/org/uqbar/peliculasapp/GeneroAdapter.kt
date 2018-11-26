package org.uqbar.peliculasapp

import org.uqbar.peliculasapp.domain.Pelicula


object GeneroAdapter {

    val mapaGeneros = HashMap<String, Int>()

    init {
        mapaGeneros["Infantil"] = R.drawable.infantil
        mapaGeneros["Infantil/Anim"] = R.drawable.infantil
        mapaGeneros["Accion"] = R.drawable.accion
        mapaGeneros["Series"] = R.drawable.default2
        mapaGeneros["Drama"] = R.drawable.drama
        mapaGeneros["Comedia"] = R.drawable.comedia
        mapaGeneros["Clasicos"] = R.drawable.comedia2
        mapaGeneros["Infantil / Peli"] = R.drawable.infantil
        mapaGeneros["C.Ficcion"] = R.drawable.sci_fi
        mapaGeneros["Musical"] = R.drawable.drama
        mapaGeneros["C.Romantica"] = R.drawable.romantica
        mapaGeneros["Suspenso"] = R.drawable.suspenso
        mapaGeneros["Terror"] = R.drawable.horror
        mapaGeneros["Infantil/Peli"] = R.drawable.infantil
        mapaGeneros["Aventuras"] = R.drawable.fantasia
        mapaGeneros["Nacional"] = R.drawable.default3
        mapaGeneros["Familia"] = R.drawable.comedia2
        mapaGeneros["Belica"] = R.drawable.horror
        mapaGeneros["Documental"] = R.drawable.default2
        mapaGeneros["Infantil-Peli"] = R.drawable.infantil
        mapaGeneros["Infantil-Anim"] = R.drawable.infantil
        mapaGeneros["Teatral"] = R.drawable.default3
    }

    fun getIconoGenero(pelicula: Pelicula): Int {
        val result = mapaGeneros[pelicula.descripcionGenero]!!
        return if (result == 0) {
            R.drawable.default3
        } else result
    }

}