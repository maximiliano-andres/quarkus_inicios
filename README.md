# quarkus-pi

Este proyecto utiliza Quarkus, el Supersónico y Subatómico Framework de Java.

Si deseas aprender más sobre Quarkus, por favor visita su sitio web: <https://quarkus.io/>.

## Índice

1. [Ejecución y empaquetado](#ejecución-y-empaquetado)
2. [Guías relacionadas](#guías-relacionadas)
3. [Estructura de carpetas](#estructura-de-carpetas)
4. [Comandos útiles y su función](#comandos-útiles-y-su-función)
5. [Generar claves RSA en Windows](#generar-claves-rsa-en-windows)

---

## Ejecución y empaquetado

### Ejecutar la aplicación en modo desarrollo

Puedes ejecutar tu aplicación en modo desarrollo, lo que habilita la codificación en vivo, usando:

```shell
./mvnw quarkus:dev
```
_Ejecuta la aplicación en modo desarrollo con recarga en caliente y acceso a la Dev UI._

### Empaquetar la aplicación

La aplicación puede ser empaquetada usando:

```shell
./mvnw package
```
_Compila y empaqueta la aplicación en el directorio `target/quarkus-app/`._

### Ejecutar el JAR generado

Puedes ejecutar el archivo JAR generado usando:

```shell
java -jar target/quarkus-app/quarkus-run.jar
```
_Ejecuta la aplicación empaquetada en modo estándar._

### Empaquetar como über-jar

Si deseas construir un _über-jar_, ejecuta:

```shell
./mvnw package -Dquarkus.package.jar.type=uber-jar
```
_Genera un über-jar ejecutable con todas las dependencias incluidas._

### Ejecutar el über-jar

La aplicación, empaquetada como un _über-jar_, se ejecuta con:

```shell
java -jar target/*-runner.jar
```
_Ejecuta el über-jar generado._

### Crear ejecutable nativo

Puedes crear un ejecutable nativo usando:

```shell
./mvnw package -Dnative
```
_Compila la aplicación como ejecutable nativo (requiere GraalVM)._ 

### Crear ejecutable nativo en contenedor

O, si no tienes GraalVM instalado, puedes construir el ejecutable nativo en un contenedor usando:

```shell
./mvnw package -Dnative -Dquarkus.native.container-build=true
```
_Compila el ejecutable nativo usando un contenedor (no requiere GraalVM local)._ 

### Ejecutar el ejecutable nativo

Luego puedes ejecutar tu ejecutable nativo con:

```shell
./target/quarkus-pi-1.0.0-SNAPSHOT-runner
```
_Ejecuta el binario nativo generado._

---

## Guías relacionadas

- REST Jackson ([guía](https://quarkus.io/guides/rest#json-serialisation)): Soporte de serialización Jackson para Quarkus REST. Esta extensión no es compatible con la extensión quarkus-resteasy, ni con ninguna de las extensiones que dependen de ella

---

## Estructura de carpetas

### Script para la estructura de carpetas (BÁSICA)

```shell
mkdir src\main\java\com\example\myapp\config src\main\java\com\example\myapp\controller src\main\java\com\example\myapp\service src\main\java\com\example\myapp\repository src\main\java\com\example\myapp\entity src\main\java\com\example\myapp\dto src\main\java\com\example\myapp\mapper src\main\java\com\example\myapp\exception src\main\java\com\example\myapp\util src\main\resources\META-INF\resources src\main\resources\db\migration src\main\docker src\test\java\com\example\myapp\controller src\test\java\com\example\myapp\service src\test\java\com\example\myapp\repository src\test\java\com\example\myapp\integration src\test\resources
```

### Script para la estructura de carpetas (AVANZADA)

```shell
mkdir src\main\java\com\example\myapp\application\service src\main\java\com\example\myapp\application\dto src\main\java\com\example\myapp\application\mapper src\main\java\com\example\myapp\domain\model src\main\java\com\example\myapp\domain\repository src\main\java\com\example\myapp\domain\service src\main\java\com\example\myapp\domain\exception src\main\java\com\example\myapp\infrastructure\persistence\entity src\main\java\com\example\myapp\infrastructure\persistence\repository src\main\java\com\example\myapp\infrastructure\persistence\mapper src\main\java\com\example\myapp\infrastructure\web src\main\java\com\example\myapp\infrastructure\config src\main\java\com\example\myapp\infrastructure\external src\main\java\com\example\myapp\shared\util src\main\java\com\example\myapp\shared\constant src\main\java\com\example\myapp\shared\validation src\main\resources\META-INF\resources src\main\resources\db\migration src\main\docker src\test\java\com\example\myapp\application src\test\java\com\example\myapp\domain src\test\java\com\example\myapp\infrastructure src\test\java\com\example\myapp\shared src\test\resources
```

---

## Comandos útiles y su función

| Comando | Función |
|---------|---------|
| `./mvnw quarkus:dev` | Ejecuta la app en modo desarrollo con recarga en caliente y Dev UI |
| `./mvnw package` | Compila y empaqueta la app en `target/` |
| `java -jar target/quarkus-app/quarkus-run.jar` | Ejecuta el JAR estándar generado |
| `./mvnw package -Dquarkus.package.jar.type=uber-jar` | Genera un über-jar ejecutable |
| `java -jar target/*-runner.jar` | Ejecuta el über-jar |
| `./mvnw package -Dnative` | Compila la app como ejecutable nativo (requiere GraalVM) |
| `./mvnw package -Dnative -Dquarkus.native.container-build=true` | Compila el ejecutable nativo usando contenedor |
| `./target/quarkus-pi-1.0.0-SNAPSHOT-runner` | Ejecuta el binario nativo generado |
| `mkdir ...` | Crea la estructura de carpetas recomendada para el proyecto |
| `openssl genrsa -out privateKey.pem 2048` | Genera una clave privada RSA de 2048 bits |
| `openssl rsa -in privateKey.pem -pubout -out publicKey.pem` | Extrae la clave pública del archivo privado |

---

## Generar claves RSA en Windows

### ✅ 1. Generar claves RSA en Windows (usando openssl)

Primero necesitas OpenSSL. Puedes instalarlo con:

- Instala Git for Windows, que incluye openssl.

Luego abre la terminal Git Bash (o PowerShell si agregaste OpenSSL al PATH).

Ejecuta:

```shell
openssl genrsa -out privateKey.pem 2048
openssl rsa -in privateKey.pem -pubout -out publicKey.pem
```

Esto generará:

- 🔐 privateKey.pem
- 🔓 publicKey.pem