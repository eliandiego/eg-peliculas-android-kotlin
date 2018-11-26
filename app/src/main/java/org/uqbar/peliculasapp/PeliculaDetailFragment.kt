package org.uqbar.peliculasapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_pelicula_detail.*
import kotlinx.android.synthetic.main.pelicula_detail.view.*
import org.uqbar.peliculasapp.domain.Pelicula
import org.uqbar.peliculasapp.repo.RepoPeliculas

/**
 * A fragment representing a single Pelicula detail screen.
 * This fragment is either contained in a [PeliculaListActivity]
 * in two-pane mode (on tablets) or a [PeliculaDetailActivity]
 * on handsets.
 */
class PeliculaDetailFragment : Fragment() {

    private var item: Pelicula? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = RepoPeliculas.getPelicula(it.getString(ARG_ITEM_ID)!!.toLong())
                activity?.toolbar_layout?.title = item?.titulo
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.pelicula_detail, container, false)

        // Show the dummy content as text in a TextView.
        item?.let {
            rootView.pelicula_detail.text = it.sinopsis
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
