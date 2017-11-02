package org.uqbar.peliculas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import org.uqbar.peliculas.domain.Pelicula
import kotlinx.android.synthetic.main.pelicula_row.view.*
import org.ubqar_project.peliculasandroidkotlin.R

/**
 * Created by fernando on 1/11/2017.
 */
class PeliculaAdapter(context: Context, peliculas: List<Pelicula>) : ArrayAdapter<Pelicula>(context, R.layout.pelicula_row, peliculas) {

    override fun getItemId(position: Int): Long {
        //return position;
        return getItem(position)!!.id!!
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.pelicula_row, parent, false)
        val pelicula = getItem(position)

        rowView.lblPelicula.setText(pelicula.toString())
        rowView.lblActores.setText(pelicula.actores.toString())
        return rowView
    }
}