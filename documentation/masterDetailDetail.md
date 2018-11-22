# Objetivo

Tenemos por el momento una aplicación que muestra una lista de películas, queremos acceder a la información detallada de una película.

¿Cómo navegamos la aplicación para ver el detalle? Una opción es

* en la lista, incorporar la acción mediante un botón o link
* al hacer click sobre un elemento, visualizamos el elemento

Elegimos la segunda opción, porque la primera fuerza a repetir las acciones para cada línea y eso nos quita espacio para mostrar más info de una película.

# User Experience

Estas decisiones forman parte de la “experiencia de usuario” o UX por sus siglas en inglés, User eXperience. La parte visual juega un papel muy importante en el desarrollo de este tipo de aplicaciones, donde podemos

* respetar el comportamiento que tienen las otras aplicaciones Android o el sistema operativo sobre el que estemos desarrollando, con la ventaja de que estamos construyendo aplicaciones nativas y no híbridas
* salirnos del esquema y trabajar de una única manera en la aplicación independientemente del dispositivo / tecnología en el que corra. Esta estrategia es válida si nuestra intención es que los usuarios puedan cambiar de aparato, sistema operativo, etc. sin notar cambios en la manipulación de la aplicación, pero sugiere un período de adaptación del usuario a nuestra aplicación, por lo que hay que invertir tiempo en que sea lo suficientemente intuitiva y permita la menor cantidad de desplazamientos, algo que en las aplicaciones “tradicionales” de escritorio o web no era una variable de tanto peso.

Dejamos algunas lecturas recomendadas:

* http://www.usability.gov/what-and-why/user-experience.html
* http://developer.android.com/design/patterns/navigation.html
* http://developer.android.com/design/patterns/navigation-drawer.html

# Pasaje de información entre actividades

Cuando creamos un proyecto de tipo Master/Detail, el IDE nos generó varias líneas que se encargan de resolver este tema. Ahora vamos a estudiarlo para saber cómo funciona y ver si es necesario hacer algún ajuste. Primero que nada tenemos que ver cómo le llega la información desde la actividad Lista hacia la Detalle:

```kt
override fun onListItemClick(listView: ListView, view: View, position: Int, id: Long) {
    super.onListItemClick(listView, view, position, id)

    // Notify the active callbacks interface (the activity, if the
    // fragment is attached to one) that an item has been selected.
    super.onListItemClick(listView, view, position, id)
    mCallbacks.onItemSelected(pelicula)
}
```

_LibroListFragment.kt_

Aquí se dispara el evento a los observers o callbacks que están interesados en escuchar cuando el usuario selecciona una película.

¿Quién es el interesado? La Activity que muestra la lista:

```kt
class PeliculaListActivity : AppCompatActivity(), PeliculaListFragment.Callbacks {
```

Pero antes de seguir debemos cambiar esta línea:

```kt
mCallbacks.onItemSelected(DummyContent.ITEMS.get(position).id)
```

porque viene del código boilerplate que genera Android Studio para un proyecto master/detail flow. Recibimos la posicion del elemento seleccionado, y un id entre otras cosas, ¿qué tenemos que pasar?

* la posición del elemento
* el identificador de la película
* un objeto película

¿Qué es lo que resultaría más cómodo? Uno podría valorar a priori tener un objeto película, pero hay que tener en cuenta que la lista de películas puede hacerse mediante un servicio REST, que quizás no nos entregue toda la información de la película, sino que use una representación en json reducida, para bajar la cantidad de datos a transmitir. Aún así, podríamos utilizar a la película como abstracción entre la vista master y la vista detalle, teniendo en cuenta que será necesario una nueva búsqueda para traer la información completa de una película.

# Navegación

Para poder obtener el identificador de la película, tenemos que modificar la implementación default que hereda PeliculaAdapter de ArrayAdapter, donde:

```kt
override fun getItemId(position: Int): Long {
    return position
}
```

Ok, entonces lo modificamos para obtener el identificador real de nuestro objeto película. Como ArrayAdapter encapsula la colección de elementos que muestra la ListView, esto nos obliga a utilizar el método getItem para luego pedirle el id:

```kt
override fun getItemId(position: Int): Long {
    return getItem(position)!!.id!!
}
```

Kotlin viene con un operador !! que permite [manejar en forma segura valores nulos](https://kotlinlang.org/docs/reference/null-safety.html).

Ahora sí el método onListItemClick recibe como parámetro el identificador posta de la película y lo puede utilizar:

```kt
override fun onListItemClick(listView: ListView, view: View, position: Int, id: Long) {
    super.onListItemClick(listView, view, position, id)

    // Notify the active callbacks interface (the activity, if the
    // fragment is attached to one) that an item has been selected.
    mCallbacks.onItemSelected("" + id)
}
```

_PeliculaListFragment.kt_

El parámetro id, no obstante es un String, lo que nos obliga a hacer un casteo: String.valueOf(id) o bien "" + id, que tiene el mismo efecto.

La actividad debe implementar el método onItemSelected. Si miramos el método, vemos que hay un if:

```kt
override fun onItemSelected(pelicula: Pelicula?) {
    if (mTwoPane) {
        // In two-pane mode, show the detail view in this activity by
        // adding or replacing the detail fragment using a
        // fragment transaction.
        val arguments = Bundle()
        arguments.putString(PeliculaDetailFragment.ARG_ITEM_ID, id)
        val fragment = PeliculaDetailFragment()
        fragment.arguments = arguments
        supportFragmentManager.beginTransaction()
                .replace(R.id.pelicula_detail_container, fragment)
                .commit()

    } else {
        // In single-pane mode, simply start the detail activity
        // for the selected item ID.
        val detailIntent = Intent(this, PeliculaDetailActivity::class.java) // PeliculaDetailActivity.class de java
        detailIntent.putExtra(PeliculaDetailFragment.ARG_ITEM_ID, id)
        startActivity(detailIntent)
    }
}
```

_PeliculaListActivity.kt_

Esta división se da porque

* si estamos testeando la aplicación con un dispositivo cuyo tamaño nos permite unificar en una sola actividad el fragmento lista y el detalle, estamos en modo two-pane. Más adelante estudiaremos su comportamiento.
* los dispositivos como el teléfono, que tienen una pantalla de tamaño chico, trabajan en modo single-pane, entonces hay que navegar hacia la vista de detalle. 

Nos concentraremos por el momento en la solución single-pane, que crea la navegación hacia la vista detalle mediante el concepto Intent, una abstracción que representa cualquier tipo de operación. El intent define un método putExtra donde pasamos parámetros de una actividad a otra, en este caso el id de la película seleccionada.

En la actividad de detalle recibimos el id y se lo pasamos al fragment: