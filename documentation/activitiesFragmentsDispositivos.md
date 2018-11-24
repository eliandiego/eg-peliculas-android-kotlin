# La aplicación en distintos dispositivos

Hasta el momento hemos visto la aplicación en un emulador de un dispositivo similar al de un teléfono. ¿Qué pasa si probamos con un dispositivo más grande, una tablet, en lugar de emular un teléfono?

## Configurando un nuevo dispositivo

En el menú Tools > AVD Manager o bien al ejecutar nuestra aplicación, podemos generar un nuevo dispositivo:

![video](../../videos/configuringTablet.gif)

A partir de aquí, podemos emular una tablet o un celular, con resultados quizás diferentes.

## Corrigiendo la visualización para tablets

De primera intención no vemos mucha diferencia, salvo que tenemos un emulador de nueve pulgadas. Lo que sucede es que nuestra vista `pelicula_list (w900dp)` quedó igual que antes:

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
