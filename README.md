# En postgres y el diagrama ER
- Todas las tablas están nombradas en plural.
- Los nombres están en inglés, si es una palabra rara, la traducción está entre paréntesis.
- Rutinas (routines) tiene un nombre diferente (wroutines, de workout routines), porque la palabra original es una palabra propia de SQL, por lo que no se debe utilizar.

# Conexión
## CredentialsDB
La información de conexión a la base de datos se obtiene desde este archivo. Es decir, el usuario, contraseña, host, nombre de la base de datos y puerto se deben especificar acá para que la conexiónse haga de forma exitosa. La estructura de dato utilizada es un mapa (o diccionario), así que solo se deben modificar los valores (values), NO las llaves (keys). Modificar las llaves hará que la conexión no sea posible.

## ConnectionDB
Contiene los comandos de conexión con la base de datos, y métodos que permiten comunicarse con la misma. Las credenciales de la base de datos se obtienen del archivo CredentialsDB, por lo que es importante asegurarse de que todos los campos de CredentialsDB tienen la información correcta. Por ejemplo, el puerto generalmente es 5432, pero en mi caso fue 5433. Modifique este dato si corresponde.

### executeQuery
Realiza una consulta en la base de datos y retorna como resultado una lista con cada registro de la consulta.

**Parámetro:** sql → es una String con la consulta que se quiere hacer. Puede especificar tablas en la consulta, y la lista de mapas solo tendrá las tablas requeridas.
```
executeQuery("SELECT * FROM users;)";
executeQuery("SELECT name, age, is_trainer, email FROM users;");
```

**Retorna:** Una lista de mapas ```List<Map<String, String>>```, donde cada elemento de la lista es un registro, y cada registro se representa con un mapa, cuya llave es el nombre de la columna, y el valor es el valor de la respectiva columna. Por ejemplo, un resultado posible para la consulta anterior sería de la forma:
```
// la lista la represento como [], y los mapas como {"llave", "valor}"
[
    {"name": "Pepe Grazon", "age": 25, "is_trainer": "false", "email": "pepegayzon@gmail.com"},
    {"name": "Andres Gaylor", "age": 13 "is_trainer": "true", "email":  "andygay@gmail.com"}
]
// Así, la consulta anterior, retornó 2 registros que pueden ser utilizados como mapas
```

### insertUpdateDelete
Para borrar, insertar o actualizar en la base de datos.
**Parámetro:** sql → La sentencia de SQL que se quiere ejecutar. Ya sea una inserción, atualización o eliminación en la base de datos.
```
insertUpdateDelete("INSERT INTO users(name, age, email, password) VALUES('Alma Marcela', 20, 'almarce@example.com', 'froggy123');");
```

### connectToDatabase
Es mejor no usarla, porque se prefiere el uso interno en los métodos anteriores. Sin embargo, si es necesario, este método retorna un objeto de tipo Connection que permite comunicarse con la base de datos actual. Si se utiliza, se recomienda cerrar la conexión después de realizar alguna acción.

## Utils
No es una clase directamente relacionada con la conexión, sin embargo, la clase principal de conexión ConnectionDB hace uso de algunos de los métodos acá definidos, que facilitan el procesamiento de la información para representarla. En algún momento, métodos como getColumnsNames o resultSetToList pueden resultar útiles fuera de ConnectionDB, no sobra tenerlos en cuenta.

# Modelos
Los modelos son las representaciones de las tablas en las clases de Java. La clase principal de modelo es Model, lo que significa que todas las subclases de Model (las tablas) tienen algunos métodos que son útiles. Como getAll (para obtener todos los registros de la tabla) o getById (para obtener un solo registro).
Además, todos los modelos cuentan con un método getDefault() que retorna un modelo básico con el objetivo de poder utilizar sus métodos de consulta sin necesidad de crear un objeto con todos los atributos únicamente para lograr el mismo objetivo.

# Don't mind 'bout this
```roomsql
SELECT a.attname, format_type(a.atttypid, a.atttypmod) AS data_type
FROM   pg_index i
JOIN   pg_attribute a ON a.attrelid = i.indrelid
AND a.attnum = ANY(i.indkey)
WHERE  i.indrelid = to_regclass('logs')
AND    i.indisprimary;
```
