# Objetivo

Tenemos por el momento una aplicación que muestra una lista de películas, queremos acceder a la información detallada de una película.

¿Cómo navegamos la aplicación para ver el detalle? Una opción es

* en la lista, incorporar la acción mediante un botón o link
* al hacer click sobre un elemento, navegamos a la vista de detalle

Elegimos la segunda opción, porque la primera quita espacio para mostrar más info de una película, además de la repetición de los botones.

## User Experience

Estas decisiones forman parte de la “experiencia de usuario” o UX por sus siglas en inglés, _User eXperience_. La parte visual juega un papel muy importante en el desarrollo de este tipo de aplicaciones, donde podemos

* respetar el comportamiento que tienen las otras aplicaciones Android o el sistema operativo sobre el que estemos desarrollando, con la ventaja de que estamos construyendo aplicaciones nativas y no híbridas
* salirnos del esquema y trabajar de una única manera en la aplicación independientemente del dispositivo / tecnología en el que corra. Esta estrategia es válida si nuestra intención es que los usuarios puedan cambiar de aparato, sistema operativo, etc. sin notar cambios en la manipulación de la aplicación, pero sugiere un período de adaptación del usuario a nuestra aplicación, por lo que hay que invertir tiempo en que sea lo suficientemente intuitiva y permita la menor cantidad de desplazamientos, algo que en las aplicaciones “tradicionales” de escritorio o web no era una variable de tanto peso.

Dejamos algunas lecturas recomendadas:

* http://www.usability.gov/what-and-why/user-experience.html
* http://developer.android.com/design/patterns/navigation.html
* http://developer.android.com/design/patterns/navigation-drawer.html

## Pasaje de información entre actividades

Cuando creamos un proyecto de tipo Master/Detail, el IDE nos generó varias líneas que se encargan de resolver este tema. Ahora vamos a estudiarlo para saber cómo funciona y ver si es necesario hacer algún ajuste. Primero que nada tenemos que ver cómo le llega la información desde la actividad Lista hacia la Detalle, en la clase PeliculaAdapter (que originalmente se llamaba SimpleItemRecyclerViewAdapter), dentro de la definición PeliculaListActivity. Dicha clase tiene un observer sobre los elementos de la list view, definido por la variable `onClickListener` y se inicializa de la siguiente manera:

```kt
    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as Pelicula // <-- casteamos a Pelicula
            if (twoPane) {
                val fragment = PeliculaDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(PeliculaDetailFragment.ARG_ITEM_ID, item.id.toString()) // <-- pasamos a String
                    }
                }
                parentActivity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.pelicula_detail_container, fragment)
                    .commit()
            } else {
                val intent = Intent(v.context, PeliculaDetailActivity::class.java).apply {
                    putExtra(PeliculaDetailFragment.ARG_ITEM_ID, item.id.toString()) // <--- pasamos a String
```

Antes de hablar de la navegación, estudiemos qué información estamos queriendo tomar para identificar la película que el usuario seleccionó, puede ser

* la posición del elemento
* el identificador de la película
* un objeto película

¿Qué es lo que resultaría más cómodo? Uno podría valorar a priori tener un objeto película, pero hay que tener en cuenta que la lista de películas puede hacerse mediante un servicio REST, que quizás no nos entregue toda la información de la película, sino que use una representación en JSON reducida, para bajar la cantidad de datos a transmitir.

## Navegación

Si miramos nuevamente el método init en `PeliculaListActivity.kt`, vemos que hay un if:

```kt
init {
    onClickListener = View.OnClickListener { v ->
        val item = v.tag as Pelicula
        if (twoPane) {
            ...
        } else {
            ...
        }
    }
}
```

Esta división se da porque

* si estamos testeando la aplicación con un dispositivo cuyo tamaño nos permite unificar en una sola actividad el fragmento lista y el detalle, estamos en modo **two-pane**. [Más adelante](./activitiesFragmentsDispositivos.md) estudiaremos su comportamiento.
* los dispositivos como el teléfono, que tienen una pantalla de tamaño chico, trabajan en modo single-pane, entonces hay que navegar hacia la vista de detalle.

Nos concentraremos por el momento en la solución **single-pane**, que crea la navegación hacia la vista detalle mediante el concepto Intent, una abstracción que representa cualquier tipo de operación. El intent define un método putExtra donde pasamos parámetros de una actividad a otra, en este caso el id de la película seleccionada.

En la actividad de detalle recibimos el id y se lo pasamos al fragment:

```kt
class PeliculaDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle) {
        ...
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = PeliculaDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(
                        PeliculaDetailFragment.ARG_ITEM_ID,
                        intent.getStringExtra(PeliculaDetailFragment.ARG_ITEM_ID)
                    )
                }
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.pelicula_detail_container, fragment)
                .commit()
        }
    }
```

En el fragment transformamos el id (un string) en un objeto película, delegando la búsqueda en la clase `RepoPeliculas`:

```kt
class PeliculaDetailFragment : Fragment() {

    private var item: Pelicula? = null

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                item = RepoPeliculas.getPelicula(it.getString(ARG_ITEM_ID)!!.toLong())
                activity?.toolbar_layout?.title = item?.titulo
            }
        }
    }
```

Entonces

* tomamos de los argumentos el ARG_ITEM_ID que tiene como string el id de la película
* forzamos con el operador `!!` a que [no pueda ser nulo](https://kotlinlang.org/docs/reference/null-safety.html) para convertirlo a Long
* luego le pasamos el id al repo y tenemos la película
* para mostrar su título en la toolbar de la vista de detalle

## Detalle custom de una película

Para terminar de corregir lo que falta, en el método onCreateView vamos a mostrar el resto de los campos:

```kt
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup,
        savedInstanceState: Bundle
    ): View {
        val rootView = inflater.inflate(R.layout.pelicula_detail, container, false)

        item?.let {
            rootView.pelicula_actores.text = it.actores
            rootView.pelicula_sinopsis.text = it.sinopsis
            rootView.pelicula_genero.text = it.descripcionGenero
            rootView.imgGenero.setImageDrawable(resources.getDrawable(
                    GeneroAdapter.getIconoGenero(it), null))
        }

        return rootView
    }
```

Esto requiere que en el archivo `pelicula_detail.xml` definamos los TextView para mostrar información de actores, sinopsis y género, y un ImageView para poder visualizar la imagen asociada a dicho género:

```xml
<LinearLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

    <LinearLayout
            xmlns:android="http://schemas.android.com/apk/res/android"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal">

        <ImageView xmlns:android="http://schemas.android.com/apk/res/android"
                   android:id="@+id/imgGenero"
                   android:layout_width="@dimen/icono"
                   android:layout_height="@dimen/icono"
                   android:padding="8dp"/>

        <TextView xmlns:android="http://schemas.android.com/apk/res/android"
                  xmlns:tools="http://schemas.android.com/tools" android:id="@+id/pelicula_genero"
                  style="?android:attr/textAppearanceLarge" android:layout_width="match_parent"
                  android:layout_height="match_parent" android:fontFamily="typeface:BOLD" android:padding="16dp"
                  android:textIsSelectable="true" tools:context=".PeliculaDetailFragment"/>

    </LinearLayout>

    <TextView xmlns:android="http://schemas.android.com/apk/res/android" xmlns:tools="http://schemas.android.com/tools"
              android:id="@+id/pelicula_actores" style="?android:attr/textAppearanceMedium"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:padding="16dp"
              android:textIsSelectable="true"
              tools:context=".PeliculaDetailFragment"/>
    <TextView xmlns:android="http://schemas.android.com/apk/res/android" xmlns:tools="http://schemas.android.com/tools"
              android:id="@+id/pelicula_sinopsis" style="?android:attr/textAppearanceMedium"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:padding="16dp"
              android:textIsSelectable="true"
              tools:context=".PeliculaDetailFragment"/>
</LinearLayout>
```

Este layout es interesante, porque permite mezclar dos LinearLayout:

* el primero es vertical y hace que los datos del género, los actores y la sinopsis estén uno abajo del otro
* el segundo permite ubicar en la misma fila la imagen del género y la descripción del género, en un container que está dentro del layout vertical

![image](../images/Layout_Detail_Pelicula.png)

## Demo

Vemos ahora sí cómo funciona la navegación de la vista master a la detalle:

![video](../videos/detailCustom2.gif)

## Navegación - gráfico general

![image](../images/NavegacionMasterDetail.png)

## Tareas para el lector

* En lugar de pasar un id como string, podríamos pasar una película desde la actividad master a la detail: eso evitaría hacer una búsqueda en el repositorio.
* Agregar un EditText para que el usuario ingrese un valor a filtrar en la búsqueda, hacerlo
  * primero, con un botón de búsqueda
  * luego, a medida que el usuario va escribiendo
  * y por último que se pueda configurar mediante un checkbox
* Modificar el layout de manera que quede la imagen y la descripción del género a izquierda y a derecha los actores y la sinopsis
