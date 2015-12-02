## Diseño

   ![Diagrama de clases](/documentation/class_diagram.png)

## Interface Dependency Injection (o Thin Cake Pattern)

Además de poder realizar inyección de dependencias por constructores y por setters, se puede hacer uso de
los *virtual default methods* para componer factories (Providers). Cada provider se encarga de producir componentes,
de manera similar a como lo haría un *AbstractFactory*.

   ![Thin Cake Pattern](/documentation/thin_cake_pattern.jpg)

En este caso, hay una interfaz `HotelRepositoryProvider` que es extendida por otras interfaces con métodos
virtuales default. Estos métodos se encargan de construir componentes `Repository<Hotel>` de manera transparente para
quien los incorpora.
En otras palabras, la forma en que se construyan las instancias de los componentes podrán variar según qué
implementación puntual de provider se esté aplicando en:

```java
protected void initializeDependencies() {
    hotelsFixture = newHotelRepository();
    roomReservationRepository = newReservationRepository();
}
```

### Provider (cake component)

 - **Single responsibility:** Cada provider se encarga de inicializar un único componente.
 - **Open/closed:** Para extender un componente es suficiente con crear otra interfaz que extienda de la interfaz original.
 - **Liskov substitution:** Dos providers del mismo componente pueden intercambiarse de manera transparente.
 - **Interface segregation:** Cada provider posee un único mensaje específico para el componente que permite instanciar.
 - **Dependency inversion:** Cada provider depende sólo de interfaces sin default methods.

## Repository Pattern

Un *Repository* es una ambstración sobre un contenedor de objetos de un tipo de terminado.
Permite encapslular la manera en que se guardan/persisten los objetos.
Posibles implementaciones podrían ser `CollectionBasedRepository`, `SQLBasedREpository`, `XMLBasedRepository`, `MongoBasedREpository`, etc.
De manera tal que el usuario de estos repositorios puede abstraerse de cada implementación particular.

 - **Single responsibility:** Un repository se encarga únicamente de contener entidades de negocio de un tipo particular permitiendo realizar busquedas sobre estas.
 - **Open/closed:** Para crear un nuevo repository es suficiente con implementar la interfaz `Repository<T>`.
 - **Liskov substitution:** Dos repositorios del mismo tipo son siempre intercambiables.
 - **Interface segregation:** La interfaz se mantiene minimalista (por esta misma razón, entre otras, un repository no extiende o es una collection nativa).
 - **Dependency inversion:** El tipo paramétrico de `Repository<T>` permite generalizar el repositorio para cualquier tipo, delegando la decisión en sus implementaciones.



## Composable Search

El search realiza la búsqueda sobre un repository, y filtra utilizando `Predicate<T>` de la siguiente manera:

```java
Search.over(hotelsFixture)
        .by(nameFilter("Barato").or(locationFilter("Antartida")));
```

![Predicate](/documentation/predicate.jpg)

Los `Predicate` pueden componerse usando los mensajes `and` y `or`. En este caso, `nameFilter` y `locationFilter` son
factories de predicados particulares.
Por ejempo:

```java
    public static Predicate<Room> nameFilter(final String hotelName){
        return (room)-> room.getHotel().getName().contains(hotelName);
    }
```

Agregando una parametrización de tipos al Search, es posible reutilizarlo para diferentes repositories:


```java
Search.over(reservationsRepository)
        .by(cityFilter("Bs As"));
```

La aplicación de los predicados sigue un patrón Composite, ya que pueden componerse jerárquicamente, gracias a que el
resultado de componer dos predicados es siempre otro predicado del mismo tipo

 - **Single responsibility:** El Predicate se encarga únicamente evaluar a un valor de verdad y componerse con otros predicados.
 - **Open/closed:** Para extender los predicados, alcanza con implementar su interfaz.
 - **Liskov substitution:** Dos predicados del mismo tipo son siempre intercambiables.
 - **Interface segregation:** La interfaz se mantiene minimalista y sólo contiene mensajes relacionados con su responsabilidad.
 - **Dependency inversion:** El tipo paramétrico de `Predicate<T>` permite generalizar el predicado para cualquier tipo, delegando la decisión en sus implementaciones.


References:

 - [Scalable Component Abstractions](http://lampwww.epfl.ch/~odersky/papers/ScalableComponent.pdf)

## Notificaciones de nuevas reservas

Un requerimiento del sistema es poder enviar correos electrónicos al pasajero y al hotel cada vez que ocurriera una reserva. Para lograr esto, hicimos uso del patrón **Observer**, ya que este es un caso típico de aplicación:

* por un lado tenemos al _sujeto_: la `RoomReservation`, de la cual nos va a interesar notificar sobre el evento de registro. Los objetos que estén interesados en recibir tal notificación deberán implementar una interfaz mínima, con un único mensaje `void sendNotification(RoomReservation reservation)`
* por el otro tenemos un componente encargado del envío de mails, al cual queremos avisarle de cada registro, pero no nos interesa su funcionamiento (podría enviar los mails en el momento, encolarlos y mandar de lotes de 5, funcionar una vez por día, incluso podría ser simplemente un wrapper de un servicio web, o cualquier otra variante que se nos pueda ocurrir)

Al elegir este patrón no sólo ganamos en desacoplamiento, sino que también logramos que nuestro código sea más fácil de testear. Si la idea de registrar una reserva y enviar un mail estuvieran acopladas, para poder probar deberíamos chequear que el mail _realmente_ fue enviado, involucrando un montón de cuestiones técnicas sobre el dominio de los correos electrónicos que no hacen más que estorbar y desviarnos de nuestro objetivo: probar que la reserva envia una notificación (sin tener en cuenta cuestiones como el tiempo que tardaría dicho test, la probabilidad de un falso negativo por problemas de red, etc).

Todos estos problemas desaparecen con nuestra implementación, ya que testear la notificación se vuelve trivial: basta con crear un **espía**, que implemente la interfase `Notifier` y registre las notificaciones que vaya recibiendo. El test quedaría así:

```java
@Test
public void theRoomNotifiesItsRegistration() {
    roomReservation.addNotifier(spyNotifier);
    roomReservation.register();

    assertTrue(spyNotifier.validateWasCalledWith(roomReservation));
}
```

Como la reserva no necesita saber nada de los _interesados_, basta con un espía, ya que únicamente nos interesa validar que la acción fue realizada.