# Master-Detail. Ventana master. List View con layout default y custom

## El ejemplo

Queremos visualizar una lista de películas y al hacer click sobre una nos interesa ver su información completa.

## Crear un proyecto Master/Detail

Generamos un nuevo proyecto: File > New > New Project... elegimos un nombre representativo "PeliculasApp", el company name. Luego elegimos el dispositivo destino (Phone and Tablet).

Entonces elegimos como tipo de proyecto un "Master / Detail Flow" y configuramos:

* Object Kind: "Pelicula"
* Object Kind Plural: "Peliculas"
* Title: "Películas"

## Activities y Fragments

Al finalizar la actividad, vemos que se generaron 4 vistas:

* PeliculaListActivity
* PeliculaListFragment
* PeliculaDetailActivity
* PeliculaDetailFragment

El fragment permite bajar la granularidad de la actividad en partes más pequeñas. La activity puede contener uno o más fragments. De esa manera podemos trabajar los componentes visuales de diferente manera para un smartphone o una tablet. Por el momento sabemos que 

* la activity PeliculaList define 
  * el título, 
  * los action buttons, en principio ninguno, 
  * y la navegación. Por el momento pensemos en una aplicación para smartphones, entonces la navegación consistiría en que cuando el usuario selecciona una película eso dispara una actividad nueva donde se muestra el detalle de la película (PeliculaDetailActivity + PeliculaDetailFragment). [Más adelante](./activitiesFragmentsDispositivos.md) veremos que esta separación actividad / fragmento permite combinarlos para diferentes dispositivos.
* el fragment PeliculaList define la vista con la lista de películas

