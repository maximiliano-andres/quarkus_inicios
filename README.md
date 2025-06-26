# quarkus-pi

Este proyecto utiliza Quarkus, el Supersónico y Subatómico Framework de Java.

Si deseas aprender más sobre Quarkus, por favor visita su sitio web: <https://quarkus.io/>.

## Ejecutar la aplicación en modo desarrollo

Puedes ejecutar tu aplicación en modo desarrollo, lo que habilita la codificación en vivo, usando:

```shell script
./mvnw quarkus:dev
```

> **_NOTA:_** Quarkus ahora incluye una Dev UI, que está disponible solo en modo desarrollo en <http://localhost:8080/q/dev/>.

## Empaquetar y ejecutar la aplicación

La aplicación puede ser empaquetada usando:

```shell script
./mvnw package
```

Esto produce el archivo `quarkus-run.jar` en el directorio `target/quarkus-app/`.
Ten en cuenta que no es un _über-jar_, ya que las dependencias se copian en el directorio `target/quarkus-app/lib/`.

La aplicación ahora se puede ejecutar usando `java -jar target/quarkus-app/quarkus-run.jar`.

Si deseas construir un _über-jar_, ejecuta el siguiente comando:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

La aplicación, empaquetada como un _über-jar_, ahora se puede ejecutar usando `java -jar target/*-runner.jar`.

## Crear un ejecutable nativo

Puedes crear un ejecutable nativo usando:

```shell script
./mvnw package -Dnative
```

O, si no tienes GraalVM instalado, puedes construir el ejecutable nativo en un contenedor usando:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

Luego puedes ejecutar tu ejecutable nativo con: `./target/quarkus-pi-1.0.0-SNAPSHOT-runner`

Si deseas aprender más sobre la construcción de ejecutables nativos, por favor consulta <https://quarkus.io/guides/maven-tooling>.

## Guías Relacionadas

- REST Jackson ([guía](https://quarkus.io/guides/rest#json-serialisation)): Soporte de serialización Jackson para Quarkus REST. Esta extensión no es compatible con la extensión quarkus-resteasy, ni con ninguna de las extensiones que dependen de ella

### Script para la estructura de carpetas (AVANZADA)

mkdir src\main\java\com\example\myapp\application\service src\main\java\com\example\myapp\application\dto src\main\java\com\example\myapp\application\mapper src\main\java\com\example\myapp\domain\model src\main\java\com\example\myapp\domain\repository src\main\java\com\example\myapp\domain\service src\main\java\com\example\myapp\domain\exception src\main\java\com\example\myapp\infrastructure\persistence\entity src\main\java\com\example\myapp\infrastructure\persistence\repository src\main\java\com\example\myapp\infrastructure\persistence\mapper src\main\java\com\example\myapp\infrastructure\web src\main\java\com\example\myapp\infrastructure\config src\main\java\com\example\myapp\infrastructure\external src\main\java\com\example\myapp\shared\util src\main\java\com\example\myapp\shared\constant src\main\java\com\example\myapp\shared\validation src\main\resources\META-INF\resources src\main\resources\db\migration src\main\docker src\test\java\com\example\myapp\application src\test\java\com\example\myapp\domain src\test\java\com\example\myapp\infrastructure src\test\java\com\example\myapp\shared src\test\resources


### Script para la estructura de carpetas (BASICA)

mkdir src\main\java\com\example\myapp\config src\main\java\com\example\myapp\controller src\main\java\com\example\myapp\service src\main\java\com\example\myapp\repository src\main\java\com\example\myapp\entity src\main\java\com\example\myapp\dto src\main\java\com\example\myapp\mapper src\main\java\com\example\myapp\exception src\main\java\com\example\myapp\util src\main\resources\META-INF\resources src\main\resources\db\migration src\main\docker src\test\java\com\example\myapp\controller src\test\java\com\example\myapp\service src\test\java\com\example\myapp\repository src\test\java\com\example\myapp\integration src\test\resources