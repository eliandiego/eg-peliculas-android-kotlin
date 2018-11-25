# Master-Detail. Ventana master. List View con layout default y custom

## El ejemplo

Queremos visualizar una lista de películas y al hacer click sobre una nos interesa ver su información completa.

## Crear un proyecto Master/Detail

![creación](../videos/createMasterDetail.gif)

Generamos un nuevo proyecto: File > New > New Project... elegimos un nombre representativo "PeliculasApp", el company name. Luego elegimos el dispositivo destino (Phone and Tablet).

Entonces elegimos como tipo de proyecto un "Master / Detail Flow" y configuramos:

* **Object Kind**: "Pelicula"
* **Object Kind Plural**: "Peliculas"
* **Title**: "Películas"

## Activities y Fragments

Al finalizar la actividad, vemos que se generaron 3 vistas:

* PeliculaListActivity
* PeliculaDetailActivity
* PeliculaDetailFragment

El fragment permite bajar la granularidad de la actividad en partes más pequeñas. La activity puede contener uno o más fragments. De esa manera podemos trabajar los componentes visuales de diferente manera para un smartphone o una tablet. Por el momento sabemos que

La activity PeliculaList define

* el título,
* los action buttons, en principio ninguno,
* y la navegación. Por el momento pensemos en una aplicación para smartphones, entonces la navegación consistiría en que cuando el usuario selecciona una película eso dispara una actividad nueva donde se muestra el detalle de la película (PeliculaDetailActivity + PeliculaDetailFragment). [Más adelante](./activitiesFragmentsDispositivos.md) veremos que esta separación actividad / fragmento permite combinarlos para diferentes dispositivos.

## Lista de Películas

De movida podemos ejecutar la aplicación gracias a todo el código [boilerplate](https://www.google.com/url?q=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FBoilerplate_code&sa=D&sntz=1&usg=AFQjCNEq3kj_eHxD1mqda7LsxRzpQk1LEw) generado:

![peliculas list](../images/peliculasList.png)

Solo hay que corregir un pequeño detalle, en la clase `PeliculaDetailFragment.kt`, dentro del método onCreate:

```kt
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    arguments?.let {
        if (it.containsKey(ARG_ITEM_ID)) {
            // así está
            // item = DummyContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]
            // así la cambiamos
            item = DummyContent.ITEM_MAP[it.getString(ARG_ITEM_ID)!!]
            activity?.toolbar_layout?.title = item?.content
        }
    }
}
```

El [operador !! de Kotlin](https://kotlinlang.org/docs/reference/null-safety.html) nos asegura que item deba tener algún valor, de lo contrario lanzará una `Null Pointer Exception` en esa línea.

La lista de ítems se define en la `activity_pelicula_list.xml`:

```xml
<android.support.design.widget.CoordinatorLayout ...>
    <FrameLayout
            android:id="@+id/frameLayout"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layout_behavior="@string/appbar_scrolling_view_behavior">

        <include layout="@layout/pelicula_list"/>
    </FrameLayout>
```

El archivo de include `pelicula_list.xml` contiene un [Recycler View](https://developer.android.com/guide/topics/ui/layout/recyclerview):

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.v7.widget.RecyclerView xmlns:android="http://schemas.android.com/apk/res/android"
                                        xmlns:app="http://schemas.android.com/apk/res-auto"
                                        xmlns:tools="http://schemas.android.com/tools"
                                        android:id="@+id/pelicula_list"
                                        android:name="org.uqbar.peliculasapp.PeliculaListFragment"
                                        android:layout_width="match_parent"
                                        android:layout_height="match_parent"
                                        android:layout_marginLeft="16dp"
                                        android:layout_marginRight="16dp"
                                        app:layoutManager="LinearLayoutManager"
                                        tools:context=".PeliculaListActivity"
                                        tools:listitem="@layout/pelicula_list_content"/>
```

Como vemos, este container define un **list item** cuyo contenido define una [ListView](https://developer.android.com/guide/topics/ui/layout/listview.html) en un archivo aparte: pelicula_list_content.xml. Sí, demasiadas indirecciones para un simple master/detail.

¿Dónde se llena la lista de elementos de la ListView? En la activity de la lista de películas:

```kt
class PeliculaListActivity : AppCompatActivity() {
```

Y específicamente en el método onCreate, se llama al método setupRecyclerView que configura nuestro container donde se encuentra la list view antes mencionada:

```kt
private fun setupRecyclerView(recyclerView: RecyclerView) {
    recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS, twoPane)
}
```

La clase SimpleItemRecyclerViewAdapter tendrá como misión responder a dos eventos:

* en la inicialización, para generar cada una de las filas de la lista de películas (con la información asociada a cada línea)
* cuando el usuario haga click sobre una película, debe navegar hacia la vista de detalle.

Reemplazamos los DummyContents.ITEMS por una lista de películas de un Repositorio creado para la ocasión:

```kt
private fun setupRecyclerView(recyclerView: RecyclerView) {
    recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, RepoPeliculas.getPeliculas(null, 10), twoPane)
}
```

Esto requiere modificar también la clase SimpleItemRecyclerViewAdapter que renombraremos a **PeliculaAdapter**, más apropiada para nuestro dominio:

```kt
class SimpleItemRecyclerViewAdapter(
    private val parentActivity: PeliculaListActivity,
    private val values: List<Pelicula>,   // <-- cambiamos el tipo de List<DummyContent.DummyItem>

    ...

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id.toString()   // el id como String
        holder.contentView.text = item.titulo     // el título de la película o lo que deseemos
```

![images](../images/peliculasList2.png)

## Otra variante

Podemos construir nuestro propio fragmento custom, por ejemplo para mostrar

* el título de la película
* la lista de actores
* y una línea horizontal que actúe como separador

El archivo _pelicula_list_content.xml_ tiene la definición de lo que muestra cada ítem. Modificamos los id de los primeros textview por `lblPelicula` y `lblActores`. También agregamos propiedades específicas para los textos (size = 14 para el título, 12 para el género, que además se visualizará en _italic_). El layout principal es Linear, pero ahora vertical. Agregamos al final una línea horizontal utilizando el color primario del tema elegido (esto permite asociarlo a la paleta de colores elegida).

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:orientation="vertical">

    <TextView
            android:id="@+id/lblPelicula"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:textSize="14dp"
            android:paddingLeft="@dimen/text_padding"
            android:textAppearance="?attr/textAppearanceListItem"/>

    <TextView
            android:id="@+id/lblActores"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_margin="@dimen/text_margin"
            android:textSize="12dp"
            android:textStyle="italic"
            android:textAppearance="?attr/textAppearanceListItem"/>

    <View
            android:layout_width="match_parent"
            android:layout_height="1dp"
            android:background="@color/colorPrimaryDark"/>

</LinearLayout>
```

También podemos agregar un padding específico al label de la película, externalizando el valor para no asociarlo solamente a esta vista: tenemos para ello un archivo específico `res/values/dimens.xml`. Vemos cómo el Android Studio nos ayuda a lograr esto:

![image](../videos/padding.gif)

### Corrigiendo el binding de una fila

Esto rompe las líneas de PeliculaAdapter:

```kt
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.id_text          // NO COMPILA
        val contentView: TextView = view.content     // NO COMPILA
    }
```

Corregimos esto:

```kt
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pelicula = values[position]
        holder.peliculaView.text = pelicula.titulo
        holder.actoresView.text = pelicula.actores

        with(holder.itemView) {
            tag = pelicula
            setOnClickListener(onClickListener)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val peliculaView: TextView = view.lblPelicula
        val actoresView: TextView = view.lblActores
    }
```

La relación entre vista y modelo de vista se da en el método onCreateViewHolder de SimpleItemRecyclerViewAdapter:

```kt
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pelicula_list_content, parent, false)
        return ViewHolder(view)
    }
```

Las nuevas versiones separan

* la configuración del xml que se va a usar para cada ítem
* vs. el binding específico de los valores que están dentro de ese xml (en nuestro caso título y actores)

Vemos cómo queda nuestra list view custom:

![image](../images/peliculasListAdapter.png)

![image](../images/listaPeliculasArquitectura.png)
