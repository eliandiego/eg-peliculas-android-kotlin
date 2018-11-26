package org.uqbar.peliculasapp.repo

import android.util.Log
import org.uqbar.peliculasapp.domain.Genero
import org.uqbar.peliculasapp.domain.Pelicula
import java.util.*

/**
 * Created by fernando on 1/11/2017.
 */
/**
 * Created by fernando on 26/10/15.
 */
object RepoPeliculas {

    private val peliculas: MutableList<Pelicula>
    private val generos: MutableMap<String, Genero>
    private val MAX_RESULTS = 10

    val instance = this

    val maximoId: Long?
        get() = peliculas.size.toLong() + 1

    val titulosDePeliculas: List<String>
        get() {
            val result = ArrayList<String>()
            for (pelicula in getPeliculas(null, 10)) {
                result.add(pelicula.titulo)
            }
            return result
        }

    init {
        peliculas = ArrayList<Pelicula>()
        generos = HashMap<String, Genero>()
        init()
    }

    /**
     *
     * Inicializacion Juego de Datos
     */
    private fun init() {
        agregarPelicula(
            Pelicula(
                "Arthur 1: Los Minimoyz",
                getGenero("Infantil"),
                "Justo cuando su abuela (Mia Farrow) está a punto de perder la casa en la que vive, Arthur (Freddie Highmore), un niño de 10 años, recuerda que su abuelo le había hablado de un gran tesoro oculto en la tierra de los Minimoys, un universo de pequeños seres fantásticos que viven en armonía con la naturaleza, un mundo tan lejano y sin embargo tan cercano e invisible al ojo humano. Decidido a salvar a su abuela, Arthur conseguirá la llave para entrar en Ã©l y allí conocerá a la princesa Selenia y a su hermano Betameche. Juntos buscarán el tesoro escondido y deberán enfrentarse al diabólico M, en una aventura que nos enseñará que, en ocasiones, los pequeños hÃ©roes son capaces de conseguir grandes logros.Duración: 103 min",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Arthur 2: La venganza de Maltazar",
                getGenero("Infantil/Anim"),
                "Secuela de Arthur y los Minimoys (2006). En esta ocasión el protagonista viaja, junto a la Princesa Selenia y su hermano Betameche, en busca de la ciudad prohibida de Necrópolis, hogar del malvado brujo Maltazard. Â¡Ayuda! Este grito de alarma inscrito en un grano de arroz y dejado por una araña mensajera pone a Arthur en alerta: Los Minimoys están en peligro. Â¡No hay ni un segundo que perder!. Justo en este momento el padre de Arthur decide partir antes de lo previsto, y espera impaciente al volante de su coche. Arthur tiene el tiempo justo para prevenir a su abuelo Archibald para que acuda a la llamada de auxilio en su lugar pero imaginar a SÃ©lÃ©nia en peligro es difícil de soportar. Con la complicidad de su perro Alfred, Arthur engaña la vigilancia de sus padres y regresa en plena noche a la casa de sus abuelos. Sin embargo, una nube se aproxima peligrosamente a la luna en la media noche, la hora en la que sus rayos deben alcanzar el telescopio de Archibald y abrir la puerta hacia el mundo de los Minimoys.Duración: 93 min",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Arthur 3: La guerra de los dos mundos",
                getGenero("Infantil/Anim"),
                "Tercera y última entrega de la trilogía de Arthur y los Minimoys. Maltazard ha conseguido infiltrarse en el mundo de los hombres. Su objetivo es reclutar un ejÃ©rcito de gigantescos secuaces y dominar el universo. Arthur es quizá el único capaz de frustrar sus planes, pero primero tendrá que volver a su habitación y recuperar su tamaño normal. Aunque sigue atrapado en un diminuto cuerpo de Minimoy, contará con la ayuda de Selenia y Betameche y, sorprendentemente, tambiÃ©n con la de Darkos, el hijo de Maltazard, que afirma estar de su lado. A pie, en bici, en coche o incluso en una Harley Davidson, Arthur y sus amigos emplearán cualquier medio a su alcance para librar la batalla final contra MaltazardDuración: 100 min",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Kill Bill: The whole bloody affair",
                getGenero("Accion"),
                "Para amantes de Kill Bill 1 y 2?, Kill bill the whole bloody affair contiene ambas historias en una sola como fue originalmente concebida por Quentin Tarantino. Contiene el Ã©pico combate en The House Of Blue Leaves sin censurar, escenas de animación extendida y alguna que otra cosita extra.Duración: 240 min",
                "Uma Thurman, Lucy Liu, Daryl Hannah, Vivica A. Fox, Sonny Chiba, Chiaki Kuriyama, Michael Bowen, Julie Dreyfus, Michael Parks, David Carradine, Michael Madsen"
            )
        )
        agregarPelicula(
            Pelicula(
                "The Big Bang Theory: Temporada 3",
                getGenero("Series"),
                "",
                "Johnny Galecki, Jim Parsons, Kaley Cuoco, Simon Helberg, Kunal Nayyar"
            )
        )
        agregarPelicula(
            Pelicula(
                "The Big Bang Theory: Temporada 4",
                getGenero("Series"),
                "Pack 3 dvds. Cuarta temporada de The Big Bang Theory. La serie que trata sobre dos nerds dos cerebritos que comparten piso: Leonard (Johnny Galecki) y Sheldon (Jim Parsons). Aunque ambos están doctorados en física teórica y son capaces de calcular la probabilidad sobre la existencia de universos paralelos, no tienen ni la menor idea de cómo relacionarse con el resto del mundo, especialmente con las chicas.Duración: 501 min",
                "Johnny Galecki, Jim Parsons, Kaley Cuoco, Simon Helberg, Kunal Nayyar"
            )
        )
        agregarPelicula(
            Pelicula(
                "Kickboxer",
                getGenero("Accion"),
                "Cuando Tong Po, el sanguinario campeón de Kickboxing, deja paralítico a su hermano, Eric jura vengarse del hombre a quienes todos consideran invencible, aunque le cueste la vida. Duración: 103 min",
                "Jean-Claude Van Damme, Dennis Alexio, Dennis Chan, Tong Po, Haskell Anderson, Rochelle Ashana, Steve Lee"
            )
        )
        agregarPelicula(
            Pelicula(
                "El expreso de medianoche",
                getGenero("Drama"),
                "Billy Hayes (Brad Davis), un joven estadounidense, es detenido en el aeropuerto de Estambul cuando se dispone a subir a un avión con varios paquetes de hachís. Acusado de uno de los delitos más graves en Turquía, Billy es condenado a cuatro años de cárcel, donde sufrirá terribles penalidades en un sistema penal brutal e inhumano.Duración: 121 min",
                "Brad Davis, John Hurt, Bo Hopkins, Irene Miracle, Randy Quaid, Paolo Bonacelli"
            )
        )
        agregarPelicula(
            Pelicula(
                "5 días sin Nora",
                getGenero("Comedia"),
                "Antes de morir, Nora elabora un plan para que JosÃ©, su ex marido, tenga que hacerse cargo del entierro. Sin embargo, la única falla del plan, una misteriosa foto olvidada debajo de la cama, provocará un inesperado desenlace haciÃ©ndonos descubrir que las más grandes historias de amor se esconden en los lugares más pequeños.Duración: 93 min",
                "Fernando Luján, Ari Brickman, Verónica Langer, Enrique Arreola, Max Kerlow, Cecilia Suárez, Juan Carlos Colombo"
            )
        )
        agregarPelicula(
            Pelicula(
                "Los intocables",
                getGenero("Clasicos"),
                "Chicago, años 30. Ã‰poca de la Ley Seca. El idealista agente federal Eliot Ness persigue implacablemente al gángster Al Capone. La falta de pruebas le impide acusarlo de asesinato, extorsión y comercio ilegal de alcohol, pero encontrará un medio para inculparlo por otra clase de delitos.Duración: 119 min",
                "Kevin Costner, Sean Connery, Robert De Niro, Andy García, Charles Martin Smith, Billy Drago, Patricia Clarkson, Brad Sullivan, Del Close, Michael Byrne, Richard Bradford, Clem Caserta "
            )
        )
        agregarPelicula(
            Pelicula(
                "Baby Einstein: Baby Beethoven (Sinfonia musical)",
                getGenero("Infantil / Peli"),
                "Baby Beethoven La Sinfonía de la Diversión es una experiencia cautivadora que muestra a los bebÃ©s y a los niños pequeños la belleza y los beneficios de la música clásica, a la vez que les fascina con imágenes estimulantes y llenas de color. Tanto a ti como a tu hijo os encantarán estas cautivadoras versiones de composiciones clásicas de Ludwig van BeethovenDuración: 59 min",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Doctor Dolittle 1",
                getGenero("Comedia"),
                "John Dolittle es un peculiar veterinario que tiene el don de comprender lo que dicen los animales. Mientras su consulta se desborda de mascotas de toda especie, sus colegas piensan que se ha vuelto loco de remate. Una taquillera comedia realizada a mayor gloria de Mr. MurphyDuración: 85 min",
                "Eddie Murphy, Ossie Davis, Oliver Platt, Peter Boyle, Richard Schiff, Kristen Wilson"
            )
        )
        agregarPelicula(
            Pelicula(
                "Wall Street",
                getGenero("Drama"),
                "Bud Fox (Charlie Sheen) es un joven y ambicioso corredor de bolsa que consiguió terminar sus estudios universitarios gracias a su esfuerzo y al de su padre (Martin Sheen), mÃ©canico y jefe de sindicato. Su mayor deseo es trabajar con un hombre al que admira, Gordon Gekko (Michael Douglas), un individuo sin escrúpulos que se ha hecho a sí mismo y que en poco tiempo ha conseguido amasar una gran fortuna en el mundo de la bolsa. Gracias a su insistencia, Bud consigue introducirse en el círculo privado del todopoderoso Gekko, y comienza a colaborar con Ã©l en sus negocios e inversionesDuración: 120 min",
                "Michael Douglas, Charlie Sheen, Daryl Hannah, Terence Stamp, Martin Sheen, Hal Holbrook"
            )
        )
        agregarPelicula(
            Pelicula(
                "Rush Hour 1",
                getGenero("Accion"),
                "Dos detectives diferentes son asignados en un mismo caso. Ambos tendrán que adaptarse a las costumbres del otro para poder concluir su trabajo con Ã©xito, pero no va a ser una tarea fácil. El detective inspector Lee (Jackie Chan) es el rey de las artes marciales y pertenece a la Royal Hong Kong Police. Su pupila preferida es la hija de once años del Cónsul chino, de la cual el inspector Lee es su guardaespaldas y su mejor amigo. Esta muchacha es capturada y llevada a los Estados Unidos por un peligroso grupo criminal. Hasta allí tendrá que viajar el inspector y, donde se unirá a la investigación con un departamento del FBI y, concretamente, al detective James Carter (Tucker), un arrogante e impulsivo agente de policía. La explosiva pareja tendrá que enfrentarse a una banda de criminales muy peligrosa, y contarán con la ayuda de Tania Johnson (Elizabeth Peña), una experta en artefactos explosivos",
                "Jackie Chan, Chris Tucker, Tom Wilkinson, Chris Penn, Elizabeth Peña, Philip Baker Hall"
            )
        )
        agregarPelicula(
            Pelicula(
                "Rush Hour 2",
                getGenero("Accion"),
                "El detective James Carter se encuentra de vacaciones en Hong Kong con su buen amigo el detective Lee. Mientras Carter está ansioso por dedicarse a la juerga y las mujeres, Lee tiene que enfrentarse con un jefe de las Tríadas, responsable del asesinato de dos funcionarios de la embajada norteamericana. Conforme avancen en la investigación, descubrirán una trama de distribución de billetes falsos",
                "Jackie Chan, Chris Tucker, John Lone, Alan King, Roselyn Sánchez, Harris Yulin, Zhang Ziyi"
            )
        )
        agregarPelicula(
            Pelicula(
                "The Big Bang theory: Temporada 2",
                getGenero("Series"),
                "Pack 4 dvds. Leonard y Sheldon son dos físicos que comparten trabajo y apartamento. La serie comienza con la mudanza de Penny, su nueva y atractiva vecina, y hace hincapiÃ© en la dificultad de los físicos para relacionarse con personas fuera de su entorno para dar lugar a situaciones cómicas. Ambos son brillantes en su trabajo, pero tanto ellos como sus amigos Howard y Rajesh son representados como unos completos Geeks, muy alejados de las inquietudes de la gente corriente. Duración: 481 min",
                "Johnny Galecki, Kunal Nayyar, Kaley Cuoco, Simon Helberg, Jim Parsons"
            )
        )
        agregarPelicula(
            Pelicula(
                "La mosca",
                getGenero("C.Ficcion"),
                "El excÃ©ntrico científico Seth Brundle invita a la reportera Veronica Quaife para que sea testigo de su más reciente experimento. Una revolucionaria máquina que permite transportar materia a travÃ©s del espacio. Luego de transportar exitosamente objetos inanimados y animales, decide dar un paso más y experimentar con un ser humano. Seth entra en la máquina sin saber las terribles consecuencias que esto acarreará.Duración: 106 min",
                "Geena Davis, Jeff Goldblum, David Cronenberg, Les Carlson, John Getz"
            )
        )
        agregarPelicula(
            Pelicula(
                "Pink Floyd: The wall",
                getGenero("Musical"),
                "En una operación cine-musical nunca antes vista, el disco originó la película, un singular film en el que el guión y los diálogos eran los mismos que contenía el trabajo del mítico grupo de rock sinfónico Pink Floyd. Lo mejor: sus expresivos dibujos de animaciónDuración: 99 min",
                "Bob Geldof, Christina Hargreaves, James Laurenson, Bob Hoskins, Eleanor David, Kevin McKeon, David Bringham"
            )
        )
        agregarPelicula(
            Pelicula(
                "Los Peques: Volumen 3 (Cronicas de por ahí nomas)",
                getGenero("Infantil/Anim"),
                "El invierno es la Ã©poca más difícil para subsistir. Es cuando los Peques deben redoblar sus esfuerzos para ayudar al mundo que los rodea. Sin embargo, una tormenta obliga a Chichi y sus amigos a abandonar su puesto y refugiarse en casa del Nono. Allí comienza una agradable charla que los transportará a un divertidísimo recorrido que abarcará las maravillas de su tierra y los avatares de los antepasados duendes. Aquellos que contribuyeron con el legado de ideales que hará reflexionar a nuestros protagonistas sobre el lugar donde deben estar cuando la tormenta arrecia.Duración: 62 min",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Defending your life",
                getGenero("C.Romantica"),
                "En el día de su cumpleaños, cuando acaba de estrenar un magnífico coche, Daniel muere en el acto al chocar contra otro vehículo. Instantes despuÃ©s se encuentra en La Ciudad del Juicio, rodeado de otros reciÃ©n llegados al Más Allá, que tambiÃ©n están a la espera de la decisión de un tribunal que, si resulta desfavorable, les enviará de nuevo a la Tierra para disfrutar de otra oportunidad en una nueva existencia. El día anterior al proceso, Daniel conoce a una mujer, que se ahogó en una piscina, y se enamora perdidamente de ella.Duración: 100 min",
                "Meryl Streep, Albert Brooks, Rip Torn, Lee Grant, Buck Henry, Shirley MacLaine, Michael Durrell, Gary Beach, James Eckhouse"
            )
        )
        agregarPelicula(
            Pelicula(
                "Psicopata americano",
                getGenero("Suspenso"),
                "En un mundo moralmente plano en el que la ropa tiene más sentido que la piel, Patrick Bateman es un espÃ©cimen soberbiamente elaborado que cumple todos los requisitos de Master del Universo, desde el diseño de su vestuario hasta el de sus productos químicos. Es prácticamente perfecto, como casi todos en su mundo e intenta desesperadamente encajar en Ã©l. Cuando más intenta ser como cualquier otro hombre adinerado de Wall Street, más anónimo se vuelve y menos control tiene sobre sus terribles ideas y su insaciable sed de sangre, adentrándose en una vorágine en la que los objetos valen más que los huesos y el alma humana es algo que debe ser acosada con cuchillos, hachas y taladradorasDuración: 101 min",
                "Christian Bale, Willem Dafoe, Jared Leto, Josh Lucas, Samantha Mathis, Matt Ross, William Sage"
            )
        )
        agregarPelicula(
            Pelicula(
                "Dracula",
                getGenero("Terror"),
                "El amor Nunca se Muere. El vampiro viene a Inglaterra a seducir al novio de un visitante y sembrar el miedo en tierra extranjera. El conde Drácula esperó varios siglos para abandonar su encierro y poder recuperar a su esposa muerta en el siglo XV. En el Londres victoriano, encuentra a Mina Murray, la reencaranción de su amor perdido. Para obtenerla el Conde Drácula deberá enfrentarse con el Profesor Abraham Van Helsing, el experto asesino de vampirosDuración: 130 min",
                "Sadie Frost, Anthony Hopkins, Cary Elwes, Monica Bellucci, Gary Oldman, Daniel Newman, Bill Campbell, Keanu Reeves, Winona Ryder"
            )
        )
        agregarPelicula(
            Pelicula(
                "Inteligencia artificial",
                getGenero("C.Ficcion"),
                "En un mundo futuro donde los avances científicos hacen posible la existencia, los humanos comparten todos los aspectos de sus vidas con sofisticados robots denominados Mecas. La emoción es la última y controvertida frontera en la evolución de las máquinas. Pero cuando un avanzado niño robótico llamado David es programado para amar, los humanos no están preparados para las consecuencias. David se encuentra solo en un extraño y peligroso mundo. Duración: 145 min",
                "Haley Joel Osment, Jude Law, Frances O Connor, Brendan Gleeson, William Hurt, Jake Thomas, Sam Robards"
            )
        )
        agregarPelicula(
            Pelicula(
                "Mannequin",
                getGenero("C.Romantica"),
                "Jonathan Switcher es el dependiente del departamento de unos grandes almacenes que un día descubre que una de sus maniquíes es una mujer del antiguo egipto que ha cobrado vida. Este hecho se convierte para Ã©l en motivo de inspiración para el diseño de los escaparates del negocio. Por otra parte, unos rivales del gremio pretenderán acabar con Ã©l con malas artes.Duración: 86 min",
                "Andrew McCarthy, Kim Cattrall, Estelle Getty, James Spader, Carole Davis, G.W. Bailey, Christopher Maher, Steve Vinovich"
            )
        )
        agregarPelicula(
            Pelicula(
                "Mannequin 2",
                getGenero("C.Romantica"),
                "Un embrujo convierte a una linda campesina en una escultura de madera por haber cometido el error de enamorarse del príncipe heredero de un legendario reino europeo en los remotos tiempos de la Edad Media. El hechizo finalizará cuando encuentre su verdadero alter-ego. Duración: 96 min",
                "Kristy Swanson, William Ragsdale, Terry Kiser, Stuart Pankin, Meshach Taylor, Cynthia Harris, Andrew Hill Newman"
            )
        )
        agregarPelicula(
            Pelicula(
                "Iron-man: La serie animada",
                getGenero("Infantil/Anim"),
                "Pack 3dvds. Iron Man, la serie animada de Marvel en su versión completa. Disfruta cada uno de sus emocionantes momentos, desde el primer episodio hasta el punto culminante de la batalla final, en esta edición de colección de tres discos. SÃ© testigo de las innumerables aventuras que comienzan cuando el multimillonario inventor Tony Stark usa su invencible armadura de hierro para enfrentarse al villano Mandarín y al poder de sus 10 mortales anillos. Con ayuda de los súper hÃ©roes War Machine, Scarlet Witch, Spider-Woman y Hawkeye, Iron Man se enfrenta a sus malvados enemigos y a sus propios demonios. Ve más allá de la armadura y conoce al hombre detrás de este poderoso traje.Duración: 637 min",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Todos contra Juan: Temparada 1",
                getGenero("Series"),
                "Pack 3dvds. Juan Perugia es un tarado entrañable que tuvo la mala suerte de quedar abrochado a los quince segundos de fama que le tocó vivir cuando coprotagonizó, hace más de 15 años, La vida es un juego , un ciclo de temática adolescente (con Cecilia Dopazo, Julián Weich, Julieta Díaz y Esteban Prol) que sucedió a Clave de sol y precedió a Montaña rusa. Y que terminó algo abruptamente por problemas, algunos de ellos con el propio Perugia, nunca aclarados. Fuera de escena y de las luces de la fama por más de una dÃ©cada, Juan Perugia decide regresar para ocupar su lugar. Un lugar que nunca tuvo y que resulta un terreno ubÃ©rrimo para asistir a las desventuras de un ex actor que se cree lo que no es; una distorsión más que frecuente en su gremioDuración: 650 min",
                "Gastón Pauls, Sebastián de Caro, Mercedes Oviedo, Henny Trayles, Oscar Nuñez, Ezequiel Campa, Ricky Alello"
            )
        )
        agregarPelicula(
            Pelicula(
                "Little Einsteins: Fire truck",
                getGenero("Infantil/Anim"),
                "Cuatro niños con su nave especial Rocket van a emprender una misión. Leo, June, Quincy y Annie deben resolver la estrategia a seguir en cada caso, y para eso realizan preguntas para que los pequeños espectadores participen, opinen y ayuden. Con historias ubicadas en países orientales o en sitios con climas fríos muy rigurosos, los protagonistas hacen referencia a pinturas y melodías clásicas muy conocidas, para unir el desarrollo de cada misión con estas vertientes artísticas.Duración: 95 min",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "The big bang theory: Temporada 1",
                getGenero("Series"),
                "Pack 3dvds. La serie que trata sobre dos nerds dos cerebritos que comparten piso: Leonard (Johnny Galecki) y Sheldon (Jim Parsons). Aunque ambos están doctorados en física teórica y son capaces de calcular la probabilidad sobre la existencia de universos paralelos, no tienen ni la menor idea de cómo relacionarse con el resto del mundo, especialmente con las chicas. La serie comienza con la llegada de Penny (Kaley Cuoco), la nueva vecina que se instala en el piso de enfrente. Penny, que habita las antípodas vitales e intelectuales de ambos cerebritos, altera la tranquila vida sentimental de Leonard y resulta un continuo detonador para el desorden obsesivo-compulsivo de Sheldon.Duracion: 335  min",
                "Johnny Galecki, Kunal Nayyar, Kaley Cuoco, Simon Helberg, Jim Parsons"
            )
        )
        agregarPelicula(
            Pelicula(
                "Barney: Por favor y gracias",
                getGenero("Infantil/Peli"),
                "Ãšnete a Barney & Friends. a medida que aprenden la importancia de ser amable y cortÃ©s con los demás. Cuando Baby Bop olvida decir por favor y gracias, Barney se da cuenta de que es hora de una lección sobre cómo las palabras mágicas pueden hacer que otras personas se sienten bien por dentro. Como fantasías Baby Bop en un mundo sin buenos modales, se da cuenta de que ser cortÃ©s no sólo es importante, pero es fácil de hacer despuÃ©s de todo Duración: 65 min",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Baby Einstein: World animals",
                getGenero("Infantil/Peli"),
                "Una nueva aventura animada-educativa de Disney para los mas pequeños de la casa. De la mano de Baby Einstein les enseñan las maravillas del Mundo Animal, acompañados de títeres y dibujos animados. Duración: 71  min",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Tomates verdes fritos",
                getGenero("Drama"),
                "Evelyn, una mujer de mediana edad que vive frustrada por su gordura y la incomprensión de su marido, conoce a Ninny, una anciana que vive en un asilo. Ã‰sta le cuenta una historia ocurrida en su pueblo de Alabama. Es el relato de la gran amistad que unía a dos mujeres y el misterioso asesinato del marido de una de ellas.Duracion: 120 min",
                "Mary-Louise Parker, Kathy Bates, Jessica Tandy, Mary Stuart Masterson, Chris O Donnell, Cicely Tyson, Gailard Sartain"
            )
        )
        agregarPelicula(
            Pelicula(
                "Chaplin: City lights",
                getGenero("Clasicos"),
                "Un pobre vagabundo (Charles Chaplin) pasa mil y un avatares para conseguir dinero y ayudar a una pobre chica ciega (Virginia Cherill) de la que se ha enamorado.Duracion: 81 min",
                "Charles Chaplin, Virginia Cherill, Florence Lee, Harry Myers, Allan Garcia, Hank Mann"
            )
        )
        agregarPelicula(
            Pelicula(
                "X-Men: La serie animada - Volumen 1",
                getGenero("Infantil/Anim"),
                "Pack 2 dvds. X-Men ahora la popular serie de los años 90 llega ahora en DVD, un material de colección que no puede faltar en tu colección. X-Men Hombres X . Primer volumen cuenta con dos DVDs cada uno conteniendo 8 Episodios de esta popular serie de TV basados en el comics del mismo nombre. ",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "X-Men: La serie animada - Volumen 2",
                getGenero("Infantil/Anim"),
                "Pack 2 dvds. X-Men ahora la popular serie de los años 90 llega ahora en DVD, un material de colección que no puede faltar en tu colección. X-Men Hombres X . Segundo volumen cuenta con dos DVDs cada uno conteniendo 8 Episodios de esta popular serie de TV basados en el comics del mismo nombre. ",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Jardines en otoño",
                getGenero("Drama"),
                " El ministro Vincent no era demasiado feo, le gustaba el buen comer y el buen beber y tenía una hermosa mujer. Al perder su trabajo ella decide abandonarlo. En ese momento, Vincent comienza una nueva vida. Poco a poco se va adentrando en caminos que había olvidado o que nunca había conocido, descubre (o redescubre) placeres sencillos y, finalmente, llega a conocer a personas que había descartado al ignorarlas por completo. Este aprendizaje de la vida a una edad inesperada desencadenará situaciones curiosas, divertidas y crueles.Duración: 118 min",
                "SÃ©verin Blanchet, Pascal Vincent, Muriel Motte, Michel Piccoli, Lily Lavina"
            )
        )
        agregarPelicula(
            Pelicula(
                "Catch me if you can",
                getGenero("Suspenso"),
                "Basada en hechos reales, cuenta la historia de Frank W. Abagnale (Leonardo DiCaprio), un joven y escurridizo delincuente que se hace pasar continuamente por diversas identidades -mÃ©dico, abogado o copiloto de líneas aÃ©reas-. Carl Hanratty (Tom Hanks), un agente del FBI, tiene como única misión seguir su pista y capturarlo para llevarlo ante la justicia, pero Frank siempre va un paso por delante de Ã©lDuración: 140 min",
                "Leonardo DiCaprio, Tom Hanks, Christopher Walken, Jennifer Garner, Martin Sheen, Nathalie Baye, Amy Adams"
            )
        )
        agregarPelicula(
            Pelicula(
                "El mariachi",
                getGenero("Accion"),
                "El Mariachi, un misterioso guitarrista, vuelve para vengar la muerte de su amante y la mutilación de su mano. Sin embargo, cometerá un nuevo error: involucrarse con Carolina, la dueña del cafÃ©-librería de un pueblucho tomado por una banda de traficantes de drogas. Ella espera que su vida cambie y al conocer al mariachi, comprende que Ã©ste va a marcar su destino. Juntos tendrán que hacer frente a la banda de Bucho, en un duelo marcado por la pólvora, la sangre y la acción.Duración: 103 min",
                "Antonio Banderas, Joaquim de Almeida, Salma Hayek, Steve Buscemi, Cheech Marin, Quentin Tarantino"
            )
        )
        agregarPelicula(
            Pelicula(
                "The dead girl",
                getGenero("Suspenso"),
                "Un conjunto de cinco historias acerca de personas, algunas sin relación aparente entre ellas, cuyas vidas convergen alrededor del asesinato de una joven (Brittany Murphy).Duración: 85 min",
                "Toni Collette,  Brittany Murphy,  Marcia Gay Harden,  Rose Byrne,  James Franco,  Josh Brolin,  Giovanni Ribisi,  Kerry Washington,"
            )
        )
        agregarPelicula(
            Pelicula(
                "El jardinero",
                getGenero("Comedia"),
                "DespuÃ©s de hacerse famoso en París, un pintor cincuentón regresa a su pueblo natal para vivir en la casa donde pasó su infancia. Un gran jardín rodea la casa, pero el pintor no puede cuidarlo y pone un anuncio en el periódico pidiendo un jardinero. El primer candidato y el definitivo, es un antiguo compañero de escuela. En su contacto diario el pintor descubre en el jardinero a un hombre que le intriga y que luego termina asombrándolo, gracias a la franqueza y a la simplicidad de la mirada con la que ve el mundo.",
                "Daniel Auteuil, Jean Pierre Darroussin, Fanny CottenÃ§on, Alexia Barlier, Hiam Abbass"
            )
        )
        agregarPelicula(
            Pelicula(
                "Willow",
                getGenero("Aventuras"),
                "Cuento medieval con brujas, enanos y poderes mágicos. En las mazmorras del castillo de la malvada reina hechicera Bavmorda, una prisionera da a luz una niña que, de acuerdo a una antigua profecía, pondrá fin al reinado de la hechicera. La comadrona salva a la niña de la ira de Bavmorda, pero se ve obligada a arrojar su cuna al río cuando es alcanzada por sus perros de presa. La corriente le hace alcanzar un pueblo de enanos en donde es adoptada por el valiente Willow.Duración: 130 min",
                "Val Kilmer, Joanne Whalley, Warwick Davis, Jean Marsh, Patricia Hayes, Billy Barty"
            )
        )
        agregarPelicula(
            Pelicula(
                "De mendigo a millonario",
                getGenero("Comedia"),
                "Por una apuesta de los dueños de una casa millonaria de inversiones los protagonistas cambian su vida de un día a otro-de mendigo a millonario y de millonario a mendigo-hasta que los dos descubren lo que pasó y juntos deciden vengarse.Duración:  118 min",
                "Frank Oz, Eddie Murphy, Dan Aykroyd, Ralph Bellamy, Don Ameche, James Belushi, Paul Gleason, Jamie Lee Curtis"
            )
        )
        agregarPelicula(
            Pelicula(
                "El Campeon (1973)",
                getGenero("Drama"),
                "Cuenta la relación entre un boxeador en el peor momento de su carrera y su hijo. La confianza del niño en su progenitor es absoluta, pero el alcohol y las deudas de juego son obstáculos que alejan al protagonista de una vuelta al ring.Duración:  120 min",
                "Jon Voight, Faye Dunaway, Rick Schroder, Jack Warden, Arthur Hill"
            )
        )
        agregarPelicula(
            Pelicula(
                "Pushing Daisies: Temporada 1",
                getGenero("Series"),
                "Pack 3dvds. El pastelero Ned tiene el increíble poder de revivir a las personas muertas con un solo toque mientras que volviÃ©ndolas a tocar las regresa al otro mundo. Pero este don tambiÃ©n es una maldición, ya que si el muerto permanece más de un minuto despierto, otra alma deberá ser tomada. Ned trabaja tranquilo como investigador privado ayudando a la única persona que sabe de su secreto, Emerson Cod, pero toda su existencia cambia cuando su amor de la infancia aparece muerta y decide traerla de vuelta. Esta tragicomedia romántica con tintes sobrenaturales la crea Bryan Fuller, quien nos introduce en un especial universo fantástico donde las tramas sobre la vida y la muerte son el eje principal, como ya hiciera en Tan Muertos Como Yo.Duración:  383 min",
                "Chi McBride, Swoosie Kurtz, Ellen Greene, Anna Friel, Kristen Chenowith, Lee Pace, Jim Dale"
            )
        )
        agregarPelicula(
            Pelicula(
                "Corazon de leon",
                getGenero("Accion"),
                "Van Damme es un soldado que, para pagar la deuda del hospital de su hermano, se mete en el turbio mundo de las luchas de gladiadores modernos.Duración: 108 min",
                "Jean-Claude Van Damme,  Harrison Page,  Deborah Rennard,  Lisa Pelikan,  Ashley Johnson"
            )
        )
        agregarPelicula(
            Pelicula(
                "Dude, where's my car?",
                getGenero("Comedia"),
                "Tras un noche de juerga, dos amigos no recuerdan dónde dejaron aparcado su coche. Su búsqueda significará el comienzo de una serie de sorpresas. Todo empieza cuando los jóvenes Jesse y Chester se despiertan una mañana despuÃ©s de una fiesta muy intensa, pero ninguno de los dos puede recordar quÃ© pasó durante la noche anterior. El coche de Jesse desapareció, y todo parece estar fuera de lugar, así que los dos amigos comienzan la búsqueda del auto y de pistas que les permita reconstruir la noche anterior, aunque a medida que profundizan en los acontecimientos de las últimas veinticuatro horas, la situación se convierte en una salvaje historia que parece extraída de la ciencia-ficción.Duración: 95 min",
                "Ashton Kutcher,  Seann William Scott,  Kristy Swanson,  Jennifer Garner,  Marla Sokoloff,  Hal Sparks"
            )
        )
        agregarPelicula(
            Pelicula(
                "Coldplay Live",
                getGenero("Musical"),
                "Concierto de mas de 1 hora y media grabado en Sydney, Australia en plena gira del 2003",
                "Coldplay"
            )
        )
        agregarPelicula(
            Pelicula(
                "Cocoon",
                getGenero("C.Ficcion"),
                "Un grupo de ancianos de una residencia descubren la fuente de la eterna juventud. El único problema es que el lugar mágico pertenece a un grupo de extraterrestres, y desconocen si sus intenciones son amigables.Duración: 117 min",
                "Steve Guttenberg,  Brian Dennehy,  Don Ameche,  Jessica Tandy,  Tahnee Welch,  Hume Cronyn,  Tyrone Power Jr.,  Gwen Verdon"
            )
        )
        agregarPelicula(
            Pelicula(
                "Cats",
                getGenero("Musical"),
                "Este musical esta basado en el libro de poemas infantiles del poeta inglÃ©s T.S.Eliot titulado Old Possum's book of practical Cats Se estrenó en 1981 en el New London Theatre de Londres y en 1982 en Broadway consiguiendo un Olivier y un Evening Standard en Inglaterra y siete Tonys en Estados Unidos. Fue tal el exito del musical que logró en 1997 el record de permanencia en Broadway, desplazando a A Chorus Line (1975-1990). Pero ya no se presenta en New York, y en Londres cesaron las funciones en mayo de este año.Duración: 125 min",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Batman y Robin: La pelicula (1966)",
                getGenero("Aventuras"),
                "Cuando a Batman y Robin les llega la información de que el Comodoro Schmidlapp se encuentra en peligro, dentro de su lujoso yate, inmediatamente organizan una misión para rescatarlo. Pero la informacion es una trampa de cuatro de los mas malvados villanos, que solo quieren acabar de una vez por todas con el Dúo Dinámico. Armados con una poderosa arma que convierte a los humanos en polvo, los villanos quieren adueñarse del mundo!! Â¿Podrán Batman y Robin con su heroísmo increíble y sus asombrosas armas derrotar a Gatubela, el PingÃ¼ino, el Guasón y al malvado Acertijo?Duración: 115 min",
                "Adam West, Burt Ward, Burgess Meredith, Cesar Romero, Sterling Holloway, Frank Gorshin, Lee Meriwether, Alan Napier"
            )
        )
        agregarPelicula(
            Pelicula(
                "El smoking",
                getGenero("Accion"),
                "Jimmy Tong tuvo que aprender que para trabajar con el millonario Clark Devlin sólo existe una regla: jamás debes tocar el smoking que tanto aprecia. Pero cuando Clark tiene un accidente, Jimmy aprovecha la oportunidad para probarse el smoking, reciÃ©n ahí comprende que ponerse el traje equivale a convertirse en cinturón negro de karate. A partir de ese momento, se sumerge en el peligroso mundo del espionaje junto a una novata compañera e involuntariamente se transforma en un agente secreto muy bien vestido.Duración: 100 min",
                "Jackie Chan, Jennifer Love Hewitt, Jason Isaacs, Debi Mazar, Peter Stormare"
            )
        )
        agregarPelicula(
            Pelicula(
                "Atracción peculiar",
                getGenero("Nacional"),
                "El director de una revista de espectáculos decide hacer una nota sobre la invasión de travestis a Mar del Plata. Así es encomendado un fotógrafo muy mujeriego, Porcel, quien secunda a un periodista, Olmedo, dando pie a divertidos equívocos. Ultima película del gran Olmedo.Duración: 90 min",
                "Alberto Olmedo, Jorge Porcel, Silvia Perez, Pablo Codevila, Judith Gabbani"
            )
        )
        agregarPelicula(
            Pelicula(
                "Cacho Castaña : Yo serÃ© el amor",
                getGenero("Musical"),
                "Yo SerÃ© El Amor, fue grabado en vivo. Contiene nuevas versiones de temas clásicos, preparadas exclusivamente para este show único que fue grabado en un clima intimista lejos del ámbito que en general rodean a los shows en vivo. Un estudio de televisión especialmente preparado para este concierto y la más alta tecnología, son los elementos que distinguen un DVD diferente a todos.Duración: 90 min",
                "Cacho Castaña"
            )
        )
        agregarPelicula(
            Pelicula(
                "Los colimbas al ataque",
                getGenero("Nacional"),
                "Alberto y Jorge están cumpliendo sus obligaciones como soldados conscriptos infractores a la ley del servicio militar obligatorio. Han cometido tantas torpezas que desde el cabo hasta el teniente coronel jefe de los servicios, se confiesan con el capellán y le cuentan que por culpa de ese par de inservibles, toda la compañía ha sido castigada. La superioridad pide al jefe que reúna a los hombres que considere capaces de soportar los altos riesgos de una segunda misión que deberá cumplirse en plena selva. El jefe no duda un instante en incluir en ese grupo de alto riesgo a Jorge y Alberto, sin imaginar que el alto riesgo son ellos mismos.Duración: 90 min",
                "Alberto Olmedo, Jorge Porcel, Mario Sánchez, Adolfo García Grau, Adriana Salgueiro, Cris Morena, Carlos Russo, Javier Portales, Nelly Beltrán"
            )
        )
        agregarPelicula(
            Pelicula(
                "Michael Jackson : 30 Aniversario MSG",
                getGenero("Musical"),
                "El recital exclusivo que muchas celebridades en el 2001 rindieron homenaje a los 30 años de carrera de MJ en el Madison Square Garden.Duración:  min",
                "Michael Jackson"
            )
        )
        agregarPelicula(
            Pelicula(
                "Michael Jackson : Dangerous",
                getGenero("Musical"),
                "Espectacular musical de MJ ! Contiene: Black Or White Superbowl Heal The World Remember The Time Will You Be There In The Closet Gone Too Soon Jam Heal The World Give In To Me Who Is It DangerousDuración: 111 min",
                "Michael Jackson"
            )
        )
        agregarPelicula(
            Pelicula(
                "Way of the dragon",
                getGenero("Accion"),
                "Tang Lung (Bruce Lee) llega a Roma para ayudar a una amiga de la familia, Chen Ching Hua (Nora Miao), amenazada por unos gangsters locales que quieren apropiarse de su restaurante. Poco despuÃ©s de la llegada de Tang, los criminales irrumpen en el restaurante con la intención de obligar a Chen a firmar la venta de su propiedad. DespuÃ©s de que se burlen del estilo de combate de su país natal, un furioso Tang reta a los matones y les derrota en una excelente exhibición de artes marciales. Tras fracasar en su afán de asesinar a Tang, el cerebro de la banda contrata a Colt (Chuck Norris), un campeón mundial de karate de Estados Unidos, en un último intento de acabar con Ã©l. Tang se verá las caras con el americano en el mítico Coliseo Romano en el que lucharán como gladiadores en un legendario combate a muerte.Duración: 100 min Aclaración: Los subss se eligen dentro de la pelicula, no desde el menu. Las partes de americanos (toda es hablada en chino) no tiene traduccion, pero se entiende.",
                "Bruce Lee,  Chuck Norris,  Chung-Hsin Huang,  Ing-Sik Whang,  Nora Miao,  Ping-Ao Wei,  Robert Wall"
            )
        )
        agregarPelicula(
            Pelicula(
                "Moonwalker",
                getGenero("Musical"),
                "Musical-Documental sobre Michael Jackson. La película va mostrando los cambios de Michael Jackson como persona y como intÃ©rprete, y lo que ha logrado en los 30 años que tenía cuando hizo el filme. El niño que cantó de manera conmovedora a la amistad en Ben, el joven que convirtió la vida en un Thriller, el apasionado intÃ©rprete que llegó hasta el corazón con We Are The World, todos están aquí. Y luego vino Bad. Todo vuelve a ocurrir. La música toca, el ritmo se mueve. Michael está aquí, y los demás tambiÃ©n. Adentrándonos en la aventura encontramos tambiÃ©n un viaje al sentido del humor de Michael Jackson, mediante los titulares de un periódico que ponen Leave Me Alone. Es una gira por la vida de Michael, sobre cómo ha cambiado y cómo piensa que los demás le ven. Para esto, Michael se desliza hacia Smooth Criminal, la escala más importante del viaje de Jackson. Michael, el hÃ©roe definitivo, se enfrenta con todas sus fuerzas al villano Mr. Big, cuya suprema intención es apoderarse del mundo, logrando que los escolares consuman drogas. En el curso de esta terrible lucha, y mientras los seguidores de Mr. Big intentan eliminar a Michael, tres niños, Sean, Katie y Zake, son testigos de sus milagrosas transformaciones y su victoria final. Un cómic con estÃ©tica videoclipera realizado a mayor gloria de la estrella del pop Michael Jackson, que se interpreta a sí mismo y que muestra aspectos de su vida, sus sueños y su música. Articulado a modo de Sketches, destaca el último de ellos que muestra a Jackson erigido en una especie de superhÃ©roe galáctico que combate a las Fuerzas del Mal, episodio en el que aparece Joe Pesci y varios niños.Duración: 84 min",
                "Michael Jackson"
            )
        )
        agregarPelicula(
            Pelicula(
                "Rambito y Rambon: Primera misión",
                getGenero("Nacional"),
                "Alberto Colifo (Alberto Olmedo) y Jorge Pumba (Jorge Porcel), son dos infractores a la ley del servicio militar que apenas llegados de Europa fueron incorporados como conscriptos. Las primeras vicisitudes por las que pasan se conocieron en la primera película de esta serie LOS COLIMBAS SE DIVIERTEN, que terminó cuando nuestros dos simpáticos colimbas causan diversos estragos con un tanque en un desfile y a consecuencia de ello, son castigados por indisciplina. Convencidos que deben tratar de mejorar su situación, para lograr un franco que les permita ver a las hijas de su jefe, con las cuales simpatizan, intentarán una y otra vez sin Ã©xito predisponer a sus superiores a su favor, pero se ven siempre envueltos en divertidas situaciones con las que sólo consiguen aumentar la rigidez en el trato y ser propuestos para integrar un grupo que operará en una región selvática donde deberán prepararse para duras misiones de supervivencia.Duración: 90 min",
                "Alberto Olmedo, Jorge Porcel, Mario Sánchez, Adolfo García Grau, Adriana Salgueiro, Cris Morena, Carlos Russo, Javier Portales, Nelly Beltrán"
            )
        )
        agregarPelicula(
            Pelicula(
                "Los colimbas se divierten",
                getGenero("Nacional"),
                "Jorge (Jorge Porcel) y Alberto (Alberto Olmedo) forman un excÃ©ntrico dúo musical, con el cual deciden probar fortuna en Europa. Una noche generan un incidente en un cabaret donde trabajan, del que tendrán que huir, pues los destrozos que por su culpa se producen enfurecen al dueño. Quiere la casualidad que lleguen a la Embajada Argentina, justo en el momento que están entrando los músicos de la Camerata para actuar en una recepción de gala, circunstancia que aprovecharán para introducirse como si formaran parte del famoso conjunto. Allí, conocerán a dos lindas compatriotas, Mónica (Cris Morena) y Susana (Adriana Salgueiro), que se sentirán rápidamente atrapadas por sus nuevos amigos, que fingiendo una relevante posición en el mundo social y de la música ganarán su confianza. Cuando llegan al aeropuerto Jorge y Alberto se encuentran con una sorprendente noticia, son infractores a la ley del servicio militar obligatorio y se ven obligados a hacer juntos la conscripción, incorporándose en la misma dependencia donde el padre de sus nuevas amigas es el director de la banda.Duración: 90 min",
                "Alberto Olmedo, Jorge Porcel, Mario Sánchez, Adriana Salgueiro, Cris Morena, Carlos Russo, Javier Portales, Nelly Beltrán"
            )
        )
        agregarPelicula(
            Pelicula(
                "Charly Garcia: Say No More",
                getGenero("Musical"),
                " Documental sobre las actuaciones de Charly en torno a la gira Say No More. Las tomas en vivo no son de una sola presentación, pero el conjunto da una buena idea de la demencia escÃ©nica de García y la forma en que la banda y los invitados se suman a la fiesta. Para devotos. Las canciones contenidas son: IÂ´m not in love, Influenza, Dos edificios dorados, Aguante la amistad, Demasiado ego, Rezo por vos, Vía muerta, El día que apagaron la luz, Los salieris de Charly, No soy un extraño, Funky, Necesito tu amor, Símbolo de paz, El karma de vivir al sur, Me siento mucho mejor, Aquí sin tu amor, Telepáticamente, IÂ´m not in love, Vicio, Nos siguen pegando abajo, No toquen, Pasajera en trance, Cerca de la revolución, Poseidón, El chico y yo.Duración: 90 min",
                "Charly Garcia"
            )
        )
        agregarPelicula(
            Pelicula(
                "Al filo de la muerte",
                getGenero("Suspenso"),
                "Nicholas Van Orton es un perspicaz e influyente hombre de negocios acostumbrado a controlar absolutamente todas las facetas de su existencia. Sin embargo, su perfecta vida sufre un dramático cambio cuando su hermano Conrad le hace un original regalo de cumpleaños que pronto tendrá consecuencias devastadoras. Se trata del acceso a una misteriosa y nueva forma de entretenimiento en la que todo puede perderse, y con una sola regla: no hay reglas. Cuando Van Orton se da cuenta de en quÃ© consiste el juego, ha llegado demasiado lejos y ya es muy tarde para retroceder. Preparado a medida para cada participante. Piensa en ello como en unas fantásticas vacaciones, excepto que tú no vas a buscarlo, Ã©l viene a tí... No hay reglas en The Game. Duración: 130 min",
                "Michael Douglas, Sean Penn, Deborah Unger, James Rebhorn"
            )
        )
        agregarPelicula(
            Pelicula(
                "Taxi driver",
                getGenero("Clasicos"),
                "El clásico film de Scorcese. El esforzado argumentista Paul Schrader retrata la siempre profunda enajenación del veterano de Vietnam Travis Brickle. Un taxista psicótico que cruza obsesivamente las calles bajas de Manhattan. Taxi Driver es un portarretrato de la unida y provocativa pesadilla provocada por la desintegración de la herida mente americana.Duración: 113 min",
                "Peter Boyle, Albert Brooks, Robert DeNiro, Jodie Foster, Leonard Harris"
            )
        )
        agregarPelicula(
            Pelicula(
                "Leon Gieco : 15 años de mi",
                getGenero("Musical"),
                "Los clasicos que relatan 15 años de historia de Leon GiecoDuración: 85 min",
                "Leon Gieco"
            )
        )
        agregarPelicula(
            Pelicula(
                "Bones: Temporada 1",
                getGenero("Series"),
                "Pack 8 dvds. David Boreanaz (Ãngel, Buffy La Caza Vampiros) interpreta al agente del FBI Seeley Booth que, al lado de la doctora antropóloga forense Temperance Bones (Emily Deschanel) y su singular equipo de científicos, busca resolver los más desconcertantes crímenes a travÃ©s de un agudo instinto combinado con la más sofisticada tecnología. Inspirada en las aventuras de la vida real de la antropóloga forense y autora Kathy Reichs, Bones presenta un interesante drama de investigación, cargado de romántica tensión y humor negro.Duración: 956 min",
                "David Boreanaz, Emily Deschanel"
            )
        )
        agregarPelicula(
            Pelicula(
                "Bones: Temporada 2",
                getGenero("Series"),
                "Pack 6 dvds. Segunda temporada de esta serie que encierra un drama policial y conjuga humor y emociones. Una apasionante serie que resalta la humanidad en contraposición al mundo de los científicos, quienes resaltan la inhumanidad que subyace debajo de los más escalofriantes crímenes.Duración: 947 min",
                "David Boreanaz, Emily Deschanel"
            )
        )
        agregarPelicula(
            Pelicula(
                "Bones: Temporada 3",
                getGenero("Series"),
                "Pack 5 dvds. Tercera temporada de esta serie que encierra un drama policial y conjuga humor y emociones. Cuando una investigación de rutina de un homicidio desentierra el espantoso rastro de un asesino serial caníbal, la antropóloga forense Dr. Temperance Bones Brennan (Emily Deschanel) y el agente especial del FBI Seeley Booth (David Boreanaz) tendrán que enfrentar el más desconcertante y truculento descubrimiento de sus carreras. Para descifrar aquellos casos con las más extremas manifestaciones, como fetichismo sexual y cadáveres carbonizados, el dúo contará con la singular habilidad científica de Brennan, el infalible instinto de Booth y un brillante equipo de investigación. Ahora, en esta emocionante tercera temporada de Bones, Brennan y Booth deberán develar la escandalosa verdad detrás de una sociedad centenaria y detener al Gormogon....antes que sea demasiado tarde!Duración: 655 min",
                "Emily Deschanel, David Boreanaz, Michaela Conlin, Eric Millegan"
            )
        )
        agregarPelicula(
            Pelicula(
                "Los Piojos : Fantasmas peleándole al viento",
                getGenero("Musical"),
                "Los Piojos en el primer DVD en su carrera, titulado Fantasmas peleándole al viento (un guiño a sus fans, ya que la frase forma parte de su canción Cruel). Este material muestra sus actuaciones en Boca (diciembre de 2005), Estadio Unico de La Plata (noviembre de 2004) y Obras (octubre de 2005). El DVD tiene varias perlas, de esas que les gustan a los seguidores. Uno de los momentos culminantes es Maradó, con el 10 como invitado, jugando un cabeza con Ciro. Hay más. El film, de gran definición de audio e imagen, fue dirigido por Alberto Carpo CortÃ©s.Duración: 90 min",
                "Los Piojos"
            )
        )
        agregarPelicula(
            Pelicula(
                "Todo sobre mi madre",
                getGenero("Drama"),
                "El jóven Esteban quiere convertirse en un gran escritor y tambiÃ©n quiere descubrir la identidad de su padre, cuidadosamente ocultado por su madre Manuela..Duración: 120 min",
                "Cecilia Roth, Marisa Paredes, Penelope Cruz, Candela Cruz"
            )
        )
        agregarPelicula(
            Pelicula(
                "Psicosis",
                getGenero("Clasicos"),
                "La macabra obra maestra de Alfred Hitchcock está interpretada por Anthony Perkins como el perturbado Norman Bates, cuya vieja y oscura casa y hotel aledaño, no son el lugar ideal para pasar una tranquila velada. Nadie lo sabe mejor que Marion Crane (Janet Leigh) la infortunada huÃ©sped que termina su viaje en la famosa escena de la ducha. Primero un detective privado, luego la hermana de Marion (Vera Miles) buscan saber algo de ella, mientras el horror y el suspenso llegan a un aterrador clímax, donde el misterioso asesino es finalmente revelado.Duración: 111 min",
                "Anthony Perkins, Vera Miles, John Gavin, Martin Balsam, John McIntre, Janet Leigh"
            )
        )
        agregarPelicula(
            Pelicula(
                "Cantando bajo la lluvia",
                getGenero("Clasicos"),
                "Una compañía de producción de película hará cautivarlo con el sonido y la imagen de un clásico recuperado. Â¡Que Sentimiento Glorioso!!! . Es el año 1927, Don Lockwood y Lina Lamont están en la cima de sus carreras actorales en Hollywood. Componen a la pareja perfecta frente a las cámaras del cine mudo y, aunque Lina piensa que el amor de los guiones se refleja en su vida real, Don no tiene ningún interÃ©s en ella. Con la llegada del sonido a la industria del celuloide, su siguiente producción se transformará en un film hablado y musical. El único problema es que la carismática Lina tiene una voz espantosamente irreproducible, capaz de hacer fracasar el esfuerzo de todos. Ahora Don y su amigo Cosmo tendrán que pensar en algún plan para que la película sea un Ã©xito. El gran clásico entre los músicales de la Ã©poca clásica de Hollywood.Duración: 103 min",
                "Gene Kelly, Donald OÂ´Connor, Debbie Reynolds"
            )
        )
        agregarPelicula(
            Pelicula(
                "Volver al futuro 1",
                getGenero("C.Ficcion"),
                "",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Volver al futuro 2",
                getGenero("C.Ficcion"),
                "",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Volver al futuro 3",
                getGenero("C.Ficcion"),
                "",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "How I met your mother : Temporada 1",
                getGenero("Series"),
                "Pack 3 dvds. Primer temporada de la exitosa serie de la CBS que cuenta la vida de Ted (Josh Radnor), un arquitecto de 27 años que decide lanzarse a la búsqueda del amor verdadero, algo que no le resultará tan sencillo. Cómo conocí a tu madre comienza con la voz en off de Ted en el año 2030, cuando relata a sus dos hijos adolescentes cómo conoció a su madre y cuáles fueron sus vivencias hasta que, por fin, encontró el amor verdadero. Todo tuvo lugar cuando Marshall (Jason Segel), el mejor amigo del protagonista, decide pedirle a Lily (Alyson Hannigan, American Pie), su novia de toda la vida, que se case con Ã©l. Ted se da cuenta de que Ã©l tambiÃ©n debe apresurarse si quiere encontrar el amor verdadero y formar una familia, para lo que cuenta con Barney (Neil Patrick Harris), el compañero perfecto para dicha búsqueda.  Cuando por fin Ted conoce a Robin (Cobie Smulders), una impresionante joven canadiense que se acaba de mudar a Nueva York, está completamente seguro de que es amor a primera vista, pero el destino podría depararle muchas sorpresas...Duración: 500 min",
                "Neil Patrick Harris, Cobie Smulders, Alyson Hannigan, Jason Segel, Josh Radnor"
            )
        )
        agregarPelicula(
            Pelicula(
                "How I met your mother : Temporada 2",
                getGenero("Series"),
                "Pack 3 dvds. Segunda temporada de la exitosa serie de la CBS que cuenta la vida de Ted (Josh Radnor), un arquitecto de 27 años que decide lanzarse a la búsqueda del amor verdadero, algo que no le resultará tan sencillo. Cómo conocí a tu madre comienza con la voz en off de Ted en el año 2030, cuando relata a sus dos hijos adolescentes cómo conoció a su madre y cuáles fueron sus vivencias hasta que, por fin, encontró el amor verdadero. Todo tuvo lugar cuando Marshall (Jason Segel), el mejor amigo del protagonista, decide pedirle a Lily (Alyson Hannigan, American Pie), su novia de toda la vida, que se case con Ã©l. Ted se da cuenta de que Ã©l tambiÃ©n debe apresurarse si quiere encontrar el amor verdadero y formar una familia, para lo que cuenta con Barney (Neil Patrick Harris), el compañero perfecto para dicha búsqueda.  Cuando por fin Ted conoce a Robin (Cobie Smulders), una impresionante joven canadiense que se acaba de mudar a Nueva York, está completamente seguro de que es amor a primera vista, pero el destino podría depararle muchas sorpresas...Duración: 500 min",
                "Neil Patrick Harris, Cobie Smulders, Alyson Hannigan, Jason Segel, Josh Radnor"
            )
        )
        agregarPelicula(
            Pelicula(
                "How I met your mother : Temporada 3",
                getGenero("Series"),
                "Pack 3 dvds. Tercer temporada de la exitosa serie de la CBS que cuenta la vida de Ted (Josh Radnor), un arquitecto de 27 años que decide lanzarse a la búsqueda del amor verdadero, algo que no le resultará tan sencillo. Cómo conocí a tu madre comienza con la voz en off de Ted en el año 2030, cuando relata a sus dos hijos adolescentes cómo conoció a su madre y cuáles fueron sus vivencias hasta que, por fin, encontró el amor verdadero. Todo tuvo lugar cuando Marshall (Jason Segel), el mejor amigo del protagonista, decide pedirle a Lily (Alyson Hannigan, American Pie), su novia de toda la vida, que se case con Ã©l. Ted se da cuenta de que Ã©l tambiÃ©n debe apresurarse si quiere encontrar el amor verdadero y formar una familia, para lo que cuenta con Barney (Neil Patrick Harris), el compañero perfecto para dicha búsqueda.  Cuando por fin Ted conoce a Robin (Cobie Smulders), una impresionante joven canadiense que se acaba de mudar a Nueva York, está completamente seguro de que es amor a primera vista, pero el destino podría depararle muchas sorpresas...Duración: 500 min",
                "Neil Patrick Harris, Cobie Smulders, Alyson Hannigan, Jason Segel, Josh Radnor"
            )
        )
        agregarPelicula(
            Pelicula(
                "Atame !",
                getGenero("Comedia"),
                "La historia de un muchacho (Antonio Banderas), reciÃ©n salido de un instituto mental, cuyo anhelo es contruir una familia y tener hijos. Para eso no tiene mejor idea que secuestrar a la reina de la películas clase B, Victoria Abril. Duración: 101 min",
                "Victoria Abril, Antonio Banderas, Loles Leon, Maria Barranco, Rossy de Palma, Francisco Rabal"
            )
        )
        agregarPelicula(
            Pelicula(
                "Cementerio de animales",
                getGenero("Terror"),
                "La feliz familia Creed se muda a una casa en Maine, a un sitio lejos del pueblo y muy cercano al cementerio de animales. Un lugar muy tranquilo pero que cuenta con varias extrañas historias y terroríficas leyendas. Louis es el esposo de Rachel y padre del pequeño Gage y la simpática Ellie, es el responsable de la mudanza, ya que consiguió un nuevo trabajo como medico en el colegio del pueblo. Y tambiÃ©n será quien cometa el error de jugar con las peligrosas fuerzas sobrenaturales, capaces de revivir a los muertos. Un best seller de Stephen King, adaptado por Ã©l mismo para la pantalla. Duración: 105 min",
                "Fred Gwynne, Dale Midkiff, Brad Greenquist, Miko Hughes, Denise Crosby"
            )
        )
        agregarPelicula(
            Pelicula(
                "La Nona",
                getGenero("Nacional"),
                "Un matrimonio de clase media da albergue a La Nona, con un apetito fenomenal que devora todo lo que encuentra. Ante la imposibilidad de mantenerla, apelan a las más variadas situaciones para eliminar a la vieja. Pero ella es indestructible y cada intento terminará trágicamente, mientras La Nona sigue comiendo. Duración: 89 min",
                "Pepe Soriano, Juan Carlos Altavista, Osvaldo Terranova, Carmelo Spadone, Guillermo Battaglia, Graciela Alfano, Vicente La Russa"
            )
        )
        agregarPelicula(
            Pelicula(
                "Supernatural : Temporada 1",
                getGenero("Series"),
                "Pack 6 dvds. Supernatural narra la vida de Sam y Dean Winchester, unos hermanos marcados por la muerte de su madre en extrañas circunstancias y por la obsesión de su padre por encontrar a la supuesta fuerza diabólica que acabó con la vida de su esposa. Duración : 1380 min",
                "Jared Padalecki,  Jensen Ackles"
            )
        )
        agregarPelicula(
            Pelicula(
                "Supernatural : Temporada 2",
                getGenero("Series"),
                "Pack 6 dvds. Lo espeluznate, lo demente, lo inexplicale, lo sobrenatural. San Winchester (Jared Padalecki) creció cazando cosas realmente aterradoras. Pero eso quedó en el pasado. La facultad de derecho lo llama. Así como tambiÃ©n llevar una vida segura y normal. Eso es hasta que Dean (Jensen Ackles), su hermano al que no ve hace años, aparece con malas noticias: su padre, un hombre que ha perseguido a las fuerzas del mal durante 22 años, desaparecio. Ahora, para encontralo, los hermanos tendrán que ir detrás de aquello que seguía su padre y Sam deberá regresar a la vida que penso había dejado atrás.Duración : 968 min",
                "Jared Padalecki,  Jensen Ackles"
            )
        )
        agregarPelicula(
            Pelicula(
                "Supernatural : Temporada 3",
                getGenero("Series"),
                "Pack 5 dvds. Ya se estableció una tercera temporada con los hermanos Sam y Dean Winchester (Jared Padalecki y Jesen Ackles) donde tratarán de rastrear algunos demonios que han salido despuÃ©s de que las puertas del infierno se abrieran, enfocándose en la guerra entre el bien y el mal. Duración: 655 min",
                "Jared Padalecki,  Jensen Ackles"
            )
        )
        agregarPelicula(
            Pelicula(
                "V : La serie completa",
                getGenero("Series"),
                "Pack 6 dvds. En V y su continuación V: The Final Battle, unos extraterrestres llegan a la tierra, aparentemente buscando agua con fines pacíficos y esperando realizar una alianza, sin embargo en realidad buscan alimento humanos. La humanidad deberá luchar para sobrevivir en una emocionante serie de 19 episodios. Duración: 900 min",
                "Marc Singer, Lane Smith, Faye Grant, Jennifer Cooke, Michael Wright, Robert Englund, Jane Badler, Blair Tefkin, Michael Fireside"
            )
        )
        agregarPelicula(
            Pelicula(
                "Torrente : El brazo tonto de la ley",
                getGenero("Comedia"),
                "Torrente es un policía español, fascista, machista, racista, alcohólico y del Atleti. En su mismo inmueble vive un chaval, Rafi, al que le gustan las peliculas de acción y las pistolas, y que vive con su madre y su prima Amparito, una ninfómana. Juntos, Torrente y Rafi, patrullarán por la noche las calles de la ciudad. Duración: 97 min",
                "Santiago Segura,  Javier Cámara,  Neus Asensi,  Chus Lampreave,  Tony Leblanc,  Nuria Carbonell,  Daniel Monzón,  Carlos Lucas"
            )
        )
        agregarPelicula(
            Pelicula(
                "Torrente 2 : Misión en Marbella",
                getGenero("Comedia"),
                "Torrente decide instalarse en plena Costa del sol, en el ayuntamiento de Marbella, y trabajar como investigador privado. Allí, con la ayuda de su ayudante a tiempo parcial, Cuco, se dedicará a desenmascarar a un peligroso traficante de armas, Spinelli, que amenaza con destruir la ciudad si no le pagan 2000 millones de pesetas. Duración: 97 min",
                "Santiago Segura,  Gabino Diego,  Tony Leblanc,  JosÃ© Luis Moreno,  InÃ©s Sastre,  Arturo Valls,  Juanito Navarro"
            )
        )
        agregarPelicula(
            Pelicula(
                "Torrente 3 : El protector",
                getGenero("Comedia"),
                "Una popular eurodiputada, Giannina Ricci, llega de visita a España. Su objetivo, a travÃ©s de un detallado informe que desvela las ilegalidades e irregularidades de la multinacional Petronosa, es cerrar las factorías de dicha empresa, que atentan contra el medio ambiente. El más alto directivo de la compañía soborna a dos mandos de los cuerpos de seguridad encargados de proteger a la señorita Ricci, para que faciliten el atentado que planea contra la eurodiputada. No se les ocurre nada mejor que encargar la protección durante la estancia en España de la diputada a Jose Luis Torrente, con probabilidad, uno de los individuos más anormales de la tierra. Por si fuera poco, le dejan elegir y entrenar los que conformarán el cuerpo de Ã©lite que le ayude con la protección. Una panda de seres más aberrantes que el propio Torrente si cabe. Se masca la tragedia. Duración: 97 min",
                "Santiago Segura,  Tony Leblanc,  JosÃ© Mota,  Javier GutiÃ©rrez,  Carlos Latre,  Yvonne Sció,  Enrique Villen,  Luis Roderas,  Silvia Gambino"
            )
        )
        agregarPelicula(
            Pelicula(
                "CSI : Las Vegas : Temporada 5",
                getGenero("Series"),
                "Pack 6 dvds. Esta multipremiada serie, cuenta la historia de los poco conocidos y menos comprendidos hÃ©roes que trabajan en las escenas de crimen: los investigadores forenses. Estos son hombres y mujeres que trabajan tiempo completo para desentrañar la evidencia detrás de las líneas amarillas policiales, reconstruyendo las piezas de un rompecabezas aparentemente insignificante, poniendo al descubierto a los delincuentes y sirviendo a aquellos que más los necesitan, las víctimas. Atencion : Backup importado. No tiene subitutlos en español. Solo audio en español",
                "Paul Guilfoyle, Marg Helgenberger, Jorja Fox, William Petersen, Gary Dourdan"
            )
        )
        agregarPelicula(
            Pelicula(
                "CSI : Las Vegas : Temporada 6",
                getGenero("Series"),
                "Pack 6 dvds. Esta multipremiada serie, cuenta la historia de los poco conocidos y menos comprendidos hÃ©roes que trabajan en las escenas de crimen: los investigadores forenses. Estos son hombres y mujeres que trabajan tiempo completo para desentrañar la evidencia detrás de las líneas amarillas policiales, reconstruyendo las piezas de un rompecabezas aparentemente insignificante, poniendo al descubierto a los delincuentes y sirviendo a aquellos que más los necesitan, las víctimas. Atencion : Backup importado. No tiene subitutlos en español. Solo audio en español",
                "Paul Guilfoyle, Marg Helgenberger, Jorja Fox, William Petersen, Gary Dourdan"
            )
        )
        agregarPelicula(
            Pelicula(
                "CSI : Las Vegas : Temporada 7",
                getGenero("Series"),
                "Pack 7 dvds. Esta multipremiada serie, cuenta la historia de los poco conocidos y menos comprendidos hÃ©roes que trabajan en las escenas de crimen: los investigadores forenses. Estos son hombres y mujeres que trabajan tiempo completo para desentrañar la evidencia detrás de las líneas amarillas policiales, reconstruyendo las piezas de un rompecabezas aparentemente insignificante, poniendo al descubierto a los delincuentes y sirviendo a aquellos que más los necesitan, las víctimas. Atencion : Backup importado. No tiene subitutlos en español. Solo audio en español",
                "Paul Guilfoyle, Marg Helgenberger, Jorja Fox, William Petersen, Gary Dourdan"
            )
        )
        agregarPelicula(
            Pelicula(
                "He-man : Temporada 2 - Volumen 1",
                getGenero("Series"),
                "Pack 6 DVD's con 33 episodios de la famosa serie. Primer volumen parte de la primer temporada con mas de 11 Horas de Duracion.",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "He-man : Temporada 2 - Volumen 2",
                getGenero("Series"),
                "Pack 6 DVD's con 33 episodios de la famosa serie. Primer volumen parte de la primer temporada con mas de 11 Horas de Duracion.",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "El increible Hulk : The television series",
                getGenero("Series"),
                "Pack 6 DVD's con 17 episodios de una recopilación de los mejores capitulos de la serie del doctor David Banner, que cuando se enoja es El hombre increible. Duración :  casi 15 hs",
                "Bill Bixbi, Lou Ferrigno, Jack Colvin"
            )
        )
        agregarPelicula(
            Pelicula(
                "El increible Hulk : Temporada 2",
                getGenero("Series"),
                "Pack 6 DVD's con 22 episodios de la famosa serie del científico David Banner, que cuando se enoja es : El hombre increible. Duración :  casi 15 hs",
                "Bill Bixbi, Lou Ferrigno, Jack Colvin"
            )
        )
        agregarPelicula(
            Pelicula(
                "Dogma",
                getGenero("Comedia"),
                "La batalla final en la eterna guerra entre el Bien y el Mal tendrá lugar en Nueva Jersey, cuando dos ángeles caídos intenten destruir el universo entero, a menos que alguien logre detenerlos. Estos dos ángeles, Loki y Bartleby, tratan de hallar un modo de acabar con su exilio perpetuo en Wisconsin, cuando se topan con el plan perfecto para regresar al paraíso: la posibilidad de eludir el dogma que les permitirá retornar al Cielo, si pasan bajo el bendito arco de la catedral de Nueva Jersey, eliminando a la vez toda existencia humana. Pero tendrán que enfrentarse a un problema; una persona ha sido elegida para impedírselo... y no tendrá que hacerlo sola. Duración: 135 min",
                "Ben Affleck,  Matt Damon,  Linda Fiorentino,  Salma Hayek,  Jason Lee,  Jason Mewes, Chris Rock, Kevin Smith,  Alanis Morissette"
            )
        )
        agregarPelicula(
            Pelicula(
                "Super bebÃ© : Colores",
                getGenero("Infantil/Anim"),
                "En este video, el juego, el humor y la belleza son instrumentos para el desarrollo: al mismo tiempo que enseña los colores, Super BebÃ© Cores estimula la lógica, la creatividad y la observación. Por medio de imágenes divertidas de juguetes, objetos cotidianos y obras de Van Gogh, Monet y CÃ©zanne, entre otros artistas, el video presenta a los niños el mundo de los colores. Trabaja la lógica, la creatividad, la concentración y la observación a partir de escenas visualmente estimulantes con cuadros de los grandes genios de la pintura.",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Harry El sucio",
                getGenero("Accion"),
                "Harry Callahan es un duro policía, que se ha criado en la calle, al que sus compañeros llaman Harry el sucio por sus particulares mÃ©todos de luchar contra el crimen, y porque siempre se encarga de los trabajos menos deseados. En San Francisco, un tirador ha matado ya a dos personas. Harry será asignado al caso.Duración:  102 min",
                "Clint Eastwood,  Harry Guardino,  Reni Santoni,  John Vernon,  Andy Robinson,  John Larch"
            )
        )
        agregarPelicula(
            Pelicula(
                "Los bicivoladores",
                getGenero("Infantil/Peli"),
                "Tres jóvenes tienen el bicicross como afición. Un día, tras un accidente sus bicicletas quedan destrozadas. Para sacar dinero se les ocurre pescar almejas y encuentran un paquete que les traerá problemas.Duración:  99 min",
                "David Argue,  John Ley,  Nicole Kidman,  Angelo Dâ€™Angelo,  James Lugton,  Bryan Marshall,  Brian Sloman"
            )
        )
        agregarPelicula(
            Pelicula(
                "Barney : The land of make believe",
                getGenero("Infantil/Peli"),
                "Divertite con Barney y sus amigos en la tierra de los espejismosDuraci{on: ",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Elmo : El pais de los gruñones",
                getGenero("Infantil/Peli"),
                "Elmo ama a su peluda manta más que a nadie en el mundo. Ambos forman un equipo inseparable desde la mañana a la noche, pero el gamberro Telly, por hacer una gracia, le quita la manta a Elmo y se la tira a la basura. Sin pensarselo dos veces, Elmo se mete en el fondo del cubo, desde donde se verá arrastrado sorprendentemente al país de los gruñones, un mundo de criaturas malolientes. En ese inhóspito lugar, Elmo se verá inmerso en mil aventuras para recuperar finalmente su manta, aunque en el camino aprenderá unas cuantas lecciones, entre ellas no ser tan egoista y compartir sus cosas con los demás.",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Hi-5 Mueve tu cuerpo",
                getGenero("Infantil/Peli"),
                "Hi-5 es un simpático y divertidísimo grupo musical que anima a los más pequeños cantando y bailando al ritmo de sus canciones. Cada miembro conduce un segmento diferente del show enfocando en la importancia de desarrollo temprano del niño en cada una de las categorías educacionales desde el pensamiento lógico y matemático hasta la música y lingÃ¼ística. Mientras Kimee ayuda a los chicos a resolver enigmas y juegos de razonamiento (pensamiento lógico), Karla se especializa en cuerpo y movimiento (coordinación y habilidades motoras). Mientras Curtis hace algo de ruido en haciendo música (musicalidad), Jenn se divierte con juego de palabras (habilidades lingÃ¼ísticas y auditivas), y Shaun juega con cuerpos en el espacio (conciencia visual y espacial). Chatterbox y Jup Jup, dos marionetas para hacer aún más divertido el mundo de Hi-5.Duración: 47 min",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Hi-5 Deseos maravillosos",
                getGenero("Infantil/Peli"),
                "Hi-5 es un simpático y divertidísimo grupo musical que anima a los más pequeños cantando y bailando al ritmo de sus canciones. Cada miembro conduce un segmento diferente del show enfocando en la importancia de desarrollo temprano del niño en cada una de las categorías educacionales desde el pensamiento lógico y matemático hasta la música y lingÃ¼ística. Mientras Kimee ayuda a los chicos a resolver enigmas y juegos de razonamiento (pensamiento lógico), Karla se especializa en cuerpo y movimiento (coordinación y habilidades motoras). Mientras Curtis hace algo de ruido en haciendo música (musicalidad), Jenn se divierte con juego de palabras (habilidades lingÃ¼ísticas y auditivas), y Shaun juega con cuerpos en el espacio (conciencia visual y espacial). Chatterbox y Jup Jup, dos marionetas para hacer aún más divertido el mundo de Hi-5.Duración: 47 min",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Caillou : Siempre aprendiendo",
                getGenero("Infantil/Anim"),
                "Caillou es un pequeño niño de 4 años con una gran imaginación. Dulce e inocente, juguetón y curioso, Caillou se queda sorprendido por todas las cosas nuevas. Las aventuras de Caillou retratan la relación entre los niños pequeños y el mundo que les rodea, ayudándoles a crecer con confianza e independencia. Los niños se reflejan en Caillou, lo que refuerza la unión que tiene con su pequeño hÃ©roe.",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Caillou : Feliz cumpleaños !",
                getGenero("Infantil/Anim"),
                "Caillou es un pequeño niño de 4 años con una gran imaginación. Dulce e inocente, juguetón y curioso, Caillou se queda sorprendido por todas las cosas nuevas. Las aventuras de Caillou retratan la relación entre los niños pequeños y el mundo que les rodea, ayudándoles a crecer con confianza e independencia. Los niños se reflejan en Caillou, lo que refuerza la unión que tiene con su pequeño hÃ©roe.",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "La novia siria",
                getGenero("Drama"),
                "Altos del Golan, Medio Oriente. Mona, una joven de origen druso, tiene que partir hacia Siria para cumplir con el matrimonio que su familia le arregló con su primo Tallel, una estrella de la televisión siria. Sin embargo para Mona no todo es color de rosa, sabe que cuando llegue a Siria nunca más podrá volver a casa ni ver a su familia.DuraciÃ²n: 98 min",
                "Hiam Abbass, Makram Khoury, Clara Khoury, Ashraf Barhoum, Eyad Sheety"
            )
        )
        agregarPelicula(
            Pelicula(
                "Quisiera ser grande",
                getGenero("Comedia"),
                "En un parque de diversiones, Joshua pide a una máquina de deseos sus ganas de ser grande. Cuando se levanta a la mañana siguiente, se da cuenta que ese deseo se hizo realidad. De cuerpo, ahora es un adulto, pero aún conserva su mentalidad de 12 años en su interior. Ahora, deberá enfrentarse al no tan amable mundo de los mayores, y lograr volver a la inocente niñez de la cual nunca debió salirse. DuraciÃ²n: 104 min",
                "Tom Hanks, Elizabeth Perkins, Robert Loggia, John Heard and Jared Rushton"
            )
        )
        agregarPelicula(
            Pelicula(
                "Bad Boys",
                getGenero("Accion"),
                "Un alijo de heroína valorado en unos 100 millones de dólares es robado del mismísimo depósito de la policía. El caso le será asignado a los agentes Burnett y Lowery, una pareja muy peculiar por los mÃ©todos que utilizan. La única pista que tienen para comenzar es la de una testigo que les ayudará a identificar a los atracadores, y a la que tendrán que proteger.DuraciÃ²n:  118 min",
                "Martin Lawrence,  Will Smith,  TÃ©a Leoni,  Tcheky Karyo,  Joe Pantoliano,  Theresa Randle,  Marg Helgenberger"
            )
        )
        agregarPelicula(
            Pelicula(
                "Baby Einstein : Un mundo de poesia (Shakespeare)",
                getGenero("Infantil/Anim"),
                "Baby Shakespeare Mundo de Poesía presenta a bebÃ©s y preescolares 12 palabras comunes que se pueden encontrar en el rico contexto de poemas clásicos y la belleza de la naturaleza. Además, mientras los pequeños se maravillan por estas estimulantes y coloreadas imágenes, se deleitarán con la gloriosa música de Ludwig van Beethoven.Duración : 101 min",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Baby Einstein : Mis primeras señas",
                getGenero("Infantil/Anim"),
                "Mis primeras señas muestra el lenguaje de las señas para estimular las palabras que aprenden en sus comienzos los bebes.  Además, mientras los pequeños se maravillan por estas estimulantes y coloreadas imágenes, se deleitarán con la gloriosa música.Duración : 27 min",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Barney : Let's go to the fire house",
                getGenero("Infantil/Anim"),
                "Divertite con Barney y sus amigos, y descubrí la estación de bomberosDuración : 56 min",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Nip Tuck : Temporada 5",
                getGenero("Series"),
                "Pack 5 dvds Sexo. Seducción. Liposucción. Encuentra todo esto en la audaz Nip/Tuck, la serie multipremiada que es el filo del bisturí del entretenimiento y el disparador para debatir sobre quÃ© puede y no puede brindarle la cirugía estÃ©tica a la vida de un paciente. Dylan Walsh y Julian McMahon interpretan a dos grandes amigos y cirujanos plásticos cuya glamorosa práctica en South Beach es la puerta giratoria para los controvertidos temas de la quinta temporada (incluyendo una aterradora historia sobre el tráfico de órganos) y las debilidades humanas (un ventrílocuo quiere verse igual a su muñeco). Emociones, sorpresas, impactos y estrellas abundan en esta colección de 5 discos. Y todo lo que se necesita es una pequeña incisión.DuraciÃ²n :  720 min",
                "Dylan Walsh,  Julian McMahon,  Joely Richardson,  John Hensley,  Valerie Cruz,  Roma Maffia,  Linda Klein"
            )
        )
        agregarPelicula(
            Pelicula(
                "El pasado",
                getGenero("Drama"),
                "El traductor Rimini se separa tras estar casado 12 años con Sofía. Comienza a salir con Vera, una modelo de 22 años que, luego de ver a Sofía besando a Rimini, muere atropellada. Un año despuÃ©s, ya recuperado, Ã©l se casa con su compañera de traducción. A partir de ese momento, Rimini se da cuenta que tiene una misteriosa amnesia que bloquea en su cerebro los idiomas que solía traducir.DuraciÃ²n :  114 min",
                "Gael García Bernal, Analía Couceyro, Moro Anghileri, Ana Celentano, Mimí Ardú"
            )
        )
        agregarPelicula(
            Pelicula(
                "Kiss : MTV Unplugged",
                getGenero("Musical"),
                "El show que que juntÃ² los viejos y nuevos integrantes de Kiss que se dio en los estudios de MTV",
                "Kiss"
            )
        )
        agregarPelicula(
            Pelicula(
                "Por siempre callas",
                getGenero("Drama"),
                "Larry Kelly, antiguo manager de María Callas, está en París por motivos profesionales. se acerca al apartamento de María, donde ella vive como una ermitaña, exciliada voluntariamente y con ayuda de drogas varias, para convencerla de que haga una aparición especial con el nombre de Callas Forever. Tras negarse varias veces, finalmente acepta fascinada por las posibilidades tecnológicas de hoy día y realiza con un clamoroso Ã©xito el video de Carmen. Tras una noche de insomnio, María le dice a Larry que debe interpretar Tosca frente al gran público y Larry accede. Durante su actuación, María revive varios momentos de su relacción con Aristóteles Onassis, María canta, actúa, dando rienda suelta a un mar de emociones, nadie nunca la había visto dar un espectáculo tan real y al terminar sólo se oye el estruendo de aplausos en la sala.DuraciÃ²n: 110 min",
                "Fanny Ardant, Jeremy Irons, Joan Plowright, Jay Rodan, Gabriel Garko, Manuel de Blas, Justino Díaz, Jean Dalric, Stephen Billington"
            )
        )
        agregarPelicula(
            Pelicula(
                "Dexter : Temporada 2",
                getGenero("Series"),
                "Pack 4dvds. Dexter, una serie sobre un experto forense de la policía metropolitana de Miami, quien por la noche se convierte en un despiadado asesino, infrigiendo su propio sentido de la justicia, y matando tan sólo a personas que se merecen morir. Dexter es una exploración inusual de la mente de un asesino sociópata, que debido a su labor de vigilante de la juticia, los espectadores lo encontrarán increíblemente simpático y carismático.DuraciÃ²n: 640 min",
                "Julie Benz, Lauren Velez, Jennifer Carpenter, James Remar, Michael C. Hall, David Zayas, Erik King"
            )
        )
        agregarPelicula(
            Pelicula(
                "Corazones",
                getGenero("Comedia"),
                "Situada en París, sigue la historia de seis personas en busca de enamorarse... Inspirada en una obra de teatro de Alan Aykborn. Una comedia agridulce, donde el tema de la soledad y la búsqueda de la felicidad engendra sólo malentendidos y situaciones jocosas.DuraciÃ²n: 120 min",
                "Sabine AzÃ©ma, Lambert Wilson, AndrÃ© Dussollier, Pierre Arditi, Laura Morante"
            )
        )
        agregarPelicula(
            Pelicula(
                "El arte de matar",
                getGenero("Suspenso"),
                "Un asesino en serie comienza a cometer brutales crímenes dejando una impronta difícilmente olvidable. Los cuerpos de sus víctimas son transformados en reflejos de grotescas obras de arte. El criminal basa el aspecto de sus crímenes en el concepto artístico de la anamorfosis, una tÃ©cnica pictórica que manipula las leyes de la perspectiva para crear dos puntos de vista diferentes de una misma composición. En una de ellas lo creado toma una forma proporcionada y clara.Duracion:  100 min",
                "Willem Dafoe, Scott Speedman, ,Peter Stormare, Clea DuVall, James Rebhorn"
            )
        )
        agregarPelicula(
            Pelicula(
                "La leccion de piano",
                getGenero("Drama"),
                "1936. Un piano marca la vida de la familia Charles. El preciado instrumento ha formado parte del clan desde hace generaciones.Duracion: 130 min",
                "Holly Hunter, Harvey Keitel, Sam Neill, Anna Paquin, Kerry Walker, GeneviÃ¨ve Lemon, Tungia Baker"
            )
        )
        agregarPelicula(
            Pelicula(
                "Carne tremula",
                getGenero("Drama"),
                "La vida le ha dado a Víctor poca felicidad. Y cuando parece que el curso de los acontecimientos por fin le sonríe, otra vez el destino le juega una mala pasada. En esta oportunidad su futuro queda encerrado en su obsesión de venganza.Duracion: 134 min",
                "Angela Molina, Javier Bardem, Francesca Neri, Liberto Rabal, Jose Sancho"
            )
        )
        agregarPelicula(
            Pelicula(
                "The Unit : Temporada 1",
                getGenero("Series"),
                "Pack 4 dvds. The Unit, es la serie de acción que nos introduce en las operaciones de un equipo encubierto de Fuerzas Especiales del ejÃ©rcito de los Estados Unidos, que arriesgan sus vida a diario en misiones de infiltración secretas a lo largo y ancho de todo el planeta, mientras sus respectivas familias se tienen que quedar en el hogar familiar, protegiendo los secretos de sus maridos.DuraciÃ²n: 572 min",
                "Dennis Haysbert, Scott Foley, Audrey Marie Anderson, Demore Barnes, Abby Brammell"
            )
        )
        agregarPelicula(
            Pelicula(
                "La Mary",
                getGenero("Nacional"),
                "La Mary es una atractiva chica de barrio que conoce a un joven apuesto y vigoroso con el que se casa. Entran en colisión, entonces, sus tabúes, prejuicios, un deseo sexual desenfrenado y una incipiente locura que hará desembocar la historia en un terrible crimen pasional.DuraciÃ²n: ",
                "Susana GimÃ©nez, Carlos Monzón, Olga Zubarry, Alberto Argibay, Dora Baret, Jorge Rivera López, Teresa Blasco, Juan JosÃ© Camero"
            )
        )
        agregarPelicula(
            Pelicula(
                "Mirame la palomita",
                getGenero("Nacional"),
                "Cayetano (Jorge Porcel), un mediocre representante de actores, viaja a Mar del Plata con su mujer, Carola (Susana Traverso), para arreglar el contrato de JosÃ© (Mario Sapag), un desconocido imitador que representa su número cómico en distintas boites. En el camino ven un cartel de publicidad que anuncia la última película del famoso actor de filmes eróticos, Federico Lupa (Alberto Olmedo). Los elogios de ella provocan una áspera discusión entre ambos. Ya en la ciudad y con la excusa de tener mucho trabajo, Cayetano se encuentra con JosÃ© y juntos van al Casino y a divertirse con cuanta joven y hermosa mujer se les cruza en el camino. La casualidad hace que al descomponÃ©rsele el auto a Carola en medio de la lluvia torrencial, sea precisamente Federico Lupa quien pare a auxiliar y la lleve hasta su casa. Con el pretexto de secarse y tomar algo caliente consigue que ella lo invite a entrar mientras trata de seducirla, Carola se equivoca y pone somníferos en lugar de sacarinas en el cafÃ©. Cuando se da cuenta y le pide que se vaya, se quedan dormidos, ella en deshabillÃ© y Ã©l con un pijama de Cayetano, que se había puesto hasta que se le secara la ropa. El escándalo que se origina al ser sorprendidos en tan comprometedora situación provoca una crisis matrimonial, agravada aún más al descubrir las infidelidades de CayetanoDuraciÃ²n: 79 min",
                "Jorge Porcel, Alberto Olmedo, Mario Sapag, Susana Traverso"
            )
        )
        agregarPelicula(
            Pelicula(
                "Vitus",
                getGenero("Drama"),
                "Vitus muestra ya desde niño algunas dotes privilegiadas, especialmente para la música. A diferencia de los chicos de su edad, Ã©l toca el piano como un virtuoso y estudia enciclopedias con sólo cinco años de edad. Sus padres le anticipan un brillante porvenir, deseando que sea pianista. Sin embargo, el pequeño genio prefiere pasar su tiempo en el taller de su excÃ©ntrico abuelo, sueña con volar y con tener una infancia normal. Como consecuencia de un inesperado accidente, Vitus tomará las riendas de su propia vida y familia.DuraciÃ²n : 120 min",
                "Fabrizio Borsani, Teo Gheorghiu, Julika Jenkins, Urs Jucker, Bruno Ganz, Eleni Haupt, Kristina Lykowa, Tamara Scarpellini"
            )
        )
        agregarPelicula(
            Pelicula(
                "El graduado",
                getGenero("Clasicos"),
                "Con el espíritu rebelde de los 60 y al ritmo de las magníficas composiciones de Simon and Garfunkel, El Graduado es verdaderamente una película memorable. El tímido Benjamin Braddock (Dustin Hoffman) vuelve al hogar reciÃ©n graduado de la universidad con un futuro incierto. La esposa del socio de su padre, la sexy Sra. Robinson (Anne Bancroft), lo seduce y esta situación solo logra profundizar aún más su confusión. Esto es, hasta que conoce a Elaine, la chica de sus sueños (Katharine Ross). Pero hay un problema: Ella es la hija de la Sra. Robinson!.DuraciÃ²n: 106 min",
                "Dustin Hoffman, Anne Bancroft, Katharine Ross, William Daniels, Murray Hamilton, Elizabeth Wilson"
            )
        )
        agregarPelicula(
            Pelicula(
                "El superagente 86 : Temporada 5",
                getGenero("Series"),
                "Pack 4 dvds. Â¿Me creería si le dijera que llegan los 30 episodios de la tercera temporada en 4 DVDs? Â¿Me creería si le dijera que contienen más de 2 horas de imágenes adicionales y de material extra? Así es Â¡es el Super Agente 86 en DVD, restaurado y remasterizado digitalmente! Ahora puedes engañar al más temible de los agentes secretos del recontra espionaje, con esta ingeniosa y divertida colección que presenta los 30 episodios de la exitosa tercera temporada del Super Agente 86. ",
                "Don Adams, Barbara Feldom"
            )
        )
        agregarPelicula(
            Pelicula(
                "La dolce vita",
                getGenero("Clasicos"),
                "Ganadora de un Oscar y del Premio en el Festival de Cine de Cannes, la Obra Maestra de Federico Fellini fue la que dió orígen al tÃ©rmino paparazzi, y catapultó a Fellini al panteón de los directores visionarios. Marcello Mastroianni protagoniza a un columnista de chismes llamado Marcello, papel que lo convirtió en una sensación internacional. Su territorio es el Jet-set Internacional de Roma, un mundo exótico vivificado por las imágenes de Fellini y la música del compositor Nino Rota. En pos del próximo gran escándalo, Marcello siente seducción y rechazo por el estilo de vida hedonista de los ricos, inmorales y aburridos para quienes nada es sagrado. Sus inolvidables encuentros con una norteamericana espectacular (Anita Ekberg), su amante adinerada (Anouk AimÃ©e), su amiga suicida, su mentor culto y su padre alienado, ponen al descubierto el vacío detrás del esplendor de la dulce vida. La Dolce Vita, una de las películas de las que más se habló, es un logro imponente del cine mundial y uno de los films más populares e influyentes de todos los tiempos.DuraciÃ²n: 174 min",
                "Marcello Mastroianni, Anita Ekberg, Anouk AimÃ©e, Yvonne Furneaux, Magali NoÃ«l, Alain Cuny, Annibale Ninchi, Walter Santesso"
            )
        )
        agregarPelicula(
            Pelicula(
                "Venus",
                getGenero("Drama"),
                "Maurice (Peter O Toole) e Ian (Leslie Phillips) son viejos amigos y actores semijubilados de segunda fila. A pesar de haber llegado a los años dorados, siguen trabajando. Por ejemplo, Maurice encarna a un paciente hospitalizado en una telenovela. Pero su cómoda rutina y sus charlas matutinas en un cafÃ© se ven interrumpidas por la llegada de Jessie (Jodie Whitaker), la bisnieta de Ian. Jessie no tarda en sacar de quicio a su tío abuelo. Pero la chica cae bien a Maurice, que decide enseñarle Londres.Y mientras intenta ayudar a Jessie, le sorprende descubrir lo poco que sabe de sí mismo cuando su vida está a punto de acabar.Duración: 100 min",
                "Peter O Toole, Leslie Phillips, Jodie Whittaker, Richard Griffiths, Vanessa Redgrave"
            )
        )
        agregarPelicula(
            Pelicula(
                "Los 3 Chiflados : Spook Louder",
                getGenero("Clasicos"),
                "Divertite con los 3 Chiflados !! 6 capítulos para recordar de los 3 grandes del humorFantasmas Fachosos (1943), Momios y Momias (1948), Detectives Temblorosos (1947), El Fantasma que Habla (1949), Hokus Pokus (1949), Noche de Terror (1947).Duración: 99 min",
                "Larry Fine, Moe Howard, Curly Howard, Shemp Howard"
            )
        )
        agregarPelicula(
            Pelicula(
                "La educación de las hadas",
                getGenero("Nacional"),
                "Nicolás, Ingrid, y Raoul, el pequeño hijo de ella, viven felizmente desde que se encontraron, hace ya cuatro años, hasta el día en que Ingrid anuncia que ha decidido abandonarlo. Pero Raoul cree haber encontrado la solución mágica: encontrar un hada que salve el matrimonio de sus padres. Las cosas se complican con la aparición de Sezar, una joven exiliada iraquí.Duración: 103 min",
                "Ricardo Darín, IrÃ¨ne Jacob, Bebe, Carles Arquimbau, Abdelaziz Arradi"
            )
        )
        agregarPelicula(
            Pelicula(
                "Mi pobre angelito",
                getGenero("Familia"),
                "Kevin McAllister es un niño de ocho años, miembro de una familia numerosa, que accidentalmente se queda abandonado en su casa cuando toda la familia se marcha a pasar las vacaciones a Francia. Kevin aprende a valerse por sí mismo e incluso a protegerse de Harry y Marv, dos bribones que se proponen asaltar todas las casas cerradas de su vecindario. Cuando su madre Kate lo hecha en falta, realiza un pintoresco viaje de vuelta contra reloj a Chicago para recuperar a su hijo.Duración: 100 min",
                "Macaulay Culkin,  Joe Pesci,  Daniel Stern,  John Heard,  Catherine O Hara"
            )
        )
        agregarPelicula(
            Pelicula(
                "Mi pobre angelito",
                getGenero("Familia"),
                "En esta ocasión, el pequeño Kevin acude, en plenas fechas navideñas, con su familia al aeropuerto con el objetivo de disfrutar de unas pequeñas vacaciones de navidad todos en familia, pero Ã©l se embarca en un avión equivocado que le lleva a Nueva York, donde vuelve a estar solo y desprotegido.Duración: 113",
                "Macaulay Culkin,  Joe Pesci,  Daniel Stern,  John Heard,  Catherine O Hara,  Tim Curry,  Devin Ratray,  Brenda Fricker,  Eddie Bracken"
            )
        )
        agregarPelicula(
            Pelicula(
                "El regalo prometido",
                getGenero("Familia"),
                "Un padre que promete y no cumple. Que intenta llegar y no puede. Todos los delirios que puedan sucederle le pasarán a este papá desesperado por conseguir revertir la situación de siempre, ahora tras el imposible de conseguir en vísperas de Navidad, el juguete más deseado por toda la platea infantil: Turbo Man ... risas en un papel por siempre carismático y que le queda bien a Arnold Schwarzenegger.Duracion: 90 min",
                "Arnold Schwarzenegger, James Belushi, Robert Conrad, Martin Mull, Rita Wilson, Sinbad, Phil Hartman"
            )
        )
        agregarPelicula(
            Pelicula(
                "El superagente 86 : Temporada 4",
                getGenero("Series"),
                "Pack 4 dvds. Â¿Me creería si le dijera que llegan los 30 episodios de la tercera temporada en 4 DVDs? Â¿Me creería si le dijera que contienen más de 2 horas de imágenes adicionales y de material extra? Así es Â¡es el Super Agente 86 en DVD, restaurado y remasterizado digitalmente! Ahora puedes engañar al más temible de los agentes secretos del recontra espionaje, con esta ingeniosa y divertida colección que presenta los 30 episodios de la exitosa tercera temporada del Super Agente 86. ",
                "Don Adams, Barbara Feldom"
            )
        )
        agregarPelicula(
            Pelicula(
                "El gran dictador",
                getGenero("Comedia"),
                "Durante la Primera Guerra Mundial, un anóni-mo combatiente de la armada de Tomania sal-va la vida de un oficial llamado Schultz. Pero el avión en el que huyen se estrella y, mientras Schultz resulta indemne, el soldado ingresa en un hospital por amnesia. Allí permanecerá veinte años de su vida, ignorando por completo los cambios que se producen a su alrede-dor. Hynkel se convertirá en el dictador de Tomania y perseguirá despiadadamente a los judíos con la ayuda de sus dos ministros, Garbitsch y Herring. A la salida del hospital, el soldado regresa a su antigua barbería en el Ghetto, esperando encontrar todo lo que dejó veinte años atrás. Allí, conocerá a Hannah, una joven de la que se enamorará. Mientras, Schultz se ha convertido en un influyente oficial del rÃ©gimen y ordena a sus tropas dejar al barbero en paz. Hynkel planifica la invasión de Osterlich, país fronterizo, y cuando Schultz pone en tela de juicio esta decisión, el dictador lo condena a ingresar en un campo de concentración. Inmediatamente, Schultz planea su fuga intentando provocar una rebelión contra el gobierno. Entonces, se refugia en el Ghetto, en casa de su amigo el barbero, pero las tropas de Hynkel queman la tienda, arrestan a los dos hombres y los internan en un campo. Prosiguiendo su plan para invadir Osterlich, Hynkel invita a palacio a Napaloni, el dictador de Bacteria y, despuÃ©s de varios desacuerdos cómicos, los dos hom-bres logran establecer una alianza. La invasión de Osterlich es un Ã©xito y Hannah, quiÃ©n se ha refugiado en este país con sus ami-gos, vuelve a encontrarse otra vez más, bajo la dominación del rÃ©-gimen de Hynkel. Mientras el dictador celebra su última conquista disfrutando de unas vacaciones en el país, Schultz y su amigo el barbero consiguen huir del campo de concentración. Por error, Hyn-kel será detenido por sus propias tropas y el barbero, confundido por el dictador",
                "Charles Chaplin, Jack Oakie, Reginald Gardiner, Henry Daniell, Billy Gilbert, Grace Hayle"
            )
        )
        agregarPelicula(
            Pelicula(
                "Brian Adams : Mtv unplugged",
                getGenero("Musical"),
                "En 1997, y ya observando la gran acogida que el público dio a la música de Bryan (en especial en la dÃ©cada de los 80's), la cadena televisiva de MTV decide darle la oportunidad de que grabe su propio MTV Unplugged (álbum que para todo artista representa un reto, pues en Ã©l no solo se graban las versiones famosas en guitarras acústicas y percusión sencilla se presenta en el mercado con tres nuevas canciones, Back To You, A little love y When You Love Someone (además de incluir un Blues Jam)",
                "Bryan Adams"
            )
        )
        agregarPelicula(
            Pelicula(
                "Milagro en la calle 34",
                getGenero("C.Ficcion"),
                "DespuÃ©s de que su madre le revelera que Santa Claus no existe, las Navidades nunca fueron lo mismo para Susan. Cuando conoce a un simpático Santa Claus contratado por unos grandes almacenes, Susan comenzará de nuevo a creer en los milagros.",
                "Richard Attenborough, Elizabeth Perkins, Dylan McDermott, J.T. Walsh, James Remar"
            )
        )
        agregarPelicula(
            Pelicula(
                "Santa Clausula",
                getGenero("Comedia"),
                "Scott Calvin es el padre divorciado de Charlie. Scott se encuentra enfadado porque la madre de Charlie, Laura, y su padrastro, un psiquiatra llamado Neal, le han contado que Santa Claus no existe. Mientras el enfadado Charlie visita a su padre el día de Navidad un ruido en el tejado hace subir a su padre que se enfrenta con el intruso que se acaba cayendo al vacío. El intruso accidentalmente muerto resulta ser Santa y debido a una misteriosa cláusula ahora Scott deberá ocupar su puestoDuración : 97 min",
                "Tim Allen,  Judge Reinhold,  Wendy Crewson,  David Krumholtz,  Peter Boyle"
            )
        )
        agregarPelicula(
            Pelicula(
                "Duro de matar",
                getGenero("Accion"),
                "El detective John McClane viaja desde Nueva York hasta Los Angeles para encontrarse con su ex-esposa, que trabaja para la corporación Nakatomi, una empresa japonesa. En el medio de los festejos de nochebuena, un grupo de terroristas copa el edificio y toma a los empleados como rehenes. Los terroristas quieren 600 millones de dólares en bonos y harán cualquier cosa para obtenerlos. Pero McLane está oculto en el edificio y va a realizar lo imposible para liberar a los rehenes.Duración :  133 min",
                "Hart Bochner, Alan Rickman, Bruce Willis, Andreas Wisniewski, Bonnie Bedelia, Alexander Godunov, Robert Davi, Reginald VelJohnson"
            )
        )
        agregarPelicula(
            Pelicula(
                "Duro de matar 2",
                getGenero("Accion"),
                "Es Navidad. El policía del departamento de Los Angeles, teniente John McLane, espera en el aeropuerto de Dulles de Washington a que aterrice el avión en el que viaja su esposa. Mientras tanto, el coronel del ejÃ©rcito Stuart, un militar bien entrando, expulsado del congreso, junto con otros mercenarios profesionales, montan una base de operaciones cerca de dicho aeropuerto, con el objetivo de liberar a un dictador sudamericano derrocado, considerado el mayor traficante del mundo, y que dentro de 58 minutos aterrizará en Dulles en calidad de prisionero político. Stuart anula todos los sistemas que permiten aterrizar a los demás aviones, condenados a permanecer en el aire con el riesgo de agotárseles el combustible. Exige, además, que un Boeing 747 estÃ© a su disposición para huir con el dictador una vez liberado, pero McClane intentará que no prospere...Duración: 123 min",
                "Bruce Willis,  Bonnie Bedelia,  Franco Nero,  Dennis Franz,  William Atherton,  Reginald Veljohnson,  William Sadler,  John Amos,  Colm Meaney"
            )
        )
        agregarPelicula(
            Pelicula(
                "Duro de matar 3",
                getGenero("Accion"),
                "Ahora la acción transcurre en las calles de Nueva York, donde John McClane enfrenta a un genio militar que con sus mercenarios pone bombas en lugares hiper concurridos de la cuidad y luego roba la reserva federal.Duración : 124 min",
                "Bruce Willis, Jeremy Irons, Samuel L. Jackson, Graham Greene, Colleen Camp"
            )
        )
        agregarPelicula(
            Pelicula(
                "La muerte le sienta bien",
                getGenero("Comedia"),
                "Una comedia sumamente divertida acerca de la codicia, la vanidad, el sexo, la inmoralidad, la vida y la muerte. En primer plano esta la actriz Madeline Ashton que tiene mas arrugas que papeles para interpretar. Luego su ex amiga Helen cuyo novio lo engaña con Madeline y por lo tanto aumenta de peso. Finalmente el marido de Madeline un reconocido cirujano plastico que se pasa las noches emborrachandose y maldiciendo el dia en que conocio a su esposa. Pero llega una hermosa y encantadora mujer, Isabella Rossellini que cambia sus vidas y muertes para siempre.Duración: 104 min",
                "Meryl Streep, Goldie Hawn, Bruce Willis, Isabella Rossellini"
            )
        )
        agregarPelicula(
            Pelicula(
                "Shrek : The halls - Especial de navidad",
                getGenero("Infantil/Anim"),
                "Justo cuando Shrek pensaba que, por fin, podría sentarse, relajarse en su ciÃ©naga y disfrutar de su reciÃ©n ampliada familia... llega la Navidad. Es Nochebuena y todo el mundo está feliz, excepto Shrek. En estas fechas, el ogro no es precisamente la viva imagen de la alegría. A pesar de todo y aunque sólo sea por Fiona y los bebÃ©s, intenta contagiarse del espíritu navideño y promete a su mujer una gran sorpresa navideña. DespuÃ©s del Ã©xito de la tercera secuela de Shrek, se estrenó en televisión esta producción de animación de media hora de duración que se centra en el gruñón ogro y su pandilla. Adaptada a la Ã©poca navideña, la cinta contó con los mismos dobladores que en la saga cinematográfica: Mike Myers, Eddie Murphy y Cameron Díaz en la versión original; y las voces de Cruz y Raya en la española. El internacional Antonio Banderas realiza un doblete interpretando en ambos idiomas al carismático Gato con Botas.Duración : 30 min",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "El superagente 86 : Temporada 3",
                getGenero("Series"),
                "Pack 5 dvds. Â¿Me creería si le dijera que llegan los 30 episodios de la tercera temporada en 5 DVDs? Â¿Me creería si le dijera que contienen más de 2 horas de imágenes adicionales y de material extra? Así es Â¡es el Super Agente 86 en DVD, restaurado y remasterizado digitalmente! Ahora puedes engañar al más temible de los agentes secretos del recontra espionaje, con esta ingeniosa y divertida colección que presenta los 30 episodios de la exitosa tercera temporada del Super Agente 86. ",
                "Don Adams, Barbara Feldom"
            )
        )
        agregarPelicula(
            Pelicula(
                "Patoaventuras : El tesoro de la lámpara perdida",
                getGenero("Infantil/Anim"),
                "El tío Rico y los sobrinos del pato Donald encuentran un legendario tesoro en una pirámide. No se imaginan que entre las piedras preciosas se encuentra una lámpara mágica que contiene un genio. Sin embargo, un malvado hechicero pretende apoderarse de la lámpara.Duración : 71 min",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Grey's Anathomy : Temporada 1",
                getGenero("Series"),
                "Pack 2 dvds. Conoce a Meredith Grey, una estudiante de medicina en su primer año como interna en el área de cirugía del Hospital Grace de Seattle. Junto a sus compañeros residentes, Meredith atraviesa traumas diarios y trampas sociales en su vida dentro del hospital, así como fuera de Ã©l, en el mundo verdadero. GreyÂ´s Anatomy es una inteligente e ingeniosa mirada a los jóvenes que luchan por convertirse en mÃ©dicos y a los mÃ©dicos que luchan por mantenerse humanos.Duración: 390",
                "Ellen Pompeo, Sandra Oh, Katherine Heigl, Justin Chambers, T.R. Knight"
            )
        )
        agregarPelicula(
            Pelicula(
                "Dexter : Temporada 1",
                getGenero("Series"),
                "Pack 4 dvds. Basada en la novela de Jeff Lindsay, Darkly Dreaming Dexter, Dexter es una serie de suspense que narra la historia de un hombre extraño llamado Dexter Morgan. Cuando era niño, Dexter (Michael C. Hall, A dos metros bajo tierra), fue maltratado y abandonado por sus padres. Ahora es un exitoso e importante forense patológico... pero bajo su carismática personalidad, se esconde una terrible verdad. Dexter ha canalizado sus innatas necesidades homicidas en una segunda profesión que guarda celosamente en secreto: buscar, dar caza y asesinar brutalmente a despiadados criminales que han conseguido evitar caer en las garras de la ley.Duración : 650 min. Atencion : Backup importado. No tiene subitutlos en español. Solo audio en español",
                "Michael C. Hall,  Julie Benz,  Jennifer Carpenter,  Erik King,  Lauren Velez,  David Zayas,  James Remar"
            )
        )
        agregarPelicula(
            Pelicula(
                "La pistola desnuda",
                getGenero("Comedia"),
                "Buscando venganza despuÃ©s de que su compañero Nordberg fuera tiroteado por una banda de narcotraficantes, el incompetente teniente Frank Drebin busca al cerebro al frente de la organización. Sospecha del magnate naviero Vincent Ludwig, pero no puede probarlo. Enfrentándose a la oposición de la alcaldesa, Drebin consigue la inesperada ayuda de Jane Spencer, una antigua novia de Ludwig. Duración : 85 min",
                "Leslie Nielsen,  Priscila Presley,  George Kennedy,  Ricardo Montalbán,  O.J. Simpson,  John Houseman"
            )
        )
        agregarPelicula(
            Pelicula(
                "La pistola desnuda 2 1/2",
                getGenero("Comedia"),
                "El inefable y patoso teniente de policía Frank Drebbin ha vuelto para salvar a la ciudad, esta vez para enfrentarse a los villanos de la industria energÃ©tica. Un importante científico, el doctor Mainheimer, está a punto de publicar un informe sobre el suministro energÃ©tico del futuro que pinta mal para los dueños de las industrias del petróleo, el carbón y la nuclear. Así que las industrias secuestran a Mainheimer y lo reemplazan por un doble más favorable a sus intereses. Jane, la secretaria del doctor, es el viejo amor de Drebbin y su llama pasional volverá a reavivarse.Duración : 84 min",
                "Leslie Nielsen,  Priscila Presley,  George Kennedy,  O.J. Simpson,  Robert Goulet,  Richard Griffiths,  Jacqueline Brookes"
            )
        )
        agregarPelicula(
            Pelicula(
                "La pistola desnuda 33 1/3",
                getGenero("Comedia"),
                "La noche de los Oscar, Â¿quiÃ©n ganará?, Â¿quiÃ©n perderá?, y por favor, Â¿puede alguien quitar a ese imbÃ©cil del escenario? Â¡Esperen! No se trata de un imbÃ©cil normal y corriente. Es el Teniente Frank Drebin, destrozando la ceremonia para detener un plan terrorista que podría ser su final... Y va a convertirse en el principio de toda una locura.Duración : 83 min",
                "Leslie Nielsen,  Priscila Presley,  George Kennedy,  Fred Ward,  Anna Nicole Smith,  Pia Zadora,  Raquel Welch,  Ellen Greene,  O.J. Simpson"
            )
        )
        agregarPelicula(
            Pelicula(
                "Ben 10 : Temporada 1",
                getGenero("Series"),
                "Pack 2 dvds. Temporada 1.Ben 10 cuenta las aventuras de Ben Tennyson, un niño normal de 10 años que descubre un extraño reloj extraterrestre en un meteorito que chocó contra la Tierra. Ben pronto se da cuenta de que el reloj le permite transformarse en 10 seres extraterrestres distintos , sin perder su personalidad de niño. En un día típico, Ben se convierte entonces en 10 superhÃ©roes alienígenas que luchan contra los malechores para salvar al mundo. A veces, además, Ã©l usa los poderes para satisfacer su curiosidad infantil. Duracion : 286 min",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Ben 10 : Temporada 2",
                getGenero("Series"),
                "Pack 2 dvds. Temporada 2Ben 10 cuenta las aventuras de Ben Tennyson, un niño normal de 10 años que descubre un extraño reloj extraterrestre en un meteorito que chocó contra la Tierra. Ben pronto se da cuenta de que el reloj le permite transformarse en 10 seres extraterrestres distintos , sin perder su personalidad de niño. En un día típico, Ben se convierte entonces en 10 superhÃ©roes alienígenas que luchan contra los malechores para salvar al mundo. A veces, además, Ã©l usa los poderes para satisfacer su curiosidad infantil. Duracion : 300 min",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Ben 10 : Temporada 3",
                getGenero("Series"),
                "Pack 2 dvds. Temporada 3Ben 10 cuenta las aventuras de Ben Tennyson, un niño normal de 10 años que descubre un extraño reloj extraterrestre en un meteorito que chocó contra la Tierra. Ben pronto se da cuenta de que el reloj le permite transformarse en 10 seres extraterrestres distintos , sin perder su personalidad de niño. En un día típico, Ben se convierte entonces en 10 superhÃ©roes alienígenas que luchan contra los malechores para salvar al mundo. A veces, además, Ã©l usa los poderes para satisfacer su curiosidad infantil. Duracion : 286 min",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "V : La miniserie original",
                getGenero("Series"),
                "Pack 2 dvds. Primera parte de la miniserie que revolucióno la televisiónCincuenta naves, cada una de seis kilómetros de diámetro, se comienzan a colocar sobre las ciudades más importantes del mundo. De las naves salen seres de apariencia humana y extienden sus manos en señal de cortesía, pero detrás de ese aspecto amigable se esconden unos malvados extraterrestres cuyo verdadero plan es absorver los recursos terrestres y llevárselos a su planeta. Duración: 200 min",
                "Marc Singer, Lane Smith, Faye Grant, Jennifer Cooke, Michael Wright, Robert Englund, Jane Badler, Blair Tefkin, Michael Fireside, "
            )
        )
        agregarPelicula(
            Pelicula(
                "V Invasion extraterrestre : La batalla final",
                getGenero("Series"),
                "Pack 3 dvds.  V: Invasion extraterrestre: La Batalla Final, es la segunda y última parte de la miniserie. Duración: 290 min",
                "Marc Singer, Lane Smith, Faye Grant, Jennifer Cooke, Michael Wright, Robert Englund, Jane Badler, Blair Tefkin, Michael Fireside, "
            )
        )
        agregarPelicula(
            Pelicula(
                "Hombre mirando al sudeste",
                getGenero("Nacional"),
                "El doctor Denis es el mÃ©dico encargado de una de las salas de un hospital psiquiátrico. De improviso aparece en el lugar un joven que dice llamarse RantÃ©s y provenir de otro planeta. El mÃ©dico supone que este individuo es un farsante. Ante la insistencia del joven, el doctor concluye que se trata de un paranoico delirante, salvo por algunos hechos inquietantes, de lo que el espectador comienza a ser testigo. Duracion : 80 min",
                "Lorenzo Quinteros, Hugo Soto"
            )
        )
        agregarPelicula(
            Pelicula(
                "Numbers : Temporada 2",
                getGenero("Series"),
                "Pack 6 dvds.Recuento de cadáveres, diferentes mentes maestras criminales, asesinos en potencia dispuestos a actuar de nuevo. Este es el mundo de NUMB3RS.El agente del FBI Don Eppes (Rob Morrow) no puede ser más distinto de su hermano pequeño Charlie (David Krumholtz), brillante profesor de matemáticas en la Universidad de California. Don se enfrenta a la dura realidad de los hechos y las pruebas, mientras que Charlie se maneja en un mundo de probabilidades matemáticas y ecuaciones. Pero al margen de sus vidas y carreras opuestas, Don y Charlit ocasionalmente combinan sus experiencias para resolver un amplio número de desconcertantes crímenes en Los Angeles. ",
                "Rob Morrow, Diane Farr, Judd Hirsch, David Krumholtz, Alimi Ballard, Peter MacNicol, Navi Rawat, Dylan Bruno"
            )
        )
        agregarPelicula(
            Pelicula(
                "CSI : Las Vegas : Temporada 1",
                getGenero("Series"),
                "Pack 6 dvs. Esta multipremiada serie, cuenta la historia de los poco conocidos y menos comprendidos hÃ©roes que trabajan en las escenas de crimen: los investigadores forenses. Estos son hombres y mujeres que trabajan tiempo completo para desentrañar la evidencia detrás de las líneas amarillas policiales, reconstruyendo las piezas de un rompecabezas aparentemente insignificante, poniendo al descubierto a los delincuentes y sirviendo a aquellos que más los necesitan, las víctimas",
                "Paul Guilfoyle, Marg Helgenberger, Jorja Fox, William Petersen, Gary Dourdan"
            )
        )
        agregarPelicula(
            Pelicula(
                "CSI : Las Vegas : Temporada 2",
                getGenero("Series"),
                "Pack 6 dvs. Esta multipremiada serie, cuenta la historia de los poco conocidos y menos comprendidos hÃ©roes que trabajan en las escenas de crimen: los investigadores forenses. Estos son hombres y mujeres que trabajan tiempo completo para desentrañar la evidencia detrás de las líneas amarillas policiales, reconstruyendo las piezas de un rompecabezas aparentemente insignificante, poniendo al descubierto a los delincuentes y sirviendo a aquellos que más los necesitan, las víctimas. Atencion : Backup importado. No tiene subitutlos en español. Solo audio en español",
                "Paul Guilfoyle, Marg Helgenberger, Jorja Fox, William Petersen, Gary Dourdan"
            )
        )
        agregarPelicula(
            Pelicula(
                "CSI : Las Vegas : Temporada 3",
                getGenero("Series"),
                "Pack 6 dvs. Esta multipremiada serie, cuenta la historia de los poco conocidos y menos comprendidos hÃ©roes que trabajan en las escenas de crimen: los investigadores forenses. Estos son hombres y mujeres que trabajan tiempo completo para desentrañar la evidencia detrás de las líneas amarillas policiales, reconstruyendo las piezas de un rompecabezas aparentemente insignificante, poniendo al descubierto a los delincuentes y sirviendo a aquellos que más los necesitan, las víctimas. Atencion : Backup importado. No tiene subitutlos en español. Solo audio en español",
                "Paul Guilfoyle, Marg Helgenberger, Jorja Fox, William Petersen, Gary Dourdan"
            )
        )
        agregarPelicula(
            Pelicula(
                "Pecado original",
                getGenero("Drama"),
                "Julia Russell le puede manifestar a un hombre una pasion que jamas experimento el, arriesgara todo por estar cerca de ella. Cuando luis vargas se casa con julia, piensa que su vida es perfecta. pero ella desaparece con su dinero y el se ve obligado a indagar en su pasado, un pasado que parece no existir, y se entera de que la mujer que ama esta acusada de un feroz asesinato.Duración: 99 min",
                "Antonio Banderas, Angelina Jolie, Thomas Jane, Jack Thompson, Gregory Itzin"
            )
        )
        agregarPelicula(
            Pelicula(
                "Dead Zone : Temporada 1",
                getGenero("Series"),
                "Pack: 4 dvds. Basada en los personajes y en la historia del best-seller escrito por Stephen King, La Zona Muerta es un thriller psicológico inigualable, que combina magníficamente lo paranormal, con acción y una continua búsqueda de justicia. Duración: 569 min",
                "David Ogden Stiers,Nicole de Boer,Anthony Michael Hall,Chris Bruno,John L. Adams,Kristen Dalton"
            )
        )
        agregarPelicula(
            Pelicula(
                "Dead Zone : Temporada 2",
                getGenero("Series"),
                "Pack 5 dvds. Basada en los personajes y en la historia del best-seller escrito por Stephen King, La Zona Muerta es un thriller psicológico inigualable, que combina magníficamente lo paranormal, con acción y una continua búsqueda de justicia.Duracion: ",
                "David Ogden Stiers,Nicole de Boer,Anthony Michael Hall,Chris Bruno,John L. Adams,Bill Mondy"
            )
        )
        agregarPelicula(
            Pelicula(
                "Dead Zone : Temporada 3",
                getGenero("Series"),
                "Pack 3 dvds. Basada en los personajes y en la historia del best-seller escrito por Stephen King, La Zona Muerta es un thriller psicológico inigualable, que combina magníficamente lo paranormal, con acción y una continua búsqueda de justicia. Duración: 510 min",
                "David Ogden Stiers,Nicole de Boer,Anthony Michael Hall,Chris Bruno,John L. Adams,Bill Mondy"
            )
        )
        agregarPelicula(
            Pelicula(
                "Numbers : Temporada 1",
                getGenero("Series"),
                "Pack 4 dvds. Esta producción es un drama policial basado en la labor de un agente especial del FBI que recluta a su hermano pequeño, un prodigio de las matemáticas, para que le ayude a resolver todo tipo de crímenes ocurridos en Los Ãngeles. La serie, que trabaja con la premisa de que LOS NÃšMEROS NO MIENTEN; LAS PERSONAS SÃ, demuestra cómo la confluencia del trabajo de la policía y las matemáticas proporciona revelaciones inesperadas y da respuesta a los más complejos casos criminales",
                "Peter MacNicol,Rob Morrow,Judd Hirsch,David Krumholtz,Alimi Ballard,Navi Rawat"
            )
        )
        agregarPelicula(
            Pelicula(
                "ReciÃ©n casados",
                getGenero("C.Romantica"),
                "Tom es un reportero nocturno del tráfico, su forma de vida y su implacable pasión por los deportes lo convierten en el último macho dominante. Sara es una hermosa escritora, libre de espíritu, cuya familia es completamente rica. Para sorpresa de los amigos de Tom y la consternación de la familia de Sarah, ambos se enamoraron instantáneamente. DespuÃ©s de la boda, la feliz pareja parte con grandes esperanzas e ideales sobre el amor y el matrimonio, en lo que ellos esperan sea una perfecta luna de miel en Italia. Pero gracias al ex-novio de Sara y a una inexplicable racha de mala suerte, la feliz pareja experimenta una catastrófica luna de miel que prueba los límites de su joven amor. Duración : 95 min",
                "Ashton Kutcher, Brittany Murphy, Christian Kane, David Moscow, Monet Mazur, David Rasche, Raymond J. Barry"
            )
        )
        agregarPelicula(
            Pelicula(
                "Madonna : The inmaculate collection",
                getGenero("Musical"),
                "13 Temas inolvidables que te sacudirán la cabeza. . Lucky Star 2. Borderline 3. Like a Virgin 4. Material Girl 5. Papa Don't Preach 6. Open Your Heart 7. La Isla Bonita 8. Like a Prayer 9. Express Yourself 10. Cherish 11. Oh Father 12. Vogue 13. Vogue (live from the 1990 MTV Video Music Awards). Duración: 70 min",
                "Madonna"
            )
        )
        agregarPelicula(
            Pelicula(
                "Nip Tuck : Temporada 1",
                getGenero("Series"),
                "Como dice el dicho, la belleza está en el interior de las personas. La belleza de Nip/Tuck reside en que va más profundo aún, dejando al desnudo las complejidades y frágiles naturalezas que a menudo se ven en los pacientes que buscan la cirugía estÃ©tica. Dylan Walsh y Julian McMahon interpretan a unos famosos cirujanos plásticos de South Beach Miami, quienes atraviesan sus propias crisis de la mediana edad, mientras confrontan sus problemas de carrera, familia y amor. Las agudas historias de la serie incluyen humor, suspenso y desmesurado poder. Las cirugías son gráficamente osadas. La sexualidad es descontrolada. Duración: 640 min",
                "Dylan Walsh,  Julian McMahon,  Joely Richardson,  John Hensley,  Valerie Cruz,  Roma Maffia,  Linda Klein"
            )
        )
        agregarPelicula(
            Pelicula(
                "Nip Tuck : Temporada 2",
                getGenero("Series"),
                " Dylan Walsh y Julian McMahon regresan como el equipo Ã©lite de cirugía estÃ©tica de McNamara/Troy, cuyo talento para dar a otros los cuerpos perfectos, contrasta con los detalles imperfectos de estos doctores en sus vidas personales. Duración:  780 min",
                "John Hensley, Roma Maffia, Julian McMahon, Joely Richardson, Dylan Walsh"
            )
        )
        agregarPelicula(
            Pelicula(
                "Nip Tuck : Temporada 3",
                getGenero("Series"),
                "Ã‰l Â¿o será ella? corta; ellos cosen. Ã‰l mutila; ellos curan. Los cirujanos plásticos Sean McNamara y Christian Troy juraron asistir a todas las víctimas del elusivo y misterioso verdugo serial apodado El Cortador. Pero reparar las fisuras en sus propias familias y profesión requerirá de mucho más que sus famosas habilidades tÃ©cnicas. Dylan Walsh y Julian McMahon regresan para una sensacional tercera temporada llena de erotismo, suspenso y desafíos mÃ©dicos que van desde un arriesgado transplante facial hasta una mujer de 294 kilos cuya piel se ha unido a su sofá. TambiÃ©n hay un nuevo mÃ©dico en el staff: el Dr. Quentin Costa, un experto en tango y probablemente un experto en diseccionar la práctica para sus propios fines. Además: Julia emprende una nueva profesión, el conflictivo Matt se relaciona con skinheads y el Cortador es Perdón, nuestros labios están sellados. Duración: 754 min",
                "Dylan Walsh, Julian McMahon, Joely Richardson, John Hensley, Kelly Carlson"
            )
        )
        agregarPelicula(
            Pelicula(
                "Nip Tuck : Temporada 4",
                getGenero("Series"),
                "Sexo. Seducción. Liposucción. Encuentra todo esto en la audaz Nip/Tuck, la serie multipremiada que es el filo del bisturí del entretenimiento y el disparador para debatir sobre quÃ© puede y no puede brindarle la cirugía estÃ©tica a la vida de un paciente. Dylan Walsh y Julian McMahon interpretan a dos grandes amigos y cirujanos plásticos cuya glamorosa práctica en South Beach es la puerta giratoria para los controvertidos temas de la cuarta temporada (incluyendo una aterradora historia sobre el tráfico de órganos) y las debilidades humanas (un ventrílocuo quiere verse igual a su muñeco). Las estrellas invitadas incluyen a Jacqueline Bisset, Larry Hagman, Alanis Morissette, Moâ€™Nique, Rosie Oâ€™Donnell, Brooke Shields y muchos más. Emociones, sorpresas, impactos y estrellas abundan en esta colección de 5 discos. Y todo lo que se necesita es una pequeña incisión. Duración: 724 min",
                "Dylan Walsh, Julian McMahon, Joely Richardson, Roma Maffia, John Hensley"
            )
        )
        agregarPelicula(
            Pelicula(
                "Swordfish",
                getGenero("Suspenso"),
                "Stanley (Hugh Jackman), sin haberlo pretendido y ya retirado de su afamada y extraoficial dedicación como uno de los mejores piratas informáticos del mundo, se verá envuelto en una sucia trama de espionaje y robo conducida por el frío y maquiavÃ©lico Gabriel (John Travolta), quien planea hacerse con miles de millones de dólares para financiar su peculiar lucha contra el terrorismo internacional.. Duración: 99 min",
                "John Travolta, Hugh Jackman, Halle Berry, Don Cheadle, Vinnie Jones, Sam Shepard"
            )
        )
        agregarPelicula(
            Pelicula(
                "Ricky Martin : Black & White tour",
                getGenero("Musical"),
                "El artista puertorriqueño, que recibió un premio tras cerrar con enorme Ã©xito su gira en el Madison Square Garden de Nueva York, publica el 6 de noviembre el DVD Black & White TourDVD, de más de 90 minutos, contiene 22 canciones que recogen todos los grandes Ã©xitos del puertorriqueño, incluyendo Tu recuerdo con la participación de la Mari de Chambao.",
                "Ricky Martin"
            )
        )
        agregarPelicula(
            Pelicula(
                "PI : Fe en el caos",
                getGenero("C.Ficcion"),
                "Max es un brillante matemático que está a punto de dar con el descubrimiento más importante de su vida: la decodificación del sistema numÃ©rico que rige el aparente caos del mercado bursátil. Pero primero ha de encontrar el valor del número PI. Mientras se acerca a la verdad, y afectado periódicamente por unas brutales jaquecas, Max es acosado por una agresiva firma de Wall Street y una secta judía que pretende descifrar los secretos ocultos tras los textos sagrados. Todos ansían apropiarse del inminente hallazgo de Max. Duración : 85 min",
                "Sean Gullette,  Mark Margolis,  Ben Shenkman,  Samia Shoaib,  Pamela Hart,  Ajay Naiou,  Joanne Gordon,  Stephen Pearlman"
            )
        )
        agregarPelicula(
            Pelicula(
                "Eric Claton : MTV Unplugged",
                getGenero("Musical"),
                "El espectacular concierto que dio Eric Clapton en los estudios MTV en 1993.Duracion : 70 minIncluye los temas :1. Signe / 2. Before You Accuse Me / 3. Hey Hey / 4. Tears In Heaven / 5. Lonely Stranger / 6. Nobody Knows You When Youre Down & Out / 7. Layla / 8. Running On Faith / 9. Walkin Blues / 10. Alberta / 11. San Francisco Bay Blues / 12. Malted Milk / 13. Old Love / 14. Rollin & Tumblin",
                "Eric Clapton"
            )
        )
        agregarPelicula(
            Pelicula(
                "Viven !",
                getGenero("Musical"),
                "Un avión uruguayo en el que un equipo de rugby chileno regresaba a casa se estrelló en los Andes el viernes 13 de octubre de 1972. Gran parte de la tripulación y algunos pasajeros murieron. Los supervivientes vivieron su calvario particular, esperando en condiciones infrahumanas un rescate que no llegaba. Duracion: 123 min",
                "Ethan Hawke,  Vincent Spano,  Bruce Ramsay,  John Malkovich,  Josh Hamilton,  John Haymes Newton,  David Kriegel,  Illeana Douglas"
            )
        )
        agregarPelicula(
            Pelicula(
                "El rey de la comedia",
                getGenero("Comedia"),
                "Rupert Pupkin, un aspirante a cómico, admira hasta rayar con la idolatría a Jerry Langford, un famoso showman cuyo programa de televisión ocupa los primeros puestos en el ranking de popularidad. Tras evitar que una histÃ©rica fan agreda a Jerry, Rupert decide secuestrarle y, bajo la amenaza de muerte, presiona al canal para que le permitan sustituir a Jerry durante una noche. Duración : 104 min",
                "Robert De Niro, Jerry Lewis, Diahnne Abbott,   Sandra Bernhard, Shelley Hack,   Ed Herlihy, Lou Brown"
            )
        )
        agregarPelicula(
            Pelicula(
                "Mars attack",
                getGenero("C.Ficcion"),
                "Parodia de los filmes de ciencia ficción de los años 50. Unos platillos volantes procedentes de Marte se encuentran sobre todas las capitales del mundo, y toda la humanidad contiene la respiración esperando ver cuáles son sus intenciones. Entre ellos está el presidente de los Estados Unidos, cuyo asesor científico le asegura que serán absolutamente pacíficos. Sin embargo sus asesores militares le aconsejan que aniquile a los marcianos antes de que sea demasiado tarde. Duración: 106 min",
                "Jack Nicholson,  Glenn Close,  Annette Bening,  Pierce Brosnan,  Danny DeVito,  Martin Short,  Michael J. Fox,  Sarah Jessica Parker,  Natalie Portman,  Tom Jones"
            )
        )
        agregarPelicula(
            Pelicula(
                "Nacido para matar",
                getGenero("Belica"),
                "En Vietnam el Viento no Sopla. Un grupo de soldados desarrolla personalidades deshumanizadas en su educación (entrenamiento) y esto lo demuestran en su viaje de deber (impuesto) en Vietnam. Duración: 116 min",
                "Matthew Modine, Vincent DOnofrio"
            )
        )
        agregarPelicula(
            Pelicula(
                "Enemigo al acecho",
                getGenero("Suspenso"),
                "Mientras los ejÃ©rcitos de Alemania y la Unión SoviÃ©tica luchan encarnizadamente y el mundo espera con ansiedad el desenlace de la batalla de Stalingrado, un francotirador ruso, Vassili Zaitsev, persevera en la empresa de eliminar a sus enemigos uno por uno. Modesto y reservado, Vassili es un hombre corriente que simplemente cumple con su deber con una destreza extraordinaria. Danilov -el oficial encargado de la propaganda soviÃ©tica- se da cuenta del enorme valor de este antiguo campesino, y convierte al sencillo pastor de los Urales en un hÃ©roe nacional. El valiente ejemplo de Vassili, convenientemente realzado por Danilov, anima a las tropas a seguir la lucha contra unas fuerzas abrumadoramente superiores. Sin embargo, Danilov mostrará pronto sus celos del hombre que ha creado cuando, en medio de la guerra, ambos se enamoran de Tania, una de las muchas intrÃ©pidas soldados que luchan al lado de los hombres. Los alemanes, por su parte, envían a su mejor francotirador, el mayor KÃ¶nig, para buscar y eliminar a Vassili, el misterioso enemigo que tambiÃ©n se ha hecho cÃ©lebre entre las tropas alemanas. Con una paciencia y habilidad exquisitas, cada uno acechará al otro librando una guerra en solitario mientras un sinfín de soldados caen muertos a su alrededor... Duracion: ",
                "Joseph Fiennes, Jude Law, Rachel Weisz, Ed Harris, Bob Hoskins"
            )
        )
        agregarPelicula(
            Pelicula(
                "Brigada explosiva : Contra los Ninjas",
                getGenero("Nacional"),
                "Sinopsis / Comentarios Los cuatro integrantes de una brigada explosiva, junto a su opulenta instructora, enfrentan a un grupo de luchadores ninjas. Segundo film de la saga de la Brigada explosiva, con otro director pero el mismo elenco, encabezado por Emilio Disi y Moria Casán ...  Duración : 75 min",
                "Emilio Disi, Alberto Fernández de Rosa, Gino Renni, Berugo Carámbula, Mario Castiglione, Moria Casan, Tincho Zabala"
            )
        )
        agregarPelicula(
            Pelicula(
                "Brigada explosiva",
                getGenero("Nacional"),
                "Emilio (Disi));, Benito ( Berugo Carámbula), Alberto (Fernández de Rosas) y Gino (Renni)son cuatro amigos de la infancia que hoy pertenecen a un mismo grupo policial, Brigada Z. Su primera misión es ocuparse del asalto a un banco... pero la falta de viveza y rapidez que los caracteriza hacen que sean ellos mismos quienes ayuden a los asaltantes a huir del lugar de los hechos!! El sargento a cargo decide enviarlos de regreso a la Academia de Policía, para que un instructor los ayude a desenvolverse en los próximos casos. Lo que los cuatro policías no saben es que este entrenador es... Margarita Zabaleta (Moria Casán), una despampanante instructora de karate, gimnasia y tiro al blanco, que los entrenará para enfrentar y tratar de capturar a Cicatriz (Norman Erlich), el más temido delincuente del momento. Con la ayuda de esta pulposa instructora formarán el quinteto policial más disparatado de Argentina!!! Duración : 95 min",
                "Emilio Disi, Alberto Fernández de Rosa, Gino Renni, Berugo Carámbula, Mario Castiglione, Moria Casan, Guillermo Francella, Norman Erlich"
            )
        )
        agregarPelicula(
            Pelicula(
                "Joaquin Sabina : Punto y seguido",
                getGenero("Musical"),
                "InÃ©dito en el que por primera vez se recogen las actuaciones de Sabina en TVE desde el comienzo de su carrera (en el programa Si yo fuera presidente de Tola) hasta sus presentaciones más recientes (como el Homenaje a Pablo Neruda): más de 40 actuaciones en televisión, la mayor parte de ellas en directo, que conforman un recorrido histórico sin nostalgias, absolutamente vital para entender la evolución de un artista cuya trayectoria musical sí que está completamente globalizada (del rock al pop, pasando por la rumba, la ranchera, la canción española, el tango e, incluso, el rap)",
                "Joaquin Sabina"
            )
        )
        agregarPelicula(
            Pelicula(
                "Cirque du Soleil : Midnight sun",
                getGenero("Familia"),
                "El 11 de Julio de 2004, más de 200.000 espectadores llenaron el centro de la ciudad de Montreal, Canadá para tomar parte en una celebración única, Â¡un evento especial de una sola noche! Con más de 250 artistas, Midnight Sun es una brillante combinación de música y circo la química perfecta de luz y sonido. Una experiencia visual inolvidable filmada en vivo Â¡en alta definición! Duracion: 97 min",
                "Cirque du Soleil"
            )
        )
        agregarPelicula(
            Pelicula(
                "Brigada explosiva : Los matamonstruos en la mansión del terror",
                getGenero("Nacional"),
                "No es un asalto a un banco ni una dura lucha contra los perversos ninjas ...esta vez los integrantes de la Brigada Z tendrán que enfrentarse a temibles fantasmas!!! Emilio (Disi));, Benito (Berugo Carámbula), Alberto (Fernández de Rosa) y Gino (Renni) son contratados para la aterradora misión de averiguar quÃ© sucede en una mansión donde, aparentemente, habitan fantasmas. Nuestro disparatado pero eficiente equipo, tratará de dilucidar el tema y llevar tranquilidad a la población, pero para eso deberán combatir contra estos misteriosos monstruos y ... hasta con un cocodrilo muy hambriento que aparece en medio de esta lucha fantasmagórica!!! LOS MATAMONSTRUOS EN LA MANSION DEL TERROR es una divertidísima comedia, con derroche de humor y disparate que te hará reír sin parar!! Duracion: 83 min",
                "Emilio Disi, Alberto Fernández de Rosa, Gino Renni, Berugo Carámbula, Onofre Lovero, Nathán Pinzón, Carlos Balá, Adriana Brodsky, Jorge Montejo, Esteban Mellino, Alejandro Lunadei"
            )
        )
        agregarPelicula(
            Pelicula(
                "Epitafios",
                getGenero("Series"),
                "Pack 5 dvds. SintiÃ©ndose responsable por la muerte de inocentes en una escuela local, Renzo Márquez (Julio Chávez) abandona la fuerza policial. Cinco años más tarde, un cuerpo aparece brutalmente mutilado. Todo parece indicar que el crimen se relaciona con los eventos que motivaron el retiro de Renzo. Una cruenta venganza apenas comienza. Su autor: un brillante asesino en serie que signa el destino de sus víctimas a travÃ©s de enigmáticos epitafios. Bajo el asedio del asesino, la psiquiatra Laura Santini (Paola Krum), tambiÃ©n involucrada en los sucesos de la escuela, se ve obligada a reunirse con Renzo. Juntos deberán enfrentar los fantasmas de su pasado e intentar develar cada uno de los misteriosos epitafios, sin imaginar que la llegada de la detective de homicidios Marina Segal (Cecilia Roth) dará un giro inesperado a los acontecimientos. Duración : 700 min",
                "Julio Chávez, Paola Krum, Cecilia Roth, Luis Luque, Antonio Birabent, Villanueva Cosse"
            )
        )
        agregarPelicula(
            Pelicula(
                "Pumas de bronce, corazón de oro",
                getGenero("Documental"),
                "Una gran idea de ESPN que, tras la histórica actuación de Los Pumas en el Mundial, editaron Pumas de bronce, corazón de oro, título del DVD de colección con las mejores e inÃ©ditas imágenes de lo sucedido en Francia durante la Copa del Mundo. Allí se podrán revivir todos los partidos de Los Pumas, los entrenamientos, la concentración y la campaña completa que marcó un antes y un despuÃ©s en el rugby argentino.",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Cirque du Soleil : Cortejo",
                getGenero("Familia"),
                "Una gran procesión y desfile festivo de la imaginación de un payaso. DÃ©jate transportar al mundo teatral de la comedia y espontaneidad, situados en un misterioso espacio entre el cielo y la tierra. Â¡CorteoTM es una celebración infinita y poÃ©tica en la cual la ilusión engaña a la realidad! Duración : 102 min",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Sabina & Serrat : Dos pajaros a tiro",
                getGenero("Musical"),
                "Grabado En El Placio De Los Deportes De Madrid 18/19/ Y 20/10/2007. Duración: 90 minTemas: 1 Concierto Grabado En El Palacio De Deportes (Madrid), 2 Ocupen Su Localidad/Hoy Puede Ser Un Gran Dia - Serrat/Sabin, 3 Algo Personal - Serrat, 4 Y Sin Embargo - Serrat / Sabina, 5 No Hago Otra Cosa Que Pensar En Ti - Serrat / Sabina, 6 Princesa - Sabina, 7 Contigo - Serrat / Sabina, 8 Contigo - Serrat / Sabina, 9 Tu Nombre Me Sabe A Yerba - Serrat / Sabina, 10 Tu Nombre Me Sabe A Yerba - Serrat / Sabina, 11 La Orilla De La Chimenea - Serrat, 12 Señora - Serrat / Sabina, 13 Aquellas Pequeñas Cosas/Ruido/El Muerto Vivo-Serrat/Sabina, 14 Fa Vint Anys Que Tinc Vint Anys - Serrat, 15 19 Dias Y 500 Noches - Sabina16 Penelope - Serrat / Sabina, 17 Mas De Cien Mentiras - Serrat / Sabina, 18 Cantares - Serrat / Sabina, 19 La Del Pirata Cojo - Serrat / Sabina, 20 Pastillas Para No Soñar - Serrat / Sabina, 21 Para La Libertad - Serrat / Sabina, 22 Contenido Extra: Documental - En El Nido De Los Pajaros",
                "Sabina, Serrat"
            )
        )
        agregarPelicula(
            Pelicula(
                "Los 4400 : Temporada 3",
                getGenero("Series"),
                "Pack 4 dvds. Los 4400 cuenta la historia de 4400 personas abducidas y desaparecidas durente varios años, tras su regreso a la Tierra. Han vuelto con una serie de poderes especiales y sin apenas darse cuenta cuenta comenzarán a exhibir habilidades que incluyen una fuerza sobrehumana, poderes curativos o la clarividencia. Duración : 560 min",
                "Brooke Nevin, Megalyn Echikunwoke, Peter Coyote, Jeffrey Combs, Bill Campbell, Mahershalalhashbaz Ali, Patrick Flueger"
            )
        )
        agregarPelicula(
            Pelicula(
                "Help ! La pelicula",
                getGenero("Musical"),
                "El argumento relata las peripecias de una secta de adoradores de la Diosa Kali, empeñados en llevarse un anillo de la mano de Ringo Starr que, a su vez, está empeñado en no abandonar el dedo que lo porta. Al estreno asistió la princesa Margarita (hermana de la Reina de Inglaterra), acompañada por su marido Lord Snowdon. La aglomeración de fans en el lugar del estreno desde doce horas antes, hizo necesaria la intervención de ambulancias para atender a numerosas chicas que sufrieron mareos y desmayos. El alboroto retrasó el Rolls-Royce de Lennon, que llegó veinte minutos tarde.",
                "The Beatles"
            )
        )
        agregarPelicula(
            Pelicula(
                "El principito",
                getGenero("Aventuras"),
                "Encantadora, tambiÃ©n, es la mágica historia acerca de un piloto varado en el desierto (Richard Kiley) y un errante niño de un lugar lejano. Juntos, los dos comparten un encuentro que entretiene, encanta y toca el corazón. Â¿Has adquirido, alguna vez, sabiduría de un zorro (Gene Wilder)? Â¿Te has preocupado por una rosa que era más especial que cualquier otra rosa? Â¿Has visitado un rey distante y remoto? Â¿Y presenciado la astuta danza de una serpiente (Bob Fosse)? El universo - no, la vida - es un lugar encantador, mucho más inclusive si es compartido con El Principito. Duración: 102 min",
                "Clive Revill, Steven Warner, Joss Ackland"
            )
        )
        agregarPelicula(
            Pelicula(
                "Ghandi",
                getGenero("Drama"),
                "Biográfico film acerca del líder pacifista hindú que logró, en el siglo XX, la independencia de la India y el final de la presencia colonial británica. Mahatma Gandhi es una de las figuras que mayor impacto han tenido en la creación del pensamiento político del siglo 20. Los libros de historia dan cuenta de la gran labor realizada por Gandhi en los años previos y posteriores a la Segunda Guerra Mundial. La película plantea la búsqueda de una aplicación práctica de los preceptos básicos y comunes a todas las religiones. La meta política de Gandhi comprendía la convivencia pacífica de todas las visiones religiosas de la Humanidad. En este sentido, es una meta que aún estamos lejos de alcanzar en nuestros días . Duración: 190",
                "Ben Kingsley, Richard Attenborough, Candice Bergen, John Briley, Edward Fox"
            )
        )
        agregarPelicula(
            Pelicula(
                "Mujeres al borde de un ataque de nervios",
                getGenero("Comedia"),
                "DespuÃ©s de varios años, Iván y Pepa, una pareja de actores de doblaje, deshace su relación, sin que Ã©l sepa que ella está embarazada. Mientras espera en vano noticias de Iván, Pepa intenta habituarse a su nueva situación. Su vida se complica aún más cuando llega a su nueva casa su amiga Candela, que huye de la policía porque Ã©sta ha detenido a su novio, un terrorista chiíta y teme que la involucren a ella en el asunto.epa decide conocer a la actual amante de Iván, con lo que sus nervios van en aumento. Por si fuera poco, en su vida entra tambiÃ©n por casualidad, Carlos, el hijo de su novio, que acude con la retrógrada novia, a ver el piso para alquilarlo. Todos se concentran en la casa, y el cruce de personajes da lugar a toda clase de situaciones divertidas. Duración: 90 min",
                "Carmen Maura, Antonio Banderas, Julieta Serrano, Rossy de Palma, Maria Barranco, Kity Manver, Guillermo Montesinos, Chus Lampreave"
            )
        )
        agregarPelicula(
            Pelicula(
                "Art Attack : Temporada 2 - Volumen 1",
                getGenero("Infantil-Peli"),
                "Como puedes renovar tu cuarto y hacerlo más divertido? Rui te enseña a hacer un móvil murciÃ©lago, un alocado marco de fotografías, y una pintura prehistórica . Para mantener tus cosas ordenadas Â¿QuÃ© mejor que un amigo carga lapiceras, un árbol porta lápices y una regla calendario? Aprenderás las tÃ©cnicas de dibujo, descubrirás nuevos colores y podrás crear puestas de sol con atractivos difuminados. Si eres fanático de las caricaturas, presta atención para dibujar divertidos animales.",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Art Attack : Temporada 2 - Volumen 2",
                getGenero("Infantil-Peli"),
                "Como puedes renovar tu cuarto y hacerlo más divertido? Rui te enseña a hacer un móvil murciÃ©lago, un alocado marco de fotografías, y una pintura prehistórica . Para mantener tus cosas ordenadas Â¿QuÃ© mejor que un amigo carga lapiceras, un árbol porta lápices y una regla calendario? Aprenderás las tÃ©cnicas de dibujo, descubrirás nuevos colores y podrás crear puestas de sol con atractivos difuminados. Si eres fanático de las caricaturas, presta atención para dibujar divertidos animales.",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Art Attack : Temporada 2 - Volumen 3",
                getGenero("Infantil-Peli"),
                "Como puedes renovar tu cuarto y hacerlo más divertido? Rui te enseña a hacer un móvil murciÃ©lago, un alocado marco de fotografías, y una pintura prehistórica . Para mantener tus cosas ordenadas Â¿QuÃ© mejor que un amigo carga lapiceras, un árbol porta lápices y una regla calendario? Aprenderás las tÃ©cnicas de dibujo, descubrirás nuevos colores y podrás crear puestas de sol con atractivos difuminados. Si eres fanático de las caricaturas, presta atención para dibujar divertidos animales.",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Art Attack : Temporada 2 - Volumen 4",
                getGenero("Infantil-Peli"),
                "Como puedes renovar tu cuarto y hacerlo más divertido? Rui te enseña a hacer un móvil murciÃ©lago, un alocado marco de fotografías, y una pintura prehistórica . Para mantener tus cosas ordenadas Â¿QuÃ© mejor que un amigo carga lapiceras, un árbol porta lápices y una regla calendario? Aprenderás las tÃ©cnicas de dibujo, descubrirás nuevos colores y podrás crear puestas de sol con atractivos difuminados. Si eres fanático de las caricaturas, presta atención para dibujar divertidos animales.",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Los padrinos magicos : Abra castatrofe",
                getGenero("Infantil-Anim"),
                "Timmy Turner tiene la posibilidad de tener un muffin mágico como premio por no mencionar nada de los Padrinos Mágicos por todo 1 año. Este muffin es bastante especial ya que puede pedir un deseo que viole las reglas, el muffin no es selecto ni sabroso pero si es poderoso. Que viva el muffin o.o eso dijo cosmo en la primera parte de la pelicula. Como si fuera esto suficiente Denzel Crocker hará de las suyas para tener este bizcocho y dominar el mundo. Duración : 80 min",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Los padrinos magicos : Terroríficos",
                getGenero("Infantil-Anim"),
                "Padrinos Mágicos TerroríficosÂ¡QuÃ© horror! Es Halloween, y Timmy está con un disfraz de momia hecho con papel de baño. Disgustado con su desafortunado atuendo, Timmy desea que el disfraz de todo mundo sea Â¡realmente terrorífico! Ahora cada uno de ellos será su disfraz, hasta los niños se han puesto el disfraz más popular de la temporada, el de Pumpkinator Â¡un monstruo gigante que intenta destruir el mundo!Dientes RelucientesEl malvado dentista Dr. Bender se roba los dientes de Chip Skylark, Â¡antes del rodaje de su próximo video musical!Â¿Cuál bruja es la bruja?Timmy viaja a travÃ©s del tiempo y un enloquecido cazador de brujas lo acusa de ser... una bruja.La Casa del Horror de Timmy... en 2DÂ¡Es la peor pesadilla de Timmy! Cuando la casa de Vicky es destruida, su familia entera se muda con los Turner. ",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Los padrinos magicos : Cazadores de canales",
                getGenero("Infantil-Anim"),
                "Timmy desea un control remoto que le permita viajar dentro de la televisión. Pero el control mágico cae en manos de Vicky! Les tomará a Timmy, Cosmo y Wanda una carrera a travÃ©s de los canales de televisión para evitar que Vicky se apodere de cada canal, y finalmente del mundo!",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Casper",
                getGenero("Infantil-Peli"),
                "El doctor James Harvey (Bill Pullman), terapeuta de fantasmas, y su hija Kat (Christina Ricci), llegan a la vieja y deteriorada Mansión Whipstaff. Su codiciosa dueña, Carrigan Crittendon (Cathy Moriarty), ha contratado al Dr. Harvey para exorcisar los espíritus de la casa: un amigable pero solitario fantasma llamado Casper, quien tan sólo busca un amigo, y a sus extravagantes tíos Látigo, Tufo y Gordi (El Trío Fantasma). Si el plan funciona, Carrigan y Dibs (Eric Idle), su socio, podrán hacerse con el legendario tesoro de la mansión. Mientras tanto, Casper ha encontrado en Kat a su alma gemela, pero el Trío Fantasma no tolerará la presencia de humanos en su casa. Duracion:  96 MIN",
                "Bill Pullman, Christina Ricci"
            )
        )
        agregarPelicula(
            Pelicula(
                "Ace Ventura : Un loco en Africa",
                getGenero("Comedia"),
                "El detective Ace Ventura, especializado en rescatar animales secuestrados, recibe el encargo de localizar y rescatar el delfin Copo de Nieve, mascota del equipo de fútbol de Miami, que ha sido secuestrado el día anterior a la gran final que debe disputar el equipo ... Duracion: 92 min",
                "Jim Carrey, Courteney Cox, Sean Young, Tone Loc,Dan Marino"
            )
        )
        agregarPelicula(
            Pelicula(
                "Rebelde sin causa",
                getGenero("Clasicos"),
                "Jimmy Stark es un joven confundido que no puede dejar de involucrarse en problemas. A causa de mudarse de pueblo en pueblo, nunca tuvo tiempo para establecer una relación seria con nadie. Cuando llega al nuevo pueblo, Jimmy comenzará a tener su primera relación con su compañera de estudio Judy, pero ella está de novia con el matón de la pequeña ciudad. Rebelde sin causa desarrolló junto a El salvaje (1954), la violencia y rebeldía juvenil en Estados Unidos.",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Dr.House : Temporada 1",
                getGenero("Series"),
                "Pack: 6 dvds. SumÃ©rgete en los misteriosos casos mÃ©dicos de House, la nueva serie de TV de más Ã©xito. Hugh Laurie protagoniza esta serie en el papel del sarcástico Dr. Gregory House, un mÃ©dico inconformista que no posee los modales típicos de un mÃ©dico de cabecera. Mientras que su comportamiento raya casi lo antisocial, el Dr. House afronta siempre el reto de resolver los casos clínicos más complicados, como si fueran puzzles, allí donde otros mÃ©dicos lo dejan. Junto a su equipo formado por escogidos y jóvenes expertos mÃ©dicos, House hará todo lo que estÃ© en su mano por ganar la carrera contrarreloj para resolver el caso.. ",
                "Hugh Laurie, Omar Epps, Lisa Edelstein, Robert Sean Leonard, Jennifer Morrison, Jesse Spencer"
            )
        )
        agregarPelicula(
            Pelicula(
                "Dr.House : Temporada 2",
                getGenero("Series"),
                "Pack: 6 dvds. SumÃ©rgete en los misteriosos casos mÃ©dicos de House, la nueva serie de TV de más Ã©xito. Hugh Laurie protagoniza esta serie en el papel del sarcástico Dr. Gregory House, un mÃ©dico inconformista que no posee los modales típicos de un mÃ©dico de cabecera. Mientras que su comportamiento raya casi lo antisocial, el Dr. House afronta siempre el reto de resolver los casos clínicos más complicados, como si fueran puzzles, allí donde otros mÃ©dicos lo dejan. Junto a su equipo formado por escogidos y jóvenes expertos mÃ©dicos, House hará todo lo que estÃ© en su mano por ganar la carrera contrarreloj para resolver el caso.. ",
                "Hugh Laurie, Omar Epps, Lisa Edelstein, Robert Sean Leonard, Jennifer Morrison, Jesse Spencer"
            )
        )
        agregarPelicula(
            Pelicula(
                "Carlito's Way : Atrapado por su pasado",
                getGenero("Accion"),
                "Al Pacino es un antiguo magnate de las drogas que lucha por escapar de su pasado de violencia y traición en esta portentosa cinta del elogio director Brian de Palma. Liberado de la cárcel gracias a un tecnicismo jurídico aducido por su abogado aficionado a la cocaína (Sean Penn), el que fuera cabecilla del hampa Carlito Brigante (Pacino) sorprende a sus colegas de malas artes al asegurar que va a enderezar su vida. Consigue un empleo del director en un ostentoso club nocturno de baja estafa, localiza a su antigua novia (Penelope Ann Miller) y reemprende la relación, prometiÃ©ndole que ha cambiado para siempre. pero la nueva vida soñada de Carlito, lealtades mal entendidas y los aún más terribles jóvenes matones de la nueva hornada, ansiosos por labrarse un nombre. Pese a las buenas intenciones de Carlito, lealtades mal entendidas y un anticuado código de honor le sumergirán en una lucha a vida o muerte contra las fuerzas implacables que se niegan a dejarle ir. En una nueva colaboración del protagonista y el director de El precio del poder, Atrapado por su pasado es una película impactante y apasionada. Duración : 142 min",
                "Al Pacino, Sean Penn, Jorge Porcel, Penelope Ann Miller, John Leguizamo"
            )
        )
        agregarPelicula(
            Pelicula(
                "Robocop 1",
                getGenero("Accion"),
                " La mega corporación Omni Consumer Products continúa empeñada en crear su nuevo proyecto de ciudad, Delta City, para sustituir a la degradada Detroit. Por desgracia los habitantes de la zona no tienen la intención de abandonar sus hogares. Para ello la OCP pretende desalojarlos por medio de un ejÃ©rcito de mercenarios. Se inicia una guerrilla callejera y Robocop debe decidir de quÃ© lado está. Duración : 104",
                "Robert Burke, Nancy Allen, Rip Torn, Remy Ryan, John Castle, Jill Hennessy, CCH Pounder"
            )
        )
        agregarPelicula(
            Pelicula(
                "Robocop 2",
                getGenero("Accion"),
                "Bajo un cielo carente de ozono y en una Ã©poca futurista, la policía de Detroit está en huelga, y las peligrosas bandas criminales dominan a la población. La adicción al Nuke, una poderosa y nueva droga es el pan de cada día. La megacorporación OCP -Omni Consumer Products-, en un intento de reforzar su implacable imperio, pretende apoderarse de la ciudad, para su propia explotación privada. El director de la OCP solicita a la doctora Faxx que reprograme a RoboCop, dejándolo incapaz de luchar contra la delincuencia. Duración: 110 min",
                "Peter Weller, Nancy Allen, Daniel O Herlihy, Belinda Bauer, Daniel O Herlihy, Tom Noonan"
            )
        )
        agregarPelicula(
            Pelicula(
                "Robocop 3",
                getGenero("Accion"),
                "La mega corporación Omni Consumer Products continúa empeñada en crear su nuevo proyecto de ciudad, Delta City, para sustituir a la degradada Detroit. Por desgracia los habitantes de la zona no tienen la intención de abandonar sus hogares. Para ello la OCP pretende desalojarlos por medio de un ejÃ©rcito de mercenarios. Se inicia una guerrilla callejera y Robocop debe decidir de quÃ© lado está. Duración: 104 min",
                "Robert Burke, Nancy Allen, Rip Torn, Remy Ryan, John Castle, Jill Hennessy, CCH Pounder"
            )
        )
        agregarPelicula(
            Pelicula(
                "Conduciendo a Miss Daisy",
                getGenero("Drama"),
                "Miss Daisy, una profesora jubilada y adinerada, decide comprarse un coche. Su hijo, temiendo un posible caos circulatorio, contrata a Hoke, un chófer negro (Morgan Freeman) que poco a poco se convertirá en el mejor amigo de la dama. Juntos descubren que aun siendo dos personas muy diferentes, tienen mucho en común. Duración: 99 min",
                "Jessica Tandy, Morgan Freeman, Dan Aykroyd, Esther Rolle, Patti LuPone"
            )
        )
        agregarPelicula(
            Pelicula(
                "Dias de gloria",
                getGenero("Drama"),
                "Una odisea antibÃ©lica situada en la guerra civil americana, en la cual un grupo de soldados negros son utilizados para una misión, la cual será una muerte segura. Basada en hechos reales, vale por su dramatismo, vigor y actuaciones mayúsculas de Denzel Washington y Morgan Freeman.",
                "Denzel Washington, Matthew Broderick, Morgan Freeman, Cary Elwes"
            )
        )
        agregarPelicula(
            Pelicula(
                "Cuando Harry conoció a Sally",
                getGenero("C.Romantica"),
                "Cuando Harry conoció a Sally reflexiona sobre las relaciones humanas, la atracción entre hombre y mujeres, la sexualidad y la amistad. Sally comparte con Harry un largo viaje en auto hacia Nueva York durante 1977. Ambos tienen planes de iniciar una carrera profesional, ella en el periodismo y Ã©l como consultor político. Tardan trece años en darse cuenta que se aman y necesitan estar juntos. En el medio, mantienen una amistad con encuentros y desencuentros, donde tratan de convencerse de que no hay nada más que una relación amigable entre ellos. Billy Crystal y Meg Ryan forman una pareja muy carismática y cada uno es insuperable en su papel. Este film consagró a Meg Ryan y la convirtió en la gran protagonista de comedias románticas.Cuando Harry conoció a Sally que tiene como escenario a la ciudad de Nueva York, retoma el estilo iniciado por Woody Allen en Annie Hall. Dos extraños amantes (1977) o Manhattan (1979). Los diálogos son divertidos y mordaces y el relato rescata los pequeños momentos cotidianos que se sitúan al borde del absurdo. Duracion : 100 min",
                "Meg Ryan, Carrie Fisher, Billy Crystal, Steven Ford, Bruno Kirby"
            )
        )
        agregarPelicula(
            Pelicula(
                "Day Light",
                getGenero("Accion"),
                "Un camión portador de sustancias peligrosas explota en el túnel Holland que une la isla de Manhattan con el continente. Las autoridades creen que hay supervivientes atrapados en el túnel pero no saben cómo sacarlos de allí. La ruptura del túnel provoca su progresiva inundación con las aguas del río Hudson, con lo que el tiempo para intentar salvarlos es limitado. Kit Matura, el anterior director del Servicio de Emergencia MÃ©dica, es el único que conoce los detalles de construcción del túnel lo bastante a fondo como para intentar el rescate.",
                "Sylvester Stallone,  Amy Brenneman,  Claire Bloom,  Viggo Mortensen"
            )
        )
        agregarPelicula(
            Pelicula(
                "Hot shots 1",
                getGenero("Comedia"),
                "Topper Harley es un piloto de Ã©lite, un Top Gun de las fuerzas aÃ©reas norteamericanas, que tiene que convivir con el legado de su padre y que mantiene una dura rivalidad con otro piloto, Kent Gregory. Un día se le encomienda una dura y peligrosa misión: destruir una planta nuclear de Sadam Hussein. (85 min)",
                "Charlie Sheen,  Valeria Golino,  Cary Elwes,  Lloyd Bridges,  Kevin Dunn,  Jon Cryer,  William OÂ´Leary,  Efrem Zimbalist Jr."
            )
        )
        agregarPelicula(
            Pelicula(
                "Hot shots 2",
                getGenero("Comedia"),
                "Topper Harley es hallado trabajando de chapuzas en un monasterio budista. La CIA le necesita para encabezar una misión de rescate en Irak: rescatar a la misión de rescate que tenía que rescatar a unos cautivos de la operación tormenta del desierto. Tug Benson, el presidente de los EE.UU., tendrá un importante papel en todo el asunto.",
                "Charlie Sheen,  Lloyd Bridges,  Valeria Golino,  Brenda Bakke,  Richard Crenna,  Miguel Ferrer,  Rowan Atkinson"
            )
        )
        agregarPelicula(
            Pelicula(
                "Heat (Fuego contra Fuego)",
                getGenero("Suspenso"),
                "Al Pacino y Robert de Niro se enfrentan cara a cara en Heat. Escrita y dirigida por Michael Mann, Heat contine algunas de las escenas más espectaculares ofrecidas por el cine, con un estilo visual muy definido y un espectacular robo a mano armada que la crítica de USA Today calificó como la escena de acción más impactante de los últimos años. Heat tambiÃ©n ofrece el reparto de actores más impresionante de los últimos tiempos, entre los que destacan Val Kilmer, Jon Voight, Tom Sizemore y Asheley Judd. Al Pacino y Robert De Niro juntos en un relato policial muy violento. (170 min)",
                "Robert De Niro, Al Pacino, Val Kilmer, Judie Nelson,"
            )
        )
        agregarPelicula(
            Pelicula(
                "James Bond 007 : Al servicio de su majestad",
                getGenero("Accion"),
                "James Bond, agente del servicio secreto británico, está buscando a Ernest Stavros Blofeld, jefe de Spectra, cuyas brillantes y originales actividades plantean continuamente problemas para la paz del mundo. La búsqueda de Bond le lleva a una joven que se intentaba suicidar en el ocÃ©ano. (140 min)",
                "George Lazenby, Diana Rigg, Telly Savalas, Gabriele Ferzetti, Ilse Steppat"
            )
        )
        agregarPelicula(
            Pelicula(
                "James Bond 007 : De Rusia con amor",
                getGenero("Accion"),
                "Una máquina que descifra los más complicados sistemas de comunicación es el objetivo del agente británico James Bond, que esta vez tiene que viajar hasta Estambul para desarrollar su misión. (115 min)",
                "Sean Connery, Daniela Bianchi, Pedro Armendáriz, Lotte Lenya, Robert Shaw"
            )
        )
        agregarPelicula(
            Pelicula(
                "James Bond 007 : Dedos de oro",
                getGenero("Accion"),
                "El poderoso villano Auric Goldfinger planea explosionar una minibomba atómica en el depósito de oro de Fort Knox y contaminar las reservas de los Estados Unidos, permitiÃ©ndole Ã©sto aumentar espectacularmente el valor de sus propias reservas y convertirse en el hombre más poderoso de la Tierra. Una vez descubiertos sus planes, Bond se enfrenta a Goldfinger y a sus peligrosísimos sicarios, con la ayuda de la secretaria personal de aquÃ©l, Jill Masterson, que acabará pagando muy cara su infidelidad: un fatídico baño de oro. (109 min)",
                "Sean Connery, Honor Blackman, Gert FrÃ¶be, Shirley Eaton, Tania Mallet"
            )
        )
        agregarPelicula(
            Pelicula(
                "James Bond 007 : El hombre del revolver de oro",
                getGenero("Accion"),
                "James Bond ha sido marcado para morir y necesitará todo su instinto letal y encanto seductor para sobrevivir en esta aventura cargada de acción. Roger Moore vuelve como el agente 007 y se enfrenta en un juego mortal de cacería con el asesino Francisco Scaramanga (Christopher Lee). Presentando una persecución automovilística salvaje a travÃ©s de Bangkok y el magnífico enfrentamiento con una escuela entera de artes marciales, El hombre del revólver de oro nos brinda una emoción sin límite. (126 min)",
                "Roger Moore, Christopher Lee, Britt Ekland, Maud Adams, HervÃ© Villechaize"
            )
        )
        agregarPelicula(
            Pelicula(
                "James Bond 007 : El satanico Dr.No",
                getGenero("Accion"),
                "Su nombre es Bond, James Bond. Y aquí, en este impresionante y explosivo film que da inicio a la gran saga, el inmortal súper agente creado por Ian Fleming nos entrega su aventura más espectacular. Sean Connery protagoniza al refinado, atractivo pero letal agente 007 y se enfrentará al misterioso Dr. No, un brillante científico empeñado en destruir el programa espacial de los Estados Unidos. (109)",
                "Sean Connery, Ursula Andress, Joseph Wiseman, Jack Lord, Bernard Lee"
            )
        )
        agregarPelicula(
            Pelicula(
                "James Bond 007 : En la mira de los asesinos",
                getGenero("Accion"),
                "Un chip interceptado a los soviÃ©ticos resulta ser idÃ©ntico al prototipo británico capaz de soportar las radiones electromagnÃ©ticas de una explosión nuclear. Los británicos sospechan que el industrial Max Zoin ha filtrado información a los rusos. Cuando James Bond es enviado a investigar, descubre que Zorin está almacenando chips y, misteriosamente, perforando cerca de la falla de San AndrÃ©s. (131 min)",
                "Roger Moore, Christopher Walken, Tanya Roberts, Grace Jones, Patrick Macnee"
            )
        )
        agregarPelicula(
            Pelicula(
                "James Bond 007 : La espia que me amo",
                getGenero("Accion"),
                "Nadie lo hace mejor que Bond, y Ã©l lo prueba nuevamente en esta adventura explosivamente entretenida que lo lleva desde las pirámides de Egipto hasta el fondo del ocÃ©ano y a una persecución en esquí desafiando la gravedad por la cima de la montaña. Roger Moore le transmite un inimitable estilo al agente 007 cuando une fuerzas con la agente rusa, Anya Amasova (Barbara Bach) para evitar que el megalómano Stromberg (Curt Jurgens) lleve a cabo un plan terrorífico para dominar el mundo. (125 min)",
                "Roger Moore, Barbara Bach, Curd JÃ¼rgens, Richard Kiel, Caroline Munro"
            )
        )
        agregarPelicula(
            Pelicula(
                "James Bond 007 : Licencia para matar",
                getGenero("Accion"),
                "Poco tiempo despuÃ©s de haber realizado una importante operación antidrogas,el agente de la CIA, FÃ©lix Leiter se casa. Sin embargo, Leiter no contaba con la fuga del capo que detuvo en la citada operación, quien mata a su esposa y le deja gravemente herido. Leiter es un viejo amigo de James Bond, quien desea vengar a su amigo. Pero el Sr. M, jefe de 007, le asigna una misión diferente, lo que hace que el superagente deserte del Servicio Secreto Británico para embarcarse en una vendetta personal. (133 min)",
                "Timothy Dalton, Carey Lowell, Robert Davi, Talisa Soto, Anthony Zerbe"
            )
        )
        agregarPelicula(
            Pelicula(
                "James Bond 007 : Los diamantes son internos",
                getGenero("Accion"),
                "Sir Donald Munger, presidente del Sindicato Internacional del Diamante, da la voz de alarma al constatar que a lo largo dos años ha ido desapareciendo del mercado mundial una gran cantidad de diamantes. James Bond, suplantando la identidad de un famoso traficante, inicia las investigaciones. El agente secreto logra entrar en contacto con Burt Saxby, director de un casino por cuenta de Willard Whyte, un misterioso millonario de quien se sospecha que se propone desestabilizar el mercado diamantífero. Bond descubre que, entre otros negocios, Whyte financia un centro de investigaciones espaciales, tras el que está Blofeld, quien, con los diamantes que ha ido atesorando, ha construido un gigantesco y amenazador rayo láser que ha puesto en órbita alrededor de la Tierra, y con el que pretende lograr su terrible objetivo. (120 min)",
                "Sean Connery, Jill St. John, Charles Gray, Lana Wood, Jimmy Dean"
            )
        )
        agregarPelicula(
            Pelicula(
                "James Bond 007 : Moonraker",
                getGenero("Accion"),
                "Esta vez James Bond debe enfrentarse a un millonario que está preparando masacrar a la población mundial disparando desde el espacio una serie de bombas cargadas de un virus mortal. Para ello, 007 viajará de Francia a Venecia, y de allí a Brasil para acabar en una base espacial en una explosiva batalla final. (126 min)",
                "Roger Moore, Lois Chiles, Corinee Clery, Richard Kiel, Michael Lonsdale"
            )
        )
        agregarPelicula(
            Pelicula(
                "James Bond 007 : Octopussy",
                getGenero("Accion"),
                "El agente 007 tiene que reemplazar a su colega 009, muerto en acto de servicio mientras vigilaba al príncipe afgano Khan y a su bella compañera Octopussy. James Bond averigua que ambos planean introducirse en la base de las Fuerzas Aereas Estadounidenses en Feldstadt para hacer explotar una bomba nuclear. (131 min)",
                "Roger Moore, Maud Adams, Louis Jourdan, Kristina Wayborn, Kabir Bedi"
            )
        )
        agregarPelicula(
            Pelicula(
                "James Bond 007 : Operación Trueno",
                getGenero("Accion"),
                "Un grupo terrorista se ha hecho con dos bombas atómicas. James Bond tendrá que localizarlas antes de que expire el plazo que los terroristas han dado para destruir dos ciudades. (130 min)",
                "Sean Connery, Claudine Auger, Adolfo Celi, Luciana Paluzzi, Rik Van Nutter"
            )
        )
        agregarPelicula(
            Pelicula(
                "James Bond 007 : Solo para sus ojos",
                getGenero("Accion"),
                "El agente 007 tiene como misión recuperar un artefacto capaz de controlar el destino de sendas cabezas nucleares. No será el único con esa intención, y su camino será muy accidentado, topándose con una hermosa joven que intenta vengar la muerte de su padre. El asesino tiene en su poder la máquina tan deseada (127 min)",
                "Roger Moore, Carole Bouquet, Topol Lynn, Holly Johnson, Julian Glover"
            )
        )
        agregarPelicula(
            Pelicula(
                "James Bond 007 : Solo se vive dos veces",
                getGenero("Accion"),
                "Un poderoso megalómano llamado Blofeld planea iniciar la III Guerra Mundial secuestrando cohetes espaciales de las potencias mundiales. Así provoca que EEUU y Rusia se acusen mutuamente de haberse entrometido en sus programas espaciales. Pero la Inteligencia Británica intenta evitar esta guerra nuclear enviando al agente secreto James Bond a Oriente, donde se pierden las pistas que pueden llevar hasta blofeld. (117 min)",
                "Sean Connery, Akiko Wakabayashi, Mie Hama, Tetsuro Tamba, Teru Shimada"
            )
        )
        agregarPelicula(
            Pelicula(
                "James Bond 007 : vive y deja morir",
                getGenero("Accion"),
                "Tres sucesos acaecidos en tres ciudades muy alejadas convulsionan al departamento de M. En Nueva York, el representante británico, que asiste a una conferencia internacional de alto nivel, muere bruscamente mientras utiliza los auriculares de traducción simultánea; en Nueva Orleans, un agente que vigila un antro donde se sospecha que se trafica con drogas, ve desfilar un cortejo fúnebre, y se da cuenta demasiado tarde que el ocupante del ataúd va a ser Ã©l mismo; en San Monique, otro agente fallece al ser mordido por una serpiente venenosa en el curso de una ceremonia de vudú. M ordena a James Bond investigar la conexión entre la muerte de sus tres agentes. En Harlem, 007 es raptado por orden de Mr. Big; en San Monique, descubre que la plantación del doctor Kananga está dedicada al cultivo clandestino de amapolas, y en Nueva Orleans, descubre que Kananga y Mr. Big son la misma persona. (121 min)",
                "Roger Moore, Yaphet Kotto, Jane Seymour, Clifton James, Julius Harris"
            )
        )
        agregarPelicula(
            Pelicula(
                "James Bond 007 : Su nombre es peligro",
                getGenero("Accion"),
                "Armado con un instinto aguzado y una licencia para matar, James Bond combate con comerciantes diabólicos de armas dispuestos a dominar el mundo en esta emocionante y dinámica aventura. Timothy Dalton muestra energía, humor y astucia despiadada en su debut como el agente 007. (131 min)",
                "Timothy Dalton, Maryam d'Abo, Jeroen KrabbÃ©, Joe Don Baker, John Rhys Davies"
            )
        )
        agregarPelicula(
            Pelicula(
                "El ciudadano Kane",
                getGenero("Clasicos"),
                "En Xanadu, su paraíso, acaba de fallecer Charles Foster Kane. Un noticiario da cuenta de la personalidad del multimillonario, de su imperio económico, su cadena de diarios, emisoras y fábricas. De sus ambiciones políticas, finalmente frustradas. De su vida sentimental con Emily Norton, sobrina del presidente de los Estados Unidos con la que se casó. Y de su idilio con una atractiva cantante, para quien construyó un teatro de la ópera. Todo se sabe de Kane menos una cosa Â¿quÃ© significa Rosebud, la palabra que pronunció justo antes de morir? Un periodista inicia una investigación al respecto, entrevistado a todas aquellas personas allegadas al fallecido. (119 min)",
                "Orson Welles,  Joseph Cotten,  Everett Sloane,  George Coulouris,  Dorothy Comingore,  Ray Collins,  Agnes Moorehead,  Paul Stewart"
            )
        )
        agregarPelicula(
            Pelicula(
                "Los Sopranos - Temporada 1",
                getGenero("Series"),
                "Tony Soprano (James Gandolfini) es el capo de una familia mafiosa de Nueva Jersey, que a pesar de su dureza sufre de ataques crónicos de ansiedad. Pero si el acentazo de Jersey con el que Tony y su grupo impar de secuaces y sicarios hablan en la serie resulta ser típico, por contraste los argumentos de la serie son sofisticados y multifacÃ©ticos. Estos son hombres de bajo perfil pero al mismo tiempo son personajes completos, tienen sus propios sueños, temores e idiosincrasias . La diferencia más obvia entre esta serie y el resto de las historias de mafias es que la verdadera familia del capo tiene un papel tan importante en la historia como el de su otra familia. Las hijas se le rebelan. Los hijos sufren de los trastornos típicos de la adolescencia. Todos los dolores de cabeza que se presentan en una familia común y corriente. Aparte de eso, las mujeres de la familia Soprano son algunos de los personajes más interesantes e importantes. La madre de Tony, Livia Soprano (Nancy Marchand) es increíblemente real y está estupenda. Carmela Soprano (Edie Falco) es una católica temerosa de Dios con una voluntad de hierro, que a pesar de darse cuenta de su marido no hace nada al respecto y que lo único que quiere es mantener a su familia unida de la mejor manera que conoce. ",
                "James Gandolfini, Lorraine Bracco, Dominic Chianese, Edie Falco, Robert Iler, Michael Imperioli, Nancy Marchand, Vincent Pastore, Tony Sirico"
            )
        )
        agregarPelicula(
            Pelicula(
                "Los Sopranos - Temporada 2",
                getGenero("Series"),
                "En la segunda estación, la hermana excÃ©ntrica del Tony Soprano Janice aparece sobre su umbral. El cañón flojo Jackie Aprile es liberado de la prisión y viene buscando un pedazo de acción. Alguien quien se dirige a los gobiernos federales va a terminar por dormir con los pescados. Esto es la estación que tiene que ser ocupado ser creído.",
                "James Gandolfini, Katalin Pota, Lorraine Bracco, Edie Falco, Dominic Chianese, Robert Iler, Michael Imperioli, Nancy Marchand, Vincent Pastore"
            )
        )
        agregarPelicula(
            Pelicula(
                "Los Sopranos - Temporada 3",
                getGenero("Series"),
                "Algunas familias de los suburbios tienen dos autos. Algunas tienen dos casas. Pero Tony Soprano tiene dos familias. Esto podría explicar porquÃ© el FBI está haciendo lo posible por intervenir el telÃ©fono de su casa. PorquÃ© el hijo de su difunto y querido amigo Jackie Aprile le está causando tantos problemas. PorquÃ© un ama de llaves rusa está buscando su pierna ortopÃ©dica. PorquÃ© su hijo está destruyendo la escuela y a su hija le están rompiendo el corazón. PorquÃ© su mujer Carmela está consultando tanto a un psiquiatra como confesándose con un cura. Y tambiÃ©n porquÃ© Tony Soprano sigue visitando a la Dra. Melfi debido a sus ataques de pánico. No es fácil liderar la mafia en Nueva Jersey. Pero es la mafia quien le da de comer a las dos familias de Tony Soprano. ",
                "James Gandolfini, Edie Falco, Jamie Lynn DiScala, Robert Iler, Nancy Marchand, Aida Turturro, Lorraine Bracco, Michael Imperioli, Tony Sirico, Steve Van Zandt, Vincent Pastore, Dominic Chianese, Drea de Matteo, John Ventimiglia, Kathrine Narducci"
            )
        )
        agregarPelicula(
            Pelicula(
                "Los Sopranos - Temporada 4",
                getGenero("Series"),
                "Son tiempos difíciles en Jersey para Tony Soprano. La floja situación económica no ha sido buena para el negocio familiar. Su esposa Carmela está tratando de conseguir más seguridad financiera y algunos antiguos lugartenientes no están conformes con las recientes decisiones tomadas por Tony. Un jefe rival quiere una porción más grande del pastel suburbano. Incluso, un Soprano irá a juicio por primera vez en dÃ©cadas. Parece que al menos un hijo ha perdido interÃ©s en la universidad. Y los patos no volverán pronto. ",
                "James Gandolfini, Lorraine Bracco, Edie Falco, Michael Imperioli, Dominic Chianese, Steve Van Zandt"
            )
        )
        agregarPelicula(
            Pelicula(
                "Los Sopranos - Temporada 5",
                getGenero("Series"),
                "La ira del infierno es menos que la furia de Los Soprano! Su separación no parece llevarlo por el buen camino. La novia de su sobrino se ha convertido en su problema para Ã©l. Su primo, que se encuentra en libertad condicional, le produce malas vibraciones. Su rival en los negocios busca una recompensa. Su psicólogo no se cree lo de los dos Tonys. Esto es suficiente para llevar a cualquier jefe de la mafia al borde de la locura..",
                "Lorraine Bracco, Steve Buscemi, Dominic Chianese, Edie Falco, James Gandolfini"
            )
        )
        agregarPelicula(
            Pelicula(
                "Anastasia",
                getGenero("Infantil/Anim"),
                "En 1916 en Rusia, el malvado Rasputín planea asesinar al Zar Nicolás Romanov y a la familia real. La única sobreviviente del fatal accidente es la pequeña princesa Anastasia y su abuela, la emperatriz Marie. Varios años despuÃ©s, Anastasia ha perdido todos los recuerdos de su infancia y se hace llamar Anya. Mientras tanto dos hombres que buscan ganar la recompensada ofrecida por la emperatriz para quien encuentre a su nieta, conocen a Anya y la convencen de que ella es la princesa perdida. Primer film animado de la 20th Century Fox y el primero hecho en CinemaScope desde La bella durmiente (1959) de Disney. Basada en la obra de Marcelle Maurette. Nominado a dos premios Oscar.",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Soda Stereo : MTV Plugged",
                getGenero("Musical"),
                "El elÃ¨ctrico que dio la banda en los estudios de MTVTemas : 1 En la ciudad de la furia2 Un misil en mi placard3 Pasos4 Entre caníbales5 TÃ© para tres6 Ãngel elÃ©ctrico7 Ella usó mi cabeza como un revólver8 Sonoman (banda de sonido)9 Planeador10 Coral11 Superstar ",
                "Soda Stereo"
            )
        )
        agregarPelicula(
            Pelicula(
                "El violin rojo",
                getGenero("Drama"),
                "En un taller de la Italia del siglo XVII, un maestro artesanal crea su obra definitiva, un violín perfecto y barnizado en rojo, para su hijo a punto de nacer. A partir de ese momento, el instrumento viaja de mano en mano desde Europa a Canadá, pasando por China, hasta la Ã©poca actual.",
                "Samuel L. Jackson, Greta Scacchi, Jason Flemyng, Carlo Cecchi, Irene Grazioli"
            )
        )
        agregarPelicula(
            Pelicula(
                "Beetlejuice",
                getGenero("Comedia"),
                "Una pareja de fantasmas jóvenes, muertos recientemente, contrata los servicios del delirante Beetlejuice para expulsar a los nuevos moradores de su casa. Pero la solución termina siendo peor que el problema.",
                "Michael Keaton, Tim Burton, Alec Baldwin, Geena Davis, Jeffrey Jones, Catherine O Hara, Winona Ryder"
            )
        )
        agregarPelicula(
            Pelicula(
                "Gustavo Cerati : 11 episodios sinfonicos",
                getGenero("Musical"),
                "Verdaderos himnos del rock nacional como canción Animal, Persiana Americana, o Signos, transformados a un estilo clásico-sinfónico, generan asombro, a la vez, deleite auditivo. Â¡QuÃ© diferente es escuchar Corazón Delator con violines, oboes y violoncellos. Â¡QuÃ© potencia adquieren los hits! Ellos, entre otros, son los pilares en los que se sustenta 11 Episodios Sinfónicos, una joya de la arquitectura musical producida por el ex líder de Soda Stereo . Con temas propios y compartidos, el cantautor está al frente de una nueva producción como solista, 11 Episodios Sinfónicos, cuenta con más de 40 músicos en vivo, que Impresiona y sorprende su perfección sonora . Nada que envidiarle a la puntillosa Orquesta Sinfónica de Viena o, sin ir tan lejos, a la eficaz Camerata Bariloche vernácula.",
                "Gustavo Cerati"
            )
        )
        agregarPelicula(
            Pelicula(
                "Matilda",
                getGenero("Infantil/Peli"),
                "Matilda es una niña con una inteligencia excepcional y enormes ganas de aprender. Sin embargo, sus padres están tan absortos en sus insustanciales vidas que no se dan cuenta de ello. Un día deciden enviar a Matilda a Crunchell Hall, una escuela con aspecto de prisión cuya directora es un monstruo despiadado. Será su encantadora profesora, Miss Honey quien descubrirá las mágicas cualidades de Matilda y juntas vencerán increíbles peripecias.",
                "Mara Wilson, Danny De Vito, Rhea Perlman, Embeth Davidtz, Pam Ferris, Paul Reubens"
            )
        )
        agregarPelicula(
            Pelicula(
                "Los sospechosos de siempre",
                getGenero("Suspenso"),
                "En un mundo donde nada es lo que parece, tienes que mirar mas allá de... Los Sospechosos de Siempre. Los delincuentes mas duros del mundo, no le tienen miedo a nada... excepto Keyser Soze. Veintisiete cadáveres, están flotando en el Puerto de Long Beach, y un testigo agonizante profiere un nombre... Keyser Soze. Ahora el agente americano Daniel Kujan (CHAZZ PALMINTERI), tiene que encontrar al asesino, pero solo un tipo tiene las pistas, Verbal Kint (KEVIN SPACEY), un ladrón nuevo y el sobreviviente del baño de sangre del Puerto de Cayo Largo. Verbal, cuenta a Kujan la increible historia de cinco bandidos que vinieron a tirar por la borda el trabajo de sus vidas, con U\$S 91 millones en el balance y Keyser Soze a sus espaldas: Keaton (GABRIEL BYRNE), McManus (STEPHEN BALDWIN), Hockney (KEVIN POLLAK), Fenster (BENICIO DEL TORO), y el propio Kint. De algún modo el trabajo de una vida acaba en una matanza y un misterio: ¿Quien es Keyser Soze?",
                "Stephen baldwin, Gabriel Byrne, Kevin Spacey"
            )
        )
        agregarPelicula(
            Pelicula(
                "Hechizo del tiempo",
                getGenero("Comedia"),
                "Un reportero de televisión a regañadientes va a cubrir una historia a un pequeño pueblo llamado Punxsutawney, Pennsylvania. Cuando descubre que ha sido hechizado y condenado a vivir el mismo día una y otra vez utilizando esto para aprovecharse de la gente a su conveniencia. Sin duda una cinta que te dejará con una sonrisa en los labios.",
                "Bill Murray, Andie Mac Dowell, Chris Elliot "
            )
        )
        agregarPelicula(
            Pelicula(
                "El espinazo del diablo",
                getGenero("Suspenso"),
                "España, finales de los años 30. Carlos, un niño de 12 años, es abandonado por su tutor en el orfanato de Santa Lucía, una construcción imponente aislada en medio de un páramos desolado. El colegio esconde, a lo largo de sus lúgubres pasillos, una serie de relaciones viciadas entre los adultos que viven allí, principalmente entre elcuarteto protagonista compuesto por Carmen (la directora), Casares (un maduro profesor), Jacinto (el agresivo portero) y Conchita (una joven maestra). Pronto surgirá una violenta rivalidad entre Carlos y Jaime, un adolescente de carácter tortuoso y hostil que ejerce de líder natural para el resto de los alumnos. Inmerso en este universo cerrado cuyas normas desconoce y rodeado de muchachos abandonados o sin familia, Carlos irá vislumbrando poco a poco el trágico secreto que permanece en sus muros.",
                "Marisa Paredes, Eduardo Noriega, Federico Luppi, Irene Visedo"
            )
        )
        agregarPelicula(
            Pelicula(
                "El coleccionista de huesos",
                getGenero("Suspenso"),
                "Un diabólico asesino anda suelto por las calles de Manhattan. A Ã©l se enfrentarán Lincoln Rhyme, un policía forense que, despuÃ©s de un terrible accidente, queda tatraplÃ©jico y con pocas ganas de vivir, y Amelia Donaghy, una joven policía que siente que el trabajo que realiza en las calles no tiene ningún sentido. A pesar de su rechazo inicial del caso, Rhyme pronto descubrirá que el asesino ha dejado escrito un mensaje que sólo un forense como Ã©l puede descubrir. A partir de ese momento, Amelia se convertirá en sus ojos, brazos y piernas para conseguir atrapar al peligrosa psicópata.",
                "Denzel Washington, Angelina Jolie, Queen Latifah, Michael Rooker"
            )
        )
        agregarPelicula(
            Pelicula(
                "Deuda de sangre",
                getGenero("Accion"),
                "El veterano Clint Eastwood, interpreta a Terry Mc Caleb, un detective retirado del FBI, que se recupera de un transplante de corazón. Graciela Rivers lo contrata para investigar el asesinato de su hermana, quien resulta ser la donante. Pronto el ex agente descubre que el autor es un asesino serial, a quien intenta atrapar hace años. El mayor problema que tendrá que enfrentar Mc Caleb serán sus propias limitaciones a la hora de actuar, debido a su delicado estado de salud.",
                "Clint Eastwood, Wanda De Jesus, Jeff Daniels, Anjelica Huston "
            )
        )
        agregarPelicula(
            Pelicula(
                "El quinto elemento",
                getGenero("C.Ficcion"),
                "La historia comienza el 18 de marzo de 2259. El mal llega a la Tierra con un único objetivo, destruirla. Para ello contará con la ayuda de Zorg (Gary Oldman), cuya misión es destruir el único arma capaz de salvar a la Tierra, y de la que solo unos pocos tienen conocimiento. Esta arma, conocida como el quinto elemento, es en realidad una chica (Milla Jovovich), que e su desesperada carrera topará de bruces con Korben Dallas (Bruce Willis), un taxista de Nueva York que inesperadamente se encontrará inmerso en una lucha entre las fuerzas del bien y del mal.",
                "Bruce Willis, Ian Holm, Gary Oldman, Milla Jovovich, Ian Holm "
            )
        )
        agregarPelicula(
            Pelicula(
                "Poltergeist",
                getGenero("Terror"),
                "Steve y Diane se mudan con sus tres hijos a una nueva casa. Al poco tiempo de instalarse, comienzan a sucederles cosas extrañas. Los fenómenos comienzan con objetos que se mueven, pero van cada vez en aumento, hasta que la pequeña de la casa, Carol Anne, desaparece en su propio cuarto. La familia contacta con un grupo de parapsicólogos para que les ayuden a recuperar a su hija.",
                "Dominique Dunne, Craig T. Nelson, Heather O Rourke, Oliver Robins "
            )
        )
        agregarPelicula(
            Pelicula(
                "Casablanca",
                getGenero("Clasicos"),
                "El clásico de los tiempos ... volverla a ver como en el cine en este fabuloso e infaltable DVD! . Casablanca es considerado el mayor clásico de la Ã©poca dorada del cine estadounidense. Sus imágenes son parte fundamental del imaginario cultural de la segunda mitad del siglo XX. La última escena en el aeropuerto se cuenta entre las más pregnante de la historia del cine. Durante los primeros incidentes de la Segunda Guerra Mundial, la ciudad de Casablanca se convierte en una posibilidad de obtener un salvoconducto para escapar del conflicto europeo. El RickÂ´s Cafe Americain es el lugar de mayor convocatoria de Casablanca. Su dueño es Rick Blaine, un sujeto carismático y lacónico que prefiere permanecer neutral frente a la guerra. Sin embargo, cambiará con la llegada de Ilsa, un viejo amor que conoció en París. Casablanca no sólo se basa en el carisma de Humphrey Bogart e Ingrid Bergman, la pareja trágica por excelencia del cine mundial. TambiÃ©n se apoya en un grupo de actores secundarios excelentes que incluye a Claude Rains, Peter Lorre, Conrad Veidt, Paul Heinret y Sydney Greenstreet. La melodía de tema Según pasan los años, que recuerda el encuentro de los amantes en París, es un leiv motif sonoro inolvidable. La celebÃ©rrima frase Tócala de nuevo, Sam nunca es pronunciada por Humprey Bogart durante la película.",
                "Ingrid Bergman, Humphrey Bogart, Paul Henreid, Sydney Greenstreet"
            )
        )
        agregarPelicula(
            Pelicula(
                "Tarde perros",
                getGenero("Accion"),
                "Basada en un hecho real. Una pequeña banda de delincuentes de poca monta e inexpertos en el arte de robar decide atracar la sucursal de un banco de Brooklyn, pero su falta de dedicación frustra completamente el intento. Con el botín conseguido, el jefe dela banda quería financiar la operación de cambio de sexo de su amante travestido.",
                "Al Pacino, Penelope Allen, Sully Boyar, John Cazale "
            )
        )
        agregarPelicula(
            Pelicula(
                "Cien veces no debo",
                getGenero("Nacional"),
                "Carmen y Julio Siri conforman un matrimonio de mediana edad. Su única hija es su razón de vivir y pretenden lo mejor para ella. Un día, la hija les confiesa que está embarazada, pero no sabe cuál de sus amantes es el padre. Este hecho dará lugar a grotescas situaciones, mientras los padres se embarcan en la búsqueda de un marido para su hija.",
                "Norma Aleandro, Luis Brandoni, Andrea Del Boca, Federico Luppi, Darío Grandinetti "
            )
        )
        agregarPelicula(
            Pelicula(
                "Delicatessen",
                getGenero("Comedia"),
                "Para los parisinos del siglo XXI la seria escasez de alimentos se traduce en la siguiente disyuntiva: o comes o sirves de comida. Los carniceros con hambre de lucro se inclinan por convertir a sus inquilinos en tajadas y venderlos como chuletas de primera. Por fortuna, un estrafalario grupo de vecinos ha ideado tácticas secretas para sobrevivir en este mundo, asolado por las sospechas, el hambre y el canibalismo. ",
                "Pascal Benezech, Dominique Pinon, Marie Laure Dougnac "
            )
        )
        agregarPelicula(
            Pelicula(
                "Cinema Paradiso",
                getGenero("Drama"),
                "Cinema Paradiso es una historia de amor por el cine. La película narra la historia de un niño de un pequeño pueblecito italiano en el que el único pasatiempo es disfrutar de las películas del Cine Paradiso. Encantado por las oscilantes imágenes, Salvatore deseaba con todas sus fuerzas que el cine fuese en realidad magia. Un día, Alfredo, el operador de cine, accede a enseñar al pequeño los misterios que se ocultan en una película. Todos los niños de la pequeña villa van creciendo, sin perder nunca su amor por el cine, pero llega el momento en el que Salvatore debe dejar el pueblo y comenzar a buscar sus sueños. Y así ocurre durante treinta años hasta que un día un mensaje le comunica que debe volver a casa donde un secreto le espera.",
                "Antonella Attili, Enzo Cannavale, Isa Danieli, Leo Gullotta"
            )
        )
        agregarPelicula(
            Pelicula(
                "El fugitivo",
                getGenero("Suspenso"),
                "Atrápalo si puedes. Es un fugitivo a la carrera. Harrison Ford y Tommy Lee Jones compiten en una persecución sin respiro en esta película basada en la clásica serie de TV. Ford es el Dr. Kimble, un fugitivo acusado de asesinar a su mujer y decidido a probar su inocencia, conduciendo al detective que le persigue sin tregua al verdadero autor del crimen. Tommy Lee Jones (ganador del Oscar y del Globo de Oro al Mejor Actor Secundario) interpreta a Sam Gerard, un implacable policía que no ceja en capturarlo. Dirigida por Andrew Davis (Alerta Máxima), la acción transcurre a un ritmo trepidante que lo dejará sin aliento.",
                "Harrison Ford, Tommy Lee Jones, Sela Ward, Julianne Moore, Joe Pantoliano "
            )
        )
        agregarPelicula(
            Pelicula(
                "Garage Olimpo",
                getGenero("Drama"),
                "La película está ambientada durante los primeros años de la dictadura en Argentina, cuando los tristemente cÃ©lebres grupos de tareas eran amos y señores de las calles del país. Salían a secuestrar personas, las levantaban en vilo de sus domicilios, a los que saqueaban metiÃ©ndose en los bolsillos cualquier cosa de valor. Las encapuchaban y las conducían a sórdidos campos de concentración como en este caso, un inmenso garage en desuso denominado Olimpo. El film narra la odisea de María, una chica que vive con su madre en un edificio donde tambiÃ©n vive Felix, un joven tímido, enamorado de Maria. María trabaja en un barrio pobre, tambiÃ©n es activista y acaba en el Olimpo donde la vida es objeto de una rutina infrahumana. Tigre, el encargado del centro designa a uno de sus mejores hombres para interrogarla, Felix",
                "Antonella Costa, Carlos Echeverría, Enrique Pineyro "
            )
        )
        agregarPelicula(
            Pelicula(
                "Cosecha Negra : Los hijos del mal",
                getGenero("Terror"),
                "Cuando la sangre es el mejor abono. Una pareja que está de viaje llega a un apartado pueblo de Nebraska donde no hay adultos. Todos han sido asesinados por los niños del lugar, que viven como una fanática comunidad religiosa que rinde culto a una extraña deidad de los campos de maíz. Cuando los jóvenes llegan a los 18 años de edad han de ser sacrificados.",
                "John Franklin, Jonas Marlowe, Peter Horton "
            )
        )
        agregarPelicula(
            Pelicula(
                "Midachi : Tomo 3",
                getGenero("Teatral"),
                "El mejor momento del trío. Un show que muestra al grupo en su apogeo con record de espectadores y la aclamación de toda la crítica. Con actuaciones cada vez más pulidas y espectáculos cada vez mas ambiciosos MIDACHI se convirtió en el fenómeno cómico mas importante de la Argentina y LatinoamÃ©rica. En este DVD podrás ver lo mejor de lo mejor del grupo. Diversión Garantizada.",
                "Midachi"
            )
        )
        agregarPelicula(
            Pelicula(
                "Midachi : Tomo 4",
                getGenero("Teatral"),
                "El mejor momento del trío. Un show que muestra al grupo en su apogeo con record de espectadores y la aclamación de toda la crítica. Con actuaciones cada vez más pulidas y espectáculos cada vez mas ambiciosos MIDACHI se convirtió en el fenómeno cómico mas importante de la Argentina y LatinoamÃ©rica. En este DVD podrás ver lo mejor de lo mejor del grupo. Diversión Garantizada",
                "Midachi"
            )
        )
        agregarPelicula(
            Pelicula(
                "Un loco amor",
                getGenero("Drama"),
                "Un día de lluvia, una adolescente de quince años sufre un accidente de moto y queda tendida en el asfalto. Una ambulancia la rescata y traslada al hospital en el que trabaja su padre como cirujano. Mientras un colega opera a su hija, Timoteo queda en la espera. Ante el terror de este acontecimiento extremo, recuerda... deja caer su máscara de firmeza y cinismo, de padre y marido modelo, y revela una imagen de sí mismo extraña y violenta. Con la esperanza de poder oponer la palabra al silencio del coma, y vencer la muerte con la vida, el protagonista revela, en un diálogo imaginario con su hija, un secreto doloroso: la historia, de apariencia miserable, de un potente y visceral amor extra conyugal. Entonces aparece ese cálido verano de tantos años atrás, una miserable periferia urbana, una mujer joven, dócil y desamparada, con un nombre sugerente: Italia.",
                "PenÃ©lope Cruz, Sergio Castellito, Claudia Gerini"
            )
        )
        agregarPelicula(
            Pelicula(
                "8 mm",
                getGenero("Suspenso"),
                "Esta dramática historia sigue la obsesión de un hombre en busca de la verdad sobre un crimen de hace 6 años y el descubrimiento de la verdad sobre el mismo",
                "Nicolas Cage, Joaquin Phoenix, James Gandolfini "
            )
        )
        agregarPelicula(
            Pelicula(
                "Austin Powers 2 : Goldmember",
                getGenero("Comedia"),
                "Han pasado tres años desde que Austin Powers, el espía internacional con más estilo que exista, enfrentara a su archi enemigo, el Dr. Evil. Pero Ã©ste y su compinche Mini Mi han escapado de una prisión de máxima seguridad, y Austin es nuevamente convocado para entrar en acción. Haciendo equipo con el misterioso y malvado Goldmember, el Dr. Evil idea un plan para apoderarse del mundo: viajar en el tiempo y secuestrar a Nigel Powers, el querido padre de Austin, y a su vez, el espía inglÃ©s más reconocido. Mientras persigue a los villanos a travÃ©s del tiempo, Austin llega al año de 1975 y se reencuentra con su antigua compañera, Foxxy Cleopatra, una inteligente y hermosa espía que sabe usar muy bien sus armas. Juntos, Austin y Foxxy, buscarán la forma de salvar a Nigel y de evitar que los malvados Dr. Evil y Goldmember logren su malicioso cometido. Está de Regreso, BABY! Esta vez con la participación de Tom Cruise, Gwyneth Paltrow, Britney Spears, John Travolta, Danny De Vito, Kevin Spacey, Ozzy Osbourne y Flia. y muchos invitados sorpresa mas.",
                "Mike Myers, Verne Troyer, Michael Caine"
            )
        )
        agregarPelicula(
            Pelicula(
                "Che : Un hombre de este mundo",
                getGenero("Documental"),
                "Film documental en que la figura del Che es tomada desde un polo novedoso: el de las voces y las figuras de quienes lo acompañan en sus combates y en su vida cotidiana de triunfos, fracasos y amor; descubriendo al hombre de ejemplar rectitud.",
                "Che Guevara"
            )
        )
        agregarPelicula(
            Pelicula(
                "FlashDance",
                getGenero("Musical"),
                "Alex Owens es una joven que, de día trabaja en una fabrica de acero y, de noche se transforma en una bailarina erótica de un bar. Su sueño siempre fue convertirse en una bailarina profesional. Y será ahora, gracias a la ayuda de su jefe y amante Nick Hurley, que consiga la gran oportunidad de una importante audición para acercarse cada vez más a hacer su sueño realidad. Un verdadero icono del cine de la dÃ©cada de 1980, recordada por el público por su famosa canción Flashdance... What a Feeling, interpretada por Irene Cara y ganadora del Oscar del Globo de Oro.",
                "Jennifer Beals, Michael Nouri, Lilia Skala "
            )
        )
        agregarPelicula(
            Pelicula(
                "Hello Kitty : Cuenta cuentos",
                getGenero("Infantil/Anim"),
                "ROBIN PINGÃœINO : Ã‰rase una vez en el bosque de Knotty Pine, el Sheriff Grinder trataba de robar chicle a todos ... hasta que la Dama Kitty y su amigo Robin PingÃ¼ino se une para echar a perder sus planes cubiertos de azúcar! HOLA MAMÃ GANSA : Guinder Piper ha perdido sus pimientos en escabeche y Catnip McMuffet no ha visto su peluche en todo el día! Â¿Podrá ayudar Hello Mamá Gansa a resolver el misterio y ayudar a que todos vivan felices par siempre? EL PATITO FEO : Mamá Quack (Fangora) ha puesto cuatro nuevos huevos. Pero uno de sus pequeños polluelos (Chip) no cabe. Â¿Podrá este raro pájaro aprender a ser como los otros... o descubrirá algo mucho más emocionante? PINOCHO PINGÃœINO : La solitaria juguetera Gheppetto Kitty nunca ha tenido con quien jugar hasta que un Hada Madrina llega y transforma a su juguete preferido en un pingÃ¼ino vivo. CAPERUCITA ROJA : Caperucita Roja se mete en problemas cuando viaja al Viejo Oeste pra visitar a la Abuela... y corre directamente hacia el forajido Belle Catnip y el gran Malvado lobo grinder. ",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Hello Kitty : Princesa",
                getGenero("Infantil/Anim"),
                "Kitty Cenicienta: La pobre Kitty Cenicienta siempre está hasta los bigotes cuando de labores domÃ©sticas se trata. Pero con un poco de ayuda del Hada Madrina (Abuela Kitty) encontrará diversión, aventura y el amor verdadero, Â¡todo antes de la media noche! Kitty Ricitos y los tres ositos: Ella es bella, es abrazable... Â¡y ha estado durmiendo en la cama de otro! Suena como un problema, pero nadie puede enojarse con Kitty Ricitos, Â¡ni siquiera tres hambrientos osos! La Bella Kitty durmiente: La hermosa Kitty se queda dormida cuando la malvada bruja Fangora lanza un hechizo sobre ella. Ahora, el Príncipe Tuxedo Sam debe hacer algo de magia para despertar a labella durmiente. Kitty y la Bestia: Â¡Abracadabra! Â¡La bruja Fangora ha convertido al Príncipe Sam en una horrible bestia! Ahora parece que nunca volverá a será feliz... hasta que comienza un romance con la dulce y adorable Hello KittyÂ®. Kitty Blancanieves y el Ãšnico Enano: La malvada Reina Catnip manda al Cazador Chip a eliminar a Kitty Blancanieves en el bosque por ser bonita. Ahí ella se hace amiga del Enano Grinder, Â¡quien la salva del próximo ataque de Catnip! ",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Jamaica Bajo Zero",
                getGenero("Comedia"),
                "Un desafortunado accidente no le permita a Derice Bannock clasificar para las olimpiadas en atletismo. Buscando otro deporte en el cual participar y aprovechando que un ex-medalla de oro en carreras de trineo vive en Jamaica, reúne un equipo de valientes jóvenes para hacer algo casi imposible, lograr que Jamaica clasifique para las olimpíadas de trineo",
                "John Candy"
            )
        )
        agregarPelicula(
            Pelicula(
                "La cena de los tontos",
                getGenero("Comedia"),
                "Un grupo de amigos se reúne cada miÃ©rcoles para cenar. Cada uno de ellos debe acudir acompañado de un idiota lo que les permitirá elegir al más idiota de ellos al final de la velada. Una noche, uno de ellos cree haber encontrado a un ejemplar realmente único, pero lo que desconoce es que tambiÃ©n es capaz de provocar apoteósicas catástrofes.",
                "Thierry Lhermitte, Jacques Villeret, Francis Huster "
            )
        )
        agregarPelicula(
            Pelicula(
                "Looney Tunes : Coleccion 1",
                getGenero("Infantil"),
                "Pack 4 dvds. CLASICOS ANIMADOS REMASTERIZADOS. Lo Mejor De Bugs Bunny, Pato Lucas, Porky y sus Amigos... Incluidos dentro de este asombroso surtido de los Looney, con los mejores cortos de Bugs Bunny brillantemente restaurados y remasterizados, están: la Ã©pica pelea entre Bugs y Lucas por ver cuál de los dos juega limpio en TEMPORADA DE CACERIA DE CONEJOS, las desopilantes payasadas de ópera de EL CONEJO DE SEVILLA, y Bugs corriendo en círculo alrededor de un bovino de mal carácter en OREJAS Y RABO PARA BUGS. Y ese es sólo el principio de esta diversión conejera, en esta eterna colección de los mejor del único e incomparable Â¡Bugs Bunny!",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Midachi : Tomo 1",
                getGenero("Teatral"),
                "Dadi, Miguel y el Chino forman MIDACHI, el grupo cómico que bate records de audiencia con cada uno de sus espectáculos y que es un clásico del humor nacional.Por mas de 23 años el trío ha recorrido el país actualizando sus show y a su vez reflejando las diferentes etapas que los argentinos hemos vivido en esos años. En toda su carrera, tres millones y medio de personas han disfrutado su humor y ahora podes disfrutar de su historia en esta colección de 4 DVDs donde podrás encontrar lo mejor del trio en sus primeros y mas recordados show.",
                "Midachi"
            )
        )
        agregarPelicula(
            Pelicula(
                "Midachi : Tomo 2",
                getGenero("Teatral"),
                "Desde sus comienzos allá en Santa Fe hasta su consolidación como uno de los mayores referentes del humor nacional, MIDACHI, debió andar mucho camino. Siempre con shows renovados y apostando a la calidad de sus actuaciones. Fue así como este trío se convirtió en uno de los fenómenos de mayor crecimiento en el espectáculo argentino. En este volumen podrás disfrutar de una etapa decisiva en la carrera de estos tres santafesinos. Un momento cumbre donde pasaron de un simple grupo del interior a ser el espectáculo numero 1 de la calle Corrientes.",
                "Midachi"
            )
        )
        agregarPelicula(
            Pelicula(
                "Prision Break : Temporada 1",
                getGenero("Series"),
                "Pack 6 dvds. Michael Scofield (Wentworth Miller) es un hombre desesperado en un situación deseperada. Su hermano Lincoln Burrows (Dominic Purcell) está en prisión condenado a pena de muerte y a la espera de ser ejecutado. A pesar de todas las evidencias, Michael cree en la inocencia de su hermano, por lo que decide robar un banco para dejarse atrapar y ser encarcelado en la misma prisión que su hermano. Su objetivo: escapar juntos.",
                "Dominic Purcell, Wentworth Miller, Robin Tunney, Peter Stormare"
            )
        )
        agregarPelicula(
            Pelicula(
                "Rocket Power : Sin limites",
                getGenero("Infantil"),
                "8 Atrevidas Aventuras",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Romeo y Julieta",
                getGenero("C.Romantica"),
                "La hermosa historia escrita por William Shakespeare ambientada en el siglo veinte y con las actuaciones estelares de Leonardo DiCaprio y Claire Danes.",
                "Leonardo DiCaprio, Claire Danes "
            )
        )
        agregarPelicula(
            Pelicula(
                "Rugrats Crecidos : El cumple de Angelica",
                getGenero("Infantil"),
                "4 Atrevidas Aventuras",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Shakespeare Apasionado",
                getGenero("C.Romantica"),
                "El joven William Shakespeare es un prometedor dramaturgo de la Ã©poca, pero ha sido profundamente golpeado por la mayor desgracia en la vida de un escritor: la falta de inspiración. Su comedia Romeo y Ethel, la hija del pirata no progresa y el teatro corre peligro de que lo cierren. Lo que William necesita es una musa, que aparece encarnada en la bella (y comprometida) Lady Viola. Sin embargo, la travesía hacia el amor verdadero no es sencilla para William: Viola está comprometida y será desposada por el intolerable Lord Wessex (Colin Firth), por orden de la Reina Isabel (Judi Dench). La dicha y la tragedia de su propia vida le abren camino para escribir un relato conmovedor, ingenioso y cautivante. Shakespeare Apasionado es una película romántica, extraordinariamente divertida, cálida y dinámica, del aclamado director John Madden (Su Majestad Mrs. Brown).",
                "Gwyneth Platrow, Joseph Fiennes, Geoffrey Rush, Colin Firth,Ben Affleck, Judi Dench "
            )
        )
        agregarPelicula(
            Pelicula(
                "Alguien sabe demasiado",
                getGenero("Suspenso"),
                "Art Jeffries (Bruce Willis), un rebelde oficial del FBI, se enfrenta a despiadados agentes federales para proteger a Simon, un niño autista de nueve años que ha descifrado un inquebrantable código secreto. El chico puede leer MERCURY, el código más avanzado que existe, tan fácilmente como otros niños leen el inglÃ©s. Su habilidad prueba que el nuevo código, de un valor de un billón de dólares, es vulnerable, especialmente si enemigos de los Estados Unidos se enteran del don de Simon y lo capturan. El jefe del programa Nick Kudrow (Alec Baldwin) ordena que la amenaza secreta sea eliminada, pero no contaba con que Jeffries se involucrara. Mientras son perseguidos por asesinos letales, Jeffries rápidamente se da cuenta que no puede confiar en nadie. Ahora, el tiempo se acaba y descubre que su única esperanza de sobrevivir es usar la habilidad especial de Simon para llevar a sus adversarios ante la justicia.",
                "Bruce Willis, Alec Baldwin, Miko Hughes "
            )
        )
        agregarPelicula(
            Pelicula(
                "Alta fidelidad",
                getGenero("Comedia"),
                "Rob es el dueño de una tienda de discos en Chicago a punto de cerrar. Sólo vende discos antiguos de vinilo y tiene dos empleados con los que se pasa el tiempo haciendo listas de las cinco mejores canciones de todos los tiempos por categorías específicas .",
                "Lisa Bonet, John Cusack, Todd Louiso "
            )
        )
        agregarPelicula(
            Pelicula(
                "Bajos Instintos",
                getGenero("Suspenso"),
                "Un famoso estrella de rock y dueño de un nightclub de San Francisco, Johnny Boz es encontrado asesinado en su cama. El Detective Nick Curran es asignado al caso; Ã©l tiene una historia de alcoholimo y abuso de drogas aunque ahora Ã©l está recuperado. La principal sospechosa es Catherine Tramell, una atractiva y manipuladora novelista quien fue vista con Boz por un tiempo. La psiquiatra policial Beth Gardner (quien resulta ser la exnovia de Nick) es convocada para el caso cuando se descubre que el asesinato de Boz fue copiado directamente de una novela de Catherine. Nick comienza a quedar demasiado envuelto y todos parecen ser sospechosos...",
                "Michael Douglas, Sharon Stone"
            )
        )
        agregarPelicula(
            Pelicula(
                "El gran Lebowski",
                getGenero("Drama"),
                "Jeff Lebowski es un pobre, bohemio y singular jugador de Bowling, considerado el habitante más haragán de la ciudad de Los Angeles. Unos matones lo confunden con un importante magnate que tiene su mismo apellido. A partir de ese momento, Jeff se involucra en situaciones ridículas e incomprensibles, y se enfrentará con las personas más extrañas de la ciudad de Los Angeles. Hilarante comedia mezcla la comedia musical hollywoodense, con el gÃ©nero pornográfico y el policial negro en una alusión directa a El sueño eterno de Raymond Chandler.",
                "John Goodman, Tara Reid, Philip Seymour Hoffman "
            )
        )
        agregarPelicula(
            Pelicula(
                "El guardaespaldas",
                getGenero("Suspenso"),
                "Frank Farmer es un hombre que cree en el autocontrol. En realidad, su carrera depende de eso. Es guardaespaldas profesional, quizás el mejor, un ex-agente del Servicio Secreto que ha arriesgado su vida en la custodia de dos presidentes estadounidenses y una serie de importantes y poderosos clientes. Nunca deja nada librado a la casualidad. Hasta que conoce a Rachel Marron, una cantante y actriz top para la cual armará toda una estructura de seguridad para protegerla de un maniático fan. Ambos son dos profesionales en la cima de sus carreras-uno vive calculando meticulosamente cada paso y resuelto a nunca bajar la guardia, el otro con la pura esencia de la sensualidad. Ambos esperan tomar el control. Lo que no esperan es enamorarse. Un thriller romántico acerca del poder, la obesión y el sometimiento. ",
                "Kevin Costner, Whitney Houston, Bill Cobbs"
            )
        )
        agregarPelicula(
            Pelicula(
                "Elvis 68 Comeback Special",
                getGenero("Musical"),
                "",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "La estafa maestra",
                getGenero("Accion"),
                "El plan no tenía fallas... el robo se ejecutó a la perfección. La única amenaza que Charlie Croker nunca imaginó fue la traición de un miembro de su propia banda. Luego de completar el audaz robo de un cargamento de lingotes de oro, Charlie y su pandilla: el informante Steve, el genio de las computadoras Lyle, el corredor de autos Handsome Rob, el experto en explosivos Left-Ear y el veterano ladrón de cajas fuertes John Bridger, se quedan asombrados al descubrir que uno de ellos los ha traicionado. Ahora el objetivo no es dividirse el botín, sino vengarse.",
                "Edward Norton, Charlize Theron, Donald Sutherland"
            )
        )
        agregarPelicula(
            Pelicula(
                "La historia Oficial",
                getGenero("Nacional"),
                "En el año 1983, en las postrimerías de la dictadura militar en Argentina, una profesora de historia, comienza a sospechar que su hija de cinco años, a la que su marido trajo a la casa, supuestamente desde un hospital, puede ser hija de ciudadanos detenidos-desaparecidos. Esta obra, ganadora de varios premios internacionales, entre otros de un Oscar como mejor film extranjero, constituye un testimonio sobre los años de terror vividos en la dictadura y sobre la conciencia política que, abruptamente, comenzó a adquirir sociedad argentina con el retorno de la democracia.",
                "HÃ©ctor Alterio, Norma Aleandro, Patricio Contreras "
            )
        )
        agregarPelicula(
            Pelicula(
                "La leyenda del jinete sin cabeza",
                getGenero("Suspenso"),
                "A fines del siglo XVIII, Ichabod Crane tiene diferentes inquietudes científicas que son incomprendidas por sus contemporáneos. Como castigo lo envían a investigar una serie de asesinatos de un pueblo llamado Sleepy Hollow. Al llegar, Ichabod se encuentra con una serie de supersticiones y leyendas sobre un jinete sin cabeza que habita en los bosques del pueblo y se dedica a decapitar a distintos pobladores. Adaptación del relato clásico de Washington Irving, que fue llevado a la pantalla en diferentes ocasiones. Ganadora del Oscar a Mejor dirección de arte.",
                "Johnny Depp, Cristina Ricci, Christopher Walken"
            )
        )
        agregarPelicula(
            Pelicula(
                "La profecia -edicion especial-",
                getGenero("Terror"),
                "El nació a las 6 de la mañana del sexto día del sexto mes. La llegada del fin del mundo, la lucha entre las fuerzas del bien y del mal tal como lo anunciaba el Apocalipsis, comenzará con el nacimiento del hijo de Satanás. Incapaz de contarle a su esposa Catherine (Lee Remick) la trágica noticia de la muerte de su bebÃ© reciÃ©n nacido, el diplomático americano Robert Thorn (Gregory Peck) acepta a un huÃ©rfano como su hijo. Detalles sobre el nacimiento del niño permanecen en absoluto secreto, pero a medida que Damien va creciendo, queda en evidencia de que Ã©l está muy lejos de ser un chico común y corriente. Pero mientras muertes inexplicables y misteriosos acontecimientos se suceden, Robert Thorn toma conciencia del terrible mal que se esconde detrás del rostro inocente del niño y del verdadero significado de los números 666 que anuncian la más terrible de las revelaciones",
                "Gregory Peck, Lee Remick, David Warner "
            )
        )
        agregarPelicula(
            Pelicula(
                "Les Luthiers : Mastropieros que nunca (1979)",
                getGenero("Teatral"),
                "",
                "Les Luthiers"
            )
        )
        agregarPelicula(
            Pelicula(
                "Les Luthiers : Unen canto con humor (1999)",
                getGenero("Teatral"),
                "",
                "Les Luthiers"
            )
        )
        agregarPelicula(
            Pelicula(
                "Les Luthiers : Viejos fracasos (1977)",
                getGenero("Teatral"),
                "",
                "Les Luthiers"
            )
        )
        agregarPelicula(
            Pelicula(
                "Les Luthiers : Vigesimo aniversario",
                getGenero("Teatral"),
                "",
                "Les Luthiers"
            )
        )
        agregarPelicula(
            Pelicula(
                "Muertos de Risa (TVE)",
                getGenero("Comedia"),
                "Muertos de Risa es un drama humano que parece un chiste. Sus protagonistas son Nino y Bruno, dos humoristas condenados al Ã©xito más estruendoso en una España marcada por los cambios sociopolíticos, la televisión, las canciones del verano y las modas que vienen y van. Como todo no puede ser perfecto, Nino y Bruno pronto descubren que, cuanto más se odian, más Ã©xito tienen, y cuanto más Ã©xito tienen, más se odian.",
                "Sancho Gracia, María Asquerino, Jesús Bonilla  "
            )
        )
        agregarPelicula(
            Pelicula(
                "Oldboy",
                getGenero("Accion"),
                "Un día Oh Daesu (Choi Min-sik), un hombre que lleva una vida de lo más normal con su mujer y su pequeña hija, es secuestrado delante de su casa. Cuando se despierta, está encerrado, sin saber dónde ni por quÃ©. Mientras Daesu intenta aclarar quÃ© le ha pasado, se queda horrorizado al oír en las noticias que su esposa ha sido brutalmente asesinada. La policía explica que Daesu es el principal sospechoso ya que se ha encontrado sangre suya en el lugar del crimen. El tiempo pasa, y decide escribir todo lo que ha hecho en su vida que haya podido causar dolor a otros. Mientras escribe, murmura: He hecho daño a demasiadas personas. Seguro que el hombre que ha matado a mi mujer y me tiene aquí es una de ellas. Poco a poco, Daesu se acostumbra a la penumbra de su celda y hace ejercicios físicos y mentales. Jura que se vengará del hombre que ha destruido su felicidad. Un día, alguien vaporiza la habitación con gas y Daesu se desmaya. Daesu vuelve en sí. Está libre. Le han dejado un telÃ©fono y una cartera llena de dinero. Recibe la llamada de un extraño, que le dice que ahora le toca descubrir el porquÃ© de su encarcelamiento. Un pasado olvidado le revelará el secreto. EXCELENTE !!!",
                "Choi Min sik, Woo Ji tae, Gang Hye jung"
            )
        )
        agregarPelicula(
            Pelicula(
                "Taken",
                getGenero("Series"),
                "Pack 5dvds - Una saga que entreteje historias extraordinarias de abducción por extraterrestres tal y como las experimentaron 3 familias a lo largo de 4 generaciones y 50 años de secretos transmitidos. Desde los cielos de Alemania durante la Segunda Guerra Mundial, pasando por la Guerra de Vietnam y llegando hasta el nuevo milenio, este drama explora lo que sabemos acerca de encuentros con extraterrestres a lo largo de un retablo histórico que abarca 50 años.",
                "Joel Gretsch, Catherine Dent, Dakota Fanning,"
            )
        )
        agregarPelicula(
            Pelicula(
                "Amarcord (Mis recuerdos)",
                getGenero("Drama"),
                "Descripción de la vida cotidiana de la gente de un pueblo en el norte de Italia durante los años del fascismo, de la dÃ©cada de los treinta. Esta película fue nominada para los Oscars al mejor Director y Guión original.  Un film espectacular de Federico Fellini",
                "Pupella Maggio, Armando Brancia, Magali NoÃ«l "
            )
        )
        agregarPelicula(
            Pelicula(
                "Atrapado sin salida",
                getGenero("Drama"),
                "Para no entrar en la cárcel, Randle Patrick McMurphy convenció al juez que es una persona insana y merece estar dentro de una institución psiquiátrica. Cuando Randle se incorpora en el hospital se encuentra con un mundo desconocido. Aunque Ã©l piensa que puede comportarse como quiera, se va a enfrentar con la enfermera Mildred Ratched, una mujer dura y que no va a permitir ningún desorden dentro de su institución. Basada en la novela de Ken Kesey. Ganadora de cinco Oscars, incluyendo Mejor película, director, actor y actriz protagónica.",
                "Jack Nicholson, Louise Fletcher, William Redfield "
            )
        )
        agregarPelicula(
            Pelicula(
                "Barney : ABC",
                getGenero("Infantil"),
                "Si de repente te planteas que si los tipos que hicieron Aterriza como puedas y Agárralo como puedas están chiflados, la respuesta es sí. Y Top Secret lo demuestra sin duda alguna. Top Secret presenta a la estrella americana del rock Nick Rivers ",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Barney : Cantando y bailando",
                getGenero("Infantil"),
                "Acompaña a Barney en este tributo a sus primeros diez años. Barney ha planeado una fiesta musical muy especial y ha invitado a muchos de sus viejos amigos. El grupo disfruta cantando las melodias favoritas de todos los tiempos, mientras son transportados a magicos lugares",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Barney : Imaginemos",
                getGenero("Infantil"),
                "Imagina la diversión que tendrás con Barney y sus amigos cuando construyen su propio avión y se aventuran a una isla tropical! Imaginando que son desde pilotos hasta piratas en la búsqueda de un tesoro escondido, los amigos de Barney descubren que la creatividad los deja elevarse en las alas de la imaginación.  ",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Barney : Juguemos en la escuela",
                getGenero("Infantil"),
                "Con una pequeña ayuda de Barney y una gran imaginación, los niños disfrutan de un día lleno de diversión en la Escuela de Baby Bop, donde cada lección incluye sorpresas inesperadas. Ãšnete a Ashley, Hannah, Robert y Jeff, mientras cantan la canción del alfabeto, leen en la librería, comen macarrón con queso y dan brincos y saltos en la clase de gimnasia. Cuando el día estÃ© por terminar acompaña a Baby Bop y su sabanita amarilla a tomar una siesta. Este es un inolvidable días repleto de risas y aprendizaje.",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Como perder un hombre en 10 dias",
                getGenero("C.Romantica"),
                "Andie Anderson escribe la columna How To en la revista Composure. Debe terminar un reportaje muy poco usual y tiene muy poco tiempo para conseguirlo. Se trata de escribir, basándose en experiencias de primera mano, sobre todas esas cosas que hacen las mujeres para alejar a los hombres de su lado sin querer... y debe tenerlo listo en diez días. Su misión es encontrar a un chico, hacer que se enamore de ella y luego provocar todos los errores típicos que pueden suceder cuando se tiene una cita, para que el chico pase de ella. La pena es que el sujeto que elige como blanco es el cotizado soltero Benjamin Barry, que precisamente acaba de apostar con el director de la agencia de publicidad en donde trabaja que es capaz de hacer que una mujer se enamore de Ã©l en diez días.",
                "Kate Hudson, Matthew McConaughey, Kathryn Hahn "
            )
        )
        agregarPelicula(
            Pelicula(
                "Conociendo a Julia",
                getGenero("Drama"),
                "En el Londres de 1938, la bella y seductora Julia Lambert se encuentra en su apogeo físico y profesional. Sin embargo, tanto su carrera teatral, llena de Ã©xitos, como su matrimonio con el guapo empresario teatral Michael Gosselyn empiezan a parecerle caducos e insatisfactorios y echa de menos la novedad, la emoción, la chispa. Todo cambia con la entrada en escena de Tom Fennell, un joven que afirma ser su mayor admirador. Su ardor es irresistible para Julia, que, pensando que un romance será el mejor antídoto para su crisis de edad, decide embarcarse en una apasionada relación. Su vida pasa a ser más atrevida y emocionante hasta que su joven amante intenta relegarla cruelmente a un papel secundario. Reuniendo todas sus fuerzas, Julia trama una inteligente venganza que la conducirá al lugar que le corresponde: en el centro del escenario y en primer plano...",
                "Annette Bening, Jeremy Irons, Bruce Greenwood"
            )
        )
        agregarPelicula(
            Pelicula(
                "Divinas tentaciones",
                getGenero("C.Romantica"),
                "Ben Stiller (Loco por Mary), Jenna Eifman (EDTV) y Edward Norton (El club de la pelea) protagonizan Divinas Tentaciones, comedia sexy y romántica con gracia y frescura. Jake Schram (Stiller) y Brian Finn (Norton) son dos chicos solteros, exitosos, muy populares, que han sido amigos desde siempre. Están a punto de reunirse con su gran amiga del pasado, la alegre Anna (Elfman) quien se ha convertido en una poderosa belleza adicta al trabajo, cuya vuelta a la vida de los amigos convierte al antiguo grupo en un triángulo amoroso bastante complicado, por cierto, dado que Jake es rabino y Brian sacerdote. Pero tengan fe, porque este tesoro les va a robar el corazón.",
                "Edward Norton, Ben Stiller, Jenna Eifman"
            )
        )
        agregarPelicula(
            Pelicula(
                "El nombre de la rosa",
                getGenero("Suspenso"),
                "Es obra del Diablo. Es lo que algunos opinan cuando se desata una siniestra serie de asesinatos en un monasterio del siglo XIV. Otros descubren que existe una relación entre las muertes y el libro de la Revelación, pero el hermano William de Baskerville piensa diferente. El pretende descubrir al asesino utilizando los hechos y la razón, las herramientas de la herejía.",
                "Sean Connery, Christian Slater, F. Murray Abraham"
            )
        )
        agregarPelicula(
            Pelicula(
                "El tunel del tiempo : Temporada 2",
                getGenero("Series"),
                "Pack 8 DVD's de la segunda temporada con 15 episodiosEpisodios : The Revenge Of Robin Hood - Kill Two By Two - Visitors From Beyond The Stars - The Ghost Of Nero - The Walls Of Jericho - Idol Of Death - Billy The Kid - Pirates Of Deadman's Island - Chase Through Time - The Death Merchant - Attack Of The Barbarians - Merlin The Magician - The Kidnappers - Raiders From Outer Space - Town Of Terror",
                "Whit Bissell, Robert Colbert, James Darren, Sam Groom, Wesley Lau, Lee Meriwether, John Zaremba "
            )
        )
        agregarPelicula(
            Pelicula(
                "Experta en bodas",
                getGenero("C.Romantica"),
                "Una comedia romántica sobre el amor, el destino y otros acontecimientos. María Fiore (Jennifer López) es una planificadora de boda. Ella es ambiciosa, trabajadora, sumamente organizada, y ella sabe exactamente que hacer y que decir cuando hace cualquier boda. Pero ella repentinamente se enamorará de un doctor y su vida tomará un giro inesperado. Ud nunca puede planear que va a pasar.",
                "Jennifer Lopez, Matthew McConaughey "
            )
        )
        agregarPelicula(
            Pelicula(
                "La dama y el vagabundo 2",
                getGenero("Infantil"),
                "Una señora y un cachorro, Pícaro, anhela el estilo de vida salvaje y libre como el que su padre tenía una vez, ahora el huirá de casa y en las calles se unirá y crecerá viviendo con un grupo de perros perdidos conocidos como los Perros de Junkyard. Buster, el líder del grupo, lo tomará como enemigo y Ã©l lo considera un rival. Â¿Escogerá el Pícaro la vida salvaje y libre de un perdido o el amor incondicional de su familia?",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Les Luthiers : El grosso concerto (2001)",
                getGenero("Teatral"),
                "",
                "Les Luthiers"
            )
        )
        agregarPelicula(
            Pelicula(
                "Les Luthiers : Grandes Hitos Antologia (1995)",
                getGenero("Teatral"),
                "",
                "Les Luthiers"
            )
        )
        agregarPelicula(
            Pelicula(
                "Les Luthiers : Hacen mucha gracia de nada (1980)",
                getGenero("Teatral"),
                "",
                "Les Luthiers"
            )
        )
        agregarPelicula(
            Pelicula(
                "Les Luthiers : Humor dulce holgar (1986)",
                getGenero("Teatral"),
                "",
                "Les Luthiers"
            )
        )
        agregarPelicula(
            Pelicula(
                "Los Cazafantasmas",
                getGenero("Aventuras"),
                "Los Drs. Venkman, Stantz y Epengler, son tres doctores en parapsicología que se quedan sin empleo tras quedar excluidos de una beca universitaria de investigación. Entonces deciden formar la empresa Los Cazafantasmas, dedicada a limpiar Nueva York de ectoplasmas. El aumento repentino de apariciones espectrales en la Gran Manzana será el presagio de la llegada de un peligroso y poderoso demonio",
                "Bill Murray, Sigourney Weaver, Dan Aykroyd, Harold Ramis, Rick Moranis, William Atherton, Annie Potts"
            )
        )
        agregarPelicula(
            Pelicula(
                "Los Cazafantasmas 2",
                getGenero("Aventuras"),
                "Han pasado cinco años desde que los Cazafantasmas entraron por última vez en acción. El doctor Peter Venkman, notable parapsicólogo de persuasivos encantos, ha quedado relegado a maestro de ceremonias de un programa de televisión donde se discuten fenómenos psíquicos. Ray Stantz y su colega Winston Zeddemore se ganan la vida entreteniendo a niños en fiestas infantiles, y el mago tecnológico Egon Spengler continúa sus investigaciones sobre los efectos de las emociones humanas en el campo de energía psicomagnÃ©tica. La relación de Dana Barrett con Venkman se disolvió; ella se casó con otro hombre y tuvo un hijo, pero el matrimonio fracasó. Ahora Dana vive sola en Nueva York, criando a Oscar, su bebÃ© de ocho meses, y trabajando como restauradora de cuadros en el Museo de arte de Manhattan, junto a un excÃ©ntrico experto en pinturas del periodo romántico, Janosz Poha. Todo parece normal hasta que el cochecito de Oscar rueda por sí solo y se mete entre el peligroso tráfico de la ciudad. Dana rescata al niño, pero se da cuenta que elementos sobrenaturales han entrado de nuevo en su vida y que amenazan a su hijo. Los Cazafantasmas no pueden resistirse a la petición de ayuda de Dana, especialmente Venkman, que aún sigue enamorado de ella. Se les une de nuevo el intrÃ©pido contador y ahora reciÃ©n graduado abogado Louis y Janine, su secretaria de inagotable paciencia",
                "Bill Murray, Sigourney Weaver, Dan Aykroyd, Harold Ramis, Rick Moranis, Ernie Hudson, Annie Potts"
            )
        )
        agregarPelicula(
            Pelicula(
                "Los mejores 120 goles de Maradona",
                getGenero("Documental"),
                "Los goles y juagadas de Diego por todo el mundo y de toda su carrera deportiva, goles con Argentinos Juniors, goles con Boca Juniors, goles con el Barcelona, goles con el Nápoles, goles con el Seleccionado Argentino, quÃ©dese paralizado viendo este DVD donde muestra la impresionante habilidad, calidad y sobre todo la grandeza del futbolista más grande de todos los tiempos que dío este deporte. Incomparable. ",
                "Diego Maradona"
            )
        )
        agregarPelicula(
            Pelicula(
                "Mad Max 1",
                getGenero("C.Ficcion"),
                "Historia de Aventura y horror gotico que se situa en Australia en el cercano futuro. La sociedad urbana esta decayendo inevitablemente. Los caminos de las ciudades del imperio se convirtieron en un lugar de pesadilla, extrañas muertes, peleas entre nomades y un puñado de jovenes policias en autos de persecucion. Mad-Max es un joven policia responsable de la muerte de un loco peleador conocido como el caballero de la noche. La mujer de Max, Jessie quiere que abandone las rutas. Una banda de pandileros llevados por un extraño psicopata Â¨corta uñasÂ¨ persigue a una joven pareja en el condado y destroza su automovil con un violento frenesi.",
                "Mel Gibson, Joanne Samuels, Steve Bisley, Roger Ward "
            )
        )
        agregarPelicula(
            Pelicula(
                "Mad Max 2",
                getGenero("C.Ficcion"),
                "Despues de Mad Max, George Miller da un drastico paso mas alla en cuanto a concepcion tecnica. El resultado es Mad Max 2: El guerrero de las Rutas, una epica de accion y muerte que lo invadira a toda velocidad, llevandolo a un paisaje casi onirico, donde el futuro postnuclear se encuentra con el pasado mitologico. Gracias a Max nace un nuevo orden. La civilizacion lucha por emerger nuevamente de las cenizas. Y despues que Mad Max siga su camino, Ud. no podra olvidarlo nunca.",
                "Mel Gibson, Vernon Wells, Virginia Hey, Bruce Spence "
            )
        )
        agregarPelicula(
            Pelicula(
                "Mad Max 3",
                getGenero("C.Ficcion"),
                "Mel Gibson regresa en Â¨Mas Alla de la Cupula del TruenoÂ¨ como el heroe que se enfrenta solo a los barbaros en la era postnuclear. Tina Turner es Auntie Entity una poderosa mujer que pretende dominar la ciudad utilizando a Max. George Miller agrega escenas de lucha jamas vistas en otra pelicula apocaliptica, donde Mad Max esta dispuesto a salvar lo que queda del mundo civilizado.",
                "Mel Gibson, Tina Turner, George Ogilvie, Angelo Rossitto "
            )
        )
        agregarPelicula(
            Pelicula(
                "Mirada de Angel",
                getGenero("Drama"),
                "Un hombre misterioso reclama para ser al ángel de guarda de un policía femenino. Persiguiendo a un sospechoso una noche, el Policía de Chicago Sharon Pogue (Jennifer López) casi se hace la víctima de una emboscada fatal hasta un forastero misterioso, Coger (Jim Caviezel) interviene, desarma al asesino y salva(ahorra) la vida de Sharon. Â¿Esto es un golpe de suerte? Â¿Una torcedura de destino? Â¿O solamente(justo) un ciudadano afectado(preocupado) quien pasó de pasar en el tiempo derecho y era con miedo de haber implicado? Tal vez, Pero Sharon y Coge se han encontrado una vez antes. Como dos se enamoran, ellos descubren la verdad sobre el uno al otro y se obligan a ocuparse de los secretos de su pasado.",
                "Jennifer Lopez, Sonia Braga, Jim Caviezel "
            )
        )
        agregarPelicula(
            Pelicula(
                "Nuestro Amor",
                getGenero("Drama"),
                "Ben y Katie Jordan unieron sus familias, sus carreras y sus vidas hasta que algo se les escapó de las manos. Ahora, despuÃ©s de 15 años diciendo Sí, los dos piensan Hasta aquí lleguÃ©. Â¿Puede una pareja sobrevivir a 15 años dematrimonio?.",
                "Bruce Willis, Michelle Pfeiffer "
            )
        )
        agregarPelicula(
            Pelicula(
                "Perfume de mujer",
                getGenero("Drama"),
                "Es el fin de semana de Día de Gracias en Nueva York y el Coronel Frank Slade (Al Pacino) está en la ciudad para probar un poco de buena vida; alta cocina, mujeres hermosas, limusinas manejadas por choferes y una suite en el Waldorf-Astoria. Y el joven Charlie Simms (Chris Oâ€™ Donnell) está invitado a regañadientes al paseo, adquiriendo la lección de su vida. Slade es una de esas personas únicas e irrepetibles, que por quedarse ciego, se ha vuelto irascible y un poco poeta. Charlie es un fervoroso estudiante de preparatoria, enfrentando una difícil encrucijada en su vida. Su bulliciosa, reveladora y a veces histÃ©rica aventura en Nueva York los hará cambiar para siempre ...",
                "Al Pacino, Chris OÂ´Donnell, Gabrielle Anwar "
            )
        )
        agregarPelicula(
            Pelicula(
                "Ace Ventura",
                getGenero("Comedia"),
                "Ace Ventura es un detective que se dedica a recuperar animales perdidos. Un día es contratado por un equipo de fútbol americano para que encuentre a su mascota, un simpático delfin. Es aqui donde comienza la más alocada carrera, bajo tierra, bajo agua o bajo el fuego de las armas de unos maleantes que quieren malograr su busqueda. ",
                "Jim Carrey, Sean Young, Courteney Cox"
            )
        )
        agregarPelicula(
            Pelicula(
                "Baby Einstein : Aprendiendo los numeros",
                getGenero("Infantil"),
                "Baby Einstein, Aprendiendo los Numeros les das a los bebes un primer acercamiento a los numeros del 1 al 5 y lo hace de una manera, entretenida e interactiva. Esta dinamica exploracion de los numeros fascinara a tu hijo a traves de las imagenes de juguetes, mascotas y objetos del mundo real acompañadas por la bella musica de Haydn, Chopin, Strauss y Schubert. Este acercamiento ayudara a tu pequeño a comenzar a entender lo que los numeros representan. Utiliza objetos de uso cotidiano, musica, arte, idiomas, ciencia y la naturaleza para acercar a los niños, de una manera fascinante y divertida, al mundo que los rodea. Todos nuestros videos contienen musica especialmente reorquestada para ver escuchada por los pequeños. Te invitamos a que compartas con tu bebe y disfruten las imagenes y sonidos juntos. ",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Baby Einstein : Baby Santas",
                getGenero("Infantil"),
                "Baby SantaÂ´s Music Box es una alegre y mágica celebración que cautivará a bebÃ©s y preescolares con luces brillantes, adornos, campanas de trineo, escenas invernales, niños felices y graciosos Papás Noel, todo acompañado por melodías tradicionales de alrededor del mundo, además de clásicos favoritos de Tchaikovsky, Mozart y Handel. ",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Barney : Numeros ! Numeros !",
                getGenero("Infantil"),
                "Tony, el amigo de Barney, muestra una cajita de números que hizo junto con su padre. De repente un viento fuerte vuela los números por todos lados. Barney y sus amigos inician una casería en el parque y dentro del furgón de cola. Descubren que hay números en todos lados. Y ?que seria una casería de números sin l Numero Limbo y el juego del Postre Agitado? Cuando solo faltan dos números por encontrar, Barney invita a los niños en casa a unirse para encontrar los números  y 10. ! Cuenta con Barney en hacer el aprendizaje súper estupendamente divertido! ",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Barney : Puedes ser lo que tu quieres",
                getGenero("Infantil"),
                "Â¿Que vas a ser cuando seas grande?, Â¿Que tal Bombero?, Â¿O por que no enfermera?. íHay que tomar una decision! Afortunadamente Barney esta ahi para ayudarte. El sabe que con la imaginacion, tu íPuedes ser lo que tu quieras!. Unete a los amigos de Barney y disfruta de esta tienda de disfraces donde podran jugar a tener alguna profesion. íPara ser lo que tu quieras ser!",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Barney : Rojo, Amarillo y Azul",
                getGenero("Infantil"),
                "",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Barney : Volumen 4 - El autobus magico ",
                getGenero("Infantil"),
                "Â¡No Esperes Más! Sube Al Autobús, Enciende Tu Imaginación Y Prepárate Para Realizar El Viaje De Tus Sueños, En Compañía De Barney Y Sus Amigos. Â¿Cuál es tu destino imaginari?Â¿QuÃ© tal comenzar este divertido viaje en un castillo, donde los sueños, de Brett se vulven realidad?. Regresa al autobús y descubre el camino que te llevará a saborear el delicioso Pepperoni de la Pizzería de Barney. Tómalo nuevamente y vive las aventuras de Keesha en el Viejo Oeste y de Baby Bop en el circo. Pero Â¡espera! nuestra aventura no termina aún, es el turno para BJ quien, con la ayuda de Barney, hace realiad sus notas musicales al formar su Dino-Banda",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Cirque du Soleil : A baroque Odissey",
                getGenero("Familia"),
                "Un extraordinario viaje al corazón del universo del Cirque du Soleil. Esta retrospectiva, realizada en 1994 con motivo de su dÃ©cimo aniversario, saca a la luz la esencia del Cirque du Soleil; toda aquella energía y magia que alumbraron el fenómeno y la maestría incomparable que ha cautivad y deleitado al público de todo el mundo.",
                "Cirque du Soleil"
            )
        )
        agregarPelicula(
            Pelicula(
                "Cirque du Soleil : Alegria",
                getGenero("Familia"),
                "QuÃ© pasaría si todo fuese posible? Â¿QuÃ© pasaría si el escenario se transformara en un mundo fantástico en el que los ancianos fueran niños nuevamente, y en el que los payasos fueran los reyes del lugar? Y si eso fuera ALEGRIA, Â¿quÃ© sería lo que harías con todo ello? En todos los lugares por donde ALEGRIA ha pasado, aún permanece su esencia de locura y el eco de irreverentes carcajadas. Su magia y emoción surgen de un mundo volteado de cabeza...",
                "Cirque du Soleil"
            )
        )
        agregarPelicula(
            Pelicula(
                "Cirque du Soleil : Cirque Reinvente",
                getGenero("Familia"),
                "Damas y caballeros, queridos niños, Ã©rase una vez... El circo siempre ha tenido un atractivo mágico. Pero Cirque du Soleil ha redefinido el significado del tÃ©rmino y lo ha elevado a otro nivel: arte circense sin límites, ballet sin gravedad. Y su fascinadora mezcla de vestuario, música, diseño escÃ©nico y sentido del espectáculo ha deslumbrado a millones de espectadores. Cirque RÃ©inventÃ©, galardonada con un premio Emmy, muestra la gira que el Cirque du Soleil realizó en el año 1987 con todos los aforos llenos. Incluye número stan asombrosos como el equilibrio sobre manos, la cuerda floja, la bicicleta acrobática, el equilibrio sobre sillas, contorsionismo, los payasos y las acrobacias aÃ©reas. Con nuestra pasión y vuestra juventud, queríamos reinventar el circo.",
                "Cirque du Soleil"
            )
        )
        agregarPelicula(
            Pelicula(
                "Cirque du Soleil : Drailon",
                getGenero("Familia"),
                "Cirque du Soleil, la vanguardista compañía de fama mundial que ha revolucionado y reinventado el circo, nos ofrece otro extraordinario viaje para los sentidos con su última y aclamada producción: Dralion. Una original mezcla entre el circo tradicional chino y la maestría de los artistas del Cirque, el espectáculo que han visto ya casi 3 millones de espectadores en todo el mundo está disponible por primera vez en esta edición especial en DVD. Además de las sobrecogedoras acrobacias, el delicioso sentido del humor, la música hipnótica y el atractivo vestuario de la producción original, esta versión incluye tambiÃ©n una sección entre bambalinas de cómo se hizo este memorable espectáculo.",
                "Cirque du Soleil"
            )
        )
        agregarPelicula(
            Pelicula(
                "Cirque du Soleil : El viaje del hombre",
                getGenero("Familia"),
                "A child is born. We see underwater swimmers representing this. He is young, in a jungle setting, with two fanciful instincts guiding him as swooping bird-like acrobats initially menace, then delight. As an adolescent, he enters a desert, where a man spins a large cube of metal tubing. He leaves his instinct-guides behind, and enters a garden where two statues dance in a pond. As he watches their sensual acrobatics of love, he becomes a man. He is offered wealth (represented by a golden hat) by a devil figure. In a richly decorated room, a scruffy troupe of a dozen acrobats and a little girl reawaken the old man's youthful nature and love!",
                "Cirque du Soleil"
            )
        )
        agregarPelicula(
            Pelicula(
                "Cirque du Soleil : La magia continua",
                getGenero("Familia"),
                "Aclamado por más de 30 millones de espectadores en todo el mundo y galardonado con numerosos premios y reconocimientos, Cirque du Soleil ha reinventado y revolucionado el mundo del circo. Desde 1984 han deslumbrado al público con su mezcla de artes circenses y teatro callejero, todo ello arropado por un espectacular vestuario y decoración de cuento de hadas, una música hipnótica y una iluminación mágica.La Magie Continue es una de las joyas de la corona del Cirque du Soleil. Se trata de su segunda gira, con la que alcanzaron el Ã©xito y la popularidad. Este DVD permitirá tanto a fanáticos como admiradores conocer los comienzos de lo que iba a convertirse en un fenómeno mundial y una inspiración para millones de personas.",
                "Cirque du Soleil"
            )
        )
        agregarPelicula(
            Pelicula(
                "Cirque du Soleil : Nouvelle experience",
                getGenero("Familia"),
                "La habilidad del Cirque du Soleil ha transformado el circo en una experiencia emocionante y original. Nouvelle ExpÃ©rience celebra la magia de sus innovadoras tÃ©cnicas y el talento de uno de los grupos de artistas más revolucionarios del mundo.El espectáculo que ha girado durante 19 meses entre 1990 y 1991 ante más de 1.3 millones de personas incluye: contorsionismo, la tabla coreana, un solo de trapecio, la cuerda floja, las correas aÃ©reas, acrobacia, el trapecio, malabaristas, payasos, la barra rusa, el trampolín y el equilibrio sobre sillas.",
                "Cirque du Soleil"
            )
        )
        agregarPelicula(
            Pelicula(
                "Cirque du Soleil : Quidam",
                getGenero("Familia"),
                "Cirque Du Soleil, Ganador De Premios, Nos Trae De La Carpa Grande Este Incrible Show Para Disfrutar En Casa El Tiempo Que Deseemos!!! Uno De Los Shows Mas Populares , Quidam, Que Se Grabo En Amsterdam Despues De Un Exitoso Tour Por America Del Norte.",
                "Cirque du Soleil"
            )
        )
        agregarPelicula(
            Pelicula(
                "Cirque du Soleil : Saltimbanco",
                getGenero("Familia"),
                "Creado como un antídoto para la violencia imperante en el siglo XX, este espectáculo propone una nueva visión de vida llena de optimísmo y alegría. Saltimbanco posee un lenguaje propio: La belleza de movimientos de los acróbatas, el doble trapecio, la doble cuerda floja, la pÃ©rtiga china, el columpio ruso, la correa elástica, los malabaristas y los payasos. Es la expresión del alma a travÃ©s de la voz, del cuerpo y de la música.",
                "Cirque du Soleil"
            )
        )
        agregarPelicula(
            Pelicula(
                "Cirque du Soleil : Varekai",
                getGenero("Familia"),
                "En lo profundo del bosque, en la cima de un volcán, existe un mundo extraordinario, un mundo donde todo es posible, un mundo llamado Varekai. De los cielos cae un hombre solitario, topándose en Varekai con fantásticas criaturas y viviendo una aventura que bordea los límites de lo absurdo y lo insólito.Haciendo gala del virtuosismo tÃ©cnico y estÃ©tico que caracteriza todos sus espectáculos, el Cirque du Soleil vuelve a las andadas en esta maravillosa producción para toda la familia.",
                "Cirque du Soleil"
            )
        )
        agregarPelicula(
            Pelicula(
                "Despedida de solteros",
                getGenero("Comedia"),
                "Las 24 horas que preceden a una boda pueden llegar a ser realmente agitadas. Entre otras cosas, los novios pueden disfrutar de la última juerga con los amigos, decir adiós a su libertad de solteros... Esta Despedida de Soltero será todo un ritual de mal comportamiento masculino. AutÃ©ntica diversión, risas y follón para la última noche de soltero del novio. Para la novia, por el contrario, habrá una despedida de soltera más tranquila. Pero para ambos hay dudas y ansiedades.",
                "Tom Hanks, Tawny Kitaen "
            )
        )
        agregarPelicula(
            Pelicula(
                "El hombre sin sombra",
                getGenero("Suspenso"),
                "Â¿Usted piensa que está solo?... Medítelo bien. Un equipo de jóvenes científicos trabaja en el descubrimiento de un compuesto químico que inyectado en la sangre permite ser invisible. Hasta el momento la investigación se ha realizado con Ã©xito en distintos animales y cuando el equipo presenta el proyecto al Pentágono, Sebastián, su director, oculta que el proyecto está ya listo para experimentar con seres humanos. Su objetivo es convertir un experimento científico en un arma de alto poder ya que Ã©l mismo se ofrece voluntario para probarlo.",
                "Elisabeth Shue, Kevin Bacon, Josh Brolin "
            )
        )
        agregarPelicula(
            Pelicula(
                "El mago de Oz",
                getGenero("Clasicos"),
                "Uno de los grandes clásicos del cine musical y una película legendaria que continúa conmoviendo, generación tras generación, a niños y mayores de todas las edades. La maravillosa tierra de Oz nos traslada a un mundo de fantasía de brujas y hadas, bosques encantados, espantapájaros que bailan y leones que cantan, que nos envuelve con su magia en una aventura repleta de canciones inolvidables. Un tesoro cinematográfico basado en los preciosos cuentos de L. Frank Baum, El Mago de Oz ha sido aclamado como la mejor película familiar de todos los tiempos por el Instituto Cinematográfico Americano. Te invitamos a seguir el camino de baldosas amarillas más famoso de la historia del cien que nos conduce a la Ciudad Esmeralda, hogar del poderoso Mago de Oz. Dorothy, el Espantapájaros en busca de un cerebro, el Hombre de Hojalata en busca de un corazón y un León Cobarde en busca de coraje, te esperan... más allá del arco iris..",
                "Judy Garland, Ray Bolger, Jack Haley "
            )
        )
        agregarPelicula(
            Pelicula(
                "El tunel del tiempo : Temporada 1",
                getGenero("Series"),
                "Pack 8 DVD's de la primera temporada con 15 episodiosEpisodios : Rendezvous with Yesterday - One Way to Moon - End of the World - The Day the Sky Fell In - The Last Patrol - The Crack of Doom - Revenge of the Gods - Massacre - Devil's Island - Reign of Terror - Secret Weapon - The Death Trap - The Alamo - Night of the Long Knives - Invasion!",
                "James Darren, Robert Colbert, Whit Bissell"
            )
        )
        agregarPelicula(
            Pelicula(
                "Halcon",
                getGenero("Accion"),
                "Lincoln Hawk es un camionero que no deja de pensar en su hijo, al que no ha visto desde su nacimiento. Ahora, Lincoln debe recogerlo en la academia militar de Virginia para llevarlo a Denver, donde su madre -de la que Lincoln está divorciado- agoniza. Pero el suegro de Lincoln no quiere que llegue. Ã‰sta es la historia de un padre que conquista el amor de su hijo, pese a que todo está contra Ã©l; un hombre con la ambición de ganar el concurso mundial de pulsos, contra todo pronóstico, y compartir sus sueños con su hijo.",
                "Sylvester Stallone, Robert Loggia, Susan Blakely "
            )
        )
        agregarPelicula(
            Pelicula(
                "He-man : Temporada 1 - Volumen 1",
                getGenero("Series"),
                "Pack 6 DVD's con 33 episodios de la famosa serie. Primer volumen parte de la primer temporada con mas de 11 Horas de Duracion.",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "He-man : Temporada 1 - Volumen 2",
                getGenero("Series"),
                "Pack 6 DVD's con 32 episodios de la famosa serie. Segundo volumen de la primer temporada con mas de 11 Horas de Duracion.",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Michael Jackson : Videos Greatest Hits History",
                getGenero("Musical"),
                "Contiene : Brace Yourself Billie Jean The Way You Make Me Feel (9:30 Long Version) Black Or White Rock With You Bad (18-minute Long Version) Thriller Beat It Remember The Time Don't Stop Til You Get Enough Heal The World . Material Adicional : The never before seen 18-minute version of Bad The rarely seen 9:30 version of The Way You Make Me Feel",
                "Michael Jackson"
            )
        )
        agregarPelicula(
            Pelicula(
                "Pinocho",
                getGenero("Infantil"),
                "Críticos de todo el mundo coinciden en que PINOCHO es una de las mejores películas Disney de todos los tiempos (ganadora de dos Oscar), dotada de una brillante animación y una magnífica banda sonora. toda la familia. Desde aquella mágica y estrellada noche en la que el Hada Azul conoce a Pinocho, la marioneta de Gepetto, la posibilidad de cobrar vida, hasta las peligrosas aventuras en alta mar, que pondrán a prueba su carácter, os sumergirÃ©is en cómicas escapadas, dramáticos rescates y lecciones de la vida que os recordarán la fuerza del amor a la familia a creer en vuestros propios sueños",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Robin Hood : Principe de Mendigos",
                getGenero("Aventuras"),
                "DespuÃ©s de escapar de Tierra Santa, Robin de Locksley y su amigo Azeem vuelven a Inglaterra despuÃ©s de muchos años. Cuando Robin llega a su patria, descubre que las cosas cambiaron. Su padre fue asesinado y su castillo quemado. El Rey está en una cruzada e Inglaterra está en manos del malvado y despótico Sheriff de Nottingham. Pero Robin Hood tomará venganza y tratará de combatir la tiranía del malvado monarca. (2 discos)",
                "Kevin Costner, Morgan Freeman, Mary Elizabeth Mastrantonio, Alan Rickman, Christian Slater, Sean Connery"
            )
        )
        agregarPelicula(
            Pelicula(
                "Stargate : Temporada 1",
                getGenero("Series"),
                "Pack 5 DVD's de la primera temporadaCapitulos :  La guarida de la serpiente - La llamada del deber - Prisioneros - El guarda - Necesidades - El carro de Thor - Mensaje en una botella - Familia - Secretos - Pesadilla - Espíritus - La Tokâ€™ra",
                "Amanda Tapping, Christopher Judge, Don S. Davis, Michael Shanks, Richard Dean Anderson "
            )
        )
        agregarPelicula(
            Pelicula(
                "Thundercats : Temporada 1 - Volumen 2",
                getGenero("Series"),
                "",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Top Secret",
                getGenero("Comedia"),
                "Si de repente te planteas que si los tipos que hicieron Aterriza como puedas y Agárralo como puedas están chiflados, la respuesta es sí. Y Top Secret lo demuestra sin duda alguna. Top Secret presenta a la estrella americana del rock Nick Rivers (Val Kilmer) enfrentándose a los temibles altos mandos de la Alemania del Este. Se trata de una carrera contrareloj en la que Nick ha de unirse a Hillary Flammond (Lucy Gutteridge) para encontrar a su padre antes de que pueda crear la super arma definitiva, la Mina Polaris. Al mismo tiempo, Top Secret pretende lograr para las películas bÃ©licas y las de Elvis lo mismo que Aterriza como puedas hizo por las cintas de catástrofes.",
                "Val Kilmer, Lucy Gutteridge, Jeremy Kemp "
            )
        )
        agregarPelicula(
            Pelicula(
                "Veo Veo en el Zoo : Volumen 1",
                getGenero("Infantil"),
                "La única serie de videos que estimulan a tu bebÃ© en forma integral, incentivando los dos modos de pensar:A travÃ©s de un fascinante viaje por el reino animal, Veo Veo en el Zoo estimula los dos hemisferios cerebrales de tu bebÃ© de la manera más didáctica y divertida.PENSAMIENTO CREATIVO- Creatividad- Inteligencia Emocional- Socialización- Idiomas y Lenguaje- Motricidad Fina- Hábitos Cotidianos",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "Veo Veo en el Zoo : Volumen 2",
                getGenero("Infantil"),
                "La única serie de videos que estimulan a tu bebÃ© en forma integral, incentivando los dos modos de pensar:A travÃ©s de un fascinante viaje por el reino animal, Veo Veo en el Zoo estimula los dos hemisferios cerebrales de tu bebÃ© de la manera más didáctica y divertida.PENSAMIENTO LOGICO- Memoria- Números en diferentes idiomas- Causa y Efecto- Opuestos- Motricidad Gruesa- Hábitos Cotidianos",
                "N/D"
            )
        )
        agregarPelicula(
            Pelicula(
                "El francotirador",
                getGenero("Accion"),
                "Ganadora de 5 Premios Oscar, incluyendo Mejor Película, Mejor Director y Mejor Actor de Reparto (Christopher Walken), este film aclamado por la crítica y extraordinariamente intenso, rastrea los pasos de un grupo de amigos que trabajan juntos en una fábrica de fundición de acero, desde la fría Pennsylvania a la letal caldera de Vietnam. Robert De Niro brinda una de sus más memorables actuaciones interpretando a Michael, el líder natural del grupo. El Francotirador es un hondo drama sobre la amistad y el coraje, y lo que sucede cuando estas cualidades se ponen duramente a prueba. Es una experiencia emocional demoledora que nunca olvidará.",
                "Robert De Niro, Meryl Streep, John Savage, Christopher Walken, John Cazale"
            )
        )
    }

    fun getGenero(descripcionGenero: String): Genero {
        var genero = generos[descripcionGenero]
        if (genero == null) {
            genero = Genero(descripcionGenero)
            generos.put(descripcionGenero, genero)
        }
        return genero
    }

    @JvmOverloads
    fun getPeliculas(titulo: String?, max: Int = MAX_RESULTS): List<Pelicula> {
        return peliculas.filter { pelicula -> pelicula.matchea(titulo) }.drop(max)
    }

    fun getPelicula(id: Long): Pelicula? {
        return peliculas.first { pelicula -> pelicula.id!!.equals(id) }
    }

    fun agregarPelicula(pelicula: Pelicula) {
        pelicula.id = maximoId
        peliculas.add(pelicula)
    }

}