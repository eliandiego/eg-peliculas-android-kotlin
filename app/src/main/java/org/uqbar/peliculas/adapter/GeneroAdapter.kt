package org.uqbar.peliculas.adapter

import org.ubqar_project.peliculasandroidkotlin.R
import org.uqbar.peliculas.domain.Genero
import org.uqbar.peliculas.domain.Pelicula
import java.util.*

/**
 * Created by fernando on 1/11/2017.
 */
class GeneroAdapter {

    fun getIconoGenero(pelicula: Pelicula): Int {
        return result?.get(pelicula.descripcionGenero) ?: R.drawable.default3
    }

    companion object {
        internal val result: MutableMap<String, Int>? = getResult()

        private fun getResult(): MutableMap<String, Int>? {
            val result = HashMap<String, Int>()
            result.put("Infantil", R.drawable.infantil)
            result.put("Infantil/Anim", R.drawable.infantil)
            result.put("Accion", R.drawable.accion)
            result.put("Series", R.drawable.default2)
            result.put("Drama", R.drawable.drama)
            result.put("Comedia", R.drawable.comedia)
            result.put("Clasicos", R.drawable.comedia2)
            result.put("Infantil / Peli", R.drawable.infantil)
            result.put("C.Ficcion", R.drawable.sci_fi)
            result.put("Musical", R.drawable.drama)
            result.put("C.Romantica", R.drawable.romantica)
            result.put("Suspenso", R.drawable.suspenso)
            result.put("Terror", R.drawable.horror)
            result.put("Infantil/Peli", R.drawable.infantil)
            result.put("Aventuras", R.drawable.fantasia)
            result.put("Nacional", R.drawable.default3)
            result.put("Familia", R.drawable.comedia2)
            result.put("Belica", R.drawable.horror)
            result.put("Documental", R.drawable.default2)
            result.put("Infantil-Peli", R.drawable.infantil)
            result.put("Infantil-Anim", R.drawable.infantil)
            result.put("Teatral", R.drawable.default3)
            return result
        }
    }

}