# La aplicación en distintos dispositivos

Hasta el momento hemos visto la aplicación en un emulador de un dispositivo similar al de un teléfono. ¿Qué pasa si probamos con un dispositivo más grande, una tablet, en lugar de emular un teléfono?

## Configurando un nuevo dispositivo

En el menú Tools > AVD Manager o bien al ejecutar nuestra aplicación, podemos generar un nuevo dispositivo:

![video](../videos/configuringTablet.gif)

A partir de aquí, podemos emular una tablet o un celular, con resultados diferentes.

## Vista específica para tablets

Al crear una vista master/detail tenemos una vista `pelicula_list (w900dp)` para dispositivos de más de 900 dp:

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              xmlns:app="http://schemas.android.com/apk/res-auto"
              xmlns:tools="http://schemas.android.com/tools"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:layout_marginLeft="16dp"
              android:layout_marginRight="16dp"
              android:baselineAligned="false"
              android:divider="?android:attr/dividerHorizontal"
              android:orientation="horizontal"
              android:showDividers="middle"
              tools:context=".PeliculaListActivity">

    <!--
    This layout is a two-pane layout for the Peliculas
    master/detail flow.
    
    -->

    <android.support.v7.widget.RecyclerView xmlns:android="http://schemas.android.com/apk/res/android"
            .../>

    <FrameLayout
            android:id="@+id/pelicula_detail_container"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="3"/>

</LinearLayout>
```

En esta vista vemos que hay dos elementos dispuestos en un layout horizontal (uno al lado del otro):

* el primero referencia a la lista de películas (PeliculaListActivity)
* el segundo es un contenedor cuyo identificador es `pelicula_detail_container`

## Configurando la aplicación para uno o dos paneles

En la clase PeliculasListActivity el Javadoc autogenerado muestra la siguiente información

```kt
/**
 * An activity representing a list of Peliculas. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [PeliculaDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
```

Vemos que la presencia de un control `pelicula_detail_container`, únicamente definido en la vista de 900dp, es el que usamos para determinar en qué dispositivo estamos corriendo:

```kt
class PeliculaListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        ...
        if (pelicula_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        setupRecyclerView(pelicula_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = PeliculaAdapter(
            this,
            RepoPeliculas.getPeliculas(null, 10),
            twoPane
        )
    }
```

La variable booleana `twoPane` define luego cómo es la interacción entre la vista master y detail:

* mientras que cuando tenemos un celular disparamos un **Intent** hacia otra actividad de detalle
* cuando estamos en una tablet permanecemos en la misma actividad master, pero trabajamos con un **fragment**, al que le pasamos la información del id de película seleccionado

```kt
    class PeliculaAdapter(
        private val parentActivity: PeliculaListActivity,
        private val values: List<Pelicula>,
        private val twoPane: Boolean
    ) :
        RecyclerView.Adapter<PeliculaAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val item = v.tag as Pelicula
                if (twoPane) {
                    val fragment = PeliculaDetailFragment().apply {
                        arguments = Bundle().apply {
                            putString(PeliculaDetailFragment.ARG_ITEM_ID, item.id.toString())
                        }
                    }
                    parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.pelicula_detail_container, fragment)
                        .commit()
                } else {
                    val intent = Intent(v.context, PeliculaDetailActivity::class.java).apply {
                        putExtra(PeliculaDetailFragment.ARG_ITEM_ID, item.id.toString())
                    }
                    v.context.startActivity(intent)
                }
            }
        }
```

![image](../images/activityFragments.png)

## Un último retoque

Para que se visualice el título de la película seleccionada en la tablet agregamos esta línea:

```kt
class PeliculaDetailFragment : Fragment() {

    ...

    override fun onCreate(savedInstanceState: Bundle?) {
        ...

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                ...
                activity?.toolbar_layout?.title = item?.titulo

                // nueva línea
                activity?.toolbar?.title = item?.titulo
                //
            }
        }
    }
```

Ya que `toolbar_layout` forma parte de la activity de detail `activity_pelicula_detail`:

```xml
        <android.support.design.widget.CollapsingToolbarLayout
                android:id="@+id/toolbar_layout"
```

mientras que `toolbar` es el identificador para `activity_pelicula_list`:

```xml
        <android.support.v7.widget.Toolbar
                android:id="@+id/toolbar"
```

## Demo final

![image](../videos/demoTablet.gif)
