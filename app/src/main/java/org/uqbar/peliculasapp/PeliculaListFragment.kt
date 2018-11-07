package org.uqbar.peliculasapp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.ListFragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.*
import org.ubqar_project.peliculasandroidkotlin.R
import org.uqbar.peliculasapp.adapter.PeliculaAdapter
import org.uqbar.peliculasapp.domain.Pelicula
import org.uqbar.peliculasapp.service.PeliculasService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * De  * A list fragment representing a list of Peliculas. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a [PeliculaDetailFragment].
 *
 *
 * Activities containing this fragment MUST implement the [Callbacks]
 * interface.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class PeliculaListFragment : ListFragment(), View.OnClickListener {

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private var mCallbacks = sDummyCallbacks

    /**
     * The current activated item position. Only used on tablets.
     */
    private var mActivatedPosition = ListView.INVALID_POSITION

    private var peliculaService: PeliculasService? = null

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    interface Callbacks {
        fun onItemSelected(pelicula: Pelicula)
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation {
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Esta URL apunta al proyecto con ORM de Grails
        // 		val API_URL = "http://10.0.2.2:8080/videoclub-ui-orm-grails"
        // Esta URL apunta a la solución en Grails con Homes hechos en Xtend
        //String SERVER_IP = "10.0.2.2";

        // IMPORTANTE
        // Por un bug de retrofit 2.0, la BASE_URL debe tener una / al final
        // y la dirección del service debe comenzar sin /, como un path relativo
        val BASE_URL = "http://10.0.2.2:8080/videoclub-ui-grails-homes-xtend/"

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        peliculaService = retrofit.create<PeliculasService>(PeliculasService::class.java)
    }

    private fun buscarPeliculas() {
        val campoBusqueda = this.view!!.findViewById(R.id.tituloContiene) as EditText
        val titulo = campoBusqueda.text.toString()

        val peliculaCall = peliculaService!!.getPeliculas(titulo)

        peliculaCall.enqueue(object : Callback<List<Pelicula>> {
            override fun onResponse(call: Call<List<Pelicula>>, response: Response<List<Pelicula>>) {
                val peliculas = response.body()

                listAdapter = PeliculaAdapter(
                        activity!!,
                        peliculas!!)

            }

            override fun onFailure(call: Call<List<Pelicula>>, t: Throwable) {
                t.printStackTrace()
                Log.e("PeliculasApp", t.message)
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION))
        }

        // Comportamiento del checkbox que indica si se busca a medida que se escribe
        val chkBuscar = view.findViewById(R.id.chkBuscarOnline) as CheckBox
        chkBuscar.setOnClickListener {
            val btnBuscar = view.findViewById(R.id.btnBuscar) as ImageButton
            if (chkBuscar.isChecked) {
                btnBuscar.visibility = View.INVISIBLE
            } else {
                btnBuscar.visibility = View.VISIBLE
            }
        }

        // Comportamiento del título de búsqueda
        val tituloContiene = view.findViewById(R.id.tituloContiene) as EditText
        tituloContiene.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable) {
                if (chkBuscar.isChecked && editable.length >= MIN_BUSQUEDA_PELICULAS) {
                    buscarPeliculas()
                }
            }
        })

        (view.findViewById(R.id.btnBuscar) as ImageButton).setOnClickListener(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val activity : Activity = context as Activity

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

        val pelicula = listView!!.adapter.getItem(position) as Pelicula
        Toast.makeText(context, pelicula.titulo, Toast.LENGTH_LONG).show()

        mCallbacks.onItemSelected(pelicula)


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition)
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.pelicula_list_fragment, null, false)
    }

    private fun setActivatedPosition(position: Int) {
        if (position == ListView.INVALID_POSITION) {
            listView.setItemChecked(mActivatedPosition, false)
        } else {
            listView.setItemChecked(position, true)
        }

        mActivatedPosition = position
    }

    override fun onClick(v: View) {
        buscarPeliculas()
    }

    companion object {

        var MIN_BUSQUEDA_PELICULAS = 2

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
            override fun onItemSelected(pelicula: Pelicula) {}
        }
    }

}