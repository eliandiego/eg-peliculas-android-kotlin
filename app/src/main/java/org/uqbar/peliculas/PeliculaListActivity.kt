package org.uqbar.peliculas

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pelicula_app_bar.*

import kotlinx.android.synthetic.main.pelicula_list.*
import org.ubqar_project.peliculasandroidkotlin.R
import org.uqbar.peliculas.domain.Pelicula

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [PeliculaDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class PeliculaListActivity : AppCompatActivity(), PeliculaListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var mTwoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pelicula_app_bar)

        setSupportActionBar(toolbar)
        toolbar.title = title

        if (pelicula_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            (supportFragmentManager
                    .findFragmentById(R.id.pelicula_list) as PeliculaListFragment)
                    .setActivateOnItemClick(true)
        }

    }

    /**
     * Callback method from [PeliculaListFragment.Callbacks]
     * indicating that the item with the given ID was selected.
     */
    override fun onItemSelected(pelicula: Pelicula?) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            val arguments = Bundle()
            arguments.putSerializable(PeliculaDetailFragment.ARG_ITEM_ID, pelicula)
            val fragment = PeliculaDetailFragment()
            fragment.arguments = arguments
            supportFragmentManager.beginTransaction()
                    .replace(R.id.pelicula_detail_container, fragment)
                    .commit()

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            val detailIntent = Intent(this, PeliculaDetailActivity::class.java)
            detailIntent.putExtra(PeliculaDetailFragment.ARG_ITEM_ID, pelicula)
            startActivity(detailIntent)
        }
    }
}
