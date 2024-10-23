# Conexion a bases de datos no relacionales con SpringBoot

## MongoDB
Es una base de datos NoSQL orientada a documentos, diseñada para almacenar, consultar y
gestionar datos no estructurados o semiestructurados. A diferencia de las bases de datos relacionales
que organizan los datos en tablas con filas y columnas. Mongo utiliza un formato basado en documentos similar a
objetos JSON llamados BSON, su única diferencia es la flexibilidad para manejar grandes cantidades de datos, en cuanto a formato son similares, 
y se agrupan los documentos en colecciones.

## Spring Data MongoDB
Spring Data MongoDB es un subproyecto que junto con los driver de conexion Mongodb driver sync y async proporciona la capacidad de 
conexion a la base de datos NoSQL MongoDB, existen diversos enfoques para interactuar con mongoDB desde springboot, desde el más nativo hasta el más abstracto:

### Enfoque driver nativo: 
#### Configuración de la conexion:
- **Creación de un cliente de forma programática:** Podemos interactuar directamente con el driver de conexion
de mongoDB directamente para crear un objeto MongoClient que nos permitirá interactuar directamente con comandos de mongodb, este enfoque
se puede utilizar fuera de spring framework (En un proyecto java puro):
    ```java
    //Uso del driver nativo de MongoDB
    MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
    MongoDatabase db = mongoClient.getDatabase("<DatabaseName>");
    MongoCollection<Document> collection = db.getCollection("<CollectionName>");
    ```
  Una vez tenemos el objeto MongoCollection podemos empezar a realizar consultas.

- **Utilizando un clase de configuración:** Podemos crear una clase de configuración que se encargará de configurar credenciales
necesarias para crear un objeto MongoClient. Este enfoque es parecido al primero pero orientado al IoC de spring framework.
  ```java
    @Configuration
    public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Bean
    @Override
    public MongoClient mongoClient() {
        MongoClientSettings settings = MongoClientSettings
                .builder()
                .applyConnectionString(new ConnectionString(mongoUri))
                .applyToConnectionPoolSettings(b -> b.maxWaitTime(30, TimeUnit.SECONDS).minSize(5).maxSize(10))
                .build();
        return MongoClients.create(settings);
    }

    @Override
    protected String getDatabaseName() {
        return "<DatabaseName>";
    }
  }
  ```
  Luego obtenemos el bean mediante IoC:
  ```java
    @Bean
    CommandLineRunner configurationMongoClient(@Autowired MongoClient mongoClient) {
        return args -> {
            var db = mongoClient.getDatabase("<DatabaseName>");
            var collection = db.getCollection("<CollectionName>");
        };
    }
  ```
- **Configuración mediante archivo de configuración principal :** Desde el archivo de configuración springboot es posible configurar las propiedades
iniciales de los beans y spring se encargará de levantar un proyecto con spring data MongoDB.
  ```properties
  spring.data.mongodb.uri=mongodb://localhost:27017
  ```
- Para el presente ejemplo basta con especificar la uri de conexion a mongodb, para aplicaciones donde se necesiten credenciales de seguridad se usa:
  ```properties
  spring.data.mongodb.password=<mongo_password>
  spring.data.mongodb.authentication-database=<auth_db>
  spring.data.mongodb.username=<mongo_username>
  spring.data.mongodb.port=27017
  spring.data.mongodb.database=<db_name>
  spring.data.mongodb.host=<host>
  ```
### Enfoque abstracto con repositorios:
#### Configuracion de la conexion:
- **Utilizando un clase de configuración:** Podemos crear una clase de configuración que se encargará de configurar credenciales
  necesarias para crear un objeto MongoClient. Este enfoque es parecido al primero pero orientado al IoC de spring framework.
  ```java
    @Configuration
    public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Bean
    @Override
    public MongoClient mongoClient() {
        MongoClientSettings settings = MongoClientSettings
                .builder()
                .applyConnectionString(new ConnectionString(mongoUri))
                .applyToConnectionPoolSettings(b -> b.maxWaitTime(30, TimeUnit.SECONDS).minSize(5).maxSize(10))
                .build();
        return MongoClients.create(settings);
    }

    @Override
    protected String getDatabaseName() {
        return "<DatabaseName>";
    }
  }
  ```
- **Configuración mediante archivo de configuración principal :** Desde el archivo de configuración springboot es posible configurar las propiedades
  iniciales de los beans y spring se encargará de levantar un proyecto con spring data MongoDB.
  ```properties
  spring.data.mongodb.uri=mongodb://localhost:27017
  ```
- Para el presente ejemplo basta con especificar la uri de conexion a mongodb, para aplicaciones donde se necesiten credenciales de seguridad se usa:
  ```properties
  spring.data.mongodb.password=<mongo_password>
  spring.data.mongodb.authentication-database=<auth_db>
  spring.data.mongodb.username=<mongo_username>
  spring.data.mongodb.port=27017
  spring.data.mongodb.database=<db_name>
  spring.data.mongodb.host=<host>
  ```
#### Operaciones e interacción:
***Para el enfoque nativo se utiliza directamente el objeto mongoClient***
- **Uso de mongoClient:** Es el objeto principal y de bajo nivel que se utiliza para interactuar con una base de datos MongoDB desde aplicaciones Java. Es parte de la API oficial de MongoDB para Java, y permite la conexión al servidor MongoDB,
  la ejecución de operaciones de lectura y escritura, y la administración de bases de datos y colecciones.
    ```java
    //Uso del driver nativo de MongoDB, es similar a interactuar directamente con la consola cli de mongo
    MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
    MongoDatabase db = mongoClient.getDatabase("<DatabaseName>");
    MongoCollection<Document> collection = db.getCollection("<CollectionName>");
    collection.insertOne(Document);
    collection.insertMany(List<Document>);
    collection.find(Document);
    collection.updateOne(Document);
    collection.updateMany(List<Document>);
    collection.deleteOne();
    collection.deleteMany(List<Document>);
    ```
    Estos metodos reciben documentos como parametros, es por ello que resulta ser similar a la forma de hacer operaciones con el cli de mongo, ejemplo:
    Busqueda en el cli de mongo: 
    ```mongodb-json
    use <DatabaseName>;
    db.getCollection("<CollectionName>").find(
    {
        "id":1,
        "key":"value"
    }
    );
    ```
    Busqueda utilizando mongoClient:
    ```java
    MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
    MongoDatabase db = mongoClient.getDatabase("<DatabaseName>");
    MongoCollection<Document> collection = db.getCollection("<CollectionName>");
    collection.find(new Document("id",1).append("key","value"));
    ```
    Esto se debe a la forma de trabajar de mongo con documentos.
- **Uso de un template:**
    Agregando un poco mas de abstraccion a la interacción con la base de datos, es posible utilizar plantillas de Querys y mapear directamente hacia objetos propios de la siguiente forma:
    ```java
    MongoTemplate m = new MongoTemplate(MongoClient,"<DatabaseName>");//Esto se puede omitir si se delega la creacion del objeto template a spring y simplemente se inyecta como dependencia
    Query query = new Query();
    query.addCriteria(Criteria.where("id").is(1))
                    .addCriteria(Criteria.where("key").is("value"));
    mongoTemplate.findOne(query, OwnCollectionObjet.class,"<CollectionName>");
    ```
#### **Enfoque de repositorios abstractos:**
Similar al uso del proyecyo Spring Data JPA, se necesitan de clases que modelen las colecciones de mongoDB, en este caso se indican con la anotacion de clase
@Document(collection="<CollectionName>"), para luego declarar las interfaces de repositorio con las operaciones CRUD necesarias.
De manera análoga, similar a los repositorios de JPA, se pueden declarar ***DerivedQueries***, que usan palabras reservadas para que el interprete de spring data genere
las consultas de forma automática, o usar las anotaciones proporcionadas por Spring data MongoDB (@Query para busquedas, @Update para actualizaciones,@CountQuery para operaciones de conteo, @DeleteQuery para eliminaciones, y @Aggregation para operaciones de agregacion complejas)
para declarar las consultas de manera nativa.
Ejemplo:
- ```java
  @Data
  @Document(collection = "persona")
  public class Persona {
  @MongoId(FieldType.INT64)//Tambien es posible utilizar la anotacion @Id para trabajar de manera genérica
    private Integer id;
    private String nombre;
    private String apellido;
    private String correo;
    private Integer edad;
    private List<Cuenta> cuentas;
  }
  @Data
  public class Cuenta {
    private String _id;
    private String tipoCuenta;
    private String banco;
    private double saldo;
  }

  @Repository
  public interface PersonaRepository extends MongoRepository<Persona, Integer> { 
    /*
    Declaracion de consultas derivadas
    Declaracion de consultas nativas mediante anotaciones 
    */
  }
    ```
### Documentación
Para mayor referencia, considere las siguientes secciones:
* [Spring Data MongoDB](https://docs.spring.io/spring-data/mongodb/reference/mongodb.html)

### Guías
Las siguientes guías ilustran cómo utilizar algunas funciones de forma concreta:
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)
