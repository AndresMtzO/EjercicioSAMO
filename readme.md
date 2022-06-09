
# Prueba "The movie database"

Para entrevista tecnica




## Autor

- [@AndresMtzO](https://www.github.com/AndresMtzO)


## Stack/Librerias

- Kotlin
- Corutinas
- MVVM
- Dependency Injection (Koin)
- Jetpack Compose (Vistas)
- Retrofit (API Calls)
- Coil (Imagenes)
- Gson (JSON Parsing)



## Por Hacer

**Uso Offline:** Por falta de tiempo no se pudo implementar, aunque una posible solucion seria agregar una dependencia a la libreria de room para hacer uso de sqlite, crear los "daos" para guardar, obtener y eliminar los datos guardados de manera local y en el repositorio agregar metodos para hacer uso de ellos. Ademas de crear un servicio para consultar si hay conexion a la red y si ademas tenemos conexion a internet.

**Manejo de errores:** Podemos aplicar metodos de retrofit para cachar errores de conexion al servidor, o de red utilizando interceptors, asi como configurar la cache para utilizar menos datos (internet).

**Videos Incorporados a la app:** Actualmente se genera un intent para ver el video en la app de youtube. Se puede usar la api de youtube para obtener la url del archivo de video y reproducir directamente en la app desde exoplayer. Se requiere crear una llave API y consultar el servicio con la llave que obtenemos de themoviedb. 

**Unit Testing:** Al tener una estructura MVVM, podemos utilizar JUnit y mockito para realizar pruebas unitarias, jetpack compose tambien tiene una libreria para probar composables.

**Modulos:** Si hay codigo que pueda ser usado en otras apps, podemos crear modulos extra para desacoplar el codigo. Luego, debemos incluirlo en nuestro gradle para que lo compile junto a nuestro modulo :app