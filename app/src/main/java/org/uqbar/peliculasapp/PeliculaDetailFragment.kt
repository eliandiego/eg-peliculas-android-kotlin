package org.uqbar.peliculasapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_pelicula_detail.*
import kotlinx.android.synthetic.main.fragment_pelicula_detail.view.*
import org.ubqar_project.peliculasandroidkotlin.R
import org.uqbar.peliculasapp.adapter.GeneroAdapter
import org.uqbar.peliculasapp.domain.Pelicula

/**
 * A fragment representing a single Pelicula detail screen.
 * This fragment is either contained in a [PeliculaListActivity]
 * in two-pane mode (on tablets) or a [PeliculaDetailActivity]
 * on handsets.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class PeliculaDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    private lateinit var pelicula: Pelicula

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments!!.containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            pelicula = arguments!!.get(ARG_ITEM_ID) as Pelicula

            val activity = this.activity
            if (toolbar_layout != null) {
                toolbar_layout.title = pelicula.titulo
            } else {
                activity!!.setTitle(pelicula.titulo)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_pelicula_detail, container, false)
        rootView.pelicula_actores.setText(pelicula.actores)
        rootView.pelicula_sinopsis.setText(pelicula.sinopsis)
        rootView.pelicula_genero.setText(pelicula.descripcionGenero)
        rootView.imgGenero.setImageDrawable(resources.getDrawable(GeneroAdapter().getIconoGenero(pelicula), null))
        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        val ARG_ITEM_ID = "item_id"
    }
}
