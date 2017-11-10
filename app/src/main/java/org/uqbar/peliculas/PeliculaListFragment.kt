package org.uqbar.peliculas

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.ListFragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.ListView
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.pelicula_list_fragment.*
import org.ubqar_project.peliculasandroidkotlin.R
import org.uqbar.peliculas.adapter.PeliculaAdapter
import org.uqbar.peliculas.domain.Pelicula
import org.uqbar.peliculas.repo.RepoPeliculas
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * SERVICE de RETROFIT
 *
 * https://android.jlelse.eu/kotlin-and-retrofit-2-tutorial-with-working-codes-333a4422a890
 */
interface PeliculasService {

    @GET("peliculas/{tituloContiene}")
    fun getPeliculas(@Path("tituloContiene") tituloContiene: String): Observable<List<Pelicula>>

    companion object {
        fun create(): PeliculasService {
            val BASE_URL = "http://10.0.2.2:8080/videoclub-ui-grails-homes-xtend/"

            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofit.create(PeliculasService::class.java)

        }
    }
}

/**
 * Created by fernando on 1/11/2017.
 */
class PeliculaListFragment : ListFragment(), View.OnClickListener {

    val peliculaService by lazy {
        PeliculasService.create()
    }

    var disposable: Disposable? = null

    override fun onClick(v: View?) {
        buscarPeliculas();
    }

    fun buscarPeliculas() {
        val titulo = tituloContiene.text.toString()

        disposable =
                peliculaService.getPeliculas(titulo)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { peliculas ->
                                    listAdapter = PeliculaAdapter(
                                            activity,
                                            peliculas!!)
                                },
                                { error ->
                                    Toast.makeText(this@PeliculaListFragment.getActivity().getApplicationContext(), "Ocurrió un error al buscar películas. " + error.message, Toast.LENGTH_LONG).show()
                                    Log.e("PeliculasApp", error.message)
                                }
                        )

    }

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
        super.onCreate(savedInstanceState)
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

        // Comportamiento del checkbox que indica si se busca a medida que se escribe
        chkBuscarOnline.setOnClickListener {
            if (chkBuscarOnline.isChecked) {
                btnBuscar.visibility = View.INVISIBLE
            } else {
                btnBuscar.visibility = View.VISIBLE
            }
        }

        // Comportamiento del título de búsqueda
        tituloContiene.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable) {
                if (chkBuscarOnline.isChecked && editable.length >= MIN_BUSQUEDA_PELICULAS) {
                    buscarPeliculas()
                }
            }
        })

        btnBuscar.setOnClickListener(this)
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
        val MIN_BUSQUEDA_PELICULAS = 2

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
