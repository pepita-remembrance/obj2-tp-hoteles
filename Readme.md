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

Un *Repository*

 - **Single responsibility:** Un repository se encarga únicamente de contener entidades de negocio de un tipo particular permitiendo realizar busquedas sobre estas.
 - **Open/closed:** Para crear un nuevo repository es suficiente con implementar la interfaz `Repository<T>`.
 - **Liskov substitution:** Dos repositorios del mismo tipo son siempre intercambiables.
 - **Interface segregation:** La interfaz se mantiene minimalista (por esta misma razón, entre otras, un repository no extiende o es una collection nativa).
 - **Dependency inversion:** El tipo paramétrico de `Repository<T>` permite generalizar el repositorio para cualquier tipo, delegando la decisión en sus implementaciones.



## Composable Search


References:

 - [Scalable Component Abstractions](http://lampwww.epfl.ch/~odersky/papers/ScalableComponent.pdf)

