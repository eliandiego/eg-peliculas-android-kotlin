package org.uqbar.peliculasapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_pelicula_list.*
import kotlinx.android.synthetic.main.pelicula_list.*
import kotlinx.android.synthetic.main.pelicula_list_content.view.*
import org.uqbar.peliculasapp.domain.Pelicula
import org.uqbar.peliculasapp.service.PeliculasService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * An activity representing a list of Peliculas. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [PeliculaDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class PeliculaListActivity : AppCompatActivity(), View.OnClickListener {
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false
    private val MIN_BUSQUEDA_PELICULAS = 2

    private var peliculaService: PeliculasService? = null

    init {
        defineBackendService()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pelicula_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        if (pelicula_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        // Comportamiento del checkbox que indica si se busca a medida que se escribe
        val chkBuscar = this.chkBuscarOnline as CheckBox
        chkBuscar.setOnClickListener {
            val btnBuscar = this.btnBuscar as ImageButton
            if (chkBuscar.isChecked) {
                btnBuscar.visibility = View.INVISIBLE
            } else {
                btnBuscar.visibility = View.VISIBLE
            }
        }

        // Comportamiento del título de búsqueda
        tituloContiene.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable) {
                if (chkBuscar.isChecked && editable.length >= MIN_BUSQUEDA_PELICULAS) {
                    buscarPeliculas()
                }
            }
        })

        this.btnBuscar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        buscarPeliculas()
    }

    fun defineBackendService() {
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
        val peliculaCall = peliculaService!!.getPeliculas(this.tituloContiene.text.toString())
        val parentActivity = this@PeliculaListActivity

        peliculaCall.enqueue(object : Callback<List<Pelicula>> {
            override fun onResponse(call: Call<List<Pelicula>>, response: Response<List<Pelicula>>) {
                val peliculas = response.body()

                parentActivity.pelicula_list.adapter = PeliculaAdapter(
                    parentActivity,
                    peliculas ?: ArrayList(),
                    twoPane
                )

            }

            override fun onFailure(call: Call<List<Pelicula>>, t: Throwable) {
                t.printStackTrace()
                Log.e("PeliculasApp", t.message)
            }
        })

    }

    class PeliculaAdapter(
        private val parentActivity: PeliculaListActivity,
        private val values: List<Pelicula>,
        private val twoPane: Boolean
    ) :
        RecyclerView.Adapter<PeliculaAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val pelicula = v.tag as Pelicula
                if (twoPane) {
                    val fragment = PeliculaDetailFragment().apply {
                        arguments = Bundle().apply {
                            putSerializable(PeliculaDetailFragment.ARG_ITEM_ID, pelicula)
                        }
                    }
                    parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.pelicula_detail_container, fragment)
                        .commit()
                } else {
                    val intent = Intent(v.context, PeliculaDetailActivity::class.java).apply {
                        putExtra(PeliculaDetailFragment.ARG_ITEM_ID, pelicula)
                    }
                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.pelicula_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val pelicula = values[position]
            holder.peliculaView.text = pelicula.titulo
            holder.actoresView.text = pelicula.actores

            with(holder.itemView) {
                tag = pelicula
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val peliculaView: TextView = view.lblPelicula
            val actoresView: TextView = view.lblActores
        }
    }
}
