package org.uqbar.peliculas

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.ListFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.ListView
import org.ubqar_project.peliculasandroidkotlin.R
import org.uqbar.peliculas.adapter.PeliculaAdapter
import org.uqbar.peliculas.domain.Pelicula
import org.uqbar.peliculas.repo.RepoPeliculas

/**
 * Created by fernando on 1/11/2017.
 */
class PeliculaListFragment : ListFragment() {

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private var mCallbacks = sDummyCallbacks

    /**
     * The current activated item position. Only used on tablets.
     */
    private var mActivatedPosition = ListView.INVALID_POSITION

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    interface Callbacks {
        fun onItemSelected(pelicula: Pelicula?)
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation {
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val peliculas = RepoPeliculas.instance.getPeliculas(null, 10)
        super.onCreate(savedInstanceState)
        listAdapter = PeliculaAdapter(
                activity,
                peliculas)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION))
        }
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)

        // Activities containing this fragment must implement its callbacks.
        if (activity !is Callbacks) {
            throw IllegalStateException("Activity must implement fragment's callbacks.")
        }

        mCallbacks = activity
    }

    override fun onDetach() {
        super.onDetach()

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks
    }

    override fun onListItemClick(listView: ListView?, view: View?, position: Int, id: Long) {
        super.onListItemClick(listView, view, position, id)

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        val pelicula = RepoPeliculas.instance.getPelicula(id)
        mCallbacks.onItemSelected(pelicula)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState!!.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition)
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    fun setActivateOnItemClick(activateOnItemClick: Boolean) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        listView.choiceMode = if (activateOnItemClick)
            ListView.CHOICE_MODE_SINGLE
        else
            ListView.CHOICE_MODE_NONE
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.pelicula_list_fragment, null, false)
    }

    private fun setActivatedPosition(position: Int) {
        if (position == ListView.INVALID_POSITION) {
            listView.setItemChecked(mActivatedPosition, false)
        } else {
            listView.setItemChecked(position, true)
        }

        mActivatedPosition = position
    }

    companion object {

        /**
         * The serialization (saved instance state) Bundle key representing the
         * activated item position. Only used on tablets.
         */
        private val STATE_ACTIVATED_POSITION = "activated_position"

        /**
         * A dummy implementation of the [Callbacks] interface that does
         * nothing. Used only when this fragment is not attached to an activity.
         */
        private val sDummyCallbacks: Callbacks = object : Callbacks {
            override fun onItemSelected(pelicula: Pelicula?) {}
        }
    }
}
